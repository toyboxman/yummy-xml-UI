//
// ���ļ����� JavaTM Architecture for XML Binding (JAXB) ����ʵ�� v2.2.8-b130911.1802 ���ɵ�
// ����� <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// �����±���Դģʽʱ, �Դ��ļ��������޸Ķ�����ʧ��
// ����ʱ��: 2015.08.15 ʱ�� 12:04:31 PM CST 
//


package king.flow.view;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>decorator_enum�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * <p>
 * <pre>
 * &lt;simpleType name="decorator_enum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="ScrollPanel"/>
 *     &lt;enumeration value="TabPanel"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "decorator_enum")
@XmlEnum
public enum DecoratorEnum {

    @XmlEnumValue("ScrollPanel")
    SCROLL_PANEL("ScrollPanel"),
    @XmlEnumValue("TabPanel")
    TAB_PANEL("TabPanel");
    private final String value;

    DecoratorEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static DecoratorEnum fromValue(String v) {
        for (DecoratorEnum c: DecoratorEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
