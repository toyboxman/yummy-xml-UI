### Oracle
- [20c ������](https://mp.weixin.qq.com/s/8jWVLY-hSbXhaDoPmADV6w)  
        `ԭ����������֧��(Native Blockchain tables)`<br>
        `�־û��ڴ�洢(Persistent Memory Store)`
- [����Ǩ�ƹ���expdp��impdp](https://mp.weixin.qq.com/s/XkuVG8yN1dNBwrwH-9dhKQ)
- [Mysql��double write������Oracleʵ��](https://mp.weixin.qq.com/s/1uqkVCeC57t07lsDl-8M7w)
- [Oracle��PostgreSQL���ת��](https://mp.weixin.qq.com/s/hk8GwZgjvlqvouk1DHj5eQ)  
	`Oracle���ݿ�Ǩ�Ƶ�Amazon RDS/Aurora�����µ�PostgreSQL`
#### ���ݿ���� 
* 1.Start DB Instance
```console
a. su oracle --�л���Oracle�û�
b. export env var --�������ݿ⻷��,���Է��� .bashrc �ļ�
	e.g.
		export ORACLE_HOME=/home/oracle/app/oracle/product/11.2.0/server
		export ORACLE_SID=orcl
c. $ORACLE_HOME/bin/sqlplus /nolog --�ǵ�¼ʹ��sqlplus, ע��'/'�����޿ո�
		export PATH=$ORACLE_HOME/bin/sqlplus:$PATH --Ҳ�ɰ�sqlplus���뵽PATH��
d. SQL>connect / as sysdba --��Ϊdba�û�����
e. SQL>startup --����dbʵ��
```
* 2.Start Listener
```console
a. $ORACLE_HOME/bin/lsnrctl start [listenerName] --�������������[��ָ��listenerName]
```
* 3.Start Enterprise Manager(11g)
```console
a. $ORACLE_HOME/bin/emctl start dbconsole --����web console
```
* 4.Stop DB Instance
```console
a. sqlplus / as sysdba --��Ϊdba�û�����,ע��'/'����Ҫ�и��ո�
b. SQL>shutdown immediate
```
* 5.Stop Net Listener
```console
a. $ORACLE_HOME/bin/lsnrctl stop listenerName --ֹͣ���������
```
* 6.Stop Enterprise Manager(11g)
```console
a. $ORACLE_HOME/bin/emctl stop dbconsole --ֹͣweb console
```
* 7.retrieve system tables
```console
SQL>select sys_context('USERENV','DB_NAME') as Instance from dual; --��ȡ���ݿ�ʵ����
SQL>select * from all_users; --��ȡ�����û���
SQL>select * from all_tables; --��ȡ���пɷ��ʵĹ�ϵ��
SQL>select * from dba_tablespaces; --��ȡ���б�ռ���Ϣ
```