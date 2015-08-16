/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.flow.action;

import com.github.jsonj.JsonArray;
import com.github.jsonj.JsonObject;
import com.github.jsonj.exceptions.JsonParseException;
import com.github.jsonj.tools.JsonParser;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import static king.flow.common.CommonConstants.ADVANCED_TABLE_CURRENT_PAGE;
import static king.flow.common.CommonConstants.ADVANCED_TABLE_TOTAL_PAGES;
import static king.flow.common.CommonConstants.ADVANCED_TABLE_VALUE;
import king.flow.common.CommonUtil;
import king.flow.view.Component;
import king.flow.view.ComponentEnum;
import org.jdesktop.swingx.JXDatePicker;
import static king.flow.common.CommonUtil.getLogger;
import static king.flow.common.CommonUtil.createTLSMessage;
import static king.flow.common.CommonUtil.parseTLSMessage;
import static king.flow.common.CommonUtil.getResourceMsg;
import static king.flow.common.CommonUtil.sendMessage;
import king.flow.data.TLSResult;
import king.flow.swing.JXMsgPanel;
import king.flow.view.MsgSendAction;
import king.flow.view.Rules;

/**
 *
 * @author LiuJin
 */
public class DefaultMsgSendAction extends DefaultBaseAction {

    protected final String prsCode;
    protected final int cmdCode;
    protected final List<Condition> conditions = new ArrayList<>();
    protected final List<String> conditionList;
    protected final MsgSendAction.NextStep next;
    protected final MsgSendAction.Exception error;
    protected Map<Integer, String> notNullMsg = null;

    public DefaultMsgSendAction(String prsCode, int cmdCode, List<String> conditionList, MsgSendAction.NextStep next,
            MsgSendAction.Exception expPage, Rules checkRules) {
        this.prsCode = prsCode;
        this.cmdCode = cmdCode;
        this.conditionList = conditionList;
        this.next = next;
        this.error = expPage;
        this.rules = checkRules;
    }

    @Override
    public void initializeData() {
        for (String c : conditionList) {
            final int id = Integer.parseInt(c);
            Object blockMeta = getBlockMeta(id);
            if (blockMeta instanceof king.flow.view.Component) {
                king.flow.view.Component cm = (king.flow.view.Component) blockMeta;
                ComponentEnum cType = cm.getType();
                switch (cType) {
                    case TEXT_FIELD:
                    case DATE:
                    case COMBO_BOX:
                    case PASSWORD_FIELD:
                        conditions.add(new Condition<>(getBlock(id, JComponent.class), cm));
                        break;
                    default:
                        getLogger(DefaultMsgSendAction.class.getName()).log(Level.INFO,
                                "Ignore useless component is : {0}", cType.value());
                        break;
                }
            }
        }

        notNullMsg = initialNotNullTips();
    }

    @Override
    public void installButtonAction() {
        JButton button = (JButton) this.owner;
        button.addActionListener(new ButtonSendAction());
        button.addKeyListener(new EnterKeySendAction());
    }

    protected Map<Integer, String> retrieveConditionValues() {
        HashMap<Integer, String> contents = new HashMap<>();
        for (Condition<JComponent, Component> condition : conditions) {
            String value = null;
            int id = condition.getMeta().getId();
            switch (condition.getMeta().getType()) {
                case TEXT_FIELD:
                    JTextField jtf = (JTextField) condition.getComponent();
                    value = jtf.getText();
                    contents.put(id, value);
                    break;
                case COMBO_BOX:
                    JComboBox jcb = (JComboBox) condition.getComponent();
                    if (jcb.isEditable()) {
                        value = jcb.getEditor().getItem().toString();
                    } else {
                        value = jcb.getSelectedItem() == null ? null : jcb.getSelectedItem().toString();
                    }
                    contents.put(id, value);
                    break;
                case DATE:
                    JXDatePicker jxdp = (JXDatePicker) condition.getComponent();
                    value = CommonUtil.toStringDate(jxdp.getDate());
                    contents.put(id, value);
                    break;
                case PASSWORD_FIELD:
                    JPasswordField jpf = (JPasswordField) condition.getComponent();
                    final String unwrapped = new String(jpf.getPassword());
                    if (unwrapped.length() > 0) {
                        try {
                            value = CommonUtil.inputString(unwrapped);
                        } catch (Throwable e) {
                            getLogger(DefaultMsgSendAction.class.getName()).log(Level.INFO, "keyboard out of work", e);
                            value = unwrapped;
                        }
                    } else {
                        value = unwrapped;
                    }
                    contents.put(id, value);
                    break;
                default:
                    getLogger(DefaultMsgSendAction.class.getName()).log(Level.INFO,
                            "Ignore useless component is : {0}", condition.getMeta().getType().value());
                    break;
            }
        }
        return contents;
    }

