
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
+ [k8s简介](https://mp.weixin.qq.com/s/JSipXCkLAMIpNa4UGDRogQ)
+ [部署Kubernetes的代价](https://mp.weixin.qq.com/s/zzn61ADe2RA7K0L4-EMAOA)
+ Kubernetes 学习曲线[[1](https://mp.weixin.qq.com/s/H8guIwQlMoKnN9OI_Om3RA), [2](https://opensource.com/article/19/6/kubernetes-basics)]
+ [Azure Kubernetes Service](https://docs.microsoft.com/en-us/azure/aks/concepts-clusters-workloads)
+ Kubernetes系列[[2](https://mp.weixin.qq.com/s/OZGLS0UsN9PiMqMaOzzpJA),[3](https://mp.weixin.qq.com/s/EHoSVCDFZoQar32nkl2-Mw),[4](https://mp.weixin.qq.com/s/ZjqEy4hCnGEcBdVfrFgzRQ)]
+ [Pod间容器通信 ](https://mp.weixin.qq.com/s/I2qA29Lpkdnowd8Txv7ftg)
+ [命名空间](https://mp.weixin.qq.com/s/KzhBWfbHDZaqFqN-jUsUCw)

#### use-digest
+ k8s怎么用

Kubernetes使用学习需要一些时间，安装配置一个集群需要琐屑的操作，下面是一些关于简单使用学习的文章
+ [k8s best practice](https://mp.weixin.qq.com/s/KLrQ2n_Kk_DR6puIsgmOqg)
+ [K8S高可用集群架构](https://mp.weixin.qq.com/s/uyLBMHmr0cRsRLGs4nwnjg)
+ [非云环境中Kubernetes的配置和运行](https://mp.weixin.qq.com/s/EdSxylOX6Yk77CXBak-3uQ)
+ [Makisu构建容器镜像](https://mp.weixin.qq.com/s/avUG1v9XHCGWAkNg3qG_Dw)
+ [Mac配置Kubernetes](https://mp.weixin.qq.com/s/A0hogBew98ekE-HDXVYNkA)
    MicroK8s需要通过snap install安装到ubuntu vm上，国内安装速度较慢。替代使用[k3s](https://k3s.io/)或[Minikube](https://github.com/kubernetes/minikube)
+ Tools for k8s[[1](https://mp.weixin.qq.com/s/nsfr6sArLotsB9U4MU7-wg), [2](https://mp.weixin.qq.com/s/CAroslMhKt21y6_XYYXqQg)]
+ [k9s集群管理的工具](https://mp.weixin.qq.com/s/Jwy42KmPhMC_1sfFrizoLQ)
+ [Prometheus和Grafana监控Kubernetes集群](https://mp.weixin.qq.com/s/ZvbK2AMNugi6DHHqBw9HCg)
+ [ansible自动化部署k8s](https://github.com/easzlab/kubeasz)
+ [基本kubectl和Helm命令](https://mp.weixin.qq.com/s/JpCVNcU2hrfHS2d_Xcttvw)
+ [使用Traefik引导Kubernetes流量](https://mp.weixin.qq.com/s/edMBhL5XtyAsILHSeTWBdA)
+ [数百个ZooKeeper实例迁移到Kubernetes](https://mp.weixin.qq.com/s/AaVTgZc_vUtKD_WytKIW5A)
+ [中文版文档](https://linfan1.gitbooks.io/kubernetes-chinese-docs/content/index.html)
+ [Awesome-Kubernetes](https://ramitsurana.gitbooks.io/awesome-kubernetes/content/)

```
root@ncpmaster:/home/pksadmin# kubectl describe pod coredns-fb8b8dccf-q78fl -n kube-system
kubectl get ds -n kube-system
```
