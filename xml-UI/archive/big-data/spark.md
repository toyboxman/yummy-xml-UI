### Spark
[***Apache Spark***](https://en.wikipedia.org/wiki/Apache_Spark)��ͨ�÷ֲ�ʽ��Ⱥ�����ܣ��ṩһ���������ݲ��к��ݴ�Ľӿڣ�����������Ⱥ��̡�Spark�ܹ�����resilient distributed dataset (RDD), һ��ֻ���ķֲ��ڶ����Ⱥ�����ϵ����ݶ��ؼ��ϣ���Щ����ͨ��fault-tolerant����ά����Dataframe API��RDD��֮�ϵĳ���ӿ�, Dataset API�����Щ�ӿڡ�Spark 1.x�汾��RDD����Ҫ�ı�̽ӿ�, ��Spark 2.x֮���Ƽ�ʹ��Dataset API����RDD�����Ծ�����ײ�������

#### �ܹ�
![Image of Stack](https://d1.awsstatic.com/Data%20Lake/what-is-apache-spark.b3a3099296936df595d9a7d3610f1a77ff0749df.PNG)

SPARK���ķֳ��Ĵ�鹦�ܣ��ֱ��Ӧ����ѧϰ��ʵʱ�������ǽṹ�����ݲ�ѯ��ͼ����
+ Spark core �C Foundation for data processing
+ Spark SQL �C Based on Shark and helps in data extracting, loading and transformation
+ Spark streaming �C Light API helps in batch processing and streaming of data
+ Machine learning library �C Helps in machine learning algorithm implementation.
+ Graph Analytics(GraphX) �C Helps in representing Resilient Distributed Graph
+ Spark Cassandra Connector
+ Spark R integration

![Image of Stack1](https://i2.wp.com/www.jenunderwood.com/wp-content/uploads/2016/10/SparkArchitecture-Databrickss.gif?ssl=1)

SPARK��Ⱥ���ԶԽӸ�������Դ���紫ͳ���ݿ⣬�ǽṹ���ı����ݣ���Ϣ���еȡ���Ⱥ����ͨ���ƣ���������ϵͳ��Hadoop��Ⱥ�����ȡ�Spark����������Hadoop data�����䣬�ܴ���data in HDFS, HBase, Cassandra, Hive, and any Hadoop InputFormat. �������������batch processing (similar to MapReduce)��streaming, interactive queries, and machine learning. 

���Spark�ܹ���Hadoop��Ϊ�����ݴ���ܹ��������MapReduce ֻ��һ��native batch processing engine������Hadoop�����ٶ�û��Spark�졣����֮�⣬�������ݴ���Ҫ����batch processing��real-time processing. ��Hadoop��s MapReduce���ܴ���batch data�������޷���������ݴ���low latencyҪ��. 

�������Spark����Hadoop֮�ϣ�����hybrid framework��resilient distributed dataset (RDD), ������Sparkʱ�������ܴ洢���ڴ��������ٴ���. û��Spark��������Hadoop�޷����Real-time and faster data processing. û��Hadoop��������SparkҲ�޷�ʹ����ֲ�ʽ�ļ�ϵͳ������multi-petabytes��data. Spark������һ��cluster computing system������data storage system���������Ҫͨ���ⲿ����Դ����дdata. �������Ҫʹ��HDFS���ļ�������Դ������local file system��Ҳ������no SQL database like Apache Cassandra or HBase or Amazon��s S3. ���ֳ���������Spark without Hadoop.

#### ����
![Image of Deploy](https://azurecomcdn.azureedge.net/mediahandler/acomblog/media/Default/blog/97bc4145-21de-47f4-b1ef-12bd4635c47a.png)

SPARK��Ⱥ��Ҫ����master�ڵ�ͺܶ�worker�ڵ㡣

���ַ�ʽ����������Spark in Hadoop cluster: Standalone, Over YARN, In MapReduce (SIMR)

+ Standalone Deployment

����ʽ��resources��̬���䵽Hadoop cluster����nodes����subsets of nodes. ��������Spark��MapReduce��Spark�����Լ�cluster. ����Hadoop 1.x�Ƽ�����ʽ

+ Over YARN Deployment

����Hadoop and Spark���ɵļ򵥷���������Ҫpre-installation��admin����. YARN��Ψһ��ȫ��cluster manager���ڴ�Hadoop cluster��Ʒ�������Ǹ��õĲ���ѡ��

+ Spark In MapReduce (SIMR)

���ַ�ʽ�²���ҪYARN��Spark jobs������MapReduce�з���.

#### ����
![Image of Run1](https://sigmoid.com/wp-content/uploads/2015/03/Apache_Spark1.png)

![Image of Run3](https://sdtimes.com/wp-content/uploads/2018/03/image7.png)

Amazon Elastic MapReduce (EMR)��Amazon Web Services (AWS)�����ݴ�����, ����Apache Hadoop����֮��
![Image of Run4](https://d1.awsstatic.com/Products/product-name/diagrams/product-page-diagram_Amazon-Kinesis-Data-Streams.074de94302fd60948e1ad070e425eeda73d350e7.png)

#### ʹ��
- **setup Spark**

����[Spark](http://spark.apache.org/downloads.html)����ѹ���趨Ŀ¼�������װPseudo Distributed/Single Node Cluster,���Բο�[help](http://why-not-learn-something.blogspot.com/2015/06/spark-installation-pseudo.html).

�ο����������� [$SPARK_HOME/conf/spark-default.conf](https://jaceklaskowski.gitbooks.io/mastering-apache-spark/spark-SparkConf.html)

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

ʹ������֮ǰ��Ҫȷ��[Scala](https://downloads.lightbend.com/scala/2.12.8/scala-2.12.8.tgz)�Ѿ��ڱ��ذ�װ�ã�����[link](https://www.scala-lang.org/)��

����·���� .bashrc
```console
export SPARK_HOME=/apache/spark
export SCALA_HOME=/apache/scala

export PATH=$PATH:$SPARK_HOME/bin:$SCALA_HOME/bin
```
����spark shell�������档
```console
$ spark-shell
scala> :help
scala> :quit

# ���ϣ������һ������shell�������Ҳ���԰�����д��һ���ļ�����shellʱpreload
# shell�ᰴ�����н��ͷ�ʽ�������ļ�
$ spark-shell -I <FilePath>
```
Create an RDD through Parallelized Collection, more refer to [commands](https://data-flair.training/blogs/scala-spark-shell-commands/)

����ʹ��spark-shellʱ����Ҫ�Ȱ�Hadoop��Yarn�ķ���������[�ο�ʹ��](https://github.com/apache/griffin/blob/master/griffin-doc/deploy/deploy-guide.md#hadoop)
```console
// Ĭ��һ��jvm�о���һ��context���У�����ֱ��ʹ��
scala> sc
res0: org.apache.spark.SparkContext = org.apache.spark.SparkContext@4b3b2a4f

// ���ϣ������һ���µ�context����ִ������ָ��
scala> import org.apache.spark.{SparkConf,SparkContext}
// Create conf object
scala> val conf = new SparkConf().setAppName("Count")
// Ĭ��һ��jvm��ֻ����һ��context����
//scala> conf.get("spark.driver.allowMultipleContexts")
//res0: String = false
//scala> conf.set("spark.driver.allowMultipleContexts","true")
//create spark context object
scala> val sc = new SparkContext(conf)
scala> sc
res0: org.apache.spark.SparkContext = org.apache.spark.SparkContext@bc4a9b0

scala> val no = Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
no: Array[Int] = Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
// Ĭ��partition��2 parts,  sc.parallelize(no, 3)��partition��3 parts
scala> val noData = sc.parallelize(no)
noData: org.apache.spark.rdd.RDD[Int] = ParallelCollectionRDD[0] at parallelize at <console>:27

// �����д���ļ�ϵͳ�����淢��'nodata.txt'��Hadoop��Ŀ¼��
scala> noData.saveAsTextFile("nodata.txt")
scala> sc.stop
```
ͨ��Hadoop�����������д����ļ�
```console
$ hdfs dfs -find / nodata*
nodata.txt
nodata.txt/_SUCCESS
nodata.txt/part-00000
nodata.txt/part-00001

// 'nodata.txt'��Ŀ¼�����ļ�
$ hdfs dfs -cat nodata.txt
cat: `nodata.txt': Is a directory

// ���ļ�
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
���sparkʹ�ñ����ļ�ϵͳ�����ѽ��д�뱾���ļ�Ŀ¼
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
ÿһ��SparkContext��һ��web UI, Ĭ�Ϸ��ʵ�ַ http://driver-node:4040, ������ʾapplication��Ϣ������scheduler stages/tasks/RDD sizes/memory usage/Environmental/running executors  [*spark application monitoring rest-api*](https://spark.apache.org/docs/latest/monitoring.html#rest-api)

������SparkContexts������ͬһ̨host��, web�������������󶨶˿�4040 (4041, 4042, etc). ���ֱ�ӷ���spark web����ʧ�ܣ�������ͨ��Hadoop���������� http://127.0.0.1:8088/proxy/application_1566972520413_0001/stages/


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
����spark������Ŵ���ţ����Բο�[databricks-spark-knowledge-base](https://databricks.gitbooks.io/databricks-spark-knowledge-base/)

- **Spark Runs with Docker** 

����ʹ��spark����ͨ��[docker container��ʽ](https://github.com/big-data-europe/docker-spark)�����ⰲװ����
```console
# ����image
docker pull bde2020/spark-master
docker pull bde2020/spark-worker
docker pull bde2020/spark-base
# ����containerʵ��
docker run --name spark-master -h spark-master -p6066:6066 -p7077:7077 -p8080:8080 -e ENABLE_INIT_DAEMON=false -d bde2020/spark-master:latest
docker run --name spark-worker-1 --link spark-master:spark-master -p8081:8081 -e ENABLE_INIT_DAEMON=false -d bde2020/spark-worker:latest

# ����docker������IPΪ 10.184.108.18������ͨ������URL����master��worker
# master node URL
http://10.184.108.18:8080/
# worker node URL http://172.17.0.3:8081/ ӳ��Ϊ������ URL
http://10.184.108.18:8081/

# ��¼spark master�ڵ�
docker exec -it spark-master bash
# ��¼spark worker-1�ڵ�
docker exec -it spark-worker-1 bash

# ����spark�����ģʽ
bash-5.0# spark/bin/spark-shell
# �˳������ģʽ
scala> :quit

# spark-submit ����ʹ��˵��
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

# �ύpython PI���������1000�ε�local����
spark/bin/spark-submit \
--master local \
spark/examples/src/main/python/pi.py 1000
# ��������ʾ     
20/04/12 14:14:02 INFO DAGScheduler: Job 0 finished: reduce at /spark/examples/src/main/python/pi.py:44, took 141.028314 s
Pi is roughly 3.141309

# �ύpython PI���������200�ε�spark cluster����
# ���������local�������ύ��������ͨ�� master/worker node URL(http://<������>:8080/) �鿴jobִ����Ϣ����־��
spark/bin/spark-submit \
--master spark://10.184.108.18:7077 \
spark/examples/src/main/python/pi.py 200 
# ��������ʾ�����Կ��� worker node(172.17.0.3)��200��ִ��ʱ��158 ms
20/04/12 14:30:18 INFO TaskSetManager: Finished task 199.0 in stage 0.0 (TID 199) in 158 ms on 172.17.0.3 (executor 0) (200/200)
20/04/12 14:30:18 INFO DAGScheduler: Job 0 finished: reduce at /spark/examples/src/main/python/pi.py:44, took 10.733714 s
Pi is roughly 3.141113

# examplesĿ¼�л��ṩ��������,����java/Scala/R���԰汾
bash-5.0# ls spark/examples/src/main/python/
als.py                  logistic_regression.py  pagerank.py             sort.py                 streaming/
avro_inputformat.py     ml/                     parquet_inputformat.py  sql/                    transitive_closure.py
kmeans.py               mllib/                  pi.py                   status_api_demo.py      wordcount.py

# �ύscala/java PI���������spark cluster����
# �������spark.eventLog���ã���Ҫ�� mkdir /tmp/spark-events,�¼����¼spark��һЩ������Ϣ
spark/bin/spark-submit \
--class org.apache.spark.examples.SparkPi \
--master spark://10.184.108.18:7077 \
--conf spark.eventLog.enabled=true \
spark/examples/jars/spark-examples_2.11-2.4.5.jar 100
# ��������ʾ
20/04/13 11:52:23 INFO TaskSetManager: Finished task 99.0 in stage 0.0 (TID 99) in 56 ms on 172.17.0.3 (executor 0) (100/100)
20/04/13 11:52:23 INFO DAGScheduler: Job 0 finished: reduce at SparkPi.scala:38, took 4.118978 s
Pi is roughly 3.1415535141553512

# ����spark-sql�����ģʽ
bash-5.0# spark/bin/spark-sql
spark-sql> :help
         > ;
Error in query: 
mismatched input ':' expecting {'(', 'SELECT', 'FROM', 'ADD', 'DESC', 'WITH', 'VALUES', 'CREATE', 'TABLE', 'INSERT', 'DELETE', 'DESCRIBE', 'EXPLAIN', 'SHOW', 'USE', 'DROP', 'ALTER', 'MAP', 'SET', 'RESET', 'START', 'COMMIT', 'ROLLBACK', 'REDUCE', 'REFRESH', 'CLEAR', 'CACHE', 'UNCACHE', 'DFS', 'TRUNCATE', 'ANALYZE', 'LIST', 'REVOKE', 'GRANT', 'LOCK', 'UNLOCK', 'MSCK', 'EXPORT', 'IMPORT', 'LOAD'}(line 1, pos 0)

== SQL ==
:help
# �˳������ģʽ
spark-sql> quit;

```
**spark-examples**
- [A broadcast variable](https://spark.apache.org/docs/2.4.5/api/java/org/apache/spark/broadcast/Broadcast.html) - sc.broadcast�����нڵ��Ϲ㲥ֻ���Ĺ��������������job���ݱ���������ÿ̨�����ϣ�������Ч�ظ�ÿ���ڵ����볬��dataset��Spark��ͨ����Ч�㷨��СͨѶ�ɱ�.
```console
# BroadcastTest [partitions] [numElem] [blockSize]
spark/bin/spark-submit \
--class org.apache.spark.examples.BroadcastTest \
--master spark://10.184.108.18:7077 \
spark/examples/jars/spark-examples_2.11-2.4.5.jar 100 500
# ���н��
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
- �����������쳣����
```console
spark/bin/spark-submit \
--class org.apache.spark.examples.ExceptionHandlingTest \
--master spark://10.184.108.18:7077 \
spark/examples/jars/spark-examples_2.11-2.4.5.jar
```
- �����������������ͳ�� - [parallelize](https://spark.apache.org/docs/2.4.5/api/scala/index.html#org.apache.spark.SparkContext)
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
# ִ�н��
20/04/13 15:09:54 INFO DAGScheduler: Job 1 finished: count at GroupByTest.scala:53, took 1.047462 s
300
```
- ����Spark�ļ�·��
```console
# ��¼master�� shell
scala> sc
res0: org.apache.spark.SparkContext = org.apache.spark.SparkContext@eef6e

scala> sc.addFile("/nodata")
org.apache.spark.SparkException: Added file file:/nodata is a directory and recursive is not turned on.
  at org.apache.spark.SparkContext.addFile(SparkContext.scala:1550)
  at org.apache.spark.SparkContext.addFile(SparkContext.scala:1508)
  ... 49 elided
# �ݹ齫Ŀ¼/�ļ���job���ص�ÿһ���ڵ���
scala> sc.addFile("/nodata", true)
scala> import org.apache.spark.SparkFiles
import org.apache.spark.SparkFiles
# ��ȡͨ��SparkContext.addFile()�ļ��ľ���·��
scala> SparkFiles.get("nodata")
res4: String = /tmp/spark-2f3aabe2-239c-4c0f-b80b-cbb82d0a2830/userFiles-136c293d-9ae6-48a6-aacf-7aeedc60b0d8/nodata

# ��¼worker-1��shell���Կ���nodataĿ¼
scala> import org.apache.spark.SparkFiles
import org.apache.spark.SparkFiles

scala> SparkFiles.get("nodata")
res0: String = /tmp/spark-1e5d74c2-3121-4cff-ab1f-2535c3c54d56/userFiles-c514c863-5e1b-424a-9026-cd92e65885ec/nodata

# �ύ�������ļ�·��
spark/bin/spark-submit \
--class org.apache.spark.examples.SparkRemoteFileTest \
--master spark://10.184.108.18:7077 \
spark/examples/jars/spark-examples_2.11-2.4.5.jar nodata
```
- ���� Spark streamingģʽ����ͳ��
```console
# ������һ������server,��� words stream
while true;do { printf 'The test streaming for the test word-count OK'; }|nc -l 9999;done
# ���� word count
spark/bin/run-example org.apache.spark.examples.streaming.NetworkWordCount 10.83.0.254 9999
# �ֶ��ύjob
spark/bin/spark-submit \
--class org.apache.spark.examples.streaming.NetworkWordCount \
--master spark://10.184.108.18:7077 \
spark/examples/jars/spark-examples_2.11-2.4.5.jar 10.83.0.254 9999
# ���н�� ÿ���һ��ͻ�ͳ��һ��stream��word������
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
- ���� Spark hdfsģʽ����ͳ��
<br>��������һ��[���ʴʿ�](https://github.com/dwyl/english-words),��words.txt�ϴ�master container `docker cp words.txt spark-master:/root`
```console
# ���� word count����
spark/bin/run-example org.apache.spark.examples.streaming.HdfsWordCount /spark/data/streaming
# ��/spark/data/streamingĿ¼����һ��text file��������ͳ�Ƽ���
cp /root/words.txt /spark/data/streaming

# ���н�� ÿ���2��ͻ�ͳ��һ��stream��word������
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
#### Դ�����/API
+ [Structured StreamingԴ�����](https://github.com/lw-lin/CoolplaySpark)
+ [spark-core](https://www.javadoc.io/doc/org.apache.spark/spark-core_2.11/2.2.0/index.html#org.apache.spark.SparkContext)
+ [spark-sql](https://www.javadoc.io/doc/org.apache.spark/spark-sql_2.12/3.0.0-preview/org/apache/spark/sql/SparkSession.html)
+ [scala-library](https://www.javadoc.io/doc/org.scala-lang/scala-library/2.12.0/index.html)
```console
# Returns a double value with a positive sign, greater than or equal to 0.0 and less than 1.0
# Deprecated. use the scala.math package object instead
scala> import scala.math.random

# ��seq����[1..10]map��[1/0]��RDD 
scala> val data=sc.parallelize(1 until 11, 2).map({i=> val x = random * 2 - 1; val y = random * 2 - 1; if (x*x + y*y <= 1) 1 else 0})

# ��data RDD����Ϊ����datamĿ¼,�ٰ�������������ӵ�reduce
scala> data.saveAsTextFile("datam"); val datar=data.reduce(_ + _)
datar: Int = 6
# �����������������reduce
scala> val datar=datam.reduce(_ - _)
datar1: Int = 1
# ��������������˵�reduce������һ������˻�Ϊ��
scala> val datar=datam.reduce(_ * _)
datar1: Int = 0
# �����������������reduce�����������Υ���˼������
scala> val datar=datam.reduce(_ / _)
20/04/27 03:51:25 ERROR Executor: Exception in task 0.0 in stage 3.0 (TID 6)
java.lang.ArithmeticException: / by zero
```

#### �������

##### HiveContext
Hive����Spark library�����һ�����HiveContext, ��SQLContext�̳е�һ�ָ���. ʹ��HiveContext, ���ܹ�create/find tables in the HiveMetaStore���ҿ�����HiveQLд��ѯ���ܡ�û�в���Hive������û�Ҳ����create a HiveContext�����û��ͨ��hive-site.xmlָ������, the context�Զ�����һ����Ϊmetastore_db��metastore��һ����Ϊwarehouse��Ŀ¼��refer to [explanation](https://www.tutorialspoint.com/spark_sql/spark_sql_hive_tables.htm)

Spark < 2.0�ķ����汾�У����Ҫ����Hive�������ʹ��HiveContext������֮�⣬��SQLContext���ͬ���ڶ�[window functions](#window-function)��֧�ֺͷ���Hive UDFs(user defined functions)��������

window functions�Ǻܿ�����ԣ�������������������������RDDs��DataFrames֮�������ػء�����Ҳ�൱�ã��ر���û��PARTITION BY��䡣

Hive UDFs���ڲ���ʲô�������⣬����Spark 1.5֮ǰ�ܶ�SQL functions�Ѿ���Hive UDFsʵ�֣����Ҳ��ҪHiveContext���������������������ܾ�����������̫�ࡣ

Spark 2.0+�Ժ�汾�ṩ��native window functions����˽�������Hive����ɺ��Ĺ��ܡ�

##### Window Function
Spark 1.5֮ǰ, Spark SQL֧����������function������һ�������ķ���ֵ��Built-in functions���ΪUDFs, ����substr��round, �ӵ���һ�����ݻ������, Ȼ��������һ�������ķ���ֵ��ÿһ�еļ����������ˡ�Aggregate functions, ����SUM��MAX, ����a group of rows��Ȼ�����һ������ֵ��ÿһ��ļ��������ˡ�

��ʵ���������ַ�ʽ�����������������������޷�����a group of rows��ȴ�Ծ�Ϊÿһ�����ݷ���һ��ֵ����������ʹ��ͬ��data processing tasks������ѣ��������仯��ƽ��ֵ, �����ۼƵĺϡ������˵���Spark SQL���û�������window functions�������Щ���ѡ�

һ��window functionΪһ�������г�ΪFrame��ÿһ�����ݼ��㷵��ֵ��ÿһ�������ж���Ψһ��frame��֮�������������ʹwindow functions����ǿ���ܹ�����������ѱ�ʾ���������ݴ�������refer to [explanation](https://databricks.com/blog/2015/07/15/introducing-window-functions-in-spark-sql.html)

##### Apache Sqoop
[Sqoop](https://cwiki.apache.org/confluence/display/SQOOP/Home)��һ��command-line interfaceӦ�ã���relational databases��Hadoop֮��ת������

##### BlinkDB
[BlinkDB](http://blinkdb.org/) ��һ�����ģ���еĲ�ѯ���棬�����ڳ����ģ�����ݼ���ִ�н���ʽSQL queries

