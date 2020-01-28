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

#### 修改字节码
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

