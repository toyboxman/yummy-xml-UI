### *markdown syntax guide*
[markdown_pdf](https://guides.github.com/pdfs/markdown-cheatsheet-online.pdf)<br>
[markdown_html](https://guides.github.com/features/mastering-markdown/)<br>
[markdown_syntax](https://sourceforge.net/p/freemind/wiki/markdown_syntax/)

---

### *Following content is docker commands used usually*
- [Docker Installation](#docker-installation-on-suse)
- [Docker Command](#docker-command)
- [Docker Compose](#docker-compose)

---

#### docker installation on SUSE
* install package
```bash
$ sudo zypper in yast2-docker
```
* Giving non-root user privilege to access, the docker daemon always runs as the root user
```bash
# create group docker
$ sudo groupadd docker
# Adding user king to group docker, sudo gpasswd -a ${USER} docker
$ sudo gpasswd -a king docker 
$ sudo service docker restart
# relogin and make user group effective
logout and login  
```
* set docker image pull proxy
```bash
$ sudo systemctl stop docker
$ sudo mkdir -p /etc/systemd/system/docker.service.d

$ sudo touch /etc/systemd/system/docker.service.d/http-proxy.conf
[Service]
Environment="HTTP_PROXY=http://proxy.example.com:80/"
OR, 
Environment="HTTP_PROXY=http://proxy.example.com:80/" \
"NO_PROXY=localhost,127.0.0.1,docker-registry.somecorporation.com"

$ sudo touch /etc/systemd/system/docker.service.d/https-proxy.conf
[Service]
Environment="HTTPS_PROXY=https://proxy.example.com:443/"

# restart docker service and make changes effective
$ sudo systemctl daemon-reload
$ sudo systemctl restart docker
$ systemctl show --property=Environment docker
```

#### docker command
```bash
# list all docker instances(up/exited/...)
$ docker ps -a 
# list up instances
$ docker ps
$ docker container ls

# list images
$ docker images
$ docker image ls 

# search default image hub
$ docker search <NAME>

# pull registry image to local repo /var/lib/docker/btrfs/subvolumes
$ docker pull java
# pull mirror image
# https://www.docker-cn.com/registry-mirror
$ docker pull registry.docker-cn.com/library/golang

# remove image forcibly
$ docker rmi -f <NAME | ID>  

# create container instance with name 'jdk-dev', tty interaction using image 'java' and start
$ docker run --name jdk-dev -it java  
# create container instance using golang image with detached mode
$ docker run -d -it golang
# run image as container with mapping port 2181 to host's port 8081
$ docker run -p8081:2181 -it solo_zk  
# 将container中文件内容导出本地，执行完成后kill container
# -rm parameter means kill container after command closes
$ docker run -i --rm postgres cat /usr/share/postgresql/postgresql.conf.sample > my-postgres.conf
# remove container by name/id
$ docker rm <NAME | ID>  
# Stop one or more running containers
$ docker stop <NAME | ID>  

# copy locally current file to docker instance folder
$ docker cp ./zkCache.sh <NAME | ID>:/opt/zk
# symbolic link copy
$ docker cp -L ./zkCache.sh <NAME | ID>:/opt/zk 
# attach to existing docker container instance
$ docker exec -it <NAME | ID> bash  

# list docker network
$ docker network ls  
# network detail examination
$ docker inspect <NAME | ID> | grep <IP>
$ docker network inspect <network_name>

# build local image, need three files, Dockerfile  zoo.cfg  zookeeper-entrypoint.sh in ./ folder
$ docker build -t solo_zk ./ 
# commit container as new image
$ docker commit 10cab gene/kafka:latest  
```
#### build docker network
> [Link](http://blog.oddbit.com/2014/08/11/four-ways-to-connect-a-docker/)

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

#### docker-compose
> [example](https://github.com/lukeolbrish/examples/tree/master/zookeeper/five-server-docker)
```bash
# docker-compose is for defining and running multi-container applications
# install docker-compose package
$ sudo zypper install docker-compose  
# check all compose containers
$ docker-compose ps  
# remove invalid containers
$ docker-compose rm
  
# run all compose services by default yaml file, docker-compose.yml
$ docker-compose up
# run all compose services by yaml file, default yaml: docker-compose.yml
$ docker-compose -f docker-compose-define.yml up  
# run all compose service with detached mode
$ docker-compose up -d  
# re-run a service node, like 'docker-compose up -d zookeeper5', 
# startup zk5 node again, it becomes a follower
$ docker-compose up <serviceName>  
# shutdown all compose service
$ docker-compose down  

# Run a one-off command on a service
$ docker-compose run
# create a container of zk client to connect zk3 server
# open zk client CLI mode
# -rm parameter means kill container after command closes
$ docker-compose run --rm zkcli -server zookeeper3  
```
#### zk command
```bash
# enter zkCli mode
$ /bin/zkCli.sh -server 172.18.0.3:2181  
# check zk node follower or leader
$ echo stat | nc localhost 2181| grep Mode  
# list zNodes
$ ls /  
# create zNode 'zk_test' to save data
$ create /zk_test <data>  
# get data saved in zk_test
$ get /zk_test   
```

#### spark command
```bash
> spark-submit --class org.apache.griffin.measure.Application --master yarn-client \
 --queue default --verbose griffin-measure.jar env.json config.json local,local
```
#### kafka command
```bash
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
kafka-console-consumer.sh --bootstrap-server 10.117.7.110:9092 \
--topic source --from-beginning
This is a message
This is another message

#check topic offset
kafka-run-class.sh kafka.tools.GetOffsetShell --broker-list localhost:9092 \
--topic source --time -1  
source:0:4

#list groups
kafka-consumer-groups.sh --bootstrap-server 10.117.7.110:9092 --list
test-consumer-group

#add topic into group1
kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic source \
--consumer-property group.id=group1

#list group
kafka-consumer-groups.sh --bootstrap-server localhost:9092 --describe --group group1
TOPIC        PARTITION  CURRENT-OFFSET  LOG-END-OFFSET  LAG 
source                     0          4               4               0          -                                   
target                       0          3               3               0          -         

# produce message
kafka-console-producer.sh --broker-list localhost:9092 --topic source
{"name": "kevin", "age": 24}
{"name": "jason", "age": 25}
{"name": "jhon", "age": 28}
{"name": "steve", "age": 31}

# consume message
kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic source --from-beginning
{"name": "kevin", "age": 24}
{"name": "jason", "age": 25}
{"name": "steve", "age": 20}
```