    public void showResult(TLSResult result) throws HeadlessException {
        CommonUtil.showMsg(owner.getTopLevelAncestor(), result.getOkMsg());
    }

    private void showOnComponent(Object metaNode, TLSResult result) {
        Component meta = (Component) metaNode;
        Logger.getLogger(CommunicationWorker.class.getName()).log(Level.INFO,
                "display component type : {0}", meta.getType().value());
        switch (meta.getType()) {
            case TABLE:
                JsonParser jsonParser = new JsonParser();
                JsonArray arrays = jsonParser.parse(result.getOkMsg()).asArray();
                JTable table = getBlock(next.getDisplay(), JTable.class);
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.setRowCount(0);
                for (Iterator it = arrays.iterator(); it.hasNext();) {
                    JsonArray row = (JsonArray) it.next();
                    Vector<String> rowData = new Vector<>();
                    for (Object v : row) {
                        rowData.add(v.toString());
                    }
                    model.addRow(rowData);
                }
                break;
            case ADVANCED_TABLE:
                jsonParser = new JsonParser();
                JsonObject jsonObj = jsonParser.parse(result.getOkMsg()).asObject();
                Integer total = jsonObj.getInt(ADVANCED_TABLE_TOTAL_PAGES);
                Integer page = jsonObj.getInt(ADVANCED_TABLE_CURRENT_PAGE);
                arrays = jsonObj.getArray(ADVANCED_TABLE_VALUE);
                JXMsgPanel advanceTable = getBlock(next.getDisplay(), JXMsgPanel.class);
                advanceTable.refreshTotalPages(total);
                advanceTable.refreshCurrentPage(page);
                advanceTable.refreshTable(arrays);
                break;
            case LABEL:
                if (result.getRetCode() == 0) {
                    JLabel label = getBlock(next.getDisplay(), JLabel.class);
                    label.setText(result.getOkMsg());
                } else {
                    JLabel label = getBlock(error.getDisplay(), JLabel.class);
                    label.setText(result.getErrMsg());
                }
                break;
            default:
                getLogger(CommunicationWorker.class.getName()).log(Level.WARNING,
                        "Unsupported component type : {0}", meta.getType());
        }
    }

