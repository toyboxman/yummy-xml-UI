### Hive
[***Apache Hive***](https://en.wikipedia.org/wiki/Apache_Hive)�����ݲֿ�ϵͳ��������Apache Hadoop�����ϣ����ṩ���ݲ�ѯ�ͷ������ܡ�Hive�ṩSQL-like ��ʽ��ѯ�洢�ڴ�ͳ���ݿ���Hadoop���ɵ��ļ�ϵͳ�е����ݡ�һ�������ֲ�ʽ���ݲ�ѯ����Ҫͨ��MapReduce Java API��ִ��SQL��Ȼ��Hive�ṩ��SQL�������HiveQL���ɵ��ײ�Javaʵ���У�Ӧ�ò���Ҫ�Լ�ʵ�ֵײ�Java API�����ڴ�������е����ݲֿ�Ӧ�ö�ʹ��SQL-based��ѯ����ʵ��, Hive���԰�����ֲ��ͳSQLӦ�õ�Hadoop��

#### �ܹ�
![Image of Arch](https://cdn.intellipaat.com/blog/wp-content/uploads/2016/12/Architecture-of-Apache-Hive.jpg)<br>
- HiveServer2(HS2)����clientsִ��Hive��ѯ��Ӧ�ã���HiveServer1�������������Thrift-based Hive service��HS2֧��multi-client��������֤����JDBC/ODBC���ɸ��á��ο�[HS2] (https://cwiki.apache.org/confluence/display/Hive/HiveServer2+Overview)
- Hive Service����������client node�ϵ�daemon���̣�ͨ��thrift protocols����ѯ���͵�HS2
- Hive metastore service��Hive tables��partitions��Щmetadata�����ڹ�ϵ���ݿ���, ���ṩmetastore service API��������Щ��Ϣ��metastore service����������ģʽ[embedded/local/remote](https://www.cloudera.com/documentation/enterprise/5-8-x/topics/cdh_ig_hive_metastore_configure.html)
- Thrift��RPC framework��������cross-platform�����书��ջ����4��: Server, Transport, Protocol, and Processor. ϸ�ڿ��Բο�[concepts](https://thrift.apache.org/docs/concepts)

#### ����
![Image of Arch](https://image.slidesharecdn.com/integrationofapachehiveandhbasefinal-120504182337-phpapp01/95/integration-of-hive-and-hbase-34-728.jpg?cb=1336156004)<br>

- Usage:
����ѯhive tables��database, ��ѯ������Զ���hive service��hive server֮�佻������Ҫ������service��project������ʹ��thrift protocols����Э�����ʵ�ֿ����Ե�web-service��

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
����metastore service ��`/apache/hive/bin/hive --service metastore`

��¼hive CLI Client��`/apache/hive/bin/hive` or `/apache/hive/bin/hive --database default`

CLI: hive> SHOW TABLES;

�ο��������ĵ�[hive CLI](https://cwiki.apache.org/confluence/display/Hive/GettingStarted#GettingStarted-RunningHiveCLI), [CLI manual](
https://cwiki.apache.org/confluence/display/Hive/LanguageManual+Cli)

hive> select * from table;

Hive SQL �﷨���Բο�[hive SQL](https://hortonworks.com/blog/hive-cheat-sheet-for-sql-users/)

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

