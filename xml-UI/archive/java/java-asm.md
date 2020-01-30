***

## 修改编译后class文件

### ASM简介
ASM就是对java class文件进行处理的一套工具，按照其用户[文档描述](https://asm.ow2.io/asm4-guide.pdf),提供两种类型APIs，类似XML的SAX/DOM,前者基于event模型，速度快内存使用少，后者基于tree模型，对象结构构建一次完成。

Event模型的API包括event producers (the class parser), event consumers
(the class writer)和一些预定义event filters。

ASM提供以下几个5个lib
* asm.jar 提供基于event的API，包括class parser([ClassReader](https://asm.ow2.io/javadoc/org/objectweb/asm/ClassReader.html))和processor([ClassWriter](https://asm.ow2.io/javadoc/org/objectweb/asm/ClassWriter.html))等components.
* asm-util.jar 提供一些开发调试的工具如[TraceClassVisitor](https://asm.ow2.io/javadoc/org/objectweb/asm/util/TraceClassVisitor.html), [CheckClassAdapter](https://asm.ow2.io/javadoc/org/objectweb/asm/util/CheckClassAdapter.html)
* asm-commons.jar 提供一些有用的class transformers
* asm-tree.jar 提供基于object tree模型的API, 以及一些工具，用来转换class在event模型和object tree模型之间不同表达.
* asm-analysis.jar 提供class analysis framework和一些基于object tree模型的class analyzers.

### Event API 使用
#### 查看字节码
1. 配置项目依赖 
```yaml
<dependencies>
    <dependency>
        <groupId>org.ow2.asm</groupId>
        <artifactId>asm</artifactId>
        <version>7.3.1</version>
    </dependency>
    <dependency>
        <groupId>org.ow2.asm</groupId>
        <artifactId>asm-util</artifactId>
        <version>7.3.1</version>
    </dependency>
</dependencies>
```
2. 利用 ClassReader／TraceClassVisitor读出并输出class文件内容
```java 
package king.law.asm;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.util.TraceClassVisitor;

import java.io.IOException;
import java.io.PrintWriter;

public class PrintClass {
    public static void main(String[] args) throws IOException {
        System.out.println("********************************************");
        System.out.println();
        ／／1.解析原始的class文件，准备event producer
        ClassReader classReader = new ClassReader("java.lang.String");
        ／／2.准备event consumer
        TraceClassVisitor traceClassVisitor = new TraceClassVisitor(new PrintWriter(System.out));
        ／／3.producer accept consumer并按顺序调用各个visitXXX方法
        classReader.accept(traceClassVisitor,ClassReader.SKIP_DEBUG);
    }
}
``` 
3. visitXXX 方法调用顺序  
```yaml
visit visitSource? visitOuterClass? ( visitAnnotation | visitAttribute )*
( visitInnerClass | visitField | visitMethod )*
visitEnd
```
首先调用visit，至多调用一次visitSource, 接着至多调用一次visitOuterClass。接下来不同顺序调用多次visitAnnotation和visitAttribute,再不同顺序多次调用visitInnerClass, visitField和visitMethod。最后调用visitEnd结束对class文件浏览.

#### 修改Class层面字节码
1. 准备目标ClassA，然后通过转换将其package改成`king.law.asm.dest`
```java
package king.law.asm.src;

public class ClassA {
    private int number;

    public ClassA(int index) {
        this.number = index;
    }

    private int index() {
        return this.number;
    }

    @Override
    public String toString() {
        return "ClassA{" +
                "number=" + number +
                '}';
    }
}
```
2. 通过ClassWriter 修改字节码
```java
package king.law.asm;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.util.TraceClassVisitor;

import java.io.*;
import java.nio.*;

public class ConvertClass {
    public static void main(String[] args) throws IOException {

        System.out.println("********************************************");
        System.out.println();
        ClassReader classReader = new ClassReader("king.law.asm.src.ClassA");
        //1.准备writer类型visitor
        ClassWriter cw = new ClassWriter(0);
        classReader.accept(cw, ClassReader.SKIP_DEBUG);
        //2.再次visit来覆盖ClassA基本信息
        cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC,
                "king/law/asm/dest/ClassA0", null, "java/lang/Object",
                null);
        cw.visitEnd();
        ／／3.导出binary的class字节码
        byte[] bytes = cw.toByteArray();
        ／／4.创建dest目录
        Path dir = Paths.get("./target/classes/king/law/asm", "dest");
        Files.createDirectory(dir);
        ／／5.字节流写入ClassA0.class文件
        Path file = dir.resolve("ClassA0.class");
        Files.write(file, bytes,
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING,
                StandardOpenOption.WRITE);
    }
}
```

修改class文件后会改变frames,local variables和operand
stack sizes，如果希望ASM自动计算，创建ClassWriter时候需要指定不同参数.
- ClassWriter(0) 表示不会自动计算，需要在visitMaxs/visitFrame方法中计算修改。
- ClassWriter(ClassWriter.COMPUTE_MAXS) 表示 the sizes of the
local variables and operand stack会自动计算，但仍需要调用visitMaxs方法,不过传入的参数都会被忽略不会纳入计算。而the frames仍需要自己计算处理。
- ClassWriter(ClassWriter.COMPUTE_FRAMES) 表示所有都自动计算。不需要调用visitFrame,但必须显示调用visitMaxs(传入的参数都会被忽略不会纳入计算).

不同的参数带来不同运行效果: 一般情况下相比ClassWriter(0)，COMPUTE_MAXS option会使 ClassWriter 10% slower, COMPUTE_FRAMES option使 ClassWriter 两倍slower。

如果选择计算frames, 可以让ClassWriter class完成compression step，这样你就要调用visitFrame(F_NEW, nLocals, locals, nStack, stack)方法来visit uncompressed frames。nLocals和nStack是the number of locals and
the operand stack size。locals和stack是arrays，包含相关types。

为了自动计算frames，常常有必要计算两个给定类的common super class。默认ClassWriter在getCommonSuperClass方法中计算,通过加载two classes进JVM再使用reflection API。但这可能有问题，如果产生多个classes互相引用，因为被引用classes可能并不存在，因而这种情况下你要override方法getCommonSuperClass来解决问题。

#### 修改Method层面字节码
在方法层面的visitXXX 方法调用顺序如下，方法体由visitCode开始，visitEnd结束
```yaml
visitAnnotationDefault?
( visitAnnotation | visitParameterAnnotation | visitAttribute )*
( visitCode
( visitTryCatchBlock | visitLabel | visitFrame | visitXxxInsn |
visitLocalVariable | visitLineNumber )*
visitMaxs )?
visitEnd
```
修改方法体又分为stateless和stateful两种类型
1. stateless

字节码transformation只在当前方法中，不依赖之前已经visited过的指令。比如加到方法开始位置的新指令和插入RETURN instruction之前的新指令都属于stateless transformations。

将ClassA的toString代码做如下修改
```java
import java.util.Random;
// remove掉index()
//private int index() {...}

@Override
public String toString() {
    // 1.增加 println
    System.out.println(this.number);
    return "ClassA{" +
            // 2.修改 "number=" + number +
            "number=" + (number * new Random().nextInt()) +
            '}';
}
```
 * 先通过 TraceClassVisitor 来比较修改前后 toString方法字节码的差异

修改前：
```java
public toString()Ljava/lang/String;
    NEW java/lang/StringBuilder
    DUP
    INVOKESPECIAL java/lang/StringBuilder.<init> ()V
    LDC "ClassA{number="
    INVOKEVIRTUAL java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
    ALOAD 0
    GETFIELD king/law/asm/src/ClassA.number : I
    INVOKEVIRTUAL java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
    BIPUSH 125
    INVOKEVIRTUAL java/lang/StringBuilder.append (C)Ljava/lang/StringBuilder;
    INVOKEVIRTUAL java/lang/StringBuilder.toString ()Ljava/lang/String;
    ARETURN
    MAXSTACK = 2
    MAXLOCALS = 1
```

修改后：
```java
public toString()Ljava/lang/String;
    //////////// 新增 println(...) 指令
    GETSTATIC java/lang/System.out : Ljava/io/PrintStream;
    ALOAD 0
    GETFIELD king/law/asm/src/ClassA.number : I
    INVOKEVIRTUAL java/io/PrintStream.println (I)V
    ////////////
    NEW java/lang/StringBuilder
    DUP
    INVOKESPECIAL java/lang/StringBuilder.<init> ()V
    LDC "ClassA{number="
    INVOKEVIRTUAL java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
    ALOAD 0
    GETFIELD king/law/asm/src/ClassA.number : I
    //////////// 新增 (number * new Random().nextInt()) 指令
    NEW java/util/Random
    DUP
    INVOKESPECIAL java/util/Random.<init> ()V
    INVOKEVIRTUAL java/util/Random.nextInt (I)I
    IMUL
    ////////////
    INVOKEVIRTUAL java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
    BIPUSH 125
    INVOKEVIRTUAL java/lang/StringBuilder.append (C)Ljava/lang/StringBuilder;
    INVOKEVIRTUAL java/lang/StringBuilder.toString ()Ljava/lang/String;
    ARETURN
    //////////// stack size扩大到4
    MAXSTACK = 4
    MAXLOCALS = 1   
    ////////////
```
* 再对照指令差异来实现方法修改
```java
package king.law.asm;

import king.law.asm.src.ClassA;
import org.objectweb.asm.*;

import java.lang.reflect.Constructor;

//定制visitor，来实现方法修改
class ClsWr extends ClassVisitor {
    public ClsWr(int flags) {
        //ClassVisitor是proxy模式，必须由ClassWriter完成实际字节操作
        super(/* latest api = */ Opcodes.ASM7, new ClassWriter(flags));
    }

    @Override
    public MethodVisitor visitMethod(int access, String name,
                                     String descriptor, String signature, String[] exceptions) {
        // private int index(){} 字节码 private index()I
        // name为index,  descriptor为()I
        if (access == Opcodes.ACC_PRIVATE
                && name.equals("index")
                && descriptor.equals("()I")) {
            // do not delegate to next visitor -> this removes the method
            return null;
        }
        MethodVisitor mv = super.visitMethod(access, name, descriptor, signature, exceptions);
        if (name.equals("toString")) {
            mv = new MtWr(mv);
        }
        return mv;
    }

    public byte[] toByteArray() {
        return ((ClassWriter) cv).toByteArray();
    }
}

//定制visitor，来实现方法体修改
class MtWr extends MethodVisitor {
    private MethodVisitor mv = null;

    public MtWr(MethodVisitor impl) {
        super(/* latest api = */ Opcodes.ASM7, impl);
        mv = impl;
    }

    @Override
    public void visitCode() {
        //1.开始进入方法体
        super.visitCode();
        //2.GETSTATIC java/lang/System.out : Ljava/io/PrintStream;
        mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System",
                "out", "Ljava/io/PrintStream;");
        //3. ALOAD 0
        mv.visitVarInsn(Opcodes.ALOAD, 0);
        //4.GETFIELD king/law/asm/src/ClassA.number : I
        mv.visitFieldInsn(Opcodes.GETFIELD, "king/law/asm/src/ClassA",
                "number", "I");
        //5.INVOKEVIRTUAL java/io/PrintStream.println (I)V
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream",
                "println", "(I)V", false);
    }

    @Override
    public void visitFieldInsn(int opcode, String owner, String name, String descriptor) {
        mv.visitFieldInsn(opcode, owner, name, descriptor);
        //6.定位到 GETFIELD king/law/asm/src/ClassA.number : I
        if (opcode == Opcodes.GETFIELD) {
            //7.NEW java/util/Random
            //import statement在class文件中直接映射到指令中
            mv.visitTypeInsn(Opcodes.NEW, "java/util/Random");
            //8.DUP
            mv.visitInsn(Opcodes.DUP);
            //9.INVOKESPECIAL java/util/Random.<init> ()V
            mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/util/Random",
                    "<init>", "()V", false);
            //10.INVOKEVIRTUAL java/util/Random.nextInt (I)I
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/util/Random",
                    "nextInt", "()I", false);
            //12.IMUL
            mv.visitInsn(Opcodes.IMUL);
        }
    }

    @Override
    public void visitMaxs(int maxStack, int maxLocals) {
        //修改前 MAXSTACK = 2 MAXLOCALS = 1
        //修改后 MAXSTACK = 4 MAXLOCALS = 1
        //12.重新计算栈和本地变量大小
        //new ClassWriter(0)不会自动计算，COMPUTE_MAXS作为参数能自动计算
        mv.visitMaxs(maxStack + 2, maxLocals);
    }
}

//定制ClassLoader载入修改后的class文件
class UpdateClassLoader extends ClassLoader {
    //可以直接调用defineClass来装载class文件
    public Class defineClass(String name, byte[] b) {
        //ClassLoader.defineClass是protected final,无法override
        //只能通过proxy方式调用
        return super.defineClass(name, b, 0, b.length);
    }

    //也可以通过覆写findClass方式来装载class文件
    @Override
    protected Class<?> findClass(String name)
            throws ClassNotFoundException {
        if (name.equals("king.law.asm.src.ClassA")) {
            ClassWriter cw = new ClassWriter(0);
            byte[] b = cw.toByteArray();
            return defineClass(name, b, 0, b.length);
        }
        return super.findClass(name);
    }
}

public class UpdateClass {
    public static void main(String[] args) throws Exception {
        //检查默认classloader中ClassA的toString/index两个方法
        System.out.println("original ClassA(5) is : " + new ClassA(5));
        System.out.println("has index() method? : " + ClassA.class.getDeclaredMethod("index"));
        System.out.println("--------------------------");
        ClassReader classReader = new ClassReader("king.law.asm.src.ClassA");
        ClassVisitor cw = new ClsWr(0);
        classReader.accept(cw, ClassReader.SKIP_DEBUG);

        byte[] bytes = ((ClsWr) cw).toByteArray();
        Class updatedClassA = new UpdateClassLoader()
                .defineClass("king.law.asm.src.ClassA", bytes);
        Constructor constructor = updatedClassA.getConstructor(Integer.TYPE);//等于 int.class
        Object updatedObj = constructor.newInstance(5);
        //检查修改后ClassA的toString/index两个方法
        System.out.println("updated ClassA(5) is : " + updatedObj);
        //private方法需要通过getDeclaredMethod获取
        System.out.println("has index() method? : " + updatedClassA.getDeclaredMethod("index"));
    }
}
```
* 运行以上程序检查输出
```console
original ClassA(5) is : ClassA{number=5}
has index() method? : private int king.law.asm.src.ClassA.index()
--------------------------
5
updated ClassA(5) is : ClassA{number=40}
Exception in thread "main" java.lang.NoSuchMethodException: king.law.asm.src.ClassA.index()
	at java.lang.Class.getDeclaredMethod(Class.java:2130)
	at king.law.asm.UpdateClass.main(UpdateClass.java:104)

```
5是由println输出，40也是5*8结果，index()方法不存在抛出异常，说明对ClassA的修改符合预期。

2. stateful

一种较复杂的transformations，需要缓存住一些之前visited过的指令状态。比如这种转换，把所有`ICONST_0 IADD`指令sequence都删除,这个指令执行`add 0`操作。当一个IADD instruction被visited, 只要上一个指令是ICONST_0，就需要把这二者都删除。这种类型转换都可以称为statefull transformations。

考虑上面这个例子的具体实现，当ICONST_0被visited时,下一个instruction是什么不知道。因此需要postpone当前指令处理到下一个visit的instruction，如果下一个指令是IADD，那么删除这两个instructions；否者正常处理前一条ICONST_0和当前instruction。

- 定义抽象基类存储指令状态
```java
public abstract class PatternMethodAdapter extends MethodVisitor {
    //constant 标识ICONST_0指令未出现
    protected final static int SEEN_NOTHING = 0;
    //flag visit方法是否碰到ICONST_0指令的状态
    protected int state;
    public PatternMethodAdapter(int api, MethodVisitor mv) {
        super(api, mv);
    }
    @Overrid public void visitInsn(int opcode) {
        //处理ICONST_0/IADD指令前先调用状态处理方法
        visitInsn();
        mv.visitInsn(opcode);
    }
    @Override public void visitIntInsn(int opcode, int operand) {
        //处理BIPUSH/SIPUSH/NEWARRAY指令前先调用状态处理方法
        visitInsn();
        mv.visitIntInsn(opcode, operand);
    }
    ...
    //根据state对instruction特殊处理
    protected abstract void visitInsn();
}
```
- 定义实现类
```java
public class RemoveAddZeroAdapter extends PatternMethodAdapter {
    //constant 标识ICONST_0指令出现
    private static int SEEN_ICONST_0 = 1;
    public RemoveAddZeroAdapter(MethodVisitor mv) {
        super(ASM4, mv);
    }
    @Override 
    public void visitInsn(int opcode) {
        if (state == SEEN_ICONST_0) {
            //如果已经出现ICONST_0
            if (opcode == IADD) {
                //并且当前指令是IADD
                //放弃处理此两条指令，置state为未出现ICONST_0
                state = SEEN_NOTHING;
                return;
            }
        }
        //如果没有出现ICONST_0+IADD两条指令，执行state检查处理
        visitInsn();
        if (opcode == ICONST_0) {
            //如果出现ICONST_0指令，置state为出现
            //并暂时返回不处理ICONST_0指令
            state = SEEN_ICONST_0;
            return;
        }
        //处理当前指令
        mv.visitInsn(opcode);
    }
    //根据state对instruction特殊处理
    @Override 
    protected void visitInsn() {
        if (state == SEEN_ICONST_0) {
            //如果state为ICONST_0，处理前面暂存的指令
            mv.visitInsn(ICONST_0);
        }
        state = SEEN_NOTHING;
    }
}
```
label和frames会在它们相关instruction之前被visit，虽然这二者本身不是指令。这会给transformations带来影响， 比如要删除的是一个跳转到的指令ICONST_0，这意味有一个label对应一个跳转指令。如果ICONST_0+IADD两条指令被删除后，这个跳转指令就会指向IADD后面那条指令。 但如果跳转指令是到IADD，由于不能确定在jump之前，a 0被压入stack，因此不能删除这两个指令序列。同样对于stack map frames，如果其在两个指令间被visit，这两指令也不能被删除。这两种情况下，就需要考虑将更多visitXXX加入
visitInsn method如下：
```java
public abstract class PatternMethodAdapter extends MethodVisitor {
...
    @Override public void visitFrame(int type, int nLocal, Object[] local,
    int nStack, Object[] stack) {
        visitInsn();
        mv.visitFrame(type, nLocal, local, nStack, stack);
    }
    @Override public void visitLabel(Label label) {
        visitInsn();
        mv.visitLabel(label);
    }
    @Override public void visitMaxs(int maxStack, int maxLocals) {
        visitInsn();
        mv.visitMaxs(maxStack, maxLocals);
    }
}
```
