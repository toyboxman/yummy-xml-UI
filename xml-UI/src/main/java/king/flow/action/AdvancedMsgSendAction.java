/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.flow.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import javax.swing.JButton;
import javax.swing.JComponent;
import king.flow.common.CommonUtil;
import king.flow.swing.JXMsgPanel;
import static king.flow.swing.JXMsgPanel.JUMP_ACTION_COMMAND;
import static king.flow.swing.JXMsgPanel.NEXT_ACTION_COMMAND;
import static king.flow.swing.JXMsgPanel.PREVIOUS_ACTION_COMMAND;
import king.flow.view.MsgSendAction;
import king.flow.view.Rules;

/**
 *
 * @author Administrator
 */
public class AdvancedMsgSendAction extends DefaultMsgSendAction {

    public final JXMsgPanel parent;

    public AdvancedMsgSendAction(String prsCode, int cmdCode, List<String> conditionList,
            MsgSendAction.NextStep next, MsgSendAction.Exception expPage,
            Rules checkRules, int id, JComponent owner, Map<Integer, Object> components,
            Map<Integer, Object> components_meta) {
        super(prsCode, cmdCode, conditionList, next, expPage, checkRules);
        parent = (JXMsgPanel) components.get(id);
        this.id = id;
        this.owner = owner;
        this.components = components;
        this.components_meta = components_meta;
    }

    @Override
    protected Map<Integer, String> retrieveConditionValues() {
        Map<Integer, String> conditionValues = super.retrieveConditionValues();
        JButton button = (JButton) this.owner;
        switch (button.getActionCommand()) {
            case JUMP_ACTION_COMMAND:
                conditionValues.put(id, String.valueOf(parent.getCurrentPage()));
                break;
            case NEXT_ACTION_COMMAND:
                conditionValues.put(id, String.valueOf((parent.getCurrentPage()) + 1));
                break;
            case PREVIOUS_ACTION_COMMAND:
                conditionValues.put(id, String.valueOf((parent.getCurrentPage()) - 1));
                break;
            default:
                CommonUtil.getLogger(AdvancedMsgSendAction.class.getName()).log(Level.WARNING,
                        "Unkonwn action command {0} of advanced table", button.getActionCommand());
        }

        return conditionValues;
    }

    @Override
    public void installButtonAction() {
        JButton button = (JButton) this.owner;
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (parent.getTotalPages() <= 0) {
                    CommonUtil.showErrorMsg(parent.getTopLevelAncestor(),
                            CommonUtil.getResourceMsg("advanced.table.init.error"));
                    return;
                }
                if (parent.getCurrentPage() == 0) {
                    CommonUtil.showErrorMsg(parent.getTopLevelAncestor(),
                            CommonUtil.getResourceMsg("advanced.table.jump.error"));
                    return;
                }
                String actionCommand = e.getActionCommand();
                switch (actionCommand) {
                    case JUMP_ACTION_COMMAND:
                        if (parent.getCurrentPage() < 1 || parent.getCurrentPage() > parent.getTotalPages()) {
                            CommonUtil.showErrorMsg(parent.getTopLevelAncestor(),
                                    CommonUtil.getResourceMsg("advanced.table.jump.warning"));
                            return;
                        }
                        break;
                    case NEXT_ACTION_COMMAND:
                        if (parent.getCurrentPage() + 1 > parent.getTotalPages()) {
                            CommonUtil.showErrorMsg(parent.getTopLevelAncestor(),
                                    CommonUtil.getResourceMsg("advanced.table.next.warning"));
                            return;
                        }
                        break;
                    case PREVIOUS_ACTION_COMMAND:
                        if (parent.getCurrentPage() - 1 < 1) {
                            CommonUtil.showErrorMsg(parent.getTopLevelAncestor(),
                                    CommonUtil.getResourceMsg("advanced.table.pre.warning"));
                            return;
                        }
                        if (parent.getCurrentPage() - 1 > parent.getTotalPages()) {
                            CommonUtil.showErrorMsg(parent.getTopLevelAncestor(),
                                    CommonUtil.getResourceMsg("advanced.table.jump.warning"));
                            return;
                        }
                        break;
                    default:
                        CommonUtil.getLogger(AdvancedMsgSendAction.class.getName()).log(Level.WARNING,
                                "Unkonwn action command {0} of advanced table", actionCommand);
                        return;
                }
                send();
            }
        });
    }

}
