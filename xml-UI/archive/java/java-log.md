***

## Ways to log setting

### 1.config log manually
常常使用一些第三方库的时候，无法配置Logger。但又需要依赖日志来跟踪程序，此时可以通过reflect方式来实现
```java
class A {
   private static final Logger logger = Logger.getLogger(A.class);
   ....
}
``` 
当调用A的方法的时候，如果没有配置logger的configuration file，logger就无法正常输出。
可以通过修改A的private field，来配置正确的logger。
```java 
private void addLogger(Class res) throws NoSuchFieldException,
            IllegalAccessException {
        //默认加上Log4J的appender
        ConsoleAppender appender = new ConsoleAppender(new PatternLayout());
        //找到logger的field
        Field field = res.getDeclaredField("logger");
        //private的field默认是禁止访问的，因此需要使能访问
        field.setAccessible(true);
        //获取此field的value
        Logger logger = (Logger) field.get(res);
        logger.addAppender(appender);
        logger.setLevel(Level.ALL);
}
```
### 2.close all log output
有时候log4j也会输出过多日志，可以通过小技巧关闭输出
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

### 3.filter output
有时候希望自己控制console输出，可以实现System类的outputsteam
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
