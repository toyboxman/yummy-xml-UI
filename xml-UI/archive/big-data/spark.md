### Spark
[***Apache Spark***](https://en.wikipedia.org/wiki/Apache_Spark)��ͨ�÷ֲ�ʽ��Ⱥ�����ܣ��ṩһ���������ݲ��к��ݴ�Ľӿڣ�����������Ⱥ��̡�Spark�ܹ�����resilient distributed dataset (RDD), һ��ֻ���ķֲ��ڶ����Ⱥ�����ϵ����ݶ��ؼ��ϣ���Щ����ͨ��fault-tolerant����ά����Dataframe API��RDD��֮�ϵĳ���ӿ�, Dataset API�����Щ�ӿڡ�Spark 1.x�汾��RDD����Ҫ�ı�̽ӿ�, ��Spark 2.x֮���Ƽ�ʹ��Dataset API����RDD�����Ծ�����ײ�������

#### �ܹ�
![Image of Stack](https://d1.awsstatic.com/Data%20Lake/what-is-apache-spark.b3a3099296936df595d9a7d3610f1a77ff0749df.PNG)

SPARK���ķֳ��Ĵ�鹦�ܣ��ֱ��Ӧ����ѧϰ��ʵʱ�������ǽṹ�����ݲ�ѯ��ͼ����

![Image of Stack1](https://i2.wp.com/www.jenunderwood.com/wp-content/uploads/2016/10/SparkArchitecture-Databrickss.gif?ssl=1)

SPARK��Ⱥ���ԶԽӸ�������Դ���紫ͳ���ݿ⣬�ǽṹ���ı����ݣ���Ϣ���еȡ���Ⱥ����ͨ���ƣ���������ϵͳ��Hadoop��Ⱥ�����ȡ�

![Image of Arch](https://tekclasses.com/wp-content/uploads/2017/06/WHAT-IS-APACHE-SPARK-_-WHY-YOU-SHOULD-LEARN-IT-NOW.png)

SPARK��Ⱥ��ʵʱ���ݵļ���������ܺ�ͻ����

#### ����
![Image of Deploy](https://azurecomcdn.azureedge.net/mediahandler/acomblog/media/Default/blog/97bc4145-21de-47f4-b1ef-12bd4635c47a.png)

SPARK��Ⱥ��Ҫ����master�ڵ�ͺܶ�worker�ڵ㡣

![Image of Deploy1](http://aptuz.com/static/media/uploads/blog/hadoop_echosystem.png)

#### ����
![Image of Run1](https://sigmoid.com/wp-content/uploads/2015/03/Apache_Spark1.png)

![Image of Run2](https://cdn.intellipaat.com/mediaFiles/2017/02/three-ways-Apache-Spark.jpg)

![Image of Run4](https://databricks.com/wp-content/uploads/2018/05/Apache-Spark-Streaming-ecosystem-diagram.png)
![Image of Run3]https://sdtimes.com/wp-content/uploads/2018/03/image7.png

#### ʹ��
- **setup Spark**

����[Spark](http://spark.apache.org/downloads.html)����ѹ���趨Ŀ¼�������װPseudo Distributed/Single Node Cluster,���Բο�[help](http://why-not-learn-something.blogspot.com/2015/06/spark-installation-pseudo.html).

�ο����������� $SPARK_HOME/conf/spark-default.conf
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

ʹ������֮ǰ��Ҫȷ��[Scala](https://downloads.lightbend.com/scala/2.12.8/scala-2.12.8.tgz)�Ѿ��ڱ��ذ�װ�ã�����[link](https://www.scala-lang.org/)��

����·���� .bashrc
```
export SPARK_HOME=/apache/spark
export SCALA_HOME=/apache/scala

export PATH=$PATH:$SPARK_HOME/bin:$SCALA_HOME/bin
```
����spark shell�������档
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