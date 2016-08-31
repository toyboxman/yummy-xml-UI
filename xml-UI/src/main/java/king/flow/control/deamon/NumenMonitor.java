/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.flow.control.deamon;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import king.flow.common.CommonConstants;
import static king.flow.common.CommonUtil.getLogger;

public class NumenMonitor implements CheckNumen {

    public static final String NUMEN_MONITOR_JMX_BEAN_NAME = "king.flow.control.deamon:Name=NumenMonitor,Type=CheckNumen";

    @Override
    public String getVersion() {
        getLogger(NumenMonitor.class.getName()).log(Level.INFO,
                "Attribute of Numen service version is : {0}", CommonConstants.VERSION);
        return CommonConstants.VERSION;
    }

    @Override
    public void killDeamon() {
        getLogger(NumenMonitor.class.getName()).log(Level.WARNING,
                "Deamon thread will end up due to receiving signal of termination : BYE-BYE");
        new Thread(() -> {
            try {
                Thread.sleep(TimeUnit.SECONDS.toMillis(3));
            } catch (InterruptedException ex) {
                Logger.getLogger(NumenMonitor.class.getName()).log(Level.WARNING,
                        "exit numen service immediately as hitting error :\n{0}", ex.getMessage());
            } finally {
                System.exit(0);
            }
        }).start();
    }

}
