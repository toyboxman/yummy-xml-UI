### Hive
[***Apache Hive***](https://en.wikipedia.org/wiki/Apache_Hive)是数据仓库系统，构建在Apache Hadoop基础上，能提供数据查询和分析功能。Hive提供SQL-like 方式查询存储在传统数据库与Hadoop集成的文件系统中的数据。一般这样分布式数据查询必须要通过MapReduce Java API来执行SQL，然而Hive提供了SQL层抽象与HiveQL集成到底层Java实现中，应用不需要自己实现底层Java API。由于大多数已有的数据仓库应用都使用SQL-based查询语言实现, Hive可以帮助移植传统SQL应用到Hadoop。

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

登录hive CLI Client：`/apache/hive/bin/hive` or `/apache/hive/bin/hive --database default`

CLI: hive> SHOW TABLES;

参考命令行文档[hive CLI](https://cwiki.apache.org/confluence/display/Hive/GettingStarted#GettingStarted-RunningHiveCLI), [CLI manual](
https://cwiki.apache.org/confluence/display/Hive/LanguageManual+Cli)

hive> select * from table;

Hive SQL 语法可以参考[hive SQL](https://hortonworks.com/blog/hive-cheat-sheet-for-sql-users/)

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

