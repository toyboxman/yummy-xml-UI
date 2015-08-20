//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.08.20 at 01:14:15 PM CST 
//


package king.flow.view;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for device_enum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
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
    KEYBOARD("keyboard");
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
