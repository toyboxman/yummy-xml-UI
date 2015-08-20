//
// ���ļ����� JavaTM Architecture for XML Binding (JAXB) ����ʵ�� v2.2.8-b130911.1802 ���ɵ�
// ����� <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// �����±���Դģʽʱ, �Դ��ļ��������޸Ķ�����ʧ��
// ����ʱ��: 2015.08.20 ʱ�� 10:46:47 PM CST 
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
 * <p>window complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="window">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="type" type="{}window_enum"/>
 *         &lt;element name="ui-style" type="{}ui_style" minOccurs="0"/>
 *         &lt;element name="driver" type="{}driver" minOccurs="0"/>
 *         &lt;element name="attribute" type="{}basic_attribute"/>
 *         &lt;element name="heartbeat" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="menubar" type="{}menubar" minOccurs="0"/>
 *         &lt;element name="contents">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="page" type="{http://www.w3.org/2001/XMLSchema}anyURI" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
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
@XmlType(name = "window", propOrder = {
    "id",
    "type",
    "uiStyle",
    "driver",
    "attribute",
    "heartbeat",
    "menubar",
    "contents"
})
public class Window {

    protected int id;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected WindowEnum type;
    @XmlElement(name = "ui-style")
    protected UiStyle uiStyle;
    protected Driver driver;
    @XmlElement(required = true)
    protected BasicAttribute attribute;
    protected Boolean heartbeat;
    protected Menubar menubar;
    @XmlElement(required = true)
    protected Window.Contents contents;

    /**
     * ��ȡid���Ե�ֵ��
     * 
     */
    public int getId() {
        return id;
    }

    /**
     * ����id���Ե�ֵ��
     * 
     */
    public void setId(int value) {
        this.id = value;
    }

    /**
     * ��ȡtype���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link WindowEnum }
     *     
     */
    public WindowEnum getType() {
        return type;
    }

    /**
     * ����type���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link WindowEnum }
     *     
     */
    public void setType(WindowEnum value) {
        this.type = value;
    }

    /**
     * ��ȡuiStyle���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link UiStyle }
     *     
     */
    public UiStyle getUiStyle() {
        return uiStyle;
    }

    /**
     * ����uiStyle���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link UiStyle }
     *     
     */
    public void setUiStyle(UiStyle value) {
        this.uiStyle = value;
    }

    /**
     * ��ȡdriver���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Driver }
     *     
     */
    public Driver getDriver() {
        return driver;
    }

    /**
     * ����driver���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Driver }
     *     
     */
    public void setDriver(Driver value) {
        this.driver = value;
    }

    /**
     * ��ȡattribute���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link BasicAttribute }
     *     
     */
    public BasicAttribute getAttribute() {
        return attribute;
    }

    /**
     * ����attribute���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link BasicAttribute }
     *     
     */
    public void setAttribute(BasicAttribute value) {
        this.attribute = value;
    }

    /**
     * ��ȡheartbeat���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isHeartbeat() {
        return heartbeat;
    }

    /**
     * ����heartbeat���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setHeartbeat(Boolean value) {
        this.heartbeat = value;
    }

    /**
     * ��ȡmenubar���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Menubar }
     *     
     */
    public Menubar getMenubar() {
        return menubar;
    }

    /**
     * ����menubar���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Menubar }
     *     
     */
    public void setMenubar(Menubar value) {
        this.menubar = value;
    }

    /**
     * ��ȡcontents���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Window.Contents }
     *     
     */
    public Window.Contents getContents() {
        return contents;
    }

    /**
     * ����contents���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Window.Contents }
     *     
     */
    public void setContents(Window.Contents value) {
        this.contents = value;
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
     *         &lt;element name="page" type="{http://www.w3.org/2001/XMLSchema}anyURI" maxOccurs="unbounded"/>
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
        "page"
    })
    public static class Contents {

        @XmlElement(required = true)
        @XmlSchemaType(name = "anyURI")
        protected List<String> page;

        /**
         * Gets the value of the page property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the page property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getPage().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getPage() {
            if (page == null) {
                page = new ArrayList<String>();
            }
            return this.page;
        }

    }

}
