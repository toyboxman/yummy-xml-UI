//
// ���ļ����� JavaTM Architecture for XML Binding (JAXB) ����ʵ�� v2.2.8-b130911.1802 ���ɵ�
// ����� <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// �����±���Դģʽʱ, �Դ��ļ��������޸Ķ�����ʧ��
// ����ʱ��: 2015.08.26 ʱ�� 09:58:15 PM CST 
//


package king.flow.view;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>msgSendAction complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
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
 *                   &lt;element name="display" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
 *                   &lt;element name="display" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
     * ��ȡcmdCode���Ե�ֵ��
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
     * ����cmdCode���Ե�ֵ��
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
     * ��ȡconditions���Ե�ֵ��
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
     * ����conditions���Ե�ֵ��
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
     * ��ȡnextStep���Ե�ֵ��
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
     * ����nextStep���Ե�ֵ��
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
     * ��ȡexception���Ե�ֵ��
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
     * ����exception���Ե�ֵ��
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
     * ��ȡcheckRules���Ե�ֵ��
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
     * ����checkRules���Ե�ֵ��
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
     * <p>anonymous complex type�� Java �ࡣ
     * 
     * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;all>
     *         &lt;element name="nextPanel" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *         &lt;element name="display" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
        @XmlElement(defaultValue = "-1")
        protected int display;

        /**
         * ��ȡnextPanel���Ե�ֵ��
         * 
         */
        public int getNextPanel() {
            return nextPanel;
        }

        /**
         * ����nextPanel���Ե�ֵ��
         * 
         */
        public void setNextPanel(int value) {
            this.nextPanel = value;
        }

        /**
         * ��ȡdisplay���Ե�ֵ��
         * 
         */
        public int getDisplay() {
            return display;
        }

        /**
         * ����display���Ե�ֵ��
         * 
         */
        public void setDisplay(int value) {
            this.display = value;
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
     *         &lt;element name="nextPanel" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *         &lt;element name="display" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
        @XmlElement(defaultValue = "-1")
        protected int display;

        /**
         * ��ȡnextPanel���Ե�ֵ��
         * 
         */
        public int getNextPanel() {
            return nextPanel;
        }

        /**
         * ����nextPanel���Ե�ֵ��
         * 
         */
        public void setNextPanel(int value) {
            this.nextPanel = value;
        }

        /**
         * ��ȡdisplay���Ե�ֵ��
         * 
         */
        public int getDisplay() {
            return display;
        }

        /**
         * ����display���Ե�ֵ��
         * 
         */
        public void setDisplay(int value) {
            this.display = value;
        }

    }

}
