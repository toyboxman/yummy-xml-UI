package king.flow.common;

import com.github.jsonj.JsonElement;
import com.github.jsonj.JsonObject;
import com.github.jsonj.tools.JsonParser;
import com.sun.org.apache.xerces.internal.dom.ElementNSImpl;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.Window;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.namespace.QName;
import king.flow.action.DefaultAction;
import static king.flow.common.CommonConstants.ABNORMAL;
import static king.flow.common.CommonConstants.GENERAL_MSG_CODE;
import static king.flow.common.CommonConstants.KEY_DOWNLOAD_MSG_CODE;
import static king.flow.common.CommonConstants.MANAGER_MSG_CODE;
import static king.flow.common.CommonConstants.NORMAL;
import static king.flow.common.CommonConstants.SUCCESSFUL_MSG_CODE;
import static king.flow.common.CommonConstants.XML_NODE_PREFIX;
import static king.flow.common.CommonUtil.AppType.TERMINAL;
import king.flow.control.driver.FingerPrintDrive;
import king.flow.control.driver.ICCardConductor;
import king.flow.control.driver.KeyBoardDriver;
import king.flow.control.driver.MagnetCardConductor;
import king.flow.control.driver.PrinterConductor;
import king.flow.data.RegistryTLSResult;
import king.flow.data.TLS;
import king.flow.data.TLSResult;
import static king.flow.data.TLSResult.CARGO;
import king.flow.design.TLSProcessor;
import king.flow.net.Tunnel;
import king.flow.net.TunnelBuilder;
import static king.flow.net.TunnelBuilder.getTunnelBuilder;
import king.flow.view.Bound;
import king.flow.view.DevicetypeEnum;
import static king.flow.view.DevicetypeEnum.IC_CARD;
import static king.flow.view.DevicetypeEnum.KEYBOARD;
import static king.flow.view.DevicetypeEnum.MAGNET_CARD;
import static king.flow.view.DevicetypeEnum.PRINTER;
import king.flow.view.FontstyleEnum;
import static king.flow.view.FontstyleEnum.BOLD;
import static king.flow.view.FontstyleEnum.ITALIC;
import static king.flow.view.FontstyleEnum.PLAIN;
import org.w3c.dom.DOMException;

/**
 *
 * @author LiuJin
 */
public class CommonUtil {

    public static int getFontStyle(FontstyleEnum fontStyle) {
        switch (fontStyle) {
            case BOLD:
                return Font.BOLD;
            case ITALIC:
                return Font.ITALIC;
            case PLAIN:
                return Font.PLAIN;
            default:
                getLogger(CommonUtil.class.getName()).log(Level.WARNING, "Unknow font configuration : {0}", fontStyle.value());
                return -9999;
        }
    }

    public static <T extends JComponent> void setFont(king.flow.view.Font newFont, T component) {
        Font font = createFont(newFont, component.getFont());
        component.setFont(font);
    }

    public static <T extends JComponent> Font createFont(king.flow.view.Font newFont, Font oldFont) {
        String fontName = newFont.getName();
        Integer fontSize = newFont.getSize();
        FontstyleEnum fontStyle = newFont.getStyle();
        int font_size = (fontSize == null) ? oldFont.getSize() : fontSize;
        int font_style = (fontStyle == null) ? oldFont.getStyle() : getFontStyle(fontStyle);
        Font font;
        if (fontName != null && !fontName.equalsIgnoreCase(oldFont.getFontName())) {
            font = new Font(fontName, font_style, font_size);
        } else {
            font = oldFont.deriveFont(font_style, font_size);
        }
        return font;
    }

    public static <T> JAXBElement createJAXBElement(QName name, T value) {
        return new JAXBElement<>(name, (Class<T>) value.getClass(), null, value);
    }

    public static <T> JAXBElement createJAXBElement(String QName, T value) {
        QName qName = new QName("", XML_NODE_PREFIX + QName);
        return createJAXBElement(qName, value);
    }

