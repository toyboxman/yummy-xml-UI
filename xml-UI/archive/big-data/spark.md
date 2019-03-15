### Spark
[***Apache Spark***](https://en.wikipedia.org/wiki/Apache_Spark)��ͨ�÷ֲ�ʽ��Ⱥ�����ܣ��ṩһ���������ݲ��к��ݴ�Ľӿڣ�����������Ⱥ��̡�Spark�ܹ�����resilient distributed dataset (RDD), һ��ֻ���ķֲ��ڶ����Ⱥ�����ϵ����ݶ��ؼ��ϣ���Щ����ͨ��fault-tolerant����ά����Dataframe API��RDD��֮�ϵĳ���ӿ�, Dataset API�����Щ�ӿڡ�Spark 1.x�汾��RDD����Ҫ�ı�̽ӿ�, ��Spark 2.x֮���Ƽ�ʹ��Dataset API����RDD�����Ծ�����ײ�������

#### �ܹ�
![Image of Stack](https://d1.awsstatic.com/Data%20Lake/what-is-apache-spark.b3a3099296936df595d9a7d3610f1a77ff0749df.PNG)

![Image of Stack1](https://i2.wp.com/www.jenunderwood.com/wp-content/uploads/2016/10/SparkArchitecture-Databrickss.gif?ssl=1)

![Image of Arch](https://tekclasses.com/wp-content/uploads/2017/06/WHAT-IS-APACHE-SPARK-_-WHY-YOU-SHOULD-LEARN-IT-NOW.png)

#### ����
![Image of Deploy](https://azurecomcdn.azureedge.net/mediahandler/acomblog/media/Default/blog/97bc4145-21de-47f4-b1ef-12bd4635c47a.png)

![Image of Deploy1](https://docs.microsoft.com/en-us/azure/cosmos-db/media/lambda-architecture/lambda-architecture-re-architected.png)

#### ����
![Image of Run](http://aptuz.com/static/media/uploads/blog/hadoop_echosystem.png)

![Image of Run1](https://sigmoid.com/wp-content/uploads/2015/03/Apache_Spark1.png)

#### ʹ��
- **setup hive**

����[Hive](http://apache.claz.org/hive/)����ѹ���趨Ŀ¼��Copy hive/conf/hive-default.xml.template to hive/conf/hive-site.xml�޸������������ԡ�
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
����metastore service ��`/apache/hive/bin/hive --service metastore`

��¼hive CLI Client��`/apache/hive/bin/hive` or `/apache/hive/bin/hive --database default`

CLI: hive> SHOW TABLES;

�ο��������ĵ�[hive CLI](https://cwiki.apache.org/confluence/display/Hive/GettingStarted#GettingStarted-RunningHiveCLI), [CLI manual](
https://cwiki.apache.org/confluence/display/Hive/LanguageManual+Cli)

hive> select * from table;

Hive SQL �﷨���Բο�[hive SQL](https://hortonworks.com/blog/hive-cheat-sheet-for-sql-users/)

Hive JDBC �������ӦSQL�ο� [DDL](https://www.tutorialspoint.com/hive/hive_drop_table.htm)
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

ʾ��:

������������(id INT, first_name STRING, date STRING, last_name STRING), ����date��һ��������������

| id | first_name | date | last_name |
| ---- | ------ | ------------------ | ------ |
| 1 | John | 2012-01-10 | Miller |
| 2 | Austin | 2012-02-22 | Powers |
| ... | ... | ... | ... |

������������䣺

***CREATE EXTERNAL TABLE my_table (id INT, first_name STRING, last_name STRING) PARTITIONED BY (date STRING) LOCATION '/usr/hive/warehouse/my_table';***

����ͼ�����в�������(via INSERT OVERWRITE command)�������HDFS�洢λ��(/usr/hive/warehouse/my_table), �ᷢ�����ݴ洢�ڴ�Ŀ¼�С�һ�׷�������һ��Ŀ¼��Ŀ¼������date=2012-01-10 or date=2012-02-22����ЩĿ¼�а��������㰴ѡ��Ŀ���ʽ��������ݡ������в�����Щ���ݴ洢��һ������һ�������У�ͨ���洢���ݵķ���Ŀ¼��������

���ϣ��������Ҳ��Ϊ��ͨ���ݴ洢���ļ�Ŀ¼�У������Ҫstaging table��

������������䣺

***CREATE EXTERNAL TABLE my_table_staging (id INT, first_name STRING, date STRING, last_name STRING) LOCATION '/usr/hive/warehouse/my_table_staging';***

ʹ��staging table��Ϊ����Դ��ͨ��Dynamic partitioning������������������Ǵ�staging table��������insert��partitioned table, ͬʱ����HDFS�Ϻ��ʵĴ洢Ŀ¼��

Hive����Ķ˿���Ϣ�ɲ鿴[�˿ڱ�](https://docs.hortonworks.com/HDPDocuments/HDP2/HDP-2.6.5/bk_reference/content/hive-ports.html)

