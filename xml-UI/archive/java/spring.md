***

## Spring Notes

### 1.Unit Test
spring����ṩ�˺�junit���ɵķ�ʽ
* junit integrates with spring<br>
  1. simply annotate a JUnit 4 based test class with **@RunWith(SpringRunner.class)**<br>[***sample***](https://github.com/apache/incubator-griffin/blob/master/service/src/test/java/org/apache/griffin/core/job/JobControllerTest.java#L54)<br>
  2. org.springframework.test.web.servlet.**MockMvc**, server-side Spring MVC test support<br>[***sample***](https://github.com/apache/incubator-griffin/blob/master/service/src/test/java/org/apache/griffin/core/job/JobControllerTest.java#L67)
  3. ...
  4. ...
* [test in spring boot](https://www.baeldung.com/spring-boot-testing)
  1. **@DataJpaTest** combinates with @RunWith(SpringRunner.class) for a typical JPA test, which will use an embedded in-memory database<br>[***sample***](https://github.com/apache/incubator-griffin/blob/master/service/src/test/java/org/apache/griffin/core/job/JobInstanceBeanRepoTest.java#L53)


### 2.claim bean managed by spring container
* @Configuration
Indicates that a class declares one or more @Bean methods and may be processed by the Spring container to generate bean definitions and service requests for those beans at runtime, for example:
```java
// ����AppConfig��ʵ������������
@Configuration
public class AppConfig {

    // ����MyBean��ʵ������������
    @Bean
    public MyBean myBean() {
        // instantiate, configure and return bean ...
    }
}
```
* @bean
Indicates that a method produces a bean to be managed by the Spring container(����������bean�����������й�)
```java
// bean available as 'b1' and 'b2', but not 'myBean'
// ApplicationContext.getBean(name)
@Bean(name = {"b1", "b2"}) 
public MyBean myBean() {
    // instantiate and configure MyBean obj
    return obj;
}
```
Scope, DependsOn, Primary, and Lazy
Note that the @Bean annotation does not provide attributes for scope, depends-on, primary, or lazy. Rather, it should be used in conjunction with @Scope, @DependsOn, @Primary, and @Lazy annotations to achieve those semantics. For example:
```java
@Bean
@Scope("prototype")
public MyBean myBean() {
    // instantiate and configure MyBean obj
    return obj;
}
```
https://stackoverflow.com/questions/45747933/best-way-to-initialize-beans-in-spring-context-after-application-started
* bean conflict
ͬһ�����������@configuration������@bean�����Ļ���spring����ʾͬһ��bean������ʵ�����޷�autowire
```java
@configuration
public class Listener {
    
    @bean
    public Listener getListener() {
    }
}

Could not autowire. There is more than one bean of 'Listener' type.
Beans:
getListener?? (Listener.java) 
listener?? (Listener.java) 
```