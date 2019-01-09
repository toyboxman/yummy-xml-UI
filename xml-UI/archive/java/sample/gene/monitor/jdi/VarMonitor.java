package gene.monitor.jdi;

import com.sun.jdi.AbsentInformationException;
import com.sun.jdi.Field;
import com.sun.jdi.IncompatibleThreadStateException;
import com.sun.jdi.LocalVariable;
import com.sun.jdi.Location;
import com.sun.jdi.PrimitiveValue;
import com.sun.jdi.ReferenceType;
import com.sun.jdi.StackFrame;
import com.sun.jdi.ThreadReference;
import com.sun.jdi.Value;
import com.sun.jdi.VirtualMachine;
import com.sun.jdi.event.BreakpointEvent;
import com.sun.jdi.event.Event;
import com.sun.jdi.event.EventQueue;
import com.sun.jdi.event.EventSet;
import com.sun.jdi.event.ModificationWatchpointEvent;
import com.sun.jdi.request.BreakpointRequest;
import com.sun.jdi.request.EventRequest;
import com.sun.jdi.request.EventRequestManager;
import com.sun.jdi.request.ModificationWatchpointRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class VarMonitor {
    private int lineNum = 0;
    private VirtualMachine vm = null;
    private ScheduledThreadPoolExecutor threadPool = null;

    public VarMonitor(int line, VirtualMachine vm) {
        this.lineNum = line;
        this.vm = vm;
        threadPool = new ScheduledThreadPoolExecutor(1);
    }

    public void enableMonitor(String className) {
        vm.allClasses().stream().filter(refer -> {
            return refer.name().equals(className);
        }).forEach(refer -> {
            EventRequestManager erm = vm.eventRequestManager();
            try {
                Field field = refer.fieldByName("index");
                ModificationWatchpointRequest watchpointRequest = erm.createModificationWatchpointRequest(field);
                watchpointRequest.setSuspendPolicy(EventRequest.SUSPEND_NONE);
                watchpointRequest.enable();

                listenForData(vm, refer);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void monitorVar(String className, String varName, int location, Map<String, String> cache) {
        vm.allClasses().stream().filter(refer -> {
            if (refer.name().lastIndexOf('.') == -1) return false;
            return refer.name().substring(refer.name().lastIndexOf('.') + 1).equals(className);
        }).forEach(refer -> {
            EventRequestManager erm = vm.eventRequestManager();
            try {
                List<Location> line = refer.locationsOfLine(location);
                BreakpointRequest br = erm.createBreakpointRequest(line.get(0));
                br.setEnabled(true);

                listenForVar(vm, refer, varName, cache);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void listenForVar(VirtualMachine vm, ReferenceType refer, String name, Map<String, String> cache) {
        EventQueue eventQueue = vm.eventQueue();
        threadPool.execute(() -> {
            while (true) {
                EventSet eventSet = null;
                try {
                    eventSet = eventQueue.remove(1000);
                    System.out.println("Get EVENT: " + eventSet);
                    if (eventSet == null) continue;
                    for (Event ev : eventSet) {
                        if (ev instanceof BreakpointEvent) {
                            BreakpointEvent breakpointEvt = (BreakpointEvent) ev;
                            ThreadReference threadReference = breakpointEvt.thread();
                            StackFrame stackFrame = threadReference.frame(0);
                            for (LocalVariable localVar : stackFrame.visibleVariables()) {
                                System.out.println("VARIABLE[Name] ON STACK: " + localVar.name());
                                Value val = null;
                                try {
                                    val = stackFrame.getValue(localVar);
                                    if (localVar.name().equals(name)) {
                                        System.out.println("VARIABLE[time] ON STACK: " + val);
                                        cache.put(name, val.toString());
                                    }
                                } catch (Exception e) {
                                    System.out.println(ev);
                                    e.printStackTrace();
                                    //sweep it under the rug....
                                }
                            }
                        }
                    }
                    eventSet.resume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (IncompatibleThreadStateException e) {
                    e.printStackTrace();
                } catch (AbsentInformationException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void listenForData(VirtualMachine vm, ReferenceType refer) {
        EventQueue eventQueue = vm.eventQueue();
        Stack<ThreadReference> stack = new Stack<>();
        List<Integer> lastIndex = new ArrayList<>(1);
        threadPool.execute(() -> {
            while (true) {
                EventSet eventSet = null;
                try {
                    eventSet = eventQueue.remove(1000);
                    System.out.println("Get EVENT: " + eventSet);
                    if (eventSet == null) continue;
                    for (Event ev : eventSet) {
                        if (ev instanceof BreakpointEvent) {
                            BreakpointEvent breakpointEvt = (BreakpointEvent) ev;
                            ThreadReference threadReference = breakpointEvt.thread();
                            StackFrame stackFrame = threadReference.frame(0);
                            for (LocalVariable localVar : stackFrame.visibleVariables()) {
                                System.out.println("VARIABLE[Name] ON STACK: " + localVar.name());
                                Value val = null;
                                try {
                                    val = stackFrame.getValue(localVar);
                                    if (localVar.name().equals("index")) {
                                        System.out.println("VARIABLE[time] ON STACK: " + val);
                                    }
                                } catch (Exception e) {
                                    System.out.println(ev);
                                    e.printStackTrace();
                                    //sweep it under the rug....
                                }
                            }
                        } else if (ev instanceof ModificationWatchpointEvent) {
                            ModificationWatchpointEvent evt = (ModificationWatchpointEvent) ev;
                            int value = ((PrimitiveValue) evt.valueCurrent()).intValue();
                            int valueTobe = ((PrimitiveValue) evt.valueToBe()).intValue();
                            System.out.println("VARIABLE[index] previous ON STACK: " + value);
                            System.out.println("VARIABLE[index] will be ON STACK: " + valueTobe);
                            if (lastIndex.isEmpty()) {
                                lastIndex.add(valueTobe);
                            } else {
                                if (valueTobe > lastIndex.get(0)) {
                                    //write operation
                                    stack.push(evt.thread());
                                    evt.thread().suspend();
                                    System.out.println("Suspend[thread]: " + evt.thread());
                                } else if (valueTobe == 0) {
                                    while (!stack.empty()) {
                                        ThreadReference thread = stack.pop();
                                        thread.resume();
                                        System.out.println("Resume[thread]: " + thread);
                                    }
                                }
                                lastIndex.remove(0);
                                lastIndex.add(0, valueTobe);
                            }
                        }
                    }
                    eventSet.resume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (IncompatibleThreadStateException e) {
                    e.printStackTrace();
                } catch (AbsentInformationException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
