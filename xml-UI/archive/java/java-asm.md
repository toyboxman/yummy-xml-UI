***

## �޸ı����class�ļ�

### ASM���
ASM���Ƕ�java class�ļ����д�����һ�׹��ߣ��������û�[�ĵ�����](https://asm.ow2.io/asm4-guide.pdf),�ṩ��������APIs������XML��SAX/DOM,ǰ�߻���eventģ�ͣ��ٶȿ��ڴ�ʹ���٣����߻���treeģ�ͣ�����ṹ����һ����ɡ�

Eventģ�͵�API����event producers (the class parser), event consumers
(the class writer)��һЩԤ����event filters��

ASM�ṩ���¼���5��lib
* asm.jar �ṩ����event��API������class parser([ClassReader](https://asm.ow2.io/javadoc/org/objectweb/asm/ClassReader.html))��processor([ClassWriter](https://asm.ow2.io/javadoc/org/objectweb/asm/ClassWriter.html))��components.
* asm-util.jar �ṩһЩ�������ԵĹ�����[TraceClassVisitor](https://asm.ow2.io/javadoc/org/objectweb/asm/util/TraceClassVisitor.html), [CheckClassAdapter](https://asm.ow2.io/javadoc/org/objectweb/asm/util/CheckClassAdapter.html)
* asm-commons.jar �ṩһЩ���õ�class transformers
* asm-tree.jar �ṩ����object treeģ�͵�API, �Լ�һЩ���ߣ�����ת��class��eventģ�ͺ�object treeģ��֮�䲻ͬ����.
* asm-analysis.jar �ṩclass analysis framework��һЩ����object treeģ�͵�class analyzers.

+ [**�鿴class�ļ��ֽ���**](#exp1)
+ [**ͨ��visit�޸�class�ļ����ṹ**](#exp2)
+ [**ͨ��visitɾ�����޸ķ���ʵ��**](#exp3)
+ [**ͨ��visit�޸���������ط���ʵ��**](#exp4)
+ [**ͨ��visitʵ��try/catch��������**](#exp5)
+ [**ͨ��ClassNode�޸�package��class name**](#exp6)
+ [**ͨ��MethodNodeɾ��index�޸�toString**](#exp7)

### Event API ʹ��
<div id = "exp1"></div>

#### �鿴�ֽ���
1. ������Ŀ���� 
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
2. ���� ClassReader��TraceClassVisitor���������class�ļ����� [PrintClass](sample/king/law/asm/PrintClass.java)
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
        ����1.����ԭʼ��class�ļ���׼��event producer
        ClassReader classReader = new ClassReader("java.lang.String");
        ����2.׼��event consumer
        TraceClassVisitor traceClassVisitor = new TraceClassVisitor(new PrintWriter(System.out));
        ����3.producer accept consumer����˳����ø���visitXXX����
        classReader.accept(traceClassVisitor,ClassReader.SKIP_DEBUG);
    }
}
``` 
3. visitXXX ��������˳��  
```yaml
visit visitSource? visitOuterClass? ( visitAnnotation | visitAttribute )*
( visitInnerClass | visitField | visitMethod )*
visitEnd
```
���ȵ���visit���������һ��visitSource, �����������һ��visitOuterClass����������ͬ˳����ö��visitAnnotation��visitAttribute,�ٲ�ͬ˳���ε���visitInnerClass, visitField��visitMethod��������visitEnd������class�ļ����.
<div id = "exp2"></div>

#### �޸�Class�����ֽ���
1. ׼��Ŀ��ClassA��Ȼ��ͨ��ת������package�ĳ�`king.law.asm.dest`
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
2. ͨ��ClassWriter �޸��ֽ��� [ConvertClass](sample/king/law/asm/ConvertClass.java)
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
        //1.׼��writer����visitor
        ClassWriter cw = new ClassWriter(0);
        classReader.accept(cw, ClassReader.SKIP_DEBUG);
        //2.�ٴ�visit������ClassA������Ϣ
        cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC,
                "king/law/asm/dest/ClassA0", null, "java/lang/Object",
                null);
        cw.visitEnd();
        ����3.����binary��class�ֽ���
        byte[] bytes = cw.toByteArray();
        ����4.����destĿ¼
        Path dir = Paths.get("./target/classes/king/law/asm", "dest");
        Files.createDirectory(dir);
        ����5.�ֽ���д��ClassA0.class�ļ�
        Path file = dir.resolve("ClassA0.class");
        Files.write(file, bytes,
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING,
                StandardOpenOption.WRITE);
    }
}
```

�޸�class�ļ����ı�frames,local variables��operand
stack sizes�����ϣ��ASM�Զ����㣬����ClassWriterʱ����Ҫָ����ͬ����.
- ClassWriter(0) ��ʾ�����Զ����㣬��Ҫ��visitMaxs/visitFrame�����м����޸ġ�
- ClassWriter(ClassWriter.COMPUTE_MAXS) ��ʾ the sizes of the
local variables and operand stack���Զ����㣬������Ҫ����visitMaxs����,��������Ĳ������ᱻ���Բ���������㡣��the frames����Ҫ�Լ����㴦����
- ClassWriter(ClassWriter.COMPUTE_FRAMES) ��ʾ���ж��Զ����㡣����Ҫ����visitFrame,��������ʾ����visitMaxs(����Ĳ������ᱻ���Բ����������).

��ͬ�Ĳ���������ͬ����Ч��: һ����������ClassWriter(0)��COMPUTE_MAXS option��ʹ ClassWriter 10% slower, COMPUTE_FRAMES optionʹ ClassWriter ����slower��

���ѡ�����frames, ������ClassWriter class���compression step���������Ҫ����visitFrame(F_NEW, nLocals, locals, nStack, stack)������visit uncompressed frames��nLocals��nStack��the number of locals and
the operand stack size��locals��stack��arrays���������types��

Ϊ���Զ�����frames�������б�Ҫ���������������common super class��Ĭ��ClassWriter��getCommonSuperClass�����м���,ͨ������two classes��JVM��ʹ��reflection API��������������⣬����������classes�������ã���Ϊ������classes���ܲ������ڣ���������������Ҫoverride����getCommonSuperClass��������⡣

#### �޸�Method�����ֽ���
�ڷ��������visitXXX ��������˳�����£���������visitCode��ʼ��visitEnd����
```yaml
visitAnnotationDefault?
( visitAnnotation | visitParameterAnnotation | visitAttribute )*
( visitCode
( visitTryCatchBlock | visitLabel | visitFrame | visitXxxInsn |
visitLocalVariable | visitLineNumber )*
visitMaxs )?
visitEnd
```
<div id = "exp3"></div>
�޸ķ������ַ�Ϊstateless��stateful��������
1. stateless

�ֽ���transformationֻ�ڵ�ǰ�����У�������֮ǰ�Ѿ�visited����ָ�����ӵ�������ʼλ�õ���ָ��Ͳ���RETURN instruction֮ǰ����ָ�����stateless transformations��

��ClassA��toString�����������޸�
```java
import java.util.Random;
// remove��index()
//private int index() {...}

@Override
public String toString() {
    // 1.���� println
    System.out.println(this.number);
    return "ClassA{" +
            // 2.�޸� "number=" + number +
            "number=" + (number * new Random().nextInt()) +
            '}';
}
```
 * ��ͨ�� TraceClassVisitor ���Ƚ��޸�ǰ�� toString�����ֽ���Ĳ���

�޸�ǰ��
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

�޸ĺ�
```java
public toString()Ljava/lang/String;
    //////////// ���� println(...) ָ��
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
    //////////// ���� (number * new Random().nextInt(5)) ָ��
    NEW java/util/Random
    DUP
    INVOKESPECIAL java/util/Random.<init> ()V
    ICONST_5
    INVOKEVIRTUAL java/util/Random.nextInt (I)I
    IMUL
    ////////////
    INVOKEVIRTUAL java/lang/StringBuilder.append (I)Ljava/lang/StringBuilder;
    BIPUSH 125
    INVOKEVIRTUAL java/lang/StringBuilder.append (C)Ljava/lang/StringBuilder;
    INVOKEVIRTUAL java/lang/StringBuilder.toString ()Ljava/lang/String;
    ARETURN
    //////////// stack size����4
    MAXSTACK = 4
    MAXLOCALS = 1   
    ////////////
```
* �ٶ���ָ�������ʵ�ַ����޸� [UpdateClass](sample/king/law/asm/UpdateClass.java)
```java
package king.law.asm;

import king.law.asm.src.ClassA;
import org.objectweb.asm.*;

import java.lang.reflect.Constructor;

//����visitor����ʵ�ַ����޸�
class ClsWr extends ClassVisitor {
    public ClsWr(int flags) {
        //ClassVisitor��proxyģʽ��������ClassWriter���ʵ���ֽڲ���
        super(/* latest api = */ Opcodes.ASM7, new ClassWriter(flags));
    }

    @Override
    public MethodVisitor visitMethod(int access, String name,
                                     String descriptor, String signature, String[] exceptions) {
        // private int index(){} �ֽ��� private index()I
        // nameΪindex,  descriptorΪ()I
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

//����visitor����ʵ�ַ������޸�
class MtWr extends MethodVisitor {
    private MethodVisitor mv = null;

    public MtWr(MethodVisitor impl) {
        super(/* latest api = */ Opcodes.ASM7, impl);
        mv = impl;
    }

    @Override
    public void visitCode() {
        //1.��ʼ���뷽����
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
        //6.��λ�� GETFIELD king/law/asm/src/ClassA.number : I
        if (opcode == Opcodes.GETFIELD) {
            //7.NEW java/util/Random
            //import statement��class�ļ���ֱ��ӳ�䵽ָ����
            mv.visitTypeInsn(Opcodes.NEW, "java/util/Random");
            //8.DUP
            mv.visitInsn(Opcodes.DUP);
            //9.INVOKESPECIAL java/util/Random.<init> ()V
            mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/util/Random",
                    "<init>", "()V", false);
            //10.ICONST_5
            mv.visitInsn(Opcodes.ICONST_5);
            //11.INVOKEVIRTUAL java/util/Random.nextInt (I)I
            mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/util/Random",
                    "nextInt", "(I)I", false);
            //12.IMUL
            mv.visitInsn(Opcodes.IMUL);
        }
    }

    @Override
    public void visitMaxs(int maxStack, int maxLocals) {
        //�޸�ǰ MAXSTACK = 2 MAXLOCALS = 1
        //�޸ĺ� MAXSTACK = 4 MAXLOCALS = 1
        //13.���¼���ջ�ͱ��ر�����С
        //new ClassWriter(0)�����Զ����㣬COMPUTE_MAXS��Ϊ�������Զ�����
        mv.visitMaxs(maxStack + 2, maxLocals);
    }
}

//����ClassLoader�����޸ĺ��class�ļ�
class UpdateClassLoader extends ClassLoader {
    //����ֱ�ӵ���defineClass������װ��class�ļ�
    public Class defineClass(String name, byte[] b) {
        //����ClassLoader.defineClass��protected final,�޷�override
        //ֻ��ͨ������proxy��ʽ����
        return super.defineClass(name, b, 0, b.length);
    }

    //Ҳ����ͨ����дfindClass��ʽ��װ��class�ļ�
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
        //���Ĭ��classloader��ClassA��toString/index��������
        System.out.println("original ClassA(5) is : " + new ClassA(5));
        System.out.println("has index() method? : " + ClassA.class.getDeclaredMethod("index"));
        System.out.println("--------------------------");
        ClassReader classReader = new ClassReader("king.law.asm.src.ClassA");
        ClassVisitor cw = new ClsWr(0);
        classReader.accept(cw, ClassReader.SKIP_DEBUG);

        byte[] bytes = ((ClsWr) cw).toByteArray();
        Class updatedClassA = new UpdateClassLoader()
                .defineClass("king.law.asm.src.ClassA", bytes);
        Constructor constructor = updatedClassA.getConstructor(Integer.TYPE);//���� int.class
        Object updatedObj = constructor.newInstance(5);
        //����޸ĺ�ClassA��toString/index��������
        System.out.println("updated ClassA(5) is : " + updatedObj);
        //private������Ҫͨ��getDeclaredMethod��ȡ
        System.out.println("has index() method? : " + updatedClassA.getDeclaredMethod("index"));
    }
}
```
* �������ϳ��������
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
5����println�����40Ҳ��5*8�����index()�����������׳��쳣��˵����ClassA���޸ķ���Ԥ�ڡ�

<div id = "exp4"></div>

2. stateful

һ�ֽϸ��ӵ�transformations����Ҫ����סһЩ֮ǰvisited����ָ��״̬����������ת����������`ICONST_0 IADD`ָ��sequence��ɾ��,���ָ��ִ��`add 0`��������һ��IADD instruction��visited, ֻҪ��һ��ָ����ICONST_0������Ҫ������߶�ɾ������������ת�������Գ�Ϊstatefull transformations��

��������������ӵľ���ʵ�֣���ICONST_0��visitedʱ,��һ��instruction��ʲô��֪���������Ҫpostpone��ǰָ�������һ��visit��instruction�������һ��ָ����IADD����ôɾ��������instructions��������������ǰһ��ICONST_0�͵�ǰinstruction��

- ����������洢ָ��״̬
```java
public abstract class PatternMethodAdapter extends MethodVisitor {
    //constant ��ʶICONST_0ָ��δ����
    protected final static int SEEN_NOTHING = 0;
    //flag visit�����Ƿ�����ICONST_0ָ���״̬
    protected int state;
    public PatternMethodAdapter(int api, MethodVisitor mv) {
        super(api, mv);
    }
    @Overrid public void visitInsn(int opcode) {
        //����ICONST_0/IADDָ��ǰ�ȵ���״̬��������
        visitInsn();
        mv.visitInsn(opcode);
    }
    @Override public void visitIntInsn(int opcode, int operand) {
        //����BIPUSH/SIPUSH/NEWARRAYָ��ǰ�ȵ���״̬��������
        visitInsn();
        mv.visitIntInsn(opcode, operand);
    }
    ...
    //����state��instruction���⴦��
    protected abstract void visitInsn();
}
```
- ����ʵ����
```java
public class RemoveAddZeroAdapter extends PatternMethodAdapter {
    //constant ��ʶICONST_0ָ�����
    private static int SEEN_ICONST_0 = 1;
    public RemoveAddZeroAdapter(MethodVisitor mv) {
        super(ASM4, mv);
    }
    @Override 
    public void visitInsn(int opcode) {
        if (state == SEEN_ICONST_0) {
            //����Ѿ�����ICONST_0
            if (opcode == IADD) {
                //���ҵ�ǰָ����IADD
                //��������������ָ���stateΪδ����ICONST_0
                state = SEEN_NOTHING;
                return;
            }
        }
        //���û�г���ICONST_0+IADD����ָ�ִ��state��鴦��
        visitInsn();
        if (opcode == ICONST_0) {
            //�������ICONST_0ָ���stateΪ����
            //����ʱ���ز�����ICONST_0ָ��
            state = SEEN_ICONST_0;
            return;
        }
        //������ǰָ��
        mv.visitInsn(opcode);
    }
    //����state��instruction���⴦��
    @Override 
    protected void visitInsn() {
        if (state == SEEN_ICONST_0) {
            //���stateΪICONST_0������ǰ���ݴ��ָ��
            mv.visitInsn(ICONST_0);
        }
        state = SEEN_NOTHING;
    }
}
```
label��frames�����������instruction֮ǰ��visit����Ȼ����߱�������ָ�����transformations����Ӱ�죬 ����Ҫɾ������һ����ת����ָ��ICONST_0������ζ��һ��label��Ӧһ����תָ����ICONST_0+IADD����ָ�ɾ���������תָ��ͻ�ָ��IADD��������ָ� �������תָ���ǵ�IADD�����ڲ���ȷ����jump֮ǰ��a 0��ѹ��stack����˲���ɾ��������ָ�����С�ͬ������stack map frames�������������ָ��䱻visit������ָ��Ҳ���ܱ�ɾ��������������£�����Ҫ���ǽ�����visitXXX����
visitInsn method���£�
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
<div id = "exp5"></div>

3. �޸ķ���ʵ�֣�����try/catch block

��ClassA��index()���������ŵ�try block��
```java
private int index() {
    try {
        return this.number;
    } finally {
        System.out.println("index() method returns");
    }
}
```
ͨ������asm���tryָ�, [InsTryBlock](sample/king/law/asm/InsTryBlock.java)
```java
package king.law.asm;

import org.objectweb.asm.*;
import org.objectweb.asm.util.CheckClassAdapter;

import java.lang.reflect.Constructor;
import java.nio.file.*;

public class InsTryBlock {
    public static void main(String[] args) throws Exception {
        ClassReader classReader = new ClassReader("king.law.asm.src.ClassA");
        // tryblockֲ��class�ļ���ᵼ��FRAMES�ı䣬�ֶ��������ѣ����ѡ���Զ�����
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        // ʹ�ö���ClassWriter���ClassVisitor����������class�ֽ���
        TryBlockClassWriter mcw = new TryBlockClassWriter(Opcodes.ASM7, cw);
        ClassVisitor cv = new CheckClassAdapter(mcw);
        classReader.accept(cv, 0);
        // ��ת������class�ֽ���д��ClassA0.class�ļ�
        byte[] bytes = cw.toByteArray();
        Path dir = Paths.get("./target/classes/king/law/asm", "src");
        Path file = dir.resolve("ClassA0.class");
        Files.write(file, bytes,
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING,
                StandardOpenOption.WRITE);
        // ͨ������classloader�������޸ĺ��class�ļ�
        Class updatedClassA = new UpdateClassLoader()
                .defineClass("king.law.asm.src.ClassA", bytes);
        Constructor constructor = updatedClassA.getConstructor(Integer.TYPE);//���� int.class
        Object updatedObj = constructor.newInstance(5);
        System.out.println("updated ClassA(5) is : " + updatedObj);
    }

    public static class TryBlockMethodWriter extends MethodVisitor {
        // ������try block��methodName
        private String methodName;

        // try block�����label����Ӧasm���� TRYCATCHBLOCK L0 L1 L2 null
        private Label lTryBlockStart; // L0
        private Label lTryBlockEnd;   // L1
        private Label lFinalBlock;    // L2

        // flag������ʾԭʼasm������IRETURN�Ƿ��Ѿ������滻 IRETURN->ISTORE 1
        private boolean replace = false;

        public TryBlockMethodWriter(int api, MethodVisitor mv, String methodName) {
            super(api, mv);
            this.methodName = methodName;
        }

        // Ϊ�˸������������ŵ�try/finally����Ҫ�ڷ������ʼ����visitTryCatchBlock
        //   visitAnnotationDefault?
        //   ( visitAnnotation | visitParameterAnnotation | visitAttribute )*
        //   ( visitCode
        //   ( visitTryCatchBlock | visitLabel | visitFrame | visitXxxInsn |
        //        visitLocalVariable | visitLineNumber )*
        //   visitMaxs )?
        //   visitEnd

        // ԭʼindex()����asm����
        //private index()I
        //L0
        //LINENUMBER 13 L0
        //ALOAD 0
        //GETFIELD king/law/asm/src/ClassA.number : I
        //IRETURN
        //
        //L1
        //LOCALVARIABLE this Lking/law/asm/src/ClassA; L0 L1 0
        //MAXSTACK = 1
        //MAXLOCALS = 1
        //

        //---> ����try block��index()����asm����
        //
        //private index()I
        //TRYCATCHBLOCK L0 L1 L2 null
        //L0
        //LINENUMBER 14 L0
        //ALOAD 0
        //GETFIELD king/law/asm/src/ClassA.number : I
        //ISTORE 1
        //
        //L1
        //LINENUMBER 16 L1
        //GETSTATIC java/lang/System.out : Ljava/io/PrintStream;
        //LDC "index() method returns"
        //INVOKEVIRTUAL java/io/ PrintStream.println (Ljava/lang/String;)V
        //ILOAD 1
        //IRETURN
        //
        //L2
        //FRAME SAME1 java/lang/Throwable
        //ASTORE 2
        //GETSTATIC java/lang/System.out : Ljava/io/PrintStream;
        //LDC "index() method returns"
        //INVOKEVIRTUAL java/io/PrintStream.println (Ljava/lang/String;)V
        //ALOAD 2
        //ATHROW
        //
        //L3
        //LOCALVARIABLE this Lking/law/asm/src/ClassA; L0 L3 0
        //MAXSTACK = 2
        //MAXLOCALS = 3
        //
        @Override
        public void visitCode() {
            super.visitCode();

            // ���� index()��������try block
            if (methodName.equals("index")) {
                lTryBlockStart = new Label();
                lTryBlockEnd = new Label();
                lFinalBlock = new Label();

                // ��ԭʼasm���������� TRYCATCHBLOCK L0 L1 L2 null
                // null��ʾ����any exceptions��Ҳ����������ָ�����岶��������"java/lang/System"
                visitTryCatchBlock(lTryBlockStart, lTryBlockEnd,
                        lFinalBlock, null);
                // ��ԭʼasm���������� L0
                visitLabel(lTryBlockStart);
            }
        }

        @Override
        public void visitInsn(int opcode) {
            // �����ԭʼasm������IRETURNָ�����滻��ISTORE 1ָ��
            if (opcode == Opcodes.IRETURN && !replace) {
                //System.out.println("do instrument: Opcodes.IRETURN");
                //������ͨ����ӡ����IRETURN��ִ�����ε����޸�classʧ��
                //��Ϊ�޸ĺ��class L1��Ҳ��IRETURNָ��Ǹ��ط������滻���������replace flag�ж�
                visitVarInsn(Opcodes.ISTORE, 1);
                replace = true;
                return;
            }
            super.visitInsn(opcode);
        }

        @Override
        public void visitMaxs(int maxStack, int maxLocals) {
            // ԭʼasm����max��֮ǰ����IRETURNָ���Ҫ����try block��ش���
            if (methodName.equals("index")) {
                // ����L1�εĴ���
                // L1
                visitLabel(lTryBlockEnd);
                //LINENUMBER 16 L1 ������Զ����㲻��Ҫ��ʽ����
                //GETSTATIC java/lang/System.out : Ljava/io/PrintStream;
                visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System",
                        "out", "Ljava/io/PrintStream;");
                //LDC "index() method returns"
                visitLdcInsn("index() method returns");
                //INVOKEVIRTUAL java/io/ PrintStream.println (Ljava/lang/String;)V
                visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream",
                        "println", "(Ljava/lang/String;)V", false);
                //ILOAD 1
                visitVarInsn(Opcodes.ILOAD, 1);
                //IRETURN
                //�˴�ָ��ͨ��replace flagʶ��Ͳ������滻��
                visitInsn(Opcodes.IRETURN);

                // ����L2�εĴ���
                // L2
                visitLabel(lFinalBlock);
                //FRAME SAME1 java/lang/Throwable ѡ��ClassWriter.COMPUTE_FRAMES�Զ����㣬����Ҫ��ʽ����
                //ASTORE 2
                visitVarInsn(Opcodes.ASTORE, 2);
                //GETSTATIC java/lang/System.out : Ljava/io/PrintStream;
                visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System",
                        "out", "Ljava/io/PrintStream;");
                //LDC "index() method returns"
                visitLdcInsn("index() method returns");
                //INVOKEVIRTUAL java/io/PrintStream.println (Ljava/lang/String;)V
                visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream",
                        "println", "(Ljava/lang/String;)V", false);
                //ALOAD 2
                visitVarInsn(Opcodes.ALOAD, 2);
                //ATHROW
                visitInsn(Opcodes.ATHROW);
            }

            super.visitMaxs(maxStack, maxLocals);
        }
    }

    public static class TryBlockClassWriter extends ClassVisitor {
        private int api;

        public TryBlockClassWriter(int api, ClassWriter cv) {
            super(api, cv);
            this.api = api;
        }

        @Override
        public MethodVisitor visitMethod(int access, String name, String desc,
                                         String signature, String[] exceptions) {

            MethodVisitor mv = super.visitMethod(access, name, desc, signature,
                    exceptions);
            // ��װϵͳMethodVisitor��ͨ��TryBlockMethodWriter������ʵ�ִ����޸�
            TryBlockMethodWriter mvw = new TryBlockMethodWriter(api, mv, name);
            return mvw;
        }
    }
}
```
ִ�н��
```console
index() method returns
updated ClassA(5) is : ClassA{number=5, index=5}
```

### Tree API ʹ��
ASM tree API�ĺ�����ClassNode/FieldNode/MethodNode classes,ʹ��tree API������class���Event API��ķ�Լ30%ʱ�䲢��ʹ�ø���memory������ȴ���԰��κ�˳��������class elements��������Event API�����ϸ���һ��˳����������ĳЩ�����ʹ�û᷽��һЩ��
<div id = "exp6"></div>

1. ͨ��ClassNode �޸��ֽ�����package������class name [TreeConvertClass](sample/king/law/asm/TreeConvertClass.java)
```java
package king.law.asm;

import org.objectweb.asm.*;

public class TreeConvertClass {
    public static void main(String[] args) throws Exception {
        //1.��ʼ��ClassNode
        ClassNode cn = new ClassNode();
        //2.װ�ؽ���class�ļ�
        ClassReader classReader = new ClassReader("king.law.asm.src.ClassA");
        classReader.accept(cn, ClassReader.SKIP_DEBUG);
        //3.ͨ��ClassNode�޸İ���������
        cn.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC,
                "king/law/asm/dest/ClassA0", null, "java/lang/Object",
                null);
        //4.ClassNode����һ��ClassWriter��dump�ֽ�����        
        ClassWriter cw = new ClassWriter(0);
        cn.accept(cw);
        byte[] bytes = cw.toByteArray();

        Class updatedClassA = new UpdateClassLoader()
                .defineClass("king.law.asm.dest.ClassA0", bytes);
        System.out.println("class is : " + updatedClassA.getName());
    }
}
```
ִ�н����
```console
class is : king.law.asm.dest.ClassA0
```
<div id = "exp7"></div>

2. ͨ��ClassNode/MethodNode ɾ��index�������޸�toString [TreeUpdateClass](sample/king/law/asm/TreeUpdateClass.java)
```java
package king.law.asm;

import king.law.asm.src.ClassA;
import org.objectweb.asm.*;

import java.lang.reflect.Constructor;
import java.util.Iterator;

public class TreeUpdateClass {
    public static void main(String[] args) throws Exception {
        System.out.println("original ClassA(5) is : " + new ClassA(5));
        System.out.println("has index() method? : " + ClassA.class.getDeclaredMethod("index"));
        System.out.println("--------------------------");
        ClassReader classReader = new ClassReader("king.law.asm.src.ClassA");
        //1.��ClassNode�����ClassReader��������ת���ڴ�tree�ṹ
        ClassNode cn = new ClassNode();
        classReader.accept(cn, 0);
        //2.��ѯtree�� method node list
        Iterator<MethodNode> methodNodeIterator = cn.methods.iterator();
        while (methodNodeIterator.hasNext()) {
            MethodNode mn = methodNodeIterator.next();
            if ("index".equals(mn.name) && mn.access == Opcodes.ACC_PRIVATE
                    && "()I".equals(mn.desc)) {
                //3.��index method node��tree��list��ɾ��
                methodNodeIterator.remove();
            }

            if ("toString".equals(mn.name) && "()Ljava/lang/String;".equals(mn.desc)) {
                //4.��λ GETFIELD king/law/asm/src/ClassA.number : I
                final AbstractInsnNode[] cNode = {null};
                mn.instructions.forEach(node -> {
                    if (node.getOpcode() == Opcodes.GETFIELD) {
                        cNode[0] = node;
                    }
                });
                //5.���� number * new Random().nextInt(5)ָ������
                InsnList randomIl = new InsnList();
                randomIl.add(new TypeInsnNode(Opcodes.NEW, "java/util/Random"));
                randomIl.add(new InsnNode(Opcodes.DUP));
                randomIl.add(new MethodInsnNode(Opcodes.INVOKESPECIAL, "java/util/Random",
                        "<init>", "()V", false));
                randomIl.add(new InsnNode(Opcodes.ICONST_5));
                randomIl.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, "java/util/Random",
                        "nextInt", "(I)I", false));
                randomIl.add(new InsnNode(Opcodes.IMUL));
                //6.������ָ���в���GETFIELDָ��֮��
                mn.instructions.insert(cNode[0], randomIl);

                //7.���� System.out.println(number)ָ������
                InsnList il = new InsnList();
                il.add(new FieldInsnNode(Opcodes.GETSTATIC, "java/lang/System",
                        "out", "Ljava/io/PrintStream;"));
                il.add(new VarInsnNode(Opcodes.ALOAD, 0));
                il.add(new FieldInsnNode(Opcodes.GETFIELD, "king/law/asm/src/ClassA",
                        "number", "I"));
                il.add(new MethodInsnNode(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream",
                        "println", "(I)V", false));
                //8.������ָ���в���methodNode��ָ���б���ǰ��
                mn.instructions.insert(il);
                //9.����stack size
                mn.maxStack += 2;
            }
        }
        //10.ʹ��ClassWriter��dump�ڴ���ClassNode��tree data
        ClassWriter cw = new ClassWriter(0);
        cn.accept(cw);
        byte[] bytes = cw.toByteArray();

        Class updatedClassA = new UpdateClassLoader()
                .defineClass("king.law.asm.src.ClassA", bytes);
        Constructor constructor = updatedClassA.getConstructor(Integer.TYPE);//���� int.class
        Object updatedObj = constructor.newInstance(5);
        System.out.println("updated ClassA(5) is : " + updatedObj);
        System.out.println("has index() method? : " + updatedClassA.getDeclaredMethod("index"));
    }
}

```
�Ƚ�core event API��tree API���ͬ�����ںδ����ֽ��롣ǰ����ͨ��ClassWriter��visitXXX��������˳��һ����������������ͨ��ClassNode��visitXXX�����������з�������һ�飬���ذ��չ̶�˳������Ȼ��ͨ��ClassWriter���޸ĺ��ֽ����������������޸�toString������core event API��example����������`println`�����޸�`number * new Random().nextInt(5)`��maxstack����tree API��example�������޸ĺ����ӡ