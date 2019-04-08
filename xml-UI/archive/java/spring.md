***

## Spring Notes

### Concept
#### Spring boot
Spring Boot提供开发stand-alone和production-grade的spring应用新方式，其避免了复杂的XML configuration，减少应用开发时间，提供快速简单启动应用的方式。

包括如下特点：
    - A flexible way to configure Java Beans, XML configurations, and Database Transactions.
    - A powerful batch processing and manages REST endpoints.
    - In Spring Boot, everything is auto configured; no manual configurations are needed.
    - It offers annotation-based spring application
    - Eases dependency management
    - It includes Embedded Servlet Container

- @EnableAutoConfiguration

通过此annotation设定，Spring Boot能基于项目依赖自动配置应用. 例如，如果MySQL database在classpath中, 而你没配置database connection, 但Spring Boot会自动配置一个in-memory database.

- @ComponentScan

Spring Boot自动扫描所有项目中用此标签声明的components。

- @Configuration

表明有@Bean声明method的class会由Spring container管理，产生bean definitions和运行期对这些bean的service requests。
```java
// 声明AppConfig类实例由容器管理
@Configuration
public class AppConfig {

    // 声明MyBean类实例由容器管理
    @Bean
    public MyBean myBean() {
        // instantiate, configure and return bean ...
    }
}
```

- @SpringBootApplication

Spring Boot应用的入口是设定此annotation class的main method，会自动触发auto-configuration和component scanning行为。这是一个convenience annotation，等价于declaring @Configuration, @EnableAutoConfiguration和@ComponentScan。

- Spring Boot starters

Spring Boot会提供一些模板化依赖关系，来解决不同类型工程依赖管理难题。所有Spring Boot starters遵循相同命名规则 spring-boot-starter- *, *表明哪种应用。例如，如果开发Spring与JPA来访问数据库应用, 在工程中包含spring-boot-starter-data-jpa的依赖就足够了。

    - write a Rest Endpoints
    ```
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    ```

### 1.Unit Test
spring框架提供了和junit集成的方式
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
// 声明AppConfig类实例由容器管理
@Configuration
public class AppConfig {

    // 声明MyBean类实例由容器管理
    @Bean
    public MyBean myBean() {
        // instantiate, configure and return bean ...
    }
}
```
* @bean
Indicates that a method produces a bean to be managed by the Spring container(方法产生的bean对象由容器托管)
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
同一个类如果即用@configuration，又用@bean声明的话，spring会提示同一个bean有两个实例，无法autowire
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