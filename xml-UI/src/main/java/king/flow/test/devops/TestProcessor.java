/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package king.flow.test.devops;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import king.flow.test.devops.define.TestCase;
import king.flow.test.devops.define.TestSuits;

/**
 *
 * @author liujin
 */
public class TestProcessor {

    private static final String TEST_CONF_PACKAGE_CONTEXT = "king.flow.test.devops.define";
    private JAXBContext context = null;
    private String xmlPath = null;

    public TestProcessor(String confPath) {
        this.xmlPath = confPath;
    }

    public TestProcessor init() throws JAXBException {
        try {
            this.context = JAXBContext.newInstance(TEST_CONF_PACKAGE_CONTEXT);
        } catch (JAXBException ex) {
            Logger.getLogger(TestProcessor.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
        return this;
    }

    public <T> T parse(Class<T> c) throws JAXBException {
        try {
            if (this.context == null) {
                init();
            }

            Unmarshaller unmarshaller = this.context.createUnmarshaller();
            JAXBElement<T> root = (JAXBElement<T>) unmarshaller.unmarshal(new File(this.xmlPath));
            return root.getValue();
        } catch (JAXBException ex) {
            Logger.getLogger(TestProcessor.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }

    public TestSuits parseTestSuits() throws JAXBException {
        return parse(TestSuits.class);
    }

    public TestCase parseTestCase() throws JAXBException {
        return parse(TestCase.class);
    }
}
