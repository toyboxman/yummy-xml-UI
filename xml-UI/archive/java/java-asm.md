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

    @Override
    public String toString() {
        return "ClassA{" +
                "number=" + number +
                '}';
    }
}
```
2. 
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

将ClassA的toString修改成 `(number * 8)`
```java
@Override
public String toString() {
    return "ClassA{" +
            // 修改 "number=" + number +
            "number=" + (number * 8) +
            '}';
}
```
 * 先通过 TraceClassVisitor 来比较修改前后 toString方法字节码的差异

修改前：
```asm
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
```asm
public toString()Ljava/lang/String;
    NEW java/lang/StringBuilder
    DUP
    INVOKESPECIAL java/lang/StringBuilder.<init> ()V
    LDC "ClassA{number="
    INVOKEVIRTUAL java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
    ALOAD 0
    GETFIELD king/law/asm/src/ClassA.number : I
    //////////// 新增加的两条指令
    BIPUSH 8
    IMUL
    ////////////
    INVOKEVIRTUAL java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
    BIPUSH 125
    INVOKEVIRTUAL java/lang/StringBuilder.append (C)Ljava/lang/StringBuilder;
    INVOKEVIRTUAL java/lang/StringBuilder.toString ()Ljava/lang/String;
    ARETURN
    MAXSTACK = 3
    MAXLOCALS = 1
```
* 再对照指令差异来实现方法修改


Hopefully ASM can compute this for you. When you create a ClassWriter
you can specify what must be automatically computed:
? with new ClassWriter(0) nothing is automatically computed. You
have to compute yourself the frames and the local variables and operand
stack sizes.
? with new ClassWriter(ClassWriter.COMPUTE_MAXS) the sizes of the
local variables and operand stack parts are computed for you. You must
still call visitMaxs, but you can use any arguments: they will be ignored
and recomputed. With this option you still have to compute the frames
yourself.
? with new ClassWriter(ClassWriter.COMPUTE_FRAMES) everything is
computed automatically. You don’t have to call visitFrame, but you
must still call visitMaxs (arguments will be ignored and recomputed).
Using these options is convenient but this has a cost: the COMPUTE_MAXS option makes a ClassWriter 10% slower, and using the COMPUTE_FRAMES option
makes it two times slower. This must be compared to the time it would take
to compute this yourself: in specific situations there are often easier and faster
algorithms for computing this, compared to the algorithm used in ASM, which
must handle all cases.
Note that if you choose to compute the frames yourself, you can let the
ClassWriter class do the compression step for you. For this you just have to
visit your uncompressed frames with visitFrame(F_NEW, nLocals, locals,
44
3.2. Interfaces and components
nStack, stack), where nLocals and nStack are the number of locals and
the operand stack size, and locals and stack are arrays containing the corresponding types (see the Javadoc for more details).
Note also that, in order to compute frames automatically, it is sometimes
necessary to compute the common super class of two given classes. By default
the ClassWriter class computes this, in the getCommonSuperClass method,
by loading the two classes into the JVM and by using the reflection API. This
can be a problem if you are generating several classes that reference each other,
because the referenced classes may not yet exist. In this case you can override
the getCommonSuperClass method to solve this problem.

