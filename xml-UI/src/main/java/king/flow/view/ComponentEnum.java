//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.8-b130911.1802 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2015.08.26 时间 09:58:15 PM CST 
//


package king.flow.view;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>component_enum的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * <p>
 * <pre>
 * &lt;simpleType name="component_enum">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Button"/>
 *     &lt;enumeration value="Table"/>
 *     &lt;enumeration value="AdvancedTable"/>
 *     &lt;enumeration value="ComboBox"/>
 *     &lt;enumeration value="Label"/>
 *     &lt;enumeration value="TextField"/>
 *     &lt;enumeration value="PasswordField"/>
 *     &lt;enumeration value="TextArea"/>
 *     &lt;enumeration value="EditorPane"/>
 *     &lt;enumeration value="Date"/>
 *     &lt;enumeration value="WebBroswer"/>
 *     &lt;enumeration value="NativeBroswer"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "component_enum")
@XmlEnum
public enum ComponentEnum {

    @XmlEnumValue("Button")
    BUTTON("Button"),
    @XmlEnumValue("Table")
    TABLE("Table"),
    @XmlEnumValue("AdvancedTable")
    ADVANCED_TABLE("AdvancedTable"),
    @XmlEnumValue("ComboBox")
    COMBO_BOX("ComboBox"),
    @XmlEnumValue("Label")
    LABEL("Label"),
    @XmlEnumValue("TextField")
    TEXT_FIELD("TextField"),
    @XmlEnumValue("PasswordField")
    PASSWORD_FIELD("PasswordField"),
    @XmlEnumValue("TextArea")
    TEXT_AREA("TextArea"),
    @XmlEnumValue("EditorPane")
    EDITOR_PANE("EditorPane"),
    @XmlEnumValue("Date")
    DATE("Date"),
    @XmlEnumValue("WebBroswer")
    WEB_BROSWER("WebBroswer"),
    @XmlEnumValue("NativeBroswer")
    NATIVE_BROSWER("NativeBroswer");
    private final String value;

    ComponentEnum(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ComponentEnum fromValue(String v) {
        for (ComponentEnum c: ComponentEnum.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
