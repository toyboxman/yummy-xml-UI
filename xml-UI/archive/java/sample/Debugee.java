package cn.gene.debuger;

import com.sun.jdi.Bootstrap;
import com.sun.jdi.Location;
import com.sun.jdi.ReferenceType;
import com.sun.jdi.StackFrame;
import com.sun.jdi.ThreadReference;
import com.sun.jdi.VirtualMachine;
import com.sun.jdi.VirtualMachineManager;
import com.sun.jdi.connect.AttachingConnector;
import com.sun.jdi.connect.Connector;
import com.sun.jdi.connect.IllegalConnectorArgumentsException;
import com.sun.jdi.event.BreakpointEvent;
import com.sun.jdi.event.Event;
import com.sun.jdi.event.EventQueue;
import com.sun.jdi.event.EventSet;
import com.sun.jdi.request.BreakpointRequest;
import com.sun.jdi.request.EventRequestManager;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Debugee {
    public static void main(String[] args) throws Exception {
        System.out.println("attach remote jvm");
        Debugee debugee = new Debugee();
        VirtualMachine machine = debugee.connect("127.0.0.1", 9000);
        machine.setDebugTraceMode(0);
        boolean canAddMethod = machine.canAddMethod();
        System.out.println("Do we add new method: " + canAddMethod);
        System.out.println("Do we redefine class: " + machine.canRedefineClasses());
        System.out.println("Do we modify class: " + machine.canBeModified());
        System.out.println("JVM Can Pop Frame:" + machine.canPopFrames());

        ThreadReference mainThread = null;
        for (ThreadReference tRef : machine.allThreads()) {
            //just use main thread for now...
            if (tRef.name().equals("main")) {
                System.out.println("main thread acquired");
                mainThread = tRef;
            } else {
                System.out.println("Thread :" + tRef.name());
            }
        }

        machine.allClasses().stream().filter(refer -> {
            return refer.name().contains("cn.gene");
        }).forEach(refer -> {
            System.out.println("Class :" + refer.name());
            System.out.println("Class Obj :" + refer.classObject());
            System.out.println("Class Loader :" + refer.classLoader());
            System.out.println("Class Initialization Fails:" + refer.failedToInitialize());
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
        });
    }

    public void listenForData(VirtualMachine vm, ReferenceType refer) throws Exception {
        EventQueue eventQueue = vm.eventQueue();
        while (true) {
            System.out.println("POLL EVENT: ");
            EventSet eventSet = eventQueue.remove(1000);
            System.out.println("Get EVENT: ");
            if (eventSet == null) continue;
            for (Event ev : eventSet) {
                if (ev instanceof BreakpointEvent) {
                    BreakpointEvent breakpointEvt = (BreakpointEvent) ev;
                    ThreadReference threadReference = breakpointEvt.thread();
                    System.out.println("Current thread status: " + threadReference.status());
                    StackFrame stackFrame = threadReference.frame(0);
                    System.out.println("Current stack: " + stackFrame);

                    threadReference.popFrames(stackFrame);
                    HashMap<ReferenceType, byte[]> map = new HashMap<>();
                    Path path = FileSystems.getDefault().getPath("./", "Target.class");
                    byte[] clsBytes = Files.readAllBytes(path);
                    map.put(refer, clsBytes);
                    vm.redefineClasses(map);
                    /*for (LocalVariable localVar : stackFrame.visibleVariables()) {
                        Value val = null;
                        try {
                            val = stackFrame.getValue(localVar);
                            if (localVar.name().equals("time")) {
                                System.out.println("VARIABLE[time] ON STACK: " + val);
                                Constructor<IntegerValueImpl> valueConstructor = IntegerValueImpl
                                    .class.getDeclaredConstructor(VirtualMachine.class, int.class);
                                valueConstructor.setAccessible(true);
                                IntegerValueImpl integerValue = valueConstructor.newInstance(vm, 500);
                                IntegerValueImpl integerValue = VirtualMachine.mirrorOf(500);
                                System.out.println("New Value: " + integerValue);
                                stackFrame.setValue(localVar, integerValue);
                                System.out.println("Set VARIABLE[time] ON STACK: " + stackFrame.getValue(localVar));
                            }
                        } catch (Exception e) {
                            System.out.println(ev);
                            e.printStackTrace();
                            //sweep it under the rug....
                        }
                    }*/

                }

                /*if (ev instanceof ModificationWatchpointEvent) {
                    //THERE IS MOVEMENT!!!!

                    ModificationWatchpointEvent me = (ModificationWatchpointEvent) ev;

                    addToTable(me.field().name(), me.valueToBe().toString(), me.field().typeName());

                    System.out.println(me);
                    System.out.println("Variable: " + me.field().name());
                    System.out.println("Value: " + me.valueToBe());

                    System.out.println();


                }*/

            }

            eventSet.resume();
        }
    }

    public VirtualMachine connect(String host, int port) throws IOException {
        String strPort = Integer.toString(port);
        AttachingConnector connector = getConnector();
        try {
            VirtualMachine vm = connect(connector, host, strPort);
            return vm;
        } catch (IllegalConnectorArgumentsException e) {
            throw new IllegalStateException(e);
        }
    }

    private AttachingConnector getConnector() {
        VirtualMachineManager vmManager = Bootstrap.virtualMachineManager();

        for (Connector connector : vmManager.attachingConnectors()) {
            if ("com.sun.jdi.SocketAttach".equals(connector.name()))
                return (AttachingConnector) connector;
        }
        throw new IllegalStateException();
    }

    private VirtualMachine connect(AttachingConnector connector, String host, String port)
        throws IllegalConnectorArgumentsException, IOException {

        Map<String, Connector.Argument> args = connector.defaultArguments();
        Connector.Argument portArg = args.get("port");
        portArg.setValue(port);
        Connector.Argument addressArg = args.get("hostname");
        addressArg.setValue(host);

        return connector.attach(args);
    }
}
