### MySQL
- [MySQL入门](https://mp.weixin.qq.com/s/_BdT8Oi6XwEjsbY_rPLd5w)  
    `mysql安装/mysql操作`
    - [MySQL命令学习笔记](https://mp.weixin.qq.com/s/pxTZOph5-2v6YTxxeA_kOg)
    - [MySQL 优化19条](https://mp.weixin.qq.com/s/IjKVF4yuJjf4TFsAxP3IoA)  
    `EXPLAIN/IN包含的值不应过多/使用limit 1/union all代替union...`
    - [MySQL8.0运维便捷命令](https://mp.weixin.qq.com/s/OZub4GiQSRjlAfDf59U3KA)  
        `RESTART/PERSIST/RETAIN CURRENT PASSWORD/EXPLAIN/SET_VAR`
    - [MySQL EXPLAIN结果集分析](https://mp.weixin.qq.com/s/p5UKuh1yY3P4zrOzVBmY1w)
    - [巧用MySQL Union](https://mp.weixin.qq.com/s/reNc5LBKhSxGfCYyc_W8XQ)  
        `允许字段的值为null，往往会引发灾难`<br>
        `尽可能用union代替or`
    - [MySQL Router高可用](https://mp.weixin.qq.com/s/WCx_ok-ajUgL4ICETXgVAA)
        `MySQL Router是mysql-proxy的一个替代品`
    - [MySQL分布式事务XA Transactions](https://mp.weixin.qq.com/s/24m9mD8JpHaPVb7lV9Gwvw)  
        `分布式事务的语法`
    - [MySQL CPU性能定位](https://mp.weixin.qq.com/s/zl9YCdW1ZXYlotTBN-DNLA)
        `CPU起步8核开始SSD硬盘/用Memcache或者Redis缓存技术，尽量从缓存中获取常用的查询结果`
    - [别在MySQL中使用UTF-8](https://mp.weixin.qq.com/s/vI49TqUcMX0qm5CNVfuZrA)  
        `MySQL把utf8改成utf8mb4`
- [MySQL重要知识点](https://mp.weixin.qq.com/s/4l_0kriTW82V9Gkd6TVdEg)  
    `事物的四大特性(ACID)/索引相关/存储引擎/乐观锁与悲观锁的区别/锁机制与InnoDB锁算法/大表优化`
- [MySQL使用规范](https://mp.weixin.qq.com/s/yMo17C3AyD2ZkXwD33NtZQ)  
    `命令规范/设计规范/字段设计规范/索引设计规范/SQL开发规范/操作行为规范`
- [鸟瞰MySQL](https://mp.weixin.qq.com/s/yuEW2CYDmkLX695Bd2sgtw)  
    `MySQL架构/MySQL日志/MySQL的MVCC/MySQL索引/语法分析及优化/分布式事务/执行计划和慢查询日志`
- [MySQL互联网常用架构方案](https://mp.weixin.qq.com/s/700PSsvKpgZa63HlaNAOcQ)  
    `主备架构/双主架构/主从架构/双主+主从架构`<br>
    `主库和从库一致性解决方案/DB和缓存一致性解决方案`
- [实现MySQL每秒57万的写入](https://mp.weixin.qq.com/s/nky5AOuQhgj60ljTxlV_tw)  
    `TokuDB写入MySQL单表20亿`
- [MySQL8 vs PostgreSQL10](https://mp.weixin.qq.com/s/njIfc8TxB77XbYJaPtdLJA)

#### 安装
在Linux上使用mysql数据库,可以使用yum(redhat) apt(ubuntu) zypper(suse)
```
# client package
yum install mysql

# server package
yum install mysql_server
     
# 安装完毕查看状态 
service mysqld status
```
#### 连接
```
# mysql只有一个默认的root用户,密码为空,只允许localhost连接
mysql -h localhost -u root
# 如果设置过密码就需要使用-p指定密码
mysql -h localhost -u root -ppassword

# 在Ubuntu系统上root用户被禁用，因此需要通过sudo连接
sudo mysql
```
JDBC URI
```
# 在使用数据库连接池的情况下，最好设置如下两个参数：
# autoReconnect=true&failOverReadOnly=false
jdbc:mysql://localhost:3306/test?user=root&password=&useUnicode=true&characterEncoding=utf8&autoReconnect=true&failOverReadOnly=false
```
需要注意的是，在应用xml配置文件中，jdbc url中的&符号需要转义。比如在tomcat的server.xml中配置数据库连接池时，mysql jdbc url样例如下：
```
jdbc:mysql://localhost:3306/test?user=root&amp;password=&amp;useUnicode=true&amp;characterEncoding=utf8&amp;autoReconnect=true&amp;failOverReadOnly
```
#### 数据库操作     
如果用root用户安装后，需要修改mysql登录密码
```
# 1.先停止mysqld服务 
# ubuntu上服务名是 service mysql status
service mysqld stop
# 2.启动安全模式
# sudo mysqld_safe --skip-grant-tables --skip-networking &
/usr/bin/mysqld_safe --skip-grant-tables
# 3.通过mysql客户端登入 
# 查看表结构 desc mysql.user;
# 新版本mysql.user table 'password' column name改成'authentication_string'
# 查看表数据 select host, user, authentication_string from user; 
# 清空密码 UPDATE mysql.user SET authentication_string=PASSWORD('') WHERE User='root';
$ mysql              
mysql> UPDATE mysql.user SET Password=PASSWORD('MyNewPass') WHERE User='root';
mysql> FLUSH PRIVILEGES;
mysql> quit
# 4.重启
service mysqld restart
# 5.使用密码登录
mysql -u root -pMyNewPass dbname
mysql> use dbname
```
MySQL新旧版本认证方式有变化，因此上面修改密码方式可能需要修改
```
# root@localhost用户认证plugin默认是auth_socket不是mysql_native_password
mysql> select host, user, authentication_string, plugin from mysql.user\G;
*************************** 1. row ***************************
                 host: localhost
                 user: root
authentication_string: *23AE809DDACAF96AF0FD78ED04B6A265E05AA257
               plugin: auth_socket
*************************** 2. row ***************************
                 host: localhost
                 user: mysql.session
authentication_string: *THISISNOTAVALIDPASSWORDTHATCANBEUSEDHERE
               plugin: mysql_native_password

# 修改认证方式和密码
# CREATE USER 'valerie'@'localhost' IDENTIFIED WITH auth_socket;
# ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY '123456';
mysql> ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password;
mysql> ALTER USER 'root'@'localhost' IDENTIFIED BY '123456';
mysql> FLUSH PRIVILEGES;
```
通过mysqlmanager可以创建用户, 通过mysqladmin可以创建新数据库实例
<br>
客户端工具：***SQuirreL SQL Client***,   ***SQL Power Architect***
#### 数据库配置
默认情况下，远程无法连接到数据库服务器，有以下原因
<br>1.防火墙没有关闭 通过 service iptables status 来查看防火墙状态
<br>2.端口(3306)被占用 通过 netstat -tlnpu 查看mysqld 端口状态
<br>3.数据库中mysql实例的“user”表里的“host”项只允许本地地址  

     1.改表法, 修改host值(以通配符%的内容增加主机/IP地址),或直接增加IP地址"localhost"改称'%'
       mysql>update user set host = '%' where user = 'root';
       mysql>select host, user from user;  
     2. 授权法
      使myuser用mypassword从任何主机连接到mysql服务器
      GRANT ALL PRIVILEGES ON *.* TO 'myuser'@'%' IDENTIFIED BY 'mypassword' WITH GRANT OPTION;
      使myuser从ip为192.168.1.3的主机连接到mysql服务器
      GRANT ALL PRIVILEGES ON *.* TO 'myuser'@'192.168.1.3' IDENTIFIED BY 'mypassword' WITH GRANT OPTION;
	  
如果myuser@%无法通过localhost登录数据库
```
root@controller:~# mysql -h localhost -u myuser -ppassword
ERROR 1045 (28000): Access denied for user 'keystone'@'localhost' (using password: YES)

mysql> use mysql;
mysql> delete from user where user='';
mysql> flush privileges;
```
意思是删除匿名用户，然后就可以从localhost登录
<br>
##### 安全设置
用户不应该只用root用户进行连接数据库,虽然使用root用户进行测试时很方便,但会给系统带来重大安全隐患.
<br>我们给一个应用中使用的用户赋予最恰当的数据库权限。如一个只进行数据插入的用户不应赋予其删除数据的权限.
<br>MySql的用户管理是通过 User表来实现的,添加新用户常用的方法有两个
<br>一.是在User表插入相应的数据行,同时设置相应的权限；创建及删除用户命令
<br>二.是通过GRANT命令创建具有某种权限的用 户.其中GRANT的常用用法如下：
```sql
grant all on mydb.* to NewUserName@HostName identified by “password” ;
grant usage on *.* to NewUserName@HostName identified by “password”;
grant select,insert,update on mydb.* to NewUserName@HostName identified by “password”;
grant update,delete on mydb.TestTable to NewUserName@HostName identified by “password”;
```
若要给此用户赋予他在相应对象上的grant权限的能力,可在GRANT后面添加WITH GRANT OPTION选项.
<br>对于用插入User表添加的用户,Password字段应用PASSWORD 函数进行更新加密,以防被窃看密码.
<br>对于那些已经不用的用户应给予清除,权限过界的用户应及时回收权限,回收权限可以通过更新User表相应字段， 也可以使用REVOKE操作。
<br>全局管理权限：
```
FILE: 在MySQL服务器上读写文件。
PROCESS: 显示或杀死属于其它用户的服务线程。
RELOAD: 重载访问控制表，刷新日志等。
SHUTDOWN: 关闭MySQL服务。
```
数据库/数据表/数据列权限：
```
ALTER: 修改已存在的数据表(例如增加/删除列)和索引。
CREATE: 建立新的数据库或数据表。
DELETE: 删除表的记录。
DROP: 删除数据表或数据库。
INDEX: 建立或删除索引。
INSERT: 增加表的记录。
SELECT: 显示/搜索表的记录。
UPDATE: 修改表中已存在的记录。
```
特别的权限：
```
ALL: 允许做任何事(和root一样)。
USAGE: 只允许登录–其它什么也不允许做。
```
#### 常用命令
```
# 1:使用SHOW语句找出在服务器上当前存在什么数据库：
mysql> SHOW DATABASES;

# 2:创建一个数据库MYSQLDATA
CREATE DATABASE db_name 
ALTER DATABASE db_name 
# COLLATE参数用来指定字符集校对规则
# MySQL这样选择数据库字符集和数据库校对规则： 
# 如果指定了CHARACTER SET X和COLLATE Y，那么采用字符集X和校对规则Y。 
# 如果指定了CHARACTER SET X而没有指定COLLATE Y，那么采用CHARACTER SET X和CHARACTER SET X的默认校对规则。 
# 否则，采用服务器字符集和服务器校对规则。
CREATE DATABASE IF NOT EXISTS my_db default charset utf8 COLLATE utf8_general_ci;

# 3:选择你所创建的数据库
mysql> USE MYSQLDATA; (按回车键出现Database changed 时说明操作成功！)

# 4:查看现在的数据库中存在什么表
mysql> SHOW TABLES;

# 5:创建一个数据库表
mysql> CREATE TABLE MYTABLE (name VARCHAR(20), sex CHAR(1));

# 6:显示表的结构：
mysql> DESCRIBE MYTABLE;

# 7:往表中加入记录
mysql> insert into MYTABLE values (”hyq”,”M”);

# 8:用文本方式将数据装入数据库表中（例如D:/mysql.txt）
mysql> LOAD DATA LOCAL INFILE “D:/mysql.txt” INTO TABLE MYTABLE;

# 9:导入.sql文件命令（例如D:/mysql.sql）
mysql>use database;
mysql>source d:/mysql.sql;

# 10:删除表
mysql>drop TABLE MYTABLE;

# 11:清空表
mysql>delete from MYTABLE;

12:更新表中数据
mysql>update MYTABLE set sex=”f” where name=’hyq’;

13.查看SQL执行的错误
mysql> show warnings;
```