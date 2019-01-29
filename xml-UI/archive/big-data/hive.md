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

