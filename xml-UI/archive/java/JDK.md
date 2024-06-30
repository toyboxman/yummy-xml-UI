***

## JDK operations
- [错误处理](#运行错误处理)
	- [Operation not permitted](#operation-not-permitted)
	- [Unable to open socket file](#unable-to-open-socket-file)
	- [Cannot open shared object file](#cannot-open-shared-object-file)
	- [Can't attach to the process: ptrace](#cant-attach-to-the-process-ptrace)
  - [JVM的外挂技术](https://mp.weixin.qq.com/s/cwU2rLOuwock048rKBz3ew)
- [命令使用](#使用命令)
	- [jps](#jps)
	- [jstack](#jstack)
	- [jmap](#jmap)
	- [jinfo](#jinfo)
	- [jstat](#jstat)
  - [javap](#javap)
  - [javadoc](#javadoc)
- Debug/Monitor开源工具
  - [Arthas](#arthas)
    - [应用案例](https://mp.weixin.qq.com/mp/appmsgalbum?__biz=MzU2MTY2MjE4OQ==&action=getalbum&album_id=1430941903897460741&subscene=189)
  - JVM-Sandbox
    - [JVM Sandbox介绍](https://mp.weixin.qq.com/s/wtDVYwgKpSHw469nb4NeDw)
    - [jvm-sandbox](https://github.com/alibaba/jvm-sandbox)
    - [jvm-sandbox-repeater](https://github.com/alibaba/jvm-sandbox-repeater)
  - [findtheflow](http://findtheflow.io/docs/doc_intellij.html#_how_to_use_flow_standalone_version)
  - [instrumentation](https://mp.weixin.qq.com/s/I15UyTbhalXEXkoesGWa-Q)
  - [Bistoury](https://github.com/qunarcorp/bistoury)  
    `在线debug功能模拟ide调试体验，通过web界面提供断点调试的功能，可以在不阻塞应用的情况下捕获断点处的信息(包括本地变量、成员变量、静态变量和方法调用栈)`
  - [Bytecode Viewer](https://github.com/Konloch/bytecode-viewer)
  - [BTrace](https://github.com/btraceio/btrace/wiki)
  - [vjtools](https://github.com/vipshop/vjtools)
  - [调试监控经验集](https://github.com/vipcolud/monitor)
  - [spring-loaded](https://github.com/spring-projects/spring-loaded)
- Evolution
  - [new in Java 14](https://mkyong.com/java/what-is-new-in-java-14/)

***

### 运行错误处理
#### Operation not permitted
```console
#download jdk8 package
wget -c --no-cookies \
--no-check-certificate \
--header "Cookie: oraclelicense=accept-securebackup-cookie" \
"http://download.oracle.com/otn-pub/java/jdk/8u171-b11/512cd62ec5174c3487ac17c61aaa89e8/jdk-8u171-linux-x64.tar.gz" \
-O jdk-8-linux-x64.tar.gz
#decompress package and change name
tar -xzvf jdk-8-linux-x64.tar.gz
mv jdk1.8.0_171/ jdk
#start jvm but fail, physical memory is adequate
/root/jdk/bin/java -version
Java HotSpot(TM) 64-Bit Server VM warning: INFO: os::commit_memory(0x00007b9c51000000, 2555904, 1) failed; error='Operation not permitted' (errno=1)
#
# There is insufficient memory for the Java Runtime Environment to continue.
# Native memory allocation (mmap) failed to map 2555904 bytes for committing reserved memory.
# An error report file with more information is saved as:
# /root/grey/hs_err_pid28237.log
``` 
按照[链接](https://bugs.eclipse.org/bugs/show_bug.cgi?id=432069)解决方案修改内存操作模式可以解决启动失败问题
```console 
sudo paxctl -C /root/jdk/bin/java
sudo paxctl -m /root/jdk/bin/java

sudo paxctl -C /root/jdk/jre/bin/java
sudo paxctl -m /root/jdk/jre/bin/java

/root/jdk/bin/java -version
java version "1.8.0_171"
Java(TM) SE Runtime Environment (build 1.8.0_171-b11)
Java HotSpot(TM) 64-Bit Server VM (build 25.171-b11, mixed mode)
```
#### Unable to open socket file
```console 
> jstack -l 2554
2554: Unable to open socket file: target process not responding or HotSpot VM not loaded
The -F option can be used when the target process is not responding
```
这个错误可能是目标process hung住,可以强制dump thread信息
```console 
> jstack -l -F 2554
```
如果出现deduce type错误则按照下面错误处理方式去排除
#### Unable to deduce type of thread
```console 
> jstack -l 26191
Attaching to process ID 26191, please wait...
Debugger attached successfully.
Server compiler detected.
JVM version is 25.171-b11
Deadlock Detection:

java.lang.RuntimeException: Unable to deduce type of thread from address...
```
这个错误提示不明确，实际上是由于执行jstack的user和java process的owner不一致
```console 
#查看26191进程的owner
> ps -ef | grep 26191
> sudo -u owner jstack -l 26191
```
如果出现lib找不到, 文件目录不存在，按照下面处理排除错误
#### cannot open shared object file
```console 
> sudo -u owner jstack -l 26191> ls -al /
drwx------   7 root root     4096 May 24 06:10 root
./jstack: error while loading shared libraries: libjli.so: cannot open shared object file: No such file or directory
```
这种错误实际上是由于当前目录执行权限不足造成
```console 
#查看执行路径权限
> pwd
/root/jdk/bin
#由于非root用户无法访问操作,需要给owner用户操作权限
> chmod 777 /root
# 成功将进程栈信息dump到文件中
> sudo -u owner jstack -l 26191 > stack.log
```
#### Can't attach to the process: ptrace
```console 
sudo -u nsx ./jmap 1180    
Attaching to process ID 1180, please wait...
Error attaching to process: sun.jvm.hotspot.debugger.DebuggerException: Can't attach to the process: ptrace(PTRACE_ATTACH, ..)...
```
这种错误是由于系统ptrace(process trace)没有设定成调试模式
```console 
#临时修改方案是将内核变量修改,重启后会失效
#不过有些系统并无此文件/proc/sys/kernel/yama/ptrace_scope
$ echo 0 | sudo tee /proc/sys/kernel/yama/ptrace_scope

#永久修改方案是将ptrace的开关打开
$ sudo vi /etc/sysctl.d/10-ptrace.conf
#将ptrace_scope值改成0
#重启系统
kernel.yama.ptrace_scope = 0
```

### 命令
#### jps
```console 
#查看当前jvm实例
 > jps
4048 Jps
9653 RemoteMavenServer
9578 Main

> jps -lv
9653 org.jetbrains.idea.maven.server.RemoteMavenServer
4070 sun.tools.jps.Jps
9578 com.intellij.idea.Main
```
#### jstack
```console 
#查看当前stack信息
#connect to running process
#-m to print both java and native frames (mixed mode)
#-l  long listing. Prints additional information about locks
> jstack -l 2555
#connect to a hung process
#-F to force a thread dump. Use when jstack <pid> does not respond (process is hung)
> jstack -F -l 2566 > stack.txt
#connect to a core file
#create coredump for java process
> kill -3 2577       
> jstack -m -l /usr/bin/java core.dump
```
#### jmap
```console 
#dump当前堆信息
# -dump:<dump-options> to dump java heap in hprof binary format
#		<dump-options>
#   			live     dump only live objects; if not specified,
#                         all objects in the heap are dumped.
#				format=b     binary format
#				file=<file>  dump heap to <file>
#
> sudo -u owner ./jmap -dump:live,format=b,file=heap.bin 1180
# dump出来的bin文件可以用jvisualvm打开 也可以用jhat打开
# jhat会启动一个web服务,不过jvisualvm可视化分析更好
> ./jhat heap.bin  
Reading from heap.bin...
Dump file created Thu May 24 07:13:03 UTC 2018
Snapshot read, resolving...
Resolving 502948 objects...
Chasing references, expect 100 dots........................................................................
Eliminating duplicate references........................................................................
Snapshot resolved.
Started HTTP server on port 7000
Server is ready


#查看当前堆信息
#print same info as Solaris pmap
#-F      force. Use with -dump:<dump-options> <pid> or -histo
#          to force a heap dump or histogram when <pid> does not respond
#-J<flag>    pass <flag> directly to the runtime system
#-clstats      print class loader statistics
#-finalizerinfo    print information on objects awaiting finalization
> ./jmap 1409
...
0x0000000000200000      2052K   /usr/java/jre1.8.0_171/bin/java
0x000075f78277d000      87K     /lib/x86_64-linux-gnu/libgcc_s.so.1
...

#-heap  print java heap summary
> ./jmap -heap 1409            
...
using thread-local object allocation.
Garbage-First (G1) GC with 2 thread(s)

Heap Configuration:
   MinHeapFreeRatio         = 40
   MaxHeapFreeRatio         = 70
   MaxHeapSize              = 12884901888 (12288.0MB)
   NewSize                  = 1363144 (1.2999954223632812MB)
   MaxNewSize               = 7730102272 (7372.0MB)
   OldSize                  = 5452592 (5.1999969482421875MB)
   NewRatio                 = 2
   SurvivorRatio            = 8
   MetaspaceSize            = 21807104 (20.796875MB)
   CompressedClassSpaceSize = 1073741824 (1024.0MB)
   MaxMetaspaceSize         = 17592186044415 MB
   G1HeapRegionSize         = 2097152 (2.0MB)
...

# -histo:live    print histogram of java object heap; if the "live"
#                      suboption is specified, only count live objects
> sudo -u nsx ./jmap -histo 1409 | less
num     #instances         #bytes  class name
----------------------------------------------
   1:        142153       85660576  [B
   2:         50612       32093784  [I
   3:        250895       26593720  [C
   4:        246006        7872192  java.util.HashMap$Node
   5:        101489        5630888  [Ljava.lang.Object;
   6:         62718        4607936  [Ljava.util.HashMap$Node;
...  
```
#### jinfo
```console 
#查看当前jvm实例的信息
 > jinfo 29620
...
Java System Properties:

java.runtime.name = Java(TM) SE Runtime Environment
sun.boot.library.path = /usr/jdk/instances/jdk1.6.0/jre/lib/sparc
java.vm.version = 1.6.0-rc-b100
...
#查看dump文件中信息
> jinfo $JAVA_HOME/bin/java core.29620
```
#### jstat
查看及诊断当前jvm实例的性能问题
```console 
#Samples for Generation Collection
# 2834 is process id
# 250 is time period
# 7 is running collection seven times
> jstat -gcutil 2834 250 7
  S0     S1     E      O      M     YGC     YGCT    FGC    FGCT     GCT   
  0.00  99.74  13.49   7.86  95.82      3    0.124     0    0.000    0.124
  0.00  99.74  13.49   7.86  95.82      3    0.124     0    0.000    0.124
  ...
  
#Samples for Young Generation Collection
> jstat -gcnew -h3 2834 250
S0C    S1C    S0U    S1U   TT MTT  DSS      EC       EU     YGC     YGCT  
 192.0  192.0    0.0    0.0 15  15   96.0   1984.0    942.0    218    1.999
 192.0  192.0    0.0    0.0 15  15   96.0   1984.0   1024.8    218    1.999
 192.0  192.0    0.0    0.0 15  15   96.0   1984.0   1068.1    218    1.999
 S0C    S1C    S0U    S1U   TT MTT  DSS      EC       EU     YGC     YGCT  
 192.0  192.0    0.0    0.0 15  15   96.0   1984.0   1109.0    218    1.999
 192.0  192.0    0.0  103.2  1  15   96.0   1984.0      0.0    219    2.019
 192.0  192.0    0.0  103.2  1  15   96.0   1984.0     71.6    219    2.019
 S0C    S1C    S0U    S1U   TT MTT  DSS      EC       EU     YGC     YGCT  
 192.0  192.0    0.0  103.2  1  15   96.0   1984.0     73.7    219    2.019
 192.0  192.0    0.0  103.2  1  15   96.0   1984.0     78.0    219    2.019
 192.0  192.0    0.0  103.2  1  15   96.0   1984.0    116.1    219    2.019

#Samples for Old Generation Capacity 
> jstat -gcoldcapacity -t 21891 250 3
Timestamp    OGCMN     OGCMX       OGC        OC   YGC   FGC     FGCT     GCT
    150.1   1408.0   60544.0   11696.0   11696.0   194    80    2.874   3.799
    150.4   1408.0   60544.0   13820.0   13820.0   194    81    2.938   3.863
    150.7   1408.0   60544.0   13820.0   13820.0   194    81    2.938   3.863

#options supports
> jstat -options	
```

#### javap
反汇编字节码文件
```console
# -s Print internal type signatures
> javap -s java.lang.String | less

# disassemble HelloWorld.class
> javap HelloWorld

# -v Print more details of instruments
# -p Print private fields/methods
> javap -v -p HelloWorld
```

#### javadoc
根据源码注释产生标准doc文档
```console
# -d 指定html生成位置
javadoc common/java/tracing/src/main/java/example/tracing/*.java -d ./javadoc/
```

#### Arthas
- [Diagnostic Tool Arthas](https://github.com/alibaba/arthas)
- [中文手册](https://arthas.gitee.io/trace.html)
- [最佳实践](https://mp.weixin.qq.com/s/PIhsu-gNb9XK0ulU06lL7g)
```console
# 下载运行包
curl -O https://alibaba.github.io/arthas/arthas-boot.jar
# 启动进程
java -jar arthas-boot.jar

# 如果目标jvm是jre启动，需要找一个对应的jdk版本
# 最简单方式是在调试机器上下载一个相同版本或略高版本的jdk，然后通过jdk中java来启动
# 可能会遇到如下错误提示
[INFO] Try to attach process 10310
Picked up JAVA_TOOL_OPTIONS: 
Error: Unable to initialize main class com.taobao.arthas.core.Arthas
Caused by: java.lang.NoClassDefFoundError: com/sun/tools/attach/AgentLoadException

# azul的zulu是基于openjdk的一种jdk发型版本
# 对应 openjdk8
wget https://cdn.azul.com/zulu/bin/zulu8.52.0.23-ca-jdk8.0.282-linux_x64.tar.gz

# openjdk version "11.0.23" 2024-04-16 LTS
# OpenJDK Runtime Environment Zulu11.72+19-CA (build 11.0.23+9-LTS)
# OpenJDK 64-Bit Server VM Zulu11.72+19-CA (build 11.0.23+9-LTS, mixed mode)
wget https://cdn.azul.com/zulu/bin/zulu11.72.19-ca-jdk11.0.23-linux_x64.tar.gz

# 也可以从对应jdk拷贝jre缺失文件
cp /usr/java/jre1.8.0_251/bin/java /usr/java/jdk1.8.0_251/bin
# 如果目标jre lib中缺少tools.jar，需要从jdk中复制一份
cp /usr/java/jdk1.8.0_251/lib/tools.jar /usr/java/jre1.8.0_251/lib

# 如果目标jvm不是root用户权限启动，例如是proton用户执行
chown -hR proton arthas-boot.jar 
# 把执行包从root目录移动到proton用户权限目录中
mv arthas-boot.jar /opt/app/proton-tomcat
# 用proton用户权限启动进程，attach到 jvm 3331进程
sudo -u proton /usr/java/jdk1.8.0_251/bin/java -jar /opt/app/proton-tomcat/arthas-boot.jar 3331
```
##### search class  
当search loaded class时候，常常碰到class找不到,确认jar文件已经放在classpath. 如果jar已经在-cp指定目录中，但仍然搜寻不到相关类如下
```console
[arthas@27705]$ sc *MyContext
Affect(row-cnt:0) cost in 2319 ms.
```
这种情况是由于目标class尚未被jvm加载，一般是由于目标类未被其他class直接调用，导致jvm没有加载，解决方式只要触发一次调用就可以search到。
```console
# 查看class具体信息
[arthas@24124]$ sc -d *ConfigConverter

# 查看加载class的classloader标示号
[arthas@24124]$ sc -d *ConfigConverter | grep classLoaderHash
classLoaderHash   1911bd1e
# 查看classloader加载哪些文件
[arthas@24124]$ classloader -c 1911bd1e
```
##### search method
搜寻class全部method可以如下操作
```console
[arthas@17977]$ sm example.messaging.RpcServices
example.messaging.RpcServices <init>()V
example.messaging.RpcServices getServices()Ljava/util/Map;
example.messaging.RpcServices getStubType(Ljava/lang/String;)Ljava/lang/Class;
example.messaging.RpcServices setRpcEnabled(Z)V
example.messaging.RpcServices getServiceEndpoint(Ljava/lang/String;)Ljava/lang/String;
Affect(row-cnt:14) cost in 339 ms.

# 搜寻相关method
[arthas@28030]$ sm *facade.LogicalSwitchImpl delete*
com.example.LogicalSwitchFacadeImpl deleteLogicalSwitch(Ljava/lang/String;)V
com.example.LogicalSwitchFacadeImpl deleteLogicalSwitch_aroundBody6(Lorg/aspectj/lang/JoinPoint;)V
Affect(row-cnt:2) cost in 188 ms.

# 查看method具体信息
[arthas@28030]$ sm -d java.lang.String toString
 declaring-class  java.lang.String                                                                                                
 method-name      toString                                                                                                        
 modifier         public                                                                                                          
 annotation                                                                                                                       
 parameters                                                                                                                       
 return           java.lang.String                                                                                                
 exceptions                                                                                                                       
 classLoaderHash  null   
```

##### [watch](https://arthas.gitee.io/doc/watch.html)
确定method后就可以watch操作
```console
# 默认情况输出调用方法的 {parameter/方法所属 class instance/return}
[arthas@17977]$ watch example.messaging.RpcServices getServiceEndpoint
Affect(class count: 1 , method count: 1) cost in 481 ms, listenerId: 1
ts=2020-12-16 13:30:41; [cost=1.273833ms] result=@ArrayList[
    # 第一项返回是方法输入参数的array， size=1表示只有一个参数
    @Object[][isEmpty=false;size=1],
    # 第二项返回是方法所属的class instance
    @NsxRpcServices[example.messaging.RpcServices@46ba4da8],
    # 第三项返回是方法返回值
    null,
]

# 查看 class instance 的 fields
[arthas@16754]$ watch example.tracing.Tracer isDumbMode "{target}" -x 2
traceConfig=@DefaultTraceConfig[DefaultTraceConfig{traceConfigName='remote_trace', flushInterval=1000, maxQueueSize=10000, samplerType='const', samplerParam=1, collectorIp='127.0.0.1', collectorPort='6831', isLogSpans='false'}],

# 如果想看到更多细节，如参数细节等，可以通过 -x 2 设定往内看两层
# OGNL表达式 "{params,target,returnObj}" 设定result包含参数/类实例/返回值数据, 这是默认的返回数据格式
# 设定 "{params,returnObj}" result只返回 参数和返回值
[arthas@17977]$ watch example.messaging.RpcServices getServiceEndpoint "{params,target,returnObj}" -x 2
Press Q or Ctrl+C to abort.
Affect(class count: 1 , method count: 1) cost in 421 ms, listenerId: 2
ts=2020-12-16 13:42:39; [cost=0.184837ms] result=@ArrayList[
    @Object[][
        # 第一项第二层表示参数类型是string，值 “Traceflow”
        @String[Traceflow],
    ],
    @NsxRpcServices[
        # 第二项第二层表示RpcServices实例的fields的值
        logger=@Logger[example.logging.Logger@19340508],
        services=@ConcurrentHashMap[isEmpty=true;size=0],
        rpcEnabled=@Boolean[true],
    ],
    null,
]

# ognl 支持的目标类型
// target : 目标对象
// clazz : the object's class                                                                        
// method : the constructor or method                                                                 
// params : 方法参数顺序组
// params[0..n] : 某一个参数
// returnObj : the returned object of method                                                             
// throwExp : the throw exception of method                                                             
// isReturn : the method ended by return                                                                
// isThrow : the method ended by throwing exception                                                    
// #cost : the execution time in ms of method invocation 
# 过滤，判断，筛选
// 'params[0]'：查看第一个参数
// 'params[0].size()'：查看第一个参数的size
// 'params[0]=="xyz"'：判断字符串相等
// 'params[0]==123456789L'：判断long型
// 'params[0].{#this.name}'：将结果按name属性映射
// 'params[0].{? #this.name == null }'：按条件过滤
// 'params[0].{? #this.age > 10 }.size()'：过滤后统计
// 'params[0].{^ #this.name != null}'：选择第一个满足条件
// 'params[0].{$ #this.name != null}'：选择最后一个满足条件
// 'params[0].{? #this.age > 10 }.size().(#this > 20 ? #this - 10 : #this + 10)'：子表达式求值
// 'name in { null,"Untitled" }':这条语句判断name是否等于null或者 Untitled

# 只看入参细节
[arthas@17977]$ watch example.messaging.RpcServices getServiceEndpoint "{params}" -x 3

# 选择入参数目为6个的method细节,过滤同名overload的其他方法 只显示第一和第二参数
[arthas@17977]$ watch example.messaging.RpcServices getServiceEndpoint "{params[0],params[1]}" "params.length==6" -x 2

# 多个过滤条件需要通过逻辑操作符 && || 来关联
# 下面过滤条件是 入参个数6个并且其中第一个参数(为集合类型)的size要大于2
[arthas@17977]$ watch *PolicyEngineHandler invokeProviders "{params[0],params[1]}" "params.length==6 && params[0].size()>2" -x 2

# 下面过滤条件是 入参个数6个并且其中第一个参数的类型为MyProvider类
# 对于集合类型OGNL参数表达式 params[0].{? #this instanceof com.example.MyProvider}将过滤并返回一个集合包含MyProvider对象
[arthas@17977]$ watch *PolicyEngineHandler invokeProviders "{params[0],params[1]}" "params.length==6 && params[0].{? #this instanceof com.example.MyProvider}.size()>0" -x 2

[arthas@17977]$ watch com.integrien.alive.common.adapter3.Logger error -x2 '{target.getName()}'
method=com.integrien.alive.common.adapter3.Logger.error location=AtExit
ts=2024-06-28 08:16:10; [cost=0.400245ms] result=@ArrayList[
    @String[(1233) com.example.util.SslUtil],
]

### 直接使用条件过滤表达式，只有被条件表达式命中的请求，才会被算进 watch 次数中
// target对象getName()方法返回string对象的长度大于0
[arthas@17977]$ watch com.integrien.alive.common.adapter3.Logger error '{target.getName()}' 'target.getName().length>0' -n1
method=com.integrien.alive.common.adapter3.Logger.error location=AtExit
ts=2024-06-30 03:06:01; [cost=0.822183ms] result=@ArrayList[
    @String[(1233) com.example.util.SslUtil],
]

// 等效于上面一个条件
[arthas@3333485]$ watch com.integrien.alive.common.adapter3.Logger error '{target.getName()}' 'target.getName()!=null' -n1
Affect(class count: 1 , method count: 2) cost in 475 ms, listenerId: 22
method=com.integrien.alive.common.adapter3.Logger.error location=AtExit
ts=2024-06-30 13:57:29; [cost=0.671868ms] result=@ArrayList[
    @String[(1233) com.example.util.SslUtil],
]

// target对象getName()方法返回string对象中包含字符串‘SslUtil’
[arthas@3333485]$ watch com.integrien.alive.common.adapter3.Logger error '{target.getName()}' 'target.getName().indexOf("SslUtil")!=-1' -n1
Press Q or Ctrl+C to abort.
Affect(class count: 1 , method count: 2) cost in 498 ms, listenerId: 25
method=com.integrien.alive.common.adapter3.Logger.error location=AtExit
ts=2024-06-30 14:22:33; [cost=0.701456ms] result=@ArrayList[
    @String[(1233) com.example.util.SslUtil],
]

// equals方法返回boolean值，但比较的话必须用数字 0/true，直接用true似乎不能成功识别
[arthas@3333485]$ watch com.integrien.alive.common.adapter3.Logger error '{target.getName()}' 'target.getName().equals("com.example.util.SslUtil")==0' -n1
Press Q or Ctrl+C to abort.
Affect(class count: 1 , method count: 2) cost in 439 ms, listenerId: 28
method=com.integrien.alive.common.adapter3.Logger.error location=AtExit
ts=2024-06-30 14:52:38; [cost=0.748555ms] result=@ArrayList[
    @String[(1233) com.example.util.SslUtil],
]


### 使用OGNL条件过滤表达式
// 直接 #this 引用，指向了 com.taobao.arthas.core.advisor.Advice@503e5cfd对象，Advice 类定义没有getName方法，因此报错
[arthas@3333485]$ watch com.integrien.alive.common.adapter3.Logger error '{target.getName()}' '#this.getName().length>0' -n2
Press Q or Ctrl+C to abort.
Affect(class count: 1 , method count: 2) cost in 470 ms, listenerId: 13
watch failed, condition is: #this.getName().length>0, express is: {target.getName()}, ognl.MethodFailedException: Method "getName" failed for object com.taobao.arthas.core.advisor.Advice@503e5cfd [java.lang.NoSuchMethodException: com.taobao.arthas.core.advisor.Advice.getName()], visit /home/admin/logs/arthas/arthas.log for more details.

// target.{? #this.getName()} 产生了一个List<Name>的集合, 集合对象没有length的属性，因此报错
[arthas@3333485]$ watch com.integrien.alive.common.adapter3.Logger error '{target.getName()}' 'target.{? #this.getName()}.length>0' -n2
Press Q or Ctrl+C to abort.
Affect(class count: 1 , method count: 2) cost in 441 ms, listenerId: 12
watch failed, condition is: target.{? #this.getName()}.length>0, express is: {target.getName()}, ognl.NoSuchPropertyException: java.util.ArrayList.length, visit /home/admin/logs/arthas/arthas.log for more details.

// List<Name>的集合, 用list的方法size返回值来作为判断条件，成功返回
[arthas@3333485]$ watch com.integrien.alive.common.adapter3.Logger error '{target.getName()}' 'target.{#this.getName()}.size()>0' -n2
Affect(class count: 1 , method count: 2) cost in 453 ms, listenerId: 17
method=com.integrien.alive.common.adapter3.Logger.error location=AtExit
ts=2024-06-30 04:11:13; [cost=0.821933ms] result=@ArrayList[
    @String[(1233) com.example.util.SslUtil],
]
method=com.integrien.alive.common.adapter3.Logger.error location=AtExit
ts=2024-06-30 04:11:14; [cost=0.088174ms] result=@ArrayList[
    @String[(1496) com.integrien.adapter.WCPManager],
]

// 按条件 equals("com.example.util.SslUtil")==0(true) 产生List<Name>的集合, 用list的方法size返回值来作为判断条件，成功返回
[arthas@3333485]$ watch com.integrien.alive.common.adapter3.Logger error '{target.getName()}' 'target.{? #this.getName().equals("com.example.util.SslUtil")==0}.size()>0' -n1
Press Q or Ctrl+C to abort.
Affect(class count: 1 , method count: 2) cost in 468 ms, listenerId: 30
method=com.integrien.alive.common.adapter3.Logger.error location=AtExit
ts=2024-06-30 15:07:40; [cost=0.713237ms] result=@ArrayList[
    @String[(1233) com.example.util.SslUtil],
]

// 产生List<Name>的集合, 用list的方法 contains("com.example.util.SslUtil")==0(true) 作为判断条件
[arthas@3333485]$ watch com.integrien.alive.common.adapter3.Logger error '{target.getName()}' 'target.{? #this.getName()}.contains("com.example.util.SslUtil")==0' -n2
Press Q or Ctrl+C to abort.
Affect(class count: 1 , method count: 2) cost in 469 ms, listenerId: 31
method=com.integrien.alive.common.adapter3.Logger.error location=AtExit
ts=2024-06-30 15:12:41; [cost=0.897218ms] result=@ArrayList[
    @String[(1233) com.example.util.SslUtil],
]
method=com.integrien.alive.common.adapter3.Logger.error location=AtExit
ts=2024-06-30 15:12:41; [cost=0.075126ms] result=@ArrayList[
    @String[(1496) com.integrien.adapter.WCPManager],
]


# 获取接口的响应时间
// -b, --before      Watch before invocation                                                 
// -e, --exception   Watch after throw exception                                             
// --exclude-class-pattern <value>  exclude class name pattern, use either '.' or '/' as separator          
// -x, --expand <value>  Expand level of object (1 by default), the max value is 4               
// -f, --finish          Watch after invocation, enable by default  
[arthas@17977]$ watch org.springframework.web.servlet.DispatcherServlet doService '{params[0].getRequestURI()+" "+ #cost}' -n5 -x3 '#cost>100' -f

# 获取指定header 头的信息，比如这里 获取 trace-id
[arthas@17977]$ watch org.springframework.web.servlet.DispatcherServlet doService '{params[0].getRequestURI()+" header="+params[1].getHeaders("trace-id")}' -n10 -x3 -f
```

##### [ognl](https://commons.apache.org/dormant/commons-ognl/language-guide.htm)
OGNL(Object-Graph Navigation Language)是一种表达式语言(EL)，简单来说就是一种简化了的Java属性的取值语言，Arthas使用它做表达式过滤。  
为什么Arthas选择了ognl？ognl表达式基于反射，比较轻量。实践下来，ognl的确比较稳定，没有出过大问题。
```console
// ognl 能支持的关键操作符如下:
// "," "=" "?" "||" "or" "&&" "and" "|" "bor" "^" "xor" "&" "band" "==" "eq" "!=" "neq" "<" "lt"
// ">" "gt" "<=" "lte" ">=" "gte" "in" "not" "<<" "shl" ">>" "shr" ">>>" "ushr" "+" "-" "*" "/"
// "%" "instanceof" "." "(" "[" "}" <DYNAMIC_SUBSCRIPT>

# 变量定义和引用， OGNL支持用变量来保存中间结果，并在后面的代码中再次引用它。
# OGNL中的所有变量，对整个表达式都是全局可见的，引用变量的方法是在变量名之前加上 # 号
# OGNL会将当前对象保存在 "this" 变量中，这个变量也可以像其他任何变量一样引用，用 #this 表示当前对象。

# 调用静态属性 -> '@ClassName@static_field'
[arthas@28030]$ ognl '@com.integrien.alive.common.adapter3.Logger@FQCN'
@String[com.integrien.alive.common.adapter3.Logger]

# 调用静态方法 -> '@ClassName@method("parameters")'
// getDefaultLogger是static method，因而返回成功
[arthas@28030]$ ognl '@com.integrien.alive.common.adapter3.Logger@getDefaultLogger("com.example.util.SslUtil")'
// getName是instance的方法，访问失败
[arthas@28030]$ ognl '@com.integrien.alive.common.adapter3.Logger@getName()'
Failed to execute ognl, exception message: ognl.MethodFailedException: Method "getName" failed for object class com.integrien.alive.common.adapter3.Logger [java.lang.NoSuchMethodException: getName()], please check $HOME/logs/arthas/arthas.log for more details.

// 通过ognl命令执行Spring容器中对象的方法 
// 定义变量 self_context，将 com.example.ApplicationContextUtil的static field 'context'赋值给 self_context
// #self_context=@com.example.ApplicationContextUtil@context -> self_context = com.example.ApplicationContextUtil.context
[arthas@28030]$ ognl -c 21ffcd7c '#self_context=@com.example.ApplicationContextUtil@context,#self_context.getBean("bean1").method1()'
# 通过vmtool也可以得到等效操作
[arthas@28030]$ vmtool --action getInstances --className *.Bean1 --express 'instances[0].method1()'

# 构造对象
// '#{ "foo" : "foo value", "bar" : "bar value" }'：构造map参数
// '#@java.util.LinkedHashMap@{ "foo" : "foo value", "bar" : "bar value" }'：构造特定类型map
// 'new com.Test("xiaoming",18)'：构造方法，new 全路径类名()
// 'new int[] { 1, 2, 3 }'：创建数组并初始化

# 访问对象
// '@com.Test@getPerson("xiaoming",18).name':访问复杂对象属性，用 .属性名 访问属性
// '@com.Test@getChilds({"xiaoming"})[0]':访问List或者数组类型，用 [索引] 访问
// '@com.Test@getMap()["xiaoming"]': 访问Map对象，用 ["key"]，key要用双引号

# 临时变量
// '#value1=@com.Test@getPerson("xiaoming",18), #value2=@com.Test@setPerson(#value1),{#value1,#value2}': 方法A的返回值当做方法B的入参
// '#value1=@System@getProperty("java.home"), #value2=@System@getProperty("java.runtime.name"), {#value1, #value2}'：执行多行表达式，赋值给临时变量，返回一个List
// '#obj=new com.User("xiaoming",18),@com.Test@inputObj(#obj)'：先用构造函数构造一个对象，然后把这个对象当做入参传入

# case-调用任意bean中的方法
// 1.先获取 classLoaderHash
[arthas@28030]$ sc -d com.alibaba.dubbo.config.spring.extension.SpringExtensionFactor
// 2.ognl 调用对应 bean 的方法，把 34f5090e 替换为对于的 classLoaderHash
[arthas@28030]$ ognl -c 34f5090e '#context=@com.alibaba.dubbo.config.spring.extension.SpringExtensionFactory@contexts.iterator.next,#context.getBean("userServiceImpl").find("key")'
// 可以构造参数作为方法调用参数
[arthas@28030]$ ognl -c 34f5090e '#context=@com.alibaba.dubbo.config.spring.extension.SpringExtensionFactory@contexts.iterator.next,#data=new Children(), #query=new User(),#query.setChildren(#data),#query.setRequestId("1"), #data.setName("key"),#context.getBean("userServiceImpl").find(#query)'

# case-动态修改 bean 属性值, 原理就是先获取 bean 实例，通过反射去修改对应属性值
[arthas@28030]$ ognl -c 34f5090e org.ClassLoader
'#context=@com.alibaba.dubbo.config.spring.extension.SpringExtensionFactory@contexts.iterator.next, #instance=#context.getBean("userServiceImpl"),#fieldObj=@com.User@class.getDeclaredField("age"),#fieldObj.setAccessible(true), #fieldObj.set(#instance,18)'
```

##### [stack](https://arthas.gitee.io/doc/stack.html)
如果想查看调用栈可以stack操作, 一直能上溯看到谁调用到此方法
```console
# 记录LogicalSwitchImpl所有方法被触发的调用栈数据
[arthas@28030]$ stack *facade.LogicalSwitchImpl *
# 按条件记录调用栈数据
[arthas@28030]$ stack *PolicyEngineHandler invokeProviders "params.length==6 && params[0].{? #this instanceof com.example.MyProvider}.size()>0"

# 把捕获调用栈信息输出到本地文件
[arthas@28030]$ stack com.example.api.search.AggregateRequestDto * | tee /opt/example/gm-tomcat/stack.log
```

##### [trace](https://arthas.gitee.io/doc/trace.html)
如果想查看调用栈可以trace操作，往下看到后面每一个被调用的方法及执行时间
```console
# 记录LogicalSwitchImpl所有方法被触发的调用栈数据
[arthas@28030]$ trace *facade.LogicalSwitchImpl delete*
# 同上面命令但是可以追踪jdk的方法调用
[arthas@28030]$ trace --skipJDKMethod false *LogicalSwitchImpl delete*trace

# 记录LogicalSwitchImpl所有构造方法被调用的栈数据
[arthas@28030]$ trace *facade.LogicalSwitchImpl <init>

# 通过正则表达追踪多个对象方法 trace -E class1|class2 method1|method2
# trace只能跟踪当前stack层次，多层次调用时候可以通过多个类方法来展开调用追踪
trace -E com.validators.ValidatorAspect|com.facade.FacadeImpl createOrReplace|validationAnnotatedMethod '#cost>500'

# 追踪 耗时超过500毫秒的 调用执行
trace *FacadeInterceptorValidatorAspect validationAnnotatedMethod '#cost>500'
```

##### [tt](https://arthas.gitee.io/doc/tt.html)
如果一个目标方法调用次数很多，用trace监控time cost，屏幕输出非常快，难以获取有效信息。此时可以通过TimeTunnel列表显示
```console
# -n 300 最大显示300条记录 默认只有100条记录，调用太多时候根据情况设定
tt -t -n 300 com.example.tracing.Tracer begin

 INDEX   TIMESTAMP            COST(ms)   IS-RET  IS-EXP   OBJECT         CLASS                        METHOD                        
---------------------------------------------------------------------------------------------------------------------------------------
 1010    2022-12-20 12:43:58  0.150055   true    false    0x168128d3     Tracer                       begin                         
 1011    2022-12-20 12:43:58  0.007688   true    false    0x168128d3     Tracer                       begin                         
 1012    2022-12-20 12:43:58  0.017379   true    false    0x168128d3     Tracer                       begin
 ...
 1210    2022-12-20 12:44:06  0.006436   true    false    0x168128d3     Tracer                       begin                         
 1211    2022-12-20 12:44:06  0.006634   true    false    0x168128d3     Tracer                       begin

# 记录耗时超过 1ms 调用
tt -t -n 300 com.example.tracing.Tracer begin '#cost>1' 

# -l 将上一次执行结果列表显示出来
tt -l

# 按ognl表达式过滤列表中数据
tt -s 'method.name=="begin"

# 查看某一条记录详细信息
tt -i 1179
INDEX          1179                                                                                                                   
GMT-CREATE     2022-12-20 12:44:03                                                                                                    
COST(ms)       1.521814                                                                                                               
OBJECT         0x168128d3                                                                                                             
CLASS          com.example.tracing.Tracer                                                                                       
METHOD         begin                                                                                                                  
IS-RETURN      true                                                                                                                   
IS-EXCEPTION   false                                                                                                                  
PARAMETERS[0]  @String[getNoThrow]                                                                                                    
RETURN-OBJ     null                                                                                                                   

# -p 根据记录的参数 重新执行一遍方法调用
tt -i 1250 -p
RE-INDEX       1250                                                                                                                   
GMT-REPLAY     2022-12-20 12:43:30                                                                                                    
OBJECT         0x5b40cc8c                                                                                                             
CLASS          com.example.tracing.Tracer                                                                                       
METHOD         begin                                                                                                                  
PARAMETERS[0]  @String[create]                                                                                                        
IS-RETURN      true                                                                                                                   
IS-EXCEPTION   false                                                                                                                  
COST(ms)       0.258549                                                                                                               
RETURN-OBJ     null                                                                                                                   
Time fragment[1250] successfully replayed 1 times.
```

##### [vmtool](https://arthas.gitee.io/vmtool.html)
允许从当前VM中查找到实例，并执行一些方法
```console
# 查看当前VM中所有 JaegerTracer 实例, 返回实例数组
[arthas@8873]$ vmtool --action getInstances --className io.jaegertracing.internal.JaegerTracer
@JaegerTracer[][
    @JaegerTracer[JaegerTracer(version=Java-1.3.2, serviceName=policy, reporter=RemoteReporter(sender=HttpSender(), closeEnqueueTimeout=1000), sampler=ConstSampler(decision=true, tags={sampler.type=const, sampler.param=true}), tags={hostname=svc, jaeger.version=Java-1.3.2, ip=10.191.191.99}, zipkinSharedRpcSpan=false, expandExceptionLogs=false, useTraceId128Bit=true)],
]

# 查看当前VM中所有 ApplicationContext 实例, 返回实例数组
[arthas@26393]$ vmtool --action getInstances --className org.springframework.context.ApplicationContext
@ApplicationContext[][
    @ClassPathXmlApplicationContext[org.springframework.context.support.ClassPathXmlApplicationContext@30459c85, started on Wed Oct 13 08:20:54 UTC 2021, parent: Root WebApplicationContext],
    @XmlWebApplicationContext[WebApplicationContext for namespace 'spring-servlet', started on Wed Oct 13 08:23:05 UTC 2021, parent: org.springframework.context.support.ClassPathXmlApplicationContext@30459c85],
    @AnnotationConfigWebApplicationContext[Root WebApplicationContext, started on Wed Oct 13 08:20:24 UTC 2021],
]

# 调用指定实例的方法(通过上一个方法返回的数组下标来指定实例，只能调用实例的public方法)
[arthas@26393]$ vmtool --action getInstances  --className org.springframework.context.ApplicationContext --express 'instances[1].toString()'
@String[WebApplicationContext for namespace 'spring-servlet', started on Wed Oct 13 08:23:05 UTC 2021, parent: org.springframework.context.support.ClassPathXmlApplicationContext@30459c85]

# 展开指定实例的两层结构(用来查看实例的各个fields的当前值)
[arthas@26393]$ vmtool --action getInstances  --className org.springframework.context.ApplicationContext --express 'instances[0]' -x 2

# 指定classloader中的实例，进行展开查看操作，调用getBeanDefinitionNames方法
[arthas@26393]$ vmtool --action getInstances -c 19469ea2 --className org.springframework.context.ApplicationContext -x 2

[arthas@26393]$ vmtool --action getInstances --classLoaderClass org.springframework.boot.loader.LaunchedURLClassLoader --className org.springframework.context.ApplicationContext --express 'instances[0].getBeanDefinitionNames()'

# 调用对象实例的一个方法 listComputeManagers
[arthas@1922536]$ vmtool --action getInstances --className org.example.service.ComputeManagerServiceImpl --express 'instances[0].listComputeManagers(null, null)'
@ArrayList[
    @CorfuStoreEntry[org.corfudb.runtime.collections.CorfuStoreEntry@3fca91cb],
]
# 调用对象实例的一个方法 listComputeManagers，并且展开返回值的结构
[arthas@1922536]$ vmtool --action getInstances --className org.example.service.ComputeManagerServiceImpl --express 'instances[0].listComputeManagers(null, null)' -x 2
@ArrayList[
    @CorfuStoreEntry[
        key=@IdentifierMsg[string_id: "b7a0bba3-8427-45a6-937d-792597a2400e"
],
        payload=@ComputeManagerModelMsg[managed_resource {
  display_name: "b7a0bba3-8427-45a6-937d-792597a2400e"
  tags_array {
  }
}
server {
  value: "10.83.46.76"
}
origin_type {
  value: "vCenter"
}
thumbprint {
  value: "9E:4E:4D:3E:4E:ED:42:DD:5D:BE:70:63:EF:3C:3D:22:38:4E:3B:3E:E7:69:6A:21:61:19:12:20:D8:D3:19:38"
}
credential_type: CREDENTIAL_TYPE_USERNAME_PASSWORD
trust_as_auth_server {
}
access_level_for_oidc {
  value: "FULL"
}
reverse_proxy_https_port: 443
],
    ],
]
[arthas@1922536]$ 
```

##### [getstatic](https://arthas.gitee.io/getstatic.html)
如果想查看类的有static field，可以直接查看而不论其是否是private
```console
# 查看System的static field 'out'
[arthas@14150]$ getstatic java.lang.System out
field: out
@SystemLogHandler[
    out=@PrintStream[java.io.PrintStream@66e9451],
    logs=@ThreadLocal[java.lang.ThreadLocal@3ef93cfa],
    reuse=@Stack[isEmpty=true;size=0],
    autoFlush=@Boolean[false],
    trouble=@Boolean[false],
    formatter=null,
    textOut=@BufferedWriter[java.io.BufferedWriter@36f5eebf],
    charOut=@OutputStreamWriter[java.io.OutputStreamWriter@2be0b283],
    closing=@Boolean[false],
    out=@PrintStream[java.io.PrintStream@66e9451],
]
Affect(row-cnt:1) cost in 101 ms.

# 如果查看class不在当前classloader，还可以指定hash值
[arthas@14150]$ sc -d <class>
[arthas@14150]$ getstatic -c 3d4eac69 <class> <field>
# 还可以搭配 ognl 条件式来处理输出结果
# n是一个Map域，Map的Key是一个Enum，过滤出Map中Key为STOP的Enum值
[arthas@14150]$ getstatic com.alibaba.arthas.Test n 'entrySet().iterator.{? #this.key.name()=="STOP"}'
field: n
@ArrayList[
    @Node[STOP=bbb],
]
Affect(row-cnt:1) cost in 68 ms.
```

##### [retransform](https://arthas.gitee.io/retransform.html)
如果想对已加载的class做热替换可以使用retransform和redefine，类似本地IDE调试时候直接修改代码即可生效。但受jvm热替换限制不能修改、添加、删除类的field和method，包括方法参数、方法名称及返回值。<br>  
retransform和redefine两个命令有点差异:
  - redefine 之后原来的class不能恢复，除非重启jvm。有可能失败(比如增加了新的field)，reset命令对redefine的类无效。如果想重置回来，需要redefine原始class的字节码。
  - 另外，redefine命令和jad/watch/trace/monitor/tt等命令会冲突。执行完redefine之后，如果再执行上面提到的命令，则会把redefine的字节码重置。因为后几个命令基于Retransform，jdk本身redefine和Retransform是不同的机制，同时使用两种机制来更新字节码，只有最后修改的那一个会生效
  - retransform则可以进行恢复，回到最初加载的class字节码
  - jad/mc也许不适合做复杂的反编译和编译，所以可以本地工程编译好class文件上传后再做class热替换
```console
# retransform
[arthas@28030]$ retransform /tmp/Test.class
# 查看 retransform entry, TransformCount列显示在ClassFileTransformer.transform函数里尝试返回entry对应的.class文件的次数，但并不表明transform一定成功
[arthas@28030]$ retransform -l
# 删除指定 retransform entry
[arthas@28030]$ retransform -d 1
# 删除所有 retransform entry
[arthas@28030]$ retransform --deleteAll  
# 显式触发 retransform 
# 对于同一个类，当存在多个 retransform entry时，如果显式触发 retransform ，则最后添加的entry生效(id最大的)
[arthas@28030]$ retransform --classPattern demo.* 
[arthas@28030]$ retransform -c 327a647b /tmp/Test.class /tmp/Test\$Inner.class
[arthas@28030]$ retransform --classLoaderClass 'sun.misc.Launcher$AppClassLoader' /tmp/Test.class

# 如果对某个类执行 retransform 之后，想消除影响恢复原状。 要删除这个类对应的 retransform entry，重新触发 retransform。
# 如果不清除掉所有的 retransform entry，并重新触发 retransform ，则arthas stop时，retransform过的类仍然生效。 

# redefine
[arthas@28030]$ jad --source-only com.example.demo.arthas.user.UserController > /tmp/UserController.java
[arthas@28030]$ mc /tmp/UserController.java -d /tmp
[arthas@28030]$ redefine /tmp/com/example/demo/arthas/user/UserController.class
```

##### thread
查看当前JVM中所有thread详情
```console
[arthas@28030]$ thread
Threads Total: 105, NEW: 0, RUNNABLE: 33, BLOCKED: 0, WAITING: 33, TIMED_WAITING: 27, TERMINATED: 0, Internal 
threads: 12                                                                                                   
ID   NAME                       GROUP         PRIORITY STATE    %CPU     DELTA_TIM TIME     INTERRUP DAEMON   
-1   C1 CompilerThread2         -             -1       -        0.28     0.000     0:8.610  false    true     
19   Log4j2-TF-1-AsyncLogger[As main          5        TIMED_WA 0.28     0.000     4:34.799 false    true     
21   Log4j2-TF-1-AsyncLogger[As main          5        TIMED_WA 0.26     0.000     4:39.703 false    true     
460  arthas-command-execute     system        5        RUNNABLE 0.23     0.000     0:0.003  false    true     
-1   VM Periodic Task Thread    -             -1       -        0.05     0.000     0:54.523 false    true     
12   Wrapper-Control-Event-Moni main          5        TIMED_WA 0.03     0.000     0:28.031 false    true     
49   Catalina-utility-2         main          1        TIMED_WA 0.02     0.000     0:5.448  false    false    
71   NotificationThread         main          5        TIMED_WA 0.01     0.000     0:3.268  false    true     
78   pool-49-thread-1           main          5        TIMED_WA 0.01     0.000     0:4.720  false    true     
48   Catalina-utility-1         main          1        WAITING  0.01     0.000     0:5.589  false    false    
2    Reference Handler          system        10       WAITING  0.0      0.000     0:0.193  false    true     
3    Finalizer                  system        8        WAITING  0.0      0.000     0:0.094  false    true     
4    Signal Dispatcher          system        9        RUNNABLE 0.0      0.000     0:0.000  false    true     
18   GC Daemon                  system        2        TIMED_WA 0.0      0.000     0:0.000  false    true     
99   Attach Listener            system        9        RUNNABLE 0.0      0.000     0:0.098  false    true     
435  Keep-Alive-SocketCleaner   system        8        WAITING  0.0      0.000     0:0.006  false    true     
441  arthas-timer               system        9        WAITING  0.0      0.000     0:0.000  false    true   

[arthas@28030]$ thread 48
"Catalina-utility-1" Id=48 WAITING on java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject@2b277d46
    at sun.misc.Unsafe.park(Native Method)
    -  waiting on java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject@2b277d46
    at java.util.concurrent.locks.LockSupport.park(LockSupport.java:175)
    at java.util.concurrent.locks.AbstractQueuedSynchronizer$ConditionObject.await(AbstractQueuedSynchronizer.java:2039)
    at java.util.concurrent.ScheduledThreadPoolExecutor$DelayedWorkQueue.take(ScheduledThreadPoolExecutor.java:1088)
    at java.util.concurrent.ScheduledThreadPoolExecutor$DelayedWorkQueue.take(ScheduledThreadPoolExecutor.java:809)
    at java.util.concurrent.ThreadPoolExecutor.getTask(ThreadPoolExecutor.java:1074)
    at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1134)
    at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:624)
    at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)
    at java.lang.Thread.run(Thread.java:750)

```

##### logger/mbean
查看当前JVM中所有logger/mbean实例详情
```console
# logger --include-no-appender 显示全部logger包括没有设定appender的
[arthas@11693]$ logger
 name                root                                                                                             
 class               org.apache.logging.log4j.core.config.LoggerConfig                                                
 classLoader         java.net.URLClassLoader@1bd86fc2                                                                 
 classLoaderHash     1bd86fc2                                                                                         
 level               INFO                                                                                             
 config              XmlConfiguration[location=/opt/example/gm-tomcat/conf/log4j2.xml]                                 
 additivity          true                                                                                             
 codeSource          file:/usr/tomcat/lib/log4j-core-2.17.1.jar                                                       
 appenders           name            LOGFILE                                                                          
                     class           org.apache.logging.log4j.core.appender.RollingRandomAccessFileAppender           
                     classLoader     java.net.URLClassLoader@1bd86fc2                                                 
                     classLoaderHash 1bd86fc2                                                                         
                     name            MESSAGETRACE_LOG                                                                 
                     class           org.apache.logging.log4j.core.appender.RollingRandomAccessFileAppender           
                     classLoader     java.net.URLClassLoader@1bd86fc2                                                 
                     classLoaderHash 1bd86fc2                                                                         
                     name            RFC5424                                                                          
                     class           org.apache.logging.log4j.core.appender.SyslogAppender                            
                     classLoader     java.net.URLClassLoader@1bd86fc2                                                 
                     classLoaderHash 1bd86fc2   

# -n --name, -l --level
# 把上面名为root的logger的level从INFO设为DEBUG
[arthas@11693]$ logger -c 1bd86fc2 -n root --level debug 

# 列出所有 Mbean 的名称
[arthas@28030]$ mbean

# 查看 Mbean 的元信息
[arthas@28030]$ mbean -m java.lang:type=Threading

# 查看 mbean 属性信息，mbean 的 name 支持通配符匹配 mbean java.lang:type=Th*
[arthas@28030]$ mbean java.lang:type=Threading

# 通配符匹配特定的属性字段
[arthas@28030]$ mbean java.lang:type=Threading *Count

# 实时监控使用-i，使用-n命令执行命令的次数（默认为 100 次）
[arthas@28030]$ mbean -i 1000 -n 50 java.lang:type=Threading *Count
# mbean 命令来查看 Druid 连接池的属性：
[arthas@28030]$ mbean com.alibaba.druid.pool:name=dataSource,type=DruidDataSource
```

##### stop
如果要结束session执行stop操作
```console
[arthas@28030]$ stop
```

##### [classloader](https://arthas.gitee.io/classloader.html)
查看classloader的继承树，urls，类加载信息
```console
[arthas@28030]$ classloader -t
+-BootstrapClassLoader                                                                                                            
+-sun.misc.Launcher$ExtClassLoader@5bd73952                                                                                       
  +-com.taobao.arthas.agent.ArthasClassloader@87787d2                                                                             
  +-com.taobao.arthas.agent.ArthasClassloader@37d87bb4                                                                            
  +-sun.misc.Launcher$AppClassLoader@33909752                                                                                     
    +-java.net.URLClassLoader@6462dee1                                                                                            
      +-ParallelWebappClassLoader                                                                                                 
          context: nsxapi                                                                                                         
          delegate: false                                                                                                         
        ----------> Parent Classloader:                                                                                           
        java.net.URLClassLoader@6462dee1                                                                                          
                                                                                                                                  
        +-org.apache.jasper.servlet.JasperLoader@750f747d                                                                         
Affect(row-cnt:8) cost in 35 ms.

# 查找class文件或resource文件从什么地方加载的
[arthas@30000]$ sc -d *EntityType | grep code-source                                    
 code-source       /opt/tomcat/webapps/api/WEB-INF/lib/model.jar

[arthas@28030]$ classloader -c 750f747d -r java/lang/String.class
 jar:file:/usr/lib/jvm/zre-8-amd64/lib/rt.jar!/java/lang/String.class 
```
