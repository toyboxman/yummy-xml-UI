***

## Spring Notes

### 1.Unit Test
spring����ṩ�˺�junit���ɵķ�ʽ
* junit integrates with spring
1.simply annotate a JUnit 4 based test class with @RunWith(SpringRunner.class)
code sample:
[1](https://github.com/apache/incubator-griffin/blob/master/service/src/test/java/org/apache/griffin/core/job/JobControllerTest.java#L54)   
2.org.springframework.test.web.servlet.MockMvc, server-side Spring MVC test support
code sample:
[1](https://github.com/apache/incubator-griffin/blob/master/service/src/test/java/org/apache/griffin/core/job/JobControllerTest.java#L67)
3.org.springframework.boot.test.mock.web.SpringBootMockServletContext
4.org.springframework.boot.test.autoconfigure.json.JsonTestersAutoConfiguration
* test in spring boot
https://www.baeldung.com/spring-boot-testing
```java

}
``` 

```java 

```
### 2.close all log output
��ʱ��log4jҲ�����������־������ͨ��С���ɹر����
```java

```
