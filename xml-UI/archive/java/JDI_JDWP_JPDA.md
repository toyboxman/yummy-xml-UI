***

## Ways to Remote Debug
JPDA(Java Platform Debugger Architecture)是一套完整的JVM调试架构，有上中下三层。分别是API层JDI，协议层JDWP，JVM接口实现层JVMDI。现在IDE所实现的remote debug包括hotswap都是基于此来实现。详细描述可以参看[官方文档](https://docs.oracle.com/javase/8/docs/technotes/guides/jpda/enhancements1.4.html)
### dev on JDI
JDI(com.sun.jdi)是随JDK的版本发布，编译在tools.jar中。如果基于maven工程的话，需要指定本地编译依赖路径，public repo中没有此jar。
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
API文档可以参考[JDI(com.sun.jdi)](https://docs.oracle.com/javase/8/docs/jdk/api/jpda/jdi/index.html)
<br>理论上如果能够处理JDWP协议，任何语言实现的客户端都可以完成JDI的功能。
```java 

```
