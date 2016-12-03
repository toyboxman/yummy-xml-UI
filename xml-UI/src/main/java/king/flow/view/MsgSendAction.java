//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.12.03 at 10:13:16 PM CST 
//


package king.flow.view;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for msgSendAction complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="msgSendAction">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="prsCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cmdCode" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="conditions" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="nextStep">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;all>
 *                   &lt;element name="nextPanel" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                   &lt;element name="display" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="nextCursor" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                   &lt;element name="trigger" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                 &lt;/all>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="exception">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;all>
 *                   &lt;element name="nextPanel" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                   &lt;element name="display" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="trigger" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                 &lt;/all>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="checkRules" type="{}rules" minOccurs="0"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "msgSendAction", propOrder = {

})
public class MsgSendAction {

    @XmlElement(required = true)
    protected String prsCode;
    @XmlElement(defaultValue = "-1")
    protected Integer cmdCode;
    @XmlElement(required = true)
    protected String conditions;
    @XmlElement(required = true)
    protected MsgSendAction.NextStep nextStep;
    @XmlElement(required = true)
    protected MsgSendAction.Exception exception;
    protected Rules checkRules;

    /**
     * Gets the value of the prsCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrsCode() {
        return prsCode;
    }

    /**
     * Sets the value of the prsCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrsCode(String value) {
        this.prsCode = value;
    }

    /**
     * Gets the value of the cmdCode property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCmdCode() {
        return cmdCode;
    }

    /**
     * Sets the value of the cmdCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCmdCode(Integer value) {
        this.cmdCode = value;
    }

    /**
     * Gets the value of the conditions property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConditions() {
        return conditions;
    }

    /**
     * Sets the value of the conditions property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConditions(String value) {
        this.conditions = value;
    }

    /**
     * Gets the value of the nextStep property.
     * 
     * @return
     *     possible object is
     *     {@link MsgSendAction.NextStep }
     *     
     */
    public MsgSendAction.NextStep getNextStep() {
        return nextStep;
    }

    /**
     * Sets the value of the nextStep property.
     * 
     * @param value
     *     allowed object is
     *     {@link MsgSendAction.NextStep }
     *     
     */
    public void setNextStep(MsgSendAction.NextStep value) {
        this.nextStep = value;
    }

    /**
     * Gets the value of the exception property.
     * 
     * @return
     *     possible object is
     *     {@link MsgSendAction.Exception }
     *     
     */
    public MsgSendAction.Exception getException() {
        return exception;
    }

    /**
     * Sets the value of the exception property.
     * 
     * @param value
     *     allowed object is
     *     {@link MsgSendAction.Exception }
     *     
     */
    public void setException(MsgSendAction.Exception value) {
        this.exception = value;
    }

    /**
     * Gets the value of the checkRules property.
     * 
     * @return
     *     possible object is
     *     {@link Rules }
     *     
     */
    public Rules getCheckRules() {
        return checkRules;
    }

    /**
     * Sets the value of the checkRules property.
     * 
     * @param value
     *     allowed object is
     *     {@link Rules }
     *     
     */
    public void setCheckRules(Rules value) {
        this.checkRules = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;all>
     *         &lt;element name="nextPanel" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *         &lt;element name="display" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="trigger" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *       &lt;/all>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {

    })
    public static class Exception {

        protected int nextPanel;
        @XmlElement(required = true)
        protected String display;
        protected Integer trigger;

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

        /**
         * Gets the value of the display property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDisplay() {
            return display;
        }

        /**
         * Sets the value of the display property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDisplay(String value) {
            this.display = value;
        }

        /**
         * Gets the value of the trigger property.
         * 
         * @return
         *     possible object is
         *     {@link Integer }
         *     
         */
        public Integer getTrigger() {
            return trigger;
        }

        /**
         * Sets the value of the trigger property.
         * 
         * @param value
         *     allowed object is
         *     {@link Integer }
         *     
         */
        public void setTrigger(Integer value) {
            this.trigger = value;
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;all>
     *         &lt;element name="nextPanel" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *         &lt;element name="display" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="nextCursor" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *         &lt;element name="trigger" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *       &lt;/all>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {

    })
    public static class NextStep {

        protected int nextPanel;
        @XmlElement(required = true)
        protected String display;
        protected Integer nextCursor;
        protected Integer trigger;

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

        /**
         * Gets the value of the display property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDisplay() {
            return display;
        }

        /**
         * Sets the value of the display property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDisplay(String value) {
            this.display = value;
        }

        /**
         * Gets the value of the nextCursor property.
         * 
         * @return
         *     possible object is
         *     {@link Integer }
         *     
         */
        public Integer getNextCursor() {
            return nextCursor;
        }

        /**
         * Sets the value of the nextCursor property.
         * 
         * @param value
         *     allowed object is
         *     {@link Integer }
         *     
         */
        public void setNextCursor(Integer value) {
            this.nextCursor = value;
        }

        /**
         * Gets the value of the trigger property.
         * 
         * @return
         *     possible object is
         *     {@link Integer }
         *     
         */
        public Integer getTrigger() {
            return trigger;
        }

        /**
         * Sets the value of the trigger property.
         * 
         * @param value
         *     allowed object is
         *     {@link Integer }
         *     
         */
        public void setTrigger(Integer value) {
            this.trigger = value;
        }

    }

}
