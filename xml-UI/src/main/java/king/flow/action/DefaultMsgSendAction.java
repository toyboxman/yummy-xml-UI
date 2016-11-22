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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
import static king.flow.common.CommonUtil.shapeErrPrompt;
import king.flow.data.TLSResult;
import king.flow.swing.JXGridPanel;
import king.flow.swing.JXMsgPanel;
import king.flow.view.Action;
import king.flow.view.MsgSendAction;
import king.flow.view.Panel;
import king.flow.view.Rules;
import org.apache.commons.lang.StringEscapeUtils;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

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
    protected final List<Integer> doneDisplayList = new ArrayList<>();
    protected final List<Integer> errDisplayList = new ArrayList<>();

    public DefaultMsgSendAction(String prsCode, int cmdCode, List<String> conditionList, MsgSendAction.NextStep next,
            MsgSendAction.Exception expPage, Rules checkRules) {
        this.prsCode = prsCode;
        this.cmdCode = cmdCode;
        this.conditionList = conditionList;
        this.next = next;
        this.error = expPage;
        this.rules = checkRules;
        parseDisplayList();
    }

    protected void parseDisplayList() throws NumberFormatException {
        ArrayList<String> displayList = CommonUtil.buildListParameters(this.next.getDisplay());
        for (String id : displayList) {
            doneDisplayList.add(Integer.parseInt(id));
        }

        displayList = CommonUtil.buildListParameters(this.error.getDisplay());
        for (String id : displayList) {
            errDisplayList.add(Integer.parseInt(id));
        }
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
                    case GRID:
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

    private Map<Integer, String> retrieveConditionValues(boolean cleanCargo) {
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
                case GRID:
                    JXGridPanel grid = (JXGridPanel) condition.getComponent();
                    value = grid.getValue();
                    contents.put(id, value);
                    break;
                case DATE:
                    JXDatePicker jxdp = (JXDatePicker) condition.getComponent();
                    value = CommonUtil.toStringDate(jxdp.getDate());
                    contents.put(id, value);
                    break;
                case PASSWORD_FIELD:
                    boolean isEncryption = false;
                    List<Action> action = condition.getMeta().getAction();
                    for (Action actionConf : action) {
                        if (actionConf.getEncryptKeyboardAction() != null) {
                            isEncryption = true;
                            break;
                        }
                    }

                    if (isEncryption) {
                        getLogger(DefaultMsgSendAction.class.getName()).log(Level.INFO,
                                "use encrypted keyboard mode");
                        value = CommonUtil.retrieveCargo(Integer.toString(id));
                        value = (value == null) ? "" : value;
                        //check notnull condition calling this method, so cannot clean pinblock
                        if (cleanCargo) {
                            CommonUtil.cleanTranStation(Integer.toString(id));
                        }
                    } else {
                        getLogger(DefaultMsgSendAction.class.getName()).log(Level.INFO,
                                "use normal keyboard mode");
                        JPasswordField jpf = (JPasswordField) condition.getComponent();
                        final String unwrapped = new String(jpf.getPassword());
                        if (unwrapped.length() > 0) {
                            try {
                                value = CommonUtil.inputString(unwrapped);
                            } catch (Throwable e) {
                                getLogger(DefaultMsgSendAction.class.getName()).log(Level.WARNING,
                                        "crypto keyboard is out of work due to\n {0}", shapeErrPrompt(e));
                                value = unwrapped;
                            }
                        } else {
                            value = unwrapped;
                        }
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

    protected Map<Integer, String> retrieveConditionValues() {
        return retrieveConditionValues(true);
    }

    public void showResult(TLSResult result) throws HeadlessException {
        CommonUtil.showMsg(owner.getTopLevelAncestor(), result.getOkMsg());
    }

    private void showOnComponent(Object metaNode, TLSResult result) {
        Component meta = (Component) metaNode;
        getLogger(CommunicationWorker.class.getName()).log(Level.INFO,
                "Display component[{0}] type : {1}",
                new Object[]{String.valueOf(meta.getId()), meta.getType().value()});
        JsonParser jsonParser;
        JsonArray arrays;
        switch (meta.getType()) {
            case TABLE:
                try {
                    jsonParser = new JsonParser();
                    arrays = jsonParser.parse(result.getOkMsg()).asArray();
                    JTable table = getBlock(meta.getId(), JTable.class);
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
                } catch (Exception e) {
                    getLogger(DefaultMsgSendAction.class.getName()).log(Level.WARNING,
                            "Invalid array data [ {0} ] for TABLE component[{1}], \n exception is {2}",
                            new Object[]{result.getOkMsg(), String.valueOf(meta.getId()), e});
                }
                break;
            case ADVANCED_TABLE:
                try {
                    jsonParser = new JsonParser();
                    JsonObject jsonObj = jsonParser.parse(result.getOkMsg()).asObject();
                    Integer total = jsonObj.getInt(ADVANCED_TABLE_TOTAL_PAGES);
                    Integer page = jsonObj.getInt(ADVANCED_TABLE_CURRENT_PAGE);
                    arrays = jsonObj.getArray(ADVANCED_TABLE_VALUE);
                    getLogger(CommunicationWorker.class.getName()).log(Level.INFO,
                            "Dump JSON DATA for ADVANCED_TABLE: \n{0} \ntotal: {1} \npage: {2}",
                            new Object[]{jsonObj.toString(), total, page});
                    JXMsgPanel advanceTable = getBlock(meta.getId(), JXMsgPanel.class);
                    advanceTable.refreshTotalPages(total);
                    advanceTable.refreshCurrentPage(page);
                    advanceTable.refreshTable(arrays);
                } catch (Exception e) {
                    getLogger(DefaultMsgSendAction.class.getName()).log(Level.WARNING,
                            "Invalid complex data [ {0} ] for ADVANCED_TABLE component[{1}] \n exception is {2}",
                            new Object[]{result.getOkMsg(), String.valueOf(meta.getId()), e});
                }
                break;
            case GRID:
                try {
                    jsonParser = new JsonParser();
                    arrays = jsonParser.parse(result.getOkMsg()).asArray();
                    JXGridPanel grid = getBlock(meta.getId(), JXGridPanel.class);
                    grid.setDataModel(arrays);
                } catch (Exception e) {
                    getLogger(DefaultMsgSendAction.class.getName()).log(Level.WARNING,
                            "Invalid array data [ {0} ] for GRID component[{1}], \n exception is {2}",
                            new Object[]{result.getOkMsg(), String.valueOf(meta.getId()), e});
                }
                break;
            case LABEL:
                JLabel label = getBlock(meta.getId(), JLabel.class);
                if (result.getRetCode() == 0) {
                    label.setText(StringEscapeUtils.unescapeHtml(result.getOkMsg()));
                } else {
                    label.setText(StringEscapeUtils.unescapeHtml(result.getErrMsg()));
                }
                break;
            case TEXT_FIELD:
                JTextField textField = getBlock(meta.getId(), JTextField.class);
                if (result.getRetCode() == 0) {
                    textField.setText(result.getOkMsg());
                } else {
                    CommonUtil.showErrorMsg(textField.getTopLevelAncestor(), result.getErrMsg());
                }
                break;
            default:
                getLogger(CommunicationWorker.class.getName()).log(Level.WARNING,
                        "Unsupported displayed component type : {0}", meta.getType());
        }
    }

    protected void showErrMsg(final int retCode, final String msg) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                TLSResult result = new TLSResult();
                result.setRetCode(retCode);
                result.setErrMsg(msg);

                if (errDisplayList.size() == 1) {
                    showOnComponent(getBlockMeta(errDisplayList.get(0)), result);
                } else {
                    try {
                        JSONParser jsonParser = new JSONParser();
                        Object element = jsonParser.parse(result.getErrMsg());
                        if (element instanceof JSONArray) {
                            JSONArray jsonArray = (JSONArray) element;
                            int len = Integer.min(errDisplayList.size(), jsonArray.size());
                            for (int i = 0; i < len; i++) {
                                TLSResult freshResult = new TLSResult(result.getRetCode(),
                                        result.getOkMsg(), jsonArray.get(i).toString(), result.getPrtMsg());
                                showOnComponent(getBlockMeta(errDisplayList.get(i)), freshResult);
                            }
                        } else {
                            showOnComponent(getBlockMeta(errDisplayList.get(0)), result);
                        }
                    } catch (Exception e) {
                        getLogger(DefaultMsgSendAction.class.getName()).log(Level.WARNING,
                                "Exception encounters during failed json value parsing : \n{0}", e);
                    }
                }

                panelJump(error.getNextPanel());
            }
        });
    }

    protected void showDoneMsg(final TLSResult result) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                if (doneDisplayList.size() == 1) {
                    showOnComponent(getBlockMeta(doneDisplayList.get(0)), result);
                } else {
                    try {
                        JSONParser jsonParser = new JSONParser();
                        Object element = jsonParser.parse(result.getOkMsg());
                        if (element instanceof JSONArray) {
                            JSONArray jsonArray = (JSONArray) element;
                            int len = Integer.min(doneDisplayList.size(), jsonArray.size());
                            for (int i = 0; i < len; i++) {
                                TLSResult freshResult = new TLSResult(result.getRetCode(),
                                        jsonArray.get(i).toString(), result.getErrMsg(), result.getPrtMsg());
                                showOnComponent(getBlockMeta(doneDisplayList.get(i)), freshResult);
                            }
                        } else {
                            showOnComponent(getBlockMeta(doneDisplayList.get(0)), result);
                        }
                    } catch (Exception e) {
                        getLogger(DefaultMsgSendAction.class.getName()).log(Level.WARNING,
                                "Exception encounters during successful json value parsing : \n{0}", e);
                    }
                }

                CommonUtil.cachePrintMsg(result.getPrtMsg());
                if (result.getRedirection() != null) {
                    String redirection = result.getRedirection();
                    getLogger(DefaultMsgSendAction.class.getName()).log(Level.INFO,
                            "Be forced to jump to page[{0}], ignore designated page[{1}]",
                            new Object[]{redirection, String.valueOf(next.getNextPanel())});
                    int forwardPage = 0;
                    try {
                        forwardPage = Integer.parseInt(redirection);
                    } catch (Exception e) {
                        panelJump(next.getNextPanel());
                        return;
                    }
                    Object blockMeta = getBlockMeta(forwardPage);
                    if (blockMeta == null || !(blockMeta instanceof Panel)) {
                        getLogger(DefaultMsgSendAction.class.getName()).log(Level.INFO,
                                "Be forced to jump to page[{0}], but page[{0}] is invalid Panel type. It is type[{1}]",
                                new Object[]{redirection, (blockMeta == null ? "NULL" : blockMeta.getClass().getSimpleName())});
                        panelJump(next.getNextPanel());
                        return;
                    }
                    panelJump(forwardPage);
                } else {
                    panelJump(next.getNextPanel());

                    //trigger the action denoted in sendMsgAction
                    Integer trigger = next.getTrigger();
                    if (trigger != null && getBlockMeta(trigger) != null) {
                        final Component blockMeta = (Component) getBlockMeta(trigger);
                        switch (blockMeta.getType()) {
                            case COMBO_BOX:
                                JComboBox comboBlock = getBlock(trigger, JComboBox.class);
                                ItemListener[] itemListeners = comboBlock.getItemListeners();
                                ItemEvent e = new ItemEvent(comboBlock,
                                        ItemEvent.ITEM_STATE_CHANGED,
                                        comboBlock.getItemAt(comboBlock.getItemCount() - 1).toString(),
                                        ItemEvent.SELECTED);
                                for (ItemListener itemListener : itemListeners) {
                                    itemListener.itemStateChanged(e);//wait and hang on util progress dialog gets to dispose
                                }
                                if (comboBlock.isEditable()) {
                                    String value = comboBlock.getEditor().getItem().toString();
                                    if (value.length() == 0) {
                                        return;
                                    }
                                }
                                break;
                            case BUTTON:
                                JButton btnBlock = getBlock(trigger, JButton.class);
                                btnBlock.doClick();
                                break;
                            default:
                                getLogger(DefaultMsgSendAction.class.getName()).log(Level.WARNING,
                                        "Invalid trigger component[{0}] as unsupported type[{1}]",
                                        new Object[]{trigger, blockMeta.getType()});
                                break;
                        }
                    }//trigger dealing 
                    //keep next cursor on correct component
                    Integer nextCursor = next.getNextCursor();
                    if (nextCursor != null && getBlockMeta(nextCursor) != null) {
                        JComponent block = getBlock(nextCursor, JComponent.class);
                        block.requestFocusInWindow();
                    }
                }// no redirection branch
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
        Map<Integer, String> conditionValues = retrieveConditionValues(false);
        Set<Map.Entry<Integer, String>> entrySet = conditionValues.entrySet();
        for (Map.Entry<Integer, String> set : entrySet) {
            String v = set.getValue();
            if (v == null || v.length() == 0) {
                String err = notNullMsg.get(set.getKey()) == null
                        ? CommonUtil.getResourceMsg("operation.submit.inadequate.prompt")
                        : notNullMsg.get(set.getKey());
                getLogger(DefaultMsgSendAction.class.getName()).log(Level.INFO, "{0} for component id {1} value {2}",
                        new Object[]{err, Integer.toString(set.getKey()), v});
                CommonUtil.showMsg(owner.getTopLevelAncestor(), err);
                return false;
            }
        }
        return true;
    }

    protected boolean validateRules() {
        if (rules != null) {
            boolean checkNotNull = checkNotNull();
            if (!checkNotNull) {
                return checkNotNull;
            }

            boolean checkTemplate = checkTemplate();
            if (!checkTemplate) {
                return checkTemplate;
            }

            boolean checkCJK = checkCJK();
            if (!checkCJK) {
                return checkCJK;
            }

            boolean checkEqualConditions = checkEqualConditions();
            if (!checkEqualConditions) {
                return checkEqualConditions;
            }

            boolean checkNotEqualConditions = checkNotEqualConditions();
            if (!checkNotEqualConditions) {
                return checkNotEqualConditions;
            }
        }
        return true;
    }

    protected void send() {
        if (!validateRules()) {
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
            getLogger(DefaultMsgSendAction.class.getName()).log(Level.INFO, "Sending TLS Message : \n{0}", msg);
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
                    getLogger(CommunicationWorker.class.getName()).log(Level.FINE, "Retrieve response from server : \n{0}", value);
                } else {
                    getLogger(CommunicationWorker.class.getName()).log(Level.INFO, "Retrieve no response from server");
                    showErrMsg(Integer.MIN_VALUE, getResourceMsg("terminal.no.response.prompt"));
                    return;
                }

                TLSResult result = parseTLSMessage(value);
                getLogger(CommunicationWorker.class.getName()).log(Level.INFO,
                        "Transform response to {0}", result);
                if (result == null) {
                    getLogger(CommunicationWorker.class.getName()).log(Level.INFO,
                            "Receive invalidated result {0} from server", value);
                    showErrMsg(Integer.MIN_VALUE, getResourceMsg("terminal.invalidated.response.prompt"));
                    return;
                } else if (result.getRetCode() != 0) {
                    final String errMsg = result.getErrMsg();
                    getLogger(CommunicationWorker.class.getName()).log(Level.INFO,
                            "Operation action failed with retcode {0}, root cause {1}",
                            new Object[]{result.getRetCode(), errMsg});
                    showErrMsg(Integer.MIN_VALUE, (errMsg == null || errMsg.length() == 0)
                            ? getResourceMsg("terminal.failed.operation.prompt") : errMsg);
                    return;
                }

                if (next == null) {
                    Logger.getLogger(CommunicationWorker.class.getName()).log(Level.INFO,
                            "No next display page is setup : {0}", next == null ? next : next.getDisplay());
                    showResult(result);
                } else {
                    //show msg to dedicated component
                    showDoneMsg(result);
                }
            } catch (InterruptedException | ExecutionException | JsonParseException ex) {
                getLogger(CommunicationWorker.class.getName()).log(Level.WARNING, null, ex);
            }
        }

    }

}
