package king.flow.design;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import king.flow.common.CommonConstants;

/**
 *
 * @author LiuJin
 */
public class FlowProcessor {

    private JAXBContext context = null;
    private String xmlPath = null;

    public FlowProcessor(String confPath) {
        this.xmlPath = confPath;
    }

    public void init() throws JAXBException {
        try {
            this.context = JAXBContext.newInstance(CommonConstants.UI_CONF_PACKAGE_CONTEXT);
        } catch (JAXBException ex) {
            Logger.getLogger(FlowProcessor.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }

    public <T> T parse(Class<T> c) throws JAXBException {
        try {
            if (this.context == null) {
                init();
            }

            Unmarshaller unmarshaller = this.context.createUnmarshaller();
            JAXBElement<T> element = (JAXBElement<T>) unmarshaller.unmarshal(new File(this.xmlPath));
            return element.getValue();
        } catch (JAXBException ex) {
            Logger.getLogger(FlowProcessor.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }
}
