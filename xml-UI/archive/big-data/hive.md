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

