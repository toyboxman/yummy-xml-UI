
---

### kubernetes
+ [���ʲô����](#problem-digest)
+ [���ʹ��](#use-digest)

---

#### problem-digest
##### k8s�ܽ��ʲô����

û��k8s֮ǰ�� ����һ���·���ֻ��ͨ����ά�ŶӴ���û�а취�������зָ�����ҵ���Ŷӣ������ԭʼDevOps������������Щ����:

+ Configure DNS (often internal service layers and external public-facing)
+ Configure load balancers (often internal services and public-facing)
+ Configure shared access to files (large NFS servers, clustered file systems, etc.)
+ Configure clustering software (databases, service layers, etc.)
+ Configure webserver cluster (could be 10 or 50 servers)

�󲿷���Щ���񶼿���ͨ�����ù����Զ�����������ʵ���ϵ��������ɸ��ӣ���Ϊ����ϵͳ�ͷ����в�ͬ�������ļ�����ͬ���ļ���ʽ����Щ������Augeas��ͨ��һ����������ת����ͬ�����ļ��������������anti-pattern����ͼ��ʽ��(normalize)������ͬ�����ļ���ʵ��ʹ��ʱҲ������

���Ӧ��Kubernetes, ����һ���·������������:

+ Configure Kubernetes YAML/JSON.
+ Submit it to the Kubernetes API (kubectl create -f service.yaml)

Kubernetes���Խ����Щ������Կ���
+ [k8s���](https://mp.weixin.qq.com/s/JSipXCkLAMIpNa4UGDRogQ)
+ [����Kubernetes�Ĵ���](https://mp.weixin.qq.com/s/zzn61ADe2RA7K0L4-EMAOA)
+ Kubernetes ѧϰ����[[1](https://mp.weixin.qq.com/s/H8guIwQlMoKnN9OI_Om3RA), [2](https://opensource.com/article/19/6/kubernetes-basics)]
+ [Azure Kubernetes Service](https://docs.microsoft.com/en-us/azure/aks/concepts-clusters-workloads)
+ Kubernetesϵ��[[2](https://mp.weixin.qq.com/s/OZGLS0UsN9PiMqMaOzzpJA),[3](https://mp.weixin.qq.com/s/EHoSVCDFZoQar32nkl2-Mw),[4](https://mp.weixin.qq.com/s/ZjqEy4hCnGEcBdVfrFgzRQ)]
+ [Pod������ͨ�� ](https://mp.weixin.qq.com/s/I2qA29Lpkdnowd8Txv7ftg)
+ [�����ռ�](https://mp.weixin.qq.com/s/KzhBWfbHDZaqFqN-jUsUCw)

#### use-digest
+ k8s��ô��

Kubernetesʹ��ѧϰ��ҪһЩʱ�䣬��װ����һ����Ⱥ��Ҫ��м�Ĳ�����������һЩ���ڼ�ʹ��ѧϰ������
+ [k8s best practice](https://mp.weixin.qq.com/s/KLrQ2n_Kk_DR6puIsgmOqg)
+ [Mac����Kubernetes](https://mp.weixin.qq.com/s/A0hogBew98ekE-HDXVYNkA)
    MicroK8s��Ҫͨ��snap install��װ��ubuntu vm�ϣ����ڰ�װ�ٶȽ��������ʹ��[k3s](https://k3s.io/)��[Minikube](https://github.com/kubernetes/minikube)
+ Tools for k8s[[1](https://mp.weixin.qq.com/s/nsfr6sArLotsB9U4MU7-wg), [2](https://mp.weixin.qq.com/s/CAroslMhKt21y6_XYYXqQg)]
+ [k9s��Ⱥ����Ĺ���](https://mp.weixin.qq.com/s/Jwy42KmPhMC_1sfFrizoLQ)
+ [ansible�Զ�������k8s](https://github.com/easzlab/kubeasz)
+ [����kubectl��Helm����](https://mp.weixin.qq.com/s/JpCVNcU2hrfHS2d_Xcttvw)
+ [ʹ��Traefik����Kubernetes����](https://mp.weixin.qq.com/s/edMBhL5XtyAsILHSeTWBdA)
+ [���ٸ�ZooKeeperʵ��Ǩ�Ƶ�Kubernetes](https://mp.weixin.qq.com/s/AaVTgZc_vUtKD_WytKIW5A)
+ [���İ��ĵ�](https://linfan1.gitbooks.io/kubernetes-chinese-docs/content/index.html)
+ [Awesome-Kubernetes](https://ramitsurana.gitbooks.io/awesome-kubernetes/content/)

```
root@ncpmaster:/home/pksadmin# kubectl describe pod coredns-fb8b8dccf-q78fl -n kube-system
kubectl get ds -n kube-system
```
