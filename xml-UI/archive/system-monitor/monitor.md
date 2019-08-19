### system monitor

- [日志分析工具](https://mp.weixin.qq.com/s/k9Nmmq7PD-yAm4HBIuNbxw)

- Nagios  

Nagios是用来监控服务器和应用运行状态的监控工具，包括针对systems, networks和infrastructure的data gathering, data archiving, dashboards, 及 alerting。与Zabbix较为相似。虽然Nagios提供一些可视化的dashboards, 但比较简单你可以利用Grafana来扩展功能。例如用Grafana来产生performance graphs，同时展示更多graphs，而且还能访问实时utilization数据。

- Zabbix

Zabbix是一个基于WEB界面提供分布式系统监控以及网络监控功能的企业级开源运维平台，也是目前国内互联网用户中使用最广的监控软件。它支持多种采集方式和采集客户端，有专用的Agent，也可以支持SNMP、IPMI、JMX、Telnet、SSH等多种协议。Zabbix 使用 MySQL、PostgreSQL、SQLite、Oracle 或 IBM DB2 儲存資料。Server 端基於 C語言、Web 前端則是基於 PHP 所製作的。Zabbix 可以使用多種方式監視。可以只使用 Simple Check 不需要安裝 Client 端，亦可基於 SMTP 或 HTTP 等各種協定做死活監視。在客戶端如 UNIX、Windows 中安裝 Zabbix Agent 之後，可監視 CPU 負荷、网络使用狀況、磁盘容量等各種狀態。[4.2-document](https://www.zabbix.com/documentation/4.2/manual/quickstart/login#overview) [使用说明](https://zhuanlan.zhihu.com/p/35064593)

docker启动zabbix服务
```
# https://github.com/zabbix/zabbix-docker
docker pull zabbix/zabbix-appliance:ubuntu-latest
docker run --name zabbix -p 80:80 -p 10051:10051 -d zabbix/zabbix-appliance:ubuntu-latest
docker exec -it zabbix bash
docker logs zabbix
```

- Nagios vs Zabbix
    - Nagios最大的亮点是轻量灵活，且报警机制很强，如果你只是需要监控服务器/服务是否在运行，nagios足矣。但是如果牵涉到画图方面，我通过这段时间的亲身体会，感觉nagios+cacti的结合是不如zabbix的all in one方式的。

    - 监控平台的话，各有优劣，但基本都可以满足需求。等达到一定监控指标后，发现，最困难的是监控项目的管理。 中小规模（服务器<=1k）：Zabbix 大规模（1k>=服务器<=10k）：Nagios进行二次开发  超大规模（服务器>=10k）：开发适应自己平台的监控软件吧


- Splunk

Splunk用来做日志分析的，可以迅速从海量日志中搜索出关键信息，而Nagios is used for continuous monitoring的，二者做health of system infrastructure的出发点和方法不相同。

- [Sensu](https://zhuanlan.zhihu.com/p/57740192)

是一个开源的基础设施和应用程序监控解决方案，它可以监控服务器、相关服务和应用程序健康状况，并通过第三方集成发送警报和通知。Sensu 用 Ruby 编写，可以使用 RabbitMQ 或 Redis 来处理消息，它使用 Redis 来存储数据。

如果你想以一种简单而有效的方式监控云基础设施，Sensu 是一个不错的选择。它可以与你的组织已经使用的许多现代 DevOps 组件集成，比如 Slack、HipChat 或 IRC，它甚至可以用 PagerDuty 发送移动或寻呼机的警报。

Sensu 的模块化架构意味着每个组件都可以安装在同一台服务器上或者在完全独立的机器上。

- [Grafana](https://www.loomsystems.com/blog/single-post/2017/06/07/prometheus-vs-grafana-vs-graphite-a-feature-comparison)

Grafana是一个开源的时序性统计平台，同时也有功能强大的界面编辑器。支持Graphite, Elasticsearch, OpenTSDB, Prometheus and InfluxDB等数据源，Grafana是用来分析和可视化metrics数据，例如system CPU, memory, disk and I/O utilization, 但是不能进行全文数据检索。

大致来说kibana能做的图形，grafana都可以做，grafana能做的展示效果，kibana不一定做的到。如果你想做dashboard来展示的话，强烈推荐grafana，如果你是用来展示log的明细，且可以写条件搜索的话，那建议使用kibana。kibana是没有用户认证的，并且没有权限管理的，而grafana有，并且还有组织管理的概念。[grafana vs kibana](https://www.zhihu.com/question/54388690)

- [Prometheus](https://zhuanlan.zhihu.com/p/46309207)

开源监控系统，Prometheus 在2016年继 Kurberntes 之后，加入了 Cloud Native Computing Foundation。大部分Prometheus components由Go实现，其他一些由Java, Python, and Ruby实现。

+[Prometheus安装使用](https://mp.weixin.qq.com/s?__biz=MjM5NjQ4MjYwMQ==&mid=2664614923&idx=2&sn=0a66146e5b5e52d90158538128b98fff)

Node Exporter提供一组与Linux/Unix systems相关的machine-level metrics，例如CPU usage, memory, disk utilization, filesystem fullness, and network bandwidth. 通过这种方式就可以监控物理机器。

- APM(Application Performance Management & Monitoring)

随着企业经营规模的扩大，以及对内快速诊断效率和对外SLA的追求，对于业务系统的掌控度的要求越来越高，在这种情况下，一般都会引入APM系统，通过各种探针采集数据，收集关键指标，同时搭配数据呈现和监控告警，来解决上述的大部分问题。

Pinpoint 是一个比较早并且成熟度也非常高的APM+调用链监控的项目，在全世界范围内均有用户使用，支持Java和PHP的探针，数据容器为HBase

Apache Skywalking 是一个新晋的项目，最近一两年发展非常迅猛，本身支持OpenTracing规范，优秀的设计提供了良好的扩展性，支持Java、PHP、.Net、NodeJs探针，数据容器为ElasticSearch

CAT 是由美团开源的一个APM项目，也历经了多年的迭代升级，拥有大量的企业级用户，对于监控和报警整合比较紧密，支持Java、C/C++、.Net、Python、Go、NodeJs，不过CAT目前主要通过侵入性的方式接入，数据容器包括HDFS（存储原始数据）和mysql（二次统计）

[功能比较](https://skywalking.apache.org/zh/blog/2019-03-29-introduction-of-skywalking-and-simple-practice.html)
