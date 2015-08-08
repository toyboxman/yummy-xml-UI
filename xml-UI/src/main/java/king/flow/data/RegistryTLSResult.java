/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.flow.data;

import static king.flow.common.CommonConstants.NORMAL;
import static king.flow.data.ObjectFactory._Changekey_QNAME;
import static king.flow.data.ObjectFactory._Restart_QNAME;
import static king.flow.data.ObjectFactory._Systemrestart_QNAME;
import static king.flow.data.ObjectFactory._Update_QNAME;
import static king.flow.data.ObjectFactory._Updatefile_QNAME;

/**
 *
 * @author Administrator
 */
public class RegistryTLSResult extends TLSResult {

    public final static String DOWNLOAD_KEY_SIGNAL = _Changekey_QNAME.getLocalPart();
    public final static String APP_RESTART_SIGNAL = _Restart_QNAME.getLocalPart();
    public final static String SYS_RESTART_SIGNAL = _Systemrestart_QNAME.getLocalPart();
    public final static String APP_UPDATE_SIGNAL = _Update_QNAME.getLocalPart();
    public final static String APP_UPDATE_FILE = _Updatefile_QNAME.getLocalPart();
    
    public final static String APP_UPDATE_PATH = "path";
    public final static String APP_UPDATE_VER = "version";
    public final static String APP_UPDATE_MD5 = "md5";

    private int downloadKeySignal = NORMAL;
    private int appRestartSignal = NORMAL;
    private int systemRestartSignal = NORMAL;
    private int appUpdateSignal = NORMAL;

    private String appUpdateFile;

    public String getAppUpdateFile() {
        return appUpdateFile;
    }

    public void setAppUpdateFile(String appUpdateFile) {
        this.appUpdateFile = appUpdateFile;
    }

    public int getAppUpdateSignal() {
        return appUpdateSignal;
    }

    public RegistryTLSResult setAppUpdateSignal(int appUpdateSignal) {
        this.appUpdateSignal = appUpdateSignal;
        return this;
    }

    public int getDownloadKeySignal() {
        return downloadKeySignal;
    }

    public RegistryTLSResult setDownloadKeySignal(int downloadKeySignal) {
        this.downloadKeySignal = downloadKeySignal;
        return this;
    }

    public int getAppRestartSignal() {
        return appRestartSignal;
    }

    public RegistryTLSResult setAppRestartSignal(int appRestartSignal) {
        this.appRestartSignal = appRestartSignal;
        return this;
    }

    public int getSystemRestartSignal() {
        return systemRestartSignal;
    }

    public RegistryTLSResult setSystemRestartSignal(int systemRestartSignal) {
        this.systemRestartSignal = systemRestartSignal;
        return this;
    }
}
