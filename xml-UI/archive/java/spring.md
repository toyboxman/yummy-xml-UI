***

## Spring Notes

### Concept

#### [Spring boot](https://www.tutorialspoint.com/spring_boot/spring_boot_introduction.htm)
Spring Boot提供开发stand-alone和production-grade的spring应用新方式，其避免了复杂的XML configuration，减少应用开发时间，提供快速简单启动应用的方式。

包括如下特点：<br>
1. A flexible way to configure Java Beans, XML configurations, and Database Transactions.
2. A powerful batch processing and manages REST endpoints.
3. In Spring Boot, everything is auto configured; no manual configurations are needed.
4. It offers annotation-based spring application
5. Eases dependency management
6. It includes Embedded Servlet Container

### Spring Boot Usage

<div id = "bu1"></div>

#### Configuration Binding
Spring Boot提供[**@ConfigurationProperties**](https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-Configuration-Binding)来声明class的root prefix，然后用一个增强binder自动将Environment中匹配prefixes的值绑定到properties，它也将自动将绑定对象exposes成Spring Bean

<div id = "bu2"></div>

#### application entry
Spring Boot提供@SpringBootApplication来声明application的入口，表示这个configuration classs申明一个或多个@Bean methods ，并且触发auto-configuration与component scanning行为. 这是个convenience使用标签，等同于同时声明@Configuration, @EnableAutoConfiguration和@ComponentScan. 

