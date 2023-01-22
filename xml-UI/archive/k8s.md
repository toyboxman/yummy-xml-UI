
---

### kubernetes
+ [解决什么问题](#problem-digest)
+ [如何使用](#use-digest)
+ [如何排错](#troubleshooting)

---

#### operation
- [k8s handbook](https://lib.jimmysong.io/kubernetes-handbook/)
#### minikube
[minikube](https://minikube.sigs.k8s.io/docs/start/) is local Kubernetes, focusing on making it easy to learn and develop for Kubernetes.   
```console
# 根据架构资源选择安装类型，下面命令是用于 linux x86-64架构
curl -LO https://storage.googleapis.com/minikube/releases/latest/minikube-linux-amd64
sudo install minikube-linux-amd64 /usr/local/bin/minikube

# Start your cluster, 在Ubuntu 22.04上启动失败
$ minikube start
# 错误原因在于默认Ubuntu没有创建docker的group，只能通过sudo来操作dock儿命令
# 因此按照错误提示可以创建docker group来解决权限问题
  minikube v1.28.0 on Ubuntu 22.04
  Unable to pick a default driver. Here is what was considered, in preference order:
     docker: Not healthy: "docker version --format {{.Server.Os}}-{{.Server.Version}}" exit status 1: Got permission denied while trying to connect to the Docker daemon socket at unix:///var/run/docker.sock: Get "http://%2Fvar%2Frun%2Fdocker.sock/v1.24/version": dial unix /var/run/docker.sock: connect: permission denied
     docker: Suggestion: Add your user to the 'docker' group: 'sudo usermod -aG docker $USER && newgrp docker' <https://docs.docker.com/engine/install/linux-postinstall/>
  Alternatively you could install one of these drivers:
     kvm2: Not installed: exec: "virsh": executable file not found in $PATH
     podman: Not installed: exec: "podman": executable file not found in $PATH
     vmware: Not installed: exec: "docker-machine-driver-vmware": executable file not found in $PATH
     virtualbox: Not installed: unable to find VBoxManage in $PATH
     qemu2: Not installed: exec: "qemu-system-x86_64": executable file not found in $PATH

  Exiting due to DRV_NOT_HEALTHY: Found driver(s) but none were healthy. See above for suggestions how to fix installed drivers.

# 检查创建docker组
~/k8s$ sudo usermod -aG docker $USER && newgrp docker
~/k8s$ groups
docker adm cdrom sudo dip plugdev lpadmin lxd sambashare jaeger

# Interact with your cluster
~/k8s$ kubectl get po -A
# 由于没有安装kubectl 提示错误
Command 'kubectl' not found, but can be installed with:
sudo snap install kubectl
# snap安装kubectl后，可以看到 minikube 创建出来的控制面组件
# 也可以用minikube下载, minikube kubectl -- get po -A
# control plane in kubernetes terminology 包括 kube-apiserver/kube-controller-manager/kube-scheduler/kubelet/kube-proxy/etcd
NAMESPACE     NAME                               READY   STATUS    RESTARTS       AGE
kube-system   coredns-565d847f94-b2tcf           1/1     Running   0              6h5m
kube-system   etcd-minikube                      1/1     Running   0              6h5m
kube-system   kube-apiserver-minikube            1/1     Running   0              6h5m
kube-system   kube-controller-manager-minikube   1/1     Running   0              6h5m
kube-system   kube-proxy-5njdq                   1/1     Running   0              6h5m
kube-system   kube-scheduler-minikube            1/1     Running   0              6h5m
kube-system   storage-provisioner                1/1     Running   1 (6h4m ago)   6h5m

# 通过命令可以启动状态板，在Ubuntu上启动失败，原因不明
# 正常结果应如此 http://www.mtitek.com/tutorials/kubernetes/minikube_dashboard.php
$ minikube dashboard

# 后面就可以正常部署 Service/LoadBalancer/Ingress 
# 可参考前面Minikube链接文档描述 Handbook Addons
```
[Kubernetes文档](https://kubernetes.io/zh-cn/docs/tutorials/hello-minikube/) 也包含minikube的配置使用  
[部署hello样例应用](https://kubernetes.io/zh-cn/docs/tutorials/hello-minikube/)
```console
# 使用 kubectl create 命令创建管理 Pod 的 Deployment。该 Pod 根据提供的 Docker 镜像运行容器。
$ kubectl create deployment hello-node --image=registry.k8s.io/e2e-test-images/agnhost:2.39 -- /agnhost netexec --http-port=8080
# kubectl get deployments --namespace cert-manager 指定ns 或者查询全部
$ kubectl get deployments -A
NAMESPACE              NAME                        READY   UP-TO-DATE   AVAILABLE   AGE
kube-system            coredns                     1/1     1            1           4d
kubernetes-dashboard   dashboard-metrics-scraper   0/1     1            0           3d17h
kubernetes-dashboard   kubernetes-dashboard        0/1     1            0           3d17h

$ kubectl get pods -A
NAMESPACE              NAME                                        READY   STATUS             RESTARTS     AGE
kube-system            coredns-565d847f94-b2tcf                    1/1     Running            0            4d
kube-system            etcd-minikube                               1/1     Running            0            4d
kube-system            kube-apiserver-minikube                     1/1     Running            0            4d
kube-system            kube-controller-manager-minikube            1/1     Running            0            4d
kube-system            kube-proxy-5njdq                            1/1     Running            0            4d
kube-system            kube-scheduler-minikube                     1/1     Running            0            4d
kube-system            storage-provisioner                         1/1     Running            1 (4d ago)   4d
kubernetes-dashboard   dashboard-metrics-scraper-b74747df5-wb9q7   0/1     ImagePullBackOff   0            3d17h
kubernetes-dashboard   kubernetes-dashboard-57bbdc5f89-llslc       0/1     ImagePullBackOff   0            3d17h

# 默认情况下，Pod 只能通过 Kubernetes 集群中的内部 IP 地址访问。 要使得 hello-node 容器可以从 Kubernetes 虚拟网络的外部访问，你必须将 Pod 暴露为 Kubernetes Service
kubectl expose deployment hello-node --type=LoadBalancer --port=8080
kubectl get services

# 清理集群中创建的资源
kubectl delete service hello-node
kubectl delete deployment hello-node
minikube stop
minikube delete
```
[部署 jaeger helm chart](https://artifacthub.io/packages/helm/jaegertracing/jaeger-operator)
```console
helm install jaegertracing/jaeger-operator
# Add the Jaeger Tracing Helm repository
$ helm repo add jaegertracing https://jaegertracing.github.io/helm-charts
$ helm repo list
NAME         	URL                                        
jaegertracing	https://jaegertracing.github.io/helm-charts

$ kubectl create namespace observability
namespace/observability created
$ kubectl delete namespace observability
namespace "observability" deleted
# namespace Aliases ns
$ kubectl create ns observability
namespace/observability created

$ kubectl get ns
NAME                   STATUS   AGE
default                Active   3d20h
kube-node-lease        Active   3d20h
kube-public            Active   3d20h
kube-system            Active   3d20h
kubernetes-dashboard   Active   3d13h
observability          Active   77s

# install the chart with the release name my-release in observability namespace
$ helm install my-release jaegertracing/jaeger-operator -n observability
Error: INSTALLATION FAILED: unable to build kubernetes objects from release manifest: [unable to recognize "": no matches for kind "Certificate" in version "cert-manager.io/v1", unable to recognize "": no matches for kind "Issuer" in version "cert-manager.io/v1"]
# 错误原因是本地没有安装 cert-manager 1.6.1+ installed, or certificate for webhook service in a secret
```
kubectl安装cert-manager[[1](https://cert-manager.io/docs/installation/),[2](https://cert-manager.io/v1.1-docs/installation/kubernetes/#installing-with-regular-manifests)]  
[helm安装cert-manager](https://cert-manager.io/v1.1-docs/installation/kubernetes/#installing-with-helm)   
[配置issuer](https://cert-manager.io/v1.1-docs/installation/kubernetes/#configuring-your-first-issuer)
```console
$ kubectl apply -f https://github.com/cert-manager/cert-manager/releases/download/v1.10.1/cert-manager.yaml
$ kubectl get pods --namespace cert-manager
NAME                                      READY   STATUS    RESTARTS   AGE
cert-manager-74d949c895-996n6             1/1     Running   0          4m22s
cert-manager-cainjector-d9bc5979d-9tqzj   1/1     Running   0          4m22s
cert-manager-webhook-84b7ddd796-sn6dp     1/1     Running   0          4m22s

# installs a chart archive. The install argument must be a chart reference, a path to a packaged chart,
# a path to an unpacked chart directory or a URL
# override chart中预设值, 使用'--values' 传入一个file
# 或者'--set' 从命令行传入配置, 强制string类型就用'--set-string'. 
# 万一 value 规模大 使用 '--set-file' 来从指定文件中读取.
# $ helm install -f myvalues.yaml myredis ./redis
# $ helm install --set name=prod myredis ./redis
# $ helm install --set-string long_int=1234567890 myredis ./redis
# $ helm install --set-file my_script=dothings.sh myredis ./redis
# 可以多次指定 '--values'/'-f' '--set' 进行配置修改
# $ helm install -f myvalues.yaml -f override.yaml  myredis ./redis
# $ helm install --set foo=bar --set foo=newbar  myredis ./redis
# To check the generated manifests of a release without installing the chart, the '--debug' and '--dry-run' flags can be combined.
# If --verify is set, the chart MUST have a provenance file, and the provenance file MUST pass all verification steps.
# helm install [NAME] [CHART] [flags]
# 不指定ns 默认放到default namespace
$ helm install jaeger jaegertracing/jaeger-operator
NAME: jaeger
LAST DEPLOYED: Mon Jan  2 02:05:47 2023
NAMESPACE: default
STATUS: deployed
REVISION: 1
TEST SUITE: None
NOTES:
jaeger-operator is installed.
$ helm delete jaeger  
$ helm search repo jaegertracing
NAME                         	CHART VERSION	APP VERSION	DESCRIPTION                              
jaegertracing/jaeger         	0.65.4       	1.39.0     	A Jaeger Helm chart for Kubernetes       
jaegertracing/jaeger-operator	2.38.0       	1.39.0     	jaeger-operator Helm chart for Kubernetes

# 指定ns -n, --namespace namespace scope for this request
$ helm install jaeger-op jaegertracing/jaeger-operator -n observability 
$ helm list -n observability
NAME     	NAMESPACE    	REVISION	UPDATED                                	STATUS  	CHART                 	APP VERSION
jaeger-op	observability	1       	2023-01-02 06:05:11.685549648 -0500 EST	deployed	jaeger-operator-2.38.0	1.39.0          
$ kubectl get pods -n observability
NAME                                         READY   STATUS             RESTARTS   AGE
jaeger-op-jaeger-operator-7d7b58d48b-89p9p   0/1     ImagePullBackOff   0          27m
# ImagePullBackOff 当Kubernetes无法获取Pod中某个容器的镜像时，将出现此错误。
# 可能原因：
# Image doesn’t exist. / Image tag or name is incorrect. / Image is private, and there is an authentication failure.
# Network issue. / Registry name is incorrect. / Container registry rate limits.
# 通过describe/logs命令可以知道下载失败由于docker hub限制次数导致，因此docker login后或换个下载源
$ kubectl describe pod jaeger-op-jaeger-operator-7d7b58d48b-89p9p -n observability
$ kubectl logs jaeger-op-jaeger-operator-7d7b58d48b-89p9p -n observability
Error from server (BadRequest): container "jaeger-op-jaeger-operator" in pod "jaeger-op-jaeger-operator-7d7b58d48b-89p9p" is waiting to start: trying and failing to pull image

# https://lib.jimmysong.io/kubernetes-handbook/storage/secret/
# https://kubernetes.io/docs/tasks/configure-pod-container/pull-image-private-registry/
# 根据配置文件、目录或指定的literal-value创建secret。secret可以保存为一个或多个key/value信息。
kubectl create secret generic regcred \
    --from-file=.dockerconfigjson=/home/jaeger/.docker/config.json \
    --type=kubernetes.io/dockerconfigjson

$ kubectl get secret
NAME                           TYPE                             DATA   AGE
jaeger-operator-service-cert   kubernetes.io/tls                3      42h
regcred                        kubernetes.io/dockerconfigjson   1      4m57s
$ kubectl get secret regcred --output=yaml
# 可以看到regcred这个文件中把docker的configjson整个内容编码成其中一个字段
$ kubectl get secret regcred --output="jsonpath={.data.\.dockerconfigjson}" | base64 --decode

# docker 和 k8s缓存本地image的位置不同，因此docker登录后也需要把credentials如此注册，k8s后续才能下载成功
# imagePullSecrets数组类型直接用set失败 at <.Values.image.imagePullSecrets>: range can't iterate over [regcred]
$ helm install jaeger-op jaegertracing/jaeger-operator --set image.imagePullSecrets=regcred
# 创建一个本地yaml文件list数组
$ nano image-pull-secrets.yaml
# 设定内容参看默认值 https://artifacthub.io/packages/helm/jaegertracing/jaeger-operator?modal=values
image:
 repository: jaegertracing/jaeger-operator
 tag: 1.39
 imagePullSecrets: 
   name: regcred
# Default values for jaeger-operator.
# This is a YAML-formatted file.
# Declare variables to be passed into your templates.
$ helm install jaeger-op jaegertracing/jaeger-operator -f image-pull-secrets.yaml 

# 查看安装的release变量
$ helm get values jaegertracing/jaeger-operator
Error: releaseContent: Release name is invalid: jaegertracing/jaeger-operator
# get values只能用于安装好的release资源
$ helm get values jaeger-op -o=yaml
USER-SUPPLIED VALUES:
imagePullSecrets:
 name: regcred
$ helm get values jaeger-op -o json
{"imagePullSecrets":[{"name":"regcred"}]}

# 按照 Creating a new Jaeger instance in https://artifacthub.io/packages/helm/jaegertracing/jaeger-operator
# 可先把配置写入本地文件再读入 kubectl apply -f simplest.yaml
$ kubectl apply -f - <<EOF
apiVersion: jaegertracing.io/v1
kind: Jaeger
metadata:
  name: simplest
EOF
# 执行完通过 get pod并没有发现 jaeger pod产生，可以通过查看operator的log，似乎当前chart不是很稳定
kubectl logs jaeger-op-jaeger-operator-7d7b58d48b-mncth

# 换成jaeger helm chart 来创建 in https://artifacthub.io/packages/helm/jaegertracing/jaeger
$ cat <<EOF > all-in-one.yaml
# all-in-one image version
tag: latest
provisionDataStore:
  cassandra: false
allInOne:
  enabled: true
  resources:
    limits:
      cpu: 500m
      memory: 512Mi
    requests:
      cpu: 256m
      memory: 128Mi
storage:
  # allowed values (cassandra, elasticsearch)
  type: none
agent:
  enabled: false
collector:
  enabled: false
query:
  enabled: false
EOF

# allInOne配置不支持.imagePullSecrets没法设定regcred，下载失败 https://artifacthub.io/packages/helm/jaegertracing/jaeger?modal=values
$ helm install jaeger jaegertracing/jaeger --values all-in-one.yaml
# 换成多节点部署方式
helm install jaeger jaegertracing/jaeger -f - <<EOF
tag: latest
provisionDataStore:
  cassandra: false
  elasticsearch: true
storage:
  # allowed values (cassandra, elasticsearch)
  type: elasticsearch
  elasticsearch:
    usePassword: false
agent:
  # k8s pull image secret from docker hub credentials
  imagePullSecrets: 
  - name: regcred
collector:
  imagePullSecrets: 
  - name: regcred
query:
  imagePullSecrets: 
  - name: regcred
EOF
# 多节点方式执行后可以看到创建出来es cluster和agent collector query的pod，状态还有问题
$ kubectl get pod
NAME                                         READY   STATUS             RESTARTS        AGE
elasticsearch-master-0                       0/1     Running            0               10h
elasticsearch-master-1                       0/1     Pending            0               10h
elasticsearch-master-2                       0/1     Pending            0               10h
jaeger-agent-ksvbz                           1/1     Running            0               10h
jaeger-collector-7d764bd744-dnqrt            0/1     CrashLoopBackOff   142 (59s ago)   10h
jaeger-query-856cd66d9-zx82b                 1/2     CrashLoopBackOff   142 (30s ago)   10h
# 排错发现是es cluster没有启动
# Readiness probe failed: Waiting for elasticsearch cluster to become ready
$ kubectl events elasticsearch-master-0
$ kubectl describe pod elasticsearch-master-0
$ kubectl logs elasticsearch-master-0
# 三个节点ES cluster chart似乎也有问题，查看默认配置，在yaml中增加elasticsearch配置，把3节点修改成单节点
helm show values elastic/elasticsearch
helm install jaeger jaegertracing/jaeger -f - <<EOF
tag: latest
provisionDataStore:
  cassandra: false
  elasticsearch: true
storage:
  # allowed values (cassandra, elasticsearch)
  type: elasticsearch
  elasticsearch:
    usePassword: false
elasticsearch:
    replicas: 1
    # 如果单节点启动不了，可以设定下面两个值
    # minimumMasterNodes: 1
    # clusterHealthCheckParams: 'wait_for_status=yellow&timeout=1s'
agent:
  # k8s pull image secret from docker hub credentials
  imagePullSecrets: 
  - name: regcred
collector:
  imagePullSecrets: 
  - name: regcred
query:
  imagePullSecrets: 
  - name: regcred
EOF
# 启动成功
$ kubectl get pod
NAME                                         READY   STATUS    RESTARTS       AGE
elasticsearch-master-0                       1/1     Running   0              3h9m
jaeger-agent-hjdtk                           1/1     Running   0              3h9m
jaeger-collector-76d964985-4rpmd             1/1     Running   3 (3h8m ago)   3h9m
jaeger-query-6d59858d8d-ms794                2/2     Running   3 (3h8m ago)   3h9m
query.service.nodePorts
# query pod是一个172开头的内网地址，如何从外部访问还需要配置端口映射或转发
# export POD_NAME=$(kubectl get pods --namespace default -l "app.kubernetes.io/instance=jaeger,app.kubernetes.io/component=query" -o jsonpath="{.items[0].metadata.name}")
# kubectl port-forward --namespace default $POD_NAME 8080:16686
# 直接配置一个端口转发 浏览器访问成功 http://127.0.0.1:8080/
$ kubectl port-forward jaeger-query-6d59858d8d-ms794 8080:16686

# 新问题是jaeger UI只能在本机通过127.0.0.1这个地址访问，通过本机IP访问失败
# 开始判断是iptables设置问题，查看INPUT没有设定drop action，查看网络连接状态
# 能看到 5901(VNC)/8080(http-alt)/22(SSH) 实际上都存在，只是8080绑定到loopback地址，因此 nc 只有5901/22能成功
$ ss -ta
State          Recv-Q      Send-Q                   Local Address:Port                 Peer Address:Port      Process     
LISTEN         0           5                              0.0.0.0:5901                      0.0.0.0:*                     
LISTEN         0           4096                         127.0.0.1:http-alt                  0.0.0.0:*                     
LISTEN         0           128                            0.0.0.0:ssh                       0.0.0.0:*                     
ESTAB          0           0                       10.176.217.242:5901                10.117.180.72:53883                 
ESTAB          0           0                         192.168.49.1:41114                192.168.49.2:8443                  
LISTEN         0           5                                 [::]:5901                         [::]:*                     
LISTEN         0           4096                             [::1]:http-alt                     [::]:*                     
LISTEN         0           128                               [::]:ssh                          [::]:*                     
# 重新配置一个端口转发增加绑定地址 https://kubernetes.io/docs/reference/generated/kubectl/kubectl-commands#port-forward
$ kubectl port-forward --address 0.0.0.0 jaeger-query-6d59858d8d-2dvz7 8080:16686
Forwarding from 0.0.0.0:8080 -> 16686
Handling connection for 8080
# 端口连接状态已改变 外部浏览器访问成功 http://10.176.217.242:8080/
$ nc -zv 10.176.217.242 8080
Connection to 10.176.217.242 8080 port [tcp/http-alt] succeeded!
$ ss -ta
State          Recv-Q      Send-Q              Local Address:Port                    Peer Address:Port       Process      
LISTEN         0           4096                 0.0.0.0:http-alt                          0.0.0.0:*                    
# 可以一次映射多个端口 kubectl port-forward --address 0.0.0.0 jaeger-query 8080:16686 8081:16687
# 如要 collector 接收JaegerThriftSpanExporter数据还需要映射 14268
# kubectl port-foward 是阻塞式, 第一个执行后进程就会等待阻塞第二个执行，只能如下才能同时执行两个pod上映射
$ kubectl port-forward --address 0.0.0.0 jaeger-query-6d59858d8d-2dvz7 8080:16686 & \
  kubectl port-forward --address 0.0.0.0 jaeger-collector-76d964985-cgzbs 14268:14268 &
# 还有一些可让外部访问k8s cluster内部端口方式 https://alesnosek.com/blog/2017/02/14/accessing-kubernetes-pods-from-outside-of-the-cluster/

# 查看 helm chart 中的变量
helm show values jaegertracing/jaeger
$ helm show values jaegertracing/jaeger-operator
# show values只能用于chart 不能用于安装好的release资源
$ helm show values jaeger-op
Error: failed to download "jaeger-op"

$ minikube cache list
$ helm delete jaeger-op -n observability

```

#### problem-digest
##### 云计算收益计算
- TCO(Total cost of ownership)是财务管理术语，帮助购买方和持有人确定直接和间接产品服务的成本。OPEX(operating expense)指的是運行企業的持續性、消耗性的支出，與之對照的是資本支出CAPEX(capital expenditure)。例如：購買影印機的支出屬於資本支出(CAPEX)，而紙張、墨水、電力、維修的費用則屬於營業費用(OPEX)。在企業中，營運費用可能包含了員工薪資、設備租金、場地租金。 
- 云化是一个趋势，理论上多方共赢。我们测算过，假设云上资源能够有80%利用率，自建云是共有云的1.6-2倍。云的使用者需要改进传统观念，认为部署上去就可以了。需要尽可能的让云上资源多利用，比如cpu,带宽，内存，磁盘等，同时利用好云厂商提供的工具，如监控，性能，扩缩容等。不断做调优，达到成本和业务均衡。TCO算清楚，上云+混合云大势所趋。私有云不能只算服务器硬件成本，还有机房租用成本，IT运维人工成本，随时扩容的风险成本，算上这些再来比价，才知道AWS赚了多少，是否合理。
- 云服务器是Opex, 物理服务器是Capex,自建云是固定资产，在报表里好看啊。租的话就是纯支出了。固定资产折旧也是资产，云服务是负债，是应付账款，万一哪天内存cpu也像之前显卡一样算力入资，价格暴涨，那资产就不是折价而是升值了，而负债永远是负债，破产了第一个清算要还债。1.自建或上云首先问问CFO同意不同意, 2.你团队有没有拥有这些能力的工程师，采购物理服务器，你要养Infra的工程师，要写脚本备份数据库，要做load balance, 要做fail over, 要检查备份是否成功，要检查和配置防火墙，要部署杀毒软件，要用到VMware/HyperV, 要用到Veritas, 要用到EMC, 群晖，要做域控的管理和维护，要做应用服务器数据服务器的防勒索，要做磁带备份，要做远程访问安全控制，要做VPN, CISCO, FORTINET, 要做……，你得给出不低的工资才能招聘到这样的人才。所以，大公司养得起这样一个Infra团队，可以自己采买，中小型公司，还是上云吧，再签一个MSC。或中小公司使用vmware vsan加个nsx再加个veeam，请一个工程师就搞定了
- 大多数共有云用户成本高的原因是因为不懂云不会用，他们的架构和运维人员大都是传统服务器托管时代过来的，喜欢租虚拟机，云原生的都不用，什么都自己搭。按量计费和弹性伸缩用的也不好，更喜欢包月那种，大量资源买了其实占用率很低。要依赖DevOps, 持续集成和交付。
- 看aws的利润就能看到，利润特别高，高的另国内云厂商咋舌，这对客户来说不是好消息，至于aws计算服务是否有超卖（至少国内有的，超卖2倍是基操）应该有的，这是主要利润来源。超卖还不是因为客户的架构设计不合理，买了大量性能富裕的资源不用。要是都设计的好，占用率80%，结合弹性伸缩，云厂商想超卖，又拿什么超卖呢. 阿里云我们测算过租用6个月，相当于购买相应新服务器成本，相信很快基于k8s本地云运维系统更加成熟，完全替代云厂商
- 相同模式下，自建云是共有云成本的1.5-2倍之间。为什么你会在共有云上消耗那么多，主要没有分析清楚你的业务和所用的共有云资源是否匹配。说白了就是杀鸡用了牛刀。 自建有很多你没算进去，包括机房类型(托管tier 3机房)，运维人员，出问题后的业务迁移等等，这些隐形支出，你觉得可能不重要，一旦出问题都是需要你自己承担。共有云机房基本由云厂家承担。
- aws我觉得还是比较贵，拿我们的一个项目为例，ec2一共11台32c/128g/400ssd每月成本3w左右，s3不仅空间收费，每次扫描一个文件都要收费，离线任务就算优化后每月也要1w多，rds每月7k，加上其他ec2平均每月成本6w，和当初预估的5w差了1w。aws的DynamoDB更贵，优化后每月成本8w。AWS的账单理解成本确实很高，而且纯后付费的模式也难以提前锁定成本。玩過云,尤其是AWS就會知道,它把服務拆得非常散, 單項計件或計時收費,坑得很。只看EC2是便宜,但你想把它打造成一個可佈署運營的環境,所費就不菲了。
- 上云五年了，月阿里云开支在15万人民币，华为云2万左右。公司一台服务器都没有了。 几个优点: 故障非常少，三网时延都低，扩容快，弹性带宽。公有云如果你租用的region地点不同，真实的sla是不一样的。有的地方不是云厂商自建机房，也是租的，那样sla就大打折扣，电啊空调啊什么的都不在云厂商自己控制之下。但是合同上呢，承诺的sla又不能分别写不同的。什么？你说异地灾备，异地多活？穷鬼那点夏利的钱，就别提劳斯莱斯的功能行吗. 
- 两台服务器，光租机房服务器位置一项一年下来就比云服务贵，再加上买服务器、网络维护，三天两头“网络波动”，两个网络服务商，来回切换，一个出问题就赶紧手动切另一个，成本高质量还差，用了四个月就换云服务了。
##### k8s能解决什么问题

没有k8s之前， 上线一个新服务，只能通过运维团队处理，没有办法把任务切分给其他业务团队，这就是原始DevOps，包括以下这些工作:

+ Configure DNS (often internal service layers and external public-facing)
+ Configure load balancers (often internal services and public-facing)
+ Configure shared access to files (large NFS servers, clustered file systems, etc.)
+ Configure clustering software (databases, service layers, etc.)
+ Configure webserver cluster (could be 10 or 50 servers)

大部分这些事务都可以通过配置管理自动化来做，但实际上的配置依旧复杂，因为大量系统和服务都有不同的配置文件，不同的文件格式。有些工具如Augeas，通过一个翻译器来转换不同配置文件，但这个方法是anti-pattern，试图范式化(normalize)大量不同配置文件，实际使用时也不容易

如今应用Kubernetes, 上线一个新服务基本上如下:

+ Configure Kubernetes YAML/JSON.
+ Submit it to the Kubernetes API (kubectl create -f service.yaml)

Kubernetes解决那些问题可以看看
+ [容器发展史](https://mp.weixin.qq.com/s/ccFkJJz97KcuXdO3r5zdXA)
+ [k8s简介](https://mp.weixin.qq.com/s/JSipXCkLAMIpNa4UGDRogQ)
    - [图解k8s资源定义node/cluster/pod](https://zhuanlan.zhihu.com/p/102663768)
+ [部署Kubernetes的代价](https://mp.weixin.qq.com/s/zzn61ADe2RA7K0L4-EMAOA)
+ Kubernetes 学习曲线[[1](https://mp.weixin.qq.com/s/H8guIwQlMoKnN9OI_Om3RA), [2](https://opensource.com/article/19/6/kubernetes-basics)]
+ [Azure Kubernetes Service](https://docs.microsoft.com/en-us/azure/aks/concepts-clusters-workloads)
+ Kubernetes系列[[2](https://mp.weixin.qq.com/s/OZGLS0UsN9PiMqMaOzzpJA),[3](https://mp.weixin.qq.com/s/EHoSVCDFZoQar32nkl2-Mw),[4](https://mp.weixin.qq.com/s/ZjqEy4hCnGEcBdVfrFgzRQ)]
+ [Kubernetes网络](https://mp.weixin.qq.com/s/SClypa7ahDtnxsuWZdu8BQ)
    + [Pod间容器通信](https://mp.weixin.qq.com/s/I2qA29Lpkdnowd8Txv7ftg)
    + [Kubernetes 网络流量流转路径](https://mp.weixin.qq.com/s/CDzcy5MwzU9GwQfRp1mpPQ)
+ [命名空间](https://mp.weixin.qq.com/s/KzhBWfbHDZaqFqN-jUsUCw)

#### use-digest
+ k8s怎么用
    - node  
    k8s集群中每个节点(可认为物理机器), Node IP是节点的IP地址,即k8s集群中每个节点的物理网卡的IP地址
    - pod   
    节点上资源划分，k8s基本服务运行单元。Pod IP每个Pod的IP地址，虚拟的二层网络。Docker Engine根据docker0网桥的IP地址段进行分配的。k8s里的一个Pod容器通过Pod IP访问另一个Pod里的容器，真实的流量通过Node IP物理网卡进出的。Kubernetes 为 Pod 提供自己的 IP 地址，并为一组 Pod 提供相同的 DNS 名， 并且可以在它们之间进行负载均衡.当一个工作 Node 挂掉后, 在 Node 上运行的 Pod 也会消亡。 ReplicaSet 会自动地通过创建新的 Pod 驱动集群回到目标状态，以保证应用程序正常运行。尽管每个 Pod 都有一个唯一的 IP 地址，但是如果没有 Service ，这些 IP 不会暴露在集群外部。Service 允许你的应用程序接收流量。
    - service[[1](https://kubernetes.io/zh-cn/docs/tutorials/kubernetes-basics/expose/expose-intro/), [2](https://kubernetes.io/zh-cn/docs/concepts/services-networking/service/)]   
    将运行在一组 Pods 上的应用程序公开为网络服务的抽象方法, 使用 Service 暴露应用, Kubernetes 中的服务(Service)是一种抽象概念，它定义了 Pod 的逻辑集和访问 Pod 的协议。Service 使从属 Pod 之间的松耦合成为可能。。Service 在 Kubernetes 中是一个 REST 对象，和 Pod 类似。 像所有的 REST 对象一样，Service 定义可以基于 POST 方式，请求 API server 创建新的实例。Service 的IP地址称为Cluster IP, 虚拟的IP，仅作用于Service这个对象，由k8s管理和分配IP地址（来源于Cluster IP地址池）单独的Cluster IP不具备TCP/IP通信基础。
    - 云原生服务发现   
    如果你想要在应用程序中使用 Kubernetes API 进行服务发现，则可以查询 API 服务器 的 Endpoints 资源，只要服务中的 Pod 集合发生更改，Endpoints 就会被更新。对于非本机应用程序，Kubernetes 提供了在应用程序和后端 Pod 之间放置网络端口或负载均衡器的方法。

Kubernetes使用学习需要一些时间，安装配置一个集群需要琐屑的操作，下面是一些关于简单使用学习的文章
+ [k8s best practice](https://mp.weixin.qq.com/s/KLrQ2n_Kk_DR6puIsgmOqg)
+ [工商银行容器在线纵向扩容的创新实践](https://mp.weixin.qq.com/s/VFYblOnEG2VbjnBbaqXdiA)
+ [K8S高可用集群架构](https://mp.weixin.qq.com/s/uyLBMHmr0cRsRLGs4nwnjg)
+ [非云环境中Kubernetes的配置和运行](https://mp.weixin.qq.com/s/EdSxylOX6Yk77CXBak-3uQ)
+ [Makisu构建容器镜像](https://mp.weixin.qq.com/s/avUG1v9XHCGWAkNg3qG_Dw)
+ [Mac配置Kubernetes](https://mp.weixin.qq.com/s/A0hogBew98ekE-HDXVYNkA)  
    `MicroK8s需要通过snap install安装到ubuntu vm上，国内安装速度较慢。`替代使用[k3s](https://k3s.io/)或[Minikube](https://github.com/kubernetes/minikube)
+ Tools for k8s[[1](https://mp.weixin.qq.com/s/nsfr6sArLotsB9U4MU7-wg), [2](https://mp.weixin.qq.com/s/CAroslMhKt21y6_XYYXqQg)]
+ [k9s集群管理的工具](https://mp.weixin.qq.com/s/Jwy42KmPhMC_1sfFrizoLQ)
+ [Prometheus和Grafana监控Kubernetes集群](https://mp.weixin.qq.com/s/ZvbK2AMNugi6DHHqBw9HCg)
+ [ansible自动化部署k8s](https://github.com/easzlab/kubeasz)
+ [基本kubectl和Helm命令](https://mp.weixin.qq.com/s/JpCVNcU2hrfHS2d_Xcttvw)
+ [使用Traefik引导Kubernetes流量](https://mp.weixin.qq.com/s/edMBhL5XtyAsILHSeTWBdA)
+ [数百个ZooKeeper实例迁移到Kubernetes](https://mp.weixin.qq.com/s/AaVTgZc_vUtKD_WytKIW5A)
    `ZooKeeper服务器迁移四步骤描述`
+ [中文版文档](https://linfan1.gitbooks.io/kubernetes-chinese-docs/content/index.html)
+ [Awesome-Kubernetes](https://ramitsurana.gitbooks.io/awesome-kubernetes/content/)

```sh
root@ncpmaster:/home/pksadmin# kubectl describe pod coredns-fb8b8dccf-q78fl -n kube-system
kubectl get ds -n kube-system
```

#### troubleshooting
+ [基于eBPF的Kubernetes问题排查](https://mp.weixin.qq.com/s/--8iEky-XCL0Y0bYM52iHQ)