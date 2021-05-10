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
  - [jvm-sandbox](https://github.com/alibaba/jvm-sandbox)
  - [jvm-sandbox-repeater](https://github.com/alibaba/jvm-sandbox-repeater)
  - [findtheflow](http://findtheflow.io/docs/doc_intellij.html#_how_to_use_flow_standalone_version)
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
```console
# 下载运行包
curl -O https://alibaba.github.io/arthas/arthas-boot.jar
# 启动进程
java -jar arthas-boot.jar

# 如果目标jvm是jre启动，需要找一个对应的jdk版本
# 最简单方式是在调试机器上下载一个相同版本或略高版本的jdk，然后通过jdk中java来启动
# azul的zulu是基于openjdk的一种jdk发型版本
wget https://cdn.azul.com/zulu/bin/zulu8.52.0.23-ca-jdk8.0.282-linux_x64.tar.gz
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
##### [watch](https://arthas.gitee.io/watch.html)
确定method后就可以watch操作
```console
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

# 只看入参细节
[arthas@17977]$ watch example.messaging.RpcServices getServiceEndpoint "{params}" -x 3
# 只看满足入参数目为6个的method细节,过滤同名overload的其他方法
[arthas@17977]$ watch example.messaging.RpcServices getServiceEndpoint "{params[0],params[1]}" "params.length==6" -x 2
# 命令过滤参数只有一个，多个条件需要通过逻辑操作符 && || 来关联
# 下面过滤条件是 入参个数6个并且其中第一个参数(为集合类型)的size要大于2
[arthas@17977]$ watch *PolicyEngineHandler invokeProviders "{params[0],params[1]}" "params.length==6 && params[0].size()>2" -x 2
# 下面过滤条件是 入参个数6个并且其中第一个参数(为集合类型)的elements包含MyProvider元素类型的个数要大于0
# 对于集合类型参数表达式 params[0].{? #this instanceof com.example.MyProvider}将返回一个新集合对象
[arthas@17977]$ watch *PolicyEngineHandler invokeProviders "{params[0],params[1]}" "params.length==6 && params[0].{? #this instanceof com.example.MyProvider}.size()>0" -x 2
```

##### [stack](https://arthas.gitee.io/stack.html)
如果想查看调用栈可以stack操作, 一直能上溯看到谁调用到此方法
```console
# 记录LogicalSwitchImpl所有方法被触发的调用栈数据
[arthas@28030]$ stack *facade.LogicalSwitchImpl *
# 按条件记录调用栈数据
[arthas@28030]$ stack *PolicyEngineHandler invokeProviders "params.length==6 && params[0].{? #this instanceof com.example.MyProvider}.size()>0"
```

##### [trace](https://arthas.gitee.io/trace.html)
如果想查看调用栈可以trace操作，往下看到后面每一个被调用的方法及执行时间
```console
# 记录LogicalSwitchImpl所有方法被触发的调用栈数据
[arthas@28030]$ trace *facade.LogicalSwitchImpl delete*
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
