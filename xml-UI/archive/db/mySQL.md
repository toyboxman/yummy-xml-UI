### PostgreSQL(https://stackoverflow.com/questions/16973018/createuser-could-not-connect-to-database-postgres-fatal-role-tom-does-not-e/16974197#16974197)
在Linux上使用mysql数据库
1.安装
   可以使用yum(redhat) apt(ubuntu) zypper(suse)
     客户端 yum install mysql
     服务器端 yum install mysql_server
     安装完毕查看状态 service mysqld status
     如果数据库启动正常可以在本机登录。mysql只有一个默认的root用户,密码为空,只允许localhost连接
          mysql -h localhost -u root -ppassword
2. 修改密码
   如果用root用户安装后，需要修改mysql登录密码
      1.先停止mysqld服务 service mysqld stop
      2.启动安全模式/usr/bin/mysqld_safe --skip-grant-tables
      3.通过mysql客户端登入 mysql              
            mysql> UPDATE mysql.user SET Password=PASSWORD('MyNewPass') WHERE User='root';
            mysql> FLUSH PRIVILEGES;
            mysql> quit
      4.重启mysqld  service mysqld restart
3. 使用密码登录
     mysql -u root -pMyNewPass dbname  or mysql> use dbname

可以通过mysqlmanager来创建用户 可以通过mysqladmin来创建新数据库实例

客户端工具：SQuirreL SQL Client   SQL Power Architect
mysql连接URL：
jdbc:mysql://localhost:3306/test?user=root&password=&useUnicode=true&characterEncoding=utf8&autoReconnect=true&failOverReadOnly=false

在使用数据库连接池的情况下，最好设置如下两个参数：
autoReconnect=true&failOverReadOnly=false

需要注意的是，在xml配置文件中，url中的&符号需要转义成&。比如在tomcat的server.xml中配置数据库连接池时，mysql jdbc url样例如下：
jdbc:mysql://localhost:3306/test?user=root&password=&useUnicode=true&characterEncoding=utf8&autoReconnect=true&failOverReadOnly

默认情况下，远程无法连接到数据库服务器，有以下原因
1.防火墙没有关闭 通过 service iptables status 来查看防火墙状态
2.端口(3306)被占用 通过 netstat -tlnpu 查看mysqld 端口状态
3.数据库中mysql实例的“user”表里的“host”项只允许本地地址  
      1.改表法, 修改host值(以通配符%的内容增加主机/IP地址),或直接增加IP地址"localhost"改称'%'
       mysql>update user set host = '%' where user = 'root';
       mysql>select host, user from user;  
     2. 授权法
      使myuser用mypassword从任何主机连接到mysql服务器
      GRANT ALL PRIVILEGES ON *.* TO 'myuser'@'%' IDENTIFIED BY 'mypassword' WITH GRANT OPTION;
      使myuser从ip为192.168.1.3的主机连接到mysql服务器
      GRANT ALL PRIVILEGES ON *.* TO 'myuser'@'192.168.1.3' IDENTIFIED BY 'mypassword' WITH GRANT OPTION;
如果myuser@%无法通过localhost登录数据库，
root@controller:~# mysql -h localhost -u myuser -ppassword
ERROR 1045 (28000): Access denied for user 'keystone'@'localhost' (using password: YES)
可以进行如下操作
mysql> use mysql;
mysql> delete from user where user='';
mysql> flush privileges;
意思是删除匿名用户，然后就可以从localhost登录


常用命令
1:使用SHOW语句找出在服务器上当前存在什么数据库：
mysql> SHOW DATABASES;
2:2、创建一个数据库MYSQLDATA
mysql> CREATE DATABASE MYSQLDATA;
CREATE DATABASE IF NOT EXISTS my_db default charset utf8 COLLATE utf8_general_ci;
COLLATE参数用来指定字符集校对规则
CREATE DATABASE db_name 
[[DEFAULT] CHARACTER SET charset_name] 
[[DEFAULT] COLLATE collation_name] 
ALTER DATABASE db_name 
[[DEFAULT] CHARACTER SET charset_name] 
[[DEFAULT] COLLATE collation_name] 
MySQL这样选择数据库字符集和数据库校对规则： 
·如果指定了CHARACTER SET X和COLLATE Y，那么采用字符集X和校对规则Y。 
·如果指定了CHARACTER SET X而没有指定COLLATE Y，那么采用CHARACTER SET X和CHARACTER SET X的默认校对规则。 
·否则，采用服务器字符集和服务器校对规则。
3:选择你所创建的数据库
mysql> USE MYSQLDATA; (按回车键出现Database changed 时说明操作成功！)
4:查看现在的数据库中存在什么表
mysql> SHOW TABLES;
5:创建一个数据库表
mysql> CREATE TABLE MYTABLE (name VARCHAR(20), sex CHAR(1));
6:显示表的结构：
mysql> DESCRIBE MYTABLE;
7:往表中加入记录
mysql> insert into MYTABLE values (”hyq”,”M”);
8:用文本方式将数据装入数据库表中（例如D:/mysql.txt）
mysql> LOAD DATA LOCAL INFILE “D:/mysql.txt” INTO TABLE MYTABLE;
9:导入.sql文件命令（例如D:/mysql.sql）
mysql>use database;
mysql>source d:/mysql.sql;
10:删除表
mysql>drop TABLE MYTABLE;
11:清空表
mysql>delete from MYTABLE;
12:更新表中数据
mysql>update MYTABLE set sex=”f” where name=’hyq’;

安全设置
用户不应该只用root用户进行连接数据库,虽然使用root用户进行测试时很方便,但会给系统带来重大安全隐患.我们给一个应用中使用的用户赋予最恰当的数据库权限。如一个只进行数据插入的用户不应赋予其删除数据的权限.MySql的用户管理是通过 User表来实现的,添加新用户常用的方法有两个
一是在User表插入相应的数据行,同时设置相应的权限；创建及删除用户命令
二是通过GRANT命令创建具有某种权限的用 户.其中GRANT的常用用法如下：
grant all on mydb.* to NewUserName@HostName identified by “password” ;
grant usage on *.* to NewUserName@HostName identified by “password”;
grant select,insert,update on mydb.* to NewUserName@HostName identified by “password”;
grant update,delete on mydb.TestTable to NewUserName@HostName identified by “password”;
若要给此用户赋予他在相应对象上的grant权限的能力,可在GRANT后面添加WITH GRANT OPTION选项.而对于用插入User表添加的用户,Password字段应用PASSWORD 函数进行更新加密,以防被窃看密码.
对于那些已经不用的用户应给予清除,权限过界的用户应及时回收权限,回收权限可以通过更新User表相应字段， 也可以使用REVOKE操作。
全局管理权限：
FILE: 在MySQL服务器上读写文件。
PROCESS: 显示或杀死属于其它用户的服务线程。
RELOAD: 重载访问控制表，刷新日志等。
SHUTDOWN: 关闭MySQL服务。
数据库/数据表/数据列权限：
ALTER: 修改已存在的数据表(例如增加/删除列)和索引。
CREATE: 建立新的数据库或数据表。
DELETE: 删除表的记录。
DROP: 删除数据表或数据库。
INDEX: 建立或删除索引。
INSERT: 增加表的记录。
SELECT: 显示/搜索表的记录。
UPDATE: 修改表中已存在的记录。
特别的权限：
ALL: 允许做任何事(和root一样)。
USAGE: 只允许登录–其它什么也不允许做。