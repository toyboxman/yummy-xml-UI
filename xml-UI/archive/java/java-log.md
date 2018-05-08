***

## Ways to log setting

### 1.config log manually
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
### 2.close all log output
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
