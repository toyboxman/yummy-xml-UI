/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.flow.control.deamon;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.rmi.registry.LocateRegistry;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;
import king.flow.common.CommonConstants;
import static king.flow.common.CommonUtil.getLogger;

/**
 *
 * @author Administrator
 */
public class Numen {

    public static void main(String[] args) {
        Thread deamon = new Thread(() -> {
            JMXConnector jmxc = null;
            while (true) {
                try {
                    int interval = 5;
                    Thread.sleep(TimeUnit.SECONDS.toMillis(interval));
                    JMXServiceURL url = new JMXServiceURL(CommonConstants.APP_JMX_RMI_URL);
                    jmxc = JMXConnectorFactory.connect(url, null);
                    getLogger(Numen.class.getName()).log(Level.INFO,
                            "Application runs well, do not restart it. Next check will be in {0} seconds\n", interval);
                } catch (InterruptedException ex) {
                    getLogger(Numen.class.getName()).log(Level.WARNING,
                            "Deamon thread hits InterruptedException as details : \n{0}", ex.getMessage());
                } catch (IOException ex) {
                    Logger.getLogger(Numen.class.getName()).log(Level.WARNING,
                            "Fail to ping application due to :\n{0}", ex.getMessage());
                    try {
                        // should restart application again
                        Runtime.getRuntime().exec(CommonConstants.APP_STARTUP_ENTRY);
                    } catch (IOException e) {
                        Logger.getLogger(Numen.class.getName()).log(Level.WARNING,
                                "fail to restart dead application again due to :\n{0}", e.getMessage());
                    }
                } finally {
                    if (jmxc != null) {
                        try {
                            jmxc.close();
                        } catch (IOException ex) {
                            Logger.getLogger(Numen.class.getName()).log(Level.WARNING,
                                    "Fail to close JMXConnector to application due to :\n{0}", ex.getMessage());
                        }
                    }
                }
            }
        });
        deamon.setName("Deamon of King");
        deamon.start();

        MBeanServer mbeanServer = ManagementFactory.getPlatformMBeanServer();
        try {
            ObjectName numenName = new ObjectName(NumenMonitor.JMX_BEAN_NAME);
            mbeanServer.registerMBean(new NumenMonitor(), numenName);
            //about url referring to http://stackoverflow.com/questions/2768087/explain-jmx-url
            //protocol:rmi, host:localhot, port:random, url:/jndi/rmi://localhost:1099/jmxrmi
            LocateRegistry.createRegistry(CommonConstants.WATCHDOG_JMX_RMI_PORT);
            JMXServiceURL serviceURL = new JMXServiceURL(CommonConstants.WATCHDOG_JMX_RMI_URL);
            JMXConnectorServer jcs = JMXConnectorServerFactory.newJMXConnectorServer(serviceURL, null, mbeanServer);
            jcs.start();
        } catch (MalformedObjectNameException | InstanceAlreadyExistsException | MBeanRegistrationException | NotCompliantMBeanException | IOException ex) {
            getLogger(Numen.class.getName()).log(Level.SEVERE,
                    "fail to start Numen Deamon", ex);
        }
    }
}
