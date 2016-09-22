/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.flow.control.agent;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import javax.management.AttributeNotFoundException;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanException;
import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.ReflectionException;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXServiceURL;
import king.flow.common.CommonConstants;
import king.flow.common.CommonUtil;
import static king.flow.common.CommonUtil.getLogger;
import static king.flow.control.agent.OpenCLI.COMMAND_SET;
import static king.flow.control.agent.OpenShellMXBean.OPEN_SHELL_JMX_BEAN_NAME;
import static king.flow.control.deamon.CheckNumen.VERSOPM_ATTRIBUTE;

/**
 *
 * @author Administrator
 */
public class OpenShell {

    private static final Logger LOGGER = getLogger("");

    public static void main(String[] args) {
        try {
            LogManager.getLogManager().readConfiguration(OpenShell.class.getResourceAsStream("/openshell.properties"));
        } catch (IOException | SecurityException ex) {
            LOGGER.log(Level.WARNING,
                    "cannot find openshell.properties due to :\n{0}", ex.getMessage());
        } finally {
            LOGGER.log(Level.CONFIG, "\n{0}", CommonUtil.showSystemInfo());
        }

        if (!(args.length > 0 && COMMAND_SET.contains(args[0]))) {
            StringBuilder sb = new StringBuilder();
            sb.append("Erroneous open shell command [").append(args.length < 1 ? "null" : args[0]).append(']').append('\n')
                    .append("\nAvailable OpenShell commands include:").append('\n')
                    .append(' ').append(OpenCLI.SHOW_APP_NAME).append("\n          --show application").append('\n').append('\n')
                    .append(' ').append(OpenCLI.SHOW_APP_NAME).append(" [json]\n          --show application with parameters").append('\n').append('\n')
                    .append(' ').append(OpenCLI.HIDE_APP_NAME).append("\n          --hide application").append('\n').append('\n')
                    .append(' ').append(OpenCLI.VERSOPM_ATTRIBUTE).append("\n          --get application version").append('\n').append('\n')
                    .append(' ').append(OpenCLI.SHOW_APP_INFO_NAME).append("\n          --show information");
            LOGGER.log(Level.SEVERE, sb.toString());
            return;
        }

        JMXConnector jmxc = null;
        try {
            JMXServiceURL url = new JMXServiceURL(CommonConstants.APP_JMX_RMI_URL);
            jmxc = JMXConnectorFactory.connect(url, null);
            if (jmxc != null) {
                MBeanServerConnection msc = jmxc.getMBeanServerConnection();
                ObjectName beanName = new ObjectName(OPEN_SHELL_JMX_BEAN_NAME);
                if (args.length == 1) {
                    switch (args[0]) {
                        case OpenCLI.SHOW_APP_NAME:
                            msc.invoke(beanName, args[0], new Object[]{""}, new String[]{String.class.getName()});
                            LOGGER.log(Level.INFO, "OK!");
                            break;
                        case OpenCLI.HIDE_APP_NAME:
                            msc.invoke(beanName, args[0], null, null);
                            LOGGER.log(Level.INFO, "OK!");
                            break;
                        case OpenCLI.VERSOPM_ATTRIBUTE:
                            String version = (String) msc.getAttribute(beanName, VERSOPM_ATTRIBUTE);
                            StringBuilder output = new StringBuilder();
                            output.append("OK!").append("\napplication version: ").append(version);
                            LOGGER.log(Level.INFO, output.toString());
                            break;
                        case OpenCLI.SHOW_APP_INFO_NAME:
                            String info = (String) msc.invoke(beanName, args[0], null, null);
                            output = new StringBuilder();
                            output.append("OK!").append("\n").append(info);
                            LOGGER.log(Level.INFO, output.toString());
                            break;
                        default:
                            LOGGER.log(Level.WARNING, "Unsupported command {0}", args[0]);
                    }
                } else {
                    msc.invoke(beanName, args[0], new Object[]{args[1]}, new String[]{String.class.getName()});
                }
            }
        } catch (IOException | MalformedObjectNameException | InstanceNotFoundException | MBeanException | AttributeNotFoundException | ReflectionException ex) {
            LOGGER.log(Level.WARNING,
                    "fail to invoke mbean method due to : \n{0}", ex.getMessage());
        } finally {
            if (jmxc != null) {
                try {
                    jmxc.close();
                } catch (IOException ex) {
                    LOGGER.log(Level.WARNING,
                            "fail to close JMXConnector to open shell due to :\n{0}", ex.getMessage());
                }
            }
        }
    }
}
