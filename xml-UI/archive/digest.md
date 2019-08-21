+ ***Linux***
    - [commands](Linux%20commands.md)
    - [git](git%20commands.md)
    - [FFmpeg命令编辑视频](https://mp.weixin.qq.com/s?__biz=MjM5NjQ4MjYwMQ==&mid=2664614334&idx=2&sn=22c884dc2553b16f7f5cf808fac3a2ce)
    - 进程间通信-[**1**](https://mp.weixin.qq.com/s?__biz=MjM5NjQ4MjYwMQ==&mid=2664614113&idx=1&sn=67fe16f7ca0bec0c16dcb429d428ff25), [**2**](https://mp.weixin.qq.com/s?__biz=MjM5NjQ4MjYwMQ==&mid=2664614153&idx=2&sn=089a3a6b8728c581cc2800cd8ded946c), [**3**](https://mp.weixin.qq.com/s?__biz=MjM5NjQ4MjYwMQ==&mid=2664614334&idx=1&sn=71862506d387949c0f78b54ab026ecc7)
    - Ansible管理工作站配置[**1**](https://mp.weixin.qq.com/s?__biz=MjM5NjQ4MjYwMQ==&mid=2664613143&idx=1&sn=8fbd47dcf411ce26c80ffa873304d7c1), [**2**](https://mp.weixin.qq.com/s?__biz=MjM5NjQ4MjYwMQ==&mid=2664613180&idx=1&sn=b57f1b66f3ded400f029f4f8b3b8f4bc), [**3**](https://mp.weixin.qq.com/s?__biz=MjM5NjQ4MjYwMQ==&mid=2664614674&idx=2&sn=23619c078386431b6810107e7246e8b2)

+ ***Programming Languages***
    - [***Github***](#github)
    - [***Java***](#java)
    - ***Scala***
        - [basic](scala/scala-basic.md)
        - [collections](scala/scala-collections.md)
        - [head](scala/scala-head.md)
    - ***Python***
        - [basic](python/python-basic.md)
    - [***Interview***](#interview)

+ [***System Monitor***](system-monitor/monitor.md)
    - [NetData性能监控](https://mp.weixin.qq.com/s?__biz=MjM5NjQ4MjYwMQ==&mid=2664614807&idx=2&sn=6eeb1cc8c0b93a3de2b6428891a74f11)

+ [***Database***](#database)
+ [***Docker***](#docker)
+ [***Kubernetes***](#kubernetes)
+ [***ElasticSearch***](#elasticsearch)

+ ***blockchain***
    - [以太坊](https://mp.weixin.qq.com/s?__biz=MjM5NjQ4MjYwMQ==&mid=2664614225&idx=2&sn=199e802d5c7f779f0b99d7c20f9921d7)   

+ [***notepad++***](#notepad)

+ ***http***
    - [Cookie vs Session](https://www.xiamensmpingguoshouhou.cn/weixin/22948)  
    - [http guide](https://developer.mozilla.org/en-US/docs/Web/HTTP)  

+ ***bench test***
    - [Geekbench测试不同公有云服务](https://www.ithome.com/0/431/894.htm)    

+ ***microservice***
    - [微服务的经验教训](https://mp.weixin.qq.com/s?__biz=MjM5MDE0Mjc4MA==&mid=2651016151&idx=2&sn=caa40d813b176a8325d61ca0c1040d19)    
    - [微服务配置中心](https://nobodyiam.com/2018/07/29/configuration-center-makes-microservices-smart/)   
    - [Spring Cloud配置中心](https://windmt.com/2018/04/19/spring-cloud-7-config-sample/)

+ ***algorithm***
    - [PBFT-SBFT](https://ittaiab.github.io/2019-06-23-what-is-the-difference-between/)

+ ***blog***    
    - [jayway](https://blog.jayway.com/)      
    -[资源搜索网站](https://mp.weixin.qq.com/s?__biz=MzA5NDIzNzY1OQ==&mid=2735614209&idx=1&sn=c3fc396ffe327225047c895ce360149a)

#### database
+ [mysql](db/mySQL.md)
    - [实现MySQL每秒 57万的写入](https://mp.weixin.qq.com/s?__biz=MjM5MDAxOTk2MQ==&mid=2650281971&idx=1&sn=08bd75dd606f06ef1d67409d13d23a47)
    - [MySQL 8 vs PostgreSQL 10](https://mp.weixin.qq.com/s?__biz=MjM5MDAxOTk2MQ==&mid=2650281707&idx=1&sn=52fdb59331e5decd81f48d5099a11436)
+ [postgres](db/PostgreSQL.md)
+ [oracle](db/oracle.md)
+ [2019上半年合集](https://mp.weixin.qq.com/s?__biz=MjM5MDAxOTk2MQ==&mid=2650281866&idx=1&sn=489001936b4855c88bf7b32e738ebc9d)    

#### docker
+ [docker](docker%20commands.md)
    - [Docker架构原理、功能及使用](https://mp.weixin.qq.com/s?__biz=MjM5MDAxOTk2MQ==&mid=2650282275&idx=2&sn=d0b5c3525fc756b101c2d32b04c7150d)

#### github
github提供检索功能[https://github.com/search](https://github.com/search), [检索语法](https://help.github.com/en/articles/about-searching-on-github),   [code search语法](https://help.github.com/en/articles/searching-code)
```
# 从java语言的项目(language:java)中
# 搜寻扩展名是java(extension:java)的文件内容(in:file)
# 匹配关键字process
process in:file language:java extension:java
```
由于github不支持正则表达搜索,因此可以用google做一些搜索
```
"key" site::https://github.com
```
还有[https://searchcode.com/](https://searchcode.com/)可以提供code search服务，不过以上都不完美无法达到grep效果

#### java
- [maven](java/mvn.md)
    - [basic](https://github.com/eugenp/tutorials/tree/master/maven)
- 变量搜索-[**codelf**](https://unbug.github.io/codelf/),  [介绍](https://zhuanlan.zhihu.com/p/53360901)
- [protobuf](#protobuf)
- [spring](#spring)
- snippet
    - [JDK command](java/JDK.md)
    - [tcp/udp](java/java-udp-tcp.md)
    - [stream2string](java/java-stream2string.md)
    - [reflection](java/java-reflection.md)
    - [log](java/java-log.md)
    - [json](java/java-json.md)
    - [decompile](java/java-decompile.md)
    - [convert](java/java-convert.md)
    - [annotation](java/java-annotation.md)
    - [zk-election](java/ZK-leader_election.md)
    - [JPDA](java/JDI_JDWP_JPDA.md)
- [JavaGuide](https://github.com/Snailclimb/JavaGuide/)

#### notepad++
需要在curl操作payload的json每一行末尾增加一个'\', 且字符后不能有空格，否则bash解析会失败。
```
curl -k -H "Content-Type: application/json" -X POST http://127.0.0.1:8080/api/v1/measures
{
    "name":"accuracy_measure",
    "measure.type":"griffin",
    "dq.type":"accuracy",
    "process.type":"batch",
    "owner":"test",
    "description":"measure description"
}
```
设定搜索模式'Extended' 然后Replace All : '\r\n'  --> '  \\\r\n'
```
{  \
    "name":"accuracy_measure",  \
    "measure.type":"griffin",  \
    "dq.type":"accuracy",  \
    "process.type":"batch",  \
    "owner":"test",  \
    "description":"measure description"  \
}
```

#### interview
+ [Q&A-1](https://www.journaldev.com/2366/core-java-interview-questions-and-answers)
+ [Q&A-spring](https://quizlet.com/306762411/baeldung-top-spring-framework-interview-questions-spring-core-flash-cards/)

#### protobuf
protobuf rpc service定义编译后缺失，原因是默认选项是留给实现自己完成custom code generator，而不是选择"generic"产生services模板.
因此，需要在.proto定义文件头加上`option java_generic_services = true;`

```
#!/bin/bash

for file in `find src/main/proto -name "*.proto"`; do
    protoc --proto_path=src/main/proto --java_out=src/main/java/ $file
done
```

#### spring
- [spring boot tutorials](https://www.tutorialspoint.com/spring_boot/spring_boot_quick_start.htm)
    - [properties injection](java/spring.md#bu1)
    - [application entry](java/spring.md#bu2)
        - [Bootstrap Web Application ](https://www.baeldung.com/bootstraping-a-web-application-with-spring-and-java-based-configuration)
        - [Shutdown Boot Application](https://www.baeldung.com/spring-boot-shutdown)
        - [Run Boot Application](https://docs.spring.io/spring-boot/docs/current/reference/html/using-boot-running-your-application.html)
    - [best practices](java/spring.md#bubp)
- [spring](java/spring.md)
    - [tutorials](https://github.com/eugenp/tutorials)
        - [spring core-1](https://github.com/eugenp/tutorials/tree/master/spring-core)
            - [ClassPathXmlApplicationContext](https://www.baeldung.com/spring-classpathxmlapplicationcontext)
            - [Web Contexts](https://www.baeldung.com/spring-web-contexts)
        - [spring core-2](https://github.com/eugenp/tutorials/tree/master/spring-all)
        - [spring mvc](https://github.com/eugenp/tutorials/tree/master/spring-mvc-java)
            - [concept of Controller ](https://www.baeldung.com/spring-controllers)
            - [Controller and RestController](https://www.baeldung.com/spring-controller-vs-restcontroller)
            - [REST with Spring Tutorial](https://www.baeldung.com/rest-with-spring-series)
        - [bootstrap](java/spring.md#u0)
        - [properties injection](java/spring.md#u1)
        - [bean creation](java/spring.md#u2)
            - [bean creation order](java/spring.md#u2s1)
            - [bean injection](java/spring.md#u2s2)
            - [bean creation stages](java/spring.md#u2s3)
            - [bean schedule](java/spring.md#u2s4)
            - [Get All Spring-Managed Beans](https://www.baeldung.com/spring-show-all-beans)
            - [Scheduling with Quartz](https://www.baeldung.com/spring-quartz-schedule)
        - [AOP](java/spring.md#u3)
            - [aspect bean injection](java/spring.md#u3s1)
        - [JPA](java/spring.md#u4)
            - [Repository](java/spring.md#u4s1)
            - [Spring Data Guides](https://www.baeldung.com/spring-data)
            - [JPA Query](https://www.baeldung.com/spring-data-query-by-example)
        - [spring junit](https://www.baeldung.com/spring-boot-testing)
    - [spring exceptions](https://www.baeldung.com/spring-exceptions)
        - [NoSuchBeanDefinitionException](https://www.baeldung.com/spring-nosuchbeandefinitionexception)
        - [NoUniqueBeanDefinitionException](java/spring.md#t1)
    - [spring doc center](https://docs.spring.io/spring/docs/)
        - [spring framework 5.0](https://docs.spring.io/spring/docs/5.0.x/spring-framework-reference/index.html)
        - [pdf/excel/html report integration](https://www.baeldung.com/spring-jasper)

#### kubernetes
- [Kubernetes 学习曲线](https://mp.weixin.qq.com/s?__biz=MjM5NjQ4MjYwMQ==&mid=2664614519&idx=2&sn=924123937683f49e79fca00c71a4463c)
    - [配置 Kubernetes](https://mp.weixin.qq.com/s?__biz=MjM5NjQ4MjYwMQ==&mid=2664614644&idx=3&sn=8176ed98194bf765a5e5ed1cdfbd503b)
    - [k8s配置工具](https://mp.weixin.qq.com/s?__biz=MjM5NjQ4MjYwMQ==&mid=2664614666&idx=1&sn=9259273f43b7ca54c00b500988ba4359)
    - [Azure Kubernetes Service](https://docs.microsoft.com/en-us/azure/aks/concepts-clusters-workloads)
```
root@ncpmaster:/home/pksadmin# kubectl describe pod coredns-fb8b8dccf-q78fl -n kube-system
kubectl get ds -n kube-system
```

#### digest
德国诗人海涅说，不要轻视观念的影响力，教授在沉静的研究中所培育出来的哲学概念可能颠覆一个文明。“海涅这句话是形容康德的，但是我认为用来形容马克思更为合适。马克思的墓前刻了两行字：哲学家们只是用不同的方式解释世界,而问题在于改变世界。但是大家有没有想过，马克思是通过什么方式去改变世界的？他是通过解释世界去改变世界的。”

冯友兰说，“哲学，特别是形而上学，对我们增进对事实的知识并无用处，但是它为我们提高我们的心智，则是必不可少的。”冯友兰认为，人和其他动物的不同之处在于，动物是没有自知力和反思力，而人对于自己的行为有一种觉解，这种觉解对他正在做的事情产生了意义，这些意义最终形成的整体，构成他的人生境界。

西方的斯多葛学派与冯友兰的观点类似，在他们看来，哲学可以带来对于人生的慰藉。马克·奥勒留说：“人所执着的是什么呢？啊，除哲学别无他物。唯哲学可以保持我心中的神性，使我们免受伤害与屈辱，使我们超然于苦乐之上。使我们不致装聋卖傻或矫情掩饰，使我们无需仰人鼻息，使人驱使。何止于此，哲学使我们的心灵虽遭逆顺而安之若素。”

庄子说的“知其已知者”在一个很基本的意义上，哲学就是在干这个事。维特根斯坦也说过几乎和庄子一模一样的话：科学是在不断地了解未知的世界，而哲学一直是在收集回忆。

什么是追求独异？吃饭不只求饱，而要吃得“正确”“健康”；不参加“大众旅游”，而要最“纯正”的“旅行”；工作不应只为稻粱谋，而要意义和乐趣。“成功地实现自我”，这是晚现代文化的生活方式导向，也是新中产追求的目标。然而事实上，基于此的个人幸福却显得虚无缥缈——一方面这取决于变化无常的个人体验，另一方面取决于难以捉摸的文化市场。

在《论法的精神》中，孟德斯鸠宣称权力显示出一种自我膨胀并滥用自身特权的自然倾向，它不会停止，直到在它的外部找到一种限制。这一教训在任何时间和任何地点都适用。如今，不停膨胀并不断自主化的权力是经济权力、金融权力。

《韩非子》说赛马的妙法，在于“不为最先，不耻最后”

[堯山堂外紀](https://zh.wikisource.org/wiki/%E5%A0%AF%E5%B1%B1%E5%A0%82%E5%A4%96%E7%B4%80/%E5%8D%B7010#%E5%8F%B8%E9%A9%AC%E6%87%BF)
[古诗十九首](https://zh.wikisource.org/wiki/%E5%8F%A4%E8%A9%A9%E5%8D%81%E4%B9%9D%E9%A6%96)

#### GraphQL
[工具和库 ](https://mp.weixin.qq.com/s?__biz=MjM5MDE0Mjc4MA==&mid=2651017395&idx=3&sn=ab0b3c87c20d4cdaad82321764195210)

#### NPM
[Web开发包注册中心的NPM](https://mp.weixin.qq.com/s?__biz=MjM5MDE0Mjc4MA==&mid=2651016822&idx=2&sn=d38c1f59ebd04052d95f2136fb950d5c)

#### go

#### ElasticSearch
+ [简单教程](https://mp.weixin.qq.com/s?__biz=MjM5MDAxOTk2MQ==&mid=2650282222&idx=1&sn=a937009d8f0a66aaa327cd6f83955ee5)
+ [安装 Elasticsearch 和 Kibana](https://mp.weixin.qq.com/s?__biz=MjM5NjQ4MjYwMQ==&mid=2664614900&idx=3&sn=66d57f1ecd5d4de53d458da11babbc38)
+ [日均5亿查询量的京东订单中心，舍MySQL用ElasticSearch](https://mp.weixin.qq.com/s?__biz=MjM5MDAxOTk2MQ==&mid=2650282197&idx=1&sn=5067069706e0e56601436609451d2aa7)

#### hive

#### hadoop
  
#### superset
https://superset.incubator.apache.org/

#### Druid
https://en.wikipedia.org/wiki/Druid_(open-source_data_store)
Apache Druid (incubating) is a high performance analytics data store for event-driven data.

#### Flink
https://en.wikipedia.org/wiki/Apache_Flink

#### junit/mock
@InjectMocks/@Mock
code sample:
[1](https://github.com/apache/incubator-griffin/blob/master/service/src/test/java/org/apache/griffin/core/job/JobControllerTest.java#L62)

#### Ratpack
https://github.com/ratpack/ratpack
Ratpack is a simple, capable, toolkit for creating high performance web applications.
Ratpack is built on Java and the Netty event-driven networking engine. The API is optimized for Groovy and Java 8.

#### griffin

https://hadoop.apache.org/docs/r2.9.1/hadoop-project-dist/hadoop-common/SingleCluster.html#Execution

https://cwiki.apache.org/confluence/display/Hive/Hive+Schema+Tool
Run schemaTool to create the initial DB structure https://mapr.com/docs/61/Hive/Config-RemotePostgreSQLForHiveMetastore.html
172  hive/bin/schematool -dbType postgres -initSchema
173  hive/bin/schematool -dbType postgres -initSchema -dryRun
Get schema information:
/apache/hive/bin/schematool -dbType postgres -info

yarn resource manager HA to ZK https://www.ibm.com/support/knowledgecenter/en/SSGSMK_7.1.0/management_sym/configuring_yarn_rm_ha_zookeeper.html
Modify the YARN configuration file ($HADOOP_CONF_DIR/yarn-site.xml) on all resource manager hosts and the node manager hosts. Do this by adding Apache ZooKeeper HA properties to the file. For example, the following is based on open-source YARN configuration:
