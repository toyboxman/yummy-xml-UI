/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.flow.control.agent;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Window;
import static java.awt.event.KeyEvent.VK_ALT;
import static java.awt.event.KeyEvent.VK_TAB;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
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
import static king.flow.common.CommonUtil.getCurrentView;
import static king.flow.common.CommonUtil.getLogger;

/**
 *
 * @author Administrator
 */
public class OpenShell implements OpenCLI {

    public static final String OPEN_SHELL_JMX_BEAN_NAME = "king.flow.control.agent:Name=OpenShell,Type=OpenCLI";

    @Override
    public void showApp() {
        getLogger(OpenShell.class.getName()).log(Level.INFO,
                "Try to show bankApp window via OpenShell");
        Window window = getCurrentView();
        if (window != null) {
            window.setVisible(true);
            window.setAutoRequestFocus(true);
            window.setAlwaysOnTop(true);
            window.toFront();
            window.requestFocus();
            window.setAlwaysOnTop(false);
            window.repaint();
            //switchApp();
        } else {
            getLogger(OpenShell.class.getName()).log(Level.INFO,
                    "Fail to show because bankApp probably failed to start up and no window can be found");
        }
    }

    @Override
    public void hideApp() {
        getLogger(OpenShell.class.getName()).log(Level.INFO,
                "Try to hide bankApp window via OpenShell");
        Window window = getCurrentView();
        if (window != null) {
            window.setVisible(false);
        } else {
            getLogger(OpenShell.class.getName()).log(Level.INFO,
                    "Fail to hide because bankApp probably failed to start up and no window can be found");
        }
    }

    @Override
    public String getVersion() {
        return CommonConstants.VERSION;
    }

    //how to switch to visible bankApp? toFront just works fine in JVM domain not in window7
    //Robot is used to generate native system input events for the purposes of test automation,
    //self-running demos, and other applications where control of the mouse and keyboard is needed
    //http://stackoverflow.com/questions/2694365/can-i-move-another-programs-window-to-the-front-of-focus
    public void switchApp() {
        try {
            final Robot robot = createRobot();
            robot.keyPress(VK_ALT);
            for (int i = 0; i < 1; i++) {
                robot.keyPress(VK_TAB);
                robot.keyRelease(VK_TAB);
                robot.delay(500);
            }
            robot.keyRelease(VK_ALT);
        } catch (AWTException ex) {
            Logger.getLogger(OpenShell.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Robot createRobot() throws AWTException {
        final Robot robot = new Robot();
        robot.setAutoWaitForIdle(true);
        robot.setAutoDelay(10);
        return robot;
    }

    public static void main(String[] args) {
        try {
            LogManager.getLogManager().readConfiguration(OpenShell.class.getResourceAsStream("/openshell.properties"));
        } catch (IOException | SecurityException ex) {
            getLogger(OpenShell.class.getName()).log(Level.WARNING,
                    "cannot find openshell.properties due to :\n{0}", ex.getMessage());
        } finally {
            getLogger(OpenShell.class.getName()).log(Level.INFO, "\n{0}", CommonUtil.showSystemInfo());
        }

        if (!(args.length > 1 && COMMAND_SET.contains(args[0]))) {
            StringBuilder sb = new StringBuilder();
            sb.append("\nErroneous open shell command [").append(args.length < 1 ? "null" : args[0]).append(']').append('\n')
                    .append("Available OpenShell commands include:").append('\n')
                    .append(OpenCLI.SHOW_APP_NAME).append("       --show application").append('\n')
                    .append(OpenCLI.HIDE_APP_NAME).append("       --hide application");
            getLogger(OpenShell.class.getName()).log(Level.SEVERE, sb.toString());
            return;
        }

        JMXConnector jmxc = null;
        try {
            JMXServiceURL url = new JMXServiceURL(CommonConstants.APP_JMX_RMI_URL);
            jmxc = JMXConnectorFactory.connect(url, null);
            if (jmxc != null) {
                MBeanServerConnection msc = jmxc.getMBeanServerConnection();
                ObjectName beanName = new ObjectName(OPEN_SHELL_JMX_BEAN_NAME);
                msc.invoke(beanName, args[0], null, null);
            }
        } catch (IOException | MalformedObjectNameException | InstanceNotFoundException | MBeanException | ReflectionException ex) {
            getLogger(OpenShell.class.getName()).log(Level.WARNING,
                    "fail to invoke mbean method due to : \n{0}", ex.getMessage());
        } finally {
            if (jmxc != null) {
                try {
                    jmxc.close();
                } catch (IOException ex) {
                    getLogger(OpenShell.class.getName()).log(Level.WARNING,
                            "fail to close JMXConnector to open shell due to :\n{0}", ex.getMessage());
                }
            }
        }
    }
}
