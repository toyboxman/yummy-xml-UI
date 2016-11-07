package king.flow.common;

import com.github.jsonj.JsonElement;
import com.github.jsonj.JsonObject;
import com.github.jsonj.tools.JsonParser;
import com.sun.org.apache.xerces.internal.dom.ElementNSImpl;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.Window;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
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
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import king.flow.action.DefaultAction;
import static king.flow.common.CommonConstants.ABNORMAL;
import static king.flow.common.CommonConstants.BALANCED_PAY_MAC;
import static king.flow.common.CommonConstants.GENERAL_MSG_CODE;
import static king.flow.common.CommonConstants.KEY_DOWNLOAD_MSG_CODE;
import static king.flow.common.CommonConstants.MANAGER_MSG_CODE;
import static king.flow.common.CommonConstants.NORMAL;
import static king.flow.common.CommonConstants.SUCCESSFUL_MSG_CODE;
import static king.flow.common.CommonConstants.WATCHDOG_CHECK_INTERVAL;
import static king.flow.common.CommonConstants.XML_NODE_PREFIX;
import static king.flow.common.CommonUtil.AppType.TERMINAL;
import king.flow.control.MainWindow;
import king.flow.control.driver.FingerPrintDrive;
import king.flow.control.driver.GzCardConductor;
import king.flow.control.driver.ICCardConductor;
import king.flow.control.driver.KeyBoardDriver;
import king.flow.control.driver.MagnetCardConductor;
import king.flow.control.driver.PKG8583;
import king.flow.control.driver.PrinterConductor;
import king.flow.control.driver.TwoInOneCardConductor;
import king.flow.data.RegistryTLSResult;
import king.flow.data.TLS;
import king.flow.data.TLSResult;
import static king.flow.data.TLSResult.CARGO;
import king.flow.design.TLSProcessor;
import king.flow.net.Tunnel;
import king.flow.net.TunnelBuilder;
import static king.flow.net.TunnelBuilder.getTunnelBuilder;
import king.flow.view.Bound;
import king.flow.view.Component;
import king.flow.view.DeviceEnum;
import static king.flow.view.DeviceEnum.GZ_CARD;
import static king.flow.view.DeviceEnum.IC_CARD;
import static king.flow.view.DeviceEnum.KEYBOARD;
import static king.flow.view.DeviceEnum.MAGNET_CARD;
import static king.flow.view.DeviceEnum.PRINTER;
import static king.flow.view.DeviceEnum.TWO_IN_ONE_CARD;
import king.flow.view.FontstyleEnum;
import static king.flow.view.FontstyleEnum.BOLD;
import static king.flow.view.FontstyleEnum.ITALIC;
import static king.flow.view.FontstyleEnum.PLAIN;
import org.w3c.dom.DOMException;
import static king.flow.data.TLSResult.UNIONPAY_CARD_INFO;
import static king.flow.data.TLSResult.UNIONPAY_MAC_INFO;
import king.flow.net.Transportation;
import static king.flow.view.DeviceEnum.PKG_8583;

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
                    getLogger(CommonUtil.class.getName()).log(Level.CONFIG,
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
                    } else if (name.equals(TLSResult.REDIRECTION)) {
                        result.setRedirection(value);
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
                    } else if (name.equals(TLSResult.REDIRECTION)) {
                        result.setRedirection(value);
                    } else {
                        getLogger(CommonUtil.class.getName()).log(Level.CONFIG,
                                "Unuseful com.sun.org.apache.xerces.internal.dom.ElementNSImpl Element : {0} with value {1}",
                                new Object[]{e.getLocalName(), value});
                    }
                } else {
                    getLogger(CommonUtil.class.getName()).log(Level.INFO,
                            "Unknown xml element type : {0} with value {1}",
                            new Object[]{o.getClass().getName(), o.toString()});
                }
            }
        } catch (JAXBException | NumberFormatException | DOMException ex) {
            getLogger(CommonUtil.class.getName()).log(Level.WARNING,
                    "Invalid response received:\n{0} \nresponse parsing hits error :\n{1} ",
                    new Object[]{TLSResp, ex.getMessage()});
        }

        return result;
    }

    public static String createRegistryTLSMessage(String prsCode, String terminalID, String token) {
        String msg = null;
        try {
            TLSProcessor tlsp = new TLSProcessor().init();
            msg = tlsp.createRegistryMsg(prsCode, terminalID, token);
        } catch (JAXBException ex) {
            getLogger(CommonUtil.class.getName()).log(Level.WARNING,
                    "fail to create registry TLS message due to :\n{0}", ex.getMessage());
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
            list.add(tlsp.createUnionPayID(TunnelBuilder.getTunnelBuilder().getUnionPayID()));
            list.add(tlsp.createBranchno(TunnelBuilder.getTunnelBuilder().getBranchID()));
            if (retrieveCargo(CARGO) != null) {
                list.add(createJAXBElement(new QName("", CARGO), retrieveCargo(CARGO)));
                cleanTranStation(CARGO);
            }
            if (retrieveCargo(UNIONPAY_CARD_INFO) != null) {
                list.add(createJAXBElement(new QName("", UNIONPAY_CARD_INFO),
                        retrieveCargo(UNIONPAY_CARD_INFO)));
                cleanTranStation(UNIONPAY_CARD_INFO);
            }
            if (retrieveCargo(UNIONPAY_MAC_INFO) != null) {
                list.add(createJAXBElement(new QName("", UNIONPAY_MAC_INFO),
                        retrieveCargo(UNIONPAY_MAC_INFO)));
                cleanTranStation(UNIONPAY_MAC_INFO);
            }
            if (retrieveCargo(BALANCED_PAY_MAC) != null) {
                list.add(createJAXBElement(new QName("", BALANCED_PAY_MAC),
                        retrieveCargo(BALANCED_PAY_MAC)));
            }
            Set<Map.Entry<Integer, String>> entrySet = contents.entrySet();
            for (Map.Entry<Integer, String> entry : entrySet) {
                list.add(createJAXBElement(entry.getKey().toString(), entry.getValue()));
            }
            msg = tlsp.buildTLS(list);
        } catch (JAXBException ex) {
            getLogger(CommonUtil.class.getName()).log(Level.WARNING,
                    "fail to create TLS message due to :\n{0}", ex.getMessage());
        }

        return msg;
    }

    public static String createUnauthorizationTLSMessage(int retCode, String errMsg) {
        String msg = null;
        try {
            TLSProcessor tlsp = new TLSProcessor().init();
            msg = tlsp.mockRegistryResp(retCode,
                    TunnelBuilder.getTunnelBuilder().getTerminalID(),
                    "",
                    errMsg,
                    "",
                    NORMAL,
                    NORMAL);
        } catch (JAXBException ex) {
            getLogger(CommonUtil.class.getName()).log(Level.WARNING,
                    "fail to create unauthorized TLS message due to :\n{0}", ex.getMessage());
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

    public static void showBlockedErrorMsg(final Container parent, final String message, final boolean exit) throws HeadlessException {
        JOptionPane.showMessageDialog(parent, message,
                UIManager.getString("OptionPane.messageDialogTitle"), JOptionPane.ERROR_MESSAGE);
        if (exit) {
            System.exit(-1);
        }
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
            Logger.getLogger(CommonUtil.class.getName()).log(Level.WARNING,
                    "fail to load resource due to error :\n{0}", ex.getMessage());
        }
    }

    public static String getResourceMsg(String key) {
        try {
            return bundle.getString(key);
        } catch (Exception e) {
            Logger.getLogger(CommonUtil.class.getName()).log(Level.WARNING,
                    "fail to catch resource item by key[{0}] due to error :\n{1}",
                    new Object[]{key, e.getMessage()});
        }
        return null;
    }

    /* finger printer driver section */
    private static volatile int fingerPrintController = -1;

    public static void closeFingerPrint() {
        FingerPrintDrive driver = FingerPrintDrive.INSTANCE;
        if (fingerPrintController > 0) {
            closeFingerDrvHandler(driver, fingerPrintController);
        } else {
            getLogger(CommonUtil.class.getName()).log(Level.INFO,
                    "no more finger print device needs to be closed");
        }
    }

    private static void closeFingerDrvHandler(FingerPrintDrive driver, int objHdl) {
        int nRet = driver.TcDeleteHDL(objHdl);
        if (nRet < 0) {
            getLogger(CommonUtil.class.getName()).log(Level.WARNING,
                    "fail to close handler of finger print device {0}, return code is {1}",
                    new Object[]{objHdl, nRet});
        }
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
            return strVer;
        }
        getLogger(CommonUtil.class.getName()).log(Level.INFO,
                "opened handler of finger print device is {0}", objHdl);

        nRet = driver.TcDoFeature(objHdl, pVer);
        if (nRet >= 0) {
            strVer = ByteToString(pVer, nRet);
        } else {
            getLogger(CommonUtil.class.getName()).log(Level.WARNING,
                    "fail to read finger print, return code is {0}", nRet);
        }
        closeFingerDrvHandler(driver, objHdl);
        return strVer;
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
            return strReg;
        }
        getLogger(CommonUtil.class.getName()).log(Level.INFO,
                "opened handler of finger print device is {0}", objHdl);

        nRet = driver.TcDoTemplet(objHdl, pReg);
        if (nRet >= 0) {
            strReg = ByteToString(pReg, nRet);
        } else {
            getLogger(CommonUtil.class.getName()).log(Level.WARNING,
                    "fail to registy finger print device, return code is {0}", nRet);
        }
        closeFingerDrvHandler(driver, objHdl);
        return strReg;
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

    /* finger printer driver section end*/
 /* JNI type driver calling*/
    private static final HashMap<DeviceEnum, String[]> driverRepo = new HashMap<>();
    final static String DRIVER_LOG_TEMPLATE = "Fail to call driver {0} due to error:\n{1}";

    private static void lookForDriverConfig(DeviceEnum device) throws Exception {
        String[] deviceConf = driverRepo.get(device);
        if (deviceConf == null || deviceConf.length < 2) {
            throw new Exception("Fail to look for "
                    + device.name()
                    + " driver configuration in xml_window.xml");
        }
    }

    public static String getDriverDll(DeviceEnum device) throws Exception {
        lookForDriverConfig(device);
        return driverRepo.get(device)[0];
    }

    public static String getDriverPort(DeviceEnum device) throws Exception {
        lookForDriverConfig(device);
        return driverRepo.get(device)[1];
    }

    public static void saveDriverConf(DeviceEnum device, String[] conf) {
        driverRepo.put(device, conf);
    }

    public static int readPrinterStatus() {
        int status = ABNORMAL;
        try {
            String driverName = getDriverDll(PRINTER);
            System.loadLibrary(driverName);
            PrinterConductor printerConductor = new PrinterConductor();
            String errMsg = "";
            status = printerConductor.printState(getDriverPort(PRINTER), errMsg);
        } catch (Throwable t) {
            getLogger(CommonUtil.class.getName()).log(Level.WARNING,
                    DRIVER_LOG_TEMPLATE,
                    new String[]{PRINTER.value(), t.getMessage()});
        }
        return status;
    }

    // print receipt
    public static void printReceipt(String header, String content, String tail) {
        try {
            System.loadLibrary(getDriverDll(PRINTER));
            PrinterConductor printerConductor = new PrinterConductor();
            String errMsg = "";
            printerConductor.print(getDriverPort(PRINTER), header, content, tail, errMsg);
        } catch (Throwable t) {
            Logger.getLogger(CommonUtil.class.getName()).log(Level.WARNING,
                    DRIVER_LOG_TEMPLATE,
                    new String[]{PRINTER.value(), t.getMessage()});
        }
    }

    public static int printPassbook(String depositRecords) {
        int result = ABNORMAL;
        try {
            System.loadLibrary(getDriverDll(PRINTER));
            PrinterConductor printerConductor = new PrinterConductor();
            String errMsg = "";
            result = printerConductor.printPassBook(getDriverPort(PRINTER), depositRecords, errMsg);
        } catch (Throwable t) {
            Logger.getLogger(CommonUtil.class.getName()).log(Level.WARNING,
                    DRIVER_LOG_TEMPLATE,
                    new String[]{PRINTER.value(), t.getMessage()});
        }
        return result;
    }

    public static String swipeMagnetCard() {
        String cardNumber = null;
        try {
            System.loadLibrary(getDriverDll(MAGNET_CARD));
            MagnetCardConductor magnetCardConductor = new MagnetCardConductor();
            String errMsg = "";
            cardNumber = magnetCardConductor.readCard(getDriverPort(MAGNET_CARD), errMsg);
            if (cardNumber != null && cardNumber.length() > 0) {
                int first = cardNumber.indexOf(';');
                int last = cardNumber.indexOf('=');
                cardNumber = cardNumber.substring(first + 1, last);
            }
        } catch (Throwable t) {
            Logger.getLogger(CommonUtil.class.getName()).log(Level.WARNING,
                    DRIVER_LOG_TEMPLATE,
                    new String[]{MAGNET_CARD.value(), t.getMessage()});
        }
        return cardNumber;
    }

    public static String swipeICCard() {
        String cardInfo = null;
        try {
            System.loadLibrary(getDriverDll(IC_CARD));
            ICCardConductor icCardConductor = new ICCardConductor();
            cardInfo = icCardConductor.readCard(getDriverPort(IC_CARD), "");
        } catch (Throwable t) {
            Logger.getLogger(CommonUtil.class.getName()).log(Level.WARNING,
                    DRIVER_LOG_TEMPLATE,
                    new String[]{IC_CARD.value(), t.getMessage()});
        }
        return cardInfo;
    }

    private static JsonObject cardInfoCache = null;

    public static void cacheCardInfo(JsonObject cardInfo) {
        cardInfoCache = cardInfo;
    }

    public static JsonObject uncacheCardInfo() {
        return cardInfoCache;
    }

    public static String swipeGzICCard() throws Throwable {
        String cardInfo = null;
        try {
            System.loadLibrary(getDriverDll(GZ_CARD));
            getLogger(CommonUtil.class.getName()).log(Level.INFO,
                    "Read guozhen IC card by {0} and port {1}",
                    new Object[]{getDriverDll(GZ_CARD), getDriverPort(GZ_CARD)});
            GzCardConductor icCardConductor = new GzCardConductor();
            cardInfo = icCardConductor.readCard(getDriverPort(GZ_CARD));
        } catch (Throwable t) {
            Logger.getLogger(CommonUtil.class.getName()).log(Level.WARNING,
                    DRIVER_LOG_TEMPLATE,
                    new String[]{GZ_CARD.value(), t.getMessage()});
            throw t;
        }
        return cardInfo;
    }

    public static int writeGzICCard(JsonObject cardInfo) throws Throwable {
        int result = ABNORMAL;
        try {
            System.loadLibrary(getDriverDll(GZ_CARD));
            getLogger(CommonUtil.class.getName()).log(Level.INFO,
                    "Write guozhen IC card by {0} and port {1}",
                    new Object[]{getDriverDll(GZ_CARD), getDriverPort(GZ_CARD)});
            GzCardConductor icCardConductor = new GzCardConductor();
            result = icCardConductor.writeCard(getDriverPort(GZ_CARD),
                    Integer.parseInt(cardInfo.getString(GzCardConductor.CARD_FACTORY)),
                    cardInfo.toString());
        } catch (Throwable t) {
            Logger.getLogger(CommonUtil.class.getName()).log(Level.WARNING,
                    DRIVER_LOG_TEMPLATE,
                    new String[]{GZ_CARD.value(), t.getMessage()});
            throw t;
        }
        return result;
    }

    private static final TwoInOneCardConductor TIOCardConductor = new TwoInOneCardConductor();

    public static int check2In1Card() {
        int checkResult = ABNORMAL;
        try {
            System.loadLibrary(getDriverDll(TWO_IN_ONE_CARD));
            String errMsg = "";
            checkResult = TIOCardConductor.checkCard(getDriverPort(TWO_IN_ONE_CARD), errMsg);
        } catch (Throwable t) {
            Logger.getLogger(CommonUtil.class.getName()).log(Level.WARNING,
                    DRIVER_LOG_TEMPLATE,
                    new String[]{TWO_IN_ONE_CARD.value(), t.getMessage()});
        }
        return checkResult;
    }

    public static String swipe2In1Card(int type) {
        String cardNumber = null;
        try {
            System.loadLibrary(getDriverDll(TWO_IN_ONE_CARD));
            String errMsg = "";
            cardNumber = TIOCardConductor.readCard(getDriverPort(TWO_IN_ONE_CARD), type, errMsg);
        } catch (Throwable t) {
            Logger.getLogger(CommonUtil.class.getName()).log(Level.WARNING,
                    DRIVER_LOG_TEMPLATE,
                    new String[]{TWO_IN_ONE_CARD.value(), t.getMessage()});
        }
        return cardNumber;
    }
    
    public static void eject2In1Card() {
        try {
            System.loadLibrary(getDriverDll(TWO_IN_ONE_CARD));
            String errMsg = "";
            TIOCardConductor.ejectCard(getDriverPort(TWO_IN_ONE_CARD), errMsg);
        } catch (Throwable t) {
            Logger.getLogger(CommonUtil.class.getName()).log(Level.WARNING,
                    DRIVER_LOG_TEMPLATE,
                    new String[]{TWO_IN_ONE_CARD.value(), t.getMessage()});
        }
    }

    public static boolean downloadKey(String maKey, String masterKey, String workSecretKey) {
        boolean result = false;
        try {
            System.loadLibrary(getDriverDll(KEYBOARD));
            KeyBoardDriver keyBoardDriver = new KeyBoardDriver();
            result = keyBoardDriver.downloadSecretKey(getDriverPort(KEYBOARD),
                    maKey, masterKey, workSecretKey);
        } catch (Throwable t) {
            Logger.getLogger(CommonUtil.class.getName()).log(Level.WARNING,
                    DRIVER_LOG_TEMPLATE,
                    new String[]{KEYBOARD.value(), t.getMessage()});
        }
        return result;
    }

    public static int openEncryptedKeyboard() {
        int result = -1;
        try {
            System.loadLibrary(getDriverDll(KEYBOARD));
            KeyBoardDriver keyBoardDriver = new KeyBoardDriver();
            String errMsg = "";
            result = keyBoardDriver.OpenComm(getDriverPort(KEYBOARD), errMsg);
        } catch (Throwable t) {
            Logger.getLogger(CommonUtil.class.getName()).log(Level.WARNING,
                    DRIVER_LOG_TEMPLATE,
                    new String[]{KEYBOARD.value(), t.getMessage()});
        }
        return result;
    }

    public static int closeEncryptedKeyboard() {
        int result = -1;
        try {
            System.loadLibrary(getDriverDll(KEYBOARD));
            KeyBoardDriver keyBoardDriver = new KeyBoardDriver();
            String errMsg = "";
            result = keyBoardDriver.CloseComm(getDriverPort(KEYBOARD), errMsg);
        } catch (Throwable t) {
            Logger.getLogger(CommonUtil.class.getName()).log(Level.WARNING,
                    DRIVER_LOG_TEMPLATE,
                    new String[]{KEYBOARD.value(), t.getMessage()});
        }
        return result;
    }

    public static String readEncryptedString(JPasswordField password) {
        String result = CommonConstants.ERROR_ENCRYPTION_TYPE;
        try {
            System.loadLibrary(getDriverDll(KEYBOARD));
            KeyBoardDriver keyBoardDriver = new KeyBoardDriver();
            String errMsg = "";
            StringBuilder sb = new StringBuilder();
            final long start = System.currentTimeMillis();
            while (true) {
                if (!password.isShowing() || !password.isFocusOwner()) {
                    return CommonConstants.QUIT_ENCRYPTION_KEYBOARD;
                }
                Thread.sleep(200);
                long now = System.currentTimeMillis();
                long duration = TimeUnit.MILLISECONDS.toSeconds(now - start);
                if (duration > 20) {
                    CommonUtil.getLogger(CommonUtil.class.getName()).log(Level.INFO,
                            "read pin time out as duration {0} greater than 20s", duration);
                    password.setText("");
                    return CommonConstants.TIMEOUT_ENCRYPTION_TYPE;
                }
                int typeValue = keyBoardDriver.ScanKeyPress(errMsg);
                if (typeValue == -1) {
                    continue;
                }

                char ch = (char) typeValue; //char type is unsigned, it cannot be -1
                switch (ch) {
                    case 0x08://backspace
                        CommonUtil.getLogger(CommonUtil.class.getName()).log(Level.INFO,
                                "backspace is hit value {0}", Integer.toHexString(ch));
                        if (sb.length() > 0) {
                            sb.deleteCharAt(sb.length() - 1);
                        }
                        break;
                    case 0xFF:
                        CommonUtil.getLogger(CommonUtil.class.getName()).log(Level.INFO,
                                "clean is hit value {0}", Integer.toHexString(ch));
                        sb.delete(0, sb.length() - 1);
                        break;
                    case 0x1b:
                        CommonUtil.getLogger(CommonUtil.class.getName()).log(Level.INFO,
                                "quit is hit value {0}", Integer.toHexString(ch));
                        password.setText("");
                        return CommonConstants.CANCEL_ENCRYPTION_KEYBOARD;
                    case 0x0d:
                        CommonUtil.getLogger(CommonUtil.class.getName()).log(Level.INFO,
                                "submit is hit value {0}", Integer.toHexString(ch));
                        password.setText("");
                        return CommonConstants.INVALID_ENCRYPTION_LENGTH;
                    /*if (sb.length() > 0) {
                            CommonUtil.getLogger(CommonUtil.class.getName()).log(Level.INFO,
                                    "read pin operation ends up");
                            String cardNum = retrieveCargo(CommonConstants.VALID_BANK_CARD);
                            String cardType = retrieveBankCardAffiliation(cardNum);
                            result = keyBoardDriver.GetPinblock(cardNum, cardType, errMsg);
                            CommonUtil.getLogger(CommonUtil.class.getName()).log(Level.INFO,
                                    "final pinblock reuslt: {0}", result);
                            return result;
                        }*/
                    case 0x2a:
                        CommonUtil.getLogger(CommonUtil.class.getName()).log(Level.INFO,
                                "useful keyborad type value {0}/{1}",
                                new Object[]{Integer.toHexString(ch), ch});
                        sb.append(ch);
                        break;
                    default:
                        CommonUtil.getLogger(CommonUtil.class.getName()).log(Level.INFO,
                                "invalid keyborad type value {0}/{1}",
                                new Object[]{Integer.toHexString(ch), ch});
                        sb.append(ch);
                        continue;
                }
                CommonUtil.getLogger(CommonUtil.class.getName()).log(Level.INFO,
                        "keyboard type content length {0}/{1}",
                        new Object[]{sb.length(), sb.toString()});
                password.setText(sb.toString());
                if (sb.length() == 6) {
                    CommonUtil.getLogger(CommonUtil.class.getName()).log(Level.INFO,
                            "read pin operation automatically ends up to maximun length");
                    String cardNum = retrieveCargo(CommonConstants.VALID_BANK_CARD);
                    String cardType = retrieveBankCardAffiliation(cardNum);
                    result = keyBoardDriver.GetPinblock(cardNum, cardType, errMsg);
                    CommonUtil.getLogger(CommonUtil.class.getName()).log(Level.INFO,
                            "final pinblock reuslt: {0}", result);
                    return result;
                }
            }
        } catch (Throwable t) {
            Logger.getLogger(CommonUtil.class.getName()).log(Level.WARNING,
                    DRIVER_LOG_TEMPLATE,
                    new String[]{KEYBOARD.value(), t.getMessage()});
        }
        return result;
    }

    public static String calculateMAC(
            String transType,
            String cardJson,
            String pinblock,
            String money) {
        String mac = null;
        try {
            System.loadLibrary(getDriverDll(PKG_8583));
            PKG8583 pkG8583 = new PKG8583();
            String errMsg = "";
            mac = pkG8583.getMac(
                    getDriverPort(PKG_8583),
                    transType, cardJson, pinblock,
                    money,
                    TunnelBuilder.getTunnelBuilder().getTerminalID(),
                    TunnelBuilder.getTunnelBuilder().getUnionPayID(),
                    errMsg);
            CommonUtil.getLogger(CommonUtil.class.getName()).log(Level.INFO,
                    "final mac result: {0}", mac);
        } catch (Throwable t) {
            Logger.getLogger(CommonUtil.class.getName()).log(Level.WARNING,
                    DRIVER_LOG_TEMPLATE,
                    new String[]{PKG_8583.value(), t.getMessage()});
        }
        return mac;
    }

    public static String inputString(String str) {
        String transformer = str;
        try {
            System.loadLibrary(getDriverDll(KEYBOARD));
            KeyBoardDriver keyBoardDriver = new KeyBoardDriver();
            String errMsg = "";
            transformer = isKeyboardReady() ? keyBoardDriver.getPin(getDriverPort(KEYBOARD), str, errMsg)
                    : keyBoardDriver.getPin("soft", str, errMsg);
        } catch (Throwable t) {
            Logger.getLogger(CommonUtil.class.getName()).log(Level.WARNING,
                    DRIVER_LOG_TEMPLATE,
                    new String[]{KEYBOARD.value(), t.getMessage()});
        }
        return transformer;
    }

    public static String inputString(JPasswordField password) {
        String result = null;
        try {
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
        } catch (Throwable t) {
            Logger.getLogger(CommonUtil.class.getName()).log(Level.WARNING,
                    DRIVER_LOG_TEMPLATE,
                    new String[]{KEYBOARD.value(), t.getMessage()});
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

    public static StringBuilder buildErrMsg(String type, int id, String pageURI) {
        StringBuilder errMsg = new StringBuilder().append(type)
                .append('[').append(id).append(']')
                .append(" in ").append(pageURI)
                .append('\n').append("conflicts with the other one using same ID");
        return errMsg;
    }

    public static String replaceSysVar(String text) {
        if (text == null) {
            return "";
        }

        if (text.matches(CommonConstants.TEXT_MINGLED_SYSTEM_VAR_PATTERN)) {
            Pattern pattern = Pattern.compile(CommonConstants.SYSTEM_VAR_PATTERN);
            Matcher matcher = pattern.matcher(text);
            matcher.find();
            final String systemVar = matcher.group();
            final String varName = systemVar.substring(systemVar.indexOf('{') + 1, systemVar.indexOf('}', systemVar.indexOf('}')));
            switch (varName) {
                case CommonConstants.TERMINAL_ID_SYS_VAR:
                    /* Matcher.quoteReplacement is just for the case of replacing "\\", otherwise replace method will drop "\\" in replcaement parameter */
                    text = text.replaceFirst(CommonConstants.SYSTEM_VAR_PATTERN, Matcher.quoteReplacement(TunnelBuilder.getTunnelBuilder().getTerminalID()));
                    break;
                default:
                    getLogger(MainWindow.class.getName()).log(Level.WARNING, "Unknown system variable {0}", systemVar);
            }
        }
        return text;
    }

    public static Color getTrueColor(String colorRGBDefinition) {
        List<String> rgb = buildListParameters(colorRGBDefinition);
        if (rgb.size() == 3) {
            try {
                Color baseColor = new Color(Integer.parseInt(rgb.get(0)), Integer.parseInt(rgb.get(1)), Integer.parseInt(rgb.get(2)));
                return baseColor;
            } catch (NumberFormatException numberFormatException) {
                return null;
            }
        } else {
            return null;
        }
    }

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

    public static boolean isValidResource(String name) {
        return !(CommonUtil.class.getResource(name) == null);
    }

    public static ImageIcon getImageIcon(String icon) {
        return isValidResource(icon) ? new ImageIcon(CommonUtil.class.getResource(icon)) : null;
    }

    public static ImageIcon getDefaultBackgroundImage() {
        int index = 0;
        while (index == 0) {
            index = new Random().nextInt(6);
        }
        String backgroundName = new StringBuilder("/image/").append(index).append(".jpg").toString();
        return getImageIcon(backgroundName);
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
                    getLogger(CommonUtil.class.getName()).log(Level.SEVERE,
                            "Unknown App type({0}) and don't know how to deal message", getAppType());
            }
        } catch (Exception e) {
            getLogger(CommonUtil.class.getName()).log(Level.SEVERE,
                    "Networks cannot be accessed normally now\n", e);
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

        @Override
        protected void done() {
            try {
                setKeyboardStatus(get());
            } catch (InterruptedException | ExecutionException ex) {
                Logger.getLogger(CommonUtil.class.getName()).log(Level.WARNING,
                        "fail to download cipher due to :\n{0}", ex);
            }
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
        return tagName == null ? null : transitionStation.get(tagName);
    }

    public static void cleanTranStation(String tagName) {
        if (tagName == null) {
            return;
        }
        transitionStation.remove(tagName);
    }

    private static String TYPE_TEXT_ACTIVE_CMD = "";
    private static String TYPE_TEXT_UNACTIVE_CMD = "";

    public static String getTypeMethodActiveCmd() {
        return TYPE_TEXT_ACTIVE_CMD;
    }

    public static void setTypeMethodActiveCmd(String cmd) {
        TYPE_TEXT_ACTIVE_CMD = cmd;
    }

    public static String getTypeMethodUnactiveCmd() {
        return TYPE_TEXT_UNACTIVE_CMD;
    }

    public static void setTypeMethodUnactiveCmd(String cmd) {
        TYPE_TEXT_UNACTIVE_CMD = cmd;
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
    private final static int SCALE = 2;
    public static final int DEFAULT_SCREEN_WIDTH = 1280;
    public static final int DEFAULT_SCREEN_HEITH = 1024;
    private static double WIDTH_SCALE = new BigDecimal(SCREEN_SIZE.width)
            .divide(new BigDecimal(DEFAULT_SCREEN_WIDTH),
                    SCALE, BigDecimal.ROUND_HALF_EVEN).doubleValue();
    private static double HEIGHT_SCALE = new BigDecimal(SCREEN_SIZE.height)
            .divide(new BigDecimal(DEFAULT_SCREEN_HEITH),
                    SCALE, BigDecimal.ROUND_HALF_EVEN).doubleValue();

    public static Bound adjustByResolution(Bound rawRect) {
        final Bound adjustment = new Bound();
        adjustment.setX((int) Math.round(rawRect.getX() * WIDTH_SCALE));
        adjustment.setY((int) Math.round(rawRect.getY() * HEIGHT_SCALE));
        adjustment.setWidth((int) Math.round(rawRect.getWidth() * WIDTH_SCALE));
        adjustment.setHeigh((int) Math.round(rawRect.getHeigh() * HEIGHT_SCALE));
        return adjustment;
    }

    public static int adjustSize(int size) {
        double sizeScale = Math.min(WIDTH_SCALE, HEIGHT_SCALE);
        return (int) Math.round(size * sizeScale);
    }

    public static void setScale(int width, int height) {
        final BigDecimal screenWidth = new BigDecimal(width);
        final BigDecimal screenHeight = new BigDecimal(height);
        WIDTH_SCALE = screenWidth.divide(new BigDecimal(DEFAULT_SCREEN_WIDTH),
                SCALE, BigDecimal.ROUND_HALF_EVEN).doubleValue();
        HEIGHT_SCALE = screenHeight.divide(new BigDecimal(DEFAULT_SCREEN_HEITH),
                SCALE, BigDecimal.ROUND_HALF_EVEN).doubleValue();
    }

    private static king.flow.view.Window windowNode = null;

    public static king.flow.view.Window getWindowNode() {
        return windowNode;
    }

    public static void setWindowNode(king.flow.view.Window windowNode) {
        CommonUtil.windowNode = windowNode;
    }

    public static String shapeErrPrompt(final java.lang.Throwable err) {
        return err.getClass().getName() + ": " + err.getMessage();
    }

    public static boolean isActionSupport(Component component, String actionName) {
        return CommonConstants.ACTION_COMPONENT_MAP.get(component.getType()) == null
                ? false : CommonConstants.ACTION_COMPONENT_MAP.get(component.getType()).contains(actionName);
    }

    static List<String> bankCardPrefix = null;

    public static void setBankCardPrefix(List<String> bankCardPrefix) {
        CommonUtil.bankCardPrefix = bankCardPrefix;
    }

    public static String retrieveBankCardAffiliation(String cardNum) {
        if (bankCardPrefix != null || bankCardPrefix.size() > 0) {
            for (String prefix : bankCardPrefix) {
                if (cardNum.startsWith(prefix)) {
                    return CommonConstants.CARD_AFFILIATION_INTERNAL;
                }
            }
        }
        return CommonConstants.CARD_AFFILIATION_EXTERNAL;
    }

    static Transportation.Misc.AllowCPU allowCPUCard = null;

    public static boolean allowCPUCard() {
        return allowCPUCard != null;
    }

    public static void setAllowCPUCard(Transportation.Misc.AllowCPU allowCPUCard) {
        CommonUtil.allowCPUCard = allowCPUCard;
    }

    public static Transportation.Misc.AllowCPU getAllowCPUConfig() {
        return allowCPUCard;
    }

    public static long convertCalendarToMills(XMLGregorianCalendar calendar) {
        Calendar markStart = Calendar.getInstance();
        markStart.setTimeInMillis(System.currentTimeMillis());
        markStart.set(Calendar.HOUR_OF_DAY, calendar.getHour());
        markStart.set(Calendar.MINUTE, calendar.getMinute());
        markStart.set(Calendar.SECOND, calendar.getSecond());
        return markStart.getTimeInMillis();
    }

    static final char BLANK_SPACE = 0x20;
    static final char NEW_BLANK_LINE = '\n';

    public static void startWatchDog() {
        final String initd = new StringBuilder()
                .append("./jre/bin/javaw.exe")
                .append(BLANK_SPACE)
                .append("-cp")
                .append(BLANK_SPACE)
                .append("./lib/xml-UI-2.0-SNAPSHOT.jar;")
                .append("./lib/jsonj-0.8.jar;")
                .append("./lib/commons-lang-2.6.jar;")
                .append("./lib/jna-3.2.3.jar;")
                .append("./lib/commons-io-2.4.jar;")
                .append("./lib/guava-18.0.jar")
                .append(BLANK_SPACE)
                .append("-Dtunnel.build.scheme=../backend.xml")
                .append(BLANK_SPACE)
                .append("king.flow.control.deamon.Numen")
                .toString();
        try {
            Runtime.getRuntime().exec(initd);
            Logger.getLogger(CommonUtil.class.getName()).log(Level.INFO,
                    "start watchdog deamon :\n{0}", initd);
        } catch (IOException ex) {
            Logger.getLogger(CommonUtil.class.getName()).log(Level.WARNING,
                    "fail to start watchdog deamon '{0}' due to :\n{1}",
                    new Object[]{initd, ex.getMessage()});
        }
    }

    static Transportation.Misc.WatchDog watchDogConfig = null;

    public static void setWatchDogConfig(Transportation.Misc.WatchDog watchDogConfig) {
        CommonUtil.watchDogConfig = watchDogConfig;
    }

    public static boolean isWatchDogEnabled() {
        return watchDogConfig == null ? false : watchDogConfig.isEnabled();
    }

    public static int getWatchDogInterval() {
        return (watchDogConfig == null || watchDogConfig.getCheckInterval() < WATCHDOG_CHECK_INTERVAL)
                ? WATCHDOG_CHECK_INTERVAL : watchDogConfig.getCheckInterval();
    }

    public static String showSystemInfo() {
        StringBuilder appInfo = new StringBuilder();
        appInfo.append("********************************************************************************************")
                .append(NEW_BLANK_LINE)
                .append("* Starting Up Application Now")
                .append(NEW_BLANK_LINE)
                .append('*')
                .append(NEW_BLANK_LINE)
                .append("* Working Folder : ").append(System.getProperty("user.dir"))
                .append(NEW_BLANK_LINE)
                .append("* Java Runtime Environment : ").append(System.getProperty("java.home"))
                .append(NEW_BLANK_LINE)
                .append("* Application Version : ").append(CommonConstants.VERSION)
                .append(NEW_BLANK_LINE)
                .append("* WatchDog Enablement : ").append(isWatchDogEnabled())
                .append(NEW_BLANK_LINE)
                .append("* WatchDog Poll Interval : ").append(getWatchDogInterval())
                .append(NEW_BLANK_LINE)
                .append("* Remote Server Address : ")
                .append(TunnelBuilder.getTunnelBuilder().getHostName())
                .append(":")
                .append(TunnelBuilder.getTunnelBuilder().getPortNumber())
                .append(NEW_BLANK_LINE)
                .append("********************************************************************************************");
        return appInfo.toString();
    }
    
    public static String dumpExceptionStack(Throwable exception) {
        if (exception == null) {
            return "";
        }
        
        StringBuilder stackInfo = new StringBuilder();
        for (StackTraceElement trace : exception.getStackTrace()) {
            stackInfo.append("at ").append(trace.toString()).append('\n');
        }
        return stackInfo.toString();
    }
}
