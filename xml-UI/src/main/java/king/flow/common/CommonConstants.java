/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.flow.common;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import java.io.File;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import king.flow.action.business.ShowClockAction;
import king.flow.data.TLSResult;
import king.flow.view.Action;
import king.flow.view.Action.CleanAction;
import king.flow.view.Action.EjectCardAction;
import king.flow.view.Action.WithdrawCardAction;
import king.flow.view.Action.EncryptKeyboardAction;
import king.flow.view.Action.HideAction;
import king.flow.view.Action.InsertICardAction;
import king.flow.view.Action.LimitInputAction;
import king.flow.view.Action.MoveCursorAction;
import king.flow.view.Action.NumericPadAction;
import king.flow.view.Action.OpenBrowserAction;
import king.flow.view.Action.PlayMediaAction;
import king.flow.view.Action.PlayVideoAction;
import king.flow.view.Action.PrintPassbookAction;
import king.flow.view.Action.RunCommandAction;
import king.flow.view.Action.RwFingerPrintAction;
import king.flow.view.Action.SetFontAction;
import king.flow.view.Action.SetPrinterAction;
import king.flow.view.Action.ShowComboBoxAction;
import king.flow.view.Action.ShowGridAction;
import king.flow.view.Action.ShowTableAction;
import king.flow.view.Action.Swipe2In1CardAction;
import king.flow.view.Action.SwipeCardAction;
import king.flow.view.Action.UploadFileAction;
import king.flow.view.Action.UseTipAction;
import king.flow.view.Action.VirtualKeyboardAction;
import king.flow.view.Action.WriteICardAction;
import king.flow.view.ComponentEnum;
import king.flow.view.DefinedAction;
import king.flow.view.DeviceEnum;
import king.flow.view.JumpAction;
import king.flow.view.MsgSendAction;

/**
 *
 * @author LiuJin
 */
public class CommonConstants {

    public static final String APP_STARTUP_ENTRY = "bank.exe";
    public static final Charset UTF8 = Charset.forName("UTF-8");
    static final File[] SYS_ROOTS = File.listRoots();
    public static final int DRIVER_COUNT = SYS_ROOTS.length;
    public static final String XML_NODE_PREFIX = "N_";
    public static final String REVERT = "re_";
    public static final String BID = "bid";
    public static final String UID_PREFIX = "<" + TLSResult.UID + ">";
    public static final String UID_AFFIX = "</" + TLSResult.UID + ">";
    public static final String DEFAULT_DATE_FORMATE = "yyyy-MM-dd";
    public static final String VALID_BANK_CARD = "validBankCard";
    public static final String BALANCED_PAY_MAC = "balancedPayMAC";
    public static final String CANCEL_ENCRYPTION_KEYBOARD = "[CANCEL]";
    public static final String QUIT_ENCRYPTION_KEYBOARD = "[QUIT]";
    public static final String INVALID_ENCRYPTION_LENGTH = "encryption.keyboard.type.length.prompt";
    public static final String TIMEOUT_ENCRYPTION_TYPE = "encryption.keyboard.type.timeout.prompt";
    public static final String ERROR_ENCRYPTION_TYPE = "encryption.keyboard.type.fail.prompt";
    public static final int CONTAINER_KEY = Integer.MAX_VALUE;
    public static final int NORMAL = 0;
    public static final int ABNORMAL = 1;
    public static final int BALANCE = 12345;
    public static final int RESTART_SIGNAL = 1;
    public static final int DOWNLOAD_KEY_SIGNAL = 1;
    public static final int UPDATE_SIGNAL = 1;
    public static final int WATCHDOG_CHECK_INTERVAL = 5;
    public static final String VERSION;
    public static final long DEBUG_MODE_PROGRESS_TIME = TimeUnit.SECONDS.toMillis(3);
    public static final long RUN_MODE_PROGRESS_TIME = TimeUnit.SECONDS.toMillis(1);

    //    public static final String VERSION = Paths.get(".").toAbsolutePath().normalize().toString();
    static {
        String workingPath = System.getProperty("user.dir");
        final int lastIndexOf = workingPath.lastIndexOf('_');
        if (lastIndexOf != -1 && lastIndexOf < workingPath.length() - 1) {
            VERSION = workingPath.substring(lastIndexOf + 1);
        } else {
            VERSION = "Unknown";
        }

    }

