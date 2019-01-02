package gene.monitor.multithread;

import com.sun.jdi.VirtualMachine;

import java.io.IOException;

import gene.monitor.jdi.JdiClient;
import gene.monitor.jdi.VarMonitor;

public class MonitorThreadProgram {
    public static void main(String ... args) throws IOException {
        JdiClient client = new JdiClient();
        VirtualMachine machine = client.connect("127.0.0.1", 9000);
        VarMonitor monitor = new VarMonitor(19, machine);
        monitor.enableMonitor(MultiThreadProgram.class.getName());
    }
}
