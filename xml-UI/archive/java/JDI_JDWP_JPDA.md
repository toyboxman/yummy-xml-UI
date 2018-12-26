***

## Ways to Remote Debug
JPDA(Java Platform Debugger Architecture)��һ��������JVM���Լܹ��������������㡣�ֱ���API��JDI��Э���JDWP��JVM�ӿ�ʵ�ֲ�JVMDI������IDE��ʵ�ֵ�remote debug����hotswap���ǻ��ڴ���ʵ�֡���ϸ�������Բο�[�ٷ��ĵ�](https://docs.oracle.com/javase/8/docs/technotes/guides/jpda/enhancements1.4.html)
### dev on JPDA
JDI(com.sun.jdi)����JDK�İ汾������������tools.jar�У����Բο�[API�ĵ�](https://docs.oracle.com/javase/8/docs/jdk/api/jpda/jdi/index.html)���������maven���̵Ļ�����Ҫָ�����ر�������·����public repo��û�д�jar��
```xml
<dependency>
    <groupId>com.sun</groupId>
    <artifactId>tools</artifactId>
    <version>1.8</version>
    <scope>system</scope>
    <systemPath>/usr/lib64/jvm/java-1.8.0-openjdk-1.8.0/lib/tools.jar</systemPath>
</dependency>
}
``` 
����������ܹ�����JDWPЭ�飬�κ�����ʵ�ֵĿͻ��˶��������JDI�Ĺ��ܡ����������Բο�[����](https://ioactive.com/hacking-java-debug-wire-protocol-or-how/)���Լ�[ʵ��](https://github.com/IOActive/jdwp-shellifier)
### use of JDI
����JDI�Ľӿ���Ҫ��������JVM �ͷ���ָ������������Ĵ���������Щ���ܡ�
* ��ȡremote jvm��connector
```java 
VirtualMachineManager vmManager = Bootstrap.virtualMachineManager();

for (Connector connector : vmManager.attachingConnectors()) {
    if ("com.sun.jdi.SocketAttach".equals(connector.name()))
        return (AttachingConnector) connector;
}
```
* connector���ӵ�Ŀ��JVM
```java
Map<String, Connector.Argument> args = connector.defaultArguments();
Connector.Argument portArg = args.get("port");
portArg.setValue(port);
Connector.Argument addressArg = args.get("hostname");
addressArg.setValue(host);

connector.attach(args);
```
* �趨debug�ϵ�/����
```java
EventRequestManager erm = machine.eventRequestManager();
try {
    List<Location> line = refer.locationsOfLine(8);
    BreakpointRequest br = erm.createBreakpointRequest(line.get(0));
    br.setEnabled(true);
    System.out.println("All breakpoints :" + erm.breakpointRequests().size());
    debugee.listenForData(machine, refer);
} catch (Exception e) {
    e.printStackTrace();
}

EventQueue eventQueue = vm.eventQueue();
while (true) {
    System.out.println("POLL EVENT: ");
    EventSet eventSet = eventQueue.remove(1000);
    System.out.println("Get EVENT: ");
    if (eventSet == null) continue;
    for (Event ev : eventSet) {
        if (ev instanceof BreakpointEvent) {...}
}
```
* hotswap class�ļ�
```bash
# note:
# redefine����ִ�к󣬰��ĵ��������жϵ㶼�ᱻɾ��
# ������main thread�ƺ���֧��refine��ʱ��Ч??? 
#    ԭ�����������Target���main��������һ������ѭ�������¾ɷ���ʼ���޷��˳����·����޷�reload
#                         ����main����ʼ����redefine���޷���ʱ��Ч
#                        ����ĳɷ�������ѭ������ôredefine class���·����ἴʱ��Ч
#    ѭ�����滻������ֻ��ǿ���Ƚ�stack frame��գ�popFrames��redefine����Ч
#     popFramesִ��ǰ����thread��Ҫ����sleeping״̬������ִͣ��thread
#    �趨�ϵ�����suspend���������ԴﵽЧ��������pop��ʧ����ʾthread������
threadReference.popFrames(stackFrame);
HashMap<ReferenceType, byte[]> map = new HashMap<>();
Path path = FileSystems.getDefault().getPath("./", "Target.class");
byte[] clsBytes = Files.readAllBytes(path);
map.put(refer, clsBytes);
vm.redefineClasses(map);
```
* ��jdwp��־���
```bash
VirtualMachine machine = debugee.connect("127.0.0.1", 9000);
// Ĭ��Ϊ0�����jdwp������־
// ����1�������ϸ��Ϣ������redefine�������ļ����͵�jvm
machine.setDebugTraceMode(1);

[JDI: Sending Command(id=70) JDWP.VirtualMachine.RedefineClasses]
[JDI: Sending:                 classes(ClassDef[]): ]
[JDI: Sending:                     classes[i](ClassDef): ]
[JDI: Sending:                     refType(ReferenceTypeImpl): ref=7]
[JDI: Sending:                     classfile(byte[]): ]
[JDI: Sending:                         classfile[i](byte): -54]
[JDI: Sending:                         classfile[i](byte): -2]
```
* ����sample����
��������鿴����[Sample Codes](./sample/Debugee.java)
```bash
#1.����Target����
java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=127.0.0.1:9000 cn.gene.debuger.Target
#2.�޸�TargetԴ�ļ���loop����������һ�д�ӡ
#3.����Debugee��������127.0.0.1:9000
java cn.gene.debuger.Debugee

#�۲�ִ�н��, Target���̿���̨��debugee������ǰ���������ִ�ӡ���
Listening for transport dt_socket at address: 9000
run target program
hit target 1
hit target 2
hit target 3
hit target 4
hit target 1   -->�����µĴ�ӡ
just wait 5000
hit target 2
just wait 5000
```
### �ο���Ŀ
[https://github.com/Jody7/JDWP-Client](https://github.com/Jody7/JDWP-Client)
<br>[https://github.com/kherink/jdwp-analyzer](https://github.com/kherink/jdwp-analyzer)
<br>[https://github.com/HotswapProjects/HotswapAgent](https://github.com/HotswapProjects/HotswapAgent)