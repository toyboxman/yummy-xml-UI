//
// ���ļ����� JavaTM Architecture for XML Binding (JAXB) ����ʵ�� v2.2.8-b130911.1802 ���ɵ�
// ����� <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// �����±���Դģʽʱ, �Դ��ļ��������޸Ķ�����ʧ��
// ����ʱ��: 2015.08.15 ʱ�� 12:04:31 PM CST 
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
 * <p>action complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
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
 *         &lt;element name="sendMsgAction" type="{}msgSendAction" minOccurs="0"/>
 *         &lt;element name="insertICardAction" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;all>
 *                   &lt;element name="suceessfulPanel" type="{http://www.w3.org/2001/XMLSchema}unsignedShort"/>
 *                   &lt;element name="failedPanel" type="{http://www.w3.org/2001/XMLSchema}unsignedShort"/>
 *                 &lt;/all>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="writeICardAction" type="{}msgSendAction" minOccurs="0"/>
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
    "sendMsgAction",
    "insertICardAction",
    "writeICardAction"
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
    protected MsgSendAction sendMsgAction;
    protected Action.InsertICardAction insertICardAction;
    protected MsgSendAction writeICardAction;

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
     * ��ȡbuttonJumpPanelAction���Ե�ֵ��
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
     * ����buttonJumpPanelAction���Ե�ֵ��
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
     * ��ȡfontSetAction���Ե�ֵ��
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
     * ����fontSetAction���Ե�ֵ��
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
     * ��ȡcleanAction���Ե�ֵ��
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
     * ����cleanAction���Ե�ֵ��
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
     * ��ȡfileUploadAction���Ե�ֵ��
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
     * ����fileUploadAction���Ե�ֵ��
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
     * ��ȡlimitInputAction���Ե�ֵ��
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
     * ����limitInputAction���Ե�ֵ��
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
     * ��ȡuseTipAction���Ե�ֵ��
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
     * ����useTipAction���Ե�ֵ��
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
     * ��ȡtableShowAction���Ե�ֵ��
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
     * ����tableShowAction���Ե�ֵ��
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
     * ��ȡcomboShowAction���Ե�ֵ��
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
     * ����comboShowAction���Ե�ֵ��
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
     * ��ȡmediaPlayAction���Ե�ֵ��
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
     * ����mediaPlayAction���Ե�ֵ��
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
     * ��ȡrunCommandAction���Ե�ֵ��
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
     * ����runCommandAction���Ե�ֵ��
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
     * ��ȡvirtualKeyboardAction���Ե�ֵ��
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
     * ����virtualKeyboardAction���Ե�ֵ��
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
     * ��ȡprinterAction���Ե�ֵ��
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
     * ����printerAction���Ե�ֵ��
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
     * ��ȡwebLoadAction���Ե�ֵ��
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
     * ����webLoadAction���Ե�ֵ��
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
     * ��ȡsendMsgAction���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link MsgSendAction }
     *     
     */
    public MsgSendAction getSendMsgAction() {
        return sendMsgAction;
    }

    /**
     * ����sendMsgAction���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link MsgSendAction }
     *     
     */
    public void setSendMsgAction(MsgSendAction value) {
        this.sendMsgAction = value;
    }

    /**
     * ��ȡinsertICardAction���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Action.InsertICardAction }
     *     
     */
    public Action.InsertICardAction getInsertICardAction() {
        return insertICardAction;
    }

    /**
     * ����insertICardAction���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Action.InsertICardAction }
     *     
     */
    public void setInsertICardAction(Action.InsertICardAction value) {
        this.insertICardAction = value;
    }

    /**
     * ��ȡwriteICardAction���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link MsgSendAction }
     *     
     */
    public MsgSendAction getWriteICardAction() {
        return writeICardAction;
    }

    /**
     * ����writeICardAction���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link MsgSendAction }
     *     
     */
    public void setWriteICardAction(MsgSendAction value) {
        this.writeICardAction = value;
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
         * ��ȡitems���Ե�ֵ��
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
         * ����items���Ե�ֵ��
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
     * <p>anonymous complex type�� Java �ࡣ
     * 
     * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
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
         * ��ȡfilter���Ե�ֵ��
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
         * ����filter���Ե�ֵ��
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
         * ��ȡuploadPath���Ե�ֵ��
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
         * ����uploadPath���Ե�ֵ��
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
     * <p>anonymous complex type�� Java �ࡣ
     * 
     * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
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
         * ��ȡfontName���Ե�ֵ��
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
         * ����fontName���Ե�ֵ��
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
         * ��ȡfontSize���Ե�ֵ��
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
         * ����fontSize���Ե�ֵ��
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
         * ��ȡfontStyle���Ե�ֵ��
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
         * ����fontStyle���Ե�ֵ��
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
     * <p>anonymous complex type�� Java �ࡣ
     * 
     * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;all>
     *         &lt;element name="suceessfulPanel" type="{http://www.w3.org/2001/XMLSchema}unsignedShort"/>
     *         &lt;element name="failedPanel" type="{http://www.w3.org/2001/XMLSchema}unsignedShort"/>
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
    public static class InsertICardAction {

        @XmlSchemaType(name = "unsignedShort")
        protected int suceessfulPanel;
        @XmlSchemaType(name = "unsignedShort")
        protected int failedPanel;

        /**
         * ��ȡsuceessfulPanel���Ե�ֵ��
         * 
         */
        public int getSuceessfulPanel() {
            return suceessfulPanel;
        }

        /**
         * ����suceessfulPanel���Ե�ֵ��
         * 
         */
        public void setSuceessfulPanel(int value) {
            this.suceessfulPanel = value;
        }

        /**
         * ��ȡfailedPanel���Ե�ֵ��
         * 
         */
        public int getFailedPanel() {
            return failedPanel;
        }

        /**
         * ����failedPanel���Ե�ֵ��
         * 
         */
        public void setFailedPanel(int value) {
            this.failedPanel = value;
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
         * ��ȡlength���Ե�ֵ��
         * 
         */
        public byte getLength() {
            return length;
        }

        /**
         * ����length���Ե�ֵ��
         * 
         */
        public void setLength(byte value) {
            this.length = value;
        }

        /**
         * ��ȡenableCashLimit���Ե�ֵ��
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
         * ����enableCashLimit���Ե�ֵ��
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
     * <p>anonymous complex type�� Java �ࡣ
     * 
     * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
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
         * ��ȡmedia���Ե�ֵ��
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
         * ����media���Ե�ֵ��
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
     * <p>anonymous complex type�� Java �ࡣ
     * 
     * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
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
         * ��ȡheader���Ե�ֵ��
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
         * ����header���Ե�ֵ��
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
         * ��ȡtail���Ե�ֵ��
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
         * ����tail���Ե�ֵ��
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
     * <p>anonymous complex type�� Java �ࡣ
     * 
     * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
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
         * ��ȡcommand���Ե�ֵ��
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
         * ����command���Ե�ֵ��
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
     * <p>anonymous complex type�� Java �ࡣ
     * 
     * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
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
         * ��ȡcolumnNames���Ե�ֵ��
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
         * ����columnNames���Ե�ֵ��
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
     * <p>anonymous complex type�� Java �ࡣ
     * 
     * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
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
         * ��ȡtip���Ե�ֵ��
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
         * ����tip���Ե�ֵ��
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
     * <p>anonymous complex type�� Java �ࡣ
     * 
     * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
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
         * ��ȡstart���Ե�ֵ��
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
         * ����start���Ե�ֵ��
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
         * ��ȡstop���Ե�ֵ��
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
         * ����stop���Ե�ֵ��
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
     * <p>anonymous complex type�� Java �ࡣ
     * 
     * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
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
         * ��ȡurl���Ե�ֵ��
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
         * ����url���Ե�ֵ��
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
