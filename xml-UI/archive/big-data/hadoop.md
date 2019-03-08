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

#### 整体架构
![Image of Arch](https://opensource.com/sites/default/files/resize/images/life-uploads/hadoop-HighLevel_hadoop_architecture-640x460.png)<br>
- 架构细节参看[描述](https://opensource.com/life/14/8/intro-apache-hadoop-big-data)
- 简要教程参看[链接](https://data-flair.training/blogs/hadoop-tutorial/)

![Image of Arch](http://www.devx.com/imagesvr_ce/6661/HDFS%20Architecture.png)<br>
![Image of Arch](https://d2h0cx97tjks2p.cloudfront.net/blogs/wp-content/uploads/sites/2/2017/07/hadoop-hdfs-architecture-1.jpg)<br>

#### MapReduce
![Image of mr](https://www.researchgate.net/profile/Kamal_Al-Barznji/publication/321808354/figure/fig1/AS:571574165217285@1513285308576/Hadoop-Framework-211-HDFS-Hadoop-Distributed-File-System-HDFS-stands-for-Hadoop.png)<br>
![Image of mr](https://www.researchgate.net/profile/Ok-Kyoon_Ha/publication/270672980/figure/fig6/AS:328313378754574@1455287417640/Works-of-MapReduce-in-the-Hadoop-framework.png)<br>
![Image of mr](http://3.bp.blogspot.com/-FW44i28u6Ak/VRBpIoytlDI/AAAAAAAABxc/X37UT2XNmCw/s1600/CAYwTFFVEAE2onl.png-large.png)<br>
![Image of mr](https://rbpaonline.com/wp-content/uploads/2018/09/hadoop-map-reduce-architecture-example-728x543.jpg)<br>

#### 内部组件
![Image of Arch](http://ownself.me/wp-content/uploads/hadoop-cluster-architecture-and-core-components-5b12311e509c1.jpg)<br>

#### YARN架构
![Image of yarn](https://i.ytimg.com/vi/ZFbkNY6Xn94/maxresdefault.jpg)<br>
![Image of yarn](http://hadoop.apache.org/docs/current/hadoop-yarn/hadoop-yarn-site/yarn_architecture.gif)<br>
![Image of yarn](https://image.slidesharecdn.com/t-525p-230c-kambatla-140617151032-phpapp01/95/yarn-high-availability-3-638.jpg?cb=1403018043)<br>
NodeManager (NM)是运行在YARN节点上的agent, 负责管理本地的计算节点，包括与ResourceManager (RM)同步, containers生命周期管理 ，监控容器物理资源使用(memory, CPU), 节点健康状态, 日志管理和维系与其他YARN applications之间通讯的auxiliary services。

ResourceManager (RM)是管理节点，负责所有集群资源间仲裁，管理运行于YARN system之上的分布式应用。RM需要与管理计算节点的NodeManagers (NMs) 和管理应用的ApplicationMasters (AMs)协同工作。NM从RM上获取指令调度节点上可用资源，AM与ResourceManager协商资源并与NM协作启动containers.

#### 使用
- **命令行**

![Image of cmd](https://s3.amazonaws.com/files.dezyre.com/images/Tutorials/Hadoop+Online+Tutorial+%E2%80%93+Hadoop+HDFS+Commands+Guide/Hadoop+HDFS+Commands+Tutorial.png)<br>
基本命令可以参考[链接](https://www.dezyre.com/hadoop-tutorial/hadoop-hdfs-commands)

* **start/stop hadoop nodes**
```bash
# format name node
# NOTE: 格式化namenode时候如果之前已经存在datanode，格式化之后可能会造成
# 二者的VERSION文件中的cluster ID不一致，需要手动修改DataNode的值
# 与namenode的保持一致。否者data node启动将会失败。
/apache/hadoop/bin/hdfs namenode -format
# 启动所有节点
# NOTE: 启动之后用 'ps -ef|grep java' 检查是否namenode/secondary namenode/datanode全部成功
# 任何启动错误可以从/apache/hadoop/logs/中查看日志
/apache/hadoop/sbin/start-dfs.sh
# 停止所有节点
/apache/hadoop/sbin/stop-dfs.sh
```
成功启动之后可以通过http://127.0.0.1:50070/ 查看name-node信息，更多[端口信息](https://docs.hortonworks.com/HDPDocuments/HDP2/HDP-2.6.5/bk_reference/content/hdfs-ports.html)

还可以通过 http://127.0.0.1:8088/cluster 来查看hadoop cluster, hadoop daemons通过HTTP协议
例如 http://127.0.0.1:8088/stacks 暴露更多内部信息. 可参阅[blog](https://blog.cloudera.com/blog/2009/08/hadoop-default-ports-quick-reference/)

在HDFS上创建目录 `bin/hadoop dfs -mkdir /foodir` , 更多[命令](https://hadoop.apache.org/docs/current1/hdfs_design.html)
```
# list dfs上所有文件
$ hdfs dfs -ls -R /yarn-logs
drwxrwxrwt   - king king          0 2019-02-26 17:00 /yarn-logs/logs/application_1551229300465_0107/ubuntu_33233
drwxrwxrwt   - king king          0 2019-02-26 17:05 /yarn-logs/logs/application_1551229300465_0107/ubuntu_33234

# 查看文件内容
$ hdfs dfs -cat /yarn-logs/logs/king/logs/application_1551229300465_0107/ubuntu_33233

# 在dfs目录之间copy
$ hdfs dfs -cp /yarn-logs/logs/king/logs/application_1551229300465_0107/ubuntu_33233 /yarn-logs

# 从dfs目录中copy到本地
$ hdfs dfs -copyToLocal /yarn-logs/logs/king/logs/application_1551229300465_0107/ubuntu_33233 ./
```
