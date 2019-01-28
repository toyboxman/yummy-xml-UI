### Hive
[***Apache Hive***](https://en.wikipedia.org/wiki/Apache_Hive)是数据仓库系统，构建在Apache Hadoop基础上，能提供数据查询和分析功能。Hive提供SQL-like 方式查询存储在传统数据库与Hadoop集成的文件系统中的数据。一般这样分布式数据查询必须要通过MapReduce Java API来执行SQL，然而Hive提供了SQL层抽象与HiveQL集成到底层Java实现中，应用不需要自己实现底层Java API。由于大多数已有的数据仓库应用都使用SQL-based查询语言实现, Hive可以帮助移植传统SQL应用到Hadoop。

#### 架构
![Image of Arch](https://cdn.intellipaat.com/blog/wp-content/uploads/2016/12/Architecture-of-Apache-Hive.jpg)<br>
Hive metastore service将Hive tables和partitions这些metadata保存在关系数据库中, 并提供metastore service API来访问这些信息。metastore service有三种配置模式[embedded/local/remote](https://www.cloudera.com/documentation/enterprise/5-8-x/topics/cdh_ig_hive_metastore_configure.html)

HiveServer2 (HS2) is a service that enables clients to execute queries against Hive. HiveServer2 is the successor to HiveServer1 which has been deprecated. HS2 supports multi-client concurrency and authentication. It is designed to provide better support for open API clients like JDBC and ODBC. You can find more details about hiveserver at https://cwiki.apache.org/confluence/display/Hive/HiveServer2+Overview

Hive Service is nothing but daemon which runs on your client node which sends requests to Hive Server.

Thrift is an RPC framework for building cross-platform services. Its stack consists of 4 layers: Server, Transport, Protocol, and Processor. You can find more details about the layers at https://thrift.apache.org/docs/concepts.

Relation between all these:

The Thrift-based Hive service is the core of HS2 and responsible for servicing the Hive queries (e.g., from Beeline). In simple terms Hive server is based on thrift protocols which sends queries from hive client i.e., your command line interface or from HUE interface to the underlying data which can be in your HDFS or any other data sources.
https://image.slidesharecdn.com/integrationofapachehiveandhbasefinal-120504182337-phpapp01/95/integration-of-hive-and-hbase-34-728.jpg?cb=1336156004

Usage:

When you query any hive tables or database, in background automatically your requests is transferred between hive service and hive server
when you want to create your own service or project you can use thrift protocols which will help you in creating layers, think this as you are creating your user defined functions using libraries, so in that case libraries will be thrift.

What is Apache Thrift: It is framework for scalable for cross-language service development.

When we can use Apache Thrift: Developing web-service that uses service developed in one language access that is in another language.

What is HiveServer : It is a service that allows a remote client to submit requests to hive. Using a variety of programming languages, and retrieve results.

create a table with partitions from existing files on Hadoop. The datevalue on which I need to partition is available in the files, but the datevalue column position is not last. It is in the middle. How can I create the table for the same?
Here is the sample:

1  John    2012-01-10 Miller  
2  Austin  2012-02-22 Powers

 you want to have a partitioned hive table with three columns (id INT, fname STRING, dt STRING, lname STRING) where id, fname, lname are columns that store an integer id, string first name and a string last name respecitvely and dt is a partition column of type string that contains date in yyyy-MM-dd format. To create a table like this you would issue a command like:

CREATE EXTERNAL TABLE my_table (id INT, fname STRING, lname STRING)
PARTITIONED BY (dt STRING)
LOCATION '/usr/hive/warehouse/my_table';

When you insert data into this table (via INSERT OVERWRITE command, say) and go check the HDFS location (/usr/hive/warehouse/my_table), you would find that the data is stored in directories; one directory per partition. The name of the directory would be something like dt=2012-01-01 or dt=2012-02-22. Inside these directories would be your actual data in whatever format you had selected it to be stored in. The partition column is not stored with this data; it is a virtual column that is deciphered from the partition directory your data is present in.

Now let's get to your question. Since partitioning column is a virtual column, you can not put a partitioned Hive table on top of your data as it is (regardless of whether your to-be-partitioning column is present in the middle of the file or at the end). You need the appropriate directory structure to be present in HDFS for partitioning to work. You would want to create a staging table that is not partitioned.

CREATE EXTERNAL TABLE my_table_staging (id INT, fname STRING, dt STRING, lname STRING)
LOCATION '/usr/hive/warehouse/my_table_staging';

Then use this staging table as a source to populate your partitioned table using Dynamic partitioning. You can use a command like the one below for this:

INSERT OVERWRITE TABLE my_table PARTITION (dt)
SELECT id, fname, lname, dt FROM my_table_staging;

This command will read the data from your staging table and insert it into the partitioned table, creating the appropriate directory structure for you on the HDFS.

