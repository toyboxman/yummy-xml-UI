//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.03.07 at 05:13:37 PM CST 
//


package king.flow.net;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the king.flow.net package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: king.flow.net
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Transportation }
     * 
     */
    public Transportation createTransportation() {
        return new Transportation();
    }

    /**
     * Create an instance of {@link Transportation.Server }
     * 
     */
    public Transportation.Server createTransportationServer() {
        return new Transportation.Server();
    }

    /**
     * Create an instance of {@link Transportation.Registration }
     * 
     */
    public Transportation.Registration createTransportationRegistration() {
        return new Transportation.Registration();
    }

}
