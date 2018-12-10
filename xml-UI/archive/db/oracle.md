### PostgreSQL(https://stackoverflow.com/questions/16973018/createuser-could-not-connect-to-database-postgres-fatal-role-tom-does-not-e/16974197#16974197)
1.Start DB Instance
**** a. su oracle --�л���Oracle�û�
**** b. export env var --�������ݿ⻷��,���Է��� .bashrc �ļ�
******** e.g.
********** export ORACLE_HOME=/home/oracle/app/oracle/product/11.2.0/server
********** export ORACLE_SID=orcl
**** c. $ORACLE_HOME/bin/sqlplus /nolog --�ǵ�¼ʹ��sqlplus, ע��'/'�����޿ո�
********** export PATH=$ORACLE_HOME/bin/sqlplus:$PATH --Ҳ�ɰ�sqlplus���뵽PATH��
**** d. SQL>connect / as sysdba --��Ϊdba�û�����
**** e. SQL>startup --����dbʵ��

2.Start Listener
**** a. $ORACLE_HOME/bin/lsnrctl start [listenerName] --�������������[��ָ��listenerName]

3.Start Enterprise Manager(11g)
**** a. $ORACLE_HOME/bin/emctl start dbconsole --����web console

4.Stop DB Instance
**** a. sqlplus / as sysdba --��Ϊdba�û�����,ע��'/'����Ҫ�и��ո�
**** b. SQL>shutdown immediate

5.Stop Net Listener
**** a. $ORACLE_HOME/bin/lsnrctl stop listenerName --ֹͣ���������

6.Stop Enterprise Manager(11g)
**** a. $ORACLE_HOME/bin/emctl stop dbconsole --ֹͣweb console

----------------------------------------------------------------------------------------------------
SQL>select sys_context('USERENV','DB_NAME') as Instance from dual; --��ȡ���ݿ�ʵ����
SQL>select * from all_users; --��ȡ�����û���
SQL>select * from all_tables; --��ȡ���пɷ��ʵĹ�ϵ��
SQL>select * from dba_tablespaces; --��ȡ���б�ռ���Ϣ