package king.flow.control;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableSet;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.logging.Level;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.xml.bind.JAXBException;
import king.flow.action.Action;
import king.flow.action.AdvancedMsgSendAction;
import king.flow.action.DefaultButtonAction;
import king.flow.action.DefaultCleanAction;
import king.flow.action.DefaultComboBoxAction;
import king.flow.action.DefaultFileUploadAction;
import king.flow.action.DefaultFontAction;
import king.flow.action.DefaultGridAction;
import king.flow.action.DefaultHideAction;
import king.flow.action.DefaultMsgSendAction;
import king.flow.action.DefaultTableAction;
import king.flow.action.DefaultTextFieldAction;
import king.flow.action.DefaultMediaAction;
import king.flow.action.DefaultMenuAction;
import king.flow.action.DefaultNumericPadAction;
import king.flow.action.DefaultPrinterAction;
import king.flow.action.DefaultRunCommandAction;
import king.flow.action.DefaultTipAction;
import king.flow.action.DefaultVideoAction;
import king.flow.action.DefaultVirtualKeyBoardAction;
import king.flow.action.DefaultWebLoadAction;
import king.flow.action.business.BalanceTransAction;
import king.flow.action.business.EjectCardAction;
import king.flow.action.business.InsertCardAction;
import king.flow.action.business.KeyboardEncryptAction;
import king.flow.action.business.MoveCursorAction;
import king.flow.action.business.OpenBrowserAction;
import king.flow.action.business.PrintPassbookAction;
import king.flow.action.business.RWFingerPrintAction;
import king.flow.action.business.ReadCardAction;
import king.flow.action.business.WriteCardAction;
import king.flow.action.business.Read2In1CardAction;
import king.flow.action.business.ShowClockAction;
import king.flow.action.business.WithdrawCardAction;
import king.flow.common.CommonConstants;
import static king.flow.common.CommonConstants.BALANCE_TRANS_ACTION;
import static king.flow.common.CommonConstants.CLEAN_ACTION;
import static king.flow.common.CommonConstants.CONTAINER_KEY;
import static king.flow.common.CommonConstants.INSERT_IC_ACTION;
import static king.flow.common.CommonConstants.LIMIT_INPUT_ACTION;
import static king.flow.common.CommonConstants.OPEN_BROWSER_ACTION;
import static king.flow.common.CommonConstants.OPEN_VIRTUAL_KEYBOARD_ACTION;
import static king.flow.common.CommonConstants.PLAY_MEDIA_ACTION;
import static king.flow.common.CommonConstants.PLAY_VIDEO_ACTION;
import static king.flow.common.CommonConstants.PRINT_PASSBOOK_ACTION;
import static king.flow.common.CommonConstants.PRINT_RECEIPT_ACTION;
import static king.flow.common.CommonConstants.READ_WRITE_FINGERPRINT_ACTION;
import static king.flow.common.CommonConstants.RUN_COMMAND_ACTION;
import static king.flow.common.CommonConstants.SET_FONT_ACTION;
import static king.flow.common.CommonConstants.SHOW_CLOCK_ACTION;
import static king.flow.common.CommonConstants.SHOW_TABLE_ACTION;
import static king.flow.common.CommonConstants.SWIPE_CARD_ACTION;
import static king.flow.common.CommonConstants.TABLE_ROW_HEIGHT;
import static king.flow.common.CommonConstants.UPLOAD_FILE_ACTION;
import static king.flow.common.CommonConstants.USE_TIP_ACTION;
import static king.flow.common.CommonConstants.WRITE_IC_ACTION;
import king.flow.common.CommonUtil;
import static king.flow.common.CommonUtil.adjustByResolution;
import static king.flow.common.CommonUtil.buildListParameters;
import static king.flow.common.CommonUtil.createFont;
import static king.flow.common.CommonUtil.getLogger;
import static king.flow.common.CommonUtil.setFont;
import static king.flow.common.CommonUtil.getImageIcon;
import static king.flow.common.CommonUtil.isActionSupport;
import static king.flow.common.CommonUtil.setCurrentView;
import static king.flow.common.CommonUtil.setWindowNode;
import static king.flow.common.CommonUtil.shapeErrPrompt;
import static king.flow.control.BankAppStarter.DEFAULT_BANK_APP_ICON;
import king.flow.design.FlowProcessor;
import king.flow.swing.JXMsgPanel;
import king.flow.swing.NativeBroswer;
import king.flow.swing.SwingBrowser;
import king.flow.swing.VideoPlayer;
import king.flow.view.BasicAttribute;
import king.flow.view.Bound;
import king.flow.view.Component;
import king.flow.view.ComponentEnum;
import king.flow.view.Decorator;
import king.flow.view.DecoratorEnum;
import king.flow.view.DefinedAction;
import king.flow.view.DeviceEnum;
import king.flow.view.Font;
import king.flow.view.FontstyleEnum;
import king.flow.view.JumpAction;
import king.flow.view.Menuaction;
import king.flow.view.Menubar;
import king.flow.view.Menuitem;
import king.flow.view.Menuoption;
import king.flow.view.MsgSendAction;
import king.flow.view.Panel;
import king.flow.view.PanelEnum;
import king.flow.view.Rules;
import king.flow.view.UiStyle;
import king.flow.view.WindowEnum;
import org.jdesktop.swingx.JXButton;
import org.jdesktop.swingx.JXDatePicker;
import org.jdesktop.swingx.JXLabel;
import static king.flow.common.CommonConstants.SWIPE_TWO_IN_ONE_CARD_ACTION;
import king.flow.swing.JXGridPanel;
import king.flow.view.Action.ShowGridAction;
import org.apache.commons.lang.StringEscapeUtils;
import static king.flow.common.CommonConstants.EJECT_CARD_ACTION;
import static king.flow.common.CommonConstants.ENCRYPT_KEYBORAD_ACTION;
import static king.flow.common.CommonConstants.HIDE_ACTION;
import static king.flow.common.CommonConstants.JUMP_ACTION;
import static king.flow.common.CommonConstants.MOVE_CURSOR_ACTION;
import static king.flow.common.CommonConstants.SEND_MSG_ACTION;
import static king.flow.common.CommonConstants.SHOW_COMBOBOX_ACTION;
import static king.flow.common.CommonConstants.SHOW_GRID_ACTION;
import static king.flow.common.CommonConstants.TYPE_NUMERIC_PAD_ACTION;
import static king.flow.common.CommonConstants.WEB_LOAD_ACTION;
import static king.flow.common.CommonConstants.WITHDRAW_CARD_ACTION;
import king.flow.swing.JXNumericPad;
import king.flow.view.Action.NumericPadAction;
import static king.flow.view.ComponentEnum.TEXT_FIELD;
import king.flow.view.Driver;

/**
 *
 * @author LiuJin
 */
public class MainWindow {

    private king.flow.view.Window winNode = null;
    private Window window = null;
    Map<Integer, Object> building_blocks = null;
    Map<Integer, Object> meta_blocks = null;
    private Map<Panel, String> panelNodes = null;
    private List<Menuitem> menuNodes = null;

    public MainWindow(king.flow.view.Window node) {
        this.winNode = node;
        setWindowNode(winNode);
        this.building_blocks = new HashMap<>();
        this.meta_blocks = new HashMap<>();
        this.panelNodes = new Hashtable<>();
        this.menuNodes = new ArrayList<>();
        initUI();
        initActions();
    }

    private void initUI() {
        UiStyle style = winNode.getUiStyle();
        initWindow();

        king.flow.view.Window.Contents contents = winNode.getContents();
        List<String> pages = contents.getPage();
        String page = null;
        try {
            String defaultBackground = style.getBackground() == null ? null : style.getBackground().trim();
            for (String pageURI : pages) {
                page = pageURI;
                final FlowProcessor flowProcessor = new FlowProcessor(pageURI);
                Panel pNode = flowProcessor.parse(Panel.class);
                if (winNode.isFormat() != null && winNode.isFormat()) {
                    flowProcessor.writeOut(pNode);
                }
                
                if (this.meta_blocks.containsKey(pNode.getId())) {
                    CommonUtil.showBlockedErrorMsg(null, CommonUtil.buildErrMsg(pNode.getType().toString(), pNode.getId(), pageURI).toString(), true);
                }

                this.panelNodes.put(pNode, pageURI);
                String background = pNode.getBackground();
                JPanel panel = constructPanel(pNode);
                initComponents(pNode, panel, pageURI);
                initDecorators(pNode, panel, pageURI);

                if (background != null && background.length() > 0) {
                    try {
                        JLabel jLabel = new JLabel(getImageIcon(background), JLabel.LEADING);
                        jLabel.setBounds(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds());
                        panel.add(jLabel, -1);
                    } catch (Exception e) {
                        getLogger(MainWindow.class.getName()).log(Level.WARNING, "Designated background {0} is not existed, use empty background", background);
                    }
                } else {
                    int index = 0;
                    while (index == 0) {
                        index = new Random().nextInt(6);
                    }
                    background = new StringBuilder("/image/").append(index).append(".jpg").toString();
                    URL resource = MainWindow.class.getResource(background);
                    if (resource != null) {
                        try {
                            JLabel jLabel = new JLabel(getImageIcon(background), JLabel.LEADING);
                            jLabel.setBounds(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds());
                            panel.add(jLabel, -1);
                        } catch (Exception e1) {
                            getLogger(MainWindow.class.getName()).log(Level.WARNING, "Designated background {0} is not existed, use empty background", background);
                        }
                    } else if (defaultBackground != null && defaultBackground.length() > 0) {
                        try {
                            JLabel jLabel = new JLabel(getImageIcon(defaultBackground), JLabel.LEADING);
                            UIManager.put(CommonConstants.KING_FLOW_BACKGROUND, getImageIcon(defaultBackground));
                            jLabel.setBounds(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds());
                            panel.add(jLabel, -1);
                        } catch (Exception e2) {
                            getLogger(MainWindow.class.getName()).log(Level.WARNING, "Designated defaultBackground {0} is not existed, use empty background", defaultBackground);
                        }
                    } else {
                        getLogger(MainWindow.class.getName()).log(Level.WARNING, "No background is set, use empty background");
                    }
                }
            }
        } catch (JAXBException ex) {
            final String severeMsg = "Page file<" + page + "> \ncontains severe error and fails to parse";
            getLogger(MainWindow.class.getName()).log(Level.SEVERE, severeMsg, ex);
            CommonUtil.showBlockedErrorMsg(null, severeMsg + "\nRoot Cause:\n" + ex.getCause().getMessage(), true);
        }
    }

