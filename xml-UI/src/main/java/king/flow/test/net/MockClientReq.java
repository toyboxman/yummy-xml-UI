/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.flow.test.net;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.namespace.QName;
import king.flow.common.CommonUtil;
import king.flow.design.TLSProcessor;

/**
 *
 * @author Administrator
 */
public class MockClientReq {

    private final static QName _Id_QNAME = new QName("", "id");
    private final static QName _Retcode_QNAME = new QName("", "retcode");
    private final static QName _Prscode_QNAME = new QName("", "prscode");
    private final static QName _Errmsg_QNAME = new QName("", "errmsg");

    public static final String mockBasicReq() {
        String result = null;
        try {
            TLSProcessor tp = new TLSProcessor().init();
            JAXBElement<String> prsCode = CommonUtil.createJAXBElement(MockClientReq._Prscode_QNAME, "AcountQuery");
            JAXBElement<String> start = CommonUtil.createJAXBElement("311", "2014-8-1");
            JAXBElement<String> end = CommonUtil.createJAXBElement("315", "2014-8-5");

            result = tp.buildTLS(prsCode, start, end);
        } catch (JAXBException ex) {
            Logger.getLogger(MockServerResp.class.getName()).log(Level.SEVERE, null, ex);
        }

        return result;
    }

}
