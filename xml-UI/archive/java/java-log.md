***

## Ways to log setting

+ [����ʵ��logger�趨](#config-log-manually)
+ [�ر�����log���](#close-all-log-output)
+ [����log���](#filter-output)
+ [���� slf4j(Simple Logging Facade)](#config-slf4j)

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
�ܶ๤�̶�����slf4j��wrap��ͬ��logʵ��lib��������������������ʾ�����������ǰruntimeû��ָ��һ��ʵ�ֿ�
```console
SLF4J: No SLF4J providers were found.
SLF4J: Defaulting to no-operation (NOP) logger implementation
SLF4J: See http://www.slf4j.org/codes.html#noProviders for further details.
```
�����Ҫ�ڹ����ļ��������⣬������classpath�м���lib
```xml
<properties>
    <java.version>1.8</java.version>
    <maven.compiler.source>${java.version}</maven.compiler.source>
    <maven.compiler.target>${java.version}</maven.compiler.target>
    <maven.compiler.release>8</maven.compiler.release>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <project.reporting.outputEncoding>UTF-8</project.reporting.outputEncoding>
    <failIfNoTests>true</failIfNoTests>
    <!-- Versions -->
    <slf4j.version>[1.7.30,1.8)</slf4j.version>
</properties>
<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-log4j12</artifactId>
    <version>${slf4j.version}</version>
</dependency>
<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-api</artifactId>
    <version>${slf4j.version}</version>
</dependency>
<!-- �������ʹ��provider�����ã����ü������ļ�����������slf4j-simple���� -->
<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-simple</artifactId>
    <version>${slf4j.version}</version>
</dependency>
<!-- �����ָ��һ��provider���ͻ�Ĭ��ʹ�� no-operation (NOP) -->
<dependency>
    <groupId>log4j</groupId>
    <artifactId>log4j</artifactId>
    <version>1.2.17</version>
</dependency>
```
���ʹ��slf4j�����÷�ʽ��ֻ��Ҫ�ڹ���Ŀ¼������src/main/resources/simplelogger.properties������Ҫָ���κβ���������ʱ���Զ���ȡ����������
```console
#org.slf4j.simpleLogger.defaultLogLevel=debug
org.slf4j.simpleLogger.showDateTime = true
org.slf4j.simpleLogger.showShortLogName = true
org.slf4j.simpleLogger.showThreadName=true
org.slf4j.simpleLogger.log.com.github.kpavlov.jreactive8583=debug
org.slf4j.simpleLogger.log.com.github.kpavlov.jreactive8583.netty.pipeline.CompositeIsoMessageHandler=info
```
���ʹ��log4j����������log������ʽ����Ҫ׼�����������ļ�
```console
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
```console
-Dlog4j.debug=true -Dlog4j.configuration=file:///home/king/source/github/java-opentracing-walkthrough/microdonuts/log4j.properties
```