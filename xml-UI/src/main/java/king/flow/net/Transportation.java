//
// ���ļ����� JavaTM Architecture for XML Binding (JAXB) ����ʵ�� v2.2.8-b130911.1802 ���ɵ�
// ����� <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// �����±���Դģʽʱ, �Դ��ļ��������޸Ķ�����ʧ��
// ����ʱ��: 2014.11.16 ʱ�� 03:56:54 PM CST 
//


package king.flow.net;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>anonymous complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
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
     * ��ȡserver���Ե�ֵ��
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
     * ����server���Ե�ֵ��
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
     * ��ȡregistration���Ե�ֵ��
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
     * ����registration���Ե�ֵ��
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
     * <p>anonymous complex type�� Java �ࡣ
     * 
     * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
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
         * ��ȡprsCode���Ե�ֵ��
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
         * ����prsCode���Ե�ֵ��
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
         * ��ȡterminalID���Ե�ֵ��
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
         * ����terminalID���Ե�ֵ��
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
         * ��ȡtoken���Ե�ֵ��
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
         * ����token���Ե�ֵ��
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
         * ��ȡbranchno���Ե�ֵ��
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
         * ����branchno���Ե�ֵ��
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
         * ��ȡheartbeat���Ե�ֵ��
         * 
         */
        public int getHeartbeat() {
            return heartbeat;
        }

        /**
         * ����heartbeat���Ե�ֵ��
         * 
         */
        public void setHeartbeat(int value) {
            this.heartbeat = value;
        }

    }


    /**
     * <p>anonymous complex type�� Java �ࡣ
     * 
     * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
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
         * ��ȡhost���Ե�ֵ��
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
         * ����host���Ե�ֵ��
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
         * ��ȡport���Ե�ֵ��
         * 
         */
        public int getPort() {
            return port;
        }

        /**
         * ����port���Ե�ֵ��
         * 
         */
        public void setPort(int value) {
            this.port = value;
        }

    }

}
