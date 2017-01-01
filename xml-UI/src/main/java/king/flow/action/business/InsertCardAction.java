/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.flow.action.business;

import com.github.jsonj.JsonArray;
import com.github.jsonj.JsonElement;
import com.github.jsonj.JsonObject;
import com.github.jsonj.JsonPrimitive;
import com.github.jsonj.tools.JsonBuilder;
import com.github.jsonj.tools.JsonParser;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.plaf.FontUIResource;
import king.flow.action.DefaultBaseAction;
import king.flow.common.CommonConstants;
import king.flow.common.CommonUtil;
import static king.flow.common.CommonUtil.getLogger;
import static king.flow.common.CommonUtil.getResourceMsg;
import static king.flow.common.CommonUtil.getWindowNode;
import king.flow.control.driver.GzCardConductor;
import king.flow.view.Action.InsertICardAction;
import king.flow.view.Component;
import king.flow.view.UiStyle;
import king.flow.view.Window;
import static king.flow.common.CommonUtil.swipeGzICCard;
import king.flow.control.driver.CashConductor;
import king.flow.control.driver.HISCardConductor;
import king.flow.control.driver.MedicareCardConductor;
import king.flow.control.driver.PIDCardConductor;
import king.flow.control.driver.PatientCardConductor;
import king.flow.net.Transportation;
import king.flow.view.DeviceEnum;
import org.apache.commons.lang.StringEscapeUtils;

/**
 *
 * @author liujin
 */
public class InsertCardAction extends DefaultBaseAction {

    private static final String OPERATION_CARD_READ_ERROR = "operation.ic.card.read.error";

    private final DeviceEnum cardType;
    private final InsertICardAction.NextStep successfulPage;
    private final InsertICardAction.Exception failedPage;
    private final String animationFile;
    private final ArrayList<Integer> successfulDisplay;
    private final ArrayList<Integer> failedDisplay;
    private final List<String> parameters;

    public InsertCardAction(DeviceEnum cardType,
            InsertICardAction.NextStep successfulPage,
            InsertICardAction.Exception failedPage,
            String animation,
            List<String> parameters) {
        this.cardType = (cardType == null ? DeviceEnum.UNKNOWN : cardType);
        this.successfulPage = successfulPage;
        this.failedPage = failedPage;
        this.animationFile = animation;
        this.parameters = parameters;
        ArrayList<String> displayList = CommonUtil.buildListParameters(successfulPage.getDisplay());
        successfulDisplay = new ArrayList<>();
        for (String id : displayList) {
            successfulDisplay.add(Integer.parseInt(id));
        }

        displayList = CommonUtil.buildListParameters(failedPage.getDisplay());
        failedDisplay = new ArrayList<>();
        for (String id : displayList) {
            failedDisplay.add(Integer.parseInt(id));
        }
    }

    protected JLabel progressTip;

