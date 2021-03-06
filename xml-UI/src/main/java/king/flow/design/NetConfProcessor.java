/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.flow.design;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import king.flow.common.CommonConstants;
import static king.flow.common.CommonUtil.getLogger;
import king.flow.net.Transportation;

/**
 *
 * @author LiuJin
 */
public class NetConfProcessor {

    private JAXBContext context = null;
    private String xmlPath = null;

    public NetConfProcessor(String confPath) {
        this.xmlPath = confPath;
    }

    public NetConfProcessor init() throws JAXBException {
        try {
            this.context = JAXBContext.newInstance(CommonConstants.NET_CONF_PACKAGE_CONTEXT);
        } catch (JAXBException ex) {
            getLogger(NetConfProcessor.class.getName()).log(Level.SEVERE,
                    "cannot init backend parser due to: \n{0}", ex);
            throw ex;
        }
        return this;
    }

    public Transportation parse() throws JAXBException {
        try {
            if (this.context == null) {
                init();
            }

            Unmarshaller unmarshaller = this.context.createUnmarshaller();
            Transportation transportation = (Transportation) unmarshaller.unmarshal(new File(this.xmlPath));
            return transportation;
        } catch (JAXBException ex) {
            getLogger(NetConfProcessor.class.getName()).log(Level.SEVERE,
                    "fail to parse {0} due to: \n{1}",
                    new Object[]{this.xmlPath, ex});
            throw ex;
        }
    }
}
