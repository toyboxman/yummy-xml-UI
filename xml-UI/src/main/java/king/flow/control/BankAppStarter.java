package king.flow.control;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Point;
import java.awt.Rectangle;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.rmi.registry.LocateRegistry;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanException;
import javax.management.MBeanServer;
import javax.management.MBeanServerConnection;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.ReflectionException;
import javax.management.remote.JMXConnector;
import javax.management.remote.JMXConnectorFactory;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.FontUIResource;
import javax.xml.bind.JAXBException;
import king.flow.common.CommonConstants;
import static king.flow.common.CommonConstants.TEXT_TYPE_TOOL_CONFIG;
import king.flow.common.CommonUtil;
import static king.flow.common.CommonUtil.AppType.EXHIBITION;
import static king.flow.common.CommonUtil.AppType.MANAGEMENT;
import static king.flow.common.CommonUtil.AppType.TERMINAL;
import king.flow.common.CommonUtil.DownloadCipherKey;
import static king.flow.common.CommonUtil.TerminalStatus.RESTART;
import static king.flow.common.CommonUtil.TerminalStatus.RUNNING;
import static king.flow.common.CommonUtil.TerminalStatus.STARTUP;
import static king.flow.common.CommonUtil.createFont;
import static king.flow.common.CommonUtil.saveDriverConf;
import static king.flow.common.CommonUtil.getLogger;
import static king.flow.common.CommonUtil.getImageIcon;
import static king.flow.common.CommonUtil.getResourceMsg;
import static king.flow.common.CommonUtil.getTerminalStatus;
import static king.flow.common.CommonUtil.registryAppType;
import static king.flow.common.CommonUtil.resetStartTimeMillis;
import static king.flow.common.CommonUtil.setKeyboardStatus;
import static king.flow.common.CommonUtil.setTerminalStatus;
import king.flow.control.deamon.NumenMonitor;
import king.flow.design.FlowProcessor;
import king.flow.net.TunnelBuilder;
import king.flow.view.BasicAttribute;
import king.flow.view.Driver;
import king.flow.view.Panel;
import king.flow.view.UiStyle;
import king.flow.view.Window;

/**
 *
 * @author LiuJin
 */
public class BankAppStarter {

    private MainWindow frame = null;
    private Window winNode = null;
    private JDialog splash;
    private boolean showTip = false;

