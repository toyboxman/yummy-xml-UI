***

## Ways to use ServiceLoader/ClassLoader

### ServiceLoader
java.util.ServiceLoader.load(Class<S> service) method为指定service type创建new service loader , 使用当前线程的context class loader. 遇到过如下service加载失败问题，opentracing服务的jaegertracing实现已经被classloader加载了，但是ServiceLoader中却找不到

错误原因在于打包时候jar中缺失注册信息。为了注册一个service, 需要在src目录中增加META-INF/service目录. 同时在META-INF/service目录中创建一个实现spi-interface同名的text file, 此文件只包含一行文字列出spi-service实现的具体class name 比如服务接口名`io.opentracing.contrib.tracerresolver.TracerFactory`,  text file就是`META-INF/service/io.opentracing.contrib.tracerresolver.TracerFactory` , 文件内容就是具体实现class的名称`io.jaegertracing.tracerresolver.internal.JaegerTracerFactory`
```java 
Class<?> aClass = Class.forName("io.jaegertracing.spi.SenderFactory");
Class<?> bClass = Class.forName("io.jaegertracing.thrift.internal.senders.ThriftSenderFactory");
logger.info("#xtrace# SenderFactory loader {}", aClass.getClassLoader());
logger.info("#xtrace# ThriftSenderFactory loader {}", bClass.getClassLoader());
logger.info("#xtrace# SenderFactory.class.getClassLoader() {}", SenderFactory.class.getClassLoader());
ServiceLoader<SenderFactory> senderFactoryServiceLoader = ServiceLoader.load(SenderFactory.class, SenderFactory.class.getClassLoader());
logger.info("#xtrace# ServiceLoader<SenderFactory> {}", senderFactoryServiceLoader.iterator().hasNext() );
senderFactoryServiceLoader.iterator().forEachRemaining(f ->{logger.info("#xtrace# ServiceLoader<SenderFactory> {}", f.toString());});
``` 
执行结果
```
#xtrace# SenderFactory loader sun.misc.launcher$appclassloader@18b4aac2
#xtrace# ThriftSenderFactory loader sun.misc.launcher$appclassloader@18b4aac2
#xtrace# SenderFactory.class.getClassLoader() sun.misc.launcher$appclassloader@18b4aac2
#xtrace# ServiceLoader<SenderFactory> false
```
参考example   
+ [load_service](https://www.tutorialspoint.com/java/util/serviceloader_load_service.htm)

### ClassLoader
+ [Understanding Java class loading](https://blogs.oracle.com/sundararajan/understanding-java-class-loading)