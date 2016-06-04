/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.flow.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import static king.flow.common.CommonConstants.DEFAULT_TABLE_ROW_COUNT;
import king.flow.swing.JXMsgPanel;
import king.flow.view.Component;
import org.jdesktop.swingx.JXDatePicker;

/**
 *
 * @author LiuJin
 */
public class DefaultCleanAction extends DefaultBaseAction {

    private final List<String> targets;

    public DefaultCleanAction(List<String> targets) {
        this.targets = targets;
    }

    @Override
    protected void installButtonAction() {
        JButton button = (JButton) this.owner;
        button.addActionListener(new CleanActionImpl());
    }

    private class CleanActionImpl implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            for (String tid : targets) {
                final int id = Integer.parseInt(tid);
                Object metaNode = getBlockMeta(id);
                if (metaNode instanceof Component) {
                    switch (((Component) metaNode).getType()) {
                        case TEXT_FIELD:
                            JTextField jtf = getBlock(id, JTextField.class);
                            jtf.setText("");
                            break;
                        case PASSWORD_FIELD:
                            JPasswordField jpf = getBlock(id, JPasswordField.class);
                            jpf.setText("");
                            break;
                        case DATE:
                            JXDatePicker date = getBlock(id, JXDatePicker.class);
                            date.setDate(Calendar.getInstance().getTime());
                            break;
                        case TABLE:
                            JTable table = getBlock(id, JTable.class);
                            ((DefaultTableModel) table.getModel()).setRowCount(0);
                            ((DefaultTableModel) table.getModel()).setRowCount(DEFAULT_TABLE_ROW_COUNT);
                            break;
                        case ADVANCED_TABLE:
                            JXMsgPanel advancedTable = getBlock(id, JXMsgPanel.class);
                            advancedTable.cleanData();
                            break;
                        case COMBO_BOX:
                            JComboBox combo = getBlock(id, JComboBox.class);
//                            if (combo.isEditable()) {
//                                combo.setSelectedIndex(0);
//                            }
                            combo.setSelectedIndex(0);
                            break;
                        case LABEL:
                            JLabel label = getBlock(id, JLabel.class);
                            label.setText("");
                            break;
                        default:
                            Logger.getLogger(DefaultCleanAction.CleanActionImpl.class.getName()).log(Level.INFO,
                                    "Clean invalid component[{0}] type[{1}]",
                                    new Object[]{String.valueOf(((Component) metaNode).getId()), ((Component) metaNode).getType()});
                    }
                } else {
                    Logger.getLogger(DefaultCleanAction.CleanActionImpl.class.getName()).log(Level.INFO,
                            "Invalid component type[{0}] for clean operation", metaNode.getClass().getName());
                }

            }
        }
    }

}
