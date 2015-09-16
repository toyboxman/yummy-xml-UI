/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.flow.action;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import static king.flow.common.CommonConstants.TEXT_TYPE_TOOL_CONFIG;
import king.flow.common.CommonUtil;
import static king.flow.common.CommonUtil.getLogger;
import static king.flow.common.CommonUtil.setTypeMethodActiveCmd;
import static king.flow.common.CommonUtil.setTypeMethodUnactiveCmd;

/**
 *
 * @author LiuJin
 */
public class DefaultVirtualKeyBoardAction extends DefaultBaseAction {

    private static final String DEFAULT_TEXT_TYPE_TOOL_FOLDER = "../AVF/";
    private final String start_cmd;
    private final String stop_cmd;
    private volatile boolean hide = true;

    public DefaultVirtualKeyBoardAction(String start_cmd, String stop_cmd) {
        String textInputConfig = System.getProperty(TEXT_TYPE_TOOL_CONFIG, DEFAULT_TEXT_TYPE_TOOL_FOLDER);
        try {
            textInputConfig = new File(textInputConfig).getCanonicalPath();
        } catch (IOException ex) {
            Logger.getLogger(DefaultVirtualKeyBoardAction.class.getName()).log(Level.SEVERE, "Invalid chinese text type config {0}", ex.getMessage());
        }
        if (textInputConfig.endsWith("/") || textInputConfig.endsWith("\\")) {
            Logger.getLogger(DefaultVirtualKeyBoardAction.class.getName()).log(Level.INFO, "Standard chinese text type config {0}", textInputConfig);
        } else {
            textInputConfig = textInputConfig + File.separatorChar;
        }
        System.getProperties().put(TEXT_TYPE_TOOL_CONFIG, textInputConfig);
        this.start_cmd = textInputConfig + start_cmd;
        this.stop_cmd = textInputConfig + stop_cmd;
        setTypeMethodActiveCmd(this.start_cmd);
        setTypeMethodUnactiveCmd(this.stop_cmd);
    }

    @Override
    protected void installButtonAction() {
        JButton btn = (JButton) owner;
        btn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (!checkStartCmd()) {
                    return;
                }

                if (!checkStopCmd()) {
                    return;
                }

                new VirtualKeyBoardTask().execute();
            }
        });
    }

    private boolean checkStopCmd() throws HeadlessException {
        if (stop_cmd == null || stop_cmd.length() == 0 || !isValidCommand(stop_cmd)) {
            CommonUtil.showMsg(owner.getTopLevelAncestor(),
                    CommonUtil.getResourceMsg("keyboard.close.fail.prompt") + stop_cmd);
            // set textfiled owner loop to get focus and show JOptionPane message dialog
            owner.setFocusable(false);
            owner.setFocusable(true);
            return false;
        }
        return true;
    }

    private boolean checkStartCmd() throws HeadlessException {
        if (start_cmd == null || start_cmd.length() == 0 || !isValidCommand(start_cmd)) {
            CommonUtil.showMsg(owner.getTopLevelAncestor(),
                    CommonUtil.getResourceMsg("keyboard.open.fail.prompt") + start_cmd);
            owner.setFocusable(false);
            owner.setFocusable(true);
            return false;
        }
        return true;
    }

    private boolean isValidCommand(String cmd) {
        return new File(cmd).exists();
    }

    @Override
    protected void installTextFieldAction() {
        JTextField jtf = (JTextField) owner;
        jtf.addFocusListener(new FocusAdapter() {

//            @Override
//            public void focusGained(FocusEvent e) {
//                if (!checkStartCmd()) {
//                    return;
//                }
//                new VirtualKeyBoardTask().execute();
//            }
            @Override
            public void focusLost(FocusEvent e) {
                if (!checkStopCmd()) {
                    return;
                }
                new VirtualKeyBoardTask().execute();
            }
        });

        jtf.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                if (!checkStartCmd()) {
                    return;
                }
                new VirtualKeyBoardTask().execute();
            }
        });
    }

    private class VirtualKeyBoardTask extends SwingWorker<Integer, Object> {

        @Override
        protected Integer doInBackground() throws Exception {
//            String sysroot = System.getenv("SystemRoot");
//            Process proc = Runtime.getRuntime().exec("cmd /c " + sysroot + "/system32/osk.exe");
//            Thread.sleep(1000 * 1);
//            proc.destroy();
            if (hide) {
                Process proc = Runtime.getRuntime().exec(start_cmd);
                hide = !hide;
                Thread.sleep(1000 * 1);
                proc.destroy();
            } else {
                Process proc = Runtime.getRuntime().exec(stop_cmd);
                hide = !hide;
                Thread.sleep(1000 * 1);
                proc.destroy();
            }

            return 0;
        }

        @Override
        protected void done() {
            try {
                get();
            } catch (InterruptedException | ExecutionException e) {
                getLogger(VirtualKeyBoardTask.class.getName()).log(Level.WARNING,
                        "Fail to operate virtual keyboard ", e);
            }
        }

    }

}
