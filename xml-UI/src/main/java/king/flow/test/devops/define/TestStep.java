//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.03.18 at 04:41:11 PM CST 
//


package king.flow.test.devops.define;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TestStep complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TestStep">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="component" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="pre-wait" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="action">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="name" type="{}TestActionEnum"/>
 *                   &lt;element name="parameter" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="post-wait" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TestStep", propOrder = {
    "component",
    "preWait",
    "action",
    "postWait"
})
public class TestStep {

    protected int component;
    @XmlElement(name = "pre-wait")
    protected int preWait;
    @XmlElement(required = true)
    protected TestStep.Action action;
    @XmlElement(name = "post-wait")
    protected int postWait;

    /**
     * Gets the value of the component property.
     * 
     */
    public int getComponent() {
        return component;
    }

    /**
     * Sets the value of the component property.
     * 
     */
    public void setComponent(int value) {
        this.component = value;
    }

    /**
     * Gets the value of the preWait property.
     * 
     */
    public int getPreWait() {
        return preWait;
    }

    /**
     * Sets the value of the preWait property.
     * 
     */
    public void setPreWait(int value) {
        this.preWait = value;
    }

    /**
     * Gets the value of the action property.
     * 
     * @return
     *     possible object is
     *     {@link TestStep.Action }
     *     
     */
    public TestStep.Action getAction() {
        return action;
    }

    /**
     * Sets the value of the action property.
     * 
     * @param value
     *     allowed object is
     *     {@link TestStep.Action }
     *     
     */
    public void setAction(TestStep.Action value) {
        this.action = value;
    }

    /**
     * Gets the value of the postWait property.
     * 
     */
    public int getPostWait() {
        return postWait;
    }

    /**
     * Sets the value of the postWait property.
     * 
     */
    public void setPostWait(int value) {
        this.postWait = value;
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
     *       &lt;sequence>
     *         &lt;element name="name" type="{}TestActionEnum"/>
     *         &lt;element name="parameter" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "name",
        "parameter"
    })
    public static class Action {

        @XmlElement(required = true)
        @XmlSchemaType(name = "string")
        protected TestActionEnum name;
        @XmlElement(required = true)
        protected String parameter;

        /**
         * Gets the value of the name property.
         * 
         * @return
         *     possible object is
         *     {@link TestActionEnum }
         *     
         */
        public TestActionEnum getName() {
            return name;
        }

        /**
         * Sets the value of the name property.
         * 
         * @param value
         *     allowed object is
         *     {@link TestActionEnum }
         *     
         */
        public void setName(TestActionEnum value) {
            this.name = value;
        }

        /**
         * Gets the value of the parameter property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getParameter() {
            return parameter;
        }

        /**
         * Sets the value of the parameter property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setParameter(String value) {
            this.parameter = value;
        }

    }

}
