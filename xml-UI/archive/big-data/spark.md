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

![Image of Run2](https://databricks.com/wp-content/uploads/2018/05/Apache-Spark-Streaming-ecosystem-diagram.png)

#### ʹ��
- **setup hive**

����[Hive](http://apache.claz.org/hive/)����ѹ���趨Ŀ¼��Copy hive/conf/hive-default.xml.template to hive/conf/hive-site.xml�޸������������ԡ�
```

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

```

- **create a table with partitions from existing files on Hadoop**