    protected void showErrMsg(final int retCode, final String msg) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                TLSResult result = new TLSResult();
                result.setRetCode(retCode);
                result.setErrMsg(msg);
                showOnComponent(getBlockMeta(error.getDisplay()), result);
                panelJump(error.getNextPanel());
            }
        });
    }

    protected void showDoneMsg(final TLSResult result) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                showOnComponent(getBlockMeta(next.getDisplay()), result);
                CommonUtil.cachePrintMsg(result.getPrtMsg());
                panelJump(next.getNextPanel());
            }
        });
    }

    private Map<Integer, String> initialNotNullTips() {
        HashMap<Integer, String> notNullTips = new HashMap<>();
        if (rules != null) {
            List<Rules.NotNull> notNull = rules.getNotNull();
            for (Rules.NotNull n : notNull) {
                int content = n.getContent();
                String errMsg = n.getErrMsg();
                if (errMsg != null && errMsg.length() > 0) {
                    notNullTips.put(content, errMsg);
                }
            }
        }

        return notNullTips;
    }

    @Override
    protected boolean checkNotNull() {
        Map<Integer, String> conditionValues = retrieveConditionValues();
        Set<Map.Entry<Integer, String>> entrySet = conditionValues.entrySet();
        for (Map.Entry<Integer, String> set : entrySet) {
            String v = set.getValue();
            if (v == null || v.length() == 0) {
                String err = notNullMsg.get(set.getKey()) == null
                        ? CommonUtil.getResourceMsg("operation.submit.inadequate.prompt")
                        : notNullMsg.get(set.getKey());
                getLogger(DefaultMsgSendAction.class.getName()).info(err + " for component id " + set.getKey() + " value " + v);
                CommonUtil.showMsg(owner.getTopLevelAncestor(), err);
                return false;
            }
        }
        return true;
    }

    protected void send() {
        if (rules != null && !checkNotNull()) {
            return;
        }

        if (rules != null && !checkTemplate()) {
            return;
        }

        if (rules != null && !checkCJK()) {
            return;
        }

        if (rules != null && !checkEqualConditions()) {
            return;
        }

        if (rules != null && !checkNotEqualConditions()) {
            return;
        }

        waitCommunicationTask(new CommunicationWorker());
    }

    class ButtonSendAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            send();
        }

    }

    class EnterKeySendAction extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                getLogger(EnterKeySendAction.class.getName()).log(Level.INFO, "Enter key input and send message");
                send();
            }
        }
    }

    class Condition<C, M> {

        private final C component;
        private final M meta;

        public Condition(C component, M meta) {
            this.component = component;
            this.meta = meta;
        }

        public C getComponent() {
            return component;
        }

        public M getMeta() {
            return meta;
        }

    }

    private class CommunicationWorker extends SwingWorker<String, Integer> {

        @Override
        protected String doInBackground() throws Exception {
            Thread.sleep(1000);
            Map<Integer, String> conditionValues = retrieveConditionValues();
            String msg = createTLSMessage(prsCode, conditionValues);
            getLogger(DefaultMsgSendAction.class.getName()).log(Level.INFO, "TLS Message : {0}", msg);
            String resp = null;
            try {
                if (cmdCode < 0) {
                    resp = sendMessage(msg);
                } else {
                    resp = sendMessage(cmdCode, msg);
                }
            } catch (Exception exception) {
                showErrMsg(Integer.MIN_VALUE, getResourceMsg("network.unaccessed.prompt"));
                throw exception;
            }
            return resp;
        }

        @Override
        protected void done() {
            try {
                String value = get();
                if (value != null) {
                    value = value.trim();
                    getLogger(CommunicationWorker.class.getName()).log(Level.INFO, "Retrieve response from server : {0}", value);
                } else {
                    getLogger(CommunicationWorker.class.getName()).log(Level.INFO, "Retrieve no response from server");
//                    showErrorMsg(owner.getTopLevelAncestor(), "Receive nothing from server");
                    showErrMsg(Integer.MIN_VALUE, getResourceMsg("terminal.no.response.prompt"));
                    return;
                }

                TLSResult result = parseTLSMessage(value);
                getLogger(CommunicationWorker.class.getName()).log(Level.INFO,
                        "Transform TLSResult : {0}", result);
                if (result == null) {
                    getLogger(CommunicationWorker.class.getName()).log(Level.INFO,
                            "Receive invalidated result {0} from server", value);
//                    showErrorMsg(owner.getTopLevelAncestor(), "Receive invalidated result from server");
                    showErrMsg(Integer.MIN_VALUE, getResourceMsg("terminal.invalidated.response.prompt"));
                    return;
                } else {
                    if (result.getRetCode() != 0) {
                        final String errMsg = result.getErrMsg();
                        getLogger(CommunicationWorker.class.getName()).log(Level.INFO,
                                "Operation action failed with retcode {0}, root cause {1}",
                                new Object[]{result.getRetCode(), errMsg});
//                        showDoneMsg(owner.getTopLevelAncestor(), result.getErrMsg());
//                        showErrMsg(Integer.MIN_VALUE, getResourceMsg("terminal.failed.operation.prompt") + result.getRetCode());
                        showErrMsg(Integer.MIN_VALUE, (errMsg == null || errMsg.length() == 0)
                                ? getResourceMsg("terminal.failed.operation.prompt") : errMsg);
                        return;
                    }
                }

                if (next == null || next.getDisplay() < 0) {
                    Logger.getLogger(CommunicationWorker.class.getName()).log(Level.INFO,
                            "No next display page is setup : {0}", next == null ? next : next.getDisplay());
                    showResult(result);
                } else {
                    //show msg to dedicated component
                    Object metaNode = getBlockMeta(next.getDisplay());
                    if (metaNode instanceof Component) {
                        showDoneMsg(result);
                    } else {
                        Logger.getLogger(CommunicationWorker.class.getName()).log(Level.INFO,
                                "Invalidated display component type : {0}", metaNode.getClass().getName());
                    }
                }
            } catch (InterruptedException | ExecutionException | JsonParseException ex) {
                getLogger(CommunicationWorker.class.getName()).log(Level.WARNING, null, ex);
            }
        }

    }

}
