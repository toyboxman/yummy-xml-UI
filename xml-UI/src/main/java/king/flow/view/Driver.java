//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.8-b130911.1802 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2015.08.15 时间 12:04:31 PM CST 
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
 * <p>driver complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="driver">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="device" maxOccurs="5" minOccurs="5">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;all>
 *                   &lt;element name="type" type="{}devicetype_enum"/>
 *                   &lt;element name="port" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="dll" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
@XmlType(name = "driver", propOrder = {
    "device"
})
public class Driver {

    @XmlElement(required = true)
    protected List<Driver.Device> device;

    /**
     * Gets the value of the device property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the device property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDevice().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Driver.Device }
     * 
     * 
     */
    public List<Driver.Device> getDevice() {
        if (device == null) {
            device = new ArrayList<Driver.Device>();
        }
        return this.device;
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
     *         &lt;element name="type" type="{}devicetype_enum"/>
     *         &lt;element name="port" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="dll" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    public static class Device {

        @XmlElement(required = true)
        @XmlSchemaType(name = "string")
        protected DevicetypeEnum type;
        @XmlElement(required = true)
        protected String port;
        @XmlElement(required = true)
        protected String dll;

        /**
         * 获取type属性的值。
         * 
         * @return
         *     possible object is
         *     {@link DevicetypeEnum }
         *     
         */
        public DevicetypeEnum getType() {
            return type;
        }

        /**
         * 设置type属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link DevicetypeEnum }
         *     
         */
        public void setType(DevicetypeEnum value) {
            this.type = value;
        }

        /**
         * 获取port属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPort() {
            return port;
        }

        /**
         * 设置port属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPort(String value) {
            this.port = value;
        }

        /**
         * 获取dll属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDll() {
            return dll;
        }

        /**
         * 设置dll属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDll(String value) {
            this.dll = value;
        }

    }

}