    public void start() {
        resetStartTimeMillis();
        if (getTerminalStatus() != RESTART) {
            setTerminalStatus(STARTUP);
        }

        try {
            MBeanServer mbeanServer = ManagementFactory.getPlatformMBeanServer();
            //about url referring to http://stackoverflow.com/questions/2768087/explain-jmx-url
            //protocol:rmi, host:localhot, port:random, url:/jndi/rmi://localhost:1099/jmxrmi
            LocateRegistry.createRegistry(CommonConstants.APP_JMX_RMI_PORT);
            JMXServiceURL serviceURL = new JMXServiceURL(CommonConstants.APP_JMX_RMI_URL);
            JMXConnectorServer jcs = JMXConnectorServerFactory.newJMXConnectorServer(serviceURL, null, mbeanServer);
            jcs.start();
        } catch (IOException e) {
            Logger.getLogger(BankAppStarter.class.getName()).log(Level.SEVERE,
                    "fail to initiative JMXConnectorServer with URL[{0}] due to :\n{1}",
                    new Object[]{CommonConstants.APP_JMX_RMI_URL, e.getMessage()});
        }

        String logConf = System.getProperty(LOG_CONF);
        if (logConf == null) {
            System.setProperty(LOG_CONF, DEFAULT_LOG_CONF);
        }
        String property = System.getProperty(UI_BUILD_SCHEME, DEFAULT_WINDOW_XML);
        switch (property) {
            case DEFAULT_WINDOW_XML:
                registryAppType(TERMINAL);
                break;
            case DEFAULT_MANAGER_XML:
                registryAppType(MANAGEMENT);
                break;
            case DEFAULT_EXHIBITION_XML:
                registryAppType(EXHIBITION);
                break;
            default:
                getLogger(BankAppStarter.class.getName()).log(Level.WARNING, "Unknown application type : {0}", property);
        }

        FlowProcessor parser = new FlowProcessor(property);
        try {
            winNode = parser.parse(Window.class);
            Boolean enableHeartbeat = winNode.isHeartbeat();
            // default action is running a new heartbeat thread
            // if this is restart process, there is no need to start new heart beat thread. because there is one hb in running
            if ((enableHeartbeat == null || enableHeartbeat) && getTerminalStatus() != RESTART) {
                TunnelBuilder.getTunnelBuilder().enableHeartBeat();
            }
            Driver driver = winNode.getDriver();
            if (driver != null) {
                List<Driver.Device> device = driver.getDevice();
                for (Driver.Device d : device) {
                    saveDriverConf(d.getType(), new String[]{d.getDll(), d.getPort()});
                }
            }
        } catch (JAXBException ex) {
            getLogger(BankAppStarter.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(1);
        }

        setLookAndFeel();

        splash = new JDialog();
        splash.setUndecorated(true);
        splash.getRootPane().setOpaque(false);
        final Color transparentColor = new Color(255, 255, 255, 0);
        splash.getContentPane().setBackground(transparentColor);
        splash.setBackground(transparentColor);
        final JLabel icon = new JLabel(new ImageIcon(
                getClass().getResource(DEFAULT_BANK_APP_ICON)));
        splash.getContentPane().add(icon, BorderLayout.CENTER);
        splash.setModal(true);
        Point centerPoint = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
        Dimension dim = new Dimension(256, 256);
        int lx = (int) (centerPoint.getX() - dim.getWidth() / 2);
        int ly = (int) (centerPoint.getY() - dim.getHeight() / 2);
        final Rectangle progressBounds = new Rectangle(lx, ly, (int) dim.getWidth(), (int) dim.getHeight());
        splash.setBounds(progressBounds);
        splash.setPreferredSize(new Dimension(256, 256));
        splash.pack();
        new DownloadKeyTask().execute();
        splash.setVisible(true);

        if (showTip) {
            showMsgDialog();
            setKeyboardStatus(false);
        }

        frame = new MainWindow(winNode);
        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                System.exit(0);
            }
        });

        frame.setVisible(true);
        setTerminalStatus(RUNNING);

        //check if deamon is alive and version is matched
        String methodName = "getVersion";
        JMXConnector jmxc = null;
        try {
            JMXServiceURL url = new JMXServiceURL(CommonConstants.WATCHDOG_JMX_RMI_URL);
            jmxc = JMXConnectorFactory.connect(url, null);
            MBeanServerConnection msc = jmxc.getMBeanServerConnection();
            final ObjectName objectName = new ObjectName(NumenMonitor.JMX_BEAN_NAME);
            String watchdogVer = (String) msc.invoke(objectName, methodName, null, null);
            if (!CommonConstants.VERSION.equals(watchdogVer)) {
                methodName = "killDeamon";
                msc.invoke(objectName, methodName, null, null);
                //start watchdog
                Runtime.getRuntime().exec("./jre/bin/javaw.exe");
            }
        } catch (IOException ex) {
            Logger.getLogger(BankAppStarter.class.getName()).log(Level.WARNING,
                    "fail to ping watchdog due to :\n{0}", ex.getMessage());
            //start watchdog

        } catch (MalformedObjectNameException | InstanceNotFoundException | MBeanException | ReflectionException ex) {
            Logger.getLogger(BankAppStarter.class.getName()).log(Level.WARNING,
                    "fail to invoke method {0} due to : \n{1}",
                    new Object[]{methodName, ex.getMessage()});
        } finally {
            if (jmxc != null) {
                try {
                    jmxc.close();
                } catch (IOException ex) {
                    Logger.getLogger(BankAppStarter.class.getName()).log(Level.WARNING,
                            "Fail to close JMXConnector to watchdog due to :\n{0}", ex.getMessage());
                }
            }
        }

        String textTypeConfig = System.getProperty(TEXT_TYPE_TOOL_CONFIG);
        try {
            Runtime.getRuntime().exec(textTypeConfig + "AVF.exe");
            Thread.sleep(2000);
            Runtime.getRuntime().exec(CommonUtil.getTypeMethodUnactiveCmd());
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(BankAppStarter.class.getName()).log(Level.WARNING,
                    "fail to initiative chinese text type tool due to {0}", ex.getMessage());
        }
    }

    private void showMsgDialog() throws HeadlessException {
        ImageIcon imageIcon = null;
        BasicAttribute attribute = winNode.getAttribute();
        if (attribute != null) {
            String icon = attribute.getIcon();
            if (icon != null) {
                imageIcon = getImageIcon(icon);
            } else {
                imageIcon = getImageIcon(DEFAULT_BANK_APP_ICON);
            }
        }

        JDialog msgDialog = new JOptionPane(getResourceMsg("bank.app.start.error.prompt"),
                JOptionPane.OK_OPTION).createDialog(getResourceMsg("bank.app.info.dialog.title"));
        if (imageIcon != null) {
            msgDialog.setIconImage(imageIcon.getImage());
        }
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                msgDialog.setVisible(false);
                msgDialog.dispose();
            }
        }, TimeUnit.SECONDS.toMillis(6));
        msgDialog.setVisible(true);
    }

    public Window getWinNode() {
        return winNode;
    }

    public Object retrieveComponentMeta(int componentID) {
        if (this.frame == null) {
            return null;
        }
        return this.frame.getBlockMeta(componentID);
    }

    public <T> T retrieveComponent(int componentID, Class<T> c) {
        if (this.frame == null) {
            return null;
        }
        return (T) this.frame.getBuildingBlock(componentID);
    }

    public Set<Map.Entry<Panel, String>> retrievePages() {
        if (this.frame == null) {
            return null;
        }
        return frame.getPanelList();
    }

    private void setLookAndFeel() {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see 
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         * http://docs.oracle.com/javase/tutorial/uiswing/lookandfeel/color.html
         * http://docs.oracle.com/javase/tutorial/uiswing/lookandfeel/_nimbusDefaults.html#primary
         * http://docs.oracle.com/javase/tutorial/uiswing/lookandfeel/size.html
         * http://nadeausoftware.com/articles/2008/11/all_ui_defaults_names_common_java_look_and_feels_windows_mac_os_x_and_linux
         * http://www-igm.univ-mlv.fr/~dr/XPOSE2005/swinglabs/swingX.php
         * http://developerlife.com/tutorials/?p=140
         */
        UiStyle style = winNode.getUiStyle();
        String lookandfeel = style.getLookandfeel();
        try {
            if (lookandfeel != null && lookandfeel.length() > 0) {
                //UIManager.setLookAndFeel("com.jgoodies.looks.windows.WindowsLookAndFeel");
                //UIManager.setLookAndFeel("com.jgoodies.looks.plastic.PlasticLookAndFeel");
                //UIManager.setLookAndFeel("com.jgoodies.looks.plastic.Plastic3DLookAndFeel");
                UIManager.setLookAndFeel(lookandfeel);
            } else {
                for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
//                        UIManager.setLookAndFeel(info.getClassName());
                        UIManager.setLookAndFeel("king.flow.lookandfeel.BankLookAndFeel");
                        customizeUI(style);
                        break;
                    }
                }

            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void customizeUI(UiStyle style) throws NumberFormatException {
        //UIManager.setLookAndFeel("king.flow.lookandfeel.BankLookAndFeel");
        /*
         * blue style
         */
        UIManager.put("nimbusBase", new Color(115, 164, 209));
        UIManager.put("nimbusBlueGrey", new Color(115, 164, 209));
//        UIManager.put("control", new Color(115, 164, 209));
        UIManager.put("control", new Color(145, 190, 220));
        UIManager.put("TextField[Disabled].textForeground", new Color(82, 82, 82));
        /*
         * orange style
         */
//                UIManager.put("nimbusBase", new Color(191, 98, 4));
//                UIManager.put("nimbusBlueGrey", new Color(191, 98, 4));
//                UIManager.put("control", new Color(191, 98, 4));
        /*
         * red style
         */
//                UIManager.put("nimbusBase", new Color(115, 164, 209));
//                UIManager.put("nimbusBlueGrey", new Color(169, 46, 34));
//                UIManager.put("control", new Color(115, 164, 209));

        if (style.getColor() != null) {
            Color color = CommonUtil.getTrueColor(style.getColor());
            if (color == null) {
                StringBuilder errMsg = new StringBuilder("ui-style property is mistakenly configurated in xml_window.xml")
                        .append('\n').append("root cause from erroneous color value[").append(style.getColor()).append(']')
                        .append('\n').append("correct value should be like [255,255,0]");
                CommonUtil.showBlockedErrorMsg(null, errMsg.toString(), true);
            }
            UIManager.put("nimbusBlueGrey", color);
        }

        if (style.getTextcolor() != null) {
            Color color = CommonUtil.getTrueColor(style.getTextcolor());
            if (color == null) {
                StringBuilder errMsg = new StringBuilder("ui-style property is mistakenly configurated in xml_window.xml")
                        .append('\n').append("root cause from erroneous textcolor value[").append(style.getTextcolor()).append(']')
                        .append('\n').append("correct value should be like [255,255,0]");
                CommonUtil.showBlockedErrorMsg(null, errMsg.toString(), true);
            }
            UIManager.put("text", color);
        }

        UIManager.put("Table.showGrid", true);

        if (style.getFont() != null) {
            java.awt.Font messageFont = UIManager.getFont("OptionPane.messageFont");
            if (messageFont == null) {
                messageFont = (java.awt.Font) UIManager.getFont("OptionPane.font");
            }
            int oldFontSize = style.getFont().getSize();
            int newFontSize = oldFontSize + 9;
            style.getFont().setSize(newFontSize);
            java.awt.Font newFont = createFont(style.getFont(), messageFont);
            FontUIResource fontUIResource = new FontUIResource(newFont);
            UIManager.put("OptionPane.messageFont", fontUIResource);
            style.getFont().setSize(oldFontSize);
        } else {
            UIManager.put("OptionPane.messageFont", new FontUIResource("Dialog", java.awt.Font.BOLD, 25));
        }
        UIManager.put("OptionPane.buttonMinimumWidth", 95);
        UIManager.put("OptionPane.buttonOrientation", SwingConstants.CENTER);

        if (style.getProgress() != null) {
            UIManager.put(CommonConstants.KING_FLOW_PROGRESS, getImageIcon(style.getProgress()));
        }
        //UIManager.getLookAndFeel().getDefaults().put("ComboBox:\"ComboBox.arrowButton\".size", new Integer(36));
        //UIManager.put("ComboBox:\"ComboBox.arrowButton\".size", 35);
//        UIManager.put("ComboBox.squareButton", Boolean.TRUE);
    }

    private class DownloadKeyTask extends DownloadCipherKey {

        @Override
        protected void done() {
            try {
                showTip = !get();
                Logger.getLogger(BankAppStarter.class.getName()).log(Level.INFO,
                        "get download key result : {0}", get());
            } catch (NullPointerException | InterruptedException | ExecutionException ex) {
                Logger.getLogger(BankAppStarter.class.getName()).log(Level.SEVERE,
                        "fail to download cipher key due to {0}", ex.getMessage());
                showTip = true;
            } finally {
                splash.setVisible(false);
                splash.dispose();
            }
        }

    }

    private static final String DEFAULT_LOG_CONF = "./conf/logging.properties";
    private static final String LOG_CONF = "java.util.logging.config.file";

    public static final String DEFAULT_WINDOW_XML = "./conf/xml_window.xml";
    public static final String DEFAULT_MANAGER_XML = "./conf-auth/xml_logon.xml";
    public static final String DEFAULT_EXHIBITION_XML = "./conf-demo/xml_demo.xml";
    static final String DEFAULT_BANK_APP_ICON = "/image/bank-icon-256.png";

    public static final String UI_BUILD_SCHEME = "ui.build.scheme";

    public static void main(String args[]) {
        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new BankAppStarter().start();
            }
        });
    }

}
