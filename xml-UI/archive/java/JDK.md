***

## JDK operations
- [������](#���д�����)
	- [Operation not permitted](#operation-not-permitted)
	- [Unable to open socket file](#unable-to-open-socket-file)
	- [Cannot open shared object file](#cannot-open-shared-object-file)
	- [Can't attach to the process: ptrace](#cant-attach-to-the-process-ptrace)
  - [JVM����Ҽ���](https://mp.weixin.qq.com/s/cwU2rLOuwock048rKBz3ew)
- [����ʹ��](#ʹ������)
	- [jps](#jps)
	- [jstack](#jstack)
	- [jmap](#jmap)
	- [jinfo](#jinfo)
	- [jstat](#jstat)
  - [javap](#javap)
  - [javadoc](#javadoc)
- Debug/Monitor��Դ����
  - [Arthas](#arthas)
    - [Ӧ�ð���](https://mp.weixin.qq.com/mp/appmsgalbum?__biz=MzU2MTY2MjE4OQ==&action=getalbum&album_id=1430941903897460741&subscene=189)
  - [jvm-sandbox](https://github.com/alibaba/jvm-sandbox)
  - [jvm-sandbox-repeater](https://github.com/alibaba/jvm-sandbox-repeater)
  - [findtheflow](http://findtheflow.io/docs/doc_intellij.html#_how_to_use_flow_standalone_version)
  - [Bistoury](https://github.com/qunarcorp/bistoury)  
    `����debug����ģ��ide�������飬ͨ��web�����ṩ�ϵ���ԵĹ��ܣ������ڲ�����Ӧ�õ�����²���ϵ㴦����Ϣ(�������ر�������Ա��������̬�����ͷ�������ջ)`
  - [Bytecode Viewer](https://github.com/Konloch/bytecode-viewer)
  - [BTrace](https://github.com/btraceio/btrace/wiki)
  - [vjtools](https://github.com/vipshop/vjtools)
  - [���Լ�ؾ��鼯](https://github.com/vipcolud/monitor)
  - [spring-loaded](https://github.com/spring-projects/spring-loaded)
- Evolution
  - [new in Java 14](https://mkyong.com/java/what-is-new-in-java-14/)

***

### ���д�����
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
����[����](https://bugs.eclipse.org/bugs/show_bug.cgi?id=432069)��������޸��ڴ����ģʽ���Խ������ʧ������
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
������������Ŀ��process hungס,����ǿ��dump thread��Ϣ
```console 
> jstack -l -F 2554
```
�������deduce type�����������������ʽȥ�ų�
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
���������ʾ����ȷ��ʵ����������ִ��jstack��user��java process��owner��һ��
```console 
#�鿴26191���̵�owner
> ps -ef | grep 26191
> sudo -u owner jstack -l 26191
```
�������lib�Ҳ���, �ļ�Ŀ¼�����ڣ��������洦���ų�����
#### cannot open shared object file
```console 
> sudo -u owner jstack -l 26191> ls -al /
drwx------   7 root root     4096 May 24 06:10 root
./jstack: error while loading shared libraries: libjli.so: cannot open shared object file: No such file or directory
```
���ִ���ʵ���������ڵ�ǰĿ¼ִ��Ȩ�޲������
```console 
#�鿴ִ��·��Ȩ��
> pwd
/root/jdk/bin
#���ڷ�root�û��޷����ʲ���,��Ҫ��owner�û�����Ȩ��
> chmod 777 /root
# �ɹ�������ջ��Ϣdump���ļ���
> sudo -u owner jstack -l 26191 > stack.log
```
#### Can't attach to the process: ptrace
```console 
sudo -u nsx ./jmap 1180    
Attaching to process ID 1180, please wait...
Error attaching to process: sun.jvm.hotspot.debugger.DebuggerException: Can't attach to the process: ptrace(PTRACE_ATTACH, ..)...
```
���ִ���������ϵͳptrace(process trace)û���趨�ɵ���ģʽ
```console 
#��ʱ�޸ķ����ǽ��ں˱����޸�,�������ʧЧ
#������Щϵͳ���޴��ļ�/proc/sys/kernel/yama/ptrace_scope
$ echo 0 | sudo tee /proc/sys/kernel/yama/ptrace_scope

#�����޸ķ����ǽ�ptrace�Ŀ��ش�
$ sudo vi /etc/sysctl.d/10-ptrace.conf
#��ptrace_scopeֵ�ĳ�0
#����ϵͳ
kernel.yama.ptrace_scope = 0
```

### ����
#### jps
```console 
#�鿴��ǰjvmʵ��
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
#�鿴��ǰstack��Ϣ
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
#dump��ǰ����Ϣ
# -dump:<dump-options> to dump java heap in hprof binary format
#		<dump-options>
#   			live     dump only live objects; if not specified,
#                         all objects in the heap are dumped.
#				format=b     binary format
#				file=<file>  dump heap to <file>
#
> sudo -u owner ./jmap -dump:live,format=b,file=heap.bin 1180
# dump������bin�ļ�������jvisualvm�� Ҳ������jhat��
# jhat������һ��web����,����jvisualvm���ӻ���������
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


#�鿴��ǰ����Ϣ
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
#�鿴��ǰjvmʵ������Ϣ
 > jinfo 29620
...
Java System Properties:

java.runtime.name = Java(TM) SE Runtime Environment
sun.boot.library.path = /usr/jdk/instances/jdk1.6.0/jre/lib/sparc
java.vm.version = 1.6.0-rc-b100
...
#�鿴dump�ļ�����Ϣ
> jinfo $JAVA_HOME/bin/java core.29620
```
#### jstat
�鿴����ϵ�ǰjvmʵ������������
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
������ֽ����ļ�
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
����Դ��ע�Ͳ�����׼doc�ĵ�
```console
# -d ָ��html����λ��
javadoc common/java/tracing/src/main/java/example/tracing/*.java -d ./javadoc/
```

#### Arthas
- [Diagnostic Tool Arthas](https://github.com/alibaba/arthas)
- [�����ֲ�](https://arthas.gitee.io/trace.html)
```console
# �������а�
curl -O https://alibaba.github.io/arthas/arthas-boot.jar
# ��������
java -jar arthas-boot.jar

# ���Ŀ��jvm��jre��������Ҫ��һ����Ӧ��jdk�汾
# ��򵥷�ʽ���ڵ��Ի���������һ����ͬ�汾���Ը߰汾��jdk��Ȼ��ͨ��jdk��java������
# azul��zulu�ǻ���openjdk��һ��jdk���Ͱ汾
wget https://cdn.azul.com/zulu/bin/zulu8.52.0.23-ca-jdk8.0.282-linux_x64.tar.gz
# Ҳ���ԴӶ�Ӧjdk����jreȱʧ�ļ�
cp /usr/java/jre1.8.0_251/bin/java /usr/java/jdk1.8.0_251/bin
# ���Ŀ��jre lib��ȱ��tools.jar����Ҫ��jdk�и���һ��
cp /usr/java/jdk1.8.0_251/lib/tools.jar /usr/java/jre1.8.0_251/lib

# ���Ŀ��jvm����root�û�Ȩ��������������proton�û�ִ��
chown -hR proton arthas-boot.jar 
# ��ִ�а���rootĿ¼�ƶ���proton�û�Ȩ��Ŀ¼��
mv arthas-boot.jar /opt/app/proton-tomcat
# ��proton�û�Ȩ���������̣�attach�� jvm 3331����
sudo -u proton /usr/java/jdk1.8.0_251/bin/java -jar /opt/app/proton-tomcat/arthas-boot.jar 3331
```
##### search class  
��search loaded classʱ�򣬳�������class�Ҳ���,ȷ��jar�ļ��Ѿ�����classpath. ���jar�Ѿ���-cpָ��Ŀ¼�У�����Ȼ��Ѱ�������������
```console
[arthas@27705]$ sc *MyContext
Affect(row-cnt:0) cost in 2319 ms.
```
�������������Ŀ��class��δ��jvm���أ�һ��������Ŀ����δ������classֱ�ӵ��ã�����jvmû�м��أ������ʽֻҪ����һ�ε��þͿ���search����
```console
# �鿴class������Ϣ
[arthas@24124]$ sc -d *ConfigConverter

# �鿴����class��classloader��ʾ��
[arthas@24124]$ sc -d *ConfigConverter | grep classLoaderHash
classLoaderHash   1911bd1e
# �鿴classloader������Щ�ļ�
[arthas@24124]$ classloader -c 1911bd1e
```
##### search method
��Ѱclassȫ��method�������²���
```console
[arthas@17977]$ sm example.messaging.RpcServices
example.messaging.RpcServices <init>()V
example.messaging.RpcServices getServices()Ljava/util/Map;
example.messaging.RpcServices getStubType(Ljava/lang/String;)Ljava/lang/Class;
example.messaging.RpcServices setRpcEnabled(Z)V
example.messaging.RpcServices getServiceEndpoint(Ljava/lang/String;)Ljava/lang/String;
Affect(row-cnt:14) cost in 339 ms.

# ��Ѱ���method
[arthas@28030]$ sm *facade.LogicalSwitchImpl delete*
com.example.LogicalSwitchFacadeImpl deleteLogicalSwitch(Ljava/lang/String;)V
com.example.LogicalSwitchFacadeImpl deleteLogicalSwitch_aroundBody6(Lorg/aspectj/lang/JoinPoint;)V
Affect(row-cnt:2) cost in 188 ms.

# �鿴method������Ϣ
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
ȷ��method��Ϳ���watch����
```console
[arthas@17977]$ watch example.messaging.RpcServices getServiceEndpoint
Affect(class count: 1 , method count: 1) cost in 481 ms, listenerId: 1
ts=2020-12-16 13:30:41; [cost=1.273833ms] result=@ArrayList[
    # ��һ����Ƿ������������array�� size=1��ʾֻ��һ������
    @Object[][isEmpty=false;size=1],
    # �ڶ�����Ƿ���������class instance
    @NsxRpcServices[example.messaging.RpcServices@46ba4da8],
    # ��������Ƿ�������ֵ
    null,
]

# ����뿴������ϸ�ڣ������ϸ�ڵȣ�����ͨ�� -x 2 �趨���ڿ�����
# OGNL���ʽ "{params,target,returnObj}" �趨result��������/��ʵ��/����ֵ����, ����Ĭ�ϵķ������ݸ�ʽ
# �趨 "{params,returnObj}" resultֻ���� �����ͷ���ֵ
[arthas@17977]$ watch example.messaging.RpcServices getServiceEndpoint "{params,target,returnObj}" -x 2
Press Q or Ctrl+C to abort.
Affect(class count: 1 , method count: 1) cost in 421 ms, listenerId: 2
ts=2020-12-16 13:42:39; [cost=0.184837ms] result=@ArrayList[
    @Object[][
        # ��һ��ڶ����ʾ����������string��ֵ ��Traceflow��
        @String[Traceflow],
    ],
    @NsxRpcServices[
        # �ڶ���ڶ����ʾRpcServicesʵ����fields��ֵ
        logger=@Logger[example.logging.Logger@19340508],
        services=@ConcurrentHashMap[isEmpty=true;size=0],
        rpcEnabled=@Boolean[true],
    ],
    null,
]

# ֻ�����ϸ��
[arthas@17977]$ watch example.messaging.RpcServices getServiceEndpoint "{params}" -x 3
# ֻ�����������ĿΪ6����methodϸ��,����ͬ��overload����������
[arthas@17977]$ watch example.messaging.RpcServices getServiceEndpoint "{params[0],params[1]}" "params.length==6" -x 2
# ������˲���ֻ��һ�������������Ҫͨ���߼������� && || ������
# ������������� ��θ���6���������е�һ������(Ϊ��������)��sizeҪ����2
[arthas@17977]$ watch *PolicyEngineHandler invokeProviders "{params[0],params[1]}" "params.length==6 && params[0].size()>2" -x 2
# ������������� ��θ���6���������е�һ������(Ϊ��������)��elements����MyProviderԪ�����͵ĸ���Ҫ����0
# ���ڼ������Ͳ������ʽ params[0].{? #this instanceof com.example.MyProvider}������һ���¼��϶���
[arthas@17977]$ watch *PolicyEngineHandler invokeProviders "{params[0],params[1]}" "params.length==6 && params[0].{? #this instanceof com.example.MyProvider}.size()>0" -x 2
```

##### [stack](https://arthas.gitee.io/stack.html)
�����鿴����ջ����stack����, һֱ�����ݿ���˭���õ��˷���
```console
# ��¼LogicalSwitchImpl���з����������ĵ���ջ����
[arthas@28030]$ stack *facade.LogicalSwitchImpl *
# ��������¼����ջ����
[arthas@28030]$ stack *PolicyEngineHandler invokeProviders "params.length==6 && params[0].{? #this instanceof com.example.MyProvider}.size()>0"
```

##### [trace](https://arthas.gitee.io/trace.html)
�����鿴����ջ����trace���������¿�������ÿһ�������õķ�����ִ��ʱ��
```console
# ��¼LogicalSwitchImpl���з����������ĵ���ջ����
[arthas@28030]$ trace *facade.LogicalSwitchImpl delete*
```

##### [retransform](https://arthas.gitee.io/retransform.html)
�������Ѽ��ص�class�����滻����ʹ��retransform��redefine�����Ʊ���IDE����ʱ��ֱ���޸Ĵ��뼴����Ч������jvm���滻���Ʋ����޸ġ���ӡ�ɾ�����field��method�����������������������Ƽ�����ֵ��<br>  
retransform��redefine���������е����:
  - redefine ֮��ԭ����class���ָܻ�����������jvm���п���ʧ��(�����������µ�field)��reset�����redefine������Ч����������û�������Ҫredefineԭʼclass���ֽ��롣
  - ���⣬redefine�����jad/watch/trace/monitor/tt��������ͻ��ִ����redefine֮�������ִ�������ᵽ���������redefine���ֽ������á���Ϊ�󼸸��������Retransform��jdk����redefine��Retransform�ǲ�ͬ�Ļ��ƣ�ͬʱʹ�����ֻ����������ֽ��룬ֻ������޸ĵ���һ������Ч
  - retransform����Խ��лָ����ص�������ص�class�ֽ���
  - jad/mcҲ���ʺ������ӵķ�����ͱ��룬���Կ��Ա��ع��̱����class�ļ��ϴ�������class���滻
```console
# retransform
[arthas@28030]$ retransform /tmp/Test.class
# �鿴 retransform entry, TransformCount����ʾ��ClassFileTransformer.transform�����ﳢ�Է���entry��Ӧ��.class�ļ��Ĵ���������������transformһ���ɹ�
[arthas@28030]$ retransform -l
# ɾ��ָ�� retransform entry
[arthas@28030]$ retransform -d 1
# ɾ������ retransform entry
[arthas@28030]$ retransform --deleteAll  
# ��ʽ���� retransform 
# ����ͬһ���࣬�����ڶ�� retransform entryʱ�������ʽ���� retransform ���������ӵ�entry��Ч(id����)
[arthas@28030]$ retransform --classPattern demo.* 
[arthas@28030]$ retransform -c 327a647b /tmp/Test.class /tmp/Test\$Inner.class
[arthas@28030]$ retransform --classLoaderClass 'sun.misc.Launcher$AppClassLoader' /tmp/Test.class

# �����ĳ����ִ�� retransform ֮��������Ӱ��ָ�ԭ״�� Ҫɾ��������Ӧ�� retransform entry�����´��� retransform��
# �������������е� retransform entry�������´��� retransform ����arthas stopʱ��retransform��������Ȼ��Ч�� 

# redefine
[arthas@28030]$ jad --source-only com.example.demo.arthas.user.UserController > /tmp/UserController.java
[arthas@28030]$ mc /tmp/UserController.java -d /tmp
[arthas@28030]$ redefine /tmp/com/example/demo/arthas/user/UserController.class
```

##### stop
���Ҫ����sessionִ��stop����
```console
[arthas@28030]$ stop
```

##### [classloader](https://arthas.gitee.io/classloader.html)
�鿴classloader�ļ̳�����urls���������Ϣ
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

# ����class�ļ���resource�ļ���ʲô�ط����ص�
[arthas@30000]$ sc -d *EntityType | grep code-source                                    
 code-source       /opt/tomcat/webapps/api/WEB-INF/lib/model.jar

[arthas@28030]$ classloader -c 750f747d -r java/lang/String.class
 jar:file:/usr/lib/jvm/zre-8-amd64/lib/rt.jar!/java/lang/String.class 
```
