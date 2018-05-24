***

## JDK operations

### ���д�����
#### Operation not permitted
```bash
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
```bash 
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
```bash 
> jstack -l 2554
2554: Unable to open socket file: target process not responding or HotSpot VM not loaded
The -F option can be used when the target process is not responding
```
������������Ŀ��process hungס,����ǿ��dump thread��Ϣ
```bash 
> jstack -l -F 2554
```
�������deduce type�����������������ʽȥ�ų�
#### Unable to deduce type of thread
```bash 
> jstack -l 26191
Attaching to process ID 26191, please wait...
Debugger attached successfully.
Server compiler detected.
JVM version is 25.171-b11
Deadlock Detection:

java.lang.RuntimeException: Unable to deduce type of thread from address...
```
���������ʾ����ȷ��ʵ����������ִ��jstack��user��java process��owner��һ��
```bash 
#�鿴26191���̵�owner
> ps -ef | grep 26191
> sudo -u owner jstack -l 26191
```
### ʹ������
#### jps �鿴��ǰjvmʵ��
```bash 
 > jps
4048 Jps
9653 RemoteMavenServer
9578 Main

> jps -lv
9653 org.jetbrains.idea.maven.server.RemoteMavenServer
4070 sun.tools.jps.Jps
9578 com.intellij.idea.Main
```
#### jstack �鿴��ǰstack��Ϣ
```bash 
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