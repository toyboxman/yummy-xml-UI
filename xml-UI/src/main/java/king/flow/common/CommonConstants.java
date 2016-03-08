/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.flow.common;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.io.File;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import king.flow.action.business.ShowClockAction;
import king.flow.data.TLSResult;
import king.flow.view.Action.CleanAction;
import king.flow.view.Action.InsertICardAction;
import king.flow.view.Action.LimitInputAction;
import king.flow.view.Action.MoveCursorAction;
import king.flow.view.Action.OpenBrowserAction;
import king.flow.view.Action.PlayMediaAction;
import king.flow.view.Action.PlayVideoAction;
import king.flow.view.Action.PrintPassbookAction;
import king.flow.view.Action.RunCommandAction;
import king.flow.view.Action.RwFingerPrintAction;
import king.flow.view.Action.SetFontAction;
import king.flow.view.Action.SetPrinterAction;
import king.flow.view.Action.ShowComboBoxAction;
import king.flow.view.Action.ShowTableAction;
import king.flow.view.Action.Swipe2In1CardAction;
import king.flow.view.Action.SwipeCardAction;
import king.flow.view.Action.UploadFileAction;
import king.flow.view.Action.UseTipAction;
import king.flow.view.Action.VirtualKeyboardAction;
import king.flow.view.ComponentEnum;
import king.flow.view.DefinedAction;
import king.flow.view.JumpAction;
import king.flow.view.MsgSendAction;

/**
 *
 * @author LiuJin
 */
public class CommonConstants {

    public static final Charset UTF8 = Charset.forName("UTF-8");
    static final File[] SYS_ROOTS = File.listRoots();
    public static final int DRIVER_COUNT = SYS_ROOTS.length;
    public static final String XML_NODE_PREFIX = "N_";
    public static final String REVERT = "re_";
    public static final String BID = "bid";
    public static final String UID_PREFIX = "<" + TLSResult.UID + ">";
    public static final String UID_AFFIX = "</" + TLSResult.UID + ">";
    public static final String DEFAULT_DATE_FORMATE = "yyyy-MM-dd";
    public static final int CONTAINER_KEY = Integer.MAX_VALUE;
    public static final int NORMAL = 0;
    public static final int ABNORMAL = 1;
    public static final int RESTART_SIGNAL = 1;
    public static final int DOWNLOAD_KEY_SIGNAL = 1;
    public static final int UPDATE_SIGNAL = 1;
    public static final String VERSION;
    
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

    /* system variable pattern */
    public static final String SYSTEM_VAR_PATTERN = "\\$\\{(_?\\p{Alpha}+_\\p{Alpha}+)+\\}";
    public static final String TEXT_MINGLED_SYSTEM_VAR_PATTERN = ".*" + SYSTEM_VAR_PATTERN + ".*";
    public static final String TERMINAL_ID_SYS_VAR = "TERMINAL_ID";

    /* swing default config */
    public static final int DEFAULT_TABLE_ROW_COUNT = 15;
    public static final int DEFAULT_VIDEO_REPLAY_INTERVAL_SECOND = 20;

    /* packet header ID */
    public static final int GENERAL_MSG_CODE = 0; //common message
    public static final int REGISTRY_MSG_CODE = 1; //terminal registration message
    public static final int KEY_DOWNLOAD_MSG_CODE = 2; //download secret key message
    public static final int MANAGER_MSG_CODE = 100; //management message
    public static final int MAX_MESSAGES_PER_READ = 9;

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
    public static final int TABLE_ROW_HEIGHT = 25;
    public static final String COMBOBOX_ITEMS_PROPERTY_PATTERN = "([^,/]*/[^,/]*,)*+([^,/]*/[^,/]*){1}+";
    public static final String ADVANCED_TABLE_TOTAL_PAGES = "total";
    public static final String ADVANCED_TABLE_VALUE = "value";
    public static final String ADVANCED_TABLE_CURRENT_PAGE = "current";

    /* card-reading state */
    public static final int INVALID_CARD_STATE = -1;
    public static final int MAGNET_CARD_STATE = 2;
    public static final int IC_CARD_STATE = 3;

    /* action-component relationship map */
    public static final String JUMP_ACTION = JumpAction.class.getSimpleName();
    public static final String SET_FONT_ACTION = SetFontAction.class.getSimpleName();
    public static final String CLEAN_ACTION = CleanAction.class.getSimpleName();
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
    public static final String WRITE_IC_ACTION = "WriteICardAction";
    public static final String BALANCE_TRANS_ACTION = "BalanceTransAction";
    public static final String PRINT_PASSBOOK_ACTION = PrintPassbookAction.class.getSimpleName();
    public static final String UPLOAD_FILE_ACTION = UploadFileAction.class.getSimpleName();
    public static final String SWIPE_CARD_ACTION = SwipeCardAction.class.getSimpleName();
    public static final String SWIPE_2IN1_CARD_ACTION = Swipe2In1CardAction.class.getSimpleName();
    public static final String READ_WRITE_FINGERPRINT_ACTION = RwFingerPrintAction.class.getSimpleName();
    public static final String PLAY_VIDEO_ACTION = PlayVideoAction.class.getSimpleName();
    public static final String CUSTOMIZED_ACTION = DefinedAction.class.getSimpleName();
    static final Map<ComponentEnum, List<String>> ACTION_COMPONENT_MAP = new ImmutableMap.Builder<ComponentEnum, List<String>>()
            .put(ComponentEnum.BUTTON, new ImmutableList.Builder<String>()
                    .add(CUSTOMIZED_ACTION)
                    .add(JUMP_ACTION)
                    .add(SET_FONT_ACTION)
                    .add(CLEAN_ACTION)
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
                    .build())
            .put(ComponentEnum.COMBO_BOX, new ImmutableList.Builder<String>()
                    .add(CUSTOMIZED_ACTION)
                    .add(SET_FONT_ACTION)
                    .add(USE_TIP_ACTION)
                    .add(SHOW_COMBOBOX_ACTION)
                    .add(SWIPE_CARD_ACTION)
                    .add(SWIPE_2IN1_CARD_ACTION)
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
            .build();

}
