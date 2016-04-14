package king.flow.design;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import king.flow.common.CommonConstants;
import static king.flow.common.CommonConstants.ABNORMAL;
import static king.flow.common.CommonConstants.NORMAL;
import static king.flow.common.CommonConstants.VERSION;
import static king.flow.common.CommonUtil.getStartTimeMillis;
import static king.flow.common.CommonUtil.getTerminalStatusValue;
import static king.flow.common.CommonUtil.isKeyboardReady;
import static king.flow.common.CommonUtil.readPrinterStatus;
import static king.flow.common.CommonUtil.tellMeCounter;
import static king.flow.common.CommonUtil.tellMeMsgUid;
import king.flow.data.ObjectFactory;
import king.flow.data.TLS;
import king.flow.data.TLSResult;
import static king.flow.net.TunnelBuilder.getTunnelBuilder;

/**
 *
 * @author LiuJin
 */
public class TLSProcessor {

    private JAXBContext context = null;
    private ObjectFactory factory = null;

    public TLSProcessor init() throws JAXBException {
        try {
            this.context = JAXBContext.newInstance(CommonConstants.TLS_PACKAGE_CONTEXT);
            this.factory = new ObjectFactory();
        } catch (JAXBException ex) {
            Logger.getLogger(TLSProcessor.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
        return this;
    }

    public TLS parse(String tls) throws JAXBException {
        if (tls == null) {
            return null;
        }

        try {
            if (this.context == null) {
                init();
            }

            Unmarshaller unmarshaller = this.context.createUnmarshaller();
            return (TLS) unmarshaller.unmarshal(new StringReader(tls));
        } catch (JAXBException ex) {
            Logger.getLogger(TLSProcessor.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }

    public JAXBElement<Long> createMsgCounterNode() {
        return factory.createCounter(tellMeCounter());
    }

    public JAXBElement<String> createMsgUidNode() {
        return factory.createUid(tellMeMsgUid());
    }

    public JAXBElement<String> createPrsCodeNode(String prsCode) {
        return factory.createPrscode(prsCode);
    }

    public JAXBElement<String> createTerminalID(String id) {
        return factory.createTerminalid(id);
    }
    
    public JAXBElement<String> createUnionPayID(String unionPayID) {
        return factory.createUnionpayid(unionPayID);
    }

    public JAXBElement<String> createBranchno(String branchID) {
        return factory.createBranchno(branchID);
    }

    public String createRegistryMsg(String prsCode, String id, String token) {
        JAXBElement<Long> counter = factory.createCounter(tellMeCounter());
        JAXBElement<String> code = factory.createPrscode(prsCode);
        JAXBElement<String> ID = factory.createTerminalid(id);
        JAXBElement<String> tokenID = factory.createToken(token);
        JAXBElement<String> branchno = factory.createBranchno(getTunnelBuilder().getBranchID());
        JAXBElement<String> unionPayID = factory.createUnionpayid(getTunnelBuilder().getUnionPayID());
        JAXBElement<Integer> terminalstate = factory.createTerminalstate(getTerminalStatusValue());
        JAXBElement<Long> startid = factory.createStartid(getStartTimeMillis());
        JAXBElement<Integer> keyboardstate = factory.createKeyboardstate(isKeyboardReady() ? NORMAL : ABNORMAL);
        JAXBElement<Integer> printerState = factory.createPrtstate(readPrinterStatus());
        JAXBElement<String> version = factory.createVersion(VERSION);
        return buildTLS(counter, code, ID, tokenID,
                branchno, unionPayID, terminalstate,
                startid, keyboardstate, printerState, version);
    }

    public String mockUpdateRegistryResp(int retCode, String id, int updateApp, String updateFile) {
        JAXBElement<Integer> code = factory.createRetcode(retCode);
        JAXBElement<String> ID = factory.createTerminalid(id);
        JAXBElement<Integer> updateBank = factory.createUpdate(updateApp);
        JAXBElement<String> upgradeFile = factory.createUpdatefile(updateFile);
        return buildTLS(code, ID, updateBank, upgradeFile);
    }

    public String mockRegistryResp(int retCode, String terminalId, String okmsg, String errmsg,
            String prtmsg, int restartApp, int downloadKey) {
        JAXBElement<Integer> code = factory.createRetcode(retCode);
        JAXBElement<String> ID = factory.createTerminalid(terminalId);
        JAXBElement<String> ok = factory.createOkmsg(okmsg);
        JAXBElement<String> err = factory.createErrmsg(errmsg);
        JAXBElement<Integer> restartBank = factory.createRestart(restartApp);
        JAXBElement<Integer> changekey = factory.createChangekey(downloadKey);
        return buildTLS(code, ID, ok, err, restartBank, changekey);
    }

    public String mockDownloadKeyResp(String id, String okmsg) {
        JAXBElement<Integer> code = factory.createRetcode(0);
        JAXBElement<String> ID = factory.createTerminalid(id);
        JAXBElement<String> ok = factory.createOkmsg(okmsg);
        return buildTLS(code, ID, ok);
    }

    public String mockGeneralResp(int retCode, String id, String okmsg, String errmsg,
            String prtmsg, String cargomsg, String redirection) {
        List<JAXBElement> payload = new ArrayList<>();
        JAXBElement<Integer> code = factory.createRetcode(retCode);
        payload.add(code);
        JAXBElement<String> ID = factory.createTerminalid(id);
        payload.add(ID);
        JAXBElement<String> ok = factory.createOkmsg(okmsg);
        payload.add(ok);
        JAXBElement<String> err = factory.createErrmsg(errmsg);
        payload.add(err);
        
        if (cargomsg != null) {
            JAXBElement<String> cargo = factory.createCargo(cargomsg);
            payload.add(cargo);
        }
        
        if (prtmsg != null) {
            JAXBElement<String> prt = buildJAXBElement(prtmsg, TLSResult.PRT_MSG);
            payload.add(prt);
        }
        
        if (redirection != null) {
            JAXBElement<String> redirectionPage = factory.createRedirection(redirection);
            payload.add(redirectionPage);
        }
        return buildTLS(payload);
    }

    private <T> JAXBElement<T> buildJAXBElement(T value, String name) {
        return new JAXBElement(new QName("", name), value.getClass(), null, value);
    }

    public String buildTLS(List<JAXBElement> parameters) {
        if (parameters == null) {
            return null;
        }

        StringWriter sw = new StringWriter();
        TLS tls = factory.createTLS();
        for (JAXBElement e : parameters) {
            tls.getAny().add(e);
        }

        try {
            Marshaller marshaller = this.context.createMarshaller();
            marshaller.marshal(tls, sw);
        } catch (JAXBException ex) {
            Logger.getLogger(TLSProcessor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sw.toString();
    }

    public String buildTLS(JAXBElement... parameters) {
        return buildTLS(Arrays.asList(parameters));
    }
    
    public String buildTLS(TLS tls) {
        if (tls == null) {
            return null;
        }
        
        StringWriter sw = new StringWriter();
        try {
            Marshaller marshaller = this.context.createMarshaller();
            marshaller.marshal(tls, sw);
        } catch (JAXBException ex) {
            Logger.getLogger(TLSProcessor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sw.toString();
    }
}
