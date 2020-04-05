***

## io/files operations

### Ŀ¼/�ļ�����
������Ҫ���ļ����в��������紴��Ŀ¼���ļ�����ȡд���ļ����ݡ�jdkԭio�����ṩ��API��Щ���ܲ������㣬�紴���༶��Ŀ¼����Щ��������lib������Щȱʧ�Ķ�������com.google.common.io��org.apache.commons.io, jdk1.7֮��������java.nio.file������һЩ����

+ д���ļ�
```java
String file_separator = System.getProperty("file.separator");
String path = ��com/example/io��;
//���һ��Ŀ¼�ṹ
Path dir = Paths.get("./", path); // Paths.get("./", "com", "example", "io");
System.out.println(dir);
//���ļ�ϵͳ�ϴ��������༶Ŀ¼�ṹ
Files.createDirectories(dir);
//��ָ��Ŀ¼�½����ļ�·��������ļ������ھʹ������ļ�
Path ifPath = dir.resolve("Sample1.java");
if (!Files.exists(ifPath)) {
    Files.createFile(ifPath);
}
//���ֽ���д���ļ����ļ����������write��ʽ�򿪲������ԭ�������ļ�����Ϊ0
Files.write(ifPath, beanClass.render().getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
``` 
