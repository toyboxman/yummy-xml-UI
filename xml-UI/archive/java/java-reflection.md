***

## Ways to reflect
��ʱ����һЩ�����ܵ�Modifier(���η���)������ƣ��޷�ֱ�ӷ��ʣ���Ҫͨ������������
<br>Modifier(���η���) ���� public protected private
<br>Qualifier(�޶�����) is an extra name given to either variables or functions , showing an extra quality or extra meaning for that variable or function. like Dr in Dr Arun Kumar
<br>Qualifiers for variables are (TYPE qualifiers): signed, unsigned, long, short, long long, const, volatile, static, auto, extern, register
<br>Qualifiers for functions are: static, extern, inline
### 1.access private field
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
### 2.access private constructor
�빹��һЩ����ʵ���������޷�����non-public�Ĺ��췽��
```java 
// �˴����ʹ��Class.getConstructor��������private modifier�᷵��null
Constructor<IntegerValueImpl> valueConstructor = IntegerValueImpl
                                    .class.getDeclaredConstructor(VirtualMachine.class, int.class);
valueConstructor.setAccessible(true);
IntegerValueImpl integerValue = valueConstructor.newInstance(vm, 500);
```
