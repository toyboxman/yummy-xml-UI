### PostgreSQL(https://stackoverflow.com/questions/16973018/createuser-could-not-connect-to-database-postgres-fatal-role-tom-does-not-e/16974197#16974197)
king@ubuntu:~/software$ psql
psql: FATAL:  role "king" does not exist
king@ubuntu:~/software$ psql -U postgres
psql: FATAL:  Peer authentication failed for user "postgres"

king@ubuntu:~/software$ sudo -u postgres psql
[sudo] password for king: 
psql (10.6 (Ubuntu 10.6-0ubuntu0.18.10.1))
Type "help" for help.

postgres-# \q

king@ubuntu:~/software$ sudo -u postgres createuser king
king@ubuntu:~/software$ psql -U king
psql: FATAL:  database "king" does not exist

king@ubuntu:~/software$ sudo -u postgres createdb -O king myDB
king@ubuntu:~/software$ psql -U king -d myDB
psql (10.6 (Ubuntu 10.6-0ubuntu0.18.10.1))
Type "help" for help.

myDB=>

king@ubuntu:~/software$ psql -U king -f init_db_postgres.sql myDB

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
