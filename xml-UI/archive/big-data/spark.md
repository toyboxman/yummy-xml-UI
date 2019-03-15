### Spark
[***Apache Spark***](https://en.wikipedia.org/wiki/Apache_Spark)是通用分布式集群计算框架，提供一个隐含数据并行和容错的接口，来对整个集群编程。Spark架构基于resilient distributed dataset (RDD), 一种只读的分布在多个集群机器上的数据多重集合，这些集合通过fault-tolerant机制维护。Dataframe API是RDD层之上的抽象接口, Dataset API遵从这些接口。Spark 1.x版本中RDD是主要的编程接口, 但Spark 2.x之后推荐使用Dataset API，但RDD技术仍旧是其底层依赖。

#### 架构
![Image of Arch](https://cdn.intellipaat.com/blog/wp-content/uploads/2016/12/Architecture-of-Apache-Hive.jpg)<br>
- HiveServer2(HS2)是让clients执行Hive查询的应用，是HiveServer1的替代，核心是Thrift-based Hive service。HS2支持multi-client并发和认证，与JDBC/ODBC集成更好。参看[HS2] (https://cwiki.apache.org/confluence/display/Hive/HiveServer2+Overview)
- Hive Service就是运行于client node上的daemon进程，通过thrift protocols将查询发送到HS2
- Hive metastore service将Hive tables和partitions这些metadata保存在关系数据库中, 并提供metastore service API来访问这些信息。metastore service有三种配置模式[embedded/local/remote](https://www.cloudera.com/documentation/enterprise/5-8-x/topics/cdh_ig_hive_metastore_configure.html)
- Thrift是RPC framework用来构建cross-platform服务，其功能栈包括4层: Server, Transport, Protocol, and Processor. 细节可以参看[concepts](https://thrift.apache.org/docs/concepts)

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
     <description>Thrift URI for the remote metastore.</description>
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
       To use SSL to encrypt/authenticate the connection, provide database-specific SSL flag in connection URL.
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
   </property>
   <property>
```
启动metastore service ：`/apache/hive/bin/hive --service metastore`

登录hive CLI Client：`/apache/hive/bin/hive` or `/apache/hive/bin/hive --database default`

CLI: hive> SHOW TABLES;

参考命令行文档[hive CLI](https://cwiki.apache.org/confluence/display/Hive/GettingStarted#GettingStarted-RunningHiveCLI), [CLI manual](
https://cwiki.apache.org/confluence/display/Hive/LanguageManual+Cli)

hive> select * from table;

Hive SQL 语法可以参考[hive SQL](https://hortonworks.com/blog/hive-cheat-sheet-for-sql-users/)

Hive JDBC 访问与对应SQL参考 [DDL](https://www.tutorialspoint.com/hive/hive_drop_table.htm)
```
hive> describe demo_src;
OK
id                  	bigint              	                    
age                 	int                 	                    
desc                	string              	                    
dt                  	string              	                    
hour                	string              	                    
	 	 
# Partition Information	 	 
# col_name            	data_type           	comment             
	 	 
dt                  	string              	                    
hour                	string              	                    
Time taken: 0.537 seconds, Fetched: 11 row(s)

hive> set hive.cli.print.header=true;
hive> select * from demo_src limit 10;
hive> set hive.cli.print.header=false;

hive> show create table demo_src;
OK
CREATE EXTERNAL TABLE `demo_src`(
  `id` bigint, 
  `age` int, 
  `desc` string)
PARTITIONED BY ( 
  `dt` string, 
  `hour` string)
ROW FORMAT SERDE 
  'org.apache.hadoop.hive.serde2.lazy.LazySimpleSerDe' 
WITH SERDEPROPERTIES ( 
  'field.delim'='|', 
  'serialization.format'='|') 
STORED AS INPUTFORMAT 
  'org.apache.hadoop.mapred.TextInputFormat' 
OUTPUTFORMAT 
  'org.apache.hadoop.hive.ql.io.HiveIgnoreKeyTextOutputFormat'
LOCATION
  'hdfs://127.0.0.1:9000/griffin/data/batch/demo_src'
TBLPROPERTIES (
  'transient_lastDdlTime'='1551168613')
Time taken: 3.762 seconds, Fetched: 20 row(s)
```

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

Hive服务的端口信息可查看[端口表](https://docs.hortonworks.com/HDPDocuments/HDP2/HDP-2.6.5/bk_reference/content/hive-ports.html)

