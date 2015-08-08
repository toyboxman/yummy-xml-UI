//
// ���ļ����� JavaTM Architecture for XML Binding (JAXB) ����ʵ�� v2.2.8-b130911.1802 ���ɵ�
// ����� <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// �����±���Դģʽʱ, �Դ��ļ��������޸Ķ�����ʧ��
// ����ʱ��: 2014.10.22 ʱ�� 08:26:57 PM CST 
//


package king.flow.view;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>devicetype_enum�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * <p>
 * <pre>
 * &lt;simpleType name="devicetype_enum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="magnetCard"/>
 *     &lt;enumeration value="ICCard"/>
 *     &lt;enumeration value="sensorCard"/>
 *     &lt;enumeration value="printer"/>
 *     &lt;enumeration value="keyboard"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "devicetype_enum")
@XmlEnum
public enum DevicetypeEnum {

    @XmlEnumValue("magnetCard")
    MAGNET_CARD("magnetCard"),
    @XmlEnumValue("ICCard")
    IC_CARD("ICCard"),
    @XmlEnumValue("sensorCard")
    SENSOR_CARD("sensorCard"),
    @XmlEnumValue("printer")
    PRINTER("printer"),
    @XmlEnumValue("keyboard")
    KEYBOARD("keyboard");
    private final String value;

    DevicetypeEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static DevicetypeEnum fromValue(String v) {
        for (DevicetypeEnum c: DevicetypeEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
