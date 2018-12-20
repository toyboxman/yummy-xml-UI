### MySQL
#### ��װ
��Linux��ʹ��mysql���ݿ�,����ʹ��yum(redhat) apt(ubuntu) zypper(suse)
```
# client package
yum install mysql

# server package
yum install mysql_server
     
# ��װ��ϲ鿴״̬ 
service mysqld status
```
#### ����
```
# mysqlֻ��һ��Ĭ�ϵ�root�û�,����Ϊ��,ֻ����localhost����
mysql -h localhost -u root -ppassword
```
JDBC URI
```
# ��ʹ�����ݿ����ӳص�����£����������������������
# autoReconnect=true&failOverReadOnly=false
jdbc:mysql://localhost:3306/test?user=root&password=&useUnicode=true&characterEncoding=utf8&autoReconnect=true&failOverReadOnly=false
```
��Ҫע����ǣ���Ӧ��xml�����ļ��У�jdbc url�е�&������Ҫת�塣������tomcat��server.xml���������ݿ����ӳ�ʱ��mysql jdbc url�������£�
```
jdbc:mysql://localhost:3306/test?user=root&amp;password=&amp;useUnicode=true&amp;characterEncoding=utf8&amp;autoReconnect=true&amp;failOverReadOnly
```
#### ���ݿ����     
�����root�û���װ����Ҫ�޸�mysql��¼����
```
# 1.��ֹͣmysqld���� 
service mysqld stop
# 2.������ȫģʽ
/usr/bin/mysqld_safe --skip-grant-tables
# 3.ͨ��mysql�ͻ��˵��� 
mysql              
mysql> UPDATE mysql.user SET Password=PASSWORD('MyNewPass') WHERE User='root';
mysql> FLUSH PRIVILEGES;
mysql> quit
# 4.����
mysqld  service mysqld restart
# 5.ʹ�������¼
mysql -u root -pMyNewPass dbname
mysql> use dbname
```
����ͨ��mysqlmanager�������û� ����ͨ��mysqladmin�����������ݿ�ʵ��
<br>
�ͻ��˹��ߣ�***SQuirreL SQL Client***,   ***SQL Power Architect***
#### ���ݿ�����
Ĭ������£�Զ���޷����ӵ����ݿ��������������ԭ��
<br>1.����ǽû�йر� ͨ�� service iptables status ���鿴����ǽ״̬
<br>2.�˿�(3306)��ռ�� ͨ�� netstat -tlnpu �鿴mysqld �˿�״̬
<br>3.���ݿ���mysqlʵ���ġ�user������ġ�host����ֻ�����ص�ַ  

     1.�ı�, �޸�hostֵ(��ͨ���%��������������/IP��ַ),��ֱ������IP��ַ"localhost"�ĳ�'%'
       mysql>update user set host = '%' where user = 'root';
       mysql>select host, user from user;  
     2. ��Ȩ��
      ʹmyuser��mypassword���κ��������ӵ�mysql������
      GRANT ALL PRIVILEGES ON *.* TO 'myuser'@'%' IDENTIFIED BY 'mypassword' WITH GRANT OPTION;
      ʹmyuser��ipΪ192.168.1.3���������ӵ�mysql������
      GRANT ALL PRIVILEGES ON *.* TO 'myuser'@'192.168.1.3' IDENTIFIED BY 'mypassword' WITH GRANT OPTION;
	  
