//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.8-b130911.1802 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2015.08.26 时间 09:58:15 PM CST 
//


package king.flow.view;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>msgSendAction complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
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
     * 获取cmdCode属性的值。
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
     * 设置cmdCode属性的值。
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
     * 获取conditions属性的值。
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
     * 设置conditions属性的值。
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
     * 获取nextStep属性的值。
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
     * 设置nextStep属性的值。
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
     * 获取exception属性的值。
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
     * 设置exception属性的值。
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
     * 获取checkRules属性的值。
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
     * 设置checkRules属性的值。
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
     * <p>anonymous complex type的 Java 类。
     * 
     * <p>以下模式片段指定包含在此类中的预期内容。
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
         * 获取nextPanel属性的值。
         * 
         */
        public int getNextPanel() {
            return nextPanel;
        }

        /**
         * 设置nextPanel属性的值。
         * 
         */
        public void setNextPanel(int value) {
            this.nextPanel = value;
        }

        /**
         * 获取display属性的值。
         * 
         */
        public int getDisplay() {
            return display;
        }

        /**
         * 设置display属性的值。
         * 
         */
        public void setDisplay(int value) {
            this.display = value;
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
         * 获取nextPanel属性的值。
         * 
         */
        public int getNextPanel() {
            return nextPanel;
        }

        /**
         * 设置nextPanel属性的值。
         * 
         */
        public void setNextPanel(int value) {
            this.nextPanel = value;
        }

        /**
         * 获取display属性的值。
         * 
         */
        public int getDisplay() {
            return display;
        }

        /**
         * 设置display属性的值。
         * 
         */
        public void setDisplay(int value) {
            this.display = value;
        }

    }

}
