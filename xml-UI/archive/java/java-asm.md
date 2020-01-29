***

## �޸ı����class�ļ�

### ASM���
ASM���Ƕ�java class�ļ����д����һ�׹��ߣ��������û�[�ĵ�����](https://asm.ow2.io/asm4-guide.pdf),�ṩ��������APIs������XML��SAX/DOM,ǰ�߻���eventģ�ͣ��ٶȿ��ڴ�ʹ���٣����߻���treeģ�ͣ�����ṹ����һ����ɡ�

Eventģ�͵�API����event producers (the class parser), event consumers
(the class writer)��һЩԤ����event filters��

ASM�ṩ���¼���5��lib
* asm.jar �ṩ����event��API������class parser([ClassReader](https://asm.ow2.io/javadoc/org/objectweb/asm/ClassReader.html))��processor([ClassWriter](https://asm.ow2.io/javadoc/org/objectweb/asm/ClassWriter.html))��components.
* asm-util.jar �ṩһЩ�������ԵĹ�����[TraceClassVisitor](https://asm.ow2.io/javadoc/org/objectweb/asm/util/TraceClassVisitor.html), [CheckClassAdapter](https://asm.ow2.io/javadoc/org/objectweb/asm/util/CheckClassAdapter.html)
* asm-commons.jar �ṩһЩ���õ�class transformers
* asm-tree.jar �ṩ����object treeģ�͵�API, �Լ�һЩ���ߣ�����ת��class��eventģ�ͺ�object treeģ��֮�䲻ͬ���.
* asm-analysis.jar �ṩclass analysis framework��һЩ����object treeģ�͵�class analyzers.

### Event API ʹ��
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
2. ���� ClassReader��TraceClassVisitor���������class�ļ�����
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
2. ͨ��ClassWriter �޸��ֽ���
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
local variables and operand stack���Զ����㣬������Ҫ����visitMaxs����,��������Ĳ������ᱻ���Բ���������㡣��the frames����Ҫ�Լ����㴦��
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
�޸ķ������ַ�Ϊstateless��stateful��������
1. stateless

��ClassA��toString�����������޸�
```java
// remove��index()
//private int index() {...}

@Override
public String toString() {
    // 1.���� println
    System.out.println(this.number);
    return "ClassA{" +
            // 2.�޸� "number=" + number +
            "number=" + (number * 8) +
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
    //////////// ���� (number*8) ָ��
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
* �ٶ���ָ�������ʵ�ַ����޸�
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
        //6.��λ��GETFIELD king/law/asm/src/ClassA.number : I
        if (opcode == Opcodes.GETFIELD) {
            //7.BIPUSH 8
            mv.visitIntInsn(Opcodes.BIPUSH, 8);
            //8.IMUL
            mv.visitInsn(Opcodes.IMUL);
        }
    }

    @Override
    public void visitMaxs(int maxStack, int maxLocals) {
        //�޸�ǰ MAXSTACK = 2 MAXLOCALS = 1
        //�޸ĺ� MAXSTACK = 3 MAXLOCALS = 1
        //9.���¼���ջ�ͱ��ر�����С
        //new ClassWriter(0)�����Զ����㣬COMPUTE_MAXS��Ϊ�������Զ�����
        mv.visitMaxs(maxStack + 1, maxLocals);
    }
}

//����ClassLoader�����޸ĺ��class�ļ�
class UpdateClassLoader extends ClassLoader {
    //����ֱ�ӵ���defineClass��װ��class�ļ�
    public Class defineClass(String name, byte[] b) {
        //ClassLoader.defineClass��protected final,�޷�override
        //ֻ��ͨ��proxy��ʽ����
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

2. stateful