    public static RegistryTLSResult parseRegTLSMessage(String TLSResp) {
        RegistryTLSResult result = null;

        try {
            TLSProcessor tlsp = new TLSProcessor().init();
            TLS raw = tlsp.parse(TLSResp);
            result = new RegistryTLSResult();
            for (Object o : raw.getAny()) {
                if (o instanceof JAXBElement) {
                    JAXBElement e = (JAXBElement) o;
                    final String value = e.getValue() == null ? "" : e.getValue().toString();
                    final String name = e.getName().getLocalPart();
                    if (name.equals(TLSResult.OK_MSG)) {
                        result.setOkMsg(value);
                    } else if (name.equals(TLSResult.ERR_MSG)) {
                        result.setErrMsg(value);
                    } else if (name.equals(TLSResult.RET_CODE)) {
                        result.setRetCode(Integer.parseInt(value));
                    } else if (name.equals(TLSResult.PRT_MSG)) {
                        result.setPrtMsg(value);
                    } else if (name.equals(RegistryTLSResult.APP_RESTART_SIGNAL)) {
                        result.setAppRestartSignal(Integer.parseInt(value));
                    } else if (name.equals(RegistryTLSResult.DOWNLOAD_KEY_SIGNAL)) {
                        result.setDownloadKeySignal(Integer.parseInt(value));
                    } else if (name.equals(RegistryTLSResult.SYS_RESTART_SIGNAL)) {
                        result.setSystemRestartSignal(Integer.parseInt(value));
                    } else if (name.equals(RegistryTLSResult.APP_UPDATE_SIGNAL)) {
                        result.setAppUpdateSignal(Integer.parseInt(value));
                    } else if (name.equals(RegistryTLSResult.APP_UPDATE_FILE)) {
                        result.setAppUpdateFile(value);
                    } else {
                        getLogger(CommonUtil.class.getName()).log(Level.CONFIG,
                                "Unuseful registry response JAXBElement : {0}", e.getName().toString());
                    }
                    // As switch clause cannot support constant in runtime, 
                    // you have to give final string in compile time. I have to
                    // use ifelse clause instead, because I set constant like
                    // APP_UPDATE_MD5 using QName.getlocalPart()
                    /*switch (name) {
                     case TLSResult.OK_MSG:
                     result.setOkMsg(value);
                     break;
                     case TLSResult.ERR_MSG:
                     result.setErrMsg(value);
                     break;
                     case TLSResult.RET_CODE:
                     result.setRetCode(Integer.parseInt(value));
                     break;
                     case TLSResult.PRT_MSG:
                     result.setPrtMsg(value);
                     break;
                     case RegistryTLSResult.APP_RESTART_SIGNAL:
                     result.setAppRestartSignal(Integer.parseInt(value));
                     break;
                     case RegistryTLSResult.DOWNLOAD_KEY_SIGNAL:
                     result.setDownloadKeySignal(Integer.parseInt(value));
                     break;
                     case RegistryTLSResult.SYS_RESTART_SIGNAL:
                     result.setSystemRestartSignal(Integer.parseInt(value));
                     break;
                     case RegistryTLSResult.VERSION:
                     result.setVersion(value);
                     break;
                     default:
                     getLogger(CommonUtil.class.getName()).log(Level.CONFIG,
                     "Unuseful registry response JAXBElement : {0}", e.getName().toString());
                     }*/
                } else {
                    getLogger(CommonUtil.class.getName()).log(Level.INFO,
                            "Unuseful registry response com.sun.org.apache.xerces.internal.dom.ElementNSImpl Element : {0} with value {1}",
                            new Object[]{((ElementNSImpl) o).getLocalName(), ((ElementNSImpl) o).getFirstChild().getNodeValue()});
                }
            }
        } catch (JAXBException | NumberFormatException | DOMException ex) {
            getLogger(CommonUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    public static TLSResult parseTLSMessage(String TLSResp) {
        TLSResult result = null;

        try {
            TLSProcessor tlsp = new TLSProcessor().init();
            TLS raw = tlsp.parse(TLSResp);
            result = new TLSResult();
            for (Object o : raw.getAny()) {
                if (o instanceof JAXBElement) {
                    JAXBElement e = (JAXBElement) o;
                    final String value = e.getValue() == null ? "" : e.getValue().toString();
                    final String name = e.getName().getLocalPart();
                    if (name.equals(TLSResult.OK_MSG)) {
                        result.setOkMsg(value);
                    } else if (name.equals(TLSResult.ERR_MSG)) {
                        result.setErrMsg(value);
                    } else if (name.equals(TLSResult.RET_CODE)) {
                        result.setRetCode(Integer.parseInt(value));
                    } else if (name.equals(TLSResult.PRT_MSG)) {
                        result.setPrtMsg(value);
                    } else if (name.equals(TLSResult.CARGO)) {
                        putCargo(TLSResult.CARGO, value);
                    } else {
                        getLogger(CommonUtil.class.getName()).log(Level.CONFIG,
                                "Unuseful response JAXBElement : {0}", value);
                    }
                } else if (o instanceof ElementNSImpl) {
                    ElementNSImpl e = (ElementNSImpl) o;
                    final String value = e.getFirstChild() == null ? "" : e.getFirstChild().getNodeValue();
                    final String name = e.getLocalName();
                    if (name.equals(TLSResult.OK_MSG)) {
                        result.setOkMsg(value);
                    } else if (name.equals(TLSResult.ERR_MSG)) {
                        result.setErrMsg(value);
                    } else if (name.equals(TLSResult.RET_CODE)) {
                        result.setRetCode(Integer.parseInt(value));
                    } else if (name.equals(TLSResult.PRT_MSG)) {
                        result.setPrtMsg(value);
                    } else if (name.equals(TLSResult.CARGO)) {
                        putCargo(TLSResult.CARGO, value);
                    } else {
                        getLogger(CommonUtil.class.getName()).log(Level.INFO,
                                "Unuseful com.sun.org.apache.xerces.internal.dom.ElementNSImpl Element : {0} with value {1}",
                                new Object[]{e.getLocalName(), value});
                    }
                } else {
                    getLogger(CommonUtil.class.getName()).log(Level.INFO,
                            "Unknown xml element type : {0} with value {1}", new Object[]{o.getClass().getName(), o.toString()});
                }
            }
        } catch (JAXBException | NumberFormatException | DOMException ex) {
            getLogger(CommonUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    public static String createRegistryTLSMessage(String prsCode, String terminalID, String token) {
        String msg = null;
        try {
            TLSProcessor tlsp = new TLSProcessor().init();
            msg = tlsp.createRegistryMsg(prsCode, terminalID, token);
        } catch (JAXBException ex) {
            getLogger(CommonUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        return msg;
    }

    public static String createDownloadKeyTLSMessage(String terminalID, String token) {
        return createRegistryTLSMessage("downloadkey", terminalID, token);
    }

    public static String createTLSMessage(String prsCode, Map<Integer, String> contents) {
        String msg = "";
        try {
            TLSProcessor tlsp = new TLSProcessor().init();
            ArrayList<JAXBElement> list = new ArrayList<>();
            list.add(tlsp.createMsgCounterNode());
            list.add(tlsp.createMsgUidNode());
            list.add(tlsp.createPrsCodeNode(prsCode));
            list.add(tlsp.createTerminalID(TunnelBuilder.getTunnelBuilder().getTerminalID()));
            list.add(tlsp.createBranchno(TunnelBuilder.getTunnelBuilder().getBranchID()));
            if (retrieveCargo(CARGO) != null) {
                list.add(createJAXBElement(new QName("", CARGO), retrieveCargo(CARGO)));
                cleanTranStation();
            }
            Set<Map.Entry<Integer, String>> entrySet = contents.entrySet();
            for (Map.Entry<Integer, String> entry : entrySet) {
                list.add(createJAXBElement(entry.getKey().toString(), entry.getValue()));
            }
            msg = tlsp.buildTLS(list);
        } catch (JAXBException ex) {
            getLogger(CommonUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        return msg;
    }

    public static String createUnauthorizationTLSMessage(int retCode, String errMsg) {
        String msg = null;
        try {
            TLSProcessor tlsp = new TLSProcessor().init();
            msg = tlsp.mockRegistryResp(retCode, TunnelBuilder.getTunnelBuilder().getTerminalID(), "", errMsg, "", NORMAL, NORMAL);
        } catch (JAXBException ex) {
            getLogger(CommonUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        return msg;
    }

    public static String toStringDate(Date date) {
        SimpleDateFormat dateFormat = new java.text.SimpleDateFormat(CommonConstants.DEFAULT_DATE_FORMATE);
        return dateFormat.format(date);
    }

    public static Date parseString2Date(String date) throws ParseException {
        SimpleDateFormat dateFormat = new java.text.SimpleDateFormat(CommonConstants.DEFAULT_DATE_FORMATE);
        return dateFormat.parse(date);
    }

    private static long point = System.currentTimeMillis();

    public static long getStartTimeMillis() {
        return point;
    }

    public static void resetStartTimeMillis() {
        point = System.currentTimeMillis();
    }

    public static ArrayList<String> buildListParameters(String columnNames) {
        Scanner scanner = new Scanner(columnNames);
        scanner.useDelimiter(",");
        ArrayList<String> names = new ArrayList<>();
        while (scanner.hasNext()) {
            names.add(scanner.next().trim());
        }
        if (names.isEmpty()) {
            getLogger(CommonUtil.class.getName()).log(Level.WARNING, "Config empty list parameters");
            names.add("A");
            names.add("B");
            names.add("C");
            names.add("D");
        }
        return names;
    }

    public static Logger getLogger(String className) {
        return Logger.getLogger(className);
    }

    public static void showMsg(final Container parent, final String message) throws HeadlessException {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                JOptionPane.showMessageDialog(parent, message,
                        UIManager.getString("OptionPane.messageDialogTitle"), JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    public static void showErrorMsg(final Container parent, final String message) throws HeadlessException {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                JOptionPane.showMessageDialog(parent, message,
                        UIManager.getString("OptionPane.messageDialogTitle"), JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    public static void showOptionMsg(final Container parent, final String message,
            final SwingWorker task, final DefaultAction action) throws HeadlessException {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                int choice = JOptionPane.showConfirmDialog(parent, message,
                        UIManager.getString("OptionPane.messageDialogTitle"), JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.OK_OPTION) {
                    if (task != null && action != null) {
                        action.waitCommunicationTask(task);
                    }
                }
            }
        });
    }

    private static ResourceBundle bundle;

    static {
        try {
            bundle = new PropertyResourceBundle(
                    CommonUtil.class.getResourceAsStream("/lang/message.properties"));
        } catch (IOException ex) {
            Logger.getLogger(CommonUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static String getResourceMsg(String key) {
        if (bundle != null) {
            return bundle.getString(key);
        }
        return null;
    }

    private static final HashMap<DevicetypeEnum, String[]> driver = new HashMap<>();

    public static String getDriverDll(DevicetypeEnum device) {
        return driver.get(device)[0];
    }

    public static String getDriverPort(DevicetypeEnum device) {
        return driver.get(device)[1];
    }

    public static void saveDriverConf(DevicetypeEnum device, String[] conf) {
        driver.put(device, conf);
    }

    public static int readPrinterStatus() {
        int status = ABNORMAL;
        try {
            System.loadLibrary(getDriverDll(PRINTER));
            PrinterConductor printerConductor = new PrinterConductor();
            String errMsg = "";
            status = printerConductor.printState(getDriverPort(PRINTER), errMsg);
        } catch (Throwable t) {
            getLogger(CommonUtil.class.getName()).log(Level.WARNING, "Fail to load DLL {0} due to {1}",
                    new String[]{getDriverDll(PRINTER), t.getMessage()});
        }
        return status;
    }

    public static String readFingerPrint() {
        int objHdl = -1;
        byte[] pVer = new byte[513];
        String strVer = "";
        int nRet;

        int nDevPort = 0; //usb port is 0
        FingerPrintDrive driver = FingerPrintDrive.INSTANCE;
        objHdl = driver.TcCreateHDL(nDevPort, 0, 0, 0);
        if (objHdl <= 0) {
            showErrorMsg(currentView, getResourceMsg("finger.print.read.error"));
            return null;
        }
        nRet = driver.TcDoFeature(objHdl, pVer);
        if (nRet >= 0) {
            strVer = ByteToString(pVer, nRet);
            return strVer;
        } else {
            getLogger(CommonUtil.class.getName()).log(Level.INFO, "fail to read finger print, return code is {0}", nRet);
            nRet = driver.TcDeleteHDL(objHdl);
            if (nRet < 0) {
                getLogger(CommonUtil.class.getName()).log(Level.INFO, "fail to close finger print device, return code is {0}", nRet);
            }
            return null;
        }
    }

    public static String registryFingerPrint() {
        int objHdl = -1;
        byte[] pReg = new byte[513];
        String strReg = "";
        int nRet;

        int nDevPort = 0; //usb port is 0
        FingerPrintDrive driver = FingerPrintDrive.INSTANCE;
        objHdl = driver.TcCreateHDL(nDevPort, 0, 0, 0);
        if (objHdl <= 0) {
            showErrorMsg(currentView, getResourceMsg("finger.print.read.error"));
            return null;
        }
        nRet = driver.TcDoTemplet(objHdl, pReg);
        if (nRet >= 0) {
            strReg = new String(pReg);
            return strReg;
        } else {
            getLogger(CommonUtil.class.getName()).log(Level.INFO, "fail to registy finger print device, return code is {0}", nRet);
            nRet = driver.TcDeleteHDL(objHdl);
            if (nRet < 0) {
                getLogger(CommonUtil.class.getName()).log(Level.INFO, "fail to close finger print device, return code is {0}", nRet);
            }
            return null;
        }
    }

    private static String ByteToString(byte[] a, int nLen) {
        int nALen = a.length;

        if (nLen < 0) {
            for (int i = (nALen - 1); i > 0; i--) {
                if (a[i] != 0) {
                    nLen = i + 1;
                    break;
                }
            }
        }
        if (nALen < nLen) {
            nLen = nALen;
        }

        if (nLen == 0) {
            return "";
        } else {
            byte[] bstr = new byte[nLen];
            System.arraycopy(a, 0, bstr, 0, nLen);
            return new String(bstr);
        }
    }

    public static void printReceipt(String header, String content, String tail) {
        System.loadLibrary(getDriverDll(PRINTER));
        PrinterConductor printerConductor = new PrinterConductor();
        String errMsg = "";
        printerConductor.print(getDriverPort(PRINTER), header, content, tail, errMsg);
    }

    public static int printPassbook(String depositRecords) {
        System.loadLibrary(getDriverDll(PRINTER));
        PrinterConductor printerConductor = new PrinterConductor();
        String errMsg = "";
        return printerConductor.printPassBook(getDriverPort(PRINTER), depositRecords, errMsg);
    }

    public static String swipeMagnetCard() {
        System.loadLibrary(getDriverDll(MAGNET_CARD));
        MagnetCardConductor magnetCardConductor = new MagnetCardConductor();
        String errMsg = "";
        String cardNumber = magnetCardConductor.readCard(getDriverPort(MAGNET_CARD), errMsg);
        if (cardNumber != null && cardNumber.length() > 0) {
            int first = cardNumber.indexOf(';');
            int last = cardNumber.indexOf('=');
            cardNumber = cardNumber.substring(first + 1, last);
        }
        return cardNumber;
    }

    public static String swipeICCard() {
        System.loadLibrary(getDriverDll(IC_CARD));
        ICCardConductor icCardConductor = new ICCardConductor();
        String errMsg = "";
        String cardNumber = icCardConductor.readCard(getDriverPort(IC_CARD), errMsg);
        return cardNumber;
    }

    public static boolean downloadKey(String maKey, String masterKey, String workSecretKey) {
        System.loadLibrary(getDriverDll(KEYBOARD));
        KeyBoardDriver keyBoardDriver = new KeyBoardDriver();
        return keyBoardDriver.downloadSecretKey(getDriverPort(KEYBOARD),
                maKey, masterKey, workSecretKey);
    }

    public static String inputString(String str) {
        System.loadLibrary(getDriverDll(KEYBOARD));
        KeyBoardDriver keyBoardDriver = new KeyBoardDriver();
        String errMsg = "";
        return isKeyboardReady() ? keyBoardDriver.getPin(getDriverPort(KEYBOARD), str, errMsg)
                : keyBoardDriver.getPin("soft", str, errMsg);
    }

    public static String inputString(JPasswordField password) {
        String result = null;
        System.loadLibrary(getDriverDll(KEYBOARD));
        KeyBoardDriver keyBoardDriver = new KeyBoardDriver();
        String errMsg = "";
        keyBoardDriver.openPin(getDriverPort(KEYBOARD), errMsg);
        StringBuilder sb = new StringBuilder();
        long start = System.currentTimeMillis();
        while (true) {
            long now = System.currentTimeMillis();
            long duration = TimeUnit.MILLISECONDS.toSeconds(now - start);
            if (duration > 15) {
                CommonUtil.getLogger(CommonUtil.class.getName()).log(Level.INFO, "read pin time out");
                break;
            }
            char ch = keyBoardDriver.readPin();
            if (ch == 0) {
                continue;
            }
            sb.append(ch);
            password.setText(sb.toString());
            CommonUtil.getLogger(CommonUtil.class.getName()).log(Level.FINEST, "read pin result {0}",
                    Integer.toHexString(ch));
            if (ch == 0x0d || ch == 0xaa) {
                CommonUtil.getLogger(CommonUtil.class.getName()).log(Level.FINEST, "read pin ends up");
                result = keyBoardDriver.closePin();
                CommonUtil.getLogger(CommonUtil.class.getName()).log(Level.FINEST, "final reuslt {0}", result);
                break;
            }
        }

        return result;
    }

    private static boolean KEY_BOARD_READY = true;

    public static void setKeyboardStatus(boolean status) {
        KEY_BOARD_READY = status;
    }

    public static boolean isKeyboardReady() {
        return KEY_BOARD_READY;
    }

    private static TerminalStatus TERMINAL_STATUS = TerminalStatus.UNKNOWN;

    public static enum TerminalStatus {

        /**
         * bank app starts to run and no UI visible
         */
        STARTUP(NORMAL),
        /**
         * bank app has set visible true and all show up
         */
        RUNNING(NORMAL),
        /**
         * bank app restarts
         */
        RESTART(NORMAL),
        /**
         * bank app initial status
         */
        UNKNOWN(NORMAL);

        private final int currentStatus;

        private TerminalStatus(int status) {
            this.currentStatus = status;
        }

        public int getStatusValue() {
            return currentStatus;
        }

    }

    public static TerminalStatus getTerminalStatus() {
        return TERMINAL_STATUS;
    }

    public static int getTerminalStatusValue() {
        return TERMINAL_STATUS.getStatusValue();
    }

    public static void setTerminalStatus(TerminalStatus status) {
        TERMINAL_STATUS = status;
    }

    public static ImageIcon getImageIcon(String icon) {
        return new ImageIcon(CommonUtil.class.getClass().getResource(icon));
    }

    public static String sendMessage(String msg) throws Exception {
        return sendMessage(GENERAL_MSG_CODE, msg);
    }

    public static String sendMessage(int cmdCode, String msg) throws Exception {
        String resp = null;
        try {
            switch (getAppType()) {
                case TERMINAL:
                case EXHIBITION:
                    resp = getTunnelBuilder().createTunnel().connect(cmdCode, msg);
                    break;
                case MANAGEMENT:
                    resp = getTunnelBuilder().createTunnel().connect(MANAGER_MSG_CODE, msg);
                    break;
                default:
                    getLogger(CommonUtil.class.getName()).log(Level.SEVERE, "Unknown App type({0}) and don't know how to deal message", getAppType());
            }
        } catch (Exception e) {
            getLogger(CommonUtil.class.getName()).log(Level.SEVERE, "Networks cannot be accessed normally now", e);
            throw e;
        }
        return resp;
    }

    public static void cachePrintMsg(String prtMsg) {
        System.setProperty(XML_NODE_PREFIX, prtMsg == null ? "" : prtMsg);
    }

    public static void cleanPrintMsg() {
        System.setProperty(XML_NODE_PREFIX, "");
    }

    public static String retrievePrintMsg() {
        return System.getProperty(XML_NODE_PREFIX);
    }

    public static enum AppType {

        TERMINAL, MANAGEMENT, EXHIBITION
    }

    private static AppType type = AppType.TERMINAL;

    public static AppType getAppType() {
        return type;
    }

    public static void registryAppType(AppType currentType) {
        type = currentType;
    }

    public static class DownloadCipherKey extends SwingWorker<Boolean, Integer> {

        @Override
        protected Boolean doInBackground() throws Exception {
            Thread.sleep(TimeUnit.SECONDS.toMillis(2));
            if (getAppType() != TERMINAL) {
                return true;
            }
            boolean result = false;
            final TunnelBuilder tb = TunnelBuilder.getTunnelBuilder();
            for (int i = 0; i < 1; i++) {
                if (result) {
                    break;
                }
                Thread.sleep(TimeUnit.SECONDS.toMillis(3));
                Tunnel tunnel = tb.createTunnel();
                final String downloadKeyMessage = createDownloadKeyTLSMessage(tb.getTerminalID(), tb.getToken());
                getLogger(DownloadCipherKey.class.getName()).log(Level.INFO, "Key TLS Message : {0}", downloadKeyMessage);
                String resp = tunnel.connect(KEY_DOWNLOAD_MSG_CODE, downloadKeyMessage);
                getLogger(DownloadCipherKey.class.getName()).log(Level.INFO, "retrieve downloadKey : {0}", resp);
                if (resp == null) {
                    result = false;
                } else {
                    TLSResult tlsResult = parseTLSMessage(resp);
                    if (tlsResult.getRetCode() == SUCCESSFUL_MSG_CODE) {
                        String okMsg = tlsResult.getOkMsg();
                        JsonParser parser = new JsonParser();
                        JsonElement element = parser.parse(okMsg);
                        if (element.isObject()) {
                            JsonObject jObj = element.asObject();
                            String maKey = jObj.getString(CommonConstants.MA_KEY);
                            String masterKey = jObj.getString(CommonConstants.MASTER_KEY);
                            String workSecretKey = jObj.getString(CommonConstants.WORK_SECRET_KEY);
                            result = (maKey != null && maKey.length() > 0
                                    && masterKey != null && masterKey.length() > 0
                                    && workSecretKey != null && workSecretKey.length() > 0);
                            if (result) {
                                if (!secret.contains(maKey) || !secret.contains(masterKey) || !secret.contains(workSecretKey)) {
                                    result = downloadKey(maKey, masterKey, workSecretKey);
                                    getLogger(DownloadCipherKey.class.getName()).log(Level.INFO, "save key to keyboard : {0}", result);
                                    secret.clear();
                                    if (result) {
                                        secret.addAll(Arrays.asList(maKey, masterKey, workSecretKey));
                                    }
                                } else {
                                    getLogger(DownloadCipherKey.class.getName()).log(Level.INFO,
                                            "no need of saving key to keyboard due to no change");
                                }
                            }
                        } else {
                            result = false;
                            getLogger(DownloadCipherKey.class.getName()).log(Level.INFO, "key data json format is not correct");
                        }
                    } else {
                        result = false;
                        getLogger(DownloadCipherKey.class.getName()).log(Level.INFO, "download key fails, get error retCode");
                    }
                }
            }
            return result;
        }

    }

    private static final Set<String> secret = new HashSet<>();

    private static Window currentView = null;

    public static Window getCurrentView() {
        return currentView;
    }

    public static void setCurrentView(Window window) {
        currentView = window;
    }

    private static final AtomicLong counter = new AtomicLong(1L);

    public static long tellMeCounter() {
        return counter.getAndIncrement();
    }

    /**
     * Message uid format including time with precision to milliseconds and day
     * number in this year, 20 character length, for example,
     * 20150607231215658159, DDD means today is 159 day in this year
     */
    private final static SimpleDateFormat MSG_UID_FORMAT = new SimpleDateFormat("yyyyMMddHHmmssSSSDDD");

    public static String tellMeMsgUid() {
        return MSG_UID_FORMAT.format(Calendar.getInstance().getTime());
    }

    private static final Map<String, String> transitionStation = new HashMap<>();

    public static void putCargo(String tagName, String cargo) {
        transitionStation.put(tagName, cargo);
    }

    public static String retrieveCargo(String tagName) {
        return transitionStation.get(tagName);
    }

    public static void cleanTranStation() {
        transitionStation.clear();
    }

    private static String INPUT_METHOD_ACTIVE_CMD = "";
    private static String INPUT_METHOD_UNACTIVE_CMD = "";

    public static String getInputMethodActiveCmd() {
        return INPUT_METHOD_ACTIVE_CMD;
    }

    public static void setInputMethodActiveCmd(String cmd) {
        INPUT_METHOD_ACTIVE_CMD = cmd;
    }

    public static String getInputMethodUnactiveCmd() {
        return INPUT_METHOD_UNACTIVE_CMD;
    }

    public static void setInputMethodUnactiveCmd(String cmd) {
        INPUT_METHOD_UNACTIVE_CMD = cmd;
    }

    public static boolean usbDeviceConnected() {
        return File.listRoots().length > CommonConstants.DRIVER_COUNT;
    }

    public static File getUsbDevice() {
        if (usbDeviceConnected()) {
            File[] listRoots = File.listRoots();
            List<File> currentRoots = new ArrayList<>(Arrays.asList(listRoots));
            List<File> sysRoots = new ArrayList<>(Arrays.asList(CommonConstants.SYS_ROOTS));
            currentRoots.removeAll(sysRoots);
            return currentRoots.get(0);
        } else {
            return null;
        }
    }

    private final static Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
    private final static double WIDTH_SCALE = SCREEN_SIZE.getWidth() / 1280.0d;
    private final static double HEIGHT_SCALE = SCREEN_SIZE.getHeight() / 1024.0d;

    public static Bound adjustByResolution(Bound rawRect) {
        final Bound adjustment = new Bound();
        adjustment.setX(BigInteger.valueOf(Math.round(rawRect.getX().doubleValue() * WIDTH_SCALE)));
        adjustment.setY(BigInteger.valueOf(Math.round(rawRect.getY().doubleValue() * HEIGHT_SCALE)));
        adjustment.setWidth(BigInteger.valueOf(Math.round(rawRect.getWidth().doubleValue() * WIDTH_SCALE)));
        adjustment.setHeigh(BigInteger.valueOf(Math.round(rawRect.getHeigh().doubleValue() * HEIGHT_SCALE)));
        return adjustment;
    }
}
