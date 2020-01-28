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

��ClassA��toString�޸ĳ� `(number * 8)`
```java
@Override
public String toString() {
    return "ClassA{" +
            // �޸� "number=" + number +
            "number=" + (number * 8) +
            '}';
}
```
 * ��ͨ�� TraceClassVisitor ���Ƚ��޸�ǰ�� toString�����ֽ���Ĳ���

�޸�ǰ��
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

�޸ĺ�
```asm
public toString()Ljava/lang/String;
    NEW java/lang/StringBuilder
    DUP
    INVOKESPECIAL java/lang/StringBuilder.<init> ()V
    LDC "ClassA{number="
    INVOKEVIRTUAL java/lang/StringBuilder.append (Ljava/lang/String;)Ljava/lang/StringBuilder;
    ALOAD 0
    GETFIELD king/law/asm/src/ClassA.number : I
    //////////// �����ӵ�����ָ��
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
computed automatically. You don��t have to call visitFrame, but you
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

