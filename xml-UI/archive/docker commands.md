### *markdown syntax guide*
[markdown_pdf](https://guides.github.com/pdfs/markdown-cheatsheet-online.pdf)<br>
[markdown_html](https://guides.github.com/features/mastering-markdown/)<br>
[markdown_syntax](https://sourceforge.net/p/freemind/wiki/markdown_syntax/)

---

### *Following content is docker commands used usually*
+ [Docker Installation](#docker-installation-on-suse)
+ [Docker Command](#docker-command)  
  - [Docker 常见问题处理](https://mp.weixin.qq.com/s/IkPwD04tavwAQ2VAmjhm0g)
+ [Docker Compose](#docker-compose)
+ [Docker Build](#docker-build)
  + [多平台Docker镜像构建](https://mp.weixin.qq.com/s/wGuJHespc6aRkIuPK1FGUg)  
    `buildx is a Docker CLI plugin for extended build capabilities with BuildKit.`
  + [Docker Image瘦身](https://mp.weixin.qq.com/s/z1a8JUNFrQjSSER3W8Hw6g)  
  - [Go应用docker镜像瘦身](https://mp.weixin.qq.com/s/4a6d4P0Gzb4uC-99XMABIw)
+ [Podman：一个更安全的运行容器的方式](https://mp.weixin.qq.com/s/JBQLqxUqZdGpQaU2iauFzA)
+ Docker Networks
  + [OverlayFS](https://mp.weixin.qq.com/s/KEKecCY6Y-i1sWa6T9jiAg)
  + [容器云平台网络架构](https://mp.weixin.qq.com/s/_4mO4hBvaZFnoJlPhUJ7gQ)
+ [Portainer.io监控和管理Docker容器](https://mp.weixin.qq.com/s/NPo9oOcm3CSLXjEonnQRgA)

---

#### docker installation on SUSE
* install package
```console
sudo zypper in yast2-docker
```
* Giving non-root user privilege to access, the docker daemon always runs as the root user
```console
# create group docker
sudo groupadd docker
# Adding user king to group docker, sudo gpasswd -a ${USER} docker
sudo gpasswd -a king docker 
sudo service docker restart
# relogin and make user group effective
logout and login  
```
* set docker image pull proxy
```console
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
```console
# 列出全部容器实例，包括运行中，已停止
docker ps -a 
docker container ls -a
# 列出全部运行容器实例
docker ps
docker container ls

# list images
docker images
docker image ls 
# list image build config
docker inspect <image NAME | ID>

# search default image hub
docker search <NAME>
# 'docker search' executes rest api, n=page_size, page, q=<NAME>
curl 'https://index.docker.io/v1/search?q=griffin&n=5&page=2'
# get Bearer Authentication token
curl 'https://auth.docker.io/token'
# access docker registry
curl 'https://registry-1.docker.io/v2/' -H 'Authorization: Bearer <TOKEN>'

# pull registry image to local repo /var/lib/docker/btrfs/subvolumes
docker pull java
# 现在docker.io限制非用户下载，因此需要注册一个用户，然后命令认证。成功后才能下载
docker login
# 从镜像站点下载 pull mirror image
# https://www.docker-cn.com/registry-mirror
docker pull registry.docker-cn.com/library/golang

# push image
# 1.查看当前local得repository中的镜像
$ docker images
REPOSITORY                                        TAG                 IMAGE ID       CREATED         SIZE
jaegertracing/jaeger-query                        latest              5cf976234b0b   7 months ago    53.5MB
jaegertracing/jaeger-collector                    latest              90d07b89b450   7 months ago    47MB
elasticsearch                                     7.16.1              405db9d10ee0   8 months ago    642MB
kibana                                            7.16.1              02f1088fcc07   8 months ago    1.3GB
jaegertracing/all-in-one                          latest              dfcef08c2fac   9 months ago    57.4MB
public.ecr.aws/docker/library/centos              7.9.2009            eeb6ee3f44bd   11 months ago   204MB
# 2.给本地镜像打上合适标签, 标签格式 [push_ip:端口/namespace/name:版本号]
docker tag public.ecr.aws/docker/library/centos:7.9.2009 projects.registry.example.com/aws/centos:latest
# 3.镜像站点需要Authentication
docker login --username=docker2020 projects.registry.example.com
# 4.push image 等待upload时间，成功后可在站点对应空间看到image文件
docker push projects.registry.example.com/aws/centos

# image 更改 name tag
docker image tag kibana:7.16.1 my-project/kibana:7.16.1
docker image tag 02f1088fcc07 my-project/kibana:7.16.1
$ docker images
REPOSITORY                                        TAG                 IMAGE ID       CREATED         SIZE
kibana                                            7.16.1              02f1088fcc07   8 months ago    1.3GB
my-project/kibana                                 7.16.1              02f1088fcc07   8 months ago    1.3GB
# 可以将旧的 name/tag 删除，由于存在一个新的 name tag, 本地实际的image文件并不删除
docker rmi kibana:7.16.1

# remove image forcibly
docker rmi -f <NAME | ID>  

# export image
docker save nginx > /tmp/nginx.tgz

# docker load creates potentially multiple images from a tarred repository (since docker save can save multiple images in a tarball).
# you can import the image(s) in the same way they were originally created with the metadata from the Dockerfile, so you can directly run them with docker run.
docker load < /tmp/nginx.tgz
docker load -i /tmp/nginx.tgz

# docker import creates one image from one tarball which is not even an image (just a filesystem you want to import as an image)
# this imported image will not be able to be run from docker run, since it has no metadata associated with it (e.g. what the CMD to run is.)
# docker export/import 仅仅作用于image中的文件系统，不会导入导出image metadata中CMD和ENTRYPOINT, 因此无法运行docker run.
# 允许导入镜像时候带上标签 docker import [OPTIONS] file|URL|- [REPOSITORY[:TAG]]
docker import sha-manager.tar sha-mgr:latest

# create container instance with name 'jdk-dev', tty interaction using image 'java' and start
# -i, --interactive    Keep STDIN open even if not attached
# -t, --tty    Allocate a pseudo-TTY
docker run --name jdk-dev -it java  
# create container instance using golang image with detached mode
docker run -d -it golang
# run image as container with mapping port 2181 to host's port 8081
docker run -p8081:2181 -it solo_zk  
# 多端口映射和udp类型映射
docker run -d -p 5775:5775/udp -p 16686:16686 jaegertracing/all-in-one:latest
# 将container中文件内容导出本地，执行完成后kill container
# -rm parameter means kill container after command closes
docker run -i --rm postgres cat /usr/share/postgresql/postgresql.conf.sample > my-postgres.conf
# postgres image guide https://github.com/docker-library/docs/blob/master/postgres/README.md
# -e 指定container的环境变量
docker run --name some-postgres -e POSTGRES_PASSWORD_FILE=/run/secrets/postgres-passwd -d postgres
# remove container by name/id
docker rm <NAME | ID>  
# Stop one or more running containers
docker stop <NAME | ID>  
# Start one or more running containers
# 启动两个container，用tab可以自动不全当前stopped的container
# 基础镜像，没有运行任何进程，会退出，Docker是内核虚拟化，在进程，网络，存储，资源方面做轻量级隔离，
# 如果没有进程运行，就不会存在容器(这也是镜像与容器的最基本区别)。
# 所以可以加一个执行命令试试（作为0号进程）docker pull centos:5 docker run -itd --net=host --name=centos centos:5 /bin/bash
docker start jaeger griffin

# 查看container的日志
docker logs <NAME | ID>
# 也可以通过systemd命令查看，有些系统不支持需要查看日志文件
# https://zhuanlan.zhihu.com/p/413553112
journalctl -u docker.service
```

# Docker Copy 
容器无法启动,里面配置文件有错，通过copy(替换，新增容器中文件)/commit(替换，新增，删除)配置文件解决 
- cp修改 [[1](https://zhuanlan.zhihu.com/p/159426055)]
- commit修改 [[1](https://zhuanlan.zhihu.com/p/147026163), [2](https://www.jianshu.com/p/d4098f776054)]
```console
# copy locally current file to docker instance folder
docker cp ./zkCache.sh <NAME | ID>:/opt/zk
# 把spark main目录中所有examples源码并子目录copy到当前宿主机目录
docker cp spark-master:/spark/examples/src/main/ ./
# symbolic link copy
docker cp -L ./zkCache.sh <NAME | ID>:/opt/zk 
# attach to existing docker container instance
docker exec -it <NAME | ID> bash  

# list docker network
docker network ls  
# network detail examination
docker inspect <NAME | ID> | grep <IP>
docker network inspect <network_name>

# --link 参数来使容器互联，随着Docker network的完善，可将容器加入自定义的Docker network来连接多个容器
# https://yeasy.gitbook.io/docker_practice/network/linking
# 源容器
docker run -d --name selenium_hub selenium/hub
# 接收容器
docker run -d --name node --link selenium_hub:hub selenium/node-chrome-debug

# build local image, need three files, Dockerfile  zoo.cfg  zookeeper-entrypoint.sh in ./ folder
docker build -t solo_zk ./ 
# commit container as new image
docker commit 10cab gene/kafka:latest  
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

#### docker-build
构建本地 image 需要用到build命令，过程分成定义和构建两步    
1.定义image，通过 Dockerfile 文件描述image构建过程。 Dockerfile 指令接近 20 个，经常用到的不超过 10 个
- Dockerfile[[1](https://segmentfault.com/a/1190000018108361),[2](https://zhuanlan.zhihu.com/p/102450025)]
- ENTRYPOINT/CMD的区别[[1](https://zhuanlan.zhihu.com/p/30555962)]
```console
# 指令是逐条执行，且相互独立
# 引用基础镜像指令 FROM
FROM ubuntu:18.04   
# 声明变量 可有值或无值设定
ARG PUID=1000
ENV PUID ${PUID}
ARG AIRFLOW_VERSION="2.0.0.dev0"
ARG ARG_RUBY_VERSION    
# 声明环境变量
ENV JAVA_HOME /opt/jdk-1.8.0  
# 设置工作目录   
WORKDIR /opt/installation   
# 构建定制的部分，例如整体的目录结构，build时依赖的文件和工具包等
# COPY与ADD两个指令几乎相同, ADD指令<src>参数是本地 tar 文件时，会自动解包; <src>可以是 url，支持从远程拉取
# ADD URL下载和解压特性不能一起使用。任何压缩文件通过URL拷贝，都不会自动解压; 这种情况就用RUN wget/curl/tar/unzip
ADD https://dlcdn.apache.org/zookeeper/zookeeper-3.7.1/apache-zookeeper-3.7.1-bin.tar.gz /opt/zookeeper.tar.gz
ADD https://cdn.azul.com/zulu/bin/zulu8.52.0.23-ca-jdk8.0.282-linux_x64.tar.gz /opt/jdk.tar.gz
COPY start.sh /opt/installation  
RUN command1 && command2 ...
# 容器入口 指定容器启动时默认执行的命令
ENTRYPOINT ["/entry.app"]  
# 指定容器启动时默认命令的默认参数
CMD ["--options"] 
```
2.build命令执行构建过程，产生本地 image 
- [打包镜像](https://zhuanlan.zhihu.com/p/158174964)
```console
# -t: 打包出镜像的名称及标签，通常写法为 name:tag
# --rm: 构建成功后，删除中间产生的容器。
# --force-rm=true: 无论是否构建成功，都删除中间产生的容器
# --no-cache: 构建镜像时不使用缓存。
# -f: 指定 Dockerfile 的路径
# 用当前目录下Dockerfile 构建一个名称为 local/centos7:v1 的image
docker build --no-cache --force-rm -t local/centos7:v1 ./    
docker build -t image .
```
3.多阶段构建就是把这一切都放到一个 Dockerfile 里，既没有源码泄漏，又不需要用脚本去跨平台编译，还获得了最小的镜像。
```console
# 第一个 FROM 开始的部分是构建一个 builder 镜像
FROM golang:alpine AS builder

WORKDIR /build

ADD go.mod .
COPY . .
RUN go build -o hello hello.go

# 第二个 From 开始的部分是从第一个镜像里 copy 出来可执行文件 hello，并且用尽可能小的基础镜像 alpine 以保障最终镜像尽可能小
# https://hub.docker.com/_/alpine?tab=tags alpine linux/amd64 2.67 MB
FROM alpine

WORKDIR /build
COPY --from=builder /build/hello /build/hello

CMD ["./hello"]
```

#### docker-compose
> [example](https://github.com/lukeolbrish/examples/tree/master/zookeeper/five-server-docker)
```console
# docker-compose is for defining and running multi-container applications
# install docker-compose package
sudo zypper install docker-compose  
# check all compose containers
docker-compose ps  
# remove invalid containers
docker-compose rm
  
# run all compose services by default yaml file, docker-compose.yml
docker-compose up
# run all compose services by yaml file, default yaml: docker-compose.yml
docker-compose -f docker-compose-define.yml up  
# run all compose service with detached mode
docker-compose up -d  
# re-run a service node, like 'docker-compose up -d zookeeper5', 
# startup zk5 node again, it becomes a follower
docker-compose up <serviceName>  
# shutdown all compose service
docker-compose down  

# Run a one-off command on a service
docker-compose run
# create a container of zk client to connect zk3 server
# open zk client CLI mode
# -rm parameter means kill container after command closes
docker-compose run --rm zkcli -server zookeeper3  
```

#### 配置 jaeger + kibana + ES
将jaeger收集trace数据存储在ES
```console
# elasticsearch
# ElasticSearch启动时，会占用两个端口9200和9300
# 9200是ES节点与外部通讯使用的端口, 它是http协议的RESTful接口(各种CRUD操作都是走的该端口,如查询：http://localhost:9200/)
# 9300是ES节点之间通讯使用的端口。它是tcp通讯端口，集群间和TCPclient都走的它(java程序中使用ES时，在配置文件中要配置该端口)
# 在es cluster中每个node都会有分工，比如master, data, ingest等等
docker pull elasticsearch:7.16.1
docker run -d --name=elasticsearch -p9200:9200 -p9300:9300 -e "discovery.type=single-node" -e "xpack.security.enabled=false" elasticsearch:7.16.1

# kibana
# Web默认端口5601, 用来提供后端服务的访问。例如后端ES GUI http://docker-server:5601/app/home#/
docker pull kibana:7.16.1
docker run -d --name=kibana --link=elasticsearch -p5601:5601 kibana:7.16.1

# jaeger all-in-one
# Web查询 http://docker-server:16686/
docker run -d --name jaeger-es \
  --link=elasticsearch \
  -e SPAN_STORAGE_TYPE=elasticsearch \
  -e COLLECTOR_ZIPKIN_HOST_PORT=:9411 \
  -e ES_SERVER_URLS=http://elasticsearch:9200 \
  -e ES_TAGS_AS_FIELDS_ALL=true \
  -p 5775:5775/udp \
  -p 6831:6831/udp \
  -p 6832:6832/udp \
  -p 5778:5778 \
  -p 16686:16686 \
  -p 14268:14268 \
  -p 14250:14250 \
  -p 9411:9411 \
  jaegertracing/all-in-one
```

#### zk command
```console
# check zk node as follower or leader
echo stat | nc localhost 2181| grep Mode

# enter zkCli mode
/bin/zkCli.sh -server 172.18.0.3:2181    
# list zNodes
ls /  
# create zNode 'zk_test' to save data
create /zk_test <data>  
# get data saved in zk_test
get /zk_test   
```

#### spark command
```console
> spark-submit --class org.apache.griffin.measure.Application --master yarn-client \
 --queue default --verbose griffin-measure.jar env.json config.json local,local
```
#### kafka command
```console
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