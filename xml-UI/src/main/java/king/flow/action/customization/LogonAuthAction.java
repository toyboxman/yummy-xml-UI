package king.flow.action.customization;

import java.awt.event.ActionEvent;
import java.util.Collection;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import king.flow.action.DefaultBaseAction;
import static king.flow.common.CommonUtil.getLogger;
import static king.flow.common.CommonUtil.showErrorMsg;
import static king.flow.common.CommonUtil.getResourceMsg;
import king.flow.db.access.TerminalinfoJpaController;
import king.flow.db.access.TerminalmgrJpaController;
import king.flow.db.config.DBFactory;
import king.flow.db.entity.Terminalinfo;
import king.flow.db.entity.Terminalmgr;
import king.flow.view.Component;
import king.flow.view.ComponentEnum;
import king.flow.view.Decorator;

/**
 *
 * @author LiuJin
 */
public class LogonAuthAction extends DefaultBaseAction {

    private final int userID;
    private final int passwordID;
    private final int nextID;

    public LogonAuthAction(int userID, int passwordID, int next) {
        this.userID = userID;
        this.passwordID = passwordID;
        this.nextID = next;
    }

    @Override
    public void initializeData() {
        JTextField user = getBlock(userID, JTextField.class);
        user.setDocument(new JTextFieldLimit(16));
        JPasswordField password = getBlock(passwordID, JPasswordField.class);
        password.setDocument(new JTextFieldLimit(16));
    }

    @Override
    protected void installButtonAction() {
        JButton btn = (JButton) owner;
        btn.addActionListener(new LogonActionImpl());
    }

    @Override
    protected void installTextFieldAction() {
        JTextField tf = (JTextField) owner;
        tf.addActionListener(new LogonActionImpl());
    }

    private final void clean() {
        JTextField user = getBlock(userID, JTextField.class);
        user.setText("");
        JPasswordField password = getBlock(passwordID, JPasswordField.class);
        password.setText("");
    }

    private class LogonActionImpl extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            waitCommunicationTask(new LogonWorker());
        }
    }

    private class LogonWorker extends SwingWorker<Boolean, Integer> {

        @Override
        protected Boolean doInBackground() throws Exception {
            boolean result = false;
            Thread.sleep(1000);
            JTextField user = LogonAuthAction.this.getBlock(userID, JTextField.class);
            JPasswordField password = LogonAuthAction.this.getBlock(passwordID, JPasswordField.class);
            TerminalmgrJpaController terminalmgrJpaController = null;
            Terminalmgr admin = null;
            try {
                terminalmgrJpaController = new TerminalmgrJpaController(DBFactory.getEMFactory());
                admin = terminalmgrJpaController.findTerminalmgr(user.getText());
            } catch (Throwable e) {
                getLogger(LogonWorker.class.getName()).log(Level.SEVERE, "Cannot retrieve database connection", e);
                showErrorMsg(owner.getTopLevelAncestor(),
                        getResourceMsg("terminal.management.logon.db.exception.prompt"));
                throw e;
            }

            if (admin == null) {
                getLogger(LogonAuthAction.class.getName()).log(Level.INFO,
                        "Invalidated system management user {0}", user.getText());
                showErrorMsg(owner.getTopLevelAncestor(),
                        getResourceMsg("terminal.management.logon.pwd.error.prompt"));
            } else {
                if (admin.getCredentials().equals(new String(password.getPassword()))) {
                    result = true;
                    getLogger(LogonAuthAction.class.getName()).log(Level.INFO, "User {0} logon successfully", user.getText());
                    king.flow.view.Panel pNode = (king.flow.view.Panel) getBlockMeta(nextID);
                    JTable table = null;
                    List<Decorator> decorator = pNode.getDecorator();
                    for (Decorator d : decorator) {
                        Component component = d.getComponent();
                        if (component.getType() == ComponentEnum.TABLE) {
                            table = getBlock(component.getId(), JTable.class);
                        }
                    }
                    if (table != null) {
                        getLogger(LogonAuthAction.class.getName()).log(Level.FINER, "Cannot get table to show terminal information");
                        DefaultTableModel model = (DefaultTableModel) table.getModel();
                        TerminalinfoJpaController tinfo = new TerminalinfoJpaController(DBFactory.getEMFactory());
                        List<Terminalinfo> registry_terminals = tinfo.findTerminalinfoEntities();
                        getLogger(TerminalMgrAction.class.getName()).log(Level.INFO,
                                "query terminal information, account is {0}", registry_terminals.size());
                        for (Terminalinfo terminalinfo : registry_terminals) {
                            Vector row = new Vector();
                            row.add(terminalinfo.getTerminalno());
                            row.add(terminalinfo.getTerminalip());
                            row.add(terminalinfo.getTerminalstatus());
                            row.add(terminalinfo.getTerminalcall());
                            model.insertRow(0, row);
                        }
                    }
                    panelJump(nextID);
                    Collection<Object> values = components.values();
                    for (Object v : values) {
                        if (v instanceof JMenu) {
                            ((JMenu) v).setEnabled(true);
                        }
                    }
                }
            }

            return result;
        }
    }

    private class JTextFieldLimit extends PlainDocument {

        private int limit;

        JTextFieldLimit(int limit) {
            super();
            this.limit = limit;
        }

        JTextFieldLimit(int limit, boolean upper) {
            super();
            this.limit = limit;
        }

        @Override
        public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
            if (str == null) {
                return;
            }

            if ((getLength() + str.length()) <= limit) {
                super.insertString(offset, str, attr);
            }
        }
    }
}
