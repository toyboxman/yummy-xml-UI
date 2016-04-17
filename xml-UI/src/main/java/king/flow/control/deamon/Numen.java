/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.flow.control.deamon;

import java.lang.management.ManagementFactory;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;
import static king.flow.common.CommonUtil.getLogger;

/**
 *
 * @author Administrator
 */
public class Numen {

    public static void main(String[] args) {
        Thread deamon = new Thread(() -> {
            while (true) {
                try {
                    int interval = 3;
                    Thread.sleep(TimeUnit.SECONDS.toMillis(interval));
                    getLogger(Numen.class.getName()).log(Level.INFO,
                            "Application runs well, do not restart it. Next check will be in {0} seconds\n", interval);
                } catch (InterruptedException ex) {
                    getLogger(Numen.class.getName()).log(Level.WARNING,
                            "Demon thread hits InterruptedException as details : \n{0}", ex);
                }
            }
        });
        deamon.setName("Deamon of King");
        deamon.start();

        MBeanServer mbeanServer = ManagementFactory.getPlatformMBeanServer();
        try {
            ObjectName numenName = new ObjectName("king.flow.control.deamon:Name=NumenMonitor,Type=CheckNumen");
            mbeanServer.registerMBean(new NumenMonitor(), numenName);
        } catch (MalformedObjectNameException | InstanceAlreadyExistsException | MBeanRegistrationException | NotCompliantMBeanException ex) {
            getLogger(Numen.class.getName()).log(Level.SEVERE,
                    "fail to start Numen Deamon", ex);
        }

    }
}
