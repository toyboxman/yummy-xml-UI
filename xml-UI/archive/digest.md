Qualifier(限定符号)
Qualifier is an extra name given to either variables or functions , showing an extra quality or extra meaning for that variable or function. like Dr in Dr Arun Kumar

Qualifiers for variables are (TYPE qualifiers): signed, unsigned, long, short, long long, const, volatile, static, auto, extern, register

Qualifiers for functions are: static, extern, inline

Modifier(修饰符号)

org.springframework.boot.autoconfigure.SpringBootApplication
Indicates a configuration class that declares one or more @Bean methods 
and also triggers auto-configuration and component scanning. This is a convenience 
annotation that is equivalent to declaring @Configuration, @EnableAutoConfiguration and @ComponentScan.

org.springframework.context.annotation.Bean
Indicates that a method produces a bean to be managed by the Spring container(方法产生的bean对象由容器托管)
@Bean({"b1", "b2"}) // bean available as 'b1' and 'b2', but not 'myBean'
       public MyBean myBean() {
           // instantiate and configure MyBean obj
           return obj;
       }

Scope, DependsOn, Primary, and Lazy
Note that the @Bean annotation does not provide attributes for scope, depends-on, primary, or lazy. Rather, it should be used in conjunction with @Scope, @DependsOn, @Primary, and @Lazy annotations to achieve those semantics. For example:
       @Bean
       @Scope("prototype")
       public MyBean myBean() {
           // instantiate and configure MyBean obj
           return obj;
       }

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


org.springframework.scheduling.annotation.EnableScheduling
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

@Configuration
Indicates that a class declares one or more @Bean methods and may be processed by the Spring container to generate bean definitions and service requests for those beans at runtime, for example:
   @Configuration
   public class AppConfig {
  
       @Bean
       public MyBean myBean() {
           // instantiate, configure and return bean ...
       }
   }
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
