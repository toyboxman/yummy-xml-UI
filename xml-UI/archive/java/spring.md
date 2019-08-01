***

## Spring Notes

### Concept
#### [Spring boot](https://www.tutorialspoint.com/spring_boot/spring_boot_introduction.htm)
Spring Boot�ṩ����stand-alone��production-grade��springӦ���·�ʽ��������˸��ӵ�XML configuration������Ӧ�ÿ���ʱ�䣬�ṩ���ټ�����Ӧ�õķ�ʽ��

���������ص㣺<br>
1. A flexible way to configure Java Beans, XML configurations, and Database Transactions.
2. A powerful batch processing and manages REST endpoints.
3. In Spring Boot, everything is auto configured; no manual configurations are needed.
4. It offers annotation-based spring application
5. Eases dependency management
6. It includes Embedded Servlet Container

- **@EnableAutoConfiguration**

ͨ����annotation�趨��Spring Boot�ܻ�����Ŀ�����Զ�����Ӧ��. ���磬���MySQL database��classpath��, ����û����database connection, ��Spring Boot���Զ�����һ��in-memory database.

- **@ComponentScan**

Spring Boot�Զ�ɨ��������Ŀ���ô˱�ǩ������components��

- **@Configuration**

������@Bean����method��class����Spring container��������bean definitions�������ڶ���Щbean��service requests��
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

- **@SpringBootApplication**

Spring BootӦ�õ�������趨��annotation class��main method�����Զ�����auto-configuration��component scanning��Ϊ������һ��convenience annotation���ȼ���declaring @Configuration, @EnableAutoConfiguration��@ComponentScan��

- **@Component**

������������class��һ��"component"��ʹ���Զ����ú�classpath scanningʱ��������classes�ܹ����Զ��ҵ�(auto-detection)������class-level annotationsҲ�ɱ���Ϊ����һ��component��e.g. @Repository annotation or AspectJ's @Aspect annotation��

- **@Service**

������������class��һ��"Service", ԭʼ����������Domain-Driven Design (Evans, 2003)��"an operation offered as an interface that stands alone in the model, with no encapsulated state." Ҳ�����Ϊclass��һ��"Business Service Facade" (in the Core J2EE patterns sense)���˱�ǩ�Ǹ�general-purpose stereotype��ʹ���߿��Ը���ʹ��������С�����巶�롣�ɿ���@Component��һ������(specialization), ����ʵ����ͨ��classpath scanning���Զ���������

- **@Controller**

������������class��һ��"Controller" (e.g. a web controller)������@Component��һ������������ʵ���౻�Զ�������������Ӧ����Class��@RequestMapping������handler�������һ��������web URL��ӳ�䡣

- **@Repository**

������������class��һ��"Repository", ��Ϊһ�ַ�װ�洢����ȡ����Ѱ������Ϊ�Ļ��ơ�
Spring 2.5֮��, �˱�ǩҲ��Ϊ@Component��һ������, ����ʵ����ͨ��classpath scanning���Զ���������

- **Spring Boot starters**<br>Spring Boot���ṩһЩģ�廯������ϵ���������ͬ���͹��������������⡣����Spring Boot starters��ѭ��ͬ�������� spring-boot-starter- *, *��������Ӧ�á����磬�������Spring��JPA���������ݿ�Ӧ��, �ڹ����а���spring-boot-starter-data-jpa���������㹻�ˡ�
    * write a Rest Endpoints
    ```
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    ```

- **AopUtils**

Spring�����AOP support���߷������ϣ��ο�[api doc](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/aop/support/AopUtils.html)

- **ReflectionUtils**

Spring�����������򻯷���APIʹ�ò���������ò����쳣���ο�[api doc](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/util/ReflectionUtils.html)

- **AnnotationUtils**