    /* JMX configuration */
    private static String getJmxRmiUrl(int port) {
        return "service:jmx:rmi:///jndi/rmi://localhost:" + port + "/jmxrmi";
    }
    public static final int APP_JMX_RMI_PORT = 9998;
    public static final String APP_JMX_RMI_URL = getJmxRmiUrl(APP_JMX_RMI_PORT);
    public static final int WATCHDOG_JMX_RMI_PORT = 9999;
    public static final String WATCHDOG_JMX_RMI_URL = getJmxRmiUrl(WATCHDOG_JMX_RMI_PORT);

    /* system variable pattern */
    public static final String SYSTEM_VAR_PATTERN = "\\$\\{(_?\\p{Alpha}+_\\p{Alpha}+)+\\}";
    public static final String TEXT_MINGLED_SYSTEM_VAR_PATTERN = ".*" + SYSTEM_VAR_PATTERN + ".*";
    public static final String TERMINAL_ID_SYS_VAR = "TERMINAL_ID";

    /* swing default config */
    public static final int DEFAULT_TABLE_ROW_COUNT = 15;
    public static final int TABLE_ROW_HEIGHT = 25;
    public static final int DEFAULT_VIDEO_REPLAY_INTERVAL_SECOND = 20;

    /* packet header ID */
    public static final int GENERAL_MSG_CODE = 0; //common message
    public static final int REGISTRY_MSG_CODE = 1; //terminal registration message
    public static final int KEY_DOWNLOAD_MSG_CODE = 2; //download secret key message
    public static final int MANAGER_MSG_CODE = 100; //management message
    /*MAX_MESSAGES_PER_READ refers to DefaultChannelConfig, AdaptiveRecvByteBufAllocator, FixedRecvByteBufAllocator */
    public static final int MAX_MESSAGES_PER_READ = 64; //how many read actions in one message conversation
    public static final int MIN_RECEIVED_BUFFER_SIZE = 1024;  //1024 bytes
    public static final int RECEIVED_BUFFER_SIZE = 32 * 1024;  //32k bytes
    public static final int MAX_RECEIVED_BUFFER_SIZE = 64 * 1024;  //64k bytes

    /* keyboard cipher key */
    public static final String WORK_SECRET_KEY = "workSecretKey";
    public static final String MA_KEY = "maKey";
    public static final String MASTER_KEY = "masterKey";

    /* packet result flag */
    public static final int SUCCESSFUL_MSG_CODE = 0;

    /* xml jaxb context */
    public static final String NET_CONF_PACKAGE_CONTEXT = "king.flow.net";
    public static final String TLS_PACKAGE_CONTEXT = "king.flow.data";
    public static final String UI_CONF_PACKAGE_CONTEXT = "king.flow.view";

    public static final String KING_FLOW_BACKGROUND = "king.flow.background";
    public static final String KING_FLOW_PROGRESS = "king.flow.progress";
    public static final String TEXT_TYPE_TOOL_CONFIG = "chinese.text.type.config";
    public static final String COMBOBOX_ITEMS_PROPERTY_PATTERN = "([^,/]*/[^,/]*,)*+([^,/]*/[^,/]*){1}+";
    public static final String ADVANCED_TABLE_TOTAL_PAGES = "total";
    public static final String ADVANCED_TABLE_VALUE = "value";
    public static final String ADVANCED_TABLE_CURRENT_PAGE = "current";

    /* card-reading state */
    public static final int INVALID_CARD_STATE = -1;
    public static final int MAGNET_CARD_STATE = 2;
    public static final int IC_CARD_STATE = 3;

    /* union-pay transaction type */
    public static final String UNION_PAY_REGISTRATION = "1";
    public static final String UNION_PAY_TRANSACTION = "3";
    public static final String UNION_PAY_TRANSACTION_BALANCE = "4";

    /* card affiliation type */
    public static final String CARD_AFFILIATION_INTERNAL = "1";
    public static final String CARD_AFFILIATION_EXTERNAL = "2";

