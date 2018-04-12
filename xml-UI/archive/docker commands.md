### *markdown syntax guide*
[markdown_pdf](https://guides.github.com/pdfs/markdown-cheatsheet-online.pdf)<br>
[markdown_html](https://guides.github.com/features/mastering-markdown/)

---

### *Following content is docker commands used usually*

---

#### docker installation on SUSE
* install package
```shell
sudo zypper in yast2-docker
```
* Giving non-root user privilege to access, the docker daemon always runs as the root user
```shell
# create group docker
sudo groupadd docker
# Adding user king to group docker, sudo gpasswd -a ${USER} docker
sudo gpasswd -a king docker 
sudo service docker restart
# relogin and make user group effective
logout and login  
```
* set docker image pull proxy
```shell
sudo systemctl stop docker
sudo mkdir -p /etc/systemd/system/docker.service.d

sudo touch /etc/systemd/system/docker.service.d/http-proxy.conf
[Service]
Environment="HTTP_PROXY=http://proxy.example.com:80/"
OR, 
Environment="HTTP_PROXY=http://proxy.example.com:80/" \
"NO_PROXY=localhost,127.0.0.1,docker-registry.somecorporation.com"

sudo touch /etc/systemd/system/docker.service.d/https-proxy.conf
[Service]
Environment="HTTPS_PROXY=https://proxy.example.com:443/"

# restart docker service and make changes effective
sudo systemctl daemon-reload
sudo systemctl restart docker
systemctl show --property=Environment docker
```


#### docker command
```shell
# list docker instances
docker ps -a 
# list images
docker images
docker image ls 
# search default image hub
docker search <NAME>
# pull registry image to local repo /var/lib/docker/btrfs/subvolumes
docker pull java
# remove image forcibly
docker rmi -f <NAME | ID>  

# create container instance 'jdk-dev' by image 'java' and start
docker run --name jdk-dev -it java  
# run image as container with mapping port 2181 to host's port 8081
docker run -p8081:2181 solo_zk  
# remove container by name/id
docker rm <NAME | ID>  
# Stop one or more running containers
docker stop <NAME | ID>  

# copy locally current file to docker instance folder
docker cp ./zkCache.sh <NAME | ID>:/opt/zk
# symbolic link copy
docker cp -L ./zkCache.sh <NAME | ID>:/opt/zk 
# attach to existing docker container instance
docker exec -it <NAME | ID> bash  

# list docker network
docker network ls  
# network detail examination
docker inspect <NAME | ID> | grep <IP>
docker network inspect <network_name>

# build local image, need three files, Dockerfile  zoo.cfg  zookeeper-entrypoint.sh in ./ folder
docker build -t solo_zk ./ 
# commit container as new image
docker commit 10cab gene/kafka:latest  
```



#### change docker local image repo
Caution - These steps depend on your current /var/lib/docker being <br>an actual directory (not a symlink to another location).<br>
1) Stop docker: service docker stop. Verify no docker process is running ps -ef|grep faux  
2) Double check docker really isn't running. Take a look at the current docker directory: ls /var/lib/docker/  
2b) Make a backup, tar -zcC /var/lib docker > /mnt/pd0/var_lib_docker-backup-$(date +%s).tar.gz  
3) Move the /var/lib/docker directory to your new partition: mv /var/lib/docker /mnt/pd0/docker  
4) Make a symlink: ln -s /mnt/pd0/docker /var/lib/docker  
5) Take a peek at the directory structure to make sure it looks like it did <br>before the mv: ls /var/lib/docker/ (note the trailing slash to resolve the symlink)  
6) Start docker back up service docker start  
7) restart your containers  

#docker tool
sudo zypper install docker-compose  -- docker-compose is a tool for defining and running multi-container Docker applications
example https://github.com/lukeolbrish/examples/tree/master/zookeeper/five-server-docker
git clone https://github.com/lukeolbrish/examples.git
docker-compose ps  -- check all compose containers
docker-compose rm  -- remove invalid containers
docker-compose -f docker-compose-define.yml up  //run all compose services in yml, default yml: docker-compose.yml
docker-compose up -- run all compose service, start five zookeeper nodes by yaml config
docker-compose up -d  // run all compose service with detached mode
docker rm fiveserverdocker_zookeeper5_1  // remove zk5 node and zk4 will be selected as leader
docker-compose up <serviceName>  -- docker-compose up -d zookeeper5  // again startup zk5 node, it becomes a follower
docker-compose down  // shutdown all compose service
$check zk cluster
docker-compose run --rm zkcli -server zookeeper3  // create a zkcli container to connect zk3 server and enter cli mode, -rm parameter means kill container after command closes
or
docker exec -it fiveserverdocker_zookeeper5_1 bash  -- attach to existing docker container instance
/bin/zkCli.sh -server 172.18.0.3:2181  -- enter zkCli mode
echo stat | nc localhost 2181| grep Mode  -- check zk node follower or leader
ls /  -- list zNodes
create /zk_test <data>  -- create zNode to save data
get /zk_test   -- get data saved in zk_test