**code sample:**
[1](https://github.com/apache/incubator-griffin/blob/master/service/src/main/java/org/apache/griffin/core/GriffinWebApplication.java#L31)

<div id = "bubp"></div>

#### [best practices](https://github.com/spring-projects/spring-boot/wiki/Building-On-Spring-Boot)

- **@EnableAutoConfiguration**

通过此annotation设定，Spring Boot能基于项目依赖自动配置应用. 例如，如果MySQL database在classpath中, 而你没配置database connection, 但Spring Boot会自动配置一个in-memory database.

- **@ComponentScan**

Spring Boot自动扫描所有项目中用此标签声明的components。

- **@Configuration**

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

- **@SpringBootApplication**

Spring Boot应用的入口是设定此annotation class的main method，会自动触发auto-configuration和component scanning行为。这是一个convenience annotation，等价于declaring @Configuration, @EnableAutoConfiguration和@ComponentScan。

- **@Component**

表明被声明的class是一个"component"，使用自动配置和classpath scanning时候，这样的classes能够被自动找到(auto-detection)。其他class-level annotations也可被认为声明一个component。e.g. @Repository annotation or AspectJ's @Aspect annotation。

- **@Service**

表明被声明的class是一个"Service", 原始定义来自于Domain-Driven Design (Evans, 2003)，"an operation offered as an interface that stands alone in the model, with no encapsulated state." 也能理解为class是一个"Business Service Facade" (in the Core J2EE patterns sense)。此标签是个general-purpose stereotype，使用者可以根据使用需求缩小其语义范畴。可看做@Component的一种特例(specialization), 允许实现类通过classpath scanning被自动搜索到。

- **@Controller**

表明被声明的class是一个"Controller" (e.g. a web controller)。这是@Component的一种特例，允许实现类被自动搜索到。典型应用是Class与@RequestMapping声明的handler方法组合一起来处理web URL的映射。

- **@Repository**

表明被声明的class是一个"Repository", 作为一种封装存储、获取和搜寻数据行为的机制。
Spring 2.5之后, 此标签也作为@Component的一种特例, 允许实现类通过classpath scanning被自动搜索到。

- **Spring Boot starters**<br>Spring Boot会提供一些模板化依赖关系，来解决不同类型工程依赖管理难题。所有Spring Boot starters遵循相同命名规则 spring-boot-starter- *, *表明哪种应用。例如，如果开发Spring与JPA来访问数据库应用, 在工程中包含spring-boot-starter-data-jpa的依赖就足够了。
    * write a Rest Endpoints
    ```
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    ```

- **AopUtils**

Spring中完成AOP support工具方法集合，参看[api doc](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/aop/support/AopUtils.html)

- **ReflectionUtils**

Spring工具类用来简化反射API使用并处理反射调用产生异常。参看[api doc](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/util/ReflectionUtils.html)

- **AnnotationUtils**

通用标签工具方法处理meta-annotations, bridge methods(编译器产生的泛型声明)和super methods(用于annotation inheritance)。这个类大多数特性JDK的反射工具(introspection facilities)本身都不提供。作为运行时保留(runtime-retained)的标签(如transaction control, authorization, or service exposure), 使用此类的lookup方法(如findAnnotation(Method, Class) or getAnnotation(Method, Class))替代调用JDK中原始的方法。参看[api doc](https://docs.spring.io/spring-framework/docs/current/javadoc-api/index.html?org/springframework/aop/support/AopUtils.html)

- **BeanPostProcessor**

一种Factory hook允许对新bean实例做定制修改, 例如检查marker interfaces(不包含方法定义的接口)、用proxies来包装(wrap)这些bean。
ApplicationContexts能自动在bean的定义中找到BeanPostProcessor标注的beans，并将它们应用到后续bean实例的产生。普通(Plain)bean工厂允许程序式进行post-processors注册, 并应用到工厂产生的所有bean实例上。典型使用方式，如果产生bean实例应该实现接口中postProcessBeforeInitialization, 如果用proxies来包装这些bean则实现postProcessAfterInitialization。 

### Spring Usage

<div id = "u1"></div>

#### properties injection
spring允许通过 **@PropertySources** **@PropertySource** **@Value** **@ConfigurationProperties** **@TestPropertySource** [[***1***](https://www.baeldung.com/spring-value-annotation), [***2***](https://www.baeldung.com/properties-with-spring)] 方式来将配置文件中的值直接赋予field域，省略程序自行加载读取过程。并且还可以支持对value进行[运算](https://www.baeldung.com/spring-expression-language)

<div id = "u2"></div>

#### bean creation
spring允许通过 **@Component** **@Configuration** **@Bean** [[***1***](https://www.baeldung.com/spring-bean)] 方式来申明由spring容器管理的bean，同时可以指定作用范围[bean scope](https://github.com/Snailclimb/JavaGuide/blob/master/docs/system-design/framework/spring/SpringBean.md)

- **@Configuration**
表明此class声明一个或多个@Bean方法，这些方法将会被Spring container调用在runtime时来产生bean definitions和service requests
```java
// 声明AppConfig类实例由容器管理
@Configuration
public class AppConfig {

    // 声明MyBean类实例由容器管理
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
典型用法中@Bean methods被声明在@Configuration classes中，@Bean methods可以直接call同一个@Configuration class的其他@Bean methods。这确保beans之间的引用是强类型和互达的称为'inter-bean references'，因此@Configuration classes所有bean工厂方法必须是非final和private修饰符

@Bean methods也可以不通过典型方式声明. 例如可以声明在@Component class甚至a plain old class. 这种方式称之为'lite'. 
Bean methods在lite mode下被spring容器看做普通工厂方法(类似factory-method declarations in XML), 也有scoping and lifecycle callbacks特性. 这些产生的容器对象除了不能修改, 也没有特别的限制. 与之相反，不像通过@Configuration classes产生的beans, 'inter-bean references'在lite mode不支持. 当在lite mode尝试@Bean-method调用另一个@Bean-method, 这个调用是一个标准的Java method invocation，Spring不会通过CGLIB proxy来截获invocation. 这与inter-@Transactional method互相之间调用相似，也是通过proxy mode, Spring不截获invocation.
```
@Component
public class Calculator {
   public int sum(int a, int b) {
       return a+b;
   }

   @Bean
   public MyBean myBean() {
       return new MyBean();
   }
}
```

- **@Scope, @DependsOn, @Primary, @Lazy**
由于@Bean不提供更多属性设置，因此其应与@Scope, @DependsOn, @Primary, and @Lazy来联合使用，[Reference](https://stackoverflow.com/questions/45747933/best-way-to-initialize-beans-in-spring-context-after-application-started)
```java
@Bean
@Scope("prototype")
public MyBean myBean() {
    // instantiate and configure MyBean obj
    return obj;
}
```
- **bean conflict**
同一个类如果即用@configuration，又用@bean声明的话，spring会提示同一个bean有两个实例，无法autowire
```java
// 声明Listener类实例由容器管理
@Configuration
public class Listener {
    
    // 声明容器通过此方法产生Listener实例
    @Bean
    public Listener getListener() {
    }
}

ERROR：
Could not autowire. There is more than one bean of 'Listener' type.
Beans:
getListener?? (Listener.java) 
listener?? (Listener.java) 
```
同理一个类如果即用@component，又用@bean声明的话，spring会初始化出不同的instance, 因而不要混用。 如下例根据listener实例的id可判断出实例不同，使用@bean方式好处是可以在创建过程中做一些操作。
```java
// 声明Listener类实例由容器管理
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

#### bean creation order
Spring自动产生bean实例的时候可以指定先后顺序，通过[**@Order**](https://www.baeldung.com/spring-order), [**@DependsOn**](https://www.baeldung.com/spring-depends-on)可以让bean顺序产生

<div id = "u2s2"></div>

#### bean reference
引用Spring产生的bean实例有多种方式[**@Autowired/@Inject/@Resource**](https://www.baeldung.com/spring-annotations-resource-inject-autowire) , 对于抽象类还支持[**autowired-abstract-class**](https://www.baeldung.com/spring-autowired-abstract-class).  如果不期望即时injection发生可以[**lazy-injection**](https://www.baeldung.com/spring-lazy-annotation)

<div id = "u2s3"></div>

#### bean stages
Spring产生的bean实例各阶段中可以插入一些逻辑[**@PostConstruct/InitializingBean/ApplicationListener/initMethod**](https://www.baeldung.com/running-setup-logic-on-startup-in-spring)

<div id = "u3"></div>

#### aop
Spring AOP提供对Aspect-Oriented Programming支持。参看Reference[[***1***](https://howtodoinjava.com/spring-aop-tutorial/), [***2***](https://www.baeldung.com/spring-aop-pointcut-tutorial)]

<div id = "u3s1"></div>

#### aspect bean injection
定义一个aspect之后， 会根据需要注入一些bean实例
```java
@Aspect
@Component
public class MyclassAspect {
    @Autowired
    private MyBean bean;
```
用aspect工具weave compile上述代码，运行的时候发现bean始终为null, 关于此[问题讨论](https://stackoverflow.com/questions/9633840/spring-autowired-bean-for-aspect-aspect-is-null). 原因简言之aspect bean是一个在Spring container之外创建的单例对象，因此无法被注入. 解决办法就是用**@configuration**来配置, 还可以用[其他方式](https://blog.csdn.net/zlp1992/article/details/81037529)
```java
@Aspect
@Configurable(autowire = Autowire.BY_TYPE)
public class MyclassAspect {
    @Autowired
    private MyBean bean;
```

<div id = "u4"></div>

#### jpa
使用[**@NoRepositoryBean**](https://www.baeldung.com/spring-data-jpa-method-in-all-repositories) 来定义Base Repository Interface, 测试时候mock entity与spring test entity manager配合是可能会碰到问题

### Troubleshooting
<div id = "t1"></div>

#### NoUniqueBeanDefinitionException
如果给定的类型存在多个bean实例，在做依赖注入时候你需要告诉Spring容器要使用哪一个bean实例。如果没有指定Spring会throw a NoUniqueBeanDefinitionException，告诉你容器不知道应该注入哪一个bean实例

有两种方式来指定注入bean实例，使用@Primary标签，它会告诉Spring容器primary bean实例在autowire时候优先其他实例。或使用@Qualifier标签，它能告诉Spring你要注入bean实例的name。默认情况下bean实例引用name是首字母小写的class name[引文](https://springframework.guru/fixing-nonuniquebeandefinitionexception-exceptions/)

### Unit Test
spring框架提供了和junit集成的方式
* junit integrates with spring<br>
  1. simply annotate a JUnit 4 based test class with **@RunWith(SpringRunner.class)**<br>[***1.description***](https://github.com/lsieun/learn-spring/blob/master/spring-boot/junit/RunWith.md) [***2.code***](https://github.com/search?q=%40RunWith%28SpringJUnit4ClassRunner.class%29&type=Code)<br>
  2. org.springframework.test.web.servlet.**MockMvc**, server-side Spring MVC test support<br>[***sample***](https://github.com/apache/incubator-griffin/blob/master/service/src/test/java/org/apache/griffin/core/job/JobControllerTest.java#L67)
  3. ...
  4. ...
* [test in spring boot](https://www.baeldung.com/spring-boot-testing)
  1. **@DataJpaTest** combinates with @RunWith(SpringRunner.class) for a typical JPA test, which will use an embedded in-memory database<br>[***sample***](https://github.com/apache/incubator-griffin/blob/master/service/src/test/java/org/apache/griffin/core/job/JobInstanceBeanRepoTest.java#L53)
