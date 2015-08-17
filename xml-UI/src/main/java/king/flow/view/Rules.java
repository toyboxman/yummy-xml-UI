//
// ���ļ����� JavaTM Architecture for XML Binding (JAXB) ����ʵ�� v2.2.8-b130911.1802 ���ɵ�
// ����� <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// �����±���Դģʽʱ, �Դ��ļ��������޸Ķ�����ʧ��
// ����ʱ��: 2015.08.17 ʱ�� 10:44:40 PM CST 
//


package king.flow.view;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>rules complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="rules">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="notNull" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;all>
 *                   &lt;element name="content" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                   &lt;element name="errMsg" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/all>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="validateCJK" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;all>
 *                   &lt;element name="content" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                   &lt;element name="errMsg" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/all>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="template" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;all>
 *                   &lt;element name="content" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                   &lt;element name="pattern" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="errMsg" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/all>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="equal" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;all>
 *                   &lt;element name="conditions" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="errMsg" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/all>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="notEqual" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;all>
 *                   &lt;element name="more" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                   &lt;element name="less" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                   &lt;element name="errMsg" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
@XmlType(name = "rules", propOrder = {
    "notNull",
    "validateCJK",
    "template",
    "equal",
    "notEqual"
})
public class Rules {

    protected List<Rules.NotNull> notNull;
    protected List<Rules.ValidateCJK> validateCJK;
    protected List<Rules.Template> template;
    protected List<Rules.Equal> equal;
    protected List<Rules.NotEqual> notEqual;

    /**
     * Gets the value of the notNull property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the notNull property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNotNull().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Rules.NotNull }
     * 
     * 
     */
    public List<Rules.NotNull> getNotNull() {
        if (notNull == null) {
            notNull = new ArrayList<Rules.NotNull>();
        }
        return this.notNull;
    }

    /**
     * Gets the value of the validateCJK property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the validateCJK property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getValidateCJK().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Rules.ValidateCJK }
     * 
     * 
     */
    public List<Rules.ValidateCJK> getValidateCJK() {
        if (validateCJK == null) {
            validateCJK = new ArrayList<Rules.ValidateCJK>();
        }
        return this.validateCJK;
    }

    /**
     * Gets the value of the template property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the template property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTemplate().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Rules.Template }
     * 
     * 
     */
    public List<Rules.Template> getTemplate() {
        if (template == null) {
            template = new ArrayList<Rules.Template>();
        }
        return this.template;
    }

    /**
     * Gets the value of the equal property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the equal property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEqual().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Rules.Equal }
     * 
     * 
     */
    public List<Rules.Equal> getEqual() {
        if (equal == null) {
            equal = new ArrayList<Rules.Equal>();
        }
        return this.equal;
    }

    /**
     * Gets the value of the notEqual property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the notEqual property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNotEqual().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Rules.NotEqual }
     * 
     * 
     */
    public List<Rules.NotEqual> getNotEqual() {
        if (notEqual == null) {
            notEqual = new ArrayList<Rules.NotEqual>();
        }
        return this.notEqual;
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
     *         &lt;element name="conditions" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="errMsg" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    public static class Equal {

        @XmlElement(required = true)
        protected String conditions;
        @XmlElement(required = true)
        protected String errMsg;

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
         * ��ȡerrMsg���Ե�ֵ��
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getErrMsg() {
            return errMsg;
        }

        /**
         * ����errMsg���Ե�ֵ��
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setErrMsg(String value) {
            this.errMsg = value;
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
     *         &lt;element name="more" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *         &lt;element name="less" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *         &lt;element name="errMsg" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    public static class NotEqual {

        protected int more;
        protected int less;
        @XmlElement(required = true)
        protected String errMsg;

        /**
         * ��ȡmore���Ե�ֵ��
         * 
         */
        public int getMore() {
            return more;
        }

        /**
         * ����more���Ե�ֵ��
         * 
         */
        public void setMore(int value) {
            this.more = value;
        }

        /**
         * ��ȡless���Ե�ֵ��
         * 
         */
        public int getLess() {
            return less;
        }

        /**
         * ����less���Ե�ֵ��
         * 
         */
        public void setLess(int value) {
            this.less = value;
        }

        /**
         * ��ȡerrMsg���Ե�ֵ��
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getErrMsg() {
            return errMsg;
        }

        /**
         * ����errMsg���Ե�ֵ��
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setErrMsg(String value) {
            this.errMsg = value;
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
     *         &lt;element name="content" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *         &lt;element name="errMsg" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    public static class NotNull {

        protected int content;
        @XmlElement(required = true)
        protected String errMsg;

        /**
         * ��ȡcontent���Ե�ֵ��
         * 
         */
        public int getContent() {
            return content;
        }

        /**
         * ����content���Ե�ֵ��
         * 
         */
        public void setContent(int value) {
            this.content = value;
        }

        /**
         * ��ȡerrMsg���Ե�ֵ��
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getErrMsg() {
            return errMsg;
        }

        /**
         * ����errMsg���Ե�ֵ��
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setErrMsg(String value) {
            this.errMsg = value;
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
     *         &lt;element name="content" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *         &lt;element name="pattern" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="errMsg" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    public static class Template {

        protected int content;
        @XmlElement(required = true)
        protected String pattern;
        @XmlElement(required = true)
        protected String errMsg;

        /**
         * ��ȡcontent���Ե�ֵ��
         * 
         */
        public int getContent() {
            return content;
        }

        /**
         * ����content���Ե�ֵ��
         * 
         */
        public void setContent(int value) {
            this.content = value;
        }

        /**
         * ��ȡpattern���Ե�ֵ��
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPattern() {
            return pattern;
        }

        /**
         * ����pattern���Ե�ֵ��
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPattern(String value) {
            this.pattern = value;
        }

        /**
         * ��ȡerrMsg���Ե�ֵ��
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getErrMsg() {
            return errMsg;
        }

        /**
         * ����errMsg���Ե�ֵ��
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setErrMsg(String value) {
            this.errMsg = value;
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
     *         &lt;element name="content" type="{http://www.w3.org/2001/XMLSchema}int"/>
     *         &lt;element name="errMsg" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    public static class ValidateCJK {

        protected int content;
        @XmlElement(required = true)
        protected String errMsg;

        /**
         * ��ȡcontent���Ե�ֵ��
         * 
         */
        public int getContent() {
            return content;
        }

        /**
         * ����content���Ե�ֵ��
         * 
         */
        public void setContent(int value) {
            this.content = value;
        }

        /**
         * ��ȡerrMsg���Ե�ֵ��
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getErrMsg() {
            return errMsg;
        }

        /**
         * ����errMsg���Ե�ֵ��
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setErrMsg(String value) {
            this.errMsg = value;
        }

    }

}
