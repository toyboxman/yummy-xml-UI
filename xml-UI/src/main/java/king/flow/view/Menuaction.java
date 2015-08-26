//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.8-b130911.1802 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2015.08.26 时间 09:58:15 PM CST 
//


package king.flow.view;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>menuaction complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="menuaction">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="customizedAction" type="{}defined_action" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="jumpPanelAction" type="{}jump_action" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "menuaction", propOrder = {
    "customizedAction",
    "jumpPanelAction"
})
public class Menuaction {

    protected List<DefinedAction> customizedAction;
    protected JumpAction jumpPanelAction;

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
     * 获取jumpPanelAction属性的值。
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
     * 设置jumpPanelAction属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JumpAction }
     *     
     */
    public void setJumpPanelAction(JumpAction value) {
        this.jumpPanelAction = value;
    }

}
