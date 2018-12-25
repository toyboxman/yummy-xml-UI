***

## Ways to Remote Debug
JPDA(Java Platform Debugger Architecture)��һ��������JVM���Լܹ��������������㡣�ֱ���API��JDI��Э���JDWP��JVM�ӿ�ʵ�ֲ�JVMDI������IDE��ʵ�ֵ�remote debug����hotswap���ǻ��ڴ���ʵ�֡���ϸ�������Բο�[�ٷ��ĵ�](https://docs.oracle.com/javase/8/docs/technotes/guides/jpda/enhancements1.4.html)
### dev on JDI
JDI(com.sun.jdi)����JDK�İ汾������������tools.jar�С��������maven���̵Ļ�����Ҫָ�����ر�������·����public repo��û�д�jar��
```xml
<dependency>
	<groupId>com.sun</groupId>
	<artifactId>tools</artifactId>
	<version>1.8</version>
	<scope>system</scope>
	<systemPath>/usr/lib64/jvm/java-1.8.0-openjdk-1.8.0/lib/tools.jar</systemPath>
</dependency>
}
``` 
API�ĵ����Բο�[JDI(com.sun.jdi)](https://docs.oracle.com/javase/8/docs/jdk/api/jpda/jdi/index.html)
<br>����������ܹ�����JDWPЭ�飬�κ�����ʵ�ֵĿͻ��˶��������JDI�Ĺ��ܡ�
```java 

```
