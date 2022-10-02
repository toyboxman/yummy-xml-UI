
---

### kubernetes
+ [解决什么问题](#problem-digest)
+ [如何使用](#use-digest)
+ [如何排错](#troubleshooting)

---

#### problem-digest
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