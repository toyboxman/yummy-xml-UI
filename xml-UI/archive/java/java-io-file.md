***

## io/files operations

### 目录/文件操作
经常需要对文件进行操作，例如创建目录，文件，读取写入文件内容。jdk原io包中提供的API有些功能不够方便，如创建多级父目录。有些第三方的lib完善这些缺失的东西，如com.google.common.io，org.apache.commons.io, jdk1.7之后增加了java.nio.file补充了一些功能

+ 写入文件
```java
String file_separator = System.getProperty("file.separator");
String path = “com/example/io”;
//组合一个目录结构
Path dir = Paths.get("./", path); // Paths.get("./", "com", "example", "io");
System.out.println(dir);
//在文件系统上创建整个多级目录结构
Files.createDirectories(dir);
//在指定目录下建立文件路径，如果文件不存在就创建此文件
Path ifPath = dir.resolve("Sample1.java");
if (!Files.exists(ifPath)) {
    Files.createFile(ifPath);
}
//把字节流写入文件，文件如果存在则按write方式打开并且清除原内容至文件长度为0
Files.write(ifPath, beanClass.render().getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
``` 