    /* supported driver types */
    static final ImmutableSet<DeviceEnum> SUPPORTED_DEVICES = new ImmutableSet.Builder<DeviceEnum>()
            .add(DeviceEnum.IC_CARD)
            .add(DeviceEnum.CASH_SAVER)
            .add(DeviceEnum.GZ_CARD)
            .add(DeviceEnum.HIS_CARD)
            .add(DeviceEnum.KEYBOARD)
            .add(DeviceEnum.MAGNET_CARD)
            .add(DeviceEnum.MEDICARE_CARD)
            .add(DeviceEnum.PATIENT_CARD)
            .add(DeviceEnum.PID_CARD)
            .add(DeviceEnum.PKG_8583)
            .add(DeviceEnum.PRINTER)
            .add(DeviceEnum.SENSOR_CARD)
            .add(DeviceEnum.TWO_IN_ONE_CARD)
            .build();

    /* action-component relationship map */
    public static final String JUMP_ACTION = JumpAction.class.getSimpleName();
    public static final String SET_FONT_ACTION = SetFontAction.class.getSimpleName();
    public static final String CLEAN_ACTION = CleanAction.class.getSimpleName();
    public static final String HIDE_ACTION = HideAction.class.getSimpleName();
    public static final String USE_TIP_ACTION = UseTipAction.class.getSimpleName();
    public static final String PLAY_MEDIA_ACTION = PlayMediaAction.class.getSimpleName();
    public static final String SEND_MSG_ACTION = MsgSendAction.class.getSimpleName();
    public static final String MOVE_CURSOR_ACTION = MoveCursorAction.class.getSimpleName();
    public static final String LIMIT_INPUT_ACTION = LimitInputAction.class.getSimpleName();
    public static final String SHOW_COMBOBOX_ACTION = ShowComboBoxAction.class.getSimpleName();
    public static final String SHOW_TABLE_ACTION = ShowTableAction.class.getSimpleName();
    public static final String SHOW_CLOCK_ACTION = ShowClockAction.class.getSimpleName();
    public static final String OPEN_BROWSER_ACTION = OpenBrowserAction.class.getSimpleName();
    public static final String RUN_COMMAND_ACTION = RunCommandAction.class.getSimpleName();
    public static final String OPEN_VIRTUAL_KEYBOARD_ACTION = VirtualKeyboardAction.class.getSimpleName();
    public static final String PRINT_RECEIPT_ACTION = SetPrinterAction.class.getSimpleName();
    public static final String INSERT_IC_ACTION = InsertICardAction.class.getSimpleName();
    public static final String WRITE_IC_ACTION = WriteICardAction.class.getSimpleName();
    public static final String BALANCE_TRANS_ACTION = "BalanceTransAction";
    public static final String PRINT_PASSBOOK_ACTION = PrintPassbookAction.class.getSimpleName();
    public static final String UPLOAD_FILE_ACTION = UploadFileAction.class.getSimpleName();
    public static final String SWIPE_CARD_ACTION = SwipeCardAction.class.getSimpleName();
    public static final String SWIPE_TWO_IN_ONE_CARD_ACTION = Swipe2In1CardAction.class.getSimpleName();
    public static final String EJECT_CARD_ACTION = EjectCardAction.class.getSimpleName();
    public static final String WITHDRAW_CARD_ACTION = WithdrawCardAction.class.getSimpleName();
    public static final String READ_WRITE_FINGERPRINT_ACTION = RwFingerPrintAction.class.getSimpleName();
    public static final String PLAY_VIDEO_ACTION = PlayVideoAction.class.getSimpleName();
    public static final String CUSTOMIZED_ACTION = DefinedAction.class.getSimpleName();
    public static final String ENCRYPT_KEYBORAD_ACTION = EncryptKeyboardAction.class.getSimpleName();
    public static final String SHOW_GRID_ACTION = ShowGridAction.class.getSimpleName();
    public static final String TYPE_NUMERIC_PAD_ACTION = NumericPadAction.class.getSimpleName();
    public static final String WEB_LOAD_ACTION = Action.WebLoadAction.class.getSimpleName();
    static final Map<ComponentEnum, List<String>> ACTION_COMPONENT_MAP = new ImmutableMap.Builder<ComponentEnum, List<String>>()
            .put(ComponentEnum.BUTTON, new ImmutableList.Builder<String>()
                    .add(CUSTOMIZED_ACTION)
                    .add(JUMP_ACTION)
                    .add(SET_FONT_ACTION)
                    .add(CLEAN_ACTION)
                    .add(HIDE_ACTION)
                    .add(USE_TIP_ACTION)
                    .add(PLAY_MEDIA_ACTION)
                    .add(OPEN_BROWSER_ACTION)
                    .add(RUN_COMMAND_ACTION)
                    .add(OPEN_VIRTUAL_KEYBOARD_ACTION)
                    .add(PRINT_RECEIPT_ACTION)
                    .add(SEND_MSG_ACTION)
                    .add(INSERT_IC_ACTION)
                    .add(WRITE_IC_ACTION)
                    .add(MOVE_CURSOR_ACTION)
                    .add(PRINT_PASSBOOK_ACTION)
                    .add(UPLOAD_FILE_ACTION)
                    .add(BALANCE_TRANS_ACTION)
                    .add(EJECT_CARD_ACTION)
                    .add(WITHDRAW_CARD_ACTION)
                    .add(WEB_LOAD_ACTION)
                    .build())
            .put(ComponentEnum.COMBO_BOX, new ImmutableList.Builder<String>()
                    .add(CUSTOMIZED_ACTION)
                    .add(SET_FONT_ACTION)
                    .add(USE_TIP_ACTION)
                    .add(SHOW_COMBOBOX_ACTION)
                    .add(SWIPE_CARD_ACTION)
                    .add(SWIPE_TWO_IN_ONE_CARD_ACTION)
                    .add(PLAY_MEDIA_ACTION)
                    .add(MOVE_CURSOR_ACTION)
                    .build())
            .put(ComponentEnum.LABEL, new ImmutableList.Builder<String>()
                    .add(CUSTOMIZED_ACTION)
                    .add(SET_FONT_ACTION)
                    .add(USE_TIP_ACTION)
                    .add(SHOW_CLOCK_ACTION)
                    .build())
            .put(ComponentEnum.TEXT_FIELD, new ImmutableList.Builder<String>()
                    .add(CUSTOMIZED_ACTION)
                    .add(SET_FONT_ACTION)
                    .add(LIMIT_INPUT_ACTION)
                    .add(USE_TIP_ACTION)
                    .add(PLAY_MEDIA_ACTION)
                    .add(READ_WRITE_FINGERPRINT_ACTION)
                    .add(OPEN_VIRTUAL_KEYBOARD_ACTION)
                    .add(MOVE_CURSOR_ACTION)
                    .build())
            .put(ComponentEnum.PASSWORD_FIELD, new ImmutableList.Builder<String>()
                    .add(CUSTOMIZED_ACTION)
                    .add(SET_FONT_ACTION)
                    .add(LIMIT_INPUT_ACTION)
                    .add(USE_TIP_ACTION)
                    .add(PLAY_MEDIA_ACTION)
                    .add(READ_WRITE_FINGERPRINT_ACTION)
                    .add(MOVE_CURSOR_ACTION)
                    .add(ENCRYPT_KEYBORAD_ACTION)
                    .build())
            .put(ComponentEnum.TABLE, new ImmutableList.Builder<String>()
                    .add(CUSTOMIZED_ACTION)
                    .add(SET_FONT_ACTION)
                    .add(USE_TIP_ACTION)
                    .add(SHOW_TABLE_ACTION)
                    .build())
            .put(ComponentEnum.ADVANCED_TABLE, new ImmutableList.Builder<String>()
                    .add(CUSTOMIZED_ACTION)
                    .add(SET_FONT_ACTION)
                    .add(SHOW_TABLE_ACTION)
                    .add(SEND_MSG_ACTION)
                    .build())
            .put(ComponentEnum.VIDEO_PLAYER, new ImmutableList.Builder<String>()
                    .add(CUSTOMIZED_ACTION)
                    .add(PLAY_VIDEO_ACTION)
                    .build())
            .put(ComponentEnum.GRID, new ImmutableList.Builder<String>()
                    .add(SHOW_GRID_ACTION)
                    .build())
            .put(ComponentEnum.NUMERIC_PAD, new ImmutableList.Builder<String>()
                    .add(TYPE_NUMERIC_PAD_ACTION)
                    .build())
            .build();

}
