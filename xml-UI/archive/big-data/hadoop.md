### Hadoop
[***Apache Hadoop***](https://en.wikipedia.org/wiki/Apache_Hadoop)��һ��open-source�Ĵ����ݹ��߼���ͨ����������������Ⱥ�����д����ݵļ��㹤�������������storage����ΪHadoop Distributed File System (HDFS), ���㴦����ΪMapReduce programming model��Hadoop�Ѽ���Ŀ���ļ��ָ�ɶ��block���ֲ��ڼ�Ⱥ�нڵ��ϣ�Ȼ�󽫼��������ϴ��������ڵ㣬�ڸ����ڵ�֮�Ͻ��в��м��㡣��������ѧϰ�˳���ļܹ�������ȳ�����Ҫ���ø�������Ͳ����ļ�ϵͳ������˵�����ڱ������ݼ���������Ч�Ϳ��١�<br>
Apache Hadoop framework������һЩģ�����:
- Hadoop Common �C contains libraries and utilities needed by other Hadoop modules
- Hadoop Distributed File System (HDFS) �C a distributed file-system that stores data on commodity machines
- Hadoop YARN �C a platform responsible for managing computing resources in clusters and using them for scheduling users' applications
- Hadoop MapReduce �C an implementation of the MapReduce programming model for large-scale data processing.

�����Hadoop������������Ӧ��ģ�����ڴ�������̬ϵͳ������ Apache Pig, Apache Hive, Apache HBase, Apache Phoenix, Apache Spark, Apache ZooKeeper, Cloudera Impala, Apache Flume, Apache Sqoop, Apache Oozie, and Apache Storm 

#### ��̬ϵͳ
![Image of Arch](https://c2programming.files.wordpress.com/2016/05/scaling-up-with-hadoop-and-banyan-hadoop-family.png)<br>
- ������Hadoopϵͳ�����˶����ϵͳ������������롢������洢����ѯ��չʾ���ֹ���
![Image of ecosystem](https://2.bp.blogspot.com/-w7KeAnwWnBQ/WfYBJzgtvQI/AAAAAAAAAMk/D58SpZfK7lkJ8QnKnQZW268mKzRvuOOnACLcBGAs/s1600/HadoopStack.png)<br>
- �����������������֮������[ecosystem](http://blog.newtechways.com/2017/10/apache-hadoop-ecosystem.html)���ӷḻ
![Image of map](http://bigdataanalyticsnews.com/wp-content/uploads/2014/02/hadoop_map1.png)

#### ����ܹ�
![Image of Arch](https://opensource.com/sites/default/files/resize/images/life-uploads/hadoop-HighLevel_hadoop_architecture-640x460.png)<br>
- �ܹ�ϸ�ڲο�[����](https://opensource.com/life/14/8/intro-apache-hadoop-big-data)
- ��Ҫ�̳̲ο�[����](https://data-flair.training/blogs/hadoop-tutorial/)

![Image of Arch](http://www.devx.com/imagesvr_ce/6661/HDFS%20Architecture.png)<br>
![Image of Arch](https://d2h0cx97tjks2p.cloudfront.net/blogs/wp-content/uploads/sites/2/2017/07/hadoop-hdfs-architecture-1.jpg)<br>

#### MapReduce
![Image of mr](https://www.researchgate.net/profile/Kamal_Al-Barznji/publication/321808354/figure/fig1/AS:571574165217285@1513285308576/Hadoop-Framework-211-HDFS-Hadoop-Distributed-File-System-HDFS-stands-for-Hadoop.png)<br>
![Image of mr](https://www.researchgate.net/profile/Ok-Kyoon_Ha/publication/270672980/figure/fig6/AS:328313378754574@1455287417640/Works-of-MapReduce-in-the-Hadoop-framework.png)<br>
![Image of mr](http://3.bp.blogspot.com/-FW44i28u6Ak/VRBpIoytlDI/AAAAAAAABxc/X37UT2XNmCw/s1600/CAYwTFFVEAE2onl.png-large.png)<br>
![Image of mr](https://rbpaonline.com/wp-content/uploads/2018/09/hadoop-map-reduce-architecture-example-728x543.jpg)<br>

#### �ڲ����
![Image of Arch](http://ownself.me/wp-content/uploads/hadoop-cluster-architecture-and-core-components-5b12311e509c1.jpg)<br>

#### YARN�ܹ�
![Image of yarn](https://i.ytimg.com/vi/ZFbkNY6Xn94/maxresdefault.jpg)<br>
![Image of yarn](http://hadoop.apache.org/docs/current/hadoop-yarn/hadoop-yarn-site/yarn_architecture.gif)<br>
![Image of yarn](https://image.slidesharecdn.com/t-525p-230c-kambatla-140617151032-phpapp01/95/yarn-high-availability-3-638.jpg?cb=1403018043)<br>
NodeManager (NM)��������YARN�ڵ��ϵ�agent, ��������صļ���ڵ㣬������ResourceManager (RM)ͬ��, containers�������ڹ��� ���������������Դʹ��(memory, CPU), �ڵ㽡��״̬, ��־�����άϵ������YARN applications֮��ͨѶ��auxiliary services��

ResourceManager (RM)�ǹ���ڵ㣬�������м�Ⱥ��Դ���ٲã�����������YARN system֮�ϵķֲ�ʽӦ�á�RM��Ҫ��������ڵ��NodeManagers (NMs) �͹���Ӧ�õ�ApplicationMasters (AMs)Эͬ������NM��RM�ϻ�ȡָ����Ƚڵ��Ͽ�����Դ��AM��ResourceManagerЭ����Դ����NMЭ������containers.

#### ʹ��
- **������**

![Image of cmd](https://s3.amazonaws.com/files.dezyre.com/images/Tutorials/Hadoop+Online+Tutorial+%E2%80%93+Hadoop+HDFS+Commands+Guide/Hadoop+HDFS+Commands+Tutorial.png)<br>
����������Բο�[����](https://www.dezyre.com/hadoop-tutorial/hadoop-hdfs-commands)

* **start/stop hadoop nodes**
```bash
# format name node
# NOTE: ��ʽ��namenodeʱ�����֮ǰ�Ѿ�����datanode����ʽ��֮����ܻ����
# ���ߵ�VERSION�ļ��е�cluster ID��һ�£���Ҫ�ֶ��޸�DataNode��ֵ
# ��namenode�ı���һ�¡�����data node��������ʧ�ܡ�
/apache/hadoop/bin/hdfs namenode -format
# �������нڵ�
# NOTE: ����֮���� 'ps -ef|grep java' ����Ƿ�namenode/secondary namenode/datanodeȫ���ɹ�
# �κ�����������Դ�/apache/hadoop/logs/�в鿴��־
/apache/hadoop/sbin/start-dfs.sh
# ֹͣ���нڵ�
/apache/hadoop/sbin/stop-dfs.sh
```
�ɹ�����֮�����ͨ��http://127.0.0.1:50070/ �鿴name-node��Ϣ������[�˿���Ϣ](https://docs.hortonworks.com/HDPDocuments/HDP2/HDP-2.6.5/bk_reference/content/hdfs-ports.html)

������ͨ�� http://127.0.0.1:8088/cluster ���鿴hadoop cluster, hadoop daemonsͨ��HTTPЭ��
���� http://127.0.0.1:8088/stacks ��¶�����ڲ���Ϣ. �ɲ���[blog](https://blog.cloudera.com/blog/2009/08/hadoop-default-ports-quick-reference/)

��HDFS�ϴ���Ŀ¼ `bin/hadoop dfs -mkdir /foodir` , ����[����](https://hadoop.apache.org/docs/current1/hdfs_design.html)
```
# list dfs�������ļ�
$ hdfs dfs -ls -R /yarn-logs
drwxrwxrwt   - king king          0 2019-02-26 17:00 /yarn-logs/logs/application_1551229300465_0107/ubuntu_33233
drwxrwxrwt   - king king          0 2019-02-26 17:05 /yarn-logs/logs/application_1551229300465_0107/ubuntu_33234

# �鿴�ļ�����
$ hdfs dfs -cat /yarn-logs/logs/king/logs/application_1551229300465_0107/ubuntu_33233

# ��dfsĿ¼֮��copy
$ hdfs dfs -cp /yarn-logs/logs/king/logs/application_1551229300465_0107/ubuntu_33233 /yarn-logs

# ��dfsĿ¼��copy������
$ hdfs dfs -copyToLocal /yarn-logs/logs/king/logs/application_1551229300465_0107/ubuntu_33233 ./
```
