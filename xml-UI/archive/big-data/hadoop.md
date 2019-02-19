### Hadoop
[***Apache Hadoop***](https://en.wikipedia.org/wiki/Apache_Hadoop)是一套open-source的大数据工具集，通过利用网络计算机集群来进行大数据的计算工作，核心组件有storage被称为Hadoop Distributed File System (HDFS), 计算处理被称为MapReduce programming model。Hadoop把计算目标文件分割成多个block并分布于集群中节点上，然后将计算代码包上传到各个节点，在各个节点之上进行并行计算。这样做法学习了超算的架构，但相比超算需要利用高速网络和并行文件系统计算来说，基于本地数据计算会更加有效和快速。<br>
Apache Hadoop framework由下面一些模块组成:

    - Hadoop Common – contains libraries and utilities needed by other Hadoop modules
    - Hadoop Distributed File System (HDFS) – a distributed file-system that stores data on commodity machines
    - Hadoop YARN – a platform responsible for managing computing resources in clusters and using them for scheduling users' applications
    - Hadoop MapReduce – an implementation of the MapReduce programming model for large-scale data processing.

广义的Hadoop常常包含更多应用模块属于大数据生态系统，例如 Apache Pig, Apache Hive, Apache HBase, Apache Phoenix, Apache Spark, Apache ZooKeeper, Cloudera Impala, Apache Flume, Apache Sqoop, Apache Oozie, and Apache Storm 

#### 生态系统
![Image of Arch](https://c2programming.files.wordpress.com/2016/05/scaling-up-with-hadoop-and-banyan-hadoop-family.png)<br>
- 完整的Hadoop系统包括了多个子系统组件，覆盖输入、输出、存储、查询、展示多种功能
![Image of ecosystem](https://2.bp.blogspot.com/-w7KeAnwWnBQ/WfYBJzgtvQI/AAAAAAAAAMk/D58SpZfK7lkJ8QnKnQZW268mKzRvuOOnACLcBGAs/s1600/HadoopStack.png)<br>
- 与更多第三方组件集成之后，整个[ecosystem](http://blog.newtechways.com/2017/10/apache-hadoop-ecosystem.html)更加丰富
![Image of map](http://bigdataanalyticsnews.com/wp-content/uploads/2014/02/hadoop_map1.png)

#### 架构
#### 部署
![Image of Arch](https://image.slidesharecdn.com/integrationofapachehiveandhbasefinal-120504182337-phpapp01/95/integration-of-hive-and-hbase-34-728.jpg?cb=1336156004)<br>

- Usage:
当查询hive tables或database, 查询请求会自动在hive service和hive server之间交互。需要创建新service或project，可以使用thrift protocols，此协议可以实现跨语言的web-service。

#### 使用
- **setup hive**

下载[Hive](http://apache.claz.org/hive/)，解压到设定目录。Copy hive/conf/hive-default.xml.template to hive/conf/hive-site.xml修改以下配置属性。
```
   <property>
     <name>hive.exec.local.scratchdir</name>
-    <value>${system:java.io.tmpdir}/${system:user.name}</value>
+    <value>/apache/tmp/hive</value>
     <description>Local scratch space for Hive jobs</description>
   </property>
   <property>
     <name>hive.downloaded.resources.dir</name>
-    <value>${system:java.io.tmpdir}/${hive.session.id}_resources</value>
+    <value>/apache/tmp/hive/${hive.session.id}_resources</value>
     <description>Temporary local directory for added resources in the remote file system.</description>
   </property>
   <property>
@@ -368,7 +368,7 @@
   </property>
   <property>
     <name>hive.metastore.uris</name>
-    <value/>
+    <value>thrift://127.0.0.1:9083</value>
     <description>Thrift URI for the remote metastore. Used by metastore client to connect to remote metastore.</description>
   </property>
   <property>
@@ -527,7 +527,7 @@
   </property>
   <property>
     <name>javax.jdo.option.ConnectionPassword</name>
-    <value>mine</value>
+    <value>secret</value>
     <description>password to use against metastore database</description>
   </property>
   <property>
@@ -542,7 +542,7 @@
   </property>
   <property>
     <name>javax.jdo.option.ConnectionURL</name>
-    <value>jdbc:derby:;databaseName=metastore_db;create=true</value>
+    <value>jdbc:postgresql://127.0.0.1/myDB?ssl=false</value>
     <description>
       JDBC connect string for a JDBC metastore.
       To use SSL to encrypt/authenticate the connection, provide database-specific SSL flag in the connection URL.
@@ -1017,7 +1017,7 @@
   </property>
   <property>
     <name>javax.jdo.option.ConnectionDriverName</name>
-    <value>org.apache.derby.jdbc.EmbeddedDriver</value>
+    <value>org.postgresql.Driver</value>
     <description>Driver class name for a JDBC metastore</description>
   </property>
   <property>
@@ -1042,7 +1042,7 @@
   </property>
   <property>
     <name>javax.jdo.option.ConnectionUserName</name>
-    <value>APP</value>
+    <value>king</value>
     <description>Username to use against metastore database</description>
   </property>
   <property>
@@ -1682,7 +1682,7 @@
   </property>
   <property>
     <name>hive.querylog.location</name>
-    <value>${system:java.io.tmpdir}/${system:user.name}</value>
+    <value>/apache/tmp/hive</value>
     <description>Location of Hive run time structured log file</description>
   </property>
   <property>
@@ -3973,7 +3973,7 @@
   </property>
   <property>
     <name>hive.server2.logging.operation.log.location</name>
-    <value>${system:java.io.tmpdir}/${system:user.name}/operation_logs</value>
+    <value>/apache/tmp/hive/operation_logs</value>
     <description>Top level directory where operation logs are stored if logging functionality is enabled</description>
   </property>
   <property>
```
启动metastore service ：`/apache/hive/bin/hive --service metastore`

登录hive CLI Client：`/apache/hive/bin/hive --database default`

CLI: hive> SHOW TABLES;

参考命令行文档[hive CLI](https://cwiki.apache.org/confluence/display/Hive/GettingStarted#GettingStarted-RunningHiveCLI), [CLI manual](
https://cwiki.apache.org/confluence/display/Hive/LanguageManual+Cli)

- **create a table with partitions from existing files on Hadoop**

示例:

分区表有四列(id INT, first_name STRING, date STRING, last_name STRING), 其中date是一个用来分区的列

| id | first_name | date | last_name |
| ---- | ------ | ------------------ | ------ |
| 1 | John | 2012-01-10 | Miller |
| 2 | Austin | 2012-02-22 | Powers |
| ... | ... | ... | ... |

创建分区表语句：

***CREATE EXTERNAL TABLE my_table (id INT, first_name STRING, last_name STRING) PARTITIONED BY (date STRING) LOCATION '/usr/hive/warehouse/my_table';***

当试图往表中插入数据(via INSERT OVERWRITE command)，并检查HDFS存储位置(/usr/hive/warehouse/my_table), 会发现数据存储在此目录中。一套分区数据一个目录，目录名类似date=2012-01-10 or date=2012-02-22，这些目录中包含所有你按选择目标格式存入的数据。分区列不与这些数据存储在一起，它是一个虚拟列，通过存储数据的分区目录来解析。

如果希望分区列也作为普通数据存储在文件目录中，你就需要staging table。

创建分区表语句：

***CREATE EXTERNAL TABLE my_table_staging (id INT, first_name STRING, date STRING, last_name STRING) LOCATION '/usr/hive/warehouse/my_table_staging';***

使用staging table作为数据源，通过Dynamic partitioning来产生分区表。步骤就是从staging table读出数据insert到partitioned table, 同时创建HDFS上合适的存储目录。


