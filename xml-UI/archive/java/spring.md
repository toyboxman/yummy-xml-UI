***

## Spring Notes

### 1.Unit Test
spring框架提供了和junit集成的方式
* junit integrates with spring<br>
  - simply annotate a JUnit 4 based test class with **@RunWith(SpringRunner.class)**<br>[***sample***](https://github.com/apache/incubator-griffin/blob/master/service/src/test/java/org/apache/griffin/core/job/JobControllerTest.java#L54)<br>
  - org.springframework.test.web.servlet.MockMvc, server-side Spring MVC test support<br>[***sample***](https://github.com/apache/incubator-griffin/blob/master/service/src/test/java/org/apache/griffin/core/job/JobControllerTest.java#L67)
  - org.springframework.boot.test.mock.web.SpringBootMockServletContext<br>
  4. org.springframework.boot.test.autoconfigure.json.JsonTestersAutoConfiguration
* test in spring boot
https://www.baeldung.com/spring-boot-testing
```java

}
``` 

```java 

```
### 2.close all log output
有时候log4j也会输出过多日志，可以通过小技巧关闭输出
```java

```
