//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.8-b130911.1802 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2015.08.15 时间 12:04:31 PM CST 
//


package king.flow.view;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>decorator_enum的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
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
