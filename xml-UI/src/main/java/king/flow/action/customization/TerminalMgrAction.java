/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.flow.action.customization;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import king.flow.action.DefaultAction;
import static king.flow.common.CommonUtil.getLogger;
import static king.flow.common.CommonUtil.showMsg;
import static king.flow.common.CommonUtil.getResourceMsg;
import king.flow.db.access.TerminalinfoJpaController;
import king.flow.db.config.DBFactory;
import king.flow.db.entity.Terminalinfo;

/**
 *
 * @author LiuJin
 */
public class TerminalMgrAction extends DefaultAction<JTable> {

    private final int refreshButtonID;
    private final int activeButtonID;
    private final int unactiveButtonID;

    public TerminalMgrAction(int refreshButtonID, int activeButtonID, int unactiveButtonID) {
        this.refreshButtonID = refreshButtonID;
        this.activeButtonID = activeButtonID;
        this.unactiveButtonID = unactiveButtonID;
    }

    @Override
    public void setupListener() {
        JButton refresh = getBlock(this.refreshButtonID, JButton.class);
        refresh.setActionCommand(REFRESH_CMD);
        refresh.addActionListener(new TerminalActionImpl());

        JButton active = getBlock(this.activeButtonID, JButton.class);
        active.setActionCommand(ACTIVE_CMD);
        active.addActionListener(new TerminalActionImpl());

        JButton unactive = getBlock(this.unactiveButtonID, JButton.class);
        unactive.setActionCommand(UNACTIVE_CMD);
        unactive.addActionListener(new TerminalActionImpl());
    }

    private static final String REFRESH_CMD = "refresh";
    private static final String UNACTIVE_CMD = "unactive";
    private static final String ACTIVE_CMD = "active";
    private static final int ACTIVE_STATE = 0;
    private static final int UNACTIVE_STATE = -99;
    private static final int TERMINAL_STATE_COLUMN = 2;
    private static final int TERMINAL_NO_COLUMN = 0;

    @Override
    public void initializeData() {
        owner.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        AdminTableCellRenderer defaultCellRenderer = new AdminTableCellRenderer();
        defaultCellRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        owner.setDefaultRenderer(Object.class, defaultCellRenderer);
        for (int c = 0; c < owner.getColumnCount(); c++) {
            Class<?> col_class = owner.getColumnClass(c);
            owner.setDefaultEditor(col_class, null);        // remove editor
        }
    }

    private class TerminalActionImpl implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String actionCommand = e.getActionCommand();
            TerminalinfoJpaController tinfo = new TerminalinfoJpaController(DBFactory.getEMFactory());
            if (actionCommand.equals(REFRESH_CMD)) {
                waitCommunicationTask(new RefreshTask(tinfo));
                return;
            }

            int selectedRow = owner.getSelectedRow();
            if (selectedRow == -1) {
                showMsg(owner.getTopLevelAncestor(), getResourceMsg("terminal.state.operation.prompt"));
                return;
            }

            String terminalID = (String) owner.getValueAt(selectedRow, TERMINAL_NO_COLUMN);
            if (terminalID == null || terminalID.length() == 0) {
                showMsg(owner.getTopLevelAncestor(), getResourceMsg("terminal.state.operation.invalidation.prompt"));
                return;
            }

            waitCommunicationTask(new OperateTask(tinfo, actionCommand));
        }
    }

    private class AdminTableCellRenderer extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                boolean hasFocus, int row, int column) {
            JLabel cellRenderer = (JLabel) super.getTableCellRendererComponent(table, value, isSelected,
                    hasFocus, row, column);
            switch (column) {
                case TERMINAL_STATE_COLUMN:
                    if (value != null) {
                        int state = Integer.parseInt(value.toString());
                        switch (state) {
                            case ACTIVE_STATE:
                                cellRenderer.setText(getResourceMsg("terminal.state.online"));
                                break;
                            case UNACTIVE_STATE:
                                cellRenderer.setText(getResourceMsg("terminal.state.offline"));
                                break;
                            default:
                                break;
                        }
                    }
                    break;
                default:
                    break;
            }
            return cellRenderer;
        }
    }

    private class RefreshTask extends SwingWorker<Boolean, Integer> {

        private final TerminalinfoJpaController tinfo;

        public RefreshTask(TerminalinfoJpaController tinfo) {
            this.tinfo = tinfo;
        }

        @Override
        protected Boolean doInBackground() throws Exception {
            Thread.sleep(1000);
            List<Terminalinfo> registry_terminals = tinfo.findTerminalinfoEntities();
            DefaultTableModel model = (DefaultTableModel) owner.getModel();
            model.setRowCount(0);
            model.setRowCount(10);
            getLogger(RefreshTask.class.getName()).log(Level.INFO,
                    "query terminal information, account is {0}", registry_terminals.size());
            for (Terminalinfo terminalinfo : registry_terminals) {
                Vector row = new Vector();
                row.add(terminalinfo.getTerminalno());
                row.add(terminalinfo.getTerminalip());
                row.add(terminalinfo.getTerminalstatus());
                row.add(terminalinfo.getTerminalcall());
                model.insertRow(0, row);
            }
            return true;
        }
    }

    private class OperateTask extends SwingWorker<Boolean, Integer> {

        private final TerminalinfoJpaController tinfo;
        private final String actionCommand;
        private final String terminalID;
        private final int selectedRow;

        public OperateTask(TerminalinfoJpaController tinfo, String actionCommand) {
            this.tinfo = tinfo;
            this.actionCommand = actionCommand;
            this.selectedRow = owner.getSelectedRow();
            this.terminalID = (String) owner.getValueAt(selectedRow, TERMINAL_NO_COLUMN);
        }

        @Override
        protected Boolean doInBackground() throws Exception {
            Thread.sleep(1000);
            try {
                Terminalinfo terminal = tinfo.findTerminalinfo(terminalID);
                switch (actionCommand) {
                    case ACTIVE_CMD:
                        terminal.setTerminalstatus(ACTIVE_STATE);
                        break;
                    case UNACTIVE_CMD:
                        terminal.setTerminalstatus(UNACTIVE_STATE);
                        break;
                    default:
                        getLogger(TerminalActionImpl.class.getName()).log(Level.INFO,
                                "Invalidated action command : {0}", actionCommand);
                        return false;
                }
                tinfo.edit(terminal);
                owner.setValueAt(terminal.getTerminalstatus(), selectedRow, TERMINAL_STATE_COLUMN);
            } catch (Exception ex) {
                getLogger(TerminalActionImpl.class.getName()).log(Level.INFO, "Encounter operation exception ", ex);
                throw ex;
            }
            return true;
        }
    }
}
