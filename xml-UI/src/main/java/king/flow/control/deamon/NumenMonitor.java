/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.flow.control.deamon;

import java.util.logging.Level;
import king.flow.common.CommonConstants;
import static king.flow.common.CommonUtil.getLogger;

public class NumenMonitor implements CheckNumen {

    public static final String JMX_BEAN_NAME = "king.flow.control.deamon:Name=NumenMonitor,Type=CheckNumen";

    @Override
    public String getVersion() {
        return CommonConstants.VERSION;
    }

    @Override
    public void killDeamon() {
        getLogger(NumenMonitor.class.getName()).log(Level.WARNING,
                "Demon thread will end up due to receive signal of termination : BYE-BYE");
        System.exit(0);
    }

}
