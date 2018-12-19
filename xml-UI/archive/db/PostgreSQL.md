### PostgreSQL
#### 安装
```bash
$ sudo apt install postgresql-10
Success. You can now start the database server using:

    /usr/lib/postgresql/10/bin/pg_ctl -D /var/lib/postgresql/10/main -l logfile start
```
#### 连接
连接错误排错可以参考 [link](https://stackoverflow.com/questions/16973018/createuser-could-not-connect-to-database-postgres-fatal-role-tom-does-not-e/16974197#16974197)
```bash
king@ubuntu:~/software$ psql
psql: FATAL:  role "king" does not exist
king@ubuntu:~/software$ psql -U postgres
psql: FATAL:  Peer authentication failed for user "postgres"

king@ubuntu:~/software$ sudo -u postgres psql
[sudo] password for king: 
psql (10.6 (Ubuntu 10.6-0ubuntu0.18.10.1))
Type "help" for help.

postgres-# \q
```
JDBC URI
```
# default port 5432
jdbc:postgresql://127.0.0.1/myDB?ssl=false
```
#### 数据库操作
```bash
# 通过超级用户来创建其他用户
king@ubuntu:~/software$ sudo -u postgres createuser king
# 无指定数据库，连接失败
king@ubuntu:~/software$ psql -U king
psql: FATAL:  database "king" does not exist

# 通过超级用户来创建其他用户所属数据库
king@ubuntu:~/software$ sudo -u postgres createdb -O king myDB
king@ubuntu:~/software$ psql -U king -d myDB
psql (10.6 (Ubuntu 10.6-0ubuntu0.18.10.1))
Type "help" for help.

myDB=>

# 指定SQL脚本来初始化数据库
king@ubuntu:~/software$ psql -U king -f init_db_postgres.sql myDB

# 列出当前用户表
king@ubuntu:~/software$ sudo -u postgres psql
[sudo] password for king: 
psql (10.6 (Ubuntu 10.6-0ubuntu0.18.10.1))
Type "help" for help.

postgres=# \du
                                   List of roles
 Role name |                         Attributes                         | Member of 
-----------+------------------------------------------------------------+-----------
 king         |                                                                     |              {}
 postgres  | Superuser, Create role, Create DB, Replication, Bypass RLS | {}
 
# help 
king@ubuntu:~/software$ psql --help

# -W, --password           force password prompt
king@ubuntu:~/software$ psql -U king -W -d myDB
password for king: 

king@ubuntu:~/software$ psql -U king -W -h localhost -d myDB
```
#### 数据库配置
PostgreSQL server默认使用5432端口提供连接，如果连接被拒，有可能是安全权限配置问题。
所有有关的配置都在[**pg_hba.conf**](https://www.postgresql.org/docs/10/auth-pg-hba-conf.html)
```
# IPv4 local connections:
# /etc/postgresql/10/main/pg_hba.conf

# DO NOT DISABLE!
# If you change this first entry you will need to make sure that the
# database superuser can access the database using some other method.
# Noninteractive access to all databases is required during automatic
# maintenance (custom daily cronjobs, replication, and similar tasks).
#
# Database administrative login by Unix domain socket
local   all             postgres                                peer

# TYPE  DATABASE        USER            ADDRESS                 METHOD

# "local" is for Unix domain socket connections only
local   all             all                                     peer
# IPv4 local connections:
host    all             all             127.0.0.1/32            password

# 修改后需要重启服务
sudo systemctl restart postgresql     # ubuntu
```
**password**方法指对客户端来的连接使用明文密码
<br>
**MD5**方法指对客户端来的连接使用MD5 hash变换密码
<br>   
如果修改后连接仍旧被拒，可能是没给用户设定密码。如果默认密码是空，安全验证会一直失败。
```bash
# SQL commands CREATE USER and ALTER USER, e.g., CREATE USER foo WITH PASSWORD 'secret';. 
# By default, that is, if no password has been set up, the stored password is null 
# and password authentication will always fail for that user.
postgres=# ALTER USER king WITH PASSWORD 'secret';
sudo systemctl restart postgresql
```
