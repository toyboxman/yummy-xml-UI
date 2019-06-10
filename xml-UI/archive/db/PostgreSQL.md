### PostgreSQL
#### ��װ
```bash
$ sudo apt install postgresql-10
Success. You can now start the database server using:

    /usr/lib/postgresql/10/bin/pg_ctl -D /var/lib/postgresql/10/main -l logfile start
```
#### ����
���Ӵ����Ŵ���Բο� [link](https://stackoverflow.com/questions/16973018/createuser-could-not-connect-to-database-postgres-fatal-role-tom-does-not-e/16974197#16974197)
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

# help 
king@ubuntu:~/software$ psql --help

# -W, --password           force password prompt
king@ubuntu:~/software$ psql -U king -W -d myDB
password for king: 

king@ubuntu:~/software$ psql -U king -W -h localhost -d myDB

#  run sql without login
$ sudo -u king /opt/vpostgres/9.6/bin/psql -c "select * from mytable;"
```
JDBC URI
```
# default port 5432
jdbc:postgresql://127.0.0.1/myDB?ssl=false
```
#### ���ݿ����
[tutorial](http://www.postgresqltutorial.com/postgresql-administration/)
```bash
# ͨ�������û������������û�
king@ubuntu:~/software$ sudo -u postgres createuser king
# ��ָ�����ݿ⣬����ʧ��
king@ubuntu:~/software$ psql -U king
psql: FATAL:  database "king" does not exist

# ͨ�������û������������û��������ݿ�
king@ubuntu:~/software$ sudo -u postgres createdb -O king myDB
king@ubuntu:~/software$ psql -U king -d myDB
psql (10.6 (Ubuntu 10.6-0ubuntu0.18.10.1))
Type "help" for help.

myDB=>

# ָ��SQL�ű�����ʼ�����ݿ�
king@ubuntu:~/software$ psql -U king -f init_db_postgres.sql myDB

# �г���ǰ�������ݿ��û�
# postgre֧��tab�����Զ��������ʾ������selectĳ����ʱ�����������table��
king@ubuntu:~/software$ sudo -u postgres psql
[sudo] password for king: 
psql (10.6 (Ubuntu 10.6-0ubuntu0.18.10.1))
Type "help" for help.

# List of roles
postgres=# \du

 Role name |                         Attributes                         | Member of 
-----------+------------------------------------------------------------+-----------
 king         |                                                                     |              {}
 postgres  | Superuser, Create role, Create DB, Replication, Bypass RLS | {}
 
# List of databases 

mydb=> \list
   Name    |  Owner   | Encoding |   Collate   |    Ctype    |   Access privileges   
-----------+----------+----------+-------------+-------------+-----------------------
 mydb      | bjdev    | UTF8     | en_US.UTF-8 | en_US.UTF-8 | =Tc/bjdev            +
           |          |          |             |             | bjdev=CTc/bjdev
 postgres  | postgres | UTF8     | en_US.UTF-8 | en_US.UTF-8 | 
 template0 | postgres | UTF8     | en_US.UTF-8 | en_US.UTF-8 | =c/postgres          +
           |          |          |             |             | postgres=CTc/postgres
 template1 | postgres | UTF8     | en_US.UTF-8 | en_US.UTF-8 | =c/postgres          +
           |          |          |             |             | postgres=CTc/postgres

# List of relations(tables)
mydb=> \dt

 Schema |         Name         | Type  | Owner 
--------+----------------------+-------+-------
 public | san_average_tps_time | table | bjdev
 public | san_sys_proc         | table | bjdev
 public | san_sys_vmstat       | table | bjdev

# describe table 
mydb=> \d san_sys_vmstat_pkey 
Index "public.san_sys_vmstat_pkey"
 Column |  Type   | Definition 
--------+---------+------------
 id     | integer | id
primary key, btree, for table "public.san_sys_vmstat"
 
```
#### ���ݿ�����
PostgreSQL serverĬ��ʹ��5432�˿��ṩ���ӣ�������ӱ��ܣ��п����ǰ�ȫȨ���������⡣
�����йص����ö���[**pg_hba.conf**](https://www.postgresql.org/docs/10/auth-pg-hba-conf.html)
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

# �޸ĺ���Ҫ��������
sudo systemctl restart postgresql     # ubuntu
```
**password**����ָ�Կͻ�����������ʹ����������
<br>
**MD5**����ָ�Կͻ�����������ʹ��MD5 hash�任����
<br>   
����޸ĺ������Ծɱ��ܣ�������û���û��趨���롣���Ĭ�������ǿգ���ȫ��֤��һֱʧ�ܡ�
```bash
# SQL commands CREATE USER and ALTER USER, e.g., CREATE USER foo WITH PASSWORD 'secret';. 
# By default, that is, if no password has been set up, the stored password is null 
# and password authentication will always fail for that user.
postgres=# ALTER USER king WITH PASSWORD 'secret';
sudo systemctl restart postgresql
```
