//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.8-b130911.1802 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2014.11.16 时间 03:56:54 PM CST 
//


package king.flow.net;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>anonymous complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
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
     * 获取server属性的值。
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
     * 设置server属性的值。
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
     * 获取registration属性的值。
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
     * 设置registration属性的值。
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
     * <p>anonymous complex type的 Java 类。
     * 
     * <p>以下模式片段指定包含在此类中的预期内容。
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
         * 获取prsCode属性的值。
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
         * 设置prsCode属性的值。
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
         * 获取terminalID属性的值。
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
         * 设置terminalID属性的值。
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
         * 获取token属性的值。
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
         * 设置token属性的值。
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
         * 获取branchno属性的值。
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
         * 设置branchno属性的值。
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
         * 获取heartbeat属性的值。
         * 
         */
        public int getHeartbeat() {
            return heartbeat;
        }

        /**
         * 设置heartbeat属性的值。
         * 
         */
        public void setHeartbeat(int value) {
            this.heartbeat = value;
        }

    }


    /**
     * <p>anonymous complex type的 Java 类。
     * 
     * <p>以下模式片段指定包含在此类中的预期内容。
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;all>
     *         &lt;element name="host" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="port" type="{http://www.w3.org/2001/XMLSchema}unsignedShort"/>
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

        /**
         * 获取host属性的值。
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
         * 设置host属性的值。
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
         * 获取port属性的值。
         * 
         */
        public int getPort() {
            return port;
        }

        /**
         * 设置port属性的值。
         * 
         */
        public void setPort(int value) {
            this.port = value;
        }

    }

}