    @Override
    protected void installButtonAction() {
        JButton btn = (JButton) this.owner;
        btn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                switch (cardType) {
                    case GZ_CARD:
                        progressTip = new JLabel(getResourceMsg(GzCardConductor.GUOZHEN_CARD_INSERT_PROMPT));
                        break;
                    case PID_CARD:
                        progressTip = new JLabel(getResourceMsg(PIDCardConductor.PID_CARD_INSERT_PROMPT));
                        break;
                    case HIS_CARD:
                        progressTip = new JLabel(getResourceMsg(HISCardConductor.HIS_CARD_PICKUP_PROMPT));
                        break;
                    case PATIENT_CARD:
                        progressTip = new JLabel(getResourceMsg(PatientCardConductor.HIS_CARD_INSERT_PROMPT));
                        break;
                    case CASH_SAVER:
                        progressTip = new JLabel(getResourceMsg(CashConductor.CASH_INSERT_PROMPT));
                        break;
                    case MEDICARE_CARD:
                        //progressTip = new JLabel(getResourceMsg(MedicareCardConductor.MEDICARE_CARD_INSERT_PROMPT));
                        progressTip = new JLabel("");
                        break;
                    default:
                        progressTip = new JLabel(getResourceMsg("operation.ic.card.insert.prompt"));
                        break;
                }

                JLabel timeoutTip = new JLabel();
                Window windowNode = getWindowNode();
                UiStyle uiStyle = windowNode.getUiStyle();
                if (uiStyle != null && uiStyle.getFont() != null && uiStyle.getFont().getName() != null) {
                    progressTip.setFont(new FontUIResource(uiStyle.getFont().getName(), java.awt.Font.BOLD, 50));
                    timeoutTip.setFont(new FontUIResource(uiStyle.getFont().getName(), java.awt.Font.BOLD, 50));
                } else {
                    progressTip.setFont(new FontUIResource("Dialog", java.awt.Font.BOLD, 50));
                    timeoutTip.setFont(new FontUIResource("Dialog", java.awt.Font.BOLD, 50));
                }
                progressTip.setHorizontalAlignment(SwingConstants.CENTER);
                progressTip.setVerticalAlignment(SwingConstants.BOTTOM);
                timeoutTip.setHorizontalAlignment(SwingConstants.RIGHT);
                timeoutTip.setVerticalAlignment(SwingConstants.BOTTOM);
                timeoutTip.setForeground(Color.red);

                final JDialog progressAnimation = buildAnimationDialog(animationFile);
                progressTip.setBounds(0, 120, progressAnimation.getBounds().width, 80);
                //progressTip.setBorder(new LineBorder(Color.yellow));
                timeoutTip.setBounds(0, 30, progressAnimation.getBounds().width, 60);
                //timeoutTip.setBorder(new LineBorder(Color.red));

                progressAnimation.getContentPane().add(timeoutTip, 1);
                progressAnimation.getContentPane().add(progressTip, 2);

                final ImageIcon bgImage = CommonUtil.getImageIcon("/image/2.jpg");
                //final ImageIcon bgImage = CommonUtil.getDefaultBackgroundImage();
                if (bgImage != null) {
                    progressAnimation.getContentPane().add(new JLabel(bgImage), 3);
                } else {
                    progressAnimation.getContentPane().add(new JLabel(), 3);
                }

                progressAnimation.addComponentListener(new ComponentAdapter() {
                    final Timer timer = new Timer();

                    @Override
                    public void componentShown(ComponentEvent e) {

                        timer.scheduleAtFixedRate(new TimerTask() {
                            int i = 40;

                            public void run() {
                                timeoutTip.setText(String.valueOf(i--));
                                if (i < 0) {
                                    timer.cancel();
                                }
                            }
                        }, 0, TimeUnit.SECONDS.toMillis(1));
                    }

                });

                switch (cardType) {
                    case GZ_CARD:
                        waitCommunicationTask(new GZReadCardTask(), progressAnimation);
                        break;
                    case PID_CARD:
                        waitCommunicationTask(new PIDReadCardTask(), progressAnimation);
                        break;
                    case HIS_CARD:
                        waitCommunicationTask(new HISProvisionCardTask(), progressAnimation);
                        break;
                    case PATIENT_CARD:
                        waitCommunicationTask(new HISReadCardTask(), progressAnimation);
                        break;
                    case CASH_SAVER:
                        //waitCommunicationTask(new CashDepositeTask(), progressAnimation);
                        new CashDepositeTask().execute();
                        break;
                    case MEDICARE_CARD:
                        waitCommunicationTask(new MedicareRunTask(), progressAnimation);
                        break;
                    default:
                        getLogger(InsertCardAction.class.getName()).log(Level.WARNING,
                                "Unsupported card type[{0}] for InsertCardAction", cardType.name());
                        handleErr(getResourceMsg(OPERATION_CARD_READ_ERROR));
                }
            }
        });
    }

    private void showOnComponent(int componentId, String value) {
        Component meta = (Component) getBlockMeta(componentId);
        getLogger(InsertCardAction.class.getName()).log(Level.INFO,
                "Display component[{0}] type : {1}",
                new Object[]{String.valueOf(meta.getId()), meta.getType().value()});
        switch (meta.getType()) {
            case LABEL:
                JLabel label = getBlock(meta.getId(), JLabel.class);
                label.setText(StringEscapeUtils.unescapeHtml(value));
                break;
            case TEXT_FIELD:
                JTextField textField = getBlock(meta.getId(), JTextField.class);
                textField.setText(value);
                break;
            case COMBO_BOX:
                JComboBox combo = getBlock(meta.getId(), JComboBox.class);
                if (combo.isEditable()) {
                    combo.getEditor().setItem(value);
                }
                break;
            default:
                getLogger(InsertCardAction.class.getName()).log(Level.WARNING,
                        "Unsupported showed component type : {0}", meta.getType());
        }
    }

    private void handleErr(String errPrompt) {
        showOnComponent(failedDisplay.get(0),
                errPrompt == null ? getResourceMsg(OPERATION_CARD_READ_ERROR) : errPrompt);
        panelJump(failedPage.getNextPanel());
        Integer trigger = failedPage.getTrigger();
        if (getBlockMeta(trigger) != null && (getBlockMeta(trigger) instanceof Component)) {
            getBlock(trigger, JButton.class).doClick();
        }
    }

    private class MedicareRunTask extends SwingWorker<String, String> {

        @Override
        protected String doInBackground() throws Exception {

            try {
                List<String> debug = successfulPage.getDebug();

                if (debug.isEmpty()) {
                    JsonArray jsonParameters = JsonBuilder.array();
                    for (String param : parameters) {
                        Object meta = getBlockMeta(Integer.parseInt(param));
                        String paramValue = null;
                        final Component componentMeta = (Component) meta;
                        switch (componentMeta.getType()) {
                            case LABEL:
                                JLabel label = getBlock(componentMeta.getId(), JLabel.class);
                                paramValue = label.getText();
                                break;
                            case TEXT_FIELD:
                                JTextField textField = getBlock(componentMeta.getId(), JTextField.class);
                                paramValue = textField.getText();
                                break;
                            case COMBO_BOX:
                                JComboBox combobox = getBlock(componentMeta.getId(), JComboBox.class);
                                paramValue = combobox.getEditor().getItem().toString();
                                break;
                            default:
                                throw new Exception("No Valid Parameter Component set in parameters attribute");
                        }

                        jsonParameters.add(String.format("\"%s\"", paramValue));
                    }

                    String variousParam = jsonParameters.toString();
                    getLogger(InsertCardAction.class.getName()).log(Level.INFO,
                            "Medicare card operation parameters : \n{0}", variousParam);
                    String result = CommonUtil.runMedicareCardCmd(variousParam);
                    if (result == null || result.length() == 0) {
                        //fail to read card information
                        throw new Exception(MedicareCardConductor.MEDICARE_CARD_OPERATION_ERROR_PROMPT);
                    }

                    getLogger(InsertCardAction.class.getName()).log(Level.INFO,
                            "Medicare card operation result : \n{0}", result);
                    JsonParser jsonParser = new JsonParser();
                    JsonObject element = jsonParser.parse(result).asObject();
                    JsonElement error = element.get("Error");
                    if (error != null) {
                        throw new Exception(error.toString());
                    }

                    List<String> displayValues = new ArrayList<>(element.size());
                    for (JsonElement value : element.values()) {
                        displayValues.add(value.toString());
                    }
                    int len = Math.min(displayValues.size(), successfulDisplay.size());
                    for (int i = 0; i < len; i++) {
                        showOnComponent(successfulDisplay.get(i), displayValues.get(i));
                    }
                } else {
                    //debug mode
                    Thread.sleep(CommonConstants.DEBUG_MODE_PROGRESS_TIME);
                    int len = Math.min(debug.size(), successfulDisplay.size());
                    for (int i = 0; i < len; i++) {
                        showOnComponent(successfulDisplay.get(i), debug.get(i));
                    }
                }

                Integer trigger = successfulPage.getTrigger();
                if (trigger == null) {
                    panelJump(successfulPage.getNextPanel());
                } else {
                    getBlock(trigger, JButton.class).doClick();
                }
                return "Success";
            } catch (Throwable t) {
                getLogger(InsertCardAction.class.getName()).log(Level.SEVERE,
                        "Occur problem during running Medicare card, root cause comes from \n{0}", t.getMessage());
                String errPrompt = getResourceMsg(t.getMessage());
                handleErr(errPrompt);
                throw new Exception(t);
            }
        }

    }

    private class CashDepositeTask extends SwingWorker<String, String> {

        @Override
        protected String doInBackground() throws Exception {
            try {
                List<String> debug = successfulPage.getDebug();

                if (debug.isEmpty()) {
                    String cardIndex = parameters.get(0);
                    String cardId = null;
                    Object meta = getBlockMeta(Integer.parseInt(cardIndex));
                    if (!(meta instanceof Component)) {
                        throw new Exception("No Correct Card Number Component set in parameters attribute");
                    }
                    final Component componentMeta = (Component) meta;
                    switch (componentMeta.getType()) {
                        case LABEL:
                            JLabel label = getBlock(componentMeta.getId(), JLabel.class);
                            cardId = label.getText();
                            break;
                        case TEXT_FIELD:
                            JTextField textField = getBlock(componentMeta.getId(), JTextField.class);
                            cardId = textField.getText();
                            break;
                        case COMBO_BOX:
                            JComboBox combobox = getBlock(componentMeta.getId(), JComboBox.class);
                            cardId = combobox.getEditor().getItem().toString();
                            break;
                        default:
                            throw new Exception("No Valid Card Number Component set in parameters attribute");
                    }

//                    CommonUtil.openCashSaver();
//                    CashConductor.CONTINUE_CASH_DEPOSITION.set(true);
                    panelJump(successfulPage.getNextPanel());

//                    while (CashConductor.CONTINUE_CASH_DEPOSITION.get()) {
//                        Thread.sleep(CommonConstants.RUN_MODE_PROGRESS_TIME);
//                        if (!CashConductor.CONTINUE_CASH_DEPOSITION.get()) {
//                            break;
//                        }
                    String cashAmount = CommonUtil.depositeCash(cardId);// driver will blocking thread and wait IC card information return
                    if (cashAmount == null || cashAmount.length() == 0) {
                        //fail to read card information
                        throw new Exception(CashConductor.CASH_DEPOSITE_ERROR_PROMPT);
                    }

                    getLogger(InsertCardAction.class.getName()).log(Level.INFO,
                            "depositing cash amount : {0}", cashAmount);
                    JsonParser jsonParser = new JsonParser();
                    JsonObject element = jsonParser.parse(cashAmount).asObject();

                    List<String> displayValues = new ArrayList<>(element.size());
                    for (JsonElement value : element.values()) {
                        displayValues.add(value.toString());
                    }
                    int len = Math.min(displayValues.size(), successfulDisplay.size());
                    for (int i = 0; i < len; i++) {
                        showOnComponent(successfulDisplay.get(i), displayValues.get(i));
                    }
//                    }
                } else {
                    //debug mode
                    Thread.sleep(CommonConstants.DEBUG_MODE_PROGRESS_TIME);
                    int len = Math.min(debug.size(), successfulDisplay.size());
                    for (int i = 0; i < len; i++) {
                        showOnComponent(successfulDisplay.get(i), debug.get(i));
                    }
                }

                Integer trigger = successfulPage.getTrigger();
                if (trigger == null) {
                    panelJump(successfulPage.getNextPanel());
                } else {
                    getBlock(trigger, JButton.class).doClick();
                }
                return "Success";
            } catch (Throwable t) {
                getLogger(InsertCardAction.class.getName()).log(Level.SEVERE,
                        "Occur problem during depositing cash, root cause comes from \n{0}", t.getMessage());
                String errPrompt = getResourceMsg(t.getMessage());
                handleErr(errPrompt);
                throw new Exception(t);
            }
        }

    }

    private class HISReadCardTask extends SwingWorker<String, String> {

        @Override
        protected String doInBackground() throws Exception {
            try {
                List<String> debug = successfulPage.getDebug();

                if (debug.isEmpty()) {
                    Thread.sleep(CommonConstants.RUN_MODE_PROGRESS_TIME);

                    String cardInfo = CommonUtil.readPatientCard();// driver will blocking thread and wait IC card information return
                    if (cardInfo == null || cardInfo.length() == 0) {
                        //fail to read card information
                        throw new Exception(PatientCardConductor.HIS_CARD_READ_ERROR_PROMPT);
                    }

                    getLogger(InsertCardAction.class.getName()).log(Level.INFO,
                            "Reading information from ID card:\n{0}", cardInfo);
                    JsonParser jsonParser = new JsonParser();
                    JsonObject element = jsonParser.parse(cardInfo).asObject();

                    List<String> displayValues = new ArrayList<>(element.size());
                    for (JsonElement value : element.values()) {
                        displayValues.add(value.toString());
                    }
                    int len = Math.min(displayValues.size(), successfulDisplay.size());
                    for (int i = 0; i < len; i++) {
                        showOnComponent(successfulDisplay.get(i), displayValues.get(i));
                    }
                } else {
                    //debug mode
                    Thread.sleep(CommonConstants.DEBUG_MODE_PROGRESS_TIME);
                    int len = Math.min(debug.size(), successfulDisplay.size());
                    for (int i = 0; i < len; i++) {
                        showOnComponent(successfulDisplay.get(i), debug.get(i));
                    }
                }

                Integer trigger = successfulPage.getTrigger();
                if (trigger == null) {
                    panelJump(successfulPage.getNextPanel());
                } else {
                    getBlock(trigger, JButton.class).doClick();
                }
                return "Success";
            } catch (Throwable t) {
                getLogger(InsertCardAction.class.getName()).log(Level.SEVERE,
                        "Occur problem during reading IC card, root cause comes from \n{0}", t.getMessage());
                String errPrompt = getResourceMsg(t.getMessage());
                handleErr(errPrompt);
                throw new Exception(t);
            }
        }

    }

    private class HISProvisionCardTask extends SwingWorker<String, String> {

        @Override
        protected String doInBackground() throws Exception {
            try {
                List<String> debug = successfulPage.getDebug();

                if (debug.isEmpty()) {
                    Thread.sleep(CommonConstants.RUN_MODE_PROGRESS_TIME);

                    String cardInfo = CommonUtil.pickUpHISCard();// driver will blocking thread and wait IC card information return
                    if (cardInfo == null || cardInfo.length() == 0) {
                        //fail to read card information
                        throw new Exception(HISCardConductor.HIS_CARD_PICKUP_ERROR_PROMPT);
                    }

                    getLogger(InsertCardAction.class.getName()).log(Level.INFO,
                            "Reading information from ID card:\n{0}", cardInfo);
                    JsonParser jsonParser = new JsonParser();
                    JsonObject element = jsonParser.parse(cardInfo).asObject();

                    List<String> displayValues = new ArrayList<>(element.size());
                    for (JsonElement value : element.values()) {
                        displayValues.add(value.toString());
                    }
                    int len = Math.min(displayValues.size(), successfulDisplay.size());
                    for (int i = 0; i < len; i++) {
                        showOnComponent(successfulDisplay.get(i), displayValues.get(i));
                    }
                } else {
                    //debug mode
                    Thread.sleep(CommonConstants.DEBUG_MODE_PROGRESS_TIME);
                    int len = Math.min(debug.size(), successfulDisplay.size());
                    for (int i = 0; i < len; i++) {
                        showOnComponent(successfulDisplay.get(i), debug.get(i));
                    }
                }

                Integer trigger = successfulPage.getTrigger();
                if (trigger == null) {
                    panelJump(successfulPage.getNextPanel());
                } else {
                    getBlock(trigger, JButton.class).doClick();
                }
                return "Success";
            } catch (Throwable t) {
                getLogger(InsertCardAction.class.getName()).log(Level.SEVERE,
                        "Occur problem during reading IC card, root cause comes from \n{0}", t.getMessage());
                String errPrompt = getResourceMsg(t.getMessage());
                handleErr(errPrompt);
                throw new Exception(t);
            }
        }
    }

    private class PIDReadCardTask extends SwingWorker<String, String> {

        @Override
        protected String doInBackground() throws Exception {
            try {
                List<String> debug = successfulPage.getDebug();

                if (debug.isEmpty()) {
                    Thread.sleep(CommonConstants.RUN_MODE_PROGRESS_TIME);

                    String cardInfo = CommonUtil.swipeIDCard();// driver will blocking thread and wait IC card information return
                    if (cardInfo == null || cardInfo.length() == 0) {
                        //fail to read card information
                        throw new Exception(PIDCardConductor.PID_CARD_READ_ERROR_PROMPT);
                    }

                    getLogger(InsertCardAction.class.getName()).log(Level.INFO,
                            "Reading information from ID card:\n{0}", cardInfo);
                    JsonParser jsonParser = new JsonParser();
                    JsonObject element = jsonParser.parse(cardInfo).asObject();

                    List<String> displayValues = new ArrayList<>(element.size());
                    for (JsonElement value : element.values()) {
                        displayValues.add(value.toString());
                    }
                    int len = Math.min(displayValues.size(), successfulDisplay.size());
                    for (int i = 0; i < len; i++) {
                        showOnComponent(successfulDisplay.get(i), displayValues.get(i));
                    }
                } else {
                    //debug mode
                    Thread.sleep(CommonConstants.DEBUG_MODE_PROGRESS_TIME);
                    int len = Math.min(debug.size(), successfulDisplay.size());
                    for (int i = 0; i < len; i++) {
                        showOnComponent(successfulDisplay.get(i), debug.get(i));
                    }
                }

                Integer trigger = successfulPage.getTrigger();
                if (trigger == null) {
                    panelJump(successfulPage.getNextPanel());
                } else {
                    getBlock(trigger, JButton.class).doClick();
                }
                return "Success";
            } catch (Throwable t) {
                getLogger(InsertCardAction.class.getName()).log(Level.SEVERE,
                        "Occur problem during reading IC card, root cause comes from \n{0}", t.getMessage());
                String errPrompt = getResourceMsg(t.getMessage());
                handleErr(errPrompt);
                throw new Exception(t);
            }
        }
    }

    private class GZReadCardTask extends SwingWorker<String, String> {

        @Override
        protected String doInBackground() throws Exception {
            try {
                List<String> debug = successfulPage.getDebug();

                if (debug.isEmpty()) {
                    Thread.sleep(CommonConstants.RUN_MODE_PROGRESS_TIME);
                    String cardInfo = swipeGzICCard();
                    if (cardInfo != null && cardInfo.equals(GzCardConductor.UNREGISTRY_CARD_TYPE)) {
                        throw new Exception(GzCardConductor.GUOZHEN_CARD_UNREGISTRY_PROMPT);
                    }
                    JsonParser jsonParser = new JsonParser();
                    JsonObject element = jsonParser.parse(cardInfo).asObject();
                    String cardId = element.getString(GzCardConductor.CARD_NO);
                    if (cardId == null || cardId.length() == 0) {
                        throw new Exception("No card number is gotten from this card");
                    }

                    JsonElement gasSurplus = element.get(GzCardConductor.CARD_SPARE);
                    if (gasSurplus == null) {
                        gasSurplus = new JsonPrimitive(GzCardConductor.ZERO_GAS_SURPLUS);
                        element.put(GzCardConductor.CARD_SPARE, gasSurplus);
                    }

                    JsonElement cardType = element.get(GzCardConductor.CARD_FACTORY);
                    if (cardType == null) {
                        cardType = new JsonPrimitive(GzCardConductor.UNSUPPORT_CARD_TYPE);
                        element.put(GzCardConductor.CARD_FACTORY, cardType);
                    }

                    switch (String.valueOf(cardType)) {
                        case GzCardConductor.UNSUPPORT_CARD_TYPE:
                            throw new Exception(GzCardConductor.GUOZHEN_CARD_OPERATION_PROMPT);
                        case GzCardConductor.CARD4_TYPE:
                            if (!CommonUtil.allowCPUCard()) {
                                throw new Exception(GzCardConductor.GUOZHEN_CARD_OPERATION_PROMPT);
                            } else {
                                Transportation.Misc.AllowCPU allowCPUConfig = CommonUtil.getAllowCPUConfig();
                                long start = CommonUtil.convertCalendarToMills(allowCPUConfig.getPeriod().getStart());
                                long end = CommonUtil.convertCalendarToMills(allowCPUConfig.getPeriod().getEnd());
                                long now = System.currentTimeMillis();
                                if (now < start || now > end) {
                                    throw new Exception(GzCardConductor.GUOZHEN_CARD_PERIOD_PROMPT);
                                }
                            }
                            break;
                        default:
                            break;
                    }

                    //cache current card information for writing action
                    CommonUtil.cacheCardInfo(element);

                    List<String> displayValues = new ArrayList<>();
                    displayValues.add(cardId);
                    displayValues.add(String.valueOf(gasSurplus));
                    displayValues.add(String.valueOf(cardType));
                    int len = Math.min(displayValues.size(), successfulDisplay.size());
                    for (int i = 0; i < len; i++) {
                        showOnComponent(successfulDisplay.get(i), displayValues.get(i));
                    }
                } else {
                    //debug mode
                    Thread.sleep(CommonConstants.DEBUG_MODE_PROGRESS_TIME);
                    int len = Math.min(debug.size(), successfulDisplay.size());
                    for (int i = 0; i < len; i++) {
                        showOnComponent(successfulDisplay.get(i), debug.get(i));
                    }
                }

                Integer trigger = successfulPage.getTrigger();
                if (trigger == null) {
                    panelJump(successfulPage.getNextPanel());
                } else {
                    getBlock(trigger, JButton.class).doClick();
                }
                return "Success";
            } catch (Throwable t) {
                getLogger(InsertCardAction.class.getName()).log(Level.SEVERE,
                        "Occur problem during reading IC card, root cause comes from \n{0}", t.getMessage());
                String errPrompt = getResourceMsg(t.getMessage());
                handleErr(errPrompt);
                throw new Exception(t);
            }
        }

    }
}
