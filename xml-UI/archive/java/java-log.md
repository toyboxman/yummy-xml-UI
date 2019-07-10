***

## Ways to log setting

### config log manually
����ʹ��һЩ���������ʱ���޷�����Logger��������Ҫ������־�����ٳ��򣬴�ʱ����ͨ��reflect��ʽ��ʵ��
```java
class A {
   private static final Logger logger = Logger.getLogger(A.class);
   ....
}
``` 
������A�ķ�����ʱ�����û������logger��configuration file��logger���޷����������
����ͨ���޸�A��private field����������ȷ��logger��
```java 
private void addLogger(Class res) throws NoSuchFieldException,
            IllegalAccessException {
        //Ĭ�ϼ���Log4J��appender
        ConsoleAppender appender = new ConsoleAppender(new PatternLayout());
        //�ҵ�logger��field
        Field field = res.getDeclaredField("logger");
        //private��fieldĬ���ǽ�ֹ���ʵģ������Ҫʹ�ܷ���
        field.setAccessible(true);
        //��ȡ��field��value
        Logger logger = (Logger) field.get(res);
        logger.addAppender(appender);
        logger.setLevel(Level.ALL);
}
```
### close all log output
��ʱ��log4jҲ�����������־������ͨ��С���ɹر����
```java
#disable log4j output
List<Logger> loggers = Collections.list(LogManager.getCurrentLoggers());
loggers.add(LogManager.getRootLogger());
for ( Logger logger : loggers ) {
logger.setLevel(Level.OFF);
}

#disable via log4j config
-Dlog4j.configuration=file:C:\Users\me\log4j.xml
```

### filter output
��ʱ��ϣ���Լ�����console���������ʵ��System���outputsteam
```java
#filter output including 'springframework'
static class ConsoleOutputStream extends PrintStream {

        public ConsoleOutputStream(OutputStream out) {
            super(out);
        }

        @Override
        public void write(byte[] buf, int off, int len) {
            String output = new String(buf);
            // disable spring template log
            if (output.contains("springframework")) return;
            super.write(buf, off, len);
        }
}

System.setOut(new ConsoleOutputStream(System.out));
```

### config slf4j
��ʹ��slf4j��Ҫ�ڹ����ļ��������⣬������classpath�м���lib
```xml
<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-api</artifactId>
    <version>1.7.5</version>
</dependency>
<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-log4j12</artifactId>
    <version>1.7.5</version>
</dependency>
```
���ʹ��log4j׼��һ�������ļ�
```
log4j.rootLogger=debug, stdout, R

log4j.appender.stdout=org.apache.log4j.ConsoleAppender
log4j.appender.stdout.layout=org.apache.log4j.PatternLayout

# Pattern to output the caller's file name and line number.
log4j.appender.stdout.layout.ConversionPattern=%5p [%t] (%F:%L) - %m%n

log4j.appender.R=org.apache.log4j.RollingFileAppender
log4j.appender.R.File=example.log

log4j.appender.R.MaxFileSize=100KB
# Keep one backup file
log4j.appender.R.MaxBackupIndex=1

log4j.appender.R.layout=org.apache.log4j.PatternLayout
log4j.appender.R.layout.ConversionPattern=%p %t %c - %m%n
```
����Ӧ��ʱ����ϲ���
```
-Dlog4j.debug=true -Dlog4j.configuration=file:///home/king/source/github/java-opentracing-walkthrough/microdonuts/log4j.properties
```