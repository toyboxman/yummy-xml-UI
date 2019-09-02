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

![Image of Deploy1](http://aptuz.com/static/media/uploads/blog/hadoop_echosystem.png)

三种方式来部署运行Spark in Hadoop cluster: Standalone, Over YARN, In MapReduce (SIMR)

+ Standalone Deployment

极简方式，resources静态分配到Hadoop cluster所有nodes或者subsets of nodes. 并行运行Spark与MapReduce，Spark管理自己cluster. 这是Hadoop 1.x推荐部署方式

+ Over YARN Deployment

这是Hadoop and Spark集成的简单方法，不需要pre-installation或admin配置. YARN是唯一安全的cluster manager，在大Hadoop cluster产品环境下是个好的部署选择

+ Spark In MapReduce (SIMR)

这种方式下不需要YARN，Spark jobs可以在MapReduce中发起.

#### 运行
![Image of Run1](https://sigmoid.com/wp-content/uploads/2015/03/Apache_Spark1.png)

![Image of Run2](https://cdn.intellipaat.com/mediaFiles/2017/02/three-ways-Apache-Spark.jpg)

![Image of Run3](https://sdtimes.com/wp-content/uploads/2018/03/image7.png)

![Image of Run4](https://d1.awsstatic.com/Products/product-name/diagrams/product-page-diagram_Amazon-Kinesis-Data-Streams.074de94302fd60948e1ad070e425eeda73d350e7.png)

Amazon Elastic MapReduce (EMR)是Amazon Web Services (AWS)大数据处理工具, 基于Apache Hadoop技术之上

#### 使用
- **setup Spark**

下载[Spark](http://spark.apache.org/downloads.html)，解压到设定目录。如果安装Pseudo Distributed/Single Node Cluster,可以参考[help](http://why-not-learn-something.blogspot.com/2015/06/spark-installation-pseudo.html).

参考如下配置项 $SPARK_HOME/conf/spark-default.conf
```
spark.master                    yarn
spark.serializer                org.apache.spark.serializer.KryoSerializer
spark.yarn.jars                 hdfs:///home/spark_lib/*
spark.yarn.dist.files		hdfs:///home/spark_conf/hive-site.xml
spark.sql.broadcastTimeout  500
```
$SPARK_HOME/conf/spark-env.sh
```
HADOOP_CONF_DIR=$HADOOP_HOME/etc/hadoop
SPARK_MASTER_HOST=localhost
SPARK_MASTER_PORT=7077
SPARK_MASTER_WEBUI_PORT=8082
SPARK_LOCAL_IP=localhost
SPARK_PID_DIR=/apache/pids
```
Upload some files otherwise you will hit `Error: Could not find or load main class org.apache.spark.deploy.yarn.ApplicationMaster`, when you schedule spark applications.
```bash
hdfs dfs -mkdir /home/spark_lib
hdfs dfs -mkdir /home/spark_conf
hdfs dfs -put $SPARK_HOME/jars/*  hdfs:///home/spark_lib/
hdfs dfs -put $HIVE_HOME/conf/hive-site.xml hdfs:///home/spark_conf/
```
- **start/stop spark nodes**
```bash
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
```
export SPARK_HOME=/apache/spark
export SCALA_HOME=/apache/scala

export PATH=$PATH:$SPARK_HOME/bin:$SCALA_HOME/bin
```
进入spark shell交互界面。
```
$ spark-shell
scala> :help
scala> :quit
```
Create an RDD through Parallelized Collection, more refer to [commands](https://data-flair.training/blogs/scala-spark-shell-commands/)

本地使用spark-shell时候需要先把Hadoop和Yarn的服务启动，[参考使用](https://github.com/apache/griffin/blob/master/griffin-doc/deploy/deploy-guide.md#hadoop)
```
scala> import org.apache.spark.{SparkConf,SparkContext}

//Create conf object
scala> val conf = new SparkConf().setAppName("Count")
// 默认一个jvm中只有一个context运行
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
```
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

$ hdfs dfs -cat nodata.txt/part*
1
2
3
4
5
6
7
8
9
10

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
每一个SparkContext有一个web UI, 默认访问地址 http://driver-node:4040, 用来显示application信息，包括scheduler stages/tasks/RDD sizes/memory usage/Environmental/running executors  [*spark application monitoring rest-api*](https://spark.apache.org/docs/latest/monitoring.html#rest-api)

如果多个SparkContexts运行在同一台host上, web服务会依次往后绑定端口4040 (4041, 4042, etc). 如果直接访问spark web服务失败，还可以通过Hadoop代理来访问 http://127.0.0.1:8088/proxy/application_1566972520413_0001/stages/


Submit a Scala job to Spark
```
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

