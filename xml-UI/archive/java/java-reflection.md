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
### 3.invoke methods
��ͨ�����䷽ʽ���ö���ʵ���ϵķ���
```java 
package king.law.reflection;

public interface Parent {
    String get();
}

public interface Child extends Parent {
    String patch();
}

public class Family implements Child {
    @Override
    public String patch() {
        return "patch family";
    }

    @Override
    public String get() {
        return "get family";
    }

    public static void main(String[] args)
            throws ClassNotFoundException, NoSuchMethodException,
            InvocationTargetException, IllegalAccessException {
        Family instance = new Family();
        Class<?> aClass = Class.forName("king.law.reflection.Family");
        show(instance, aClass);

        Class<?> childClass = Class.forName("king.law.reflection.Child");
        show(instance, childClass);
      
        // Parent�ӿ�û��child�ӿڵ�patch��������˻��׳��쳣
        Class<?> parentClass = Class.forName("king.law.reflection.Parent");
        show(instance, parentClass);
    }

    private static void show(Family instance, Class<?> aClass)
            throws NoSuchMethodException,
            IllegalAccessException,
            InvocationTargetException {
        System.out.println(aClass.getSimpleName() + "/"
                + aClass.getName());
        Method patch = aClass.getMethod("patch");
        System.out.println(patch.invoke(instance));
        Method get = aClass.getMethod("get");
        System.out.println(get.invoke(instance));
        System.out.println(aClass.getCanonicalName() + "/"
                + aClass.getTypeName());
    }
}
```
���н��
```console
Family/king.law.reflection.Family
patch family
get family
king.law.reflection.Family/king.law.reflection.Family
Child/king.law.reflection.Child
patch family
get family
king.law.reflection.Child/king.law.reflection.Child
Parent/king.law.reflection.Parent
Exception in thread "main" java.lang.NoSuchMethodException: king.law.reflection.Parent.patch()
	at java.lang.Class.getMethod(Class.java:1786)
	at king.law.reflection.Family.show(Family.java:35)
	at king.law.reflection.Family.main(Family.java:28)
```