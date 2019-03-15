### Spark
[***Apache Spark***](https://en.wikipedia.org/wiki/Apache_Spark)是通用分布式集群计算框架，提供一个隐含数据并行和容错的接口，来对整个集群编程。Spark架构基于resilient distributed dataset (RDD), 一种只读的分布在多个集群机器上的数据多重集合，这些集合通过fault-tolerant机制维护。Dataframe API是RDD层之上的抽象接口, Dataset API遵从这些接口。Spark 1.x版本中RDD是主要的编程接口, 但Spark 2.x之后推荐使用Dataset API，但RDD技术仍旧是其底层依赖。

#### 架构
![Image of Stack](https://d1.awsstatic.com/Data%20Lake/what-is-apache-spark.b3a3099296936df595d9a7d3610f1a77ff0749df.PNG)

![Image of Stack1](https://i2.wp.com/www.jenunderwood.com/wp-content/uploads/2016/10/SparkArchitecture-Databrickss.gif?ssl=1)

![Image of Arch](https://tekclasses.com/wp-content/uploads/2017/06/WHAT-IS-APACHE-SPARK-_-WHY-YOU-SHOULD-LEARN-IT-NOW.png)

#### 部署
![Image of Deploy](https://azurecomcdn.azureedge.net/mediahandler/acomblog/media/Default/blog/97bc4145-21de-47f4-b1ef-12bd4635c47a.png)

![Image of Deploy1](https://docs.microsoft.com/en-us/azure/cosmos-db/media/lambda-architecture/lambda-architecture-re-architected.png)

#### 运行
![Image of Run](http://aptuz.com/static/media/uploads/blog/hadoop_echosystem.png)

![Image of Run1](https://sigmoid.com/wp-content/uploads/2015/03/Apache_Spark1.png)

![Image of Run2](https://databricks.com/wp-content/uploads/2018/05/Apache-Spark-Streaming-ecosystem-diagram.png)

#### 使用
- **setup hive**

下载[Hive](http://apache.claz.org/hive/)，解压到设定目录。Copy hive/conf/hive-default.xml.template to hive/conf/hive-site.xml修改以下配置属性。
```

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

```

- **create a table with partitions from existing files on Hadoop**



