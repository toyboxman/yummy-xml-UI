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
 * <p>panel complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="panel">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="type" type="{}panel_enum"/>
 *         &lt;element name="active" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="background" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="attribute" type="{}basic_attribute" minOccurs="0"/>
 *         &lt;element name="component" type="{}component" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="decorator" type="{}decorator" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "panel", propOrder = {
    "id",
    "type",
    "active",
    "background",
    "attribute",
    "component",
    "decorator"
})
public class Panel {

    protected int id;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected PanelEnum type;
    @XmlElement(defaultValue = "false")
    protected boolean active;
    protected String background;
    protected BasicAttribute attribute;
    protected List<Component> component;
    protected List<Decorator> decorator;

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
     *     {@link PanelEnum }
     *     
     */
    public PanelEnum getType() {
        return type;
    }

    /**
     * ����type���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link PanelEnum }
     *     
     */
    public void setType(PanelEnum value) {
        this.type = value;
    }

    /**
     * ��ȡactive���Ե�ֵ��
     * 
     */
    public boolean isActive() {
        return active;
    }

    /**
     * ����active���Ե�ֵ��
     * 
     */
    public void setActive(boolean value) {
        this.active = value;
    }

    /**
     * ��ȡbackground���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBackground() {
        return background;
    }

    /**
     * ����background���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBackground(String value) {
        this.background = value;
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
     * Gets the value of the component property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the component property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getComponent().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Component }
     * 
     * 
     */
    public List<Component> getComponent() {
        if (component == null) {
            component = new ArrayList<Component>();
        }
        return this.component;
    }

    /**
     * Gets the value of the decorator property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the decorator property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDecorator().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Decorator }
     * 
     * 
     */
    public List<Decorator> getDecorator() {
        if (decorator == null) {
            decorator = new ArrayList<Decorator>();
        }
        return this.decorator;
    }

}
