/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.flow.test.net;

import com.github.jsonj.tools.JsonBuilder;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBException;
import static king.flow.common.CommonConstants.ABNORMAL;
import static king.flow.common.CommonConstants.NORMAL;
import static king.flow.data.RegistryTLSResult.APP_UPDATE_MD5;
import static king.flow.data.RegistryTLSResult.APP_UPDATE_PATH;
import static king.flow.data.RegistryTLSResult.APP_UPDATE_VER;
import king.flow.design.TLSProcessor;

/**
 *
 * @author LiuJin
 */
public class MockServerResp {

    public static final String mockSuccessfulRegistryResp(String terminalId, String okMsg) {
        String result = null;
        try {
            TLSProcessor tp = new TLSProcessor().init();
            result = tp.mockRegistryResp(NORMAL, terminalId, okMsg, "", "", NORMAL, NORMAL);
        } catch (JAXBException ex) {
            Logger.getLogger(MockServerResp.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    public static final String mockSuccessfulDownloadKeyResp(String terminalId, String okMsg) {
        String result = null;
        try {
            TLSProcessor tp = new TLSProcessor().init();
            result = tp.mockDownloadKeyResp(terminalId, okMsg);
        } catch (JAXBException ex) {
            Logger.getLogger(MockServerResp.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    public static final String mockSuccessfulGeneralResp(String terminalId, String okMsg) {
        String result = null;
        try {
            TLSProcessor tp = new TLSProcessor().init();
            result = tp.mockGeneralResp(NORMAL, terminalId, okMsg, "", "", "", null);
        } catch (JAXBException ex) {
            Logger.getLogger(MockServerResp.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    public static final String mockSuccessfulGeneralResp(String terminalId, String okMsg, String prtMsg, String redirection) {
        String result = null;
        try {
            TLSProcessor tp = new TLSProcessor().init();
            result = tp.mockGeneralResp(NORMAL, terminalId, okMsg, "", prtMsg, "", redirection);
        } catch (JAXBException ex) {
            Logger.getLogger(MockServerResp.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static final String mockFailedGeneralResp(String terminalId, String errMsg) {
        String result = null;
        try {
            TLSProcessor tp = new TLSProcessor().init();
            result = tp.mockGeneralResp(ABNORMAL, terminalId, "", errMsg, "", "", null);
        } catch (JAXBException ex) {
            Logger.getLogger(MockServerResp.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

    public static final String mockUpdateRegistryResp() {
        String result = null;
        try {
            TLSProcessor tp = new TLSProcessor().init();
            JsonBuilder upgradeFile = JsonBuilder.object();
            upgradeFile.put(APP_UPDATE_PATH, "http://192.168.0.104/bankApp-update.zip");
            upgradeFile.put(APP_UPDATE_VER, "2.1");
//            upgradeFile.put(APP_UPDATE_MD5, "81adb2fc08a7f3e40ea805a37ed24ede");
            upgradeFile.put(APP_UPDATE_MD5, "9e03c90131596fa0285d52cd4f21b518");
            result = tp.mockUpdateRegistryResp(0, "0X12347890ABCDEF", 1, upgradeFile.get().toString());
        } catch (JAXBException ex) {
            Logger.getLogger(MockServerResp.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

}
