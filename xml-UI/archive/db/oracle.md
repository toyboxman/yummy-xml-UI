### Oracle
- [20c 新特性](https://mp.weixin.qq.com/s/8jWVLY-hSbXhaDoPmADV6w)  
        `原生的区块链支持(Native Blockchain tables)`<br>
        `持久化内存存储(Persistent Memory Store)`
- [数据迁移工具expdp和impdp](https://mp.weixin.qq.com/s/XkuVG8yN1dNBwrwH-9dhKQ)
- [Mysql的double write机制在Oracle实现](https://mp.weixin.qq.com/s/1uqkVCeC57t07lsDl-8M7w)
- [Oracle到PostgreSQL最佳转换](https://mp.weixin.qq.com/s/hk8GwZgjvlqvouk1DHj5eQ)  
	`Oracle数据库迁移到Amazon RDS/Aurora环境下的PostgreSQL`
#### 数据库操作 
* 1.Start DB Instance
```console
a. su oracle --切换成Oracle用户
b. export env var --导出数据库环境,可以放入 .bashrc 文件
	e.g.
		export ORACLE_HOME=/home/oracle/app/oracle/product/11.2.0/server
		export ORACLE_SID=orcl
c. $ORACLE_HOME/bin/sqlplus /nolog --非登录使用sqlplus, 注意'/'后面无空格
		export PATH=$ORACLE_HOME/bin/sqlplus:$PATH --也可把sqlplus加入到PATH中
d. SQL>connect / as sysdba --作为dba用户连接
e. SQL>startup --启动db实例
```
* 2.Start Listener
```console
a. $ORACLE_HOME/bin/lsnrctl start [listenerName] --启动网络监听器[可指定listenerName]
```
* 3.Start Enterprise Manager(11g)
```console
a. $ORACLE_HOME/bin/emctl start dbconsole --启动web console
```
* 4.Stop DB Instance
```console
a. sqlplus / as sysdba --作为dba用户连接,注意'/'后面要有个空格
b. SQL>shutdown immediate
```
* 5.Stop Net Listener
```console
a. $ORACLE_HOME/bin/lsnrctl stop listenerName --停止网络监听器
```
* 6.Stop Enterprise Manager(11g)
```console
a. $ORACLE_HOME/bin/emctl stop dbconsole --停止web console
```
* 7.retrieve system tables
```console
SQL>select sys_context('USERENV','DB_NAME') as Instance from dual; --获取数据库实例名
SQL>select * from all_users; --获取所有用户名
SQL>select * from all_tables; --获取所有可访问的关系表
SQL>select * from dba_tablespaces; --获取所有表空间信息
```