***

## Ways to Remote Debug
JPDA(Java Platform Debugger Architecture)是一套完整的JVM调试架构，有上中下三层。分别是API层JDI，协议层JDWP，JVM接口实现层JVMDI。现在IDE所实现的remote debug包括hotswap都是基于此来实现。详细描述可以参看[官方文档](https://docs.oracle.com/javase/8/docs/technotes/guides/jpda/enhancements1.4.html)
### dev on JPDA
JDI(com.sun.jdi)是随JDK的版本发布，编译在tools.jar中，可以参考[API文档](https://docs.oracle.com/javase/8/docs/jdk/api/jpda/jdi/index.html)。如果基于maven工程的话，需要指定本地编译依赖路径，public repo中没有此jar。
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
理论上如果能够处理JDWP协议，任何语言实现的客户端都可以完成JDI的功能。关于这块可以参看[此文](https://ioactive.com/hacking-java-debug-wire-protocol-or-how/)，以及[实现](https://github.com/IOActive/jdwp-shellifier)
### use of JDI
调用JDI的接口主要包括连接JVM 和发送指令两步，下面的代码块完成这些功能。
* 获取remote jvm的connector
```java 
VirtualMachineManager vmManager = Bootstrap.virtualMachineManager();

for (Connector connector : vmManager.attachingConnectors()) {
    if ("com.sun.jdi.SocketAttach".equals(connector.name()))
        return (AttachingConnector) connector;
}
```
* connector连接到目标JVM
```java
Map<String, Connector.Argument> args = connector.defaultArguments();
Connector.Argument portArg = args.get("port");
portArg.setValue(port);
Connector.Argument addressArg = args.get("hostname");
addressArg.setValue(host);

connector.attach(args);
```
* 设定debug断点/监听
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
* hotswap class文件
```bash
# note:
# redefine方法执行后，按文档描述所有断点都会被删除
# 经测试main thread似乎不支持refine后及时生效??? 
#    原因分析：测试Target类的main方法中是一个无穷循环，导致旧方法始终无法退出，新方法无法reload
#                         所以main方法始终在redefine后无法即时生效
#                        如果改成方法体外循环，那么redefine class后新方法会即时生效
#    循环体替换方法：只能强行先将stack frame清空，popFrames后redefine才生效
#     popFrames执行前提是thread需要处于sleeping状态，即暂停执行thread
#    设定断点或调用suspend方法都可以达到效果，否则pop会失败提示thread运行中
threadReference.popFrames(stackFrame);
HashMap<ReferenceType, byte[]> map = new HashMap<>();
Path path = FileSystems.getDefault().getPath("./", "Target.class");
byte[] clsBytes = Files.readAllBytes(path);
map.put(refer, clsBytes);
vm.redefineClasses(map);
```
* 打开jdwp日志输出
```bash
VirtualMachine machine = debugee.connect("127.0.0.1", 9000);
// 默认为0不输出jdwp过程日志
// 大于1会输出详细信息，比如redefine将新类文件发送到jvm
machine.setDebugTraceMode(1);

[JDI: Sending Command(id=70) JDWP.VirtualMachine.RedefineClasses]
[JDI: Sending:                 classes(ClassDef[]): ]
[JDI: Sending:                     classes[i](ClassDef): ]
[JDI: Sending:                     refType(ReferenceTypeImpl): ref=7]
[JDI: Sending:                     classfile(byte[]): ]
[JDI: Sending:                         classfile[i](byte): -54]
[JDI: Sending:                         classfile[i](byte): -2]
```
* 运行sample代码
完整代码查看链接[Sample Codes](./sample/Debugee.java)
```bash
#1.启动Target进程
java -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=127.0.0.1:9000 cn.gene.debuger.Target
#2.修改Target源文件，loop方法中增加一行打印
#3.启动Debugee进程连接127.0.0.1:9000
java cn.gene.debuger.Debugee

#观察执行结果, Target进程控制台在debugee连接上前后会输出两种打印语句
Listening for transport dt_socket at address: 9000
run target program
hit target 1
hit target 2
hit target 3
hit target 4
hit target 1   -->增加新的打印
just wait 5000
hit target 2
just wait 5000
```
### 参考项目
[https://github.com/Jody7/JDWP-Client](https://github.com/Jody7/JDWP-Client)
<br>[https://github.com/kherink/jdwp-analyzer](https://github.com/kherink/jdwp-analyzer)
<br>[https://github.com/HotswapProjects/HotswapAgent](https://github.com/HotswapProjects/HotswapAgent)