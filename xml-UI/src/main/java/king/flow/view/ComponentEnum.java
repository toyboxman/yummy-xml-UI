//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.01.29 at 09:53:28 PM CST 
//


package king.flow.view;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for component_enum.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
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
 *     &lt;enumeration value="VideoPlayer"/>
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
    NATIVE_BROSWER("NativeBroswer"),
    @XmlEnumValue("VideoPlayer")
    VIDEO_PLAYER("VideoPlayer");
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
