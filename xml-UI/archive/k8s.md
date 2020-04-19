
---

### kubernetes
+ [解决什么问题](#problem-digest)
+ [如何使用](#use-digest)

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

Kubernetes可以解决那些问题可以看看
+ Kubernetes 学习曲线[[1](https://mp.weixin.qq.com/s?__biz=MjM5NjQ4MjYwMQ==&mid=2664614519&idx=2&sn=924123937683f49e79fca00c71a4463c), [2](https://opensource.com/article/19/6/kubernetes-basics)]
+ [k8s简介](https://mp.weixin.qq.com/s?__biz=MjM5NjQ4MjYwMQ==&mid=2664615729&idx=1&sn=2c08423255bad3aa9005afa943960672)
+ [Azure Kubernetes Service](https://docs.microsoft.com/en-us/azure/aks/concepts-clusters-workloads)
+ Kubernetes系列[[2](https://mp.weixin.qq.com/s?__biz=MjM5NTU2MTQwNA==&mid=2650660650&idx=2&sn=0a7d468d92d58a683422796de938b29e),[3](https://mp.weixin.qq.com/s?__biz=MjM5NTU2MTQwNA==&mid=2650660702&idx=2&sn=caac340acbf9c4d22751f79e3c011024),[4](https://mp.weixin.qq.com/s?__biz=MjM5NTU2MTQwNA==&mid=2650660760&idx=3&sn=c66a40e21a26824b5a4fd3699c82cd9b)]
+ [Pod间容器通信 ](https://mp.weixin.qq.com/s?__biz=MzAwMDc2NjQ4Nw==&mid=2663495069&idx=1&sn=0dac4fead9cf936caa1adf0f62ab4162)
+ [命名空间](https://mp.weixin.qq.com/s?__biz=MjM5NjQ4MjYwMQ==&mid=2664615992&idx=2&sn=6682338284c2a1c1769c2f55ca49391e)

#### use-digest
+ k8s怎么用

Kubernetes使用学习需要一些时间，安装配置一个集群需要琐屑的操作，下面是一些关于简单使用学习的文章
+ [Mac配置Kubernetes](https://mp.weixin.qq.com/s/A0hogBew98ekE-HDXVYNkA)
    MicroK8s需要通过snap install安装到ubuntu vm上，国内安装速度较慢。替代使用[k3s](https://k3s.io/)或[Minikube](https://github.com/kubernetes/minikube)
+ [4 tools for k8s](https://mp.weixin.qq.com/s/nsfr6sArLotsB9U4MU7-wg)
+ [ansible自动化部署k8s](https://github.com/easzlab/kubeasz)
+ [基本kubectl和Helm命令](https://mp.weixin.qq.com/s/JpCVNcU2hrfHS2d_Xcttvw)
+ [使用Traefik引导Kubernetes流量](https://mp.weixin.qq.com/s/edMBhL5XtyAsILHSeTWBdA)
+ [中文版文档](https://linfan1.gitbooks.io/kubernetes-chinese-docs/content/index.html)
+ [Awesome-Kubernetes](https://ramitsurana.gitbooks.io/awesome-kubernetes/content/)

```
root@ncpmaster:/home/pksadmin# kubectl describe pod coredns-fb8b8dccf-q78fl -n kube-system
kubectl get ds -n kube-system
```