example:
[zk: zookeeper5(CONNECTED) 14] create /test aaa
Created /test                                                                                                                                                                                                                                                                                                                        
[zk: zookeeper5(CONNECTED) 16] ls /                                                                                                                                         
[zookeeper, test]                                                                                                                                                           
[zk: zookeeper5(CONNECTED) 18] get /test                                                                                                                                    
aaa                                                                                                                                                                         
cZxid = 0x100000002                                                                                                                                                         
ctime = Tue Apr 11 01:13:16 GMT 2017  -- created time                                                                                                                                      
mZxid = 0x100000002                                                                                                                                                         
mtime = Tue Apr 11 01:13:16 GMT 2017 -- changed time     
pZxid = 0x100000002
cversion = 0
dataVersion = 0  -- inital data version
aclVersion = 0
ephemeralOwner = 0x0
dataLength = 3  -- string data length
numChildren = 0
[zk: zookeeper5(CONNECTED) 20] set /test bbb
[zk: zookeeper5(CONNECTED) 21] get /test
bbb
cZxid = 0x100000002
ctime = Tue Apr 11 01:13:16 GMT 2017
mZxid = 0x100000004
mtime = Tue Apr 11 01:14:13 GMT 2017
pZxid = 0x100000002
cversion = 0
dataVersion = 1  -- changed data version
aclVersion = 0
ephemeralOwner = 0x0
dataLength = 3
numChildren = 0

Zxid:Every change to the ZooKeeper state receives a stamp in the form of a zxid (ZooKeeper Transaction Id). This exposes the total ordering of all changes to ZooKeeper. Each change will have a unique zxid and if zxid1 is smaller than zxid2 then zxid1 happened before zxid2.
Version numbers:Every change to a node will cause an increase to one of the version numbers of that node. The three version numbers are version (number of changes to the data of a znode), cversion (number of changes to the children of a znode), and aversion (number of changes to the ACL of a znode).
Ticks:When using multi-server ZooKeeper, servers use ticks to define timing of events such as status uploads, session timeouts, connection timeouts between peers, etc. The tick time is only indirectly exposed through the minimum session timeout (2 times the tick time); if a client requests a session timeout less than the minimum session timeout, the server will tell the client that the session timeout is actually the minimum session timeout.
Real time:ZooKeeper doesn't use real time, or clock time, at all except to put timestamps into the stat structure on znode creation and znode modification.
czxid:The zxid of the change that caused this znode to be created.
mzxid:The zxid of the change that last modified this znode.
pzxid:The zxid of the change that last modified children of this znode.
ctime:The time in milliseconds from epoch when this znode was created.
mtime:The time in milliseconds from epoch when this znode was last modified.
version:The number of changes to the data of this znode.
cversion:The number of changes to the children of this znode.
aversion:The number of changes to the ACL of this znode.
ephemeralOwner:The session id of the owner of this znode if the znode is an ephemeral node. If it is not an ephemeral node, it will be zero.
dataLength:The length of the data field of this znode.
numChildren:The number of children of this znode.

#build docker network
http://blog.oddbit.com/2014/08/11/four-ways-to-connect-a-docker/


> spark-submit --class org.apache.griffin.measure.Application --master yarn-client --queue default --verbose griffin-measure.jar env.json config.json local,local
#topic creation
> bin/kafka-topics.sh --create \
    --zookeeper localhost:2181 \
    --replication-factor 1 \
    --partitions 1 \
    --topic test
#topic list	
kafka-topics.sh --list --zookeeper 10.117.7.110:2181
test	
#topic deletion
kafka-topics.sh --zookeeper 10.117.7.110:2181 --delete --topic=target
#topic details
kafka-topics.sh --describe --zookeeper 10.117.7.110:2181 --topic source
#topic send message
kafka-console-producer.sh --broker-list 10.117.7.110:9092 --topic test
This is a message
This is another message
#topic receive message
kafka-console-consumer.sh --bootstrap-server 10.117.7.110:9092 --topic source --from-beginning
This is a message
This is another message
#check topic offset
kafka-run-class.sh kafka.tools.GetOffsetShell --broker-list localhost:9092 --topic source --time -1  
source:0:4
#list groups
kafka-consumer-groups.sh --bootstrap-server 10.117.7.110:9092 --list
test-consumer-group
#add topic into group1
kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic source --consumer-property group.id=group1
#list group
kafka-consumer-groups.sh --bootstrap-server localhost:9092 --describe --group group1
TOPIC        PARTITION  CURRENT-OFFSET  LOG-END-OFFSET  LAG 
source                     0          4               4               0          -                                   
target                       0          3               3               0          -                                   


> bin/kafka-topics.sh --create \
    --zookeeper 10.117.7.110:2181 \
    --replication-factor 1 \
    --partitions 1 \
    --topic target
kafka-topics.sh --describe --zookeeper 10.117.7.110:2181 --topic source
	
kafka-console-producer.sh --broker-list localhost:9092 --topic source
{"name": "kevin", "age": 24}
{"name": "jason", "age": 25}
{"name": "jhon", "age": 28}
{"name": "steve", "age": 31}
kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic source --from-beginning
{"name": "kevin", "age": 24}
{"name": "jason", "age": 25}
{"name": "steve", "age": 20}