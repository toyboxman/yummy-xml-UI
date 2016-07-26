/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.flow.control.agent;

import javax.swing.JDialog;
import static king.flow.control.BankAppStarter.DEFAULT_LOG_CONF;
import static king.flow.control.BankAppStarter.LOG_CONF;
import king.flow.net.TunnelBuilder;

/**
 *
 * @author liujin
 */
public class AppAgent {

    public static void main(String[] args) {
        String logConf = System.getProperty(LOG_CONF);
        if (logConf == null) {
            System.setProperty(LOG_CONF, DEFAULT_LOG_CONF);
        }
        TunnelBuilder.getTunnelBuilder();
        TunnelBuilder.getTunnelBuilder().enableHeartBeat();
        java.awt.EventQueue.invokeLater(() -> {
            JDialog jDialog = new JDialog();
            jDialog.setVisible(true);
            jDialog.setVisible(false);
        });
    }
}
