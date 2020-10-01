***

## JDK operations
- [错误处理](#运行错误处理)
	- [Operation not permitted](#operation-not-permitted)
	- [Unable to open socket file](#unable-to-open-socket-file)
	- [cannot open shared object file](#cannot-open-shared-object-file)
	- [Can't attach to the process: ptrace](#cant-attach-to-the-process-ptrace)
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
  - [jvm-sandbox](https://github.com/alibaba/jvm-sandbox)
  - [jvm-sandbox-repeater](https://github.com/alibaba/jvm-sandbox-repeater)
  - [findtheflow](http://findtheflow.io/docs/doc_intellij.html#_how_to_use_flow_standalone_version)
  - [Bistoury](https://github.com/qunarcorp/bistoury)
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

# 如果目标jvm是jre启动，需要找一个对于jdk版本
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
当search loaded class时候，常常碰到class找不到,确认jar文件已经放在classpath. 
```console
[arthas@27705]$ sc *MyContext
Affect(row-cnt:0) cost in 2319 ms.
```
这种情况一般是由于目标class未被其他class调用到，因此jvm中没有加载，只要触发调用后就可以search到。
