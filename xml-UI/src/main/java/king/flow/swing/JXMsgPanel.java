/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.flow.swing;

import com.github.jsonj.JsonArray;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Iterator;
import java.util.Vector;
import java.util.logging.Level;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import king.flow.action.DefaultMsgSendAction;
import king.flow.action.DefaultTableAction;
import king.flow.action.DefaultTextFieldAction;
import static king.flow.common.CommonConstants.DEFAULT_TABLE_ROW_COUNT;
import static king.flow.common.CommonConstants.TABLE_ROW_HEIGHT;
import static king.flow.common.CommonUtil.getLogger;
import static king.flow.common.CommonUtil.getResourceMsg;

/**
 *
 * @author LiuJin
 */
public class JXMsgPanel extends JPanel {

    static final String PAGE_SUMMARY = getResourceMsg("advanced.table.text.summary");
    static final String REPLACE_X = " ";
    public static final String NEXT_ACTION_COMMAND = "next";
    public static final String PREVIOUS_ACTION_COMMAND = "previous";
    public static final String JUMP_ACTION_COMMAND = "jump";

    final JScrollPane jScrollPane;
    final JTable jTable;
    final JTextField section;
    final JButton jump;
    final JButton previous;
    final JButton next;
    final JLabel summary;
    final JPanel display;
    int totalPages = 0;

    public JXMsgPanel() {
        display = new JPanel(new FlowLayout(FlowLayout.LEADING, 0, 0));
        summary = new JLabel(PAGE_SUMMARY);
        section = new JTextField("");
        section.setPreferredSize(new Dimension(40, 40));
        display.add(new JLabel(getResourceMsg("advanced.table.text.label.head")));
        display.add(section);
        display.add(new JLabel(getResourceMsg("advanced.table.text.label.tail")));
        display.setOpaque(false);
        jump = new JButton(getResourceMsg("advanced.table.text.jump"));
        jump.setActionCommand(JUMP_ACTION_COMMAND);
        previous = new JButton(getResourceMsg("advanced.table.text.pre"));
        previous.setActionCommand(PREVIOUS_ACTION_COMMAND);
        next = new JButton(getResourceMsg("advanced.table.text.next"));
        next.setActionCommand(NEXT_ACTION_COMMAND);
        jTable = new JTable();
        jTable.getTableHeader().setResizingAllowed(false);
        jTable.getTableHeader().setReorderingAllowed(false);
        jTable.setRowHeight(TABLE_ROW_HEIGHT);
        jScrollPane = new JScrollPane();
        jScrollPane.setViewportView(jTable);
        jScrollPane.setOpaque(false);
        jScrollPane.getViewport().setOpaque(false);
        jScrollPane.setBorder(null);
        jScrollPane.setViewportBorder(null);
        this.setLayout(new BorderLayout());
        this.setOpaque(false);
        init();
    }

    void init() {
        this.add(jScrollPane, BorderLayout.CENTER);
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        controlPanel.setOpaque(false);
        this.add(controlPanel, BorderLayout.NORTH);
        controlPanel.add(summary);
        controlPanel.add(previous);
        controlPanel.add(next);
        controlPanel.add(jump);
        controlPanel.add(display);
        initialControlPanelAction();
    }

    public JTable getTable() {
        return jTable;
    }

    public JTextField getSection() {
        return section;
    }

    public void refreshTotalPages(int total) {
        summary.setText(PAGE_SUMMARY.replace(REPLACE_X, String.valueOf(total)));
        this.totalPages = total;
    }

    public void refreshCurrentPage(int page) {
        section.setText(String.valueOf(page));
    }

    public void refreshTable(JsonArray arrays) {
        DefaultTableModel model = (DefaultTableModel) jTable.getModel();
        model.setRowCount(0);
        for (Iterator it = arrays.iterator(); it.hasNext();) {
            JsonArray row = (JsonArray) it.next();
            Vector<String> rowData = new Vector<>();
            for (Object v : row) {
                rowData.add(v.toString());
            }
            model.addRow(rowData);
        }
    }

    public void cleanData() {
        DefaultTableModel model = (DefaultTableModel) jTable.getModel();
        model.setRowCount(0);
        model.setRowCount(DEFAULT_TABLE_ROW_COUNT);
        summary.setText(PAGE_SUMMARY);
        totalPages = 0;
        section.setText("");
    }

    public JButton getJump() {
        return jump;
    }

    public JButton getPrevious() {
        return previous;
    }

    public JButton getNext() {
        return next;
    }

    public int getCurrentPage() {
        return section.getText().length() != 0 ? Integer.parseInt(section.getText()) : 0;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void initialMsgSendAction(DefaultMsgSendAction... actions) {
        if (actions == null || actions.length < 3) {
            getLogger(JXMsgPanel.class.getName()).log(Level.INFO, "fail to initial advanced table actions");
            return;
        }

        actions[0].installButtonAction();
        actions[0].initializeData();

        actions[1].installButtonAction();
        actions[1].initializeData();

        actions[2].installButtonAction();
        actions[2].initializeData();
    }

    public void initialTableAction(DefaultTableAction tableAction) {
        tableAction.setOwner(jTable);
        tableAction.setupListener();
        tableAction.initializeData();
    }

    private void initialControlPanelAction() {
        DefaultTextFieldAction inputAction = new DefaultTextFieldAction(2, DefaultTextFieldAction.InputType.NUMBER);
        inputAction.setOwner(section);
        inputAction.setupListener();
        inputAction.initializeData();
    }
}
