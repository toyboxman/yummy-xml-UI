+ <span style="font-size:6em;">多媒体处理</span>
    - [FFmpeg命令编辑视频](https://mp.weixin.qq.com/s?__biz=MjM5NjQ4MjYwMQ==&mid=2664614334&idx=2&sn=22c884dc2553b16f7f5cf808fac3a2ce)

+ [system monitor](system-monitor/monitor.md)

+ [maven](java/mvn.md)

### digest
德国诗人海涅说，不要轻视观念的影响力，教授在沉静的研究中所培育出来的哲学概念可能颠覆一个文明。“海涅这句话是形容康德的，但是我认为用来形容马克思更为合适。马克思的墓前刻了两行字：哲学家们只是用不同的方式解释世界,而问题在于改变世界。但是大家有没有想过，马克思是通过什么方式去改变世界的？他是通过解释世界去改变世界的。”

冯友兰说，“哲学，特别是形而上学，对我们增进对事实的知识并无用处，但是它为我们提高我们的心智，则是必不可少的。”冯友兰认为，人和其他动物的不同之处在于，动物是没有自知力和反思力，而人对于自己的行为有一种觉解，这种觉解对他正在做的事情产生了意义，这些意义最终形成的整体，构成他的人生境界。

西方的斯多葛学派与冯友兰的观点类似，在他们看来，哲学可以带来对于人生的慰藉。马克·奥勒留说：“人所执着的是什么呢？啊，除哲学别无他物。唯哲学可以保持我心中的神性，使我们免受伤害与屈辱，使我们超然于苦乐之上。使我们不致装聋卖傻或矫情掩饰，使我们无需仰人鼻息，使人驱使。何止于此，哲学使我们的心灵虽遭逆顺而安之若素。”

庄子说的“知其已知者”在一个很基本的意义上，哲学就是在干这个事。维特根斯坦也说过几乎和庄子一模一样的话：科学是在不断地了解未知的世界，而哲学一直是在收集回忆。

什么是追求独异？吃饭不只求饱，而要吃得“正确”“健康”；不参加“大众旅游”，而要最“纯正”的“旅行”；工作不应只为稻粱谋，而要意义和乐趣。“成功地实现自我”，这是晚现代文化的生活方式导向，也是新中产追求的目标。然而事实上，基于此的个人幸福却显得虚无缥缈——一方面这取决于变化无常的个人体验，另一方面取决于难以捉摸的文化市场。

在《论法的精神》中，孟德斯鸠宣称权力显示出一种自我膨胀并滥用自身特权的自然倾向，它不会停止，直到在它的外部找到一种限制。这一教训在任何时间和任何地点都适用。如今，不停膨胀并不断自主化的权力是经济权力、金融权力。

### ansible
Ansible管理工作站配置[**1**](https://mp.weixin.qq.com/s?__biz=MjM5NjQ4MjYwMQ==&mid=2664613143&idx=1&sn=8fbd47dcf411ce26c80ffa873304d7c1), [**2**](https://mp.weixin.qq.com/s?__biz=MjM5NjQ4MjYwMQ==&mid=2664613180&idx=1&sn=b57f1b66f3ded400f029f4f8b3b8f4bc), [**3**](https://mp.weixin.qq.com/s?__biz=MjM5NjQ4MjYwMQ==&mid=2664614674&idx=2&sn=23619c078386431b6810107e7246e8b2)

### Kubernetes
root@ncpmaster:/home/pksadmin# kubectl describe pod coredns-fb8b8dccf-q78fl -n kube-system
kubectl get ds -n kube-system
[Kubernetes 学习曲线](https://mp.weixin.qq.com/s?__biz=MjM5NjQ4MjYwMQ==&mid=2664614519&idx=2&sn=924123937683f49e79fca00c71a4463c)
[配置 Kubernetes](https://mp.weixin.qq.com/s?__biz=MjM5NjQ4MjYwMQ==&mid=2664614644&idx=3&sn=8176ed98194bf765a5e5ed1cdfbd503b)
[k8s配置工具](https://mp.weixin.qq.com/s?__biz=MjM5NjQ4MjYwMQ==&mid=2664614666&idx=1&sn=9259273f43b7ca54c00b500988ba4359)

### algorithm
[PBFT-SBFT](https://ittaiab.github.io/2019-06-23-what-is-the-difference-between/)

### python

### replacement
- notepad++

需要在curl payload的json每一行末尾增加一个'\', 且字符后不能有空格，否则bash解析会失败。
```
curl -k -H "Content-Type: application/json" -H "Accept: application/json" -X POST http://127.0.0.1:8080/api/v1/measures
{
    "name":"accuracy_measure",
    "measure.type":"griffin",
    "dq.type":"accuracy",
    "process.type":"batch",
    "owner":"test",
    "description":"measure description"
}
```
Replace All : '\r\n'  --> '  \\\r\n'

### java
try (DatagramSocket datagramSocket = new DatagramSocket()) {
    byte[] buffer = {10, 23, 12};
    byte[] IP = {10, 117, 4, 117};
    InetAddress address = InetAddress.getByAddress(IP);
    DatagramPacket packet = new DatagramPacket(
        buffer, buffer.length, address, 6831
    );
    datagramSocket.send(packet);
} catch (IOException e) {
    e.printStackTrace();
}

### database
[实现MySQL每秒 57万的写入](https://mp.weixin.qq.com/s?__biz=MjM5MDAxOTk2MQ==&mid=2650281971&idx=1&sn=08bd75dd606f06ef1d67409d13d23a47)

[MySQL 8 vs PostgreSQL 10](https://mp.weixin.qq.com/s?__biz=MjM5MDAxOTk2MQ==&mid=2650281707&idx=1&sn=52fdb59331e5decd81f48d5099a11436)

[2019上半年合集](https://mp.weixin.qq.com/s?__biz=MjM5MDAxOTk2MQ==&mid=2650281866&idx=1&sn=489001936b4855c88bf7b32e738ebc9d)

### Geekbench 4
[测试不同公有云服务](https://www.ithome.com/0/431/894.htm)

### GraphQL
[工具和库 ](https://mp.weixin.qq.com/s?__biz=MjM5MDE0Mjc4MA==&mid=2651017395&idx=3&sn=ab0b3c87c20d4cdaad82321764195210)

### NPM
[Web开发包注册中心的NPM](https://mp.weixin.qq.com/s?__biz=MjM5MDE0Mjc4MA==&mid=2651016822&idx=2&sn=d38c1f59ebd04052d95f2136fb950d5c)

### go

### interview
https://www.journaldev.com/2366/core-java-interview-questions-and-answers

### hive

### Kubernetes
https://docs.microsoft.com/en-us/azure/aks/concepts-clusters-workloads

### 微服务配置中心
https://nobodyiam.com/2018/07/29/configuration-center-makes-microservices-smart/
Spring Cloud（七）：配置中心（Git 版与动态刷新）
https://windmt.com/2018/04/19/spring-cloud-7-config-sample/

### hadoop
  
### superset
https://superset.incubator.apache.org/

### Druid
https://en.wikipedia.org/wiki/Druid_(open-source_data_store)

### Flink
https://en.wikipedia.org/wiki/Apache_Flink

### junit/mock
@InjectMocks/@Mock
code sample:
[1](https://github.com/apache/incubator-griffin/blob/master/service/src/test/java/org/apache/griffin/core/job/JobControllerTest.java#L62)

### spring
* junit integrates with spring
* test in spring boot
https://www.baeldung.com/spring-boot-testing

* spring xml schema
https://docs.spring.io/spring/docs/4.0.x/spring-framework-reference/html/xsd-config.html
https://docs.spring.io/spring/docs/4.0.x/spring-framework-reference/html/spring-core.html

* @NoRepositoryBean
public interface CrudRepository
mock entity probably hit problem running with spring test entity manager

* Boot Configuration Binding
https://github.com/spring-projects/spring-boot/wiki/Spring-Boot-Configuration-Binding
code sample:
[1](https://github.com/apache/incubator-griffin/blob/master/service/src/main/java/org/apache/griffin/core/GriffinWebApplication.java#L31)

* org.springframework.boot.autoconfigure.SpringBootApplication
Indicates a configuration class that declares one or more @Bean methods 
and also triggers auto-configuration and component scanning. This is a convenience 
annotation that is equivalent to declaring @Configuration, @EnableAutoConfiguration and @ComponentScan.

https://www.baeldung.com/junit-assert-exception

@Primary, @Order and @Qualifier
@dependon
@ConditionalXXXX
@ObjectMapper

In module A I have a class annotated with @Configuration and also @AutoConfigureBefore(ClassFromModuleD.class). In module B I have another class annotated with @Configuration and also @AutoConfigureBefore(ClassFromModuleA.class)

@Bean Methods in @Configuration Classes
Typically, @Bean methods are declared within @Configuration classes. 
In this case, bean methods may reference other @Bean methods in the same class by calling them directly. 
This ensures that references between beans are strongly typed and navigable. 
Such so-called 'inter-bean references' are guaranteed to respect scoping and AOP semantics,
just like getBean() lookups would. These are the semantics known from the original 
'Spring JavaConfig' project which require CGLIB subclassing of each such configuration class at runtime. 
As a consequence, @Configuration classes and their factory methods must not be marked as final or private in this mode. For example:
   @Configuration
   public class AppConfig {
  
       @Bean
       public FooService fooService() {
           return new FooService(fooRepository());
       }
  
       @Bean
       public FooRepository fooRepository() {
           return new JdbcFooRepository(dataSource());
       }
  
       // ...
   }
@Bean Lite Mode
@Bean methods may also be declared within classes that are not annotated with @Configuration. For example, bean methods may be declared in a @Component class or even in a plain old class. In such cases, a @Bean method will get processed in a so-called 'lite' mode.
Bean methods in lite mode will be treated as plain factory methods by the container (similar to factory-method declarations in XML), with scoping and lifecycle callbacks properly applied. The containing class remains unmodified in this case, and there are no unusual constraints for the containing class or the factory methods.
In contrast to the semantics for bean methods in @Configuration classes, 'inter-bean references' are not supported in lite mode. Instead, when one @Bean-method invokes another @Bean-method in lite mode, the invocation is a standard Java method invocation; Spring does not intercept the invocation via a CGLIB proxy. This is analogous to inter-@Transactional method calls where in proxy mode, Spring does not intercept the invocation — Spring does so only in AspectJ mode.
For example:
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
Bootstrapping
See @Configuration Javadoc for further details including how to bootstrap the container using AnnotationConfigApplicationContext and friends.
BeanFactoryPostProcessor-returning @Bean methods
Special consideration must be taken for @Bean methods that return Spring BeanFactoryPostProcessor (BFPP) types. Because BFPP objects must be instantiated very early in the container lifecycle, they can interfere with processing of annotations such as @Autowired, @Value, and @PostConstruct within @Configuration classes. To avoid these lifecycle issues, mark BFPP-returning @Bean methods as static. For example:
       @Bean
       public static PropertyPlaceholderConfigurer ppc() {
           // instantiate, configure and return ppc ...
       }
   
By marking this method as static, it can be invoked without causing instantiation of its declaring @Configuration class, thus avoiding the above-mentioned lifecycle conflicts. Note however that static @Bean methods will not be enhanced for scoping and AOP semantics as mentioned above. This works out in BFPP cases, as they are not typically referenced by other @Bean methods. As a reminder, a WARN-level log message will be issued for any non-static @Bean methods having a return type assignable to BeanFactoryPostProcessor.


* org.springframework.scheduling.annotation.EnableScheduling
Enables Spring's scheduled task execution capability, similar to functionality found in Spring's <task:*> XML namespace. To be used on @Configuration classes as follows:
   @Configuration
   @EnableScheduling
   public class AppConfig {
  
       // various @Bean definitions
   }
This enables detection of @Scheduled annotations on any Spring-managed bean in the container. For example, given a class MyTask
   package com.myco.tasks;
  
   public class MyTask {
  
       @Scheduled(fixedRate=1000)
       public void work() {
           // task execution logic
       }
   }
the following configuration would ensure that MyTask.work() is called once every 1000 ms:
   @Configuration
   @EnableScheduling
   public class AppConfig {
  
       @Bean
       public MyTask task() {
           return new MyTask();
       }
   }
Alternatively, if MyTask were annotated with @Component, the following configuration would ensure that its @Scheduled method is invoked at the desired interval:
   @Configuration
   @EnableScheduling
   @ComponentScan(basePackages="com.myco.tasks")
   public class AppConfig {
   }
Methods annotated with @Scheduled may even be declared directly within @Configuration classes:
   @Configuration
   @EnableScheduling
   public class AppConfig {
  
       @Scheduled(fixedRate=1000)
       public void work() {
           // task execution logic
       }
   }
By default, will be searching for an associated scheduler definition: either a unique org.springframework.scheduling.TaskScheduler bean in the context, or a TaskScheduler bean named "taskScheduler" otherwise; the same lookup will also be performed for a java.util.concurrent.ScheduledExecutorService bean. If neither of the two is resolvable, a local single-threaded default scheduler will be created and used within the registrar.
When more control is desired, a @Configuration class may implement SchedulingConfigurer. This allows access to the underlying ScheduledTaskRegistrar instance. For example, the following example demonstrates how to customize the Executor used to execute scheduled tasks:
   @Configuration
   @EnableScheduling
   public class AppConfig implements SchedulingConfigurer {
  
       @Override
       public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
           taskRegistrar.setScheduler(taskExecutor());
       }
  
       @Bean(destroyMethod="shutdown")
       public Executor taskExecutor() {
           return Executors.newScheduledThreadPool(100);
       }
   }
Note in the example above the use of @Bean(destroyMethod="shutdown"). This ensures that the task executor is properly shut down when the Spring application context itself is closed.
Implementing SchedulingConfigurer also allows for fine-grained control over task registration via the ScheduledTaskRegistrar. For example, the following configures the execution of a particular bean method per a custom Trigger implementation:
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
               new CustomTrigger()
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
For reference, the example above can be compared to the following Spring XML configuration:
   <beans>

     <task:annotation-driven scheduler="taskScheduler"/>

     <task:scheduler id="taskScheduler" pool-size="42"/>

     <task:scheduled-tasks scheduler="taskScheduler">
         <task:scheduled ref="myTask" method="work" fixed-rate="1000"/>
     </task:scheduled-tasks>

     <bean id="myTask" class="com.foo.MyTask"/>

 </beans>
 
The examples are equivalent save that in XML a fixed-rate period is used instead of a custom Trigger implementation; this is because the task: namespace scheduled cannot easily expose such support. This is but one demonstration how the code-based approach allows for maximum configurability through direct access to actual componentry.

@ComponentScan
Configures component scanning directives for use with @Configuration classes. Provides support parallel with Spring XML's <context:component-scan> element.
Either basePackageClasses or basePackages (or its alias value) may be specified to define specific packages to scan. If specific packages are not defined, scanning will occur from the package of the class that declares this annotation.
Note that the <context:component-scan> element has an annotation-config attribute; however, this annotation does not. This is because in almost all cases when using @ComponentScan, default annotation config processing (e.g. processing @Autowired and friends) is assumed. Furthermore, when using AnnotationConfigApplicationContext, annotation config processors are always registered, meaning that any attempt to disable them at the @ComponentScan level would be ignored.


Bootstrapping @Configuration classes
Via AnnotationConfigApplicationContext
@Configuration classes are typically bootstrapped using either AnnotationConfigApplicationContext or its web-capable variant, AnnotationConfigWebApplicationContext. A simple example with the former follows:
   AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
   ctx.register(AppConfig.class);
   ctx.refresh();
   MyBean myBean = ctx.getBean(MyBean.class);
   // use myBean ...
   
See AnnotationConfigApplicationContext Javadoc for further details and see AnnotationConfigWebApplicationContext for web.xml configuration instructions.
Via Spring <beans> XML
As an alternative to registering @Configuration classes directly against an AnnotationConfigApplicationContext, @Configuration classes may be declared as normal <bean> definitions within Spring XML files:
   <beans>
    <context:annotation-config/>
    <bean class="com.acme.AppConfig"/>
 </beans>
In the example above, <context:annotation-config/> is required in order to enable ConfigurationClassPostProcessor and other annotation-related post processors that facilitate handling @Configuration classes.
Via component scanning
@Configuration is meta-annotated with @Component, therefore @Configuration classes are candidates for component scanning (typically using Spring XML's <context:component-scan/> element) and therefore may also take advantage of @Autowired/@Inject like any regular @Component. In particular, if a single constructor is present autowiring semantics will be applied transparently:
   @Configuration
   public class AppConfig {
       private final SomeBean someBean;
  
       public AppConfig(SomeBean someBean) {
           this.someBean = someBean;
       }
  
       // @Bean definition using "SomeBean"
  
   }
@Configuration classes may not only be bootstrapped using component scanning, but may also themselves configure component 
scanning using the @ComponentScan annotation:
   @Configuration
   @ComponentScan("com.acme.app.services")
   public class AppConfig {
       // various @Bean definitions ...
   }
See the @ComponentScan javadoc for details.
Working with externalized values
Using the Environment API
Externalized values may be looked up by injecting the Spring org.springframework.core.env.Environment into a @Configuration class the usual (e.g. using the @Autowired annotation):
   @Configuration
   public class AppConfig {
  
       @Autowired Environment env;
  
       @Bean
       public MyBean myBean() {
           MyBean myBean = new MyBean();
           myBean.setName(env.getProperty("bean.name"));
           return myBean;
       }
   }
Properties resolved through the Environment reside in one or more "property source" objects, and @Configuration classes may contribute property sources to the Environment object using the @PropertySources annotation:
   @Configuration
   @PropertySource("classpath:/com/acme/app.properties")
   public class AppConfig {
  
       @Inject Environment env;
  
       @Bean
       public MyBean myBean() {
           return new MyBean(env.getProperty("bean.name"));
       }
   }
See Environment and @PropertySource Javadoc for further details.
Using the @Value annotation
Externalized values may be 'wired into' @Configuration classes using the @Value annotation:
   @Configuration
   @PropertySource("classpath:/com/acme/app.properties")
   public class AppConfig {
  
       @Value("${bean.name}") String beanName;
  
       @Bean
       public MyBean myBean() {
           return new MyBean(beanName);
       }
   }
This approach is most useful when using Spring's PropertySourcesPlaceholderConfigurer, usually enabled via XML with <context:property-placeholder/>. See the section below on composing @Configuration classes with Spring XML using @ImportResource, see @Value Javadoc, and see @Bean Javadoc for details on working with BeanFactoryPostProcessor types such as PropertySourcesPlaceholderConfigurer.
Composing @Configuration classes
With the @Import annotation
@Configuration classes may be composed using the @Import annotation, not unlike the way that <import> works in Spring XML. Because @Configuration objects are managed as Spring beans within the container, imported configurations may be injected the usual way (e.g. via constructor injection):
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
Now both AppConfig and the imported DatabaseConfig can be bootstrapped by registering only AppConfig against the Spring context:
   new AnnotationConfigApplicationContext(AppConfig.class);
With the @Profile annotation
@Configuration classes may be marked with the @Profile annotation to indicate they should be processed only if a given profile or profiles are active:
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
See the @Profile and org.springframework.core.env.Environment javadocs for further details.
With Spring XML using the @ImportResource annotation
As mentioned above, @Configuration classes may be declared as regular Spring <bean> definitions within Spring XML files. It is also possible to import Spring XML configuration files into @Configuration classes using the @ImportResource annotation. Bean definitions imported from XML can be injected the usual way (e.g. using the Inject annotation):
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
With nested @Configuration classes
@Configuration classes may be nested within one another as follows:
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
When bootstrapping such an arrangement, only AppConfig need be registered against the application context. By virtue of being a nested @Configuration class, DatabaseConfig will be registered automatically. This avoids the need to use an @Import annotation when the relationship between AppConfig DatabaseConfig is already implicitly clear.
Note also that nested @Configuration classes can be used to good effect with the @Profile annotation to provide two options of the same bean to the enclosing @Configuration class.
Configuring lazy initialization
By default, @Bean methods will be eagerly instantiated at container bootstrap time. To avoid this, @Configuration may be used in conjunction with the @Lazy annotation to indicate that all @Bean methods declared within the class are by default lazily initialized. Note that @Lazy may be used on individual @Bean methods as well.
Testing support for @Configuration classes
The Spring TestContext framework available in the spring-test module provides the @ContextConfiguration annotation, which as of Spring 3.1 can accept an array of @Configuration Class objects:
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
See TestContext framework reference documentation for details.
Enabling built-in Spring features using @Enable annotations
Spring features such as asynchronous method execution, scheduled task execution, annotation driven transaction management, and even Spring MVC can be enabled and configured from @Configuration classes using their respective "@Enable" annotations. See @EnableAsync, @EnableScheduling, @EnableTransactionManagement, @EnableAspectJAutoProxy, and @EnableWebMvc for details.
Constraints when authoring @Configuration classes
Configuration classes must be provided as classes (i.e. not as instances returned from factory methods), allowing for runtime enhancements through a generated subclass.
Configuration classes must be non-final.
Configuration classes must be non-local (i.e. may not be declared within a method).
Any nested configuration classes must be declared as static.
@Bean methods may not in turn create further configuration classes (any such instances will be treated as regular beans, 
with their configuration annotations remaining undetected).

org.springframework.boot public class SpringApplication
extends Object
Classes that can be used to bootstrap and launch a Spring application from a Java main method. By default class will perform the following steps to bootstrap your application:
Create an appropriate ApplicationContext instance (depending on your classpath)
Register a CommandLinePropertySource to expose command line arguments as Spring properties
Refresh the application context, loading all singleton beans
Trigger any CommandLineRunner beans
In most circumstances the static run(Object, String[]) method can be called directly from your main method to bootstrap your application:
   @Configuration
   @EnableAutoConfiguration
   public class MyApplication  {
  
     // ... Bean definitions
  
     public static void main(String[] args) throws Exception {
       SpringApplication.run(MyApplication.class, args);
     }
   }
   
For more advanced configuration a SpringApplication instance can be created and customized before being run:
   public static void main(String[] args) throws Exception {
     SpringApplication app = new SpringApplication(MyApplication.class);
     // ... customize app settings here
     app.run(args)
   }
   
SpringApplications can read beans from a variety of different sources. It is generally recommended that a single @Configuration class is used to bootstrap your application, however, any of the following sources can also be used:
Class - A Java class to be loaded by AnnotatedBeanDefinitionReader
Resource - An XML resource to be loaded by XmlBeanDefinitionReader, or a groovy script to be loaded by GroovyBeanDefinitionReader
Package - A Java package to be scanned by ClassPathBeanDefinitionScanner
CharSequence - A class name, resource handle or package name to loaded as appropriate. If the CharSequence cannot be 
resolved to class and does not resolve to a Resource that exists it will be considered a Package.


https://cwiki.apache.org/confluence/display/HAWQ/ASF+Maturity+Evaluation
https://cwiki.apache.org/confluence/display/CARBONDATA/Apache+Maturity+Model+Assessment+for+CarbonData
? security statement
? license tree
? dependency tree

http://carbondata.apache.org/pdf/CarbonData-TPCH-Report.pdf
? performance data

https://cwiki.apache.org/confluence/display/HAWQ/Contributing+to+HAWQ
https://cwiki.apache.org/confluence/display/IMPALA/Contributing+to+Impala
? how to contribute

https://scan.coverity.com/projects/apache-incubator-hawq
? coverity

https://stackoverflow.com/questions/tagged/hawq
? social media(stackoverflow, tweet)

https://cwiki.apache.org/confluence/display/IMPALA/Effective+Coding+Practices
?code standard

https://github.com/ratpack/ratpack
Ratpack is a simple, capable, toolkit for creating high performance web applications.
Ratpack is built on Java and the Netty event-driven networking engine. The API is optimized for Groovy and Java 8.

Apache Druid (incubating)
Apache Druid (incubating) is a high performance analytics data store for event-driven data.

### griffin
Elasticsearch
102      26701 26682  8 13:14 ?        00:00:16 /docker-java-home/jre/bin/java -Xms2g -Xmx2g -XX:+UseConcMarkSweepGC -XX:CMSInitiatingOccupancyFraction=75 -XX:+UseCMSInitiatingOccupancyOnly -XX:+AlwaysPreTouch -server -Xss1m -Djava.awt.headless=true -Dfile.encoding=UTF-8 -Djna.nosys=true -Djdk.io.permissionsUseCanonicalPath=true -Dio.netty.noUnsafe=true -Dio.netty.noKeySetOptimization=true -Dio.netty.recycler.maxCapacityPerThread=0 -Dlog4j.shutdownHookEnabled=false -Dlog4j2.disable.jmx=true -Dlog4j.skipJansi=true -XX:+HeapDumpOnOutOfMemoryError -Des.path.home=/usr/share/elasticsearch -cp /usr/share/elasticsearch/lib/* org.elasticsearch.bootstrap.Elasticsearch

https://hadoop.apache.org/docs/r2.9.1/hadoop-project-dist/hadoop-common/SingleCluster.html#Execution

export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64 in .bashrc
root@griffin:/apache/hadoop-2.6.5# cat /etc/hosts
127.0.0.1       localhost
172.17.0.2      es es
172.17.0.3      griffin
root@griffin:/apache# cat /etc/hostname 
griffin

root@griffin:/apache# env
ES_HOSTNAME=es
ES_ENV_JAVA_HOME=/docker-java-home/jre
ES_PORT_9300_TCP=tcp://172.17.0.2:9300
ES_ENV_CA_CERTIFICATES_JAVA_VERSION=20170531+nmu1
ES_PORT_9300_TCP_PORT=9300
ES_PORT_9200_TCP=tcp://172.17.0.2:9200
ES_PORT_9300_TCP_PROTO=tcp
ES_ENV_JAVA_VERSION=8u141
ES_ENV_LANG=C.UTF-8
ES_ENV_ELASTICSEARCH_VERSION=5.5.2
ES_PORT_9200_TCP_PROTO=tcp
ES_ENV_JAVA_DEBIAN_VERSION=8u141-b15-1~deb9u1
ES_PORT_9200_TCP_ADDR=172.17.0.2
ES_ENV_ELASTICSEARCH_DEB_VERSION=5.5.2
ES_ENV_GOSU_VERSION=1.10
ES_PORT_9300_TCP_ADDR=172.17.0.2
ES_PORT=tcp://172.17.0.2:9200
ES_PORT_9200_TCP_PORT=9200
ES_NAME=/griffin/es
   
   https://cwiki.apache.org/confluence/display/Hive/Hive+Schema+Tool
   Run schemaTool to create the initial DB structure https://mapr.com/docs/61/Hive/Config-RemotePostgreSQLForHiveMetastore.html
  172  hive/bin/schematool -dbType postgres -initSchema
  173  hive/bin/schematool -dbType postgres -initSchema -dryRun
  Get schema information:
/apache/hive/bin/schematool -dbType postgres -info


griffin/service
root     29711 26909 59 13:15 pts/0    00:01:15 java -jar -Xmx1500m -Xms1500m service.jar

yarn-site.xml
<property>
	<name>yarn.resourcemanager.hostname</name>
	<value>localhost</value>
</property>
<property>
	<name>yarn.nodemanager.aux-services</name>
	<value>mapreduce_shuffle</value>
</property>
<property>
	<name>yarn.nodemanager.aux-services.mapreduce.shuffle.class</name>
	<value>org.apache.hadoop.mapred.ShuffleHandler</value>
</property>
<property>
	<name>yarn.nodemanager.log-dirs</name>
	<value>/tmp/logs</value>
</property>
<property>
	<name>yarn.log-aggregation-enable</name>
	<value>true</value>
</property>
<property>
	<name>yarn.nodemanager.remote-app-log-dir</name>
	<value>/yarn-logs/logs</value>
</property>
<property>
	<name>yarn.nodemanager.remote-app-log-dir-suffix</name>
	<value>logs</value>
</property>
<property>
	<name>yarn.log-aggregation.retain-seconds</name>
	<value>360000</value>
</property>
<property>
	<name>yarn.log.server.url</name>
	<value>http://localhost:19888/jobhistory/logs</value>
</property>
<!-- for java 8 -->
<property>
    <name>yarn.nodemanager.pmem-check-enabled</name>
    <value>false</value>
</property>
<property>
    <name>yarn.nodemanager.vmem-check-enabled</name>
    <value>false</value>
</property>


yarn resource manager HA to ZK https://www.ibm.com/support/knowledgecenter/en/SSGSMK_7.1.0/management_sym/configuring_yarn_rm_ha_zookeeper.html
Modify the YARN configuration file ($HADOOP_CONF_DIR/yarn-site.xml) on all resource manager hosts and the node manager hosts. Do this by adding Apache ZooKeeper HA properties to the file. For example, the following is based on open-source YARN configuration:

<property>
	<name>yarn.resourcemanager.ha.enabled</name>
	<value>true</value>
</property>
<property>
	<name>yarn.resourcemanager.zk-address</name>
	<value>zk1:port1,zk2:port2,..., zkN:portN</value>
</property>
<property>
	<name>yarn.resourcemanager.recovery.enabled</name>
	<value>true</value>
</property>
<property>
	<name>yarn.resourcemanager.ha.automatic-failover.enabled</name>
	<value>true</value>
</property>