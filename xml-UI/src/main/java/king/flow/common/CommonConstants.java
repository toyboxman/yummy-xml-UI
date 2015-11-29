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
import king.flow.view.Action;
import king.flow.view.Action.CleanAction;
import king.flow.view.Action.LimitInputAction;
import king.flow.view.Action.MoveCursorAction;
import king.flow.view.Action.ShowComboBoxAction;
import king.flow.view.ComponentEnum;
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
    static final Map<ComponentEnum, List<String>> ACTION_COMPONENT_MAP = ImmutableMap.of(
            ComponentEnum.BUTTON, ImmutableList.of(
                    JumpAction.class.getSimpleName(),
                    CleanAction.class.getSimpleName(),
                    MsgSendAction.class.getSimpleName(),
                    MoveCursorAction.class.getSimpleName()),
            ComponentEnum.TEXT_FIELD, ImmutableList.of(
                    LimitInputAction.class.getSimpleName(),
                    MoveCursorAction.class.getSimpleName()),
            ComponentEnum.PASSWORD_FIELD, ImmutableList.of(
                    LimitInputAction.class.getSimpleName(),
                    MoveCursorAction.class.getSimpleName()),
            ComponentEnum.COMBO_BOX, ImmutableList.of(
                    ShowComboBoxAction.class.getSimpleName(),
                    MoveCursorAction.class.getSimpleName()),
            ComponentEnum.LABEL, ImmutableList.of(
                    ShowClockAction.class.getSimpleName())
    );
}
