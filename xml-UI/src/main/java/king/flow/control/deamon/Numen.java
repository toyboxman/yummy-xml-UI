/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.flow.control.deamon;

import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.net.URL;
import java.rmi.registry.LocateRegistry;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.LogManager;
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
import king.flow.common.CommonUtil;
import static king.flow.common.CommonUtil.getLogger;
import static king.flow.control.BankAppStarter.LOG_CONF;
import king.flow.net.TunnelBuilder;

/**
 *
 * @author Administrator
 */
public class Numen {

    private final int checkInterval;
    private volatile int times = 1;

    public Numen(int checkInterval) {
        this.checkInterval = checkInterval;
    }

    private boolean initConnectorServer() {
        try {
            MBeanServer mbeanServer = ManagementFactory.getPlatformMBeanServer();
            ObjectName numenName = new ObjectName(NumenMonitor.JMX_BEAN_NAME);
            mbeanServer.registerMBean(new NumenMonitor(), numenName);
            //about url referring to http://stackoverflow.com/questions/2768087/explain-jmx-url
            //protocol:rmi, host:localhot, port:random, url:/jndi/rmi://localhost:1099/jmxrmi
            LocateRegistry.createRegistry(CommonConstants.WATCHDOG_JMX_RMI_PORT);
            JMXServiceURL serviceURL = new JMXServiceURL(CommonConstants.WATCHDOG_JMX_RMI_URL);
            JMXConnectorServer jcs = JMXConnectorServerFactory.newJMXConnectorServer(serviceURL, null, mbeanServer);
            jcs.start();
            return true;
        } catch (MalformedObjectNameException | InstanceAlreadyExistsException | MBeanRegistrationException | NotCompliantMBeanException | IOException ex) {
            getLogger(Numen.class.getName()).log(Level.SEVERE,
                    "fail to start Numen Deamon JMX Connector Server due to :\n{0}", ex.getMessage());
            return false;
        }
    }

    private void startup() {
        if (initConnectorServer()) {
            if (!CommonUtil.isWatchDogEnabled()) {
                getLogger(Numen.class.getName()).log(Level.INFO,
                        "Disable watch dog service, Numen will exit now");
                return;
            }

            Thread deamon = new Thread(() -> {
                JMXConnector jmxc = null;
                while (true) {
                    try {
                        Thread.sleep(TimeUnit.SECONDS.toMillis(checkInterval * times));
                        JMXServiceURL url = new JMXServiceURL(CommonConstants.APP_JMX_RMI_URL);
                        jmxc = JMXConnectorFactory.connect(url, null);
                        times = 1;
                        getLogger(Numen.class.getName()).log(Level.CONFIG,
                                "Application runs well, do not restart it. Next check will be in {0} seconds\n", checkInterval);
                    } catch (InterruptedException ex) {
                        getLogger(Numen.class.getName()).log(Level.WARNING,
                                "Deamon thread hits InterruptedException as details : \n{0}", ex.getMessage());
                    } catch (IOException ex) {
                        Logger.getLogger(Numen.class.getName()).log(Level.WARNING,
                                "Fail to ping application due to :\n{0}", ex.getMessage());
                        try {
                            // should restart application again
                            if (times > 1) {
                                Runtime.getRuntime().exec(CommonConstants.APP_STARTUP_ENTRY);
                                getLogger(Numen.class.getName()).log(Level.INFO,
                                        "after a duration {0} seconds, let restart application[{1}]",
                                        new Object[]{checkInterval * times, CommonConstants.APP_STARTUP_ENTRY});
                            } else {
                                times = 3;
                                getLogger(Numen.class.getName()).log(Level.INFO,
                                        "wait a duration {0} seconds to see if application is really dead", checkInterval * times);
                            }
                        } catch (IOException e) {
                            getLogger(Numen.class.getName()).log(Level.WARNING,
                                    "fail to restart dead application due to :\n{0}", e.getMessage());
                        }
                    } finally {
                        if (jmxc != null) {
                            try {
                                jmxc.close();
                            } catch (IOException ex) {
                                getLogger(Numen.class.getName()).log(Level.WARNING,
                                        "Fail to close JMXConnector to application due to :\n{0}", ex.getMessage());
                            }
                        }
                    }
                }
            });
            deamon.setName("Deamon of King");
            deamon.start();
        } else {
            getLogger(Numen.class.getName()).log(Level.SEVERE,
                    "Fail to start up Numen deamon, exiting now");
        }
    }

    public static void main(String[] args) {
        try {
            LogManager.getLogManager().readConfiguration(Numen.class.getResourceAsStream("/numen.properties"));
            //force to read backend configuration
            TunnelBuilder.getTunnelBuilder();
        } catch (IOException | SecurityException ex) {
            System.setProperty(LOG_CONF, "./conf/logging.properties");
            Logger.getLogger(Numen.class.getName()).log(Level.WARNING,
                    "cannot find numen.properties due to :\n{0}", ex.getMessage());
        }

        getLogger(Numen.class.getName()).log(Level.INFO, "\n{0}", CommonUtil.showSystemInfo());
        new Numen(CommonUtil.getWatchDogInterval()).startup();
    }
}
