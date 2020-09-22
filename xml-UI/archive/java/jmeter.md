***

## 压力性能测试工具
- [jmeter](#jmeter)
	- [安装启动](#install)
	- [测试配置](#configuration)

### jmeter
[***jmeter***](https://jmeter.apache.org/)是apache开源的一套性能测试工具，已从最初的Web Applications测试扩展到很多其他类型测试。目前最新版能够支持命令行运行和多种格式payload的良好支持。

#### install
推荐下载最新版本，[jmeter下载地址](https://jmeter.apache.org/download_jmeter.cgi). 
```console
$ wget https://mirrors.bfsu.edu.cn/apache/jmeter/binaries/apache-jmeter-5.3.zip

$ unzip apache-jmeter-5.3.zip

$ cd apache-jmeter-5.3/
$ bin/jmeter &
```

#### configuration
jmeter提供图形化界面供用户配置测试内容，然后把配置保存成xml文件。执行测试时候推荐使用CLI方式，指定xml文件后运行。

jmeter测试配置基本结构如下：  
+ **Test-Plan** 测试基本信息描述配置，UI上通过右键菜单add/delete子项
  + **Thread Group** 执行线程组配置，如几个线程，是否无限循环执行   
    + **HTTP Request Defaults** 公用配置，如https/ip/port
    + **HTTP Authorization Manager** 认证配置
    + **HTTP Header Manager** 公用header配置
    + **HTTP Request** 执行接口配置，如URL/POST/payload
    + **View Results Tree** 通过UI查看执行结果
    + **Constant Throughput Timer** 设定吞吐量，例如一分钟固定调用多少次。更多[Timer 类型](https://www.cnblogs.com/TingJie/articles/5198505.html)

Thread Group还可以配置更多内容，可以参考 [User's Manual](https://jmeter.apache.org/usermanual/index.html) 和 [Best Practices](https://jmeter.apache.org/usermanual/best-practices.html)

还有一个有用的配置是内置的 [functions](https://jmeter.apache.org/usermanual/functions.html),它可以帮你完成一个动态值变化。例如想每次执行修改提交的值
```console
# 每次创建一个新用户 user-1, user-2,...user-n
# 可以调用内置counter函数来产生incrementing number
POST /policy/api/v1/users/user-${__counter(false)}
{
    "name" : "user-${__counter(false)}"
}
```
如果配置的测试内容通过执行能在 View Results Tree中看到成果结果，就可以通过命令行大批量运行了
```console
# 查看命令行参数
$ bin/jmeter -?

# -n, --nongui run JMeter in nongui mode
# -t, --testfile <argument>
# the jmeter test(.jmx) file to run. "-t LAST" will load last used file
# -l, --logfile <argument> the file to log samples to
$ bin/jmeter -n -t Test-Plan.jmx -l my.log
```
