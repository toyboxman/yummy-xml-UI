### Spark
[***Apache Spark***](https://en.wikipedia.org/wiki/Apache_Spark)是通用分布式集群计算框架，提供一个隐含数据并行和容错的接口，来对整个集群编程。Spark架构基于resilient distributed dataset (RDD), 一种只读的分布在多个集群机器上的数据多重集合，这些集合通过fault-tolerant机制维护。Dataframe API是RDD层之上的抽象接口, Dataset API遵从这些接口。Spark 1.x版本中RDD是主要的编程接口, 但Spark 2.x之后推荐使用Dataset API，但RDD技术仍旧是其底层依赖。

#### 架构
![Image of Stack](https://d1.awsstatic.com/Data%20Lake/what-is-apache-spark.b3a3099296936df595d9a7d3610f1a77ff0749df.PNG)

SPARK核心分成四大块功能，分别对应机器学习，实时分析，非结构化数据查询和图处理。

![Image of Stack1](https://i2.wp.com/www.jenunderwood.com/wp-content/uploads/2016/10/SparkArchitecture-Databrickss.gif?ssl=1)

SPARK集群可以对接各种数据源，如传统数据库，非结构化文本数据，消息队列等。集群可以通过云，容器编排系统，Hadoop集群来调度。

![Image of Arch](https://tekclasses.com/wp-content/uploads/2017/06/WHAT-IS-APACHE-SPARK-_-WHY-YOU-SHOULD-LEARN-IT-NOW.png)

SPARK集群对实时数据的计算分析功能很突出。

#### 部署
![Image of Deploy](https://azurecomcdn.azureedge.net/mediahandler/acomblog/media/Default/blog/97bc4145-21de-47f4-b1ef-12bd4635c47a.png)

SPARK集群主要包括master节点和很多worker节点。

![Image of Deploy1](http://aptuz.com/static/media/uploads/blog/hadoop_echosystem.png)

#### 运行
![Image of Run1](https://sigmoid.com/wp-content/uploads/2015/03/Apache_Spark1.png)

![Image of Run2](https://cdn.intellipaat.com/mediaFiles/2017/02/three-ways-Apache-Spark.jpg)

![Image of Run3](https://sdtimes.com/wp-content/uploads/2018/03/image7.png)

#### 使用
- **setup Spark**

下载[Spark](http://spark.apache.org/downloads.html)，解压到设定目录。如果安装Pseudo Distributed/Single Node Cluster,可以参考[help](http://why-not-learn-something.blogspot.com/2015/06/spark-installation-pseudo.html).

参考如下配置项 $SPARK_HOME/conf/spark-default.conf
```
spark.master                    yarn-cluster
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
```
scala> val no = Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
scala> val noData = sc.parallelize(no)
```

#### 术语概念

##### HiveContext
Hive加上Spark library打包在一起就是HiveContext, 从SQLContext继承的一种概念. 使用HiveContext, 你能够create/find tables in the HiveMetaStore并且可以用HiveQL写查询功能。没有部署Hive服务的用户也可以create a HiveContext。如果没有通过hive-site.xml指定配置, the context自动创建一个名为metastore_db的metastore和一个名为warehouse的目录。refer to [explanation](https://www.tutorialspoint.com/spark_sql/spark_sql_hive_tables.htm)

Spark < 2.0的发布版本中，如果要集成Hive，你必须使用HiveContext。除此之外，与SQLContext最大不同在于对[window functions](#windowfunction)的支持和访问Hive UDFs(user defined functions)的能力。

window functions是很酷的特性，能用来解决复杂问题而不用在RDDs和DataFrames之间来来回回。性能也相当好，特别是没有PARTITION BY语句。

Hive UDFs现在不是什么严重问题，但在Spark 1.5之前很多SQL functions已经用Hive UDFs实现，因而也需要HiveContext继续工作。其最大问题可能就是依赖东西太多。

Spark 2.0+以后版本提供了native window functions，因此较少依赖Hive来完成核心功能。

##### Window Function
Spark 1.5之前, Spark SQL支持两种类型function来计算一个单独的返回值，Built-in functions或称为UDFs, 例如substr和round, 从单独一行数据获得输入, 然后计算产生一个单独的返回值，每一行的计算规则都是如此。Aggregate functions, 例如SUM和MAX, 操作a group of rows，然后计算一个返回值，每一组的计算规则都如此。

但实际上这两种方式不足以满足所有需求，例如无法计算a group of rows后却仍旧为每一行数据返回一个值。这样限制使不同的data processing tasks变得困难，例如计算变化的平均值, 计算累计的合。但幸运的是Spark SQL的用户可以用window functions来解决这些困难。

一个window function为一组数据行称为Frame中每一条数据计算返回值。每一个输入行都有唯一的frame与之关联。这个特性使window functions更加强大，能够表达更多很困难表示出来的数据处理任务。refer to [explanation](https://databricks.com/blog/2015/07/15/introducing-window-functions-in-spark-sql.html)

