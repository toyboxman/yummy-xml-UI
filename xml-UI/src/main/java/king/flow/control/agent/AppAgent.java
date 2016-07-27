/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.flow.control.agent;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
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
            jDialog.setLayout(new BorderLayout());
            final JLabel jLabel = new JLabel("Succeed in starting bank agent");
            jLabel.setHorizontalAlignment(SwingConstants.CENTER);
            jLabel.setFont(new Font(Font.DIALOG, Font.BOLD, 21));
            jLabel.setPreferredSize(new Dimension(400, 90));
            jDialog.add(jLabel, BorderLayout.CENTER);
            jDialog.setLocationRelativeTo(null);
            jDialog.pack();
            jDialog.setVisible(true);
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    jDialog.setVisible(false);
                }
            }, TimeUnit.SECONDS.toMillis(5));
        });
    }
}
