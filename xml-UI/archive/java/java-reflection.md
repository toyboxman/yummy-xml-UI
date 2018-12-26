***

## Ways to reflect
有时候处理一些对象受到Modifier(修饰符号)域的限制，无法直接访问，需要通过反射来做。
<br>Modifier(修饰符号) 包括 public protected private
<br>Qualifier(限定符号) is an extra name given to either variables or functions , showing an extra quality or extra meaning for that variable or function. like Dr in Dr Arun Kumar
<br>Qualifiers for variables are (TYPE qualifiers): signed, unsigned, long, short, long long, const, volatile, static, auto, extern, register
<br>Qualifiers for functions are: static, extern, inline
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
### 2.access private constructor
想构造一些对象实例，但是无法调用non-public的构造方法
```java 
// 此处如果使用Class.getConstructor，由于是private modifier会返回null
Constructor<IntegerValueImpl> valueConstructor = IntegerValueImpl
                                    .class.getDeclaredConstructor(VirtualMachine.class, int.class);
valueConstructor.setAccessible(true);
IntegerValueImpl integerValue = valueConstructor.newInstance(vm, 500);
```