    private JPanel constructPanel(Panel pNode) throws AssertionError {
        final int id = pNode.getId();
        PanelEnum type = pNode.getType();
        JPanel panel = null;
        switch (type) {
            case PANEL:
                panel = new JPanel(null);
                break;
            default:
                final AssertionError configError = new AssertionError("Mistaken configuration type out of PANEL : " + type.value());
                getLogger(MainWindow.class.getName()).log(Level.SEVERE, "Panel configuration Error", configError);
                throw configError;
        }

        this.building_blocks.put(id, panel);
        this.meta_blocks.put(id, pNode);
        final boolean activePanel = pNode.isActive() == null ? false : pNode.isActive();
        if (activePanel) {
            switch (this.window.getClass().getSimpleName()) {
                case "JFrame":
                    ((JFrame) window).getContentPane().add(panel, BorderLayout.CENTER);
                    break;
                case "JDialog":
                    ((JDialog) window).getContentPane().add(panel, BorderLayout.CENTER);
                    break;
                default:
                    final AssertionError unknownContainerError = new AssertionError(
                            "Unknown container type out of JFrame or JDialog : "
                            + this.window.getClass().getSimpleName());
                    getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, unknownContainerError);
                    throw unknownContainerError;
            }
        }
        return panel;
    }

    private void initDecorators(Panel pNode, JPanel panel, String pageURI) throws AssertionError {
        List<Decorator> decorators = pNode.getDecorator();
        for (Decorator decorator : decorators) {
            if (this.meta_blocks.containsKey(decorator.getId())) {
                CommonUtil.showBlockedErrorMsg(null, CommonUtil.buildErrMsg(decorator.getType().toString(),
                        decorator.getId(), pageURI).toString(), true);
            }
            constructDecorator(decorator, panel, pageURI);
        }
    }

    private void constructDecorator(Decorator decorator, JPanel panel, String pageURI) throws AssertionError {
        final int decorator_id = decorator.getId();
        DecoratorEnum type = decorator.getType();
        BasicAttribute attribute = decorator.getAttribute();
        Component component = decorator.getComponent();
        Bound rect = attribute.getRect();
        if (isScaleScreen()) {
            rect = adjustByResolution(rect);
        }
        switch (type) {
            case SCROLL_PANEL:
                JScrollPane scrollPane = new JScrollPane();
                scrollPane.setBounds(rect.getX(), rect.getY(),
                        rect.getWidth(), rect.getHeigh());
                this.building_blocks.put(decorator_id, scrollPane);
                this.meta_blocks.put(decorator_id, decorator);
                if (this.meta_blocks.containsKey(component.getId())) {
                    CommonUtil.showBlockedErrorMsg(null, CommonUtil.buildErrMsg(component.getType().toString(),
                            component.getId(), pageURI).toString(), true);
                }
                JComponent constructor = constructComponent(component);
                scrollPane.setViewportView(constructor);
                scrollPane.setOpaque(false);
                scrollPane.getViewport().setOpaque(false);
                scrollPane.setBorder(null);
                scrollPane.setViewportBorder(null);
                panel.add(scrollPane);
                debugComponent(attribute, scrollPane, decorator_id, decorator.getType().toString());
                break;
            case TAB_PANEL:
                JTabbedPane tabbedPane = new JTabbedPane();
                tabbedPane.setBounds(rect.getX(), rect.getY(),
                        rect.getWidth(), rect.getHeigh());
                this.building_blocks.put(decorator_id, tabbedPane);
                this.meta_blocks.put(decorator_id, decorator);
                if (this.meta_blocks.containsKey(component.getId())) {
                    CommonUtil.showBlockedErrorMsg(null, CommonUtil.buildErrMsg(component.getType().toString(),
                            component.getId(), pageURI).toString(), true);
                }
                JComponent component1 = constructComponent(component);
                tabbedPane.add(component.getAttribute().getText(), component1);
                panel.add(tabbedPane);
                debugComponent(attribute, tabbedPane, decorator_id, decorator.getType().toString());
                break;
            default:
                final AssertionError configError = new AssertionError("Mistaken configuration type out of SCROLL_PANEL : "
                        + type.value());
                getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, configError);
                throw configError;
        }
    }

    public void debugComponent(BasicAttribute attribute, JComponent component, int componentID, String componentType) throws NumberFormatException {
        if (attribute.getDebug() != null) {
            BasicAttribute.Debug debug = attribute.getDebug();
            if (debug.isEnable()) {
                String colorDef = debug.getColor();
                if (colorDef != null) {
                    Color color = CommonUtil.getTrueColor(colorDef);
                    if (color == null) {
                        StringBuilder errMsg = new StringBuilder("{attribute} property of ").append(componentType).append('[').append(componentID).append(']').append(" is mistakenly configurated")
                                .append('\n').append("root cause from erroneous debug.color value[").append(colorDef).append(']')
                                .append('\n').append("correct value should be like [255,255,0]");
                        CommonUtil.showBlockedErrorMsg(null, errMsg.toString(), true);
                    }
                    component.setBorder(new LineBorder(color, 2));
                } else {
                    component.setBorder(new LineBorder(Color.RED, 2));
                }
            }
        }
    }

    private void initActions() {
        for (Panel panel : panelNodes.keySet()) {
            String pageURI = panelNodes.get(panel);
            List<Component> components = panel.getComponent();
            for (Component component : components) {
                validateActionConfig(component, panel, pageURI);
                setupAction(component, panel);
            }

            List<Decorator> decorators = panel.getDecorator();
            for (Decorator decorator : decorators) {
                validateActionConfig(decorator.getComponent(), panel, pageURI);
                setupAction(decorator.getComponent(), panel);
            }
        }

        for (Menuitem item : menuNodes) {
            Menuaction action = item.getAction();
            JumpAction jumpAction = action.getJumpPanelAction();
            if (jumpAction != null) {
                DefaultMenuAction menuJumpAction = new DefaultMenuAction(jumpAction.getNextPanel());
                doAction(menuJumpAction, item.getId());
            }

            doDefinedAction(action.getCustomizedAction(), item.getId());
        }
    }

    private void validateActionConfig(Component component, Panel panel, String pageURI) throws HeadlessException {
        List<king.flow.view.Action> actions = component.getAction();
        for (king.flow.view.Action action : actions) {
            validateJumpAction(action.getJumpPanelAction(), component, panel, pageURI);

            validateDefinedAction(action.getCustomizedAction(), component, panel, pageURI);

            validateSetFontAction(action.getSetFontAction(), component, panel, pageURI);

            validateShowTableAction(action.getShowTableAction(), component, panel, pageURI);

            validateShowComboBoxAction(action.getShowComboBoxAction(), component, panel, pageURI);

            validateSendMsgAction(action.getSendMsgAction(), component, panel, pageURI);

            validateCleanAction(action.getCleanAction(), component, panel, pageURI);

            validateHideAction(action.getHideAction(), component, panel, pageURI);

            validateLimitInputAction(action.getLimitInputAction(), component, panel, pageURI);

            validatePlayMediaAction(action.getPlayMediaAction(), component, panel, pageURI);

            validateVirtualKeyboardAction(action.getVirtualKeyboardAction(), component, panel, pageURI);

            validateLoadWebAction(action.getWebLoadAction(), component, panel, pageURI);

            validateRunCommandAction(action.getRunCommandAction(), component, panel, pageURI);

            validateSetPrinterAction(action.getSetPrinterAction(), component, panel, pageURI);

            validateUseTipAction(action.getUseTipAction(), component, panel, pageURI);

            validateInsertICardAction(action.getInsertICardAction(), component, panel, pageURI);

            validateWriteICardAction(action.getWriteICardAction(), component, panel, pageURI);

            validateBalanceTransAction(action.getBalanceTransAction(), component, panel, pageURI);

            validateUploadFileAction(action.getUploadFileAction(), component, panel, pageURI);

            validateMoveCursorAction(action.getMoveCursorAction(), component, panel, pageURI);

            validateSwipeCardAction(action.getSwipeCardAction(), component, panel, pageURI);

            validateReadWriteFingerPrintAction(action.getRwFingerPrintAction(), component, panel, pageURI);

            validateOpenBroswerAction(action.getOpenBrowserAction(), component, panel, pageURI);

            validateSwipe2In1CardAction(action.getSwipe2In1CardAction(), component, panel, pageURI);

            validateEjectCardAction(action.getEjectCardAction(), component, panel, pageURI);

            validateWithdrawCardAction(action.getWithdrawCardAction(), component, panel, pageURI);

            validatePrintPassbookAction(action.getPrintPassbookAction(), component, panel, pageURI);

            validatePlayVideoAction(action.getPlayVideoAction(), component, panel, pageURI);

            validateShowClockAction(action.getShowClockAction(), component, panel, pageURI);

            validateEncryptKeyboardAction(action.getEncryptKeyboardAction(), component, panel, pageURI);

            validateShowGridAction(action.getShowGridAction(), component, panel, pageURI);

            validateTypeNumericPadAction(action.getNumericPadAction(), component, panel, pageURI);
        }
    }

    private void validateTypeNumericPadAction(NumericPadAction typePadAction,
            Component component, Panel panel, String pageURI) {
        if (typePadAction != null) {
            final String propertyName = "targetId";
            checkSupportedAction(component, TYPE_NUMERIC_PAD_ACTION, panel, pageURI);
            int targetId = typePadAction.getTargetId();

            if (!this.meta_blocks.containsKey(targetId)) {
                promptNonexistentBlockErr(targetId, TYPE_NUMERIC_PAD_ACTION, propertyName, component, panel, pageURI, null);
            }
            checkComponentType(targetId, TYPE_NUMERIC_PAD_ACTION, propertyName, component, panel, pageURI);
            final ComponentEnum type = ((Component) this.meta_blocks.get(targetId)).getType();
            //validate supported component type config
            ImmutableSet<String> supportedTypeSet = new ImmutableSet.Builder<String>()
                    .add(ComponentEnum.TEXT_FIELD.value())
                    .add(ComponentEnum.PASSWORD_FIELD.value())
                    .build();
            String typeTips = Joiner.on(",\n").join(supportedTypeSet);
            if (!supportedTypeSet.contains(type.value())) {
                String configErrMsgFooter = String.format("\n [%s] type cannot be supported here,\nonly for [%s]",
                        type, typeTips);
                promptMistakenTypeBlockErr(targetId, TYPE_NUMERIC_PAD_ACTION, propertyName,
                        component, panel, pageURI, configErrMsgFooter);
            }
        }
    }

    private void validateShowGridAction(ShowGridAction showGridAction,
            Component component, Panel panel, String pageURI) {
        if (showGridAction != null) {
            checkSupportedAction(component, SHOW_GRID_ACTION, panel, pageURI);
        }
    }

    private void validateJumpAction(JumpAction jumpPanelAction, Component component,
            Panel panel, String pageURI) throws HeadlessException {
        if (jumpPanelAction != null) {
            checkSupportedAction(component, JUMP_ACTION, panel, pageURI);
            String propertyName = "nextPanel";
            checkNextPanelParameter(jumpPanelAction.getNextPanel(), JUMP_ACTION,
                    propertyName, component, panel, pageURI);
            checkNextCursorParameter(jumpPanelAction.getNextCursor(), JUMP_ACTION, component, panel, pageURI);
            checkNextTriggerParameter(jumpPanelAction.getTrigger(), JUMP_ACTION, component, panel, pageURI);
        }
    }

    private void validateEncryptKeyboardAction(king.flow.view.Action.EncryptKeyboardAction encryptKeyboardAction,
            Component component, Panel panel, String pageURI) {
        if (encryptKeyboardAction != null) {
            checkSupportedAction(component, ENCRYPT_KEYBORAD_ACTION, panel, pageURI);

            int moneyId = encryptKeyboardAction.getMoneyId();
            final String moneyIdProperty = "moneyId";
            if (!this.meta_blocks.containsKey(moneyId)) {
                promptNonexistentBlockErr(moneyId, ENCRYPT_KEYBORAD_ACTION,
                        moneyIdProperty, component, panel, pageURI, null);
            } else {
                checkComponentType(moneyId, ENCRYPT_KEYBORAD_ACTION,
                        moneyIdProperty, component, panel, pageURI);
            }

            Integer nextTrigger = encryptKeyboardAction.getNextTrigger();
            final String nextTriggerProperty = "nextTrigger";
            if (nextTrigger != null) {
                if (!this.meta_blocks.containsKey(nextTrigger)) {
                    promptNonexistentBlockErr(nextTrigger, ENCRYPT_KEYBORAD_ACTION,
                            nextTriggerProperty, component, panel, pageURI, null);
                } else {
                    checkComponentType(nextTrigger, ENCRYPT_KEYBORAD_ACTION,
                            nextTriggerProperty, component, panel, pageURI);
                }
            }

            Integer cancelTrigger = encryptKeyboardAction.getCancelTrigger();
            final String cancelTriggerProperty = "cancelTrigger";
            if (cancelTrigger != null) {
                if (!this.meta_blocks.containsKey(cancelTrigger)) {
                    promptNonexistentBlockErr(cancelTrigger, ENCRYPT_KEYBORAD_ACTION,
                            cancelTriggerProperty, component, panel, pageURI, null);
                } else {
                    checkComponentType(cancelTrigger, ENCRYPT_KEYBORAD_ACTION,
                            cancelTriggerProperty, component, panel, pageURI);
                }
            }
        }
    }

    private void validateDefinedAction(List<DefinedAction> definedActions,
            Component component, Panel panel, String pageURI) {
        if (definedActions != null && !definedActions.isEmpty()) {
            for (DefinedAction definedAction : definedActions) {
                String className = (definedAction.getClassName() == null || definedAction.getClassName().isEmpty())
                        ? "[empty name]" : definedAction.getClassName();
                try {
                    Class.forName(className);
                } catch (ClassNotFoundException ex) {
                    String configErrMsgFooter = "\nCannot load " + className;
                    promptUnfoundClassBlockErr(component.getId(), "customizedAction",
                            "className", component, panel, pageURI, configErrMsgFooter);
                }
            }
        }
    }

    private void validateShowComboBoxAction(king.flow.view.Action.ShowComboBoxAction showComboBoxAction,
            Component component, Panel panel, String pageURI) {
        if (showComboBoxAction != null) {
            checkSupportedAction(component, SHOW_COMBOBOX_ACTION, panel, pageURI);
            String items = showComboBoxAction.getItems();
            if (items == null || !items.matches(CommonConstants.COMBOBOX_ITEMS_PROPERTY_PATTERN)) {
                String configErrMsgFooter = "\nCorrect format is [key/value, ... ,key/value]";
                promptMistakenFormatBlockErr(component.getId(), SHOW_COMBOBOX_ACTION,
                        "items", component, panel, pageURI, configErrMsgFooter);
            }
        }
    }

    private void validateMoveCursorAction(king.flow.view.Action.MoveCursorAction moveCursorAction,
            Component component, Panel panel, String pageURI) throws HeadlessException {
        if (moveCursorAction != null) {
            checkSupportedAction(component, MOVE_CURSOR_ACTION, panel, pageURI);
            int upCursor = moveCursorAction.getUpCursor();
            int downCursor = moveCursorAction.getDownCursor();

            final String upCursorProperty = "upCursor";
            if (!this.meta_blocks.containsKey(upCursor)) {
                promptNonexistentBlockErr(upCursor, MOVE_CURSOR_ACTION,
                        upCursorProperty, component, panel, pageURI, null);
            } else {
                checkReachableBlock(upCursor, MOVE_CURSOR_ACTION,
                        upCursorProperty, component, panel, pageURI);
            }

            final String downCursorProperty = "downCursor";
            if (!this.meta_blocks.containsKey(downCursor)) {
                promptNonexistentBlockErr(downCursor, MOVE_CURSOR_ACTION,
                        downCursorProperty, component, panel, pageURI, null);
            } else {
                checkReachableBlock(upCursor, MOVE_CURSOR_ACTION,
                        downCursorProperty, component, panel, pageURI);
            }
        }
    }

    private void validateSendMsgAction(MsgSendAction sendMsgAction,
            Component component, Panel panel, String pageURI) throws HeadlessException {
        if (sendMsgAction != null) {
            checkSupportedAction(component, SEND_MSG_ACTION, panel, pageURI);
            //validate parameter config concerning data collection from components 
            checkConditionsParameter(sendMsgAction.getConditions(), SEND_MSG_ACTION, "conditions",
                    component, panel, pageURI);

            //validate successful path parameter config
            if (sendMsgAction.getNextStep() != null) {
                final String nextStepPropertyName = sendMsgAction.getNextStep().getClass().getSimpleName();
                checkNextPanelParameter(sendMsgAction.getNextStep().getNextPanel(), SEND_MSG_ACTION, nextStepPropertyName,
                        component, panel, pageURI);

                ArrayList<String> displayParameters = buildListParameters(sendMsgAction.getNextStep().getDisplay());
                for (String displayId : displayParameters) {
                    try {
                        int id = Integer.parseInt(displayId);
                        checkDisplayParameter(id, SEND_MSG_ACTION, nextStepPropertyName, component, panel, pageURI);
                        checkComponentType(id, SEND_MSG_ACTION, nextStepPropertyName, component, panel, pageURI);
                    } catch (Exception e) {
                        promptMistakenNumberBlockErr(displayId, SEND_MSG_ACTION, nextStepPropertyName,
                                component, panel, pageURI, null);
                    }
                }

                checkNextCursorParameter(sendMsgAction.getNextStep().getNextCursor(),
                        SEND_MSG_ACTION, component, panel, pageURI);
                checkNextTriggerParameter(sendMsgAction.getNextStep().getTrigger(),
                        SEND_MSG_ACTION, component, panel, pageURI);
            } else {
                //successful next step must be set
                promptIncompleteActionBlockErr(SEND_MSG_ACTION, component, panel, pageURI, "[nextStep]");
            }

            //validate exceptional path parameter config
            if (sendMsgAction.getException() != null) {
                final String exceptionPropertyName = sendMsgAction.getException().getClass().getSimpleName();
                checkNextPanelParameter(sendMsgAction.getException().getNextPanel(), SEND_MSG_ACTION, exceptionPropertyName,
                        component, panel, pageURI);

                ArrayList<String> displayParameters = buildListParameters(sendMsgAction.getException().getDisplay());
                for (String displayId : displayParameters) {
                    try {
                        int id = Integer.parseInt(displayId);
                        checkDisplayParameter(id, SEND_MSG_ACTION, exceptionPropertyName, component, panel, pageURI);
                        checkComponentType(id, SEND_MSG_ACTION, exceptionPropertyName, component, panel, pageURI);
                    } catch (Exception e) {
                        promptMistakenNumberBlockErr(displayId, SEND_MSG_ACTION, exceptionPropertyName,
                                component, panel, pageURI, null);
                    }
                }
            } else {
                //exception next step must be set
                promptIncompleteActionBlockErr(SEND_MSG_ACTION, component, panel, pageURI, "[exception]");
            }

            //validate rules path parameter config
            if (sendMsgAction.getCheckRules() != null) {
                final String checkRulesPropertyName = sendMsgAction.getCheckRules().getClass().getSimpleName();
                checkRulesParameter(sendMsgAction.getCheckRules(), SEND_MSG_ACTION,
                        checkRulesPropertyName, component, panel, pageURI);
            }
        }
    }

    private void validateSetFontAction(king.flow.view.Action.SetFontAction setFontAction,
            Component component, Panel panel, String pageURI) {
        if (setFontAction != null) {
            checkSupportedAction(component, SET_FONT_ACTION, panel, pageURI);
            final String correctionTips = "\ncorrect value should be like [255,255,0]";
            if (setFontAction.getFontColor() != null) {
                Color fontColor = CommonUtil.getTrueColor(setFontAction.getFontColor());
                if (fontColor == null) {
                    promptMistakenFormatBlockErr(component.getId(), SET_FONT_ACTION, "fontColor",
                            component, panel, pageURI, correctionTips);
                }
            }

            if (setFontAction.getFontBgColor() != null) {
                Color backgroundColor = CommonUtil.getTrueColor(setFontAction.getFontBgColor());
                if (backgroundColor == null) {
                    promptMistakenFormatBlockErr(component.getId(), SET_FONT_ACTION, "fontBgColor",
                            component, panel, pageURI, correctionTips);
                }
            }
        }
    }

    private void validateShowTableAction(king.flow.view.Action.ShowTableAction showTableAction,
            Component component, Panel panel, String pageURI) {
        if (showTableAction != null) {
            checkSupportedAction(component, SHOW_TABLE_ACTION, panel, pageURI);
            ArrayList<String> colsNames = buildListParameters(showTableAction.getColumnNames());
            if (colsNames.isEmpty()) {
                String configErrMsgFooter = "\nCorrect format is [name, ... ,name]";
                promptMistakenFormatBlockErr(component.getId(), SHOW_TABLE_ACTION,
                        "columnName", component, panel, pageURI, configErrMsgFooter);
            }
            List<king.flow.view.Action.ShowTableAction.Sum> sumFunctions = showTableAction.getSum();
            if (!sumFunctions.isEmpty()) {
                //validate card type config
                ImmutableSet<String> validTypeSet = new ImmutableSet.Builder<String>()
                        .add(ComponentEnum.LABEL.value())
                        .add(ComponentEnum.TEXT_FIELD.value())
                        .build();
                String typeTips = Joiner.on(",\n").join(validTypeSet);
                final String invalidTypeTip = "\nvalid type includes:\n["
                        + typeTips + "]";

                for (king.flow.view.Action.ShowTableAction.Sum sum : sumFunctions) {
                    if (!colsNames.contains(sum.getColumnName())) {
                        String configErrMsg = buildConfigErrMsgHeader(component, panel, pageURI)
                                .append(SHOW_TABLE_ACTION).append('\n')
                                .append("with nonexistent ").append("columnName").append('[').append(sum.getColumnName()).append(']')
                                .append("\nfor column sum function").toString();
                        CommonUtil.showBlockedErrorMsg(null, configErrMsg, true);
                    }

                    final Object displayBlockMeta = getBlockMeta(sum.getDisplay());
                    if (displayBlockMeta == null) {
                        promptNonexistentBlockErr(sum.getDisplay(), SHOW_TABLE_ACTION,
                                "display", component, panel, pageURI, null);
                    }

                    if (!(displayBlockMeta instanceof Component)
                            || !validTypeSet.contains(((Component) displayBlockMeta).getType().value())) {
                        String configErrMsg = buildConfigErrMsgHeader(component, panel, pageURI)
                                .append(SHOW_TABLE_ACTION).append('\n')
                                .append("with invalid property display").append('[').append(sum.getDisplay()).append(']')
                                .append(invalidTypeTip).toString();
                        CommonUtil.showBlockedErrorMsg(null, configErrMsg, true);
                    }
                }
            }
        }
    }

    private void validateLimitInputAction(king.flow.view.Action.LimitInputAction limitInputAction,
            Component component, Panel panel, String pageURI) {
        if (limitInputAction != null) {
            checkSupportedAction(component, LIMIT_INPUT_ACTION, panel, pageURI);
        }
    }

    private void validatePlayMediaAction(king.flow.view.Action.PlayMediaAction playMediaAction,
            Component component, Panel panel, String pageURI) {
        if (playMediaAction != null) {
            checkSupportedAction(component, PLAY_MEDIA_ACTION, panel, pageURI);
        }
    }

    private void validateVirtualKeyboardAction(king.flow.view.Action.VirtualKeyboardAction virtualKeyboardAction,
            Component component, Panel panel, String pageURI) {
        if (virtualKeyboardAction != null) {
            checkSupportedAction(component, OPEN_VIRTUAL_KEYBOARD_ACTION, panel, pageURI);
        }
    }

    private void validateRunCommandAction(king.flow.view.Action.RunCommandAction runCommandAction,
            Component component, Panel panel, String pageURI) {
        if (runCommandAction != null) {
            checkSupportedAction(component, RUN_COMMAND_ACTION, panel, pageURI);
        }
    }

    private void validateSetPrinterAction(king.flow.view.Action.SetPrinterAction setPrinterAction,
            Component component, Panel panel, String pageURI) {
        if (setPrinterAction != null) {
            checkSupportedAction(component, PRINT_RECEIPT_ACTION, panel, pageURI);
        }
    }

    private void validateUseTipAction(king.flow.view.Action.UseTipAction useTipAction, Component component,
            Panel panel, String pageURI) {
        if (useTipAction != null) {
            checkSupportedAction(component, USE_TIP_ACTION, panel, pageURI);
        }
    }

    private void validateInsertICardAction(king.flow.view.Action.InsertICardAction insertICardAction,
            Component component, Panel panel, String pageURI) {
        if (insertICardAction != null) {
            checkSupportedAction(component, INSERT_IC_ACTION, panel, pageURI);

            //validate card type config
            ImmutableSet<String> validTypeSet = new ImmutableSet.Builder<String>()
                    .add(DeviceEnum.GZ_CARD.value())
                    .add(DeviceEnum.CASH_SAVER.value())
                    .add(DeviceEnum.HIS_CARD.value())
                    .add(DeviceEnum.PID_CARD.value())
                    .add(DeviceEnum.PATIENT_CARD.value())
                    .add(DeviceEnum.MEDICARE_CARD.value())
                    .build();
            String typeTips = Joiner.on(",\n").join(validTypeSet);
            String invalidTypeTip = "\nvalid type includes:\n["
                    + typeTips + "]";
            if (insertICardAction.getCardType() == null) {
                promptIncompleteActionBlockErr(INSERT_IC_ACTION, component, panel, pageURI, invalidTypeTip);
            } else if (!validTypeSet.contains(insertICardAction.getCardType().value())) {
                promptMistakenTypeBlockErr(component.getId(), INSERT_IC_ACTION, "cardType",
                        component, panel, pageURI, invalidTypeTip);
            } else if (!winNode.getDriver().getDevice()
                    .stream()
                    .anyMatch(
                            (Driver.Device device) -> device.getType() == insertICardAction.getCardType())) {
                invalidTypeTip = String.format("\nNo device driver config set for cardType [%s]", insertICardAction.getCardType().value());
                promptMistakenTypeBlockErr(component.getId(), INSERT_IC_ACTION, "cardType",
                        component, panel, pageURI, invalidTypeTip);
            }

            //validate card type config
            ImmutableSet<String> validCompTypeSet = new ImmutableSet.Builder<String>()
                    .add(ComponentEnum.TEXT_FIELD.value())
                    .add(ComponentEnum.LABEL.value())
                    .build();
            if (insertICardAction.getCardType() == DeviceEnum.CASH_SAVER) {
                List<String> parameters = insertICardAction.getParameters();
                String propertyName = "parameters";
                if (parameters.size() != 1) {
                    String configErrMsgFooter = "[" + propertyName + "]" + " property \nfor " + DeviceEnum.CASH_SAVER.value();
                    promptIncompleteActionBlockErr(INSERT_IC_ACTION, component, panel, pageURI, configErrMsgFooter);
                } else {
                    int cardNumCompId = Integer.MIN_VALUE;
                    try {
                        cardNumCompId = Integer.parseInt(parameters.get(0));
                    } catch (Exception e) {
                        promptMistakenNumberBlockErr(parameters.get(0), INSERT_IC_ACTION,
                                propertyName, component, panel, pageURI, "");
                    }
                    if (!this.meta_blocks.containsKey(cardNumCompId)) {
                        promptNonexistentBlockErr(cardNumCompId, INSERT_IC_ACTION,
                                propertyName, component, panel, pageURI, "");
                    }
                    checkComponentType(cardNumCompId, INSERT_IC_ACTION, propertyName, component, panel, pageURI);
                    final ComponentEnum type = ((Component) this.meta_blocks.get(cardNumCompId)).getType();
                    if (!validCompTypeSet.contains(type.value())) {
                        String configErrMsgFooter = String.format("\n [%s] type cannot be supported here,\nonly for [%s, %s]",
                                type, ComponentEnum.TEXT_FIELD, ComponentEnum.LABEL);
                        promptMistakenTypeBlockErr(cardNumCompId, INSERT_IC_ACTION, propertyName,
                                component, panel, pageURI, configErrMsgFooter);
                    }
                }
            }

            if (insertICardAction.getCardType() == DeviceEnum.MEDICARE_CARD) {
                List<String> parameters = insertICardAction.getParameters();
                String propertyName = "parameters";
                if (parameters.size() < 1) {
                    String configErrMsgFooter = "[" + propertyName + "]" + " property \nfor " + DeviceEnum.MEDICARE_CARD.value();
                    promptIncompleteActionBlockErr(INSERT_IC_ACTION, component, panel, pageURI, configErrMsgFooter);
                } else {
                    for (String param : parameters) {
                        int paramCompId = Integer.MIN_VALUE;
                        try {
                            paramCompId = Integer.parseInt(param);
                        } catch (Exception e) {
                            promptMistakenNumberBlockErr(param, INSERT_IC_ACTION,
                                    propertyName, component, panel, pageURI, "");
                        }
                        if (!this.meta_blocks.containsKey(paramCompId)) {
                            promptNonexistentBlockErr(paramCompId, INSERT_IC_ACTION,
                                    propertyName, component, panel, pageURI, "");
                        }
                        checkComponentType(paramCompId, INSERT_IC_ACTION, propertyName, component, panel, pageURI);
                        final ComponentEnum type = ((Component) this.meta_blocks.get(paramCompId)).getType();
                        if (!validCompTypeSet.contains(type.value())) {
                            String configErrMsgFooter = String.format("\n [%s] type cannot be supported here,\nonly for [%s, %s]",
                                    type, ComponentEnum.TEXT_FIELD, ComponentEnum.LABEL);
                            promptMistakenTypeBlockErr(paramCompId, INSERT_IC_ACTION, propertyName,
                                    component, panel, pageURI, configErrMsgFooter);
                        }
                    }
                }
            }

            //validate successful path parameter config
            if (insertICardAction.getNextStep() != null) {
                final String nextStepPropertyName = insertICardAction.getNextStep().getClass().getSimpleName();
                checkNextPanelParameter(insertICardAction.getNextStep().getNextPanel(), INSERT_IC_ACTION, nextStepPropertyName,
                        component, panel, pageURI);

                ArrayList<String> displayParameters = buildListParameters(insertICardAction.getNextStep().getDisplay());
                for (String displayId : displayParameters) {
                    try {
                        int id = Integer.parseInt(displayId);
                        checkDisplayParameter(id, INSERT_IC_ACTION, nextStepPropertyName, component, panel, pageURI);
                        checkComponentType(id, INSERT_IC_ACTION, nextStepPropertyName, component, panel, pageURI);
                    } catch (Exception e) {
                        promptMistakenNumberBlockErr(displayId, INSERT_IC_ACTION, nextStepPropertyName,
                                component, panel, pageURI, null);
                    }
                }

                Integer trigger = insertICardAction.getNextStep().getTrigger();
                if (trigger != null) {
                    if (!this.meta_blocks.containsKey(trigger)) {
                        promptNonexistentBlockErr(trigger, INSERT_IC_ACTION, "trigger", component, panel, pageURI, null);
                    }

                    Object blockMeta = getBlockMeta(trigger);
                    if (!(blockMeta instanceof Component)
                            || ((Component) blockMeta).getType() != ComponentEnum.BUTTON) {
                        promptMistakenTypeBlockErr(trigger, INSERT_IC_ACTION, nextStepPropertyName,
                                component, panel, pageURI, "\ncorrect <trigger> type should be [BUTTON]");
                    }
                }
            } else {
                //successful next step must be set
                promptIncompleteActionBlockErr(INSERT_IC_ACTION, component, panel, pageURI, "[nextStep]");
            }

            //validate exceptional path parameter config
            if (insertICardAction.getException() != null) {
                final String exceptionPropertyName = insertICardAction.getException().getClass().getSimpleName();
                checkNextPanelParameter(insertICardAction.getException().getNextPanel(), INSERT_IC_ACTION, exceptionPropertyName,
                        component, panel, pageURI);

                ArrayList<String> displayParameters = buildListParameters(insertICardAction.getException().getDisplay());
                for (String displayId : displayParameters) {
                    try {
                        int id = Integer.parseInt(displayId);
                        checkDisplayParameter(id, INSERT_IC_ACTION, exceptionPropertyName, component, panel, pageURI);
                        checkComponentType(id, INSERT_IC_ACTION, exceptionPropertyName, component, panel, pageURI);
                    } catch (Exception e) {
                        promptMistakenNumberBlockErr(displayId, INSERT_IC_ACTION, exceptionPropertyName,
                                component, panel, pageURI, null);
                    }
                }
                
                Integer trigger = insertICardAction.getException().getTrigger();
                if (trigger != null) {
                    if (!this.meta_blocks.containsKey(trigger)) {
                        promptNonexistentBlockErr(trigger, INSERT_IC_ACTION, "trigger", component, panel, pageURI, null);
                    }

                    Object blockMeta = getBlockMeta(trigger);
                    if (!(blockMeta instanceof Component)
                            || ((Component) blockMeta).getType() != ComponentEnum.BUTTON) {
                        promptMistakenTypeBlockErr(trigger, INSERT_IC_ACTION, exceptionPropertyName,
                                component, panel, pageURI, "\ncorrect <trigger> type should be [BUTTON]");
                    }
                }
            } else {
                //exception next step must be set
                promptIncompleteActionBlockErr(INSERT_IC_ACTION, component, panel, pageURI, "[exception]");
            }
        }
    }

    private void validateWriteICardAction(king.flow.view.Action.WriteICardAction writeICardAction, Component component,
            Panel panel, String pageURI) {
        if (writeICardAction != null) {
            checkSupportedAction(component, WRITE_IC_ACTION, panel, pageURI);
            //validate card type config
            if (writeICardAction.getCardType() == null) {
                final String valid_type = "[cardType] configuration\n"
                        + "valid type includes["
                        + DeviceEnum.GZ_CARD.value()
                        + "]";
                promptIncompleteActionBlockErr(WRITE_IC_ACTION, component, panel, pageURI, valid_type);
            }
            if (writeICardAction.getCardAction() == null) {
                final String valid_type = "[cardAction] configuration";
                promptIncompleteActionBlockErr(WRITE_IC_ACTION, component, panel, pageURI, valid_type);
            }
            validateSendMsgAction(writeICardAction.getCardAction(), component, panel, pageURI);
        }
    }

    private void validateBalanceTransAction(MsgSendAction balanceTransAction, Component component,
            Panel panel, String pageURI) {
        if (balanceTransAction != null) {
            checkSupportedAction(component, BALANCE_TRANS_ACTION, panel, pageURI);
            validateSendMsgAction(balanceTransAction, component, panel, pageURI);
        }
    }

    private void validateUploadFileAction(king.flow.view.Action.UploadFileAction uploadFileAction,
            Component component, Panel panel, String pageURI) {
        if (uploadFileAction != null) {
            checkSupportedAction(component, UPLOAD_FILE_ACTION, panel, pageURI);
        }
    }

    private void validateSwipeCardAction(king.flow.view.Action.SwipeCardAction swipeCardAction,
            Component component, Panel panel, String pageURI) {
        if (swipeCardAction != null) {
            checkSupportedAction(component, SWIPE_CARD_ACTION, panel, pageURI);
        }
    }

    private void validateReadWriteFingerPrintAction(king.flow.view.Action.RwFingerPrintAction rwFingerPrintAction,
            Component component, Panel panel, String pageURI) {
        if (rwFingerPrintAction != null) {
            checkSupportedAction(component, READ_WRITE_FINGERPRINT_ACTION, panel, pageURI);
        }
    }

    private void validateOpenBroswerAction(king.flow.view.Action.OpenBrowserAction openBrowserAction,
            Component component, Panel panel, String pageURI) {
        if (openBrowserAction != null) {
            checkSupportedAction(component, OPEN_BROWSER_ACTION, panel, pageURI);
        }
    }

    private void validateSwipe2In1CardAction(king.flow.view.Action.Swipe2In1CardAction swipe2In1CardAction,
            Component component, Panel panel, String pageURI) {
        if (swipe2In1CardAction != null) {
            checkSupportedAction(component, SWIPE_TWO_IN_ONE_CARD_ACTION, panel, pageURI);
        }
    }

    private void validateEjectCardAction(king.flow.view.Action.EjectCardAction ejectCardAction,
            Component component, Panel panel, String pageURI) {
        if (ejectCardAction != null) {
            checkSupportedAction(component, EJECT_CARD_ACTION, panel, pageURI);
            //validate card type config
            ImmutableSet<String> validTypeSet = new ImmutableSet.Builder<String>()
                    .add(DeviceEnum.CASH_SAVER.value())
                    .add(DeviceEnum.HIS_CARD.value())
                    .add(DeviceEnum.TWO_IN_ONE_CARD.value())
                    .build();
            String typeTips = Joiner.on(",\n").join(validTypeSet);
            final String invalidTypeTip = "\nvalid type includes:\n["
                    + typeTips + "]";
            if (ejectCardAction.getCardType() == null) {
                promptIncompleteActionBlockErr(EJECT_CARD_ACTION, component, panel, pageURI, invalidTypeTip);
            } else if (!validTypeSet.contains(ejectCardAction.getCardType().value())) {
                promptMistakenTypeBlockErr(component.getId(), EJECT_CARD_ACTION, "cardType",
                        component, panel, pageURI, invalidTypeTip);
            }
        }
    }

    private void validateWithdrawCardAction(king.flow.view.Action.WithdrawCardAction withdrawCardAction,
            Component component, Panel panel, String pageURI) {
        if (withdrawCardAction != null) {
            checkSupportedAction(component, WITHDRAW_CARD_ACTION, panel, pageURI);
            //validate card type config
            ImmutableSet<String> validTypeSet = new ImmutableSet.Builder<String>()
                    .add(DeviceEnum.HIS_CARD.value())
                    .build();
            String typeTips = Joiner.on(",\n").join(validTypeSet);
            final String invalidTypeTip = "\nvalid type includes:\n["
                    + typeTips + "]";
            if (withdrawCardAction.getCardType() == null) {
                promptIncompleteActionBlockErr(WITHDRAW_CARD_ACTION, component, panel, pageURI, invalidTypeTip);
            } else if (!validTypeSet.contains(withdrawCardAction.getCardType().value())) {
                promptMistakenTypeBlockErr(component.getId(), WITHDRAW_CARD_ACTION, "cardType",
                        component, panel, pageURI, invalidTypeTip);
            }
        }
    }

    private void validatePrintPassbookAction(king.flow.view.Action.PrintPassbookAction printPassbookAction,
            Component component, Panel panel, String pageURI) {
        if (printPassbookAction != null) {
            checkSupportedAction(component, PRINT_PASSBOOK_ACTION, panel, pageURI);
        }
    }

    private void validatePlayVideoAction(king.flow.view.Action.PlayVideoAction playVideoAction,
            Component component, Panel panel, String pageURI) {
        if (playVideoAction != null) {
            checkSupportedAction(component, PLAY_VIDEO_ACTION, panel, pageURI);
        }
    }

    private void validateShowClockAction(king.flow.view.Action.ShowClockAction showClockAction,
            Component component, Panel panel, String pageURI) {
        if (showClockAction != null) {
            checkSupportedAction(component, SHOW_CLOCK_ACTION, panel, pageURI);
        }
    }

    private void validateLoadWebAction(king.flow.view.Action.WebLoadAction webLoadAction,
            Component component, Panel panel, String pageURI) {
        if (webLoadAction != null) {
            checkSupportedAction(component, WEB_LOAD_ACTION, panel, pageURI);
        }
    }

    private void validateCleanAction(king.flow.view.Action.CleanAction cleanAction, Component component,
            Panel panel, String pageURI) throws HeadlessException {
        if (cleanAction != null) {
            checkSupportedAction(component, CLEAN_ACTION, panel, pageURI);
            checkConditionsParameter(cleanAction.getConditions(), CLEAN_ACTION, "conditions",
                    component, panel, pageURI);
        }
    }

    private void validateHideAction(king.flow.view.Action.HideAction hideAction, Component component,
            Panel panel, String pageURI) throws HeadlessException {
        if (hideAction != null) {
            checkSupportedAction(component, HIDE_ACTION, panel, pageURI);
        }
    }

    public void checkReachableBlock(int blockId, String actionName, String propertyName,
            Component component, Panel panel, String pageURI) throws HeadlessException {
        List<Component> pageComponents = panel.getComponent();
        boolean existInPanel = false;
        for (Component cp : pageComponents) {
            if (cp.getId() == blockId) {
                existInPanel = true;
                break;
            }
        }
        if (!existInPanel) {
            String configErrMsgFooter = " of " + propertyName + " property";
            String configErrMsg = buildConfigErrMsgHeader(component, panel, pageURI)
                    .append(actionName).append('\n')
                    .append("with unreachable ").append(propertyName).append('[').append(blockId).append(']')
                    .append(configErrMsgFooter == null ? "" : configErrMsgFooter).toString();
            CommonUtil.showBlockedErrorMsg(null, configErrMsg, true);
        }
    }

    private void checkComponentType(int id, final String actionName, final String propertyName,
            Component component, Panel panel, String pageURI) throws HeadlessException {
        Object meta = meta_blocks.get(id);
        if (!(meta instanceof Component)) {
            String configErrMsgFooter = String.format("\ncorrect type must be [Component] not [%s] type",
                    meta.getClass().getSimpleName());
            promptMistakenTypeBlockErr(id, actionName, propertyName,
                    component, panel, pageURI, configErrMsgFooter);
        }
    }

    private void checkTriggerType(int id, final String actionName, final String propertyName,
            Component component, Panel panel, String pageURI) throws HeadlessException {
        checkComponentType(id, actionName, propertyName, component, panel, pageURI);
        Object meta = meta_blocks.get(id);
        Component trigger = (Component) meta;
        switch (trigger.getType()) {
            case BUTTON:
            case COMBO_BOX:
                break;
            default:
                String configErrMsgFooter = String.format("\n [%s] type cannot be supported here,\nonly for [%s, %s]",
                        trigger.getType(), ComponentEnum.BUTTON, ComponentEnum.COMBO_BOX);
                promptMistakenTypeBlockErr(id, actionName, propertyName,
                        component, panel, pageURI, configErrMsgFooter);
        }
    }

    public void checkRulesParameter(Rules rules, String actionName, String propertyName, Component component,
            Panel panel, String pageURI) {
        if (rules != null) {
            String configErrMsgFooter = " of " + propertyName + " property";
            List<Rules.Equal> equalRules = rules.getEqual();
            if (equalRules != null && !equalRules.isEmpty()) {
                for (Rules.Equal equal : equalRules) {
                    checkConditionsParameter(equal.getConditions(), actionName, propertyName, component, panel, pageURI);
                }
            }

            List<Rules.NotEqual> notEqualRules = rules.getNotEqual();
            if (notEqualRules != null && !notEqualRules.isEmpty()) {
                for (Rules.NotEqual notEqual : notEqualRules) {
                    int less = notEqual.getLess();
                    if (this.meta_blocks.containsKey(less)) {
                        // As there is a special scenario, I have to remove reachable component-checking.
                        // because probably when you firstly query account in one page and secondly transfer in other page,
                        // you have to use an unreachable component in first page
                        //validateReachableBlock(less, actionName, notEqual.getClass().getSimpleName(), component, panel, pageURI);
                    } else {
                        promptNonexistentBlockErr(less, actionName, notEqual.getClass().getSimpleName(),
                                component, panel, pageURI, configErrMsgFooter);
                    }

                    int more = notEqual.getMore();
                    if (this.meta_blocks.containsKey(more)) {
                        // As there is a special scenario, I have to remove reachable component-checking.
                        // because probably when you firstly query account in one page and secondly transfer in other page,
                        // you have to use an unreachable component in first page
                        //validateReachableBlock(less, actionName, notEqual.getClass().getSimpleName(), component, panel, pageURI);
                    } else {
                        promptNonexistentBlockErr(more, actionName, notEqual.getClass().getSimpleName(),
                                component, panel, pageURI, configErrMsgFooter);
                    }
                }
            }

            List<Rules.NotNull> notNullRules = rules.getNotNull();
            if (notNullRules != null && !notNullRules.isEmpty()) {
                for (Rules.NotNull notNull : notNullRules) {
                    int content = notNull.getContent();
                    if (this.meta_blocks.containsKey(content)) {
                        // As there is a special scenario, I have to remove reachable component-checking.
                        // because probably when you firstly query account in one page and secondly transfer in other page,
                        // you have to use an unreachable component in first page
                        //validateReachableBlock(less, actionName, notEqual.getClass().getSimpleName(), component, panel, pageURI);
                    } else {
                        promptNonexistentBlockErr(content, actionName, notNull.getClass().getSimpleName(),
                                component, panel, pageURI, configErrMsgFooter);
                    }
                }
            }

            List<Rules.Template> templateRules = rules.getTemplate();
            if (templateRules != null && !templateRules.isEmpty()) {
                for (Rules.Template template : templateRules) {
                    int content = template.getContent();
                    if (this.meta_blocks.containsKey(content)) {
                        // As there is a special scenario, I have to remove reachable component-checking.
                        // because probably when you firstly query account in one page and secondly transfer in other page,
                        // you have to use an unreachable component in first page
                        //validateReachableBlock(less, actionName, notEqual.getClass().getSimpleName(), component, panel, pageURI);
                    } else {
                        promptNonexistentBlockErr(content, actionName, template.getClass().getSimpleName(),
                                component, panel, pageURI, configErrMsgFooter);
                    }
                }
            }

            List<Rules.ValidateCJK> validateCJKRules = rules.getValidateCJK();
            if (validateCJKRules != null && !validateCJKRules.isEmpty()) {
                for (Rules.ValidateCJK validateCJK : validateCJKRules) {
                    int content = validateCJK.getContent();
                    if (this.meta_blocks.containsKey(content)) {
                        checkReachableBlock(content, actionName, validateCJK.getClass().getSimpleName(),
                                component, panel, pageURI);
                    } else {
                        promptNonexistentBlockErr(content, actionName, validateCJK.getClass().getSimpleName(),
                                component, panel, pageURI, configErrMsgFooter);
                    }
                }
            }
        }
    }

    private void checkConditionsParameter(String conditions, String actionName, String propertyName, Component component,
            Panel panel, String pageURI) throws HeadlessException {
        String configErrMsg;
        String configErrMsgFooter;
        if (propertyName == null) {
            configErrMsgFooter = " for property[conditions]";
        } else {
            configErrMsgFooter = " for property[" + propertyName + "]";
        }

        if (conditions == null || conditions.trim().length() == 0) {
            configErrMsg = buildConfigErrMsgHeader(component, panel, pageURI)
                    .append(actionName).append('\n')
                    .append("with empty conditions").append(configErrMsgFooter).toString();
            CommonUtil.showBlockedErrorMsg(null, configErrMsg, true);
        }
        ArrayList<String> listParameters = buildListParameters(conditions);
        for (String cleanTargetId : listParameters) {
            int id = Integer.MIN_VALUE;
            try {
                id = Integer.parseInt(cleanTargetId);
            } catch (NumberFormatException numberFormatException) {
                configErrMsg = buildConfigErrMsgHeader(component, panel, pageURI)
                        .append(actionName).append('\n')
                        .append("with invalid conditions").append('[')
                        .append(cleanTargetId).append(']').append(configErrMsgFooter).toString();
                CommonUtil.showBlockedErrorMsg(null, configErrMsg, true);
            }
            if (!this.meta_blocks.containsKey(id)) {
                promptNonexistentBlockErr(id, actionName, "conditions", component, panel, pageURI, configErrMsgFooter);
            }
            checkComponentType(id, actionName, propertyName, component, panel, pageURI);
        }
    }

    private void checkDisplayParameter(int displayBlockId, String actionName, String propertyName,
            Component component, Panel panel, String pageURI) throws HeadlessException {
        Object showTarget = this.meta_blocks.get(displayBlockId);
        if (showTarget == null) {
            String configErrMsgFooter = " for property[" + propertyName + "]";
            promptNonexistentBlockErr(displayBlockId, actionName, "display", component, panel, pageURI, configErrMsgFooter);
        }
    }

    private void checkNextPanelParameter(int nextPanel, String actionName, String properyName,
            Component component, Panel panel, String pageURI) throws HeadlessException {
        String configErrMsg;
        String configErrMsgFooter;
        if (properyName == null) {
            configErrMsgFooter = " for property[nextPanel]";
        } else {
            configErrMsgFooter = " for property[" + properyName + "]";
        }

        Object np = this.meta_blocks.get(nextPanel);
        if (np == null) {
            promptNonexistentBlockErr(nextPanel, actionName, properyName, component, panel, pageURI, configErrMsgFooter);
        } else if (!(np instanceof Panel)) {
            String type = null;
            if (np instanceof Component) {
                type = ((Component) np).getType().toString();
            } else if (np instanceof king.flow.view.Window) {
                type = ((king.flow.view.Window) np).getType().toString();
            } else if (np instanceof Decorator) {
                type = ((Decorator) np).getType().toString();
            } else if (np instanceof Menuitem) {
                type = "MenuItem";
            } else if (np instanceof Menubar) {
                type = "MenuBar";
            } else if (np instanceof Menuoption) {
                type = "MenuOption";
            }
            configErrMsg = buildConfigErrMsgHeader(component, panel, pageURI)
                    .append(actionName).append('\n')
                    .append("with invalid panel type").append('[').append(type).append(']').append('\n')
                    .append("and value").append('[').append(nextPanel).append(']')
                    .append(configErrMsgFooter).toString();
            CommonUtil.showBlockedErrorMsg(null, configErrMsg, true);
        }
    }

    private void checkNextCursorParameter(Integer nextCursor, final String actionName,
            Component component, Panel panel, String pageURI) throws HeadlessException {
        String propertyName = "nextCursor";
        if (nextCursor != null) {
            if (!this.meta_blocks.containsKey(nextCursor)) {
                promptNonexistentBlockErr(nextCursor, actionName, propertyName, component, panel, pageURI, null);
            }
            checkComponentType(nextCursor, actionName, propertyName, component, panel, pageURI);
        }
    }

    private void checkNextTriggerParameter(Integer nextTrigger, final String actionName,
            Component component, Panel panel, String pageURI) throws HeadlessException {
        String propertyName = "trigger";
        if (nextTrigger != null) {
            if (!this.meta_blocks.containsKey(nextTrigger)) {
                promptNonexistentBlockErr(nextTrigger, actionName, propertyName, component, panel, pageURI, null);
            }
            checkTriggerType(nextTrigger, actionName, propertyName, component, panel, pageURI);
        }
    }

    private void checkSupportedAction(Component component, final String actionName,
            Panel panel, String pageURI) throws HeadlessException {
        if (!isActionSupport(component, actionName)) {
            promptUnsupportedActionBlockErr(actionName, component, panel, pageURI, null);
        }
    }

    private void promptNonexistentBlockErr(int blockId, String actionName, String propertyName,
            Component component, Panel panel, String pageURI, String configErrMsgFooter) throws HeadlessException {
        String configErrMsg = buildConfigErrMsgHeader(component, panel, pageURI)
                .append(actionName).append('\n')
                .append("with nonexistent ").append(propertyName).append('[').append(blockId).append(']')
                .append(configErrMsgFooter == null ? "" : configErrMsgFooter).toString();
        CommonUtil.showBlockedErrorMsg(null, configErrMsg, true);
    }

    private void promptMistakenTypeBlockErr(int blockId, String actionName, String propertyName,
            Component component, Panel panel, String pageURI, String configErrMsgFooter) throws HeadlessException {
        String configErrMsg = buildConfigErrMsgHeader(component, panel, pageURI)
                .append(actionName).append('\n')
                .append("with invalid type").append('[').append(blockId)
                .append(']').append(" for property[").append(propertyName).append(']')
                .append(' ').append(configErrMsgFooter == null ? "" : configErrMsgFooter).toString();
        CommonUtil.showBlockedErrorMsg(null, configErrMsg, true);
    }

    private void promptMistakenFormatBlockErr(int blockId, String actionName, String propertyName,
            Component component, Panel panel, String pageURI, String configErrMsgFooter) throws HeadlessException {
        String configErrMsg = buildConfigErrMsgHeader(component, panel, pageURI)
                .append(actionName).append('\n')
                .append("with erroneous ").append(propertyName).append('[')
                .append(blockId).append(']').append(" value format")
                .append(configErrMsgFooter == null ? "" : configErrMsgFooter).toString();
        CommonUtil.showBlockedErrorMsg(null, configErrMsg, true);
    }

    private void promptUnfoundClassBlockErr(int blockId, String actionName, String propertyName,
            Component component, Panel panel, String pageURI, String configErrMsgFooter) throws HeadlessException {
        String configErrMsg = buildConfigErrMsgHeader(component, panel, pageURI)
                .append(actionName).append('\n')
                .append("with unfound ").append(propertyName).append('[').append(blockId).append(']')
                .append(configErrMsgFooter == null ? "" : configErrMsgFooter).toString();
        CommonUtil.showBlockedErrorMsg(null, configErrMsg, true);
    }

    private void promptMistakenNumberBlockErr(String number, String actionName, String propertyName,
            Component component, Panel panel, String pageURI, String configErrMsgFooter) throws HeadlessException {
        String configErrMsg = buildConfigErrMsgHeader(component, panel, pageURI)
                .append(actionName).append('\n')
                .append("with erroneous number").append('[').append(number).append(']')
                .append(" for property[").append(propertyName).append(']')
                .append(configErrMsgFooter == null ? "" : configErrMsgFooter).toString();
        CommonUtil.showBlockedErrorMsg(null, configErrMsg, true);
    }

    private void promptUnsupportedActionBlockErr(String actionName, Component component,
            Panel panel, String pageURI, String configErrMsgFooter) throws HeadlessException {
        String configErrMsg = buildConfigErrMsgHeader(component, panel, pageURI)
                .append(actionName).append('\n')
                .append(component.getType().toString()).append(" never support ").append(actionName)
                .append(configErrMsgFooter == null ? "" : configErrMsgFooter).toString();
        CommonUtil.showBlockedErrorMsg(null, configErrMsg, true);
    }

    private void promptIncompleteActionBlockErr(String actionName, Component component,
            Panel panel, String pageURI, String configErrMsgFooter) throws HeadlessException {
        String configErrMsg = buildConfigErrMsgHeader(component, panel, pageURI)
                .append(actionName).append('\n')
                .append(actionName).append(" must have ")
                .append(configErrMsgFooter == null ? "" : configErrMsgFooter).toString();
        CommonUtil.showBlockedErrorMsg(null, configErrMsg, true);
    }

    public StringBuilder buildConfigErrMsgHeader(Component component, Panel panel, String pageURI) {
        StringBuilder configErrMsg = new StringBuilder().append(component.getType().toString())
                .append('[').append(component.getId()).append(']').append(" of ")
                .append(panel.getType()).append('[').append(panel.getId()).append(']').append('\n')
                .append("in ").append(pageURI).append('\n')
                .append("mistakenly configures ");
        return configErrMsg;
    }

    private void setupAction(Component component, Panel parentContainer) {
        List<king.flow.view.Action> actions = component.getAction();

        for (king.flow.view.Action actionNode : actions) {

            doJumpPanelAction(actionNode, component);

            doCustomizedAction(actionNode, component);

            doSetFontAction(actionNode, component);

            doShowTableAction(actionNode, component);

            doShowComboBoxAction(actionNode, component);

            doSendMsgAction(actionNode, component);

            doCleanAction(actionNode, component);

            doHideAction(actionNode, component);

            doLimitInputAction(actionNode, component);

            doPlayMediaAction(actionNode, component);

            doVirtualKeyboardAction(actionNode, component);

            doLoadWebAction(actionNode, component);

            doRunCommandAction(actionNode, component);

            doSetPrinterAction(actionNode, component);

            doUseTipAction(actionNode, component);

            doInsertICardAction(actionNode, component);

            doWriteICardAction(actionNode, component);

            doUploadFileAction(actionNode, component);

            doMoveCursorAction(actionNode, component);

            doSwipeCardAction(actionNode, component);

            doReadWriteFingerPrintAction(actionNode, component);

            doOpenBroswerAction(actionNode, component);

            doSwipe2In1CardAction(actionNode, component);

            doEjectCardAction(actionNode, component);

            doWithdrawCardAction(actionNode, component);

            doPrintPassbookAction(actionNode, component);

            doPlayVideoAction(actionNode, component, parentContainer);

            doShowClockAction(actionNode, component);

            doBalanceTransAction(actionNode, component);

            doEncryptKeyboardAction(actionNode, component);

            doShowGridAction(actionNode, component);

            doNumericPadAction(actionNode, component);
        }
    }

    private void doNumericPadAction(king.flow.view.Action actionNode, Component component) {
        king.flow.view.Action.NumericPadAction numericPadAction = actionNode.getNumericPadAction();
        if (numericPadAction != null) {
            DefaultNumericPadAction padAction = new DefaultNumericPadAction(numericPadAction.getTargetId());
            doAction(padAction, component.getId());
        }
    }

    private void doShowGridAction(king.flow.view.Action actionNode, Component component) {
        king.flow.view.Action.ShowGridAction showGridAction = actionNode.getShowGridAction();
        if (showGridAction != null) {
            DefaultGridAction defaultGridAction = new DefaultGridAction(showGridAction);
            doAction(defaultGridAction, component.getId());
        }
    }

    private void doHideAction(king.flow.view.Action actionNode, Component component) {
        king.flow.view.Action.HideAction hideAction = actionNode.getHideAction();
        if (hideAction != null) {
            DefaultHideAction defaultHideAction = new DefaultHideAction();
            doAction(defaultHideAction, component.getId());
        }
    }

    private void doEjectCardAction(king.flow.view.Action actionNode, Component component) {
        king.flow.view.Action.EjectCardAction ejectCardAction = actionNode.getEjectCardAction();
        if (ejectCardAction != null) {
            EjectCardAction ejectCard = new EjectCardAction(ejectCardAction.getCardType());
            doAction(ejectCard, component.getId());
        }
    }

    private void doWithdrawCardAction(king.flow.view.Action actionNode, Component component) {
        king.flow.view.Action.WithdrawCardAction wca = actionNode.getWithdrawCardAction();
        if (wca != null) {
            WithdrawCardAction withdrawCardAction = new WithdrawCardAction(wca.getCardType());
            doAction(withdrawCardAction, component.getId());
        }
    }

    private void doEncryptKeyboardAction(king.flow.view.Action actionNode, Component component) {
        king.flow.view.Action.EncryptKeyboardAction encryptKeyboardAction = actionNode.getEncryptKeyboardAction();
        if (encryptKeyboardAction != null) {
            int moneyId = encryptKeyboardAction.getMoneyId();
            Integer nextTrigger = encryptKeyboardAction.getNextTrigger();
            Integer cancelTrigger = encryptKeyboardAction.getCancelTrigger();
            KeyboardEncryptAction keyboardEncryptAction = new KeyboardEncryptAction(moneyId, nextTrigger, cancelTrigger);
            doAction(keyboardEncryptAction, component.getId());
        }
    }

    private void doBalanceTransAction(king.flow.view.Action actionNode, Component component) {
        MsgSendAction balanceTransAction = actionNode.getBalanceTransAction();
        if (balanceTransAction != null) {
            String prsCode = balanceTransAction.getPrsCode();
            int cmdCode = balanceTransAction.getCmdCode() == null ? -1 : balanceTransAction.getCmdCode();
            String conditions = balanceTransAction.getConditions();
            MsgSendAction.NextStep nextStep = balanceTransAction.getNextStep();
            MsgSendAction.Exception exception = balanceTransAction.getException();
            Rules checkRules = balanceTransAction.getCheckRules();
            ArrayList<String> listConditions = buildListParameters(conditions);

            BalanceTransAction balanceAction = new BalanceTransAction(prsCode, cmdCode,
                    listConditions, nextStep, exception, checkRules);
            doAction(balanceAction, component.getId());
        }
    }

    private void doShowClockAction(king.flow.view.Action actionNode, Component component) {
        king.flow.view.Action.ShowClockAction showClockAction = actionNode.getShowClockAction();
        if (showClockAction != null) {
            String format = showClockAction.getFormat();
            if (format != null && !format.isEmpty()) {
                doAction(new ShowClockAction(format), component.getId());
            } else {
                doAction(new ShowClockAction(), component.getId());
            }
        }
    }

    private void doPlayVideoAction(king.flow.view.Action actionNode, Component component, Panel parentContainer) {
        king.flow.view.Action.PlayVideoAction playVideoAction = actionNode.getPlayVideoAction();
        if (playVideoAction != null && component.getType() == ComponentEnum.VIDEO_PLAYER) {
            String media = playVideoAction.getMedia();
            int replayInterval = playVideoAction.getReplayInterval() == null
                    ? CommonConstants.DEFAULT_VIDEO_REPLAY_INTERVAL_SECOND : playVideoAction.getReplayInterval();
            DefaultVideoAction videoAction = new DefaultVideoAction(media, replayInterval, parentContainer.getId());
            doAction(videoAction, component.getId());
        }
    }

    private void doPrintPassbookAction(king.flow.view.Action actionNode, Component component) {
        king.flow.view.Action.PrintPassbookAction printPassbookAction = actionNode.getPrintPassbookAction();
        if (printPassbookAction != null) {
            int tableId = printPassbookAction.getTableId();
            PrintPassbookAction printPbAction = new PrintPassbookAction(tableId);
            doAction(printPbAction, component.getId());
        }
    }

    private void doSwipe2In1CardAction(king.flow.view.Action actionNode, Component component) {
        king.flow.view.Action.Swipe2In1CardAction swipe2In1CardAction = actionNode.getSwipe2In1CardAction();
        if (swipe2In1CardAction != null) {
            Integer nextCursor = swipe2In1CardAction.getNextCursor();
            Boolean editable = swipe2In1CardAction.isEditable();
            String mediaTip = swipe2In1CardAction.getMediaTip();
            String animationTip = swipe2In1CardAction.getAnimationTip();
            nextCursor = nextCursor == null ? component.getId() : nextCursor;
            king.flow.view.Action.Swipe2In1CardAction.Debug debug = swipe2In1CardAction.getDebug();
            Read2In1CardAction read2In1CardAction = null;
            if (debug == null) {
                read2In1CardAction = editable == null ? new Read2In1CardAction(nextCursor, mediaTip, animationTip)
                        : new Read2In1CardAction(nextCursor, editable, mediaTip, animationTip);
            } else {
                read2In1CardAction = editable == null ? new Read2In1CardAction(nextCursor, mediaTip, animationTip, debug)
                        : new Read2In1CardAction(nextCursor, editable, mediaTip, animationTip, debug);
            }

            doAction(read2In1CardAction, component.getId());
        }
    }

    private void doOpenBroswerAction(king.flow.view.Action actionNode, Component component) {
        king.flow.view.Action.OpenBrowserAction openBroswerAction = actionNode.getOpenBrowserAction();
        if (openBroswerAction != null) {
            String url = openBroswerAction.getUrl();
            OpenBrowserAction openBroswer = new OpenBrowserAction(url);
            doAction(openBroswer, component.getId());
        }
    }

    private void doReadWriteFingerPrintAction(king.flow.view.Action actionNode, Component component) {
        king.flow.view.Action.RwFingerPrintAction rwFingerPrintAction = actionNode.getRwFingerPrintAction();
        if (rwFingerPrintAction != null) {
            Integer nextCursor = rwFingerPrintAction.getNextCursor();
            Boolean isWrite = rwFingerPrintAction.isWritePrint();
            nextCursor = nextCursor == null ? component.getId() : nextCursor;
            RWFingerPrintAction readWriteAction = isWrite == null ? new RWFingerPrintAction(nextCursor)
                    : new RWFingerPrintAction(nextCursor, isWrite);
            doAction(readWriteAction, component.getId());
        }
    }

    private void doSwipeCardAction(king.flow.view.Action actionNode, Component component) {
        king.flow.view.Action.SwipeCardAction swipeCardAction = actionNode.getSwipeCardAction();
        if (swipeCardAction != null) {
            Integer nextCursor = swipeCardAction.getNextCursor();
            Boolean editable = swipeCardAction.isEditable();
            nextCursor = nextCursor == null ? component.getId() : nextCursor;
            king.flow.view.Action.SwipeCardAction.Debug debug = swipeCardAction.getDebug();
            ReadCardAction readCardAction = null;
            if (debug == null) {
                readCardAction = editable == null ? new ReadCardAction(nextCursor)
                        : new ReadCardAction(nextCursor, editable);
            } else {
                readCardAction = editable == null ? new ReadCardAction(nextCursor, debug)
                        : new ReadCardAction(nextCursor, editable, debug);
            }

            doAction(readCardAction, component.getId());
        }
    }

    private void doMoveCursorAction(king.flow.view.Action actionNode, Component component) {
        king.flow.view.Action.MoveCursorAction moveCursorAction = actionNode.getMoveCursorAction();
        if (moveCursorAction != null) {
            int up = moveCursorAction.getUpCursor();
            int down = moveCursorAction.getDownCursor();
            MoveCursorAction moveAction = new MoveCursorAction(up, down);
            doAction(moveAction, component.getId());
        }
    }

    private void doWriteICardAction(king.flow.view.Action actionNode, Component component) {
        king.flow.view.Action.WriteICardAction writeICardAction = actionNode.getWriteICardAction();
        if (writeICardAction != null) {
            DeviceEnum cardType = writeICardAction.getCardType();
            String prsCode = writeICardAction.getCardAction().getPrsCode();
            int cmdCode = writeICardAction.getCardAction().getCmdCode() == null ? -1
                    : writeICardAction.getCardAction().getCmdCode();
            String conditions = writeICardAction.getCardAction().getConditions();
            MsgSendAction.NextStep nextStep = writeICardAction.getCardAction().getNextStep();
            MsgSendAction.Exception exception = writeICardAction.getCardAction().getException();
            Rules checkRules = writeICardAction.getCardAction().getCheckRules();
            ArrayList<String> listConditions = buildListParameters(conditions);

            WriteCardAction writeAction = new WriteCardAction(cardType, prsCode, cmdCode,
                    listConditions, nextStep, exception, checkRules);
            doAction(writeAction, component.getId());
        }
    }

    private void doInsertICardAction(king.flow.view.Action actionNode, Component component) {
        king.flow.view.Action.InsertICardAction insertICardAction = actionNode.getInsertICardAction();
        if (insertICardAction != null) {
            DeviceEnum cardType = insertICardAction.getCardType();
            king.flow.view.Action.InsertICardAction.NextStep suceessfulPanel = insertICardAction.getNextStep();
            king.flow.view.Action.InsertICardAction.Exception failedPanel = insertICardAction.getException();
            String animation = insertICardAction.getAnimationTip();
            String prompt = insertICardAction.getPromptTip();
            List<String> parameters = insertICardAction.getParameters();
            InsertCardAction insertCardAction = new InsertCardAction(cardType, suceessfulPanel, failedPanel,
                    animation, prompt, parameters);
            doAction(insertCardAction, component.getId());
        }
    }

    private void doUploadFileAction(king.flow.view.Action actionNode, Component component) {
        king.flow.view.Action.UploadFileAction fileUploadAction = actionNode.getUploadFileAction();
        if (fileUploadAction != null) {
            String filter = fileUploadAction.getFilter();
            String uploadPath = fileUploadAction.getUploadPath();
            DefaultFileUploadAction defaultFileChooseAction = new DefaultFileUploadAction(filter, uploadPath);
            doAction(defaultFileChooseAction, component.getId());
        }
    }

    private void doUseTipAction(king.flow.view.Action actionNode, Component component) {
        king.flow.view.Action.UseTipAction tipAction = actionNode.getUseTipAction();
        if (tipAction != null) {
            String tip = tipAction.getTip();
            DefaultTipAction defaultTipAction = new DefaultTipAction(tip);
            doAction(defaultTipAction, component.getId());
        }
    }

    private void doSetPrinterAction(king.flow.view.Action actionNode, Component component) {
        king.flow.view.Action.SetPrinterAction printerAction = actionNode.getSetPrinterAction();
        if (printerAction != null) {
            String header = printerAction.getHeader();
            String tail = printerAction.getTail();
            DefaultPrinterAction defaultPrinterAction = null;
            king.flow.view.Action.SetPrinterAction.Debug debug = printerAction.getDebug();
            if (debug == null) {
                defaultPrinterAction = new DefaultPrinterAction(header, tail);
            } else {
                defaultPrinterAction = new DefaultPrinterAction(header, tail, debug);
            }

            doAction(defaultPrinterAction, component.getId());
        }
    }

    private void doRunCommandAction(king.flow.view.Action actionNode, Component component) {
        king.flow.view.Action.RunCommandAction runCommandAction = actionNode.getRunCommandAction();
        if (runCommandAction != null) {
            String command = runCommandAction.getCommand();
            DefaultRunCommandAction defaultRunCommandAction = new DefaultRunCommandAction(command);
            doAction(defaultRunCommandAction, component.getId());
        }
    }

    private void doLoadWebAction(king.flow.view.Action actionNode, Component component) {
        king.flow.view.Action.WebLoadAction webLoadAction = actionNode.getWebLoadAction();
        if (webLoadAction != null) {
            String url = webLoadAction.getUrl();
            DefaultWebLoadAction defaultWebLoadAction = new DefaultWebLoadAction(url);
            doAction(defaultWebLoadAction, component.getId());
        }
    }

    private void doVirtualKeyboardAction(king.flow.view.Action actionNode, Component component) {
        king.flow.view.Action.VirtualKeyboardAction virtualKeyboardAction = actionNode.getVirtualKeyboardAction();
        if (virtualKeyboardAction != null) {
            String start_cmd = virtualKeyboardAction.getStart();
            String stop_cmd = virtualKeyboardAction.getStop();
            DefaultVirtualKeyBoardAction defaultKeyboardAction = new DefaultVirtualKeyBoardAction(start_cmd, stop_cmd);
            doAction(defaultKeyboardAction, component.getId());
        }
    }

    private void doPlayMediaAction(king.flow.view.Action actionNode, Component component) {
        king.flow.view.Action.PlayMediaAction mediaPlayAction = actionNode.getPlayMediaAction();
        if (mediaPlayAction != null) {
            String media = mediaPlayAction.getMedia();
            DefaultMediaAction defaultMediaAction = new DefaultMediaAction(media);
            doAction(defaultMediaAction, component.getId());
        }
    }

    private void doLimitInputAction(king.flow.view.Action actionNode, Component component) {
        king.flow.view.Action.LimitInputAction limitInputAction = actionNode.getLimitInputAction();
        if (limitInputAction != null) {
            int length = limitInputAction.getLength();
            DefaultTextFieldAction defaultTextFieldAction = null;
            Boolean enableCashLimit = limitInputAction.isEnableCashLimit();
            Boolean enableNumberLimit = limitInputAction.isEnableNumberLimit();
            if (enableCashLimit != null && enableCashLimit) {
                if (limitInputAction.isEditable()) {
                    defaultTextFieldAction = new DefaultTextFieldAction(length, enableCashLimit);
                } else {
                    defaultTextFieldAction = new DefaultTextFieldAction(length, enableCashLimit, false);
                }
            } else if (enableNumberLimit != null && enableNumberLimit) {
                if (limitInputAction.isEditable()) {
                    defaultTextFieldAction = new DefaultTextFieldAction(length, DefaultTextFieldAction.InputType.NUMBER);
                } else {
                    defaultTextFieldAction = new DefaultTextFieldAction(length, false, false);
                }
            } else if (limitInputAction.isEditable()) {
                defaultTextFieldAction = new DefaultTextFieldAction(length, false, true);
            } else {
                defaultTextFieldAction = new DefaultTextFieldAction(length, false, false);
            }
            doAction(defaultTextFieldAction, component.getId());
        }
    }

    private void doCleanAction(king.flow.view.Action actionNode, Component component) {
        king.flow.view.Action.CleanAction cleanAction = actionNode.getCleanAction();
        if (cleanAction != null) {
            String conditions = cleanAction.getConditions();
            ArrayList<String> listParameters = buildListParameters(conditions);
            DefaultCleanAction defaultCleanAction = new DefaultCleanAction(listParameters);
            doAction(defaultCleanAction, component.getId());
        }
    }

    private void doSendMsgAction(king.flow.view.Action actionNode, Component component) {
        MsgSendAction sendMsgAction = actionNode.getSendMsgAction();
        if (sendMsgAction != null) {
            String prsCode = sendMsgAction.getPrsCode();
            int cmdCode = sendMsgAction.getCmdCode() == null ? -1 : sendMsgAction.getCmdCode();
            String conditions = sendMsgAction.getConditions();
            MsgSendAction.NextStep nextStep = sendMsgAction.getNextStep();
            MsgSendAction.Exception exception = sendMsgAction.getException();
            Rules checkRules = sendMsgAction.getCheckRules();
            ArrayList<String> listConditions = buildListParameters(conditions);

            if (component.getType() == ComponentEnum.ADVANCED_TABLE) {
                JXMsgPanel advancedTable = (JXMsgPanel) building_blocks.get(component.getId());
                advancedTable.initialMsgSendAction(new DefaultMsgSendAction[]{
                    new AdvancedMsgSendAction(prsCode, cmdCode,
                    listConditions, nextStep, exception, checkRules,
                    component.getId(), advancedTable.getJump(), building_blocks, meta_blocks),
                    new AdvancedMsgSendAction(prsCode, cmdCode,
                    listConditions, nextStep, exception, checkRules,
                    component.getId(), advancedTable.getNext(), building_blocks, meta_blocks),
                    new AdvancedMsgSendAction(prsCode, cmdCode,
                    listConditions, nextStep, exception, checkRules,
                    component.getId(), advancedTable.getPrevious(), building_blocks, meta_blocks)});
            } else {
                DefaultMsgSendAction sendAction = new DefaultMsgSendAction(prsCode, cmdCode,
                        listConditions, nextStep, exception, checkRules);
                doAction(sendAction, component.getId());
            }
        }
    }

    private void doShowComboBoxAction(king.flow.view.Action actionNode, Component component) {
        king.flow.view.Action.ShowComboBoxAction comboShowAction = actionNode.getShowComboBoxAction();
        if (comboShowAction != null) {
            String items = comboShowAction.getItems();
            ArrayList<String> listItems = buildListParameters(items);
            DefaultComboBoxAction comboBoxAction = new DefaultComboBoxAction(listItems);
            doAction(comboBoxAction, component.getId());
        }
    }

    private void doShowTableAction(king.flow.view.Action actionNode, Component component) {
        king.flow.view.Action.ShowTableAction tableShowAction = actionNode.getShowTableAction();
        if (tableShowAction != null) {
            String columnNames = tableShowAction.getColumnNames();
            ArrayList<String> colsNames = buildListParameters(columnNames);
            DefaultTableAction tableAction = new DefaultTableAction(colsNames, tableShowAction.getSum());
            switch (component.getType()) {
                case TABLE:
                    doAction(tableAction, component.getId());
                    break;
                case ADVANCED_TABLE:
                    JXMsgPanel advancedTable = (JXMsgPanel) building_blocks.get(component.getId());
                    advancedTable.initialTableAction(tableAction);
                    break;
                default:
                    getLogger(MainWindow.class.getName()).log(Level.INFO,
                            "Mistaken table type {0}", component.getType());
                    break;
            }
        }
    }

    private void doSetFontAction(king.flow.view.Action actionNode, Component component) {
        king.flow.view.Action.SetFontAction fontSetAction = actionNode.getSetFontAction();
        if (fontSetAction != null) {
            DefaultFontAction fontAction = null;
            String name = fontSetAction.getFontName();
            Integer size = fontSetAction.getFontSize();
            FontstyleEnum style = fontSetAction.getFontStyle();
            boolean do_setting = (name != null) || (size != null) || (style != null);
            if (do_setting) {
                Font font = new Font();
                if (name != null) {
                    font.setName(name);
                }
                if (size != null) {
                    if (isScaleScreen()) {
                        font.setSize(CommonUtil.adjustSize(size));
                    } else {
                        font.setSize(size);
                    }
                }
                if (style != null) {
                    font.setStyle(style);
                }
                fontAction = new DefaultFontAction(font);
            }

            if (fontSetAction.getFontColor() != null) {
                if (fontAction == null) {
                    fontAction = new DefaultFontAction();
                }
                fontAction.setForegroundColor(CommonUtil.getTrueColor(fontSetAction.getFontColor()));
            }

            if (fontSetAction.getFontBgColor() != null) {
                if (fontAction == null) {
                    fontAction = new DefaultFontAction();
                }
                fontAction.setBackgroundColor(CommonUtil.getTrueColor(fontSetAction.getFontBgColor()));
            }

            if (fontAction != null) {
                doAction(fontAction, component.getId());
            }
        }
    }

    private void doJumpPanelAction(king.flow.view.Action actionNode, Component component) {
        JumpAction jumpPanelAction = actionNode.getJumpPanelAction();
        if (jumpPanelAction != null) {
            int nextPanel = jumpPanelAction.getNextPanel();
            Integer nextCursor = jumpPanelAction.getNextCursor();
            Integer trigger = jumpPanelAction.getTrigger();
            Action buttonAction = new DefaultButtonAction(nextPanel, nextCursor, trigger);
            doAction(buttonAction, component.getId());
        }
    }

    private void doCustomizedAction(king.flow.view.Action actionNode, Component component) {
        int componentID = component.getId();
        doDefinedAction(actionNode.getCustomizedAction(), componentID);
    }

    private void doDefinedAction(List<DefinedAction> definedActions, int componentID) {
        String actionClass = null;
        try {
            if (definedActions != null) {
                for (DefinedAction customizedAction : definedActions) {
                    actionClass = customizedAction.getClassName();
                    if (actionClass == null || actionClass.length() == 0) {
                        continue;
                    }
                    Action action = null;
                    DefinedAction.ConstructorParameters constructorParameters = customizedAction.getConstructorParameters();
                    if (constructorParameters != null) {
                        List<DefinedAction.ConstructorParameters.Parameter> parameters = constructorParameters.getParameter();
                        List<Class> typeList = new ArrayList<>();
                        List valueList = new ArrayList();
                        for (DefinedAction.ConstructorParameters.Parameter p : parameters) {
                            String type = p.getType();
                            String value = p.getValue();
                            Class<?> cType;
                            Object oValue = null;
                            switch (type) {
                                case "int":
                                    cType = int.class;
                                    oValue = Integer.parseInt(value);
                                    break;
                                case "boolean":
                                    cType = boolean.class;
                                    oValue = Boolean.parseBoolean(value);
                                    break;
                                case "string":
                                    cType = String.class;
                                    oValue = String.valueOf(value);
                                    break;
                                case "java.util.List":
                                    cType = Class.forName(type);
                                    oValue = buildListParameters(value);
                                    break;
                                case "king.flow.view.ComponentEnum":
                                    cType = Class.forName(type);
                                    oValue = king.flow.view.ComponentEnum.fromValue(value);
                                    break;
                                default:
                                    cType = Class.forName(type);
                                    oValue = value;
                            }
                            typeList.add(cType);
                            valueList.add(oValue);
                        }
                        Constructor<?> constructor = Class.forName(actionClass).getConstructor(typeList.toArray(new Class[1]));
                        action = (Action) constructor.newInstance(valueList.toArray());
                        getLogger(MainWindow.class.getName()).log(Level.CONFIG, constructor.toString());
                    } else {
                        action = (Action) Class.forName(actionClass).newInstance();
                    }
                    doAction(action, componentID);
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException ex) {
            String err_msg = "Action loading failed for " + actionClass + " in component with ID " + componentID + " due to\n {0}";
            String cause = shapeErrPrompt(ex);
            getLogger(MainWindow.class.getName()).log(Level.WARNING, err_msg, cause);
        }
    }

    private void doAction(Action action, int id) {
        action.holdComponents(id, building_blocks, meta_blocks);
        action.setupListener();
        action.initializeData();
    }

    private void initComponents(Panel pNode, JPanel panel, String pageURI) throws AssertionError {
        List<Component> components = pNode.getComponent();
        for (Component component : components) {
            if (this.meta_blocks.containsKey(component.getId())) {
                CommonUtil.showBlockedErrorMsg(null, CommonUtil.buildErrMsg(component.getType().toString(),
                        component.getId(), pageURI).toString(), true);
            }
            JComponent jcomponent = constructComponent(component);
            if (jcomponent != null) {
                panel.add(jcomponent);
            }
        }
    }

    private JComponent constructComponent(Component component) throws AssertionError {
        final int id = component.getId();
        BasicAttribute attribute = component.getAttribute();
        if (attribute == null) {
            return null;
        }
        String text = attribute.getText() == null ? "" : attribute.getText();
        Bound rect = attribute.getRect();
        if (isScaleScreen()) {
            rect = adjustByResolution(rect);
        }

        String icon = attribute.getIcon();
        JComponent jcomponent = null;
        ComponentEnum ctype = component.getType();
        switch (ctype) {
            case BUTTON:
                JButton jButton = new JXButton();
                jcomponent = jButton;
                if (icon != null && icon.length() > 0) {
                    ArrayList<String> iconList = buildListParameters(icon);
                    if (iconList.size() >= 3) {
                        jButton.setIcon(getImageIcon(iconList.get(0)));
                        jButton.setPressedIcon(getImageIcon(iconList.get(1)));
                        jButton.setDisabledIcon(getImageIcon(iconList.get(2)));
                    } else if (iconList.size() == 2) {
                        jButton.setIcon(getImageIcon(iconList.get(0)));
                        jButton.setPressedIcon(getImageIcon(iconList.get(1)));
                    } else {
                        jButton.setIcon(getImageIcon(icon));
                    }
                    jButton.setHorizontalTextPosition(JButton.CENTER);
                    jButton.setVerticalTextPosition(JButton.CENTER);
                }
                jButton.setText(text);
                break;
            case LABEL:
                JLabel jLabel = new JXLabel();
                jcomponent = jLabel;
                final String txt = StringEscapeUtils.unescapeHtml(CommonUtil.replaceSysVar(text));
                jLabel.setText(txt);
                if (icon != null && icon.length() > 0) {
                    jLabel.setIcon(getImageIcon(icon));
                    jLabel.setHorizontalTextPosition(JLabel.CENTER);
                    jLabel.setVerticalTextPosition(JLabel.CENTER);
                }
                break;
            case TABLE:
                JTable jTable = new JTable();
                jTable.getTableHeader().setResizingAllowed(false);
                jTable.getTableHeader().setReorderingAllowed(false);
                jTable.setRowHeight(TABLE_ROW_HEIGHT);
                jcomponent = jTable;
                break;
            case ADVANCED_TABLE:
                JXMsgPanel advancedTable = new JXMsgPanel();
                jcomponent = advancedTable;
                break;
            case TEXT_AREA:
                JTextArea jTextArea = new JTextArea();
                jcomponent = jTextArea;
                break;
            case TEXT_FIELD:
                JTextField jTextField = new JTextField();
                jcomponent = jTextField;
                break;
            case DATE:
                JXDatePicker datePicker = new JXDatePicker(Calendar.getInstance().getTime());
                datePicker.getEditor().setEditable(false);
                jcomponent = datePicker;
                break;
            case PASSWORD_FIELD:
                JPasswordField jPasswordField = new JPasswordField();
                jcomponent = jPasswordField;
                break;
            case COMBO_BOX:
                JComboBox jComboBox = new JComboBox();
                jcomponent = jComboBox;
                break;
            case EDITOR_PANE:
//                JXEditorPane jxEditorPane = new JXEditorPane();
//                jxEditorPane.setEditable(false);
//                jcomponent = jxEditorPane;
                JTextPane jTextPane = new JTextPane();
                jTextPane.setEditable(false);
                jcomponent = jTextPane;
                break;
            case WEB_BROSWER:
                SwingBrowser swingBrowser = new SwingBrowser();
                jcomponent = swingBrowser;
                break;
            case NATIVE_BROSWER:
                NativeBroswer nativeBroswer = new NativeBroswer();
                jcomponent = nativeBroswer;
                break;
            case VIDEO_PLAYER:
                VideoPlayer videoPlayer = new VideoPlayer();
                this.building_blocks.put(id, videoPlayer);
                this.meta_blocks.put(id, component);
                return null;
            case GRID:
                JXGridPanel gridPanel = new JXGridPanel();
                jcomponent = gridPanel;
                break;
            case NUMERIC_PAD:
                JXNumericPad numericPad = new JXNumericPad();
                jcomponent = numericPad;
                break;
            default:
                final AssertionError configError = new AssertionError("Mistaken configuration type out of components : " + ctype.value());
                getLogger(MainWindow.class.getName()).log(Level.SEVERE,
                        "Configuration error comes from :\n{0}", configError);
                throw configError;
        }

        UiStyle uiStyle = winNode.getUiStyle();
        if (uiStyle != null && uiStyle.getFont() != null) {
            if (isScaleScreen()) {
                final int adjustSize = CommonUtil.adjustSize(uiStyle.getFont().getSize());
                Font font = new Font();
                font.setName(uiStyle.getFont().getName());
                font.setSize(adjustSize);
                font.setStyle(uiStyle.getFont().getStyle());
                setFont(font, jcomponent);
            } else {
                setFont(uiStyle.getFont(), jcomponent);
            }
        }

        if (jcomponent != null) {
            jcomponent.setBounds(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeigh());
            debugComponent(attribute, jcomponent, id, component.getType().toString());
            this.building_blocks.put(id, jcomponent);
            this.meta_blocks.put(id, component);
        }
        return jcomponent;
    }

    private void initWindow() throws AssertionError, HeadlessException {
        BasicAttribute attribute = winNode.getAttribute();
        final int id = winNode.getId();
        this.meta_blocks.put(id, winNode);
        this.meta_blocks.put(CONTAINER_KEY, winNode);
        String text = attribute.getText();
        String icon = attribute.getIcon();
        Bound rect = attribute.getRect();
        if (rect != null && rect.getWidth() > 0 && rect.getHeigh() > 0) {
            CommonUtil.setScale(rect.getWidth(), rect.getHeigh());
        }
        JMenuBar menuBar = createMenuBar();

        WindowEnum type = winNode.getType();
        switch (type) {
            case FRAME:
                JFrame jFrame = new JFrame();
                jFrame.setResizable(false);
                if (isFullScreen()) {
                    jFrame.setUndecorated(true);
                    // add this from JFrame.setDefaultLookAndFeelDecorated API, 
                    // microsoft Text Type Input crash led by frame undecorated from http://www.programmersolution.com/3022806856/
                    // some solution from http://blog.csdn.net/ycb1689/article/details/7894316
                    // sun bug id http://bugs.java.com/bugdatabase/view_bug.do?bug_id=4919138
                    jFrame.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
                }
                if (text != null) {
                    jFrame.setTitle(text);
                }
                if (rect != null) {
                    jFrame.setBounds(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeigh());
                } else if (!isScaleScreen()) {
                    jFrame.setBounds(0, 0,
                            CommonUtil.DEFAULT_SCREEN_WIDTH,
                            CommonUtil.DEFAULT_SCREEN_HEITH);
                } else {
                    jFrame.setBounds(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds());
                }
                if (menuBar != null) {
                    jFrame.setJMenuBar(menuBar);
                }
                if (icon != null) {
                    jFrame.setIconImage(getImageIcon(icon).getImage());
                } else {
                    jFrame.setIconImage(getImageIcon(DEFAULT_BANK_APP_ICON).getImage());
                }
                this.window = jFrame;
                this.building_blocks.put(id, jFrame);
                this.building_blocks.put(CONTAINER_KEY, this.window);
                break;
            case DIALOG:
                JDialog jDialog = new JDialog();
                jDialog.setResizable(false);
                if (isFullScreen()) {
                    jDialog.setUndecorated(true);
                    jDialog.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);
                }
                if (text != null) {
                    jDialog.setTitle(text);
                }
                if (rect != null) {
                    jDialog.setBounds(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeigh());
                } else if (!isScaleScreen()) {
                    jDialog.setBounds(0, 0,
                            CommonUtil.DEFAULT_SCREEN_WIDTH,
                            CommonUtil.DEFAULT_SCREEN_HEITH);
                } else {
                    jDialog.setBounds(GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds());
                }
                if (menuBar != null) {
                    jDialog.setJMenuBar(menuBar);
                }
                if (icon != null) {
                    jDialog.setIconImage(getImageIcon(icon).getImage());
                } else {
                    jDialog.setIconImage(getImageIcon(DEFAULT_BANK_APP_ICON).getImage());
                }
                this.window = jDialog;
                this.building_blocks.put(id, jDialog);
                this.building_blocks.put(CONTAINER_KEY, this.window);
                break;
            default:
                final AssertionError configError = new AssertionError("Mistaken configuration type out of FRAME/DIALOG : " + type.value());
                getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, configError);
                throw configError;
        }

        setCurrentView(window);
    }

    private JMenuBar createMenuBar() {
        JMenuBar bar = null;
        Menubar menubar = winNode.getMenubar();
        if (menubar != null) {
            bar = new JMenuBar();
            List<Menuoption> menus = menubar.getMenu();
            if (menus != null && menus.size() > 0) {
                for (Menuoption mo : menus) {
                    List<Menuitem> items = mo.getItem();
                    if (items != null && items.size() > 0) {
                        JMenu menu = new JMenu(mo.getName() == null ? "NULL" : mo.getName());
                        for (Menuitem it : items) {
                            JMenuItem menuItem = new JMenuItem(it.getName() == null ? "NULL" : it.getName());
                            menuItem.setEnabled(it.isEnable() == null ? true : it.isEnable());
                            setMenuFont(winNode.getUiStyle(), menuItem);
                            menu.add(menuItem);
                            this.building_blocks.put(it.getId(), menuItem);
                            this.meta_blocks.put(it.getId(), it);
                            this.menuNodes.add(it);
                        }
                        setMenuFont(winNode.getUiStyle(), menu);
                        menu.setEnabled(mo.isEnable() == null ? false : mo.isEnable());
                        this.building_blocks.put(mo.getId(), menu);
                        this.meta_blocks.put(mo.getId(), mo);
                        bar.add(menu);
                    }
                }
            }
        }
        return bar;
    }

    private void setMenuFont(UiStyle uiStyle, JMenuItem menuItem) {
        if (uiStyle != null && uiStyle.getFont() != null) {
            final Font font_des = uiStyle.getFont();
            Integer size = font_des.getSize();
//            if (size != null && size > 20) {
//                font_des.setSize(20);
//            }
            java.awt.Font font = createFont(font_des, UIManager.getDefaults().getFont("Menu.font"));
            menuItem.setFont(font);
        }
    }

    private boolean isFullScreen() {
        Boolean fullscreen = winNode.getUiStyle().isFullscreen();
        return fullscreen == null ? true : fullscreen;
    }

    private boolean isScaleScreen() {
        Boolean scalescreen = winNode.getUiStyle().isScalescreen();
        return scalescreen == null ? true : scalescreen;
    }

    public void setVisible(boolean b) {
        this.window.setVisible(b);
    }

    public void addWindowListener(WindowAdapter windowAdapter) {
        this.window.addWindowListener(windowAdapter);
    }

    public Object getBuildingBlock(int id) {
        if (this.building_blocks == null) {
            return null;
        }
        return building_blocks.get(id);
    }

    public Object getBlockMeta(int id) {
        if (this.meta_blocks == null) {
            return null;
        }
        return meta_blocks.get(id);
    }

    public Set<Map.Entry<Panel, String>> getPanelList() {
        return this.panelNodes.entrySet();
    }
}
