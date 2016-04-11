//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.8-b130911.1802 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2016.04.11 时间 10:19:20 PM CST 
//


package king.flow.view;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>device_enum的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * <p>
 * <pre>
 * &lt;simpleType name="device_enum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="magnetCard"/>
 *     &lt;enumeration value="ICCard"/>
 *     &lt;enumeration value="sensorCard"/>
 *     &lt;enumeration value="twoInOneCard"/>
 *     &lt;enumeration value="printer"/>
 *     &lt;enumeration value="keyboard"/>
 *     &lt;enumeration value="gzCard"/>
 *     &lt;enumeration value="pkg8583"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "device_enum")
@XmlEnum
public enum DeviceEnum {

    @XmlEnumValue("magnetCard")
    MAGNET_CARD("magnetCard"),
    @XmlEnumValue("ICCard")
    IC_CARD("ICCard"),
    @XmlEnumValue("sensorCard")
    SENSOR_CARD("sensorCard"),
    @XmlEnumValue("twoInOneCard")
    TWO_IN_ONE_CARD("twoInOneCard"),
    @XmlEnumValue("printer")
    PRINTER("printer"),
    @XmlEnumValue("keyboard")
    KEYBOARD("keyboard"),
    @XmlEnumValue("gzCard")
    GZ_CARD("gzCard"),
    @XmlEnumValue("pkg8583")
    PKG_8583("pkg8583");
    private final String value;

    DeviceEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static DeviceEnum fromValue(String v) {
        for (DeviceEnum c: DeviceEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
