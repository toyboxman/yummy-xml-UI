//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.03.07 at 05:13:37 PM CST 
//


package king.flow.net;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="server">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;all>
 *                   &lt;element name="host" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="port" type="{http://www.w3.org/2001/XMLSchema}unsignedShort"/>
 *                   &lt;element name="timeout" type="{http://www.w3.org/2001/XMLSchema}unsignedShort" minOccurs="0"/>
 *                 &lt;/all>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="registration">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;all>
 *                   &lt;element name="prsCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="terminalID" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="token" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="branchno" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="heartbeat" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                 &lt;/all>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
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
    "server",
    "registration"
})
@XmlRootElement(name = "Transportation")
public class Transportation {

    @XmlElement(required = true)
    protected Transportation.Server server;
    @XmlElement(required = true)
    protected Transportation.Registration registration;

    /**
     * Gets the value of the server property.
     * 
     * @return
     *     possible object is
     *     {@link Transportation.Server }
     *     
     */
    public Transportation.Server getServer() {
        return server;
    }

    /**
     * Sets the value of the server property.
     * 
     * @param value
     *     allowed object is
     *     {@link Transportation.Server }
     *     
     */
    public void setServer(Transportation.Server value) {
        this.server = value;
    }

    /**
     * Gets the value of the registration property.
     * 
     * @return
     *     possible object is
     *     {@link Transportation.Registration }
     *     
     */
    public Transportation.Registration getRegistration() {
        return registration;
    }

    /**
     * Sets the value of the registration property.
     * 
     * @param value
     *     allowed object is
     *     {@link Transportation.Registration }
     *     
     */
    public void setRegistration(Transportation.Registration value) {
        this.registration = value;
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
     *         &lt;element name="prsCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="terminalID" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="token" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="branchno" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="heartbeat" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
    public static class Registration {

        @XmlElement(required = true)
        protected String prsCode;
        @XmlElement(required = true)
        protected String terminalID;
        @XmlElement(required = true)
        protected String token;
        @XmlElement(required = true)
        protected String branchno;
        protected int heartbeat;

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
         * Gets the value of the terminalID property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTerminalID() {
            return terminalID;
        }

        /**
         * Sets the value of the terminalID property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTerminalID(String value) {
            this.terminalID = value;
        }

        /**
         * Gets the value of the token property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getToken() {
            return token;
        }

        /**
         * Sets the value of the token property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setToken(String value) {
            this.token = value;
        }

        /**
         * Gets the value of the branchno property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getBranchno() {
            return branchno;
        }

        /**
         * Sets the value of the branchno property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setBranchno(String value) {
            this.branchno = value;
        }

        /**
         * Gets the value of the heartbeat property.
         * 
         */
        public int getHeartbeat() {
            return heartbeat;
        }

        /**
         * Sets the value of the heartbeat property.
         * 
         */
        public void setHeartbeat(int value) {
            this.heartbeat = value;
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
     *         &lt;element name="host" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="port" type="{http://www.w3.org/2001/XMLSchema}unsignedShort"/>
     *         &lt;element name="timeout" type="{http://www.w3.org/2001/XMLSchema}unsignedShort" minOccurs="0"/>
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
    public static class Server {

        @XmlElement(required = true)
        protected String host;
        @XmlSchemaType(name = "unsignedShort")
        protected int port;
        @XmlElement(defaultValue = "120")
        @XmlSchemaType(name = "unsignedShort")
        protected Integer timeout;

        /**
         * Gets the value of the host property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getHost() {
            return host;
        }

        /**
         * Sets the value of the host property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setHost(String value) {
            this.host = value;
        }

        /**
         * Gets the value of the port property.
         * 
         */
        public int getPort() {
            return port;
        }

        /**
         * Sets the value of the port property.
         * 
         */
        public void setPort(int value) {
            this.port = value;
        }

        /**
         * Gets the value of the timeout property.
         * 
         * @return
         *     possible object is
         *     {@link Integer }
         *     
         */
        public Integer getTimeout() {
            return timeout;
        }

        /**
         * Sets the value of the timeout property.
         * 
         * @param value
         *     allowed object is
         *     {@link Integer }
         *     
         */
        public void setTimeout(Integer value) {
            this.timeout = value;
        }

    }

}
