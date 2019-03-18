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

![Image of Run4](https://databricks.com/wp-content/uploads/2018/05/Apache-Spark-Streaming-ecosystem-diagram.png)
![Image of Run3]https://sdtimes.com/wp-content/uploads/2018/03/image7.png

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