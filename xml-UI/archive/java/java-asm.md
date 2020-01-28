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

#### �޸��ֽ���
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

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

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