���myuser@%�޷�ͨ��localhost��¼���ݿ�
```
root@controller:~# mysql -h localhost -u myuser -ppassword
ERROR 1045 (28000): Access denied for user 'keystone'@'localhost' (using password: YES)

mysql> use mysql;
mysql> delete from user where user='';
mysql> flush privileges;
```
��˼��ɾ�������û���Ȼ��Ϳ��Դ�localhost��¼
<br>
##### ��ȫ����
�û���Ӧ��ֻ��root�û������������ݿ�,��Ȼʹ��root�û����в���ʱ�ܷ���,�����ϵͳ�����ش�ȫ����.
<br>���Ǹ�һ��Ӧ����ʹ�õ��û�������ǡ�������ݿ�Ȩ�ޡ���һ��ֻ�������ݲ�����û���Ӧ������ɾ�����ݵ�Ȩ��.
<br>MySql���û�������ͨ�� User����ʵ�ֵ�,������û����õķ���������
<br>һ.����User�������Ӧ��������,ͬʱ������Ӧ��Ȩ�ޣ�������ɾ���û�����
<br>��.��ͨ��GRANT���������ĳ��Ȩ�޵��� ��.����GRANT�ĳ����÷����£�
```sql
grant all on mydb.* to NewUserName@HostName identified by ��password�� ;
grant usage on *.* to NewUserName@HostName identified by ��password��;
grant select,insert,update on mydb.* to NewUserName@HostName identified by ��password��;
grant update,delete on mydb.TestTable to NewUserName@HostName identified by ��password��;
```
��Ҫ�����û�����������Ӧ�����ϵ�grantȨ�޵�����,����GRANT�������WITH GRANT OPTIONѡ��.
<br>�����ò���User����ӵ��û�,Password�ֶ�Ӧ��PASSWORD �������и��¼���,�Է����Կ�����.
<br>������Щ�Ѿ����õ��û�Ӧ�������,Ȩ�޹�����û�Ӧ��ʱ����Ȩ��,����Ȩ�޿���ͨ������User����Ӧ�ֶΣ� Ҳ����ʹ��REVOKE������
<br>ȫ�ֹ���Ȩ�ޣ�
```
FILE: ��MySQL�������϶�д�ļ���
PROCESS: ��ʾ��ɱ�����������û��ķ����̡߳�
RELOAD: ���ط��ʿ��Ʊ�ˢ����־�ȡ�
SHUTDOWN: �ر�MySQL����
```
���ݿ�/���ݱ�/������Ȩ�ޣ�
```
ALTER: �޸��Ѵ��ڵ����ݱ�(��������/ɾ����)��������
CREATE: �����µ����ݿ�����ݱ�
DELETE: ɾ����ļ�¼��
DROP: ɾ�����ݱ�����ݿ⡣
INDEX: ������ɾ��������
INSERT: ���ӱ�ļ�¼��
SELECT: ��ʾ/������ļ�¼��
UPDATE: �޸ı����Ѵ��ڵļ�¼��
```
�ر��Ȩ�ޣ�
```
ALL: �������κ���(��rootһ��)��
USAGE: ֻ�����¼�C����ʲôҲ����������
```
#### ��������
```
# 1:ʹ��SHOW����ҳ��ڷ������ϵ�ǰ����ʲô���ݿ⣺
mysql> SHOW DATABASES;

# 2:����һ�����ݿ�MYSQLDATA
CREATE DATABASE db_name 
ALTER DATABASE db_name 
# COLLATE��������ָ���ַ���У�Թ���
# MySQL����ѡ�����ݿ��ַ��������ݿ�У�Թ��� 
# ���ָ����CHARACTER SET X��COLLATE Y����ô�����ַ���X��У�Թ���Y�� 
# ���ָ����CHARACTER SET X��û��ָ��COLLATE Y����ô����CHARACTER SET X��CHARACTER SET X��Ĭ��У�Թ��� 
# ���򣬲��÷������ַ����ͷ�����У�Թ���
CREATE DATABASE IF NOT EXISTS my_db default charset utf8 COLLATE utf8_general_ci;

# 3:ѡ���������������ݿ�
mysql> USE MYSQLDATA; (���س�������Database changed ʱ˵�������ɹ���)

# 4:�鿴���ڵ����ݿ��д���ʲô��
mysql> SHOW TABLES;

# 5:����һ�����ݿ��
mysql> CREATE TABLE MYTABLE (name VARCHAR(20), sex CHAR(1));

# 6:��ʾ��Ľṹ��
mysql> DESCRIBE MYTABLE;

# 7:�����м����¼
mysql> insert into MYTABLE values (��hyq��,��M��);

# 8:���ı���ʽ������װ�����ݿ���У�����D:/mysql.txt��
mysql> LOAD DATA LOCAL INFILE ��D:/mysql.txt�� INTO TABLE MYTABLE;

# 9:����.sql�ļ��������D:/mysql.sql��
mysql>use database;
mysql>source d:/mysql.sql;

# 10:ɾ����
mysql>drop TABLE MYTABLE;

# 11:��ձ�
mysql>delete from MYTABLE;

12:���±�������
mysql>update MYTABLE set sex=��f�� where name=��hyq��;
```