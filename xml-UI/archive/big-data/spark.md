### Spark
[***Apache Spark***](https://en.wikipedia.org/wiki/Apache_Spark)是通用分布式集群计算框架，提供一个隐含数据并行和容错的接口，来对整个集群编程。Spark架构基于resilient distributed dataset (RDD), 一种只读的分布在多个集群机器上的数据多重集合，这些集合通过fault-tolerant机制维护。Dataframe API是RDD层之上的抽象接口, Dataset API遵从这些接口。Spark 1.x版本中RDD是主要的编程接口, 但Spark 2.x之后推荐使用Dataset API，但RDD技术仍旧是其底层依赖。

#### 架构
![Image of Stack](https://d1.awsstatic.com/Data%20Lake/what-is-apache-spark.b3a3099296936df595d9a7d3610f1a77ff0749df.PNG)

SPARK核心分成四大块功能，分别对应机器学习，实时分析，非结构化数据查询和图处理。
+ Spark core – Foundation for data processing
+ Spark SQL – Based on Shark and helps in data extracting, loading and transformation
+ Spark streaming – Light API helps in batch processing and streaming of data
+ Machine learning library – Helps in machine learning algorithm implementation.
+ Graph Analytics(GraphX) – Helps in representing Resilient Distributed Graph
+ Spark Cassandra Connector
+ Spark R integration

![Image of Stack1](https://i2.wp.com/www.jenunderwood.com/wp-content/uploads/2016/10/SparkArchitecture-Databrickss.gif?ssl=1)

SPARK集群可以对接各种数据源，如传统数据库，非结构化文本数据，消息队列等。集群可以通过云，容器编排系统，Hadoop集群来调度。Spark处理引擎与Hadoop data相适配，能处理data in HDFS, HBase, Cassandra, Hive, and any Hadoop InputFormat. 处理引擎可以做batch processing (similar to MapReduce)和streaming, interactive queries, and machine learning. 

相比Spark架构，Hadoop作为大数据处理架构最大不足是MapReduce 只是一个native batch processing engine，而且Hadoop处理速度没有Spark快。除此之外，如今大数据处理要求兼具batch processing和real-time processing. 而Hadoop’s MapReduce仅能处理batch data，并且无法满足大数据处理low latency要求. 

因而，将Spark跑在Hadoop之上，利用hybrid framework和resilient distributed dataset (RDD), 当运行Spark时候数据能存储于内存中来加速处理. 没有Spark的能力，Hadoop无法完成Real-time and faster data processing. 没有Hadoop的能力，Spark也无法使用其分布式文件系统来保存multi-petabytes的data. Spark本身是一个cluster computing system而不是data storage system，因此它需要通过外部数据源来读写data. 如果不需要使用HDFS上文件，数据源可以是local file system，也可以是no SQL database like Apache Cassandra or HBase or Amazon’s S3. 这种场景下运行Spark without Hadoop.

#### 部署
![Image of Deploy](https://azurecomcdn.azureedge.net/mediahandler/acomblog/media/Default/blog/97bc4145-21de-47f4-b1ef-12bd4635c47a.png)

SPARK集群主要包括master节点和很多worker节点。

三种方式来部署运行Spark in Hadoop cluster: Standalone, Over YARN, In MapReduce (SIMR)

+ Standalone Deployment

极简方式，resources静态分配到Hadoop cluster所有nodes或者subsets of nodes. 并行运行Spark与MapReduce，Spark管理自己cluster. 这是Hadoop 1.x推荐部署方式

+ Over YARN Deployment

这是Hadoop and Spark集成的简单方法，不需要pre-installation或admin配置. YARN是唯一安全的cluster manager，在大Hadoop cluster产品环境下是个好的部署选择

+ Spark In MapReduce (SIMR)

这种方式下不需要YARN，Spark jobs可以在MapReduce中发起.

#### 运行
![Image of Run1](https://sigmoid.com/wp-content/uploads/2015/03/Apache_Spark1.png)

![Image of Run3](https://sdtimes.com/wp-content/uploads/2018/03/image7.png)

Amazon Elastic MapReduce (EMR)是Amazon Web Services (AWS)大数据处理工具, 基于Apache Hadoop技术之上
![Image of Run4](https://d1.awsstatic.com/Products/product-name/diagrams/product-page-diagram_Amazon-Kinesis-Data-Streams.074de94302fd60948e1ad070e425eeda73d350e7.png)

#### 使用
- **setup Spark**

下载[Spark](http://spark.apache.org/downloads.html)，解压到设定目录。如果安装Pseudo Distributed/Single Node Cluster,可以参考[help](http://why-not-learn-something.blogspot.com/2015/06/spark-installation-pseudo.html).

参考如下配置项 [$SPARK_HOME/conf/spark-default.conf](https://jaceklaskowski.gitbooks.io/mastering-apache-spark/spark-SparkConf.html)

+ [spark.master](https://spark.apache.org/docs/latest/submitting-applications.html#master-urls)
+ [Spark Configuration](http://spark.apache.org/docs/latest/configuration.html)

```console
spark.master                    yarn
spark.serializer                org.apache.spark.serializer.KryoSerializer
spark.yarn.jars                 hdfs:///home/spark_lib/*
spark.yarn.dist.files		hdfs:///home/spark_conf/hive-site.xml
spark.sql.broadcastTimeout  500
```
$SPARK_HOME/conf/spark-env.sh
```console
HADOOP_CONF_DIR=$HADOOP_HOME/etc/hadoop
SPARK_MASTER_HOST=localhost
SPARK_MASTER_PORT=7077
SPARK_MASTER_WEBUI_PORT=8082
SPARK_LOCAL_IP=localhost
SPARK_PID_DIR=/apache/pids
```
Upload some files otherwise you will hit `Error: Could not find or load main class org.apache.spark.deploy.yarn.ApplicationMaster`, when you schedule spark applications.
```console
hdfs dfs -mkdir /home/spark_lib
hdfs dfs -mkdir /home/spark_conf
hdfs dfs -put $SPARK_HOME/jars/*  hdfs:///home/spark_lib/
hdfs dfs -put $HIVE_HOME/conf/hive-site.xml hdfs:///home/spark_conf/
```
- **start/stop spark nodes**
```console
cp /apache/hive/conf/hive-site.xml /apache/spark/conf/
# start master and slave nodes
/apache/spark/sbin/start-master.sh
/apache/spark/sbin/start-slave.sh  spark://localhost:7077

# stop master and slave nodes
/apache/spark/sbin/stop-slaves.sh 
/apache/spark/sbin/stop-master.sh 

# stop all
/apache/spark/sbin/stop-all.sh
```

- **Spark shell**

使用命令之前需要确认[Scala](https://downloads.lightbend.com/scala/2.12.8/scala-2.12.8.tgz)已经在本地安装好，官网[link](https://www.scala-lang.org/)。

导出路径到 .bashrc
```console
export SPARK_HOME=/apache/spark
export SCALA_HOME=/apache/scala

export PATH=$PATH:$SPARK_HOME/bin:$SCALA_HOME/bin
```
进入spark shell交互界面。
```console
$ spark-shell
scala> :help
scala> :quit

# 如果希望不用一行行在shell中敲命令，也可以把命令写入一个文件启动shell时preload
# shell会按照逐行解释方式来处理文件
$ spark-shell -I <FilePath>
```
Create an RDD through Parallelized Collection, more refer to [commands](https://data-flair.training/blogs/scala-spark-shell-commands/)

本地使用spark-shell时候需要先把Hadoop和Yarn的服务启动，[参考使用](https://github.com/apache/griffin/blob/master/griffin-doc/deploy/deploy-guide.md#hadoop)
```console
// 默认一个jvm中就有一个context运行，可以直接使用
scala> sc
res0: org.apache.spark.SparkContext = org.apache.spark.SparkContext@4b3b2a4f

// 如果希望创建一个新的context，则执行下面指令
scala> import org.apache.spark.{SparkConf,SparkContext}
// Create conf object
scala> val conf = new SparkConf().setAppName("Count")
// 默认一个jvm中只允许一个context运行
//scala> conf.get("spark.driver.allowMultipleContexts")
//res0: String = false
//scala> conf.set("spark.driver.allowMultipleContexts","true")
//create spark context object
scala> val sc = new SparkContext(conf)
scala> sc
res0: org.apache.spark.SparkContext = org.apache.spark.SparkContext@bc4a9b0

scala> val no = Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
no: Array[Int] = Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
// 默认partition成2 parts,  sc.parallelize(no, 3)会partition成3 parts
scala> val noData = sc.parallelize(no)
noData: org.apache.spark.rdd.RDD[Int] = ParallelCollectionRDD[0] at parallelize at <console>:27

// 将结果写入文件系统，后面发现'nodata.txt'是Hadoop的目录名
scala> noData.saveAsTextFile("nodata.txt")
scala> sc.stop
```
通过Hadoop的命令来检查写入的文件
```console
$ hdfs dfs -find / nodata*
nodata.txt
nodata.txt/_SUCCESS
nodata.txt/part-00000
nodata.txt/part-00001

// 'nodata.txt'是目录不是文件
$ hdfs dfs -cat nodata.txt
cat: `nodata.txt': Is a directory

// 空文件
$ hdfs dfs -cat nodata.txt/_SUCCESS

$ hdfs dfs -cat nodata.txt/part-00000
1
2
3
4
5
$ hdfs dfs -cat nodata.txt/part-00001
6
7
8
9
10
```
如果spark使用本地文件系统，则会把结果写入本地文件目录
```console
scala> noData.saveAsTextFile("nodata")

bash-5.0# ls nodata/
._SUCCESS.crc    .part-00002.crc  part-00000       part-00003
.part-00000.crc  .part-00003.crc  part-00001       
.part-00001.crc  _SUCCESS         part-00002       
bash-5.0# cat nodata/part-00000
1
2
bash-5.0# cat nodata/part-00001
3
4
5
bash-5.0# cat nodata/part-00002
6
7
bash-5.0# cat nodata/part-00003
8
9
10
```
每一个SparkContext有一个web UI, 默认访问地址 http://driver-node:4040, 用来显示application信息，包括scheduler stages/tasks/RDD sizes/memory usage/Environmental/running executors  [*spark application monitoring rest-api*](https://spark.apache.org/docs/latest/monitoring.html#rest-api)

如果多个SparkContexts运行在同一台host上, web服务会依次往后绑定端口4040 (4041, 4042, etc). 如果直接访问spark web服务失败，还可以通过Hadoop代理来访问 http://127.0.0.1:8088/proxy/application_1566972520413_0001/stages/


- **Submit a Scala job to Spark**
```console
# submit python task
/opt/spark/bin/spark-submit --master yarn-client test.py

# submit scala task
/opt/spark/bin/spark-submit --master yarn-client --class "HelloWorld" path_to.jar
# scala source
object HelloWorld {
    def main(args: Array[String]): Unit = {
        println("Hello, world!")
    }
}
```
关于spark任务的排错调优，可以参考[databricks-spark-knowledge-base](https://databricks.gitbooks.io/databricks-spark-knowledge-base/)

- **Spark Runs with Docker** 

快速使用spark可以通过[docker container方式](https://github.com/big-data-europe/docker-spark)，避免安装配置
```console
# 下载image
docker pull bde2020/spark-master
docker pull bde2020/spark-worker
docker pull bde2020/spark-base
# 运行container实例
docker run --name spark-master -h spark-master -p6066:6066 -p7077:7077 -p8080:8080 -e ENABLE_INIT_DAEMON=false -d bde2020/spark-master:latest
docker run --name spark-worker-1 --link spark-master:spark-master -p8081:8081 -e ENABLE_INIT_DAEMON=false -d bde2020/spark-worker:latest

# 假设docker宿主机IP为 10.184.108.18，可以通过以下URL访问master和worker
# master node URL
http://10.184.108.18:8080/
# worker node URL http://172.17.0.3:8081/ 映射为宿主机 URL
http://10.184.108.18:8081/

# 登录spark master节点
docker exec -it spark-master bash
# 登录spark worker-1节点
docker exec -it spark-worker-1 bash

# 进入spark命令交互模式
bash-5.0# spark/bin/spark-shell
# 退出命令交互模式
scala> :quit

# spark-submit 命令使用说明
Usage: spark-submit [options] <app jar | python file | R file> [app arguments]
Usage: spark-submit --kill [submission ID] --master [spark://...]
Usage: spark-submit --status [submission ID] --master [spark://...]
Usage: spark-submit run-example [options] example-class [example args]
Options:
  --master MASTER_URL         spark://host:port, mesos://host:port, yarn,
                              k8s://https://host:port, or local (Default: local[*]).
  --deploy-mode DEPLOY_MODE   Whether to launch the driver program locally ("client") or
                              on one of the worker machines inside the cluster ("cluster")
                              (Default: client).

# 提交python PI计算的任务1000次到local计算
spark/bin/spark-submit \
--master local \
spark/examples/src/main/python/pi.py 1000
# 计算结果显示     
20/04/12 14:14:02 INFO DAGScheduler: Job 0 finished: reduce at /spark/examples/src/main/python/pi.py:44, took 141.028314 s
Pi is roughly 3.141309

# 提交python PI计算的任务200次到spark cluster计算
# 这个和上面local区别在提交任务后可以通过 master/worker node URL(http://<宿主机>:8080/) 查看job执行信息如日志等
spark/bin/spark-submit \
--master spark://10.184.108.18:7077 \
spark/examples/src/main/python/pi.py 200 
# 计算结果显示，可以看到 worker node(172.17.0.3)第200次执行时长158 ms
20/04/12 14:30:18 INFO TaskSetManager: Finished task 199.0 in stage 0.0 (TID 199) in 158 ms on 172.17.0.3 (executor 0) (200/200)
20/04/12 14:30:18 INFO DAGScheduler: Job 0 finished: reduce at /spark/examples/src/main/python/pi.py:44, took 10.733714 s
Pi is roughly 3.141113

# examples目录中还提供其他例子,包括java/Scala/R语言版本
bash-5.0# ls spark/examples/src/main/python/
als.py                  logistic_regression.py  pagerank.py             sort.py                 streaming/
avro_inputformat.py     ml/                     parquet_inputformat.py  sql/                    transitive_closure.py
kmeans.py               mllib/                  pi.py                   status_api_demo.py      wordcount.py

# 提交scala/java PI计算的任务到spark cluster计算
# 如果增加spark.eventLog配置，需要先 mkdir /tmp/spark-events,事件会记录spark的一些配置信息
spark/bin/spark-submit \
--class org.apache.spark.examples.SparkPi \
--master spark://10.184.108.18:7077 \
--conf spark.eventLog.enabled=true \
spark/examples/jars/spark-examples_2.11-2.4.5.jar 100
# 计算结果显示
20/04/13 11:52:23 INFO TaskSetManager: Finished task 99.0 in stage 0.0 (TID 99) in 56 ms on 172.17.0.3 (executor 0) (100/100)
20/04/13 11:52:23 INFO DAGScheduler: Job 0 finished: reduce at SparkPi.scala:38, took 4.118978 s
Pi is roughly 3.1415535141553512

# 进入spark-sql命令交互模式
bash-5.0# spark/bin/spark-sql
spark-sql> :help
         > ;
Error in query: 
mismatched input ':' expecting {'(', 'SELECT', 'FROM', 'ADD', 'DESC', 'WITH', 'VALUES', 'CREATE', 'TABLE', 'INSERT', 'DELETE', 'DESCRIBE', 'EXPLAIN', 'SHOW', 'USE', 'DROP', 'ALTER', 'MAP', 'SET', 'RESET', 'START', 'COMMIT', 'ROLLBACK', 'REDUCE', 'REFRESH', 'CLEAR', 'CACHE', 'UNCACHE', 'DFS', 'TRUNCATE', 'ANALYZE', 'LIST', 'REVOKE', 'GRANT', 'LOCK', 'UNLOCK', 'MSCK', 'EXPORT', 'IMPORT', 'LOAD'}(line 1, pos 0)

== SQL ==
:help
# 退出命令交互模式
spark-sql> quit;

```
**spark-examples**
- [A broadcast variable](https://spark.apache.org/docs/2.4.5/api/java/org/apache/spark/broadcast/Broadcast.html) - sc.broadcast在所有节点上广播只读的共享变量而不必随job传递变量拷贝到每台机器上，例如有效地给每个节点输入超大dataset，Spark会通过有效算法减小通讯成本.
```console
# BroadcastTest [partitions] [numElem] [blockSize]
spark/bin/spark-submit \
--class org.apache.spark.examples.BroadcastTest \
--master spark://10.184.108.18:7077 \
spark/examples/jars/spark-examples_2.11-2.4.5.jar 100 500
# 运行结果
20/04/13 14:46:14 INFO DAGScheduler: Job 2 finished: collect at BroadcastTest.scala:51, took 0.570157 s
500
500
500
500
500
500
500
500
500
500
Iteration 2 took 586 milliseconds
```
- 测试任务中异常处理
```console
spark/bin/spark-submit \
--class org.apache.spark.examples.ExceptionHandlingTest \
--master spark://10.184.108.18:7077 \
spark/examples/jars/spark-examples_2.11-2.4.5.jar
```
- 测试随机产生数归组统计 - [parallelize](https://spark.apache.org/docs/2.4.5/api/scala/index.html#org.apache.spark.SparkContext)
Distribute a local Scala collection to form an RDD.
    - [spark by examples](https://sparkbyexamples.com/apache-spark-rdd/how-to-create-an-rdd-using-parallelize/)
    - [map vs. flatMap and reduce vs. reduceByKey](https://annefou.github.io/pyspark/03-pyspark_context/)
    - [reduce by scala/java/python](https://backtobazics.com/big-data/spark/apache-spark-reduce-example/)
```console
# GroupByTest [numMappers] [numKVPairs] [KeySize] [numReducers]
spark/bin/spark-submit \
--class org.apache.spark.examples.GroupByTest \
--master spark://10.184.108.18:7077 \
spark/examples/jars/spark-examples_2.11-2.4.5.jar 3 100 50 2
# 执行结果
20/04/13 15:09:54 INFO DAGScheduler: Job 1 finished: count at GroupByTest.scala:53, took 1.047462 s
300
```
- 测试Spark文件路径
```console
# 登录master的 shell
scala> sc
res0: org.apache.spark.SparkContext = org.apache.spark.SparkContext@eef6e

scala> sc.addFile("/nodata")
org.apache.spark.SparkException: Added file file:/nodata is a directory and recursive is not turned on.
  at org.apache.spark.SparkContext.addFile(SparkContext.scala:1550)
  at org.apache.spark.SparkContext.addFile(SparkContext.scala:1508)
  ... 49 elided
# 递归将目录/文件随job下载到每一个节点上
scala> sc.addFile("/nodata", true)
scala> import org.apache.spark.SparkFiles
import org.apache.spark.SparkFiles
# 获取通过SparkContext.addFile()文件的绝对路径
scala> SparkFiles.get("nodata")
res4: String = /tmp/spark-2f3aabe2-239c-4c0f-b80b-cbb82d0a2830/userFiles-136c293d-9ae6-48a6-aacf-7aeedc60b0d8/nodata

# 登录worker-1的shell可以看到nodata目录
scala> import org.apache.spark.SparkFiles
import org.apache.spark.SparkFiles

scala> SparkFiles.get("nodata")
res0: String = /tmp/spark-1e5d74c2-3121-4cff-ab1f-2535c3c54d56/userFiles-c514c863-5e1b-424a-9026-cd92e65885ec/nodata

# 提交任务检查文件路径
spark/bin/spark-submit \
--class org.apache.spark.examples.SparkRemoteFileTest \
--master spark://10.184.108.18:7077 \
spark/examples/jars/spark-examples_2.11-2.4.5.jar nodata
```
- 测试 Spark streaming模式词数统计
```console
# 先启动一个测试server,输出 words stream
while true;do { printf 'The test streaming for the test word-count OK'; }|nc -l 9999;done
# 运行 word count
spark/bin/run-example org.apache.spark.examples.streaming.NetworkWordCount 10.83.0.254 9999
# 手动提交job
spark/bin/spark-submit \
--class org.apache.spark.examples.streaming.NetworkWordCount \
--master spark://10.184.108.18:7077 \
spark/examples/jars/spark-examples_2.11-2.4.5.jar 10.83.0.254 9999
# 运行结果 每间隔一秒就会统计一次stream中word的数量
...
(word-count,1)
(OK,1)
(The,1)
(for,1)
(the,1)
(streaming,1)
(test,2)

-------------------------------------------
Time: 1586963207000 ms
-------------------------------------------

20/04/15 15:06:47 WARN ReceiverSupervisorImpl: Restarting receiver with delay 2000 ms: Socket data stream had no more data
20/04/15 15:06:47 ERROR ReceiverTracker: Deregistered receiver for stream 0: Restarting receiver with delay 2000ms: Socket data stream had no more data
-------------------------------------------
Time: 1586963208000 ms
-------------------------------------------
(word-count,1)
(OK,1)
(The,1)
(for,1)
(the,1)
(streaming,1)
(test,2)
...
```
- 测试 Spark hdfs模式词数统计
<br>首先下载一个[单词词库](https://github.com/dwyl/english-words),把words.txt上传master container `docker cp words.txt spark-master:/root`
```console
# 运行 word count程序
spark/bin/run-example org.apache.spark.examples.streaming.HdfsWordCount /spark/data/streaming
# 在/spark/data/streaming目录创建一个text file，触发词统计计算
cp /root/words.txt /spark/data/streaming

# 运行结果 每间隔2秒就会统计一次stream中word的数量
-------------------------------------------
Time: 1589727310000 ms
-------------------------------------------

20/05/17 14:55:14 WARN Executor: Managed memory leak detected; size = 15882842 bytes, TID = 9
-------------------------------------------
Time: 1589727312000 ms
-------------------------------------------
(thysanopteron,1)
(ilio-,1)
(scurrilities,1)
(beshow,1)
(reemigration,1)
(columbaceous,1)
(self-helping,1)
(unprecludable,1)
(Spanish-top,1)
(odwyer,1)
...

-------------------------------------------
Time: 1589727314000 ms
-------------------------------------------

-------------------------------------------
Time: 1589727316000 ms
-------------------------------------------
```
#### 源码分析/API
+ [Structured Streaming源码解析](https://github.com/lw-lin/CoolplaySpark)
+ [spark-core](https://www.javadoc.io/doc/org.apache.spark/spark-core_2.11/2.2.0/index.html#org.apache.spark.SparkContext)
+ [spark-sql](https://www.javadoc.io/doc/org.apache.spark/spark-sql_2.12/3.0.0-preview/org/apache/spark/sql/SparkSession.html)
+ [scala-library](https://www.javadoc.io/doc/org.scala-lang/scala-library/2.12.0/index.html)
```console
# Returns a double value with a positive sign, greater than or equal to 0.0 and less than 1.0
# Deprecated. use the scala.math package object instead
scala> import scala.math.random

# 把seq类型[1..10]map成[1/0]的RDD 
scala> val data=sc.parallelize(1 until 11, 2).map({i=> val x = random * 2 - 1; val y = random * 2 - 1; if (x*x + y*y <= 1) 1 else 0})

# 把data RDD保存为本地datam目录,再把所有数据做相加的reduce
scala> data.saveAsTextFile("datam"); val datar=data.reduce(_ + _)
datar: Int = 6
# 把所有数据做相减的reduce
scala> val datar=datam.reduce(_ - _)
datar1: Int = 1
# 把所有数据做相乘的reduce，存在一个零则乘积为零
scala> val datar=datam.reduce(_ * _)
datar1: Int = 0
# 把所有数据做相除的reduce，如果除零则违反了计算规则
scala> val datar=datam.reduce(_ / _)
20/04/27 03:51:25 ERROR Executor: Exception in task 0.0 in stage 3.0 (TID 6)
java.lang.ArithmeticException: / by zero
```

#### 术语概念

##### HiveContext
Hive加上Spark library打包在一起就是HiveContext, 从SQLContext继承的一种概念. 使用HiveContext, 你能够create/find tables in the HiveMetaStore并且可以用HiveQL写查询功能。没有部署Hive服务的用户也可以create a HiveContext。如果没有通过hive-site.xml指定配置, the context自动创建一个名为metastore_db的metastore和一个名为warehouse的目录。refer to [explanation](https://www.tutorialspoint.com/spark_sql/spark_sql_hive_tables.htm)

Spark < 2.0的发布版本中，如果要集成Hive，你必须使用HiveContext。除此之外，与SQLContext最大不同在于对[window functions](#window-function)的支持和访问Hive UDFs(user defined functions)的能力。

window functions是很酷的特性，能用来解决复杂问题而不用在RDDs和DataFrames之间来来回回。性能也相当好，特别是没有PARTITION BY语句。

Hive UDFs现在不是什么严重问题，但在Spark 1.5之前很多SQL functions已经用Hive UDFs实现，因而也需要HiveContext继续工作。其最大问题可能就是依赖东西太多。

Spark 2.0+以后版本提供了native window functions，因此较少依赖Hive来完成核心功能。

##### Window Function
Spark 1.5之前, Spark SQL支持两种类型function来计算一个单独的返回值，Built-in functions或称为UDFs, 例如substr和round, 从单独一行数据获得输入, 然后计算产生一个单独的返回值，每一行的计算规则都是如此。Aggregate functions, 例如SUM和MAX, 操作a group of rows，然后计算一个返回值，每一组的计算规则都如此。

但实际上这两种方式不足以满足所有需求，例如无法计算a group of rows后却仍旧为每一行数据返回一个值。这样限制使不同的data processing tasks变得困难，例如计算变化的平均值, 计算累计的合。但幸运的是Spark SQL的用户可以用window functions来解决这些困难。

一个window function为一组数据行称为Frame中每一条数据计算返回值。每一个输入行都有唯一的frame与之关联。这个特性使window functions更加强大，能够表达更多很困难表示出来的数据处理任务。refer to [explanation](https://databricks.com/blog/2015/07/15/introducing-window-functions-in-spark-sql.html)

##### Apache Sqoop
[Sqoop](https://cwiki.apache.org/confluence/display/SQOOP/Home)是一个command-line interface应用，在relational databases和Hadoop之间转换数据

##### BlinkDB
[BlinkDB](http://blinkdb.org/) 是一个大规模并行的查询引擎，可以在超大规模的数据集上执行交互式SQL queries

