***

## Ways to reflect

### 1.access private field
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