ͨ�ñ�ǩ���߷�������meta-annotations, bridge methods(�����������ķ�������)��super methods(����annotation inheritance)���������������JDK�ķ��乤��(introspection facilities)�������ṩ����Ϊ����ʱ����(runtime-retained)�ı�ǩ(��transaction control, authorization, or service exposure), ʹ�ô����lookup����(��findAnnotation(Method, Class) or getAnnotation(Method, Class))�������JDK��ԭʼ�ķ������ο�[api doc](https://docs.spring.io/spring-framework/docs/current/javadoc-api/index.html?org/springframework/aop/support/AopUtils.html)

- **BeanPostProcessor**

һ��Factory hook�������beanʵ���������޸�, ������marker interfaces(��������������Ľӿ�)����proxies����װ(wrap)��Щbean��
ApplicationContexts���Զ���bean�Ķ������ҵ�BeanPostProcessor��ע��beans����������Ӧ�õ�����beanʵ���Ĳ�������ͨ(Plain)bean�����������ʽ����post-processorsע��, ��Ӧ�õ���������������beanʵ���ϡ�����ʹ�÷�ʽ���������beanʵ��Ӧ��ʵ�ֽӿ���postProcessBeforeInitialization, �����proxies����װ��Щbean��ʵ��postProcessAfterInitialization�� 

### Usage
<div id = "u1"></div>

#### properties injection
spring����ͨ�� **@PropertySources** **@PropertySource** **@Value** **@ConfigurationProperties** **@TestPropertySource** [[***1***](https://www.baeldung.com/spring-value-annotation), [***2***](https://www.baeldung.com/properties-with-spring)] ��ʽ���������ļ��е�ֱֵ�Ӹ���field��ʡ�Գ������м��ض�ȡ���̡����һ�����֧�ֶ�value����[����](https://www.baeldung.com/spring-expression-language)

<div id = "u2"></div>

#### bean creation
spring����ͨ�� **@Component** **@Configuration** **@Bean** [[***1***](https://www.baeldung.com/spring-bean)] ��ʽ��������spring���������bean��ͬʱ����ָ�����÷�Χ[bean scope](https://github.com/Snailclimb/JavaGuide/blob/master/docs/system-design/framework/spring/SpringBean.md)

- **@Configuration**
������class����һ������@Bean��������Щ�������ᱻSpring container������runtimeʱ������bean definitions��service requests
```java
// ����AppConfig��ʵ������������
@Configuration
public class AppConfig {

    // ����MyBean��ʵ������������
    @Bean
    public MyBean myBean() {
        // instantiate, configure and return bean ...
    }
    
    // bean available as 'b1' and 'b2', but not 'yourBean'
    // yourBean = ApplicationContext.getBean('b1')
    @Bean(name = {"b1", "b2"}) 
    public YourBean yourBean() {
        // instantiate and configure MyBean obj
        return obj;
    }
}
```
- **@Scope, @DependsOn, @Primary, @Lazy**
����@Bean���ṩ�����������ã������Ӧ��@Scope, @DependsOn, @Primary, and @Lazy������ʹ�ã�[Reference](https://stackoverflow.com/questions/45747933/best-way-to-initialize-beans-in-spring-context-after-application-started)
```java
@Bean
@Scope("prototype")
public MyBean myBean() {
    // instantiate and configure MyBean obj
    return obj;
}
```
- **bean conflict**
ͬһ�����������@configuration������@bean�����Ļ���spring����ʾͬһ��bean������ʵ�����޷�autowire
```java
// ����Listener��ʵ������������
@Configuration
public class Listener {
    
    // ��������ͨ���˷�������Listenerʵ��
    @Bean
    public Listener getListener() {
    }
}

ERROR��
Could not autowire. There is more than one bean of 'Listener' type.
Beans:
getListener?? (Listener.java) 
listener?? (Listener.java) 
```
ͬ��һ�����������@component������@bean�����Ļ���spring���ʼ������ͬ��instance, �����Ҫ���á� ����������listenerʵ����id���жϳ�ʵ����ͬ��ʹ��@bean��ʽ�ô��ǿ����ڴ�����������һЩ������
```java
// ����Listener��ʵ������������
@Component
public class Listener {
    private String id = UUID.random()
    public Listener(){...}
}

@Configuration
public class ListenerConfig {
    
    @Bean
    public getListener(){...}
}
```

<div id = "u2s1"></div>

##### bean creation order
Spring�Զ�����beanʵ����ʱ�����ָ���Ⱥ�˳��ͨ��[**@Order**](https://www.baeldung.com/spring-order), [**@DependsOn**](https://www.baeldung.com/spring-depends-on)������bean˳�����

<div id = "u3"></div>

#### aop
Spring AOP�ṩ��Aspect-Oriented Programming֧�֡��ο�Reference[[***1***](https://howtodoinjava.com/spring-aop-tutorial/), [***2***](https://www.baeldung.com/spring-aop-pointcut-tutorial)]

### Troubleshooting
<div id = "t1"></div>

#### NoUniqueBeanDefinitionException
������������ʹ��ڶ��beanʵ������������ע��ʱ������Ҫ����Spring����Ҫʹ����һ��beanʵ�������û��ָ��Spring��throw a NoUniqueBeanDefinitionException��������������֪��Ӧ��ע����һ��beanʵ��

�����ַ�ʽ��ָ��ע��beanʵ����ʹ��@Primary��ǩ���������Spring����primary beanʵ����autowireʱ����������ʵ������ʹ��@Qualifier��ǩ�����ܸ���Spring��Ҫע��beanʵ����name��Ĭ�������beanʵ������name������ĸСд��class name[����](https://springframework.guru/fixing-nonuniquebeandefinitionexception-exceptions/)

### Unit Test
spring����ṩ�˺�junit���ɵķ�ʽ
* junit integrates with spring<br>
  1. simply annotate a JUnit 4 based test class with **@RunWith(SpringRunner.class)**<br>[***1.description***](https://github.com/lsieun/learn-spring/blob/master/spring-boot/junit/RunWith.md) [***2.code***](https://github.com/search?q=%40RunWith%28SpringJUnit4ClassRunner.class%29&type=Code)<br>
  2. org.springframework.test.web.servlet.**MockMvc**, server-side Spring MVC test support<br>[***sample***](https://github.com/apache/incubator-griffin/blob/master/service/src/test/java/org/apache/griffin/core/job/JobControllerTest.java#L67)
  3. ...
  4. ...
* [test in spring boot](https://www.baeldung.com/spring-boot-testing)
  1. **@DataJpaTest** combinates with @RunWith(SpringRunner.class) for a typical JPA test, which will use an embedded in-memory database<br>[***sample***](https://github.com/apache/incubator-griffin/blob/master/service/src/test/java/org/apache/griffin/core/job/JobInstanceBeanRepoTest.java#L53)
