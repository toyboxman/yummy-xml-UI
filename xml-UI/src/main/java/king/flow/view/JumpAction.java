//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.08.18 at 11:13:00 AM CST 
//


package king.flow.view;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for jump_action complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="jump_action">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="nextPanel" type="{http://www.w3.org/2001/XMLSchema}unsignedShort"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "jump_action", propOrder = {

})
public class JumpAction {

    @XmlSchemaType(name = "unsignedShort")
    protected int nextPanel;

    /**
     * Gets the value of the nextPanel property.
     * 
     */
    public int getNextPanel() {
        return nextPanel;
    }

    /**
     * Sets the value of the nextPanel property.
     * 
     */
    public void setNextPanel(int value) {
        this.nextPanel = value;
    }

}
