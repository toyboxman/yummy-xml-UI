//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.8-b130911.1802 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2015.05.25 时间 08:46:55 PM CST 
//


package king.flow.view;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>action complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="action">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="customizedAction" type="{}defined_action" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="buttonJumpPanelAction" type="{}jump_action" minOccurs="0"/>
 *         &lt;element name="fontSetAction" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="fontName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="fontSize" type="{http://www.w3.org/2001/XMLSchema}unsignedShort" minOccurs="0"/>
 *                   &lt;element name="fontStyle" type="{}fontstyle_enum" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="cleanAction" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;all>
 *                   &lt;element name="conditions" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/all>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="fileUploadAction" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;all>
 *                   &lt;element name="filter" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="uploadPath" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/all>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="limitInputAction" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;all>
 *                   &lt;element name="length" type="{http://www.w3.org/2001/XMLSchema}byte"/>
 *                   &lt;element name="enableCashLimit" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                 &lt;/all>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="useTipAction" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;all>
 *                   &lt;element name="tip" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/all>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="tableShowAction" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;all>
 *                   &lt;element name="columnNames" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/all>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="comboShowAction" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;all>
 *                   &lt;element name="items" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/all>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="mediaPlayAction" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;all>
 *                   &lt;element name="media" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/all>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="runCommandAction" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;all>
 *                   &lt;element name="command" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/all>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="virtualKeyboardAction" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;all>
 *                   &lt;element name="start" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="stop" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/all>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="printerAction" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;all>
 *                   &lt;element name="header" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="tail" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/all>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="webLoadAction" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;all>
 *                   &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/all>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="msgSendAction" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;all>
 *                   &lt;element name="prsCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="cmdCode" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                   &lt;element name="conditions" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="nextStep">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;all>
 *                             &lt;element name="nextPanel" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                             &lt;element name="display" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                           &lt;/all>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="exception">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;all>
 *                             &lt;element name="nextPanel" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                             &lt;element name="display" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *                           &lt;/all>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="checkRules" type="{}rules" minOccurs="0"/>
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
@XmlType(name = "action", propOrder = {
    "customizedAction",
    "buttonJumpPanelAction",
    "fontSetAction",
    "cleanAction",
    "fileUploadAction",
    "limitInputAction",
    "useTipAction",
    "tableShowAction",
    "comboShowAction",
    "mediaPlayAction",
    "runCommandAction",
    "virtualKeyboardAction",
    "printerAction",
    "webLoadAction",
    "msgSendAction"
})
public class Action {

    protected List<DefinedAction> customizedAction;
    protected JumpAction buttonJumpPanelAction;
    protected Action.FontSetAction fontSetAction;
    protected Action.CleanAction cleanAction;
    protected Action.FileUploadAction fileUploadAction;
    protected Action.LimitInputAction limitInputAction;
    protected Action.UseTipAction useTipAction;
    protected Action.TableShowAction tableShowAction;
    protected Action.ComboShowAction comboShowAction;
    protected Action.MediaPlayAction mediaPlayAction;
    protected Action.RunCommandAction runCommandAction;
    protected Action.VirtualKeyboardAction virtualKeyboardAction;
    protected Action.PrinterAction printerAction;
    protected Action.WebLoadAction webLoadAction;
    protected Action.MsgSendAction msgSendAction;

    /**
     * Gets the value of the customizedAction property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the customizedAction property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCustomizedAction().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DefinedAction }
     * 
     * 
     */
    public List<DefinedAction> getCustomizedAction() {
        if (customizedAction == null) {
            customizedAction = new ArrayList<DefinedAction>();
        }
        return this.customizedAction;
    }

    /**
     * 获取buttonJumpPanelAction属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JumpAction }
     *     
     */
    public JumpAction getButtonJumpPanelAction() {
        return buttonJumpPanelAction;
    }

    /**
     * 设置buttonJumpPanelAction属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JumpAction }
     *     
     */
    public void setButtonJumpPanelAction(JumpAction value) {
        this.buttonJumpPanelAction = value;
    }

    /**
     * 获取fontSetAction属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Action.FontSetAction }
     *     
     */
    public Action.FontSetAction getFontSetAction() {
        return fontSetAction;
    }

    /**
     * 设置fontSetAction属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Action.FontSetAction }
     *     
     */
    public void setFontSetAction(Action.FontSetAction value) {
        this.fontSetAction = value;
    }

    /**
     * 获取cleanAction属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Action.CleanAction }
     *     
     */
    public Action.CleanAction getCleanAction() {
        return cleanAction;
    }

    /**
     * 设置cleanAction属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Action.CleanAction }
     *     
     */
    public void setCleanAction(Action.CleanAction value) {
        this.cleanAction = value;
    }

    /**
     * 获取fileUploadAction属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Action.FileUploadAction }
     *     
     */
    public Action.FileUploadAction getFileUploadAction() {
        return fileUploadAction;
    }

    /**
     * 设置fileUploadAction属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Action.FileUploadAction }
     *     
     */
    public void setFileUploadAction(Action.FileUploadAction value) {
        this.fileUploadAction = value;
    }

    /**
     * 获取limitInputAction属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Action.LimitInputAction }
     *     
     */
    public Action.LimitInputAction getLimitInputAction() {
        return limitInputAction;
    }

    /**
     * 设置limitInputAction属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Action.LimitInputAction }
     *     
     */
    public void setLimitInputAction(Action.LimitInputAction value) {
        this.limitInputAction = value;
    }

    /**
     * 获取useTipAction属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Action.UseTipAction }
     *     
     */
    public Action.UseTipAction getUseTipAction() {
        return useTipAction;
    }

    /**
     * 设置useTipAction属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Action.UseTipAction }
     *     
     */
    public void setUseTipAction(Action.UseTipAction value) {
        this.useTipAction = value;
    }

    /**
     * 获取tableShowAction属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Action.TableShowAction }
     *     
     */
    public Action.TableShowAction getTableShowAction() {
        return tableShowAction;
    }

    /**
     * 设置tableShowAction属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Action.TableShowAction }
     *     
     */
    public void setTableShowAction(Action.TableShowAction value) {
        this.tableShowAction = value;
    }

    /**
     * 获取comboShowAction属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Action.ComboShowAction }
     *     
     */
    public Action.ComboShowAction getComboShowAction() {
        return comboShowAction;
    }

    /**
     * 设置comboShowAction属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Action.ComboShowAction }
     *     
     */
    public void setComboShowAction(Action.ComboShowAction value) {
        this.comboShowAction = value;
    }

    /**
     * 获取mediaPlayAction属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Action.MediaPlayAction }
     *     
     */
    public Action.MediaPlayAction getMediaPlayAction() {
        return mediaPlayAction;
    }

    /**
     * 设置mediaPlayAction属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Action.MediaPlayAction }
     *     
     */
    public void setMediaPlayAction(Action.MediaPlayAction value) {
        this.mediaPlayAction = value;
    }

    /**
     * 获取runCommandAction属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Action.RunCommandAction }
     *     
     */
    public Action.RunCommandAction getRunCommandAction() {
        return runCommandAction;
    }

    /**
     * 设置runCommandAction属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Action.RunCommandAction }
     *     
     */
    public void setRunCommandAction(Action.RunCommandAction value) {
        this.runCommandAction = value;
    }

    /**
     * 获取virtualKeyboardAction属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Action.VirtualKeyboardAction }
     *     
     */
    public Action.VirtualKeyboardAction getVirtualKeyboardAction() {
        return virtualKeyboardAction;
    }

    /**
     * 设置virtualKeyboardAction属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Action.VirtualKeyboardAction }
     *     
     */
    public void setVirtualKeyboardAction(Action.VirtualKeyboardAction value) {
        this.virtualKeyboardAction = value;
    }

    /**
     * 获取printerAction属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Action.PrinterAction }
     *     
     */
    public Action.PrinterAction getPrinterAction() {
        return printerAction;
    }

    /**
     * 设置printerAction属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Action.PrinterAction }
     *     
     */
    public void setPrinterAction(Action.PrinterAction value) {
        this.printerAction = value;
    }

    /**
     * 获取webLoadAction属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Action.WebLoadAction }
     *     
     */
    public Action.WebLoadAction getWebLoadAction() {
        return webLoadAction;
    }

    /**
     * 设置webLoadAction属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Action.WebLoadAction }
     *     
     */
    public void setWebLoadAction(Action.WebLoadAction value) {
        this.webLoadAction = value;
    }

    /**
     * 获取msgSendAction属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Action.MsgSendAction }
     *     
     */
    public Action.MsgSendAction getMsgSendAction() {
        return msgSendAction;
    }

    /**
     * 设置msgSendAction属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Action.MsgSendAction }
     *     
     */
    public void setMsgSendAction(Action.MsgSendAction value) {
        this.msgSendAction = value;
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
     *         &lt;element name="conditions" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    public static class CleanAction {

        @XmlElement(required = true)
        protected String conditions;

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
     *         &lt;element name="items" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    public static class ComboShowAction {

        @XmlElement(required = true)
        protected String items;

        /**
         * 获取items属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getItems() {
            return items;
        }

        /**
         * 设置items属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setItems(String value) {
            this.items = value;
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
     *         &lt;element name="filter" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="uploadPath" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    public static class FileUploadAction {

        @XmlElement(required = true)
        protected String filter;
        @XmlElement(required = true)
        protected String uploadPath;

        /**
         * 获取filter属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getFilter() {
            return filter;
        }

        /**
         * 设置filter属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setFilter(String value) {
            this.filter = value;
        }

        /**
         * 获取uploadPath属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getUploadPath() {
            return uploadPath;
        }

        /**
         * 设置uploadPath属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setUploadPath(String value) {
            this.uploadPath = value;
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
     *       &lt;sequence>
     *         &lt;element name="fontName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="fontSize" type="{http://www.w3.org/2001/XMLSchema}unsignedShort" minOccurs="0"/>
     *         &lt;element name="fontStyle" type="{}fontstyle_enum" minOccurs="0"/>
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
        "fontName",
        "fontSize",
        "fontStyle"
    })
    public static class FontSetAction {

        protected String fontName;
        @XmlSchemaType(name = "unsignedShort")
        protected Integer fontSize;
        @XmlSchemaType(name = "string")
        protected FontstyleEnum fontStyle;

        /**
         * 获取fontName属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getFontName() {
            return fontName;
        }

        /**
         * 设置fontName属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setFontName(String value) {
            this.fontName = value;
        }

        /**
         * 获取fontSize属性的值。
         * 
         * @return
         *     possible object is
         *     {@link Integer }
         *     
         */
        public Integer getFontSize() {
            return fontSize;
        }

        /**
         * 设置fontSize属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link Integer }
         *     
         */
        public void setFontSize(Integer value) {
            this.fontSize = value;
        }

        /**
         * 获取fontStyle属性的值。
         * 
         * @return
         *     possible object is
         *     {@link FontstyleEnum }
         *     
         */
        public FontstyleEnum getFontStyle() {
            return fontStyle;
        }

        /**
         * 设置fontStyle属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link FontstyleEnum }
         *     
         */
        public void setFontStyle(FontstyleEnum value) {
            this.fontStyle = value;
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
     *         &lt;element name="length" type="{http://www.w3.org/2001/XMLSchema}byte"/>
     *         &lt;element name="enableCashLimit" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
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
    public static class LimitInputAction {

        protected byte length;
        @XmlElement(defaultValue = "false")
        protected Boolean enableCashLimit;

        /**
         * 获取length属性的值。
         * 
         */
        public byte getLength() {
            return length;
        }

        /**
         * 设置length属性的值。
         * 
         */
        public void setLength(byte value) {
            this.length = value;
        }

        /**
         * 获取enableCashLimit属性的值。
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public Boolean isEnableCashLimit() {
            return enableCashLimit;
        }

        /**
         * 设置enableCashLimit属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setEnableCashLimit(Boolean value) {
            this.enableCashLimit = value;
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
     *         &lt;element name="media" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    public static class MediaPlayAction {

        @XmlElement(required = true)
        protected String media;

        /**
         * 获取media属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getMedia() {
            return media;
        }

        /**
         * 设置media属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setMedia(String value) {
            this.media = value;
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
    @XmlType(name = "", propOrder = {

    })
    public static class MsgSendAction {

        @XmlElement(required = true)
        protected String prsCode;
        @XmlElement(defaultValue = "-1")
        protected Integer cmdCode;
        @XmlElement(required = true)
        protected String conditions;
        @XmlElement(required = true)
        protected Action.MsgSendAction.NextStep nextStep;
        @XmlElement(required = true)
        protected Action.MsgSendAction.Exception exception;
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
         *     {@link Action.MsgSendAction.NextStep }
         *     
         */
        public Action.MsgSendAction.NextStep getNextStep() {
            return nextStep;
        }

        /**
         * 设置nextStep属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link Action.MsgSendAction.NextStep }
         *     
         */
        public void setNextStep(Action.MsgSendAction.NextStep value) {
            this.nextStep = value;
        }

        /**
         * 获取exception属性的值。
         * 
         * @return
         *     possible object is
         *     {@link Action.MsgSendAction.Exception }
         *     
         */
        public Action.MsgSendAction.Exception getException() {
            return exception;
        }

        /**
         * 设置exception属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link Action.MsgSendAction.Exception }
         *     
         */
        public void setException(Action.MsgSendAction.Exception value) {
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
     *         &lt;element name="header" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="tail" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    public static class PrinterAction {

        @XmlElement(required = true)
        protected String header;
        @XmlElement(required = true)
        protected String tail;

        /**
         * 获取header属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getHeader() {
            return header;
        }

        /**
         * 设置header属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setHeader(String value) {
            this.header = value;
        }

        /**
         * 获取tail属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTail() {
            return tail;
        }

        /**
         * 设置tail属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTail(String value) {
            this.tail = value;
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
     *         &lt;element name="command" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    public static class RunCommandAction {

        @XmlElement(required = true)
        protected String command;

        /**
         * 获取command属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCommand() {
            return command;
        }

        /**
         * 设置command属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCommand(String value) {
            this.command = value;
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
     *         &lt;element name="columnNames" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    public static class TableShowAction {

        @XmlElement(required = true)
        protected String columnNames;

        /**
         * 获取columnNames属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getColumnNames() {
            return columnNames;
        }

        /**
         * 设置columnNames属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setColumnNames(String value) {
            this.columnNames = value;
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
     *         &lt;element name="tip" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    public static class UseTipAction {

        @XmlElement(required = true)
        protected String tip;

        /**
         * 获取tip属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTip() {
            return tip;
        }

        /**
         * 设置tip属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTip(String value) {
            this.tip = value;
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
     *         &lt;element name="start" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="stop" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    public static class VirtualKeyboardAction {

        @XmlElement(required = true)
        protected String start;
        @XmlElement(required = true)
        protected String stop;

        /**
         * 获取start属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getStart() {
            return start;
        }

        /**
         * 设置start属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setStart(String value) {
            this.start = value;
        }

        /**
         * 获取stop属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getStop() {
            return stop;
        }

        /**
         * 设置stop属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setStop(String value) {
            this.stop = value;
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
     *         &lt;element name="url" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    public static class WebLoadAction {

        @XmlElement(required = true)
        protected String url;

        /**
         * 获取url属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getUrl() {
            return url;
        }

        /**
         * 设置url属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setUrl(String value) {
            this.url = value;
        }

    }

}
