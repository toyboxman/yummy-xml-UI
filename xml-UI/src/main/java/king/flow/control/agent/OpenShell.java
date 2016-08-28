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
import static java.lang.Integer.parseInt;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import king.flow.common.CommonConstants;
import static king.flow.common.CommonUtil.getCurrentView;
import static king.flow.common.CommonUtil.getLogger;

/**
 *
 * @author Administrator
 */
public class OpenShell implements OpenCLI{
    
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
    
}
