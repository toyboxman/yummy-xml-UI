/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.flow.common;

import java.io.File;
import java.nio.charset.Charset;

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

    /* swing default config */
    public static final int DEFAULT_TABLE_ROW_COUNT = 15;

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
    public static final int TABLE_ROW_HEIGHT = 25;
    public static final String ADVANCED_TABLE_TOTAL_PAGES = "total";
    public static final String ADVANCED_TABLE_VALUE = "value";
    public static final String ADVANCED_TABLE_CURRENT_PAGE = "current";
}
