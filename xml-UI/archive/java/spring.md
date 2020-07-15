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

### Spring Boot Usage

<div id = "bu1"></div>

#### configuration binding
Spring Boot�ṩ[**@ConfigurationProperties**](https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-Configuration-Binding)������class��root prefix��Ȼ����һ����ǿbinder�Զ���Environment��ƥ��prefixes��ֵ�󶨵�properties����Ҳ���Զ����󶨶���exposes��Spring Bean

Spring Framework�����ṩ����ע��[autowire-generics-type](https://www.baeldung.com/spring-autowire-generics), ����ע��[injecting-collections-type](https://www.baeldung.com/spring-injecting-collections)

<div id = "bu2"></div>

#### application entry
Spring Boot�ṩ **@SpringBootApplication** ������application����ڣ���ʾ���configuration classs����һ������@Bean methods �����Ҵ���auto-configuration��component scanning��Ϊ. ���Ǹ�convenienceʹ�ñ�ǩ����ͬ��ͬʱ���� **@Configuration, @EnableAutoConfiguration��@ComponentScan**   �ο�[spring bootstrap](#u0)

**code sample:**
[1](https://github.com/apache/incubator-griffin/blob/master/service/src/main/java/org/apache/griffin/core/GriffinWebApplication.java#L31)

<div id = "bubp"></div>

#### [best practices](https://github.com/spring-projects/spring-boot/wiki/Building-On-Spring-Boot)

- **@EnableAutoConfiguration**

ͨ����annotation�趨��Spring Boot�ܻ�����Ŀ�����Զ�����Ӧ��. ���磬���MySQL database��classpath��, ����û����database connection, ��Spring Boot���Զ�����һ��in-memory database.

- **@ComponentScan**

Spring Boot�Զ�ɨ��������Ŀ���ô˱�ǩ������components

- **@SpringBootApplication**

Spring BootӦ�õ�������趨��annotation class��main method�����Զ�����auto-configuration��component scanning��Ϊ������һ��convenience annotation���ȼ���declaring @Configuration, @EnableAutoConfiguration��@ComponentScan��

- **@Component**

������������class��һ��"component"��ʹ���Զ����ú�classpath scanningʱ��������classes�ܹ����Զ��ҵ�(auto-detection)������class-level annotationsҲ�ɱ���Ϊ����һ��component��e.g. **@Repository** annotation or AspectJ's **@Aspect** annotation��

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

### Spring Usage

<div id = "u0"></div>

#### bootstrap

�����������SpringApplication.run(Object, String[]) method��ֱ�Ӵ�main method������bootstrap application, Ĭ��bootstrap application�������¼����£�

1. Create an appropriate ApplicationContext instance (depending on your classpath)
2. Register a CommandLinePropertySource to expose command line arguments as Spring properties
3. Refresh the application context, loading all singleton beans
4. Trigger any CommandLineRunner beans

```
@Configuration
@EnableAutoConfiguration
public class MyApplication  {

    // ... Bean definitions

    public static void main(String[] args) throws Exception {
    SpringApplication.run(MyApplication.class, args);
    }
}
```
���ඨ�����õ�������������ʵ��
``` 
public static void main(String[] args) throws Exception {
     SpringApplication app = new SpringApplication(MyApplication.class);
     // ... customize app settings here
     app.run(args)
}
```
SpringApplications�ܹ��Ӷ���Դ��ȡbean����. һ�㽨����a single @Configuration class��bootstrap application, Ȼ��Ҳ����������ѡ��

1. Class - A Java class to be loaded by AnnotatedBeanDefinitionReader
2. Resource - An XML resource to be loaded by XmlBeanDefinitionReader, or a groovy script to be loaded by GroovyBeanDefinitionReader
3. Package - A Java package to be scanned by ClassPathBeanDefinitionScanner
4. CharSequence - A class name, resource handle or package name to loaded as appropriate. If the CharSequence cannot be resolved to class and does not resolve to a Resource that exists it will be considered a Package.

<div id = "u1"></div>

#### properties injection
spring����ͨ�� **@PropertySources** **@PropertySource** **@Value** **@ConfigurationProperties** **@TestPropertySource** [[***1***](https://www.baeldung.com/spring-value-annotation), [***2***](https://www.baeldung.com/properties-with-spring)] ��ʽ���������ļ��е�ֱֵ�Ӹ���field��ʡ�Գ������м��ض�ȡ���̡����һ�����֧�ֶ�value����[����](https://www.baeldung.com/spring-expression-language)

���⻹��֧�ֶԷ���������[Not-Managed-bean](https://www.baeldung.com/inject-properties-value-non-spring-class)��ע��properties

<div id = "u2"></div>

#### bean creation
spring����ͨ�� **@Component** **@Configuration** **@Bean** [[***1***](https://www.baeldung.com/spring-bean)] ��ʽ��������spring���������bean��ͬʱ����ָ�����÷�Χ[bean scope](https://github.com/Snailclimb/JavaGuide/blob/master/docs/system-design/framework/spring/SpringBean.md)

- **Application Context Events**
����ͨ��[@EventListener](https://www.baeldung.com/spring-context-events)������context�����¼�

- **@Configuration**
������class����һ������@Bean��������Щ�������ᱻSpring container������runtimeʱ������bean definitions��service requests. Configuration classes������non-final, non-local (i.e. not as instances returned from factory methods), allowing for runtime enhancements through a generated subclass.
```java
// ����AppConfig��ʵ������������
@Configuration
public class AppConfig {

    @Autowired Environment env;

    // ����MyBean��ʵ������������
    @Bean
    public MyBean myBean() {
        // instantiate, configure and return bean ...
        MyBean myBean = new MyBean();
        myBean.setName(env.getProperty("bean.name"));
        return myBean;
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
�����÷���@Bean methods��������@Configuration classes�У�@Bean methods����ֱ��callͬһ��@Configuration class������@Bean methods����ȷ��beans֮���������ǿ���ͺͻ���ĳ�Ϊ'inter-bean references'�����@Configuration classes����bean�������������Ƿ�final��private���η�

@Configuration classes������֧��Ƕ��ʹ��, nested configuration classes������static. �Ǿ�̬@Bean methods�������β�������configuration classes, ��ʹ��Щbean����@Configuration��ע��ϵͳҲ����Ե���������Ϊ��ͨ����bean
```
@Configuration
public class AppConfig {

   @Inject DataSource dataSource;

   @Bean
   public MyBean myBean() {
       return new MyBean(dataSource);
   }

   @Configuration
   static class DatabaseConfig {
       @Bean
       DataSource dataSource() {
           return new EmbeddedDatabaseBuilder().build();
       }
   }
}
```

@Bean methodsҲ���Բ�ͨ�����ͷ�ʽ����. �������������@Component class����a plain old class. ���ַ�ʽ��֮Ϊ'lite'. 
Bean methods��lite mode�±�spring����������ͨ��������(����factory-method declarations in XML), Ҳ��scoping and lifecycle callbacks����. ��Щ����������������˲����޸�, Ҳû���ر������. ��֮�෴������ͨ��@Configuration classes������beans, 'inter-bean references'��lite mode��֧��. ����lite mode����@Bean-method������һ��@Bean-method, ���������һ����׼��Java method invocation��Spring����ͨ��CGLIB proxy���ػ�invocation. ����inter-@Transactional method����֮��������ƣ�Ҳ��ͨ��proxy mode, Spring���ػ�invocation.
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
@Configuration����ͨ��[AnnotationConfigApplicationContext](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/context/annotation/Configuration.html)����ʼ������
```
AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
ctx.register(AppConfig.class);
ctx.refresh();
MyBean myBean = ctx.getBean(MyBean.class);
// use myBean ...
```

��Щ�����������Ҫ@Bean methods����[BeanFactoryPostProcessor](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/beans/factory/config/BeanFactoryPostProcessor.html)(BFPP) types. ����BFPP�����ܸ�Ԥ��@Configuration classes��һЩ��ǩ�Ĵ�����@Autowired, @Value, @PostConstruct. Ϊ�˱������lifecycle issues, BFPP���������container lifecycle���ڱ�ʵ����, ���Խ�BFPP-returning @Bean methods��Ϊstatic����
```
@Bean
public static PropertyPlaceholderConfigurer ppc() {
   // instantiate, configure and return ppc ...
}
```   
Ȼ��static @Bean methods���ᱻscoping and AOP semantics��̬��ǿ������BFPP cases����Ч, ͬʱҲ�޷�������������@Bean methods���õ�.��Ϊ��ʾ, һ��WARN-level log��Ϣ�ᱻ��¼��any non-static @Bean methods having a return type assignable to BeanFactoryPostProcessor.

- **@Import @ImportResource**
��@import��ǩҲ����ע��bean����(e.g. via constructor injection) 
```
@Configuration
public class DatabaseConfig {

   @Bean
   public DataSource dataSource() {
       // instantiate, configure and return DataSource
   }
}

@Configuration
@Import(DatabaseConfig.class)
public class AppConfig {

   private final DatabaseConfig dataConfig;

   public AppConfig(DatabaseConfig dataConfig) {
       this.dataConfig = dataConfig;
   }

   @Bean
   public MyBean myBean() {
       // reference the dataSource() bean method
       return new MyBean(dataConfig.dataSource());
   }
}
```
���ʹ��Spring XML���ã�@Configuration classes����Ҫʹ��@ImportResource��ǩ
```
@Configuration
@ImportResource("classpath:/com/acme/database-config.xml")
public class AppConfig {

   @Inject DataSource dataSource; // from XML

   @Bean
   public MyBean myBean() {
       // inject the XML-defined dataSource bean
       return new MyBean(this.dataSource);
   }
}
```

- **@Profile**
@Configuration classesʹ��@Profile�����ɸ���ָ��profile������ͬbean
```
@Profile("embedded")
@Configuration
public class EmbeddedDatabaseConfig {

   @Bean
   public DataSource dataSource() {
       // instantiate, configure and return embedded DataSource
   }
}

@Profile("production")
@Configuration
public class ProductionDatabaseConfig {

   @Bean
   public DataSource dataSource() {
       // instantiate, configure and return production DataSource
   }
}
``` 

- **@Scope, @DependsOn, @Primary, @Lazy @AutoConfigureBefore**
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
- **bean Circular Dependencies**
����beans֮�以����������������[lazy-workaround](https://www.baeldung.com/circular-dependencies-in-spring)�ȷ�ʽ���

<div id = "u2s1"></div>

#### bean creation order
Spring�Զ�����beanʵ����ʱ�����ָ���Ⱥ�˳��ͨ��[**@Order**](https://www.baeldung.com/spring-order), [**@DependsOn**](https://www.baeldung.com/spring-depends-on)������bean˳�����

���ϣ���˽�spring������ÿһ��bean������ʵ��˳�򣬿��Դ�[log4j2.xmlѡ��](https://docs.spring.io/spring/docs/4.3.26.RELEASE/spring-framework-reference/htmlsingle/#overview-logging)
```xml
<?xml version="1.0" encoding="UTF-8"?>
<Configuration status="WARN">
  <Appenders>
    <Console name="Console" target="SYSTEM_OUT">
      <PatternLayout pattern="%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n"/>
    </Console>
  </Appenders>
  <Loggers>
    <!-- org.springframework.beans.factory����bean�Ĳ��� -->
    <Logger name="org.springframework.beans.factory" level="DEBUG"/>
      <!-- ָ����־�����appender -->
      <AppenderRef ref="Console"/>
  </Loggers>
</Configuration>
```
����־�������֮�󣬿��Բ�ѯ��bean����˳��
```console
$ grep -in ajimpl /var/log/restart.log 
1968:2020-07-13T12:51:05.241Z DEBUG coordinationEventsProcessor-1 DefaultListableBeanFactory - Creating shared instance of singleton bean 'policyConnectivityFacadeImplAjImpl'
1972:2020-07-13T12:51:05.244Z DEBUG coordinationEventsProcessor-1 DefaultListableBeanFactory - Creating shared instance of singleton bean 'segmentServiceImplAjImpl'

# �鿴bean����˳��
$ grep -in 'singleton bean' /var/log/restart.log | less
10:2020-07-13T12:50:04.303Z DEBUG localhost-startStop-1 DefaultListableBeanFactory - Creating shared instance of singleton bean 'org.springframework.context.annotation.internalConfigurationAnnotationProcessor'
14:2020-07-13T12:50:04.696Z DEBUG localhost-startStop-1 DefaultListableBeanFactory - Creating shared instance of singleton bean 'IntegrationConfigurationBeanFactoryPostProcessor'
15:2020-07-13T12:50:04.953Z DEBUG localhost-startStop-1 DefaultListableBeanFactory - Creating shared instance of singleton bean 'org.springframework.context.support.PropertySourcesPlaceholderConfigurer#0'
```

<div id = "u2s2"></div>

#### bean reference
����Spring������beanʵ���ж��ַ�ʽ[**@Autowired/@Inject/@Resource**](https://www.baeldung.com/spring-annotations-resource-inject-autowire) , ���ڳ����໹֧��[**autowired-abstract-class**](https://www.baeldung.com/spring-autowired-abstract-class).  �����������ʱinjection��������[**lazy-injection**](https://www.baeldung.com/spring-lazy-annotation)

<div id = "u2s3"></div>

#### bean stages
Spring������beanʵ�����׶��п��Բ���һЩ�߼�[**@PostConstruct/InitializingBean/ApplicationListener/initMethod**](https://www.baeldung.com/running-setup-logic-on-startup-in-spring)

<div id = "u2s4"></div>

#### bean schedule
ͨ�� **@EnableScheduling @Scheduled** ����ʹ��Spring's scheduled task����,���Ʊ�ǩ���� **@EnableAsync, @EnableScheduling, @EnableTransactionManagement, @EnableAspectJAutoProxy, @EnableWebMvc**
```
@Configuration
@EnableScheduling
public class AppConfig {

   @Bean
   public MyTask task() {
       return new MyTask();
   }
}

package com.example.tasks;

public class MyTask {
   // ensure that MyTask.work() is called once every 1000 ms
   @Scheduled(fixedRate=1000)
   public void work() {
       // task execution logic
   }
}
```   
���MyTaskֱ��ͨ��@Component����, AppConfig����ֱ��ComponentScan�ҵ�MyTask������
```
@Configuration
@EnableScheduling
@ComponentScan(basePackages="com.example.tasks")
public class AppConfig {
}

package com.example.tasks;

@Component
public class MyTask {
   // ensure that MyTask.work() is called once every 1000 ms
   @Scheduled(fixedRate=1000)
   public void work() {
       // task execution logic
   }
}
```   
@ScheduledҲ����ֱ��������@Configuration classes�ķ�����
```
@Configuration
@EnableScheduling
public class AppConfig {

   @Scheduled(fixedRate=1000)
   public void work() {
       // task execution logic
   }
}
```
SpringҲ������schedulerʵ�֣�Ĭ�������ʹ��context��Ψһ��TaskScheduler bean����java.util.concurrent.ScheduledExecutorService bean.�������runtimeʱ�̶��޷�resolvable, a local single-threaded default scheduler��������ʹ��.�������scheduler��Ҫʵ��SchedulingConfigurer
```
@Configuration
@EnableScheduling
public class AppConfig implements SchedulingConfigurer {

   @Override
   public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
       taskRegistrar.setScheduler(taskExecutor());
   }

    // ensures that the task executor is properly shut down 
    // when the Spring application context itself is closed.
   @Bean(destroyMethod="shutdown")
   public Executor taskExecutor() {
       return Executors.newScheduledThreadPool(100);
   }
}
```   
�������ϸ���ȿ���(fine-grained control) ����ע�ᣬ�����Զ���trigger
```
@Configuration
@EnableScheduling
public class AppConfig implements SchedulingConfigurer {

   @Override
   public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
       taskRegistrar.setScheduler(taskScheduler());
       taskRegistrar.addTriggerTask(
           new Runnable() {
               public void run() {
                   myTask().work();
               }
           },
           new CustomTrigger() //���ƴ����߼�
       );
   }

   @Bean(destroyMethod="shutdown")
   public Executor taskScheduler() {
       return Executors.newScheduledThreadPool(42);
   }

   @Bean
   public MyTask myTask() {
       return new MyTask();
   }
}
```

<div id = "u3"></div>

#### aop
Spring AOP�ṩ��Aspect-Oriented Programming֧�֡��ο�Reference[[***1***](https://howtodoinjava.com/spring-aop-tutorial/), [***2***](https://www.baeldung.com/spring-aop-pointcut-tutorial)]

+ [Spring AOP  Proxying Mechanisms](https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#aop-proxying)
+ [Spring AOP vs AspectJ](https://www.baeldung.com/spring-aop-vs-aspectj)

    `Spring AOP���AspectJ��ʹ���ϼ�һЩ��ȱ��Ҳ���ԣ���ֻ��֧��public������AOP,��֧�ֹ��캯����ͨ��proxyִ��Ч��Ҳ��weaved class�ļ�Ч�ʵ�. ��̬֯�뷽ʽ��Ҫ��build������ͨ��AspectJ�޸�class�ļ�����̬֯������Ҫ����jvmʱ��ָ���������� -javaagent:aspectjweaver.jar �� -javaagent:spring-instrument.jar���� -javaagent:spring-agent.jar`

    `֯�뷽ʽ�ѷ���pointcut��class�ļ�ȫ���޸ģ�proxy��ʽ��������proxy�ı���÷��ء�proxy��ʽ�£�����ͨ��spring�������������Ķ���ʵ���Ż���advice`
+ [Spring AOP Samples Of Three Modes](sample/spring)
    
    - spring-proxy-aop : 
    `mvn clean compile exec:java -Dexec.mainClass=king.law.spring.aop.TestSpringAop`
    - spring-compile-weave : 
    `mvn clean compile aspectj:compile exec:java -Dexec.mainClass=king.law.aspectj.aop.compile.TestAspectJCompileAop`
    - spring-loadtime-weave : 
    `mvn clean package exec:exec`
 + [aop��pointcut���ʽ](https://zhuanlan.zhihu.com/p/63001123)    

<div id = "u3s1"></div>

#### aspect bean injection
����һ��aspect֮�� �������Ҫע��һЩbeanʵ��
```java
@Aspect
@Component
public class MyclassAspect {
    @Autowired
    private MyBean bean;
```
��aspect����weave compile�������룬���е�ʱ����beanʼ��Ϊnull, ���ڴ�[��������](https://stackoverflow.com/questions/9633840/spring-autowired-bean-for-aspect-aspect-is-null). ԭ�����֮aspect bean��һ����Spring container֮�ⴴ���ĵ�����������޷���ע��. ����취������ **@configuration** ������, ��������[������ʽ](https://blog.csdn.net/zlp1992/article/details/81037529)
```java
@Aspect
@Configurable(autowire = Autowire.BY_TYPE)
public class MyclassAspect {
    @Autowired
    private MyBean bean;
```
���������ʱ���� MyBean NoSuchBeanDefinitionException������ʹ��lazy injectҲ�޷����������Ҫ���ǿ���aspect�����pointcut��Ŀ�����г�ͻ���������������⣬����
```java
# ϣ��pointcut��PolicyFacadeImpl���з�������Ч
# ��PolicyFacadeImpl��Щ��������ֲ�����棬���autowireʧ��
@Aspect
@Configurable(autowire = Autowire.BY_TYPE)
public class MyclassAspect {
    @Autowired
    private MyBean bean;

    @Pointcut("execution(* com.example.policy.facade.PolicyFacadeImpl.*(..))")
    public void tracePointCut() {
    }
    ...
}

# PolicyFacadeImpl ��һ��@PostConstruct��initialize��ʼ������
# �ƺ���ֲ���������ɲ���Ԥ֪�ĳ�ͻ������ϸ��springû�и�������ϸ��־
# �ƺ���Щclass����ĳ�ʼ������Ҳ���г�ͻ
@Aspect
@Service
public class PolicyFacadeImpl {
    @PostConstruct
    private void initialize() {...}
    ...
}

# �����pointcut�����з����ĳɲ��ַ������쳣�͵õ������spring��������
@Pointcut("execution(* com.example.policy.facade.PolicyFacadeImpl.create*(..))")
public void tracePointCut() {
}
```

<div id = "u4"></div>

#### jpa
[spring-data-jpa](https://www.baeldung.com/the-persistence-layer-with-spring-data-jpa)Ϊspring�ṩ�������ݶ��������

ʹ��[**@NoRepositoryBean**](https://www.baeldung.com/spring-data-jpa-method-in-all-repositories) ������Base Repository Interface, ����ʱ��mock entity��spring test entity manager����ǿ��ܻ���������

<div id = "u4s1"></div>

#### Repository
[**CrudRepository, JpaRepository, and PagingAndSortingRepository**](https://www.baeldung.com/spring-data-repositories)����Ҫ���ֲֿ����ͣ�����[CrudRepository](https://www.baeldung.com/spring-data-crud-repository-save) ��generic CRUD operations���ͣ����ṩ����������(out of the box)�����������ݿ⽻��

### Troubleshooting
<div id = "ts1"></div>

#### NoUniqueBeanDefinitionException
������������ʹ��ڶ��beanʵ������������ע��ʱ������Ҫ����Spring����Ҫʹ����һ��beanʵ�������û��ָ��Spring��throw a NoUniqueBeanDefinitionException��������������֪��Ӧ��ע����һ��beanʵ��

�����ַ�ʽ��ָ��ע��beanʵ����ʹ��@Primary��ǩ���������Spring����primary beanʵ����autowireʱ����������ʵ������ʹ��@Qualifier��ǩ�����ܸ���Spring��Ҫע��beanʵ����name��Ĭ�������beanʵ������name������ĸСд��class name[����](https://springframework.guru/fixing-nonuniquebeandefinitionexception-exceptions/)

<div id = "ts2"></div>

#### NoSuchBeanDefinitionException
[**NoSuchBeanDefinitionException**](https://www.baeldung.com/spring-nosuchbeandefinitionexception)��һ�ֳ�����ע����󣬳��������Ҳ���beanʵ��,����class�����Ҳ�����������

����ʱ��Ҳ������bean�����Ⱥ�˳�������������bean1����bean2������bean1������autowire��bean2�����ʱ��Ҳ������������󡣽���취������bean1 *@lazy* autowire bean2������[ָ������˳��](#u2s1)

### Unit Test
<div id = "ut1"></div>

#### junit
The Spring TestContext framework�ṩ[**@ContextConfiguration**](https://www.baeldung.com/junit-assert-exception), ���ܽ���an array of @Configuration Class objects
```
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={AppConfig.class, DatabaseConfig.class})
public class MyTests {

   @Autowired MyBean myBean;

   @Autowired DataSource dataSource;

   @Test
   public void test() {
       // assertions against myBean ...
   }
}
```   
spring����ṩ�˺�junit���ɵķ�ʽ
* junit integrates with spring<br>
  1. simply annotate a JUnit 4 based test class with **@RunWith(SpringRunner.class)**<br>[***1.description***](https://github.com/lsieun/learn-spring/blob/master/spring-boot/junit/RunWith.md) [***2.code***](https://github.com/search?q=%40RunWith%28SpringJUnit4ClassRunner.class%29&type=Code)<br>
  2. org.springframework.test.web.servlet.**MockMvc**, server-side Spring MVC test support<br>[***sample***](https://github.com/apache/incubator-griffin/blob/master/service/src/test/java/org/apache/griffin/core/job/JobControllerTest.java#L67)
  3. ...
  4. ...
* [test in spring boot](https://www.baeldung.com/spring-boot-testing)
  1. **@DataJpaTest** combinates with @RunWith(SpringRunner.class) for a typical JPA test, which will use an embedded in-memory database<br>[***sample***](https://github.com/apache/incubator-griffin/blob/master/service/src/test/java/org/apache/griffin/core/job/JobInstanceBeanRepoTest.java#L53)
