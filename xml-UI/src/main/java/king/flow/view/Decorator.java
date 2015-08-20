//
// ���ļ����� JavaTM Architecture for XML Binding (JAXB) ����ʵ�� v2.2.8-b130911.1802 ���ɵ�
// ����� <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// �����±���Դģʽʱ, �Դ��ļ��������޸Ķ�����ʧ��
// ����ʱ��: 2015.08.20 ʱ�� 10:46:47 PM CST 
//


package king.flow.view;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>decorator complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="decorator">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="type" type="{}decorator_enum"/>
 *         &lt;element name="attribute" type="{}basic_attribute"/>
 *         &lt;element name="component" type="{}component"/>
 *         &lt;element name="action" type="{}action" minOccurs="0"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "decorator", propOrder = {

})
public class Decorator {

    protected int id;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected DecoratorEnum type;
    @XmlElement(required = true)
    protected BasicAttribute attribute;
    @XmlElement(required = true)
    protected Component component;
    protected Action action;

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
     *     {@link DecoratorEnum }
     *     
     */
    public DecoratorEnum getType() {
        return type;
    }

    /**
     * ����type���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link DecoratorEnum }
     *     
     */
    public void setType(DecoratorEnum value) {
        this.type = value;
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
     * ��ȡcomponent���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Component }
     *     
     */
    public Component getComponent() {
        return component;
    }

    /**
     * ����component���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Component }
     *     
     */
    public void setComponent(Component value) {
        this.component = value;
    }

    /**
     * ��ȡaction���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link Action }
     *     
     */
    public Action getAction() {
        return action;
    }

    /**
     * ����action���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link Action }
     *     
     */
    public void setAction(Action value) {
        this.action = value;
    }

}
