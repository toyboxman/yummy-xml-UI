//
// ���ļ����� JavaTM Architecture for XML Binding (JAXB) ����ʵ�� v2.2.8-b130911.1802 ���ɵ�
// ����� <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// �����±���Դģʽʱ, �Դ��ļ��������޸Ķ�����ʧ��
// ����ʱ��: 2015.11.28 ʱ�� 08:04:46 PM CST 
//


package king.flow.view;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
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
 *         &lt;element name="jumpPanelAction" type="{}jump_action" minOccurs="0"/>
 *         &lt;element name="setFontAction" minOccurs="0">
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
 *         &lt;element name="uploadFileAction" minOccurs="0">
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
 *         &lt;element name="moveCursorAction" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;all>
 *                   &lt;element name="upCursor" type="{http://www.w3.org/2001/XMLSchema}unsignedShort"/>
 *                   &lt;element name="downCursor" type="{http://www.w3.org/2001/XMLSchema}unsignedShort"/>
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
 *                 &lt;attribute name="editable" type="{http://www.w3.org/2001/XMLSchema}boolean" default="true" />
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
 *         &lt;element name="showTableAction" minOccurs="0">
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
 *         &lt;element name="showComboBoxAction" minOccurs="0">
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
 *         &lt;element name="playMediaAction" minOccurs="0">
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
 *         &lt;element name="playVideoAction" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;all>
 *                   &lt;element name="media" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="replayInterval" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
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
 *         &lt;element name="setPrinterAction" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;all>
 *                   &lt;element name="header" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="tail" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="debug" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="prompt" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/all>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="swipeCardAction" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;all>
 *                   &lt;element name="nextCursor" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                   &lt;element name="editable" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                   &lt;element name="debug" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="cardId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                           &lt;/sequence>
 *                           &lt;attribute name="limit" type="{http://www.w3.org/2001/XMLSchema}boolean" default="true" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/all>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="swipe2In1CardAction" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;all>
 *                   &lt;element name="nextCursor" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                   &lt;element name="editable" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                   &lt;element name="mediaTip" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="animationTip" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="debug" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="cardId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                           &lt;/sequence>
 *                           &lt;attribute name="limit" type="{http://www.w3.org/2001/XMLSchema}boolean" default="true" />
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/all>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="rwFingerPrintAction" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;all>
 *                   &lt;element name="nextCursor" type="{http://www.w3.org/2001/XMLSchema}unsignedShort" minOccurs="0"/>
 *                   &lt;element name="writePrint" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                 &lt;/all>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="openBrowserAction" minOccurs="0">
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
 *         &lt;element name="printPassbookAction" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;all>
 *                   &lt;element name="tableId" type="{http://www.w3.org/2001/XMLSchema}unsignedShort"/>
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
 *                   &lt;element name="animationTip" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                 &lt;/all>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="writeICardAction" type="{}msgSendAction" minOccurs="0"/>
 *         &lt;element name="showClockAction" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;all>
 *                   &lt;element name="format" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "jumpPanelAction",
    "setFontAction",
    "cleanAction",
    "uploadFileAction",
    "moveCursorAction",
    "limitInputAction",
    "useTipAction",
    "showTableAction",
    "showComboBoxAction",
    "playMediaAction",
    "playVideoAction",
    "runCommandAction",
    "virtualKeyboardAction",
    "setPrinterAction",
    "swipeCardAction",
    "swipe2In1CardAction",
    "rwFingerPrintAction",
    "openBrowserAction",
    "webLoadAction",
    "printPassbookAction",
    "sendMsgAction",
    "insertICardAction",
    "writeICardAction",
    "showClockAction"
})
public class Action {

    protected List<DefinedAction> customizedAction;
    protected JumpAction jumpPanelAction;
    protected Action.SetFontAction setFontAction;
    protected Action.CleanAction cleanAction;
    protected Action.UploadFileAction uploadFileAction;
    protected Action.MoveCursorAction moveCursorAction;
    protected Action.LimitInputAction limitInputAction;
    protected Action.UseTipAction useTipAction;
    protected Action.ShowTableAction showTableAction;
    protected Action.ShowComboBoxAction showComboBoxAction;
    protected Action.PlayMediaAction playMediaAction;
    protected Action.PlayVideoAction playVideoAction;
    protected Action.RunCommandAction runCommandAction;
    protected Action.VirtualKeyboardAction virtualKeyboardAction;
    protected Action.SetPrinterAction setPrinterAction;
    protected Action.SwipeCardAction swipeCardAction;
    protected Action.Swipe2In1CardAction swipe2In1CardAction;
    protected Action.RwFingerPrintAction rwFingerPrintAction;
    protected Action.OpenBrowserAction openBrowserAction;
    protected Action.WebLoadAction webLoadAction;
    protected Action.PrintPassbookAction printPassbookAction;
    protected MsgSendAction sendMsgAction;
    protected Action.InsertICardAction insertICardAction;
    protected MsgSendAction writeICardAction;
    protected Action.ShowClockAction showClockAction;

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
     * ��ȡjumpPanelAction���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link JumpAction }
     *     
     */
    public JumpAction getJumpPanelAction() {
        return jumpPanelAction;
    }

    /**
     * ����jumpPanelAction���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link JumpAction }
     *     
     */
    public void setJumpPanelAction(JumpAction value) {
        this.jumpPanelAction = value;
    }

    /**
     * ��ȡsetFontAction���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Action.SetFontAction }
     *     
     */
    public Action.SetFontAction getSetFontAction() {
        return setFontAction;
    }

    /**
     * ����setFontAction���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Action.SetFontAction }
     *     
     */
    public void setSetFontAction(Action.SetFontAction value) {
        this.setFontAction = value;
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
     * ��ȡuploadFileAction���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Action.UploadFileAction }
     *     
     */
    public Action.UploadFileAction getUploadFileAction() {
        return uploadFileAction;
    }

    /**
     * ����uploadFileAction���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Action.UploadFileAction }
     *     
     */
    public void setUploadFileAction(Action.UploadFileAction value) {
        this.uploadFileAction = value;
    }

    /**
     * ��ȡmoveCursorAction���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Action.MoveCursorAction }
     *     
     */
    public Action.MoveCursorAction getMoveCursorAction() {
        return moveCursorAction;
    }

    /**
     * ����moveCursorAction���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Action.MoveCursorAction }
     *     
     */
    public void setMoveCursorAction(Action.MoveCursorAction value) {
        this.moveCursorAction = value;
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
     * ��ȡshowTableAction���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Action.ShowTableAction }
     *     
     */
    public Action.ShowTableAction getShowTableAction() {
        return showTableAction;
    }

    /**
     * ����showTableAction���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Action.ShowTableAction }
     *     
     */
    public void setShowTableAction(Action.ShowTableAction value) {
        this.showTableAction = value;
    }

    /**
     * ��ȡshowComboBoxAction���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Action.ShowComboBoxAction }
     *     
     */
    public Action.ShowComboBoxAction getShowComboBoxAction() {
        return showComboBoxAction;
    }

    /**
     * ����showComboBoxAction���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Action.ShowComboBoxAction }
     *     
     */
    public void setShowComboBoxAction(Action.ShowComboBoxAction value) {
        this.showComboBoxAction = value;
    }

    /**
     * ��ȡplayMediaAction���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Action.PlayMediaAction }
     *     
     */
    public Action.PlayMediaAction getPlayMediaAction() {
        return playMediaAction;
    }

    /**
     * ����playMediaAction���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Action.PlayMediaAction }
     *     
     */
    public void setPlayMediaAction(Action.PlayMediaAction value) {
        this.playMediaAction = value;
    }

    /**
     * ��ȡplayVideoAction���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Action.PlayVideoAction }
     *     
     */
    public Action.PlayVideoAction getPlayVideoAction() {
        return playVideoAction;
    }

    /**
     * ����playVideoAction���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Action.PlayVideoAction }
     *     
     */
    public void setPlayVideoAction(Action.PlayVideoAction value) {
        this.playVideoAction = value;
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
     * ��ȡsetPrinterAction���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Action.SetPrinterAction }
     *     
     */
    public Action.SetPrinterAction getSetPrinterAction() {
        return setPrinterAction;
    }

    /**
     * ����setPrinterAction���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Action.SetPrinterAction }
     *     
     */
    public void setSetPrinterAction(Action.SetPrinterAction value) {
        this.setPrinterAction = value;
    }

    /**
     * ��ȡswipeCardAction���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Action.SwipeCardAction }
     *     
     */
    public Action.SwipeCardAction getSwipeCardAction() {
        return swipeCardAction;
    }

    /**
     * ����swipeCardAction���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Action.SwipeCardAction }
     *     
     */
    public void setSwipeCardAction(Action.SwipeCardAction value) {
        this.swipeCardAction = value;
    }

    /**
     * ��ȡswipe2In1CardAction���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Action.Swipe2In1CardAction }
     *     
     */
    public Action.Swipe2In1CardAction getSwipe2In1CardAction() {
        return swipe2In1CardAction;
    }

    /**
     * ����swipe2In1CardAction���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Action.Swipe2In1CardAction }
     *     
     */
    public void setSwipe2In1CardAction(Action.Swipe2In1CardAction value) {
        this.swipe2In1CardAction = value;
    }

    /**
     * ��ȡrwFingerPrintAction���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Action.RwFingerPrintAction }
     *     
     */
    public Action.RwFingerPrintAction getRwFingerPrintAction() {
        return rwFingerPrintAction;
    }

    /**
     * ����rwFingerPrintAction���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Action.RwFingerPrintAction }
     *     
     */
    public void setRwFingerPrintAction(Action.RwFingerPrintAction value) {
        this.rwFingerPrintAction = value;
    }

    /**
     * ��ȡopenBrowserAction���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Action.OpenBrowserAction }
     *     
     */
    public Action.OpenBrowserAction getOpenBrowserAction() {
        return openBrowserAction;
    }

    /**
     * ����openBrowserAction���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Action.OpenBrowserAction }
     *     
     */
    public void setOpenBrowserAction(Action.OpenBrowserAction value) {
        this.openBrowserAction = value;
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
     * ��ȡprintPassbookAction���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Action.PrintPassbookAction }
     *     
     */
    public Action.PrintPassbookAction getPrintPassbookAction() {
        return printPassbookAction;
    }

    /**
     * ����printPassbookAction���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Action.PrintPassbookAction }
     *     
     */
    public void setPrintPassbookAction(Action.PrintPassbookAction value) {
        this.printPassbookAction = value;
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
     * ��ȡshowClockAction���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Action.ShowClockAction }
     *     
     */
    public Action.ShowClockAction getShowClockAction() {
        return showClockAction;
    }

    /**
     * ����showClockAction���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Action.ShowClockAction }
     *     
     */
    public void setShowClockAction(Action.ShowClockAction value) {
        this.showClockAction = value;
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
     *         &lt;element name="suceessfulPanel" type="{http://www.w3.org/2001/XMLSchema}unsignedShort"/>
     *         &lt;element name="failedPanel" type="{http://www.w3.org/2001/XMLSchema}unsignedShort"/>
     *         &lt;element name="animationTip" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
        protected String animationTip;

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

        /**
         * ��ȡanimationTip���Ե�ֵ��
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAnimationTip() {
            return animationTip;
        }

        /**
         * ����animationTip���Ե�ֵ��
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAnimationTip(String value) {
            this.animationTip = value;
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
     *       &lt;attribute name="editable" type="{http://www.w3.org/2001/XMLSchema}boolean" default="true" />
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
        @XmlAttribute(name = "editable")
        protected Boolean editable;

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

        /**
         * ��ȡeditable���Ե�ֵ��
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public boolean isEditable() {
            if (editable == null) {
                return true;
            } else {
                return editable;
            }
        }

        /**
         * ����editable���Ե�ֵ��
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setEditable(Boolean value) {
            this.editable = value;
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
     *         &lt;element name="upCursor" type="{http://www.w3.org/2001/XMLSchema}unsignedShort"/>
     *         &lt;element name="downCursor" type="{http://www.w3.org/2001/XMLSchema}unsignedShort"/>
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
    public static class MoveCursorAction {

        @XmlSchemaType(name = "unsignedShort")
        protected int upCursor;
        @XmlSchemaType(name = "unsignedShort")
        protected int downCursor;

        /**
         * ��ȡupCursor���Ե�ֵ��
         * 
         */
        public int getUpCursor() {
            return upCursor;
        }

        /**
         * ����upCursor���Ե�ֵ��
         * 
         */
        public void setUpCursor(int value) {
            this.upCursor = value;
        }

        /**
         * ��ȡdownCursor���Ե�ֵ��
         * 
         */
        public int getDownCursor() {
            return downCursor;
        }

        /**
         * ����downCursor���Ե�ֵ��
         * 
         */
        public void setDownCursor(int value) {
            this.downCursor = value;
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
    public static class OpenBrowserAction {

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
    public static class PlayMediaAction {

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
     *         &lt;element name="media" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="replayInterval" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
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
    public static class PlayVideoAction {

        @XmlElement(required = true)
        protected String media;
        @XmlElement(defaultValue = "20")
        protected Integer replayInterval;

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

        /**
         * ��ȡreplayInterval���Ե�ֵ��
         * 
         * @return
         *     possible object is
         *     {@link Integer }
         *     
         */
        public Integer getReplayInterval() {
            return replayInterval;
        }

        /**
         * ����replayInterval���Ե�ֵ��
         * 
         * @param value
         *     allowed object is
         *     {@link Integer }
         *     
         */
        public void setReplayInterval(Integer value) {
            this.replayInterval = value;
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
     *         &lt;element name="tableId" type="{http://www.w3.org/2001/XMLSchema}unsignedShort"/>
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
    public static class PrintPassbookAction {

        @XmlSchemaType(name = "unsignedShort")
        protected int tableId;

        /**
         * ��ȡtableId���Ե�ֵ��
         * 
         */
        public int getTableId() {
            return tableId;
        }

        /**
         * ����tableId���Ե�ֵ��
         * 
         */
        public void setTableId(int value) {
            this.tableId = value;
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
     *         &lt;element name="nextCursor" type="{http://www.w3.org/2001/XMLSchema}unsignedShort" minOccurs="0"/>
     *         &lt;element name="writePrint" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
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
    public static class RwFingerPrintAction {

        @XmlSchemaType(name = "unsignedShort")
        protected Integer nextCursor;
        protected Boolean writePrint;

        /**
         * ��ȡnextCursor���Ե�ֵ��
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
         * ����nextCursor���Ե�ֵ��
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
         * ��ȡwritePrint���Ե�ֵ��
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public Boolean isWritePrint() {
            return writePrint;
        }

        /**
         * ����writePrint���Ե�ֵ��
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setWritePrint(Boolean value) {
            this.writePrint = value;
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
    public static class SetFontAction {

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
     *         &lt;element name="header" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="tail" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="debug" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="prompt" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
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
    public static class SetPrinterAction {

        @XmlElement(required = true)
        protected String header;
        @XmlElement(required = true)
        protected String tail;
        protected Action.SetPrinterAction.Debug debug;

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

        /**
         * ��ȡdebug���Ե�ֵ��
         * 
         * @return
         *     possible object is
         *     {@link Action.SetPrinterAction.Debug }
         *     
         */
        public Action.SetPrinterAction.Debug getDebug() {
            return debug;
        }

        /**
         * ����debug���Ե�ֵ��
         * 
         * @param value
         *     allowed object is
         *     {@link Action.SetPrinterAction.Debug }
         *     
         */
        public void setDebug(Action.SetPrinterAction.Debug value) {
            this.debug = value;
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
         *         &lt;element name="prompt" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
            "prompt"
        })
        public static class Debug {

            @XmlElement(required = true)
            protected String prompt;

            /**
             * ��ȡprompt���Ե�ֵ��
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getPrompt() {
                return prompt;
            }

            /**
             * ����prompt���Ե�ֵ��
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setPrompt(String value) {
                this.prompt = value;
            }

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
     *         &lt;element name="format" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    public static class ShowClockAction {

        protected String format;

        /**
         * ��ȡformat���Ե�ֵ��
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getFormat() {
            return format;
        }

        /**
         * ����format���Ե�ֵ��
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setFormat(String value) {
            this.format = value;
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
    public static class ShowComboBoxAction {

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
    public static class ShowTableAction {

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
     *         &lt;element name="nextCursor" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *         &lt;element name="editable" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *         &lt;element name="mediaTip" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="animationTip" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="debug" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="cardId" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                 &lt;/sequence>
     *                 &lt;attribute name="limit" type="{http://www.w3.org/2001/XMLSchema}boolean" default="true" />
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
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
    public static class Swipe2In1CardAction {

        protected Integer nextCursor;
        protected Boolean editable;
        protected String mediaTip;
        protected String animationTip;
        protected Action.Swipe2In1CardAction.Debug debug;

        /**
         * ��ȡnextCursor���Ե�ֵ��
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
         * ����nextCursor���Ե�ֵ��
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
         * ��ȡeditable���Ե�ֵ��
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public Boolean isEditable() {
            return editable;
        }

        /**
         * ����editable���Ե�ֵ��
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setEditable(Boolean value) {
            this.editable = value;
        }

        /**
         * ��ȡmediaTip���Ե�ֵ��
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getMediaTip() {
            return mediaTip;
        }

        /**
         * ����mediaTip���Ե�ֵ��
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setMediaTip(String value) {
            this.mediaTip = value;
        }

        /**
         * ��ȡanimationTip���Ե�ֵ��
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAnimationTip() {
            return animationTip;
        }

        /**
         * ����animationTip���Ե�ֵ��
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAnimationTip(String value) {
            this.animationTip = value;
        }

        /**
         * ��ȡdebug���Ե�ֵ��
         * 
         * @return
         *     possible object is
         *     {@link Action.Swipe2In1CardAction.Debug }
         *     
         */
        public Action.Swipe2In1CardAction.Debug getDebug() {
            return debug;
        }

        /**
         * ����debug���Ե�ֵ��
         * 
         * @param value
         *     allowed object is
         *     {@link Action.Swipe2In1CardAction.Debug }
         *     
         */
        public void setDebug(Action.Swipe2In1CardAction.Debug value) {
            this.debug = value;
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
         *         &lt;element name="cardId" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *       &lt;/sequence>
         *       &lt;attribute name="limit" type="{http://www.w3.org/2001/XMLSchema}boolean" default="true" />
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "cardId"
        })
        public static class Debug {

            @XmlElement(required = true)
            protected String cardId;
            @XmlAttribute(name = "limit")
            protected Boolean limit;

            /**
             * ��ȡcardId���Ե�ֵ��
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCardId() {
                return cardId;
            }

            /**
             * ����cardId���Ե�ֵ��
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCardId(String value) {
                this.cardId = value;
            }

            /**
             * ��ȡlimit���Ե�ֵ��
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public boolean isLimit() {
                if (limit == null) {
                    return true;
                } else {
                    return limit;
                }
            }

            /**
             * ����limit���Ե�ֵ��
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setLimit(Boolean value) {
                this.limit = value;
            }

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
     *         &lt;element name="nextCursor" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *         &lt;element name="editable" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *         &lt;element name="debug" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="cardId" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                 &lt;/sequence>
     *                 &lt;attribute name="limit" type="{http://www.w3.org/2001/XMLSchema}boolean" default="true" />
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
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
    public static class SwipeCardAction {

        protected Integer nextCursor;
        protected Boolean editable;
        protected Action.SwipeCardAction.Debug debug;

        /**
         * ��ȡnextCursor���Ե�ֵ��
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
         * ����nextCursor���Ե�ֵ��
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
         * ��ȡeditable���Ե�ֵ��
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public Boolean isEditable() {
            return editable;
        }

        /**
         * ����editable���Ե�ֵ��
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setEditable(Boolean value) {
            this.editable = value;
        }

        /**
         * ��ȡdebug���Ե�ֵ��
         * 
         * @return
         *     possible object is
         *     {@link Action.SwipeCardAction.Debug }
         *     
         */
        public Action.SwipeCardAction.Debug getDebug() {
            return debug;
        }

        /**
         * ����debug���Ե�ֵ��
         * 
         * @param value
         *     allowed object is
         *     {@link Action.SwipeCardAction.Debug }
         *     
         */
        public void setDebug(Action.SwipeCardAction.Debug value) {
            this.debug = value;
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
         *         &lt;element name="cardId" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *       &lt;/sequence>
         *       &lt;attribute name="limit" type="{http://www.w3.org/2001/XMLSchema}boolean" default="true" />
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "cardId"
        })
        public static class Debug {

            @XmlElement(required = true)
            protected String cardId;
            @XmlAttribute(name = "limit")
            protected Boolean limit;

            /**
             * ��ȡcardId���Ե�ֵ��
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getCardId() {
                return cardId;
            }

            /**
             * ����cardId���Ե�ֵ��
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setCardId(String value) {
                this.cardId = value;
            }

            /**
             * ��ȡlimit���Ե�ֵ��
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public boolean isLimit() {
                if (limit == null) {
                    return true;
                } else {
                    return limit;
                }
            }

            /**
             * ����limit���Ե�ֵ��
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setLimit(Boolean value) {
                this.limit = value;
            }

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
    public static class UploadFileAction {

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
