//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.8-b130911.1802 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2016.01.24 时间 05:18:13 PM CST 
//
package king.flow.data;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the king.flow.data package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the
 * Java representation for XML content. The Java representation of XML content
 * can consist of schema derived interfaces and classes representing the binding
 * of schema type definitions, element declarations and model groups. Factory
 * methods for each of these are provided in this class.
 *
 */
@XmlRegistry
public class ObjectFactory {

    final static QName _Systemrestart_QNAME = new QName("", "systemrestart");
    final static QName _Updatefile_QNAME = new QName("", "updatefile");
    final static QName _Terminalstate_QNAME = new QName("", "terminalstate");
    final static QName _Restart_QNAME = new QName("", "restart");
    final static QName _Keyboardstate_QNAME = new QName("", "keyboardstate");
    final static QName _Update_QNAME = new QName("", "update");
    final static QName _Errmsg_QNAME = new QName("", "errmsg");
    final static QName _Terminalid_QNAME = new QName("", "terminalid");
    final static QName _Okmsg_QNAME = new QName("", "okmsg");
    final static QName _Counter_QNAME = new QName("", "counter");
    final static QName _Version_QNAME = new QName("", "version");
    final static QName _Token_QNAME = new QName("", "token");
    final static QName _Uid_QNAME = new QName("", "uid");
    final static QName _Changekey_QNAME = new QName("", "changekey");
    final static QName _Startid_QNAME = new QName("", "startid");
    final static QName _Prtstate_QNAME = new QName("", "prtstate");
    final static QName _Prscode_QNAME = new QName("", "prscode");
    final static QName _Cargo_QNAME = new QName("", "cargo");
    final static QName _Redirection_QNAME = new QName("", "redirection");
    final static QName _Branchno_QNAME = new QName("", "branchno");
    final static QName _Retcode_QNAME = new QName("", "retcode");

    /**
     * Create a new ObjectFactory that can be used to create new instances of
     * schema derived classes for package: king.flow.data
     *
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link TLS }
     *
     */
    public TLS createTLS() {
        return new TLS();
    }

    /**
     * Create an instance of
     * {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "", name = "systemrestart")
    public JAXBElement<Integer> createSystemrestart(Integer value) {
        return new JAXBElement<Integer>(_Systemrestart_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of
     * {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "", name = "updatefile")
    public JAXBElement<String> createUpdatefile(String value) {
        return new JAXBElement<String>(_Updatefile_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of
     * {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "", name = "terminalstate")
    public JAXBElement<Integer> createTerminalstate(Integer value) {
        return new JAXBElement<Integer>(_Terminalstate_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of
     * {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "", name = "restart")
    public JAXBElement<Integer> createRestart(Integer value) {
        return new JAXBElement<Integer>(_Restart_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of
     * {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "", name = "keyboardstate")
    public JAXBElement<Integer> createKeyboardstate(Integer value) {
        return new JAXBElement<Integer>(_Keyboardstate_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of
     * {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "", name = "update")
    public JAXBElement<Integer> createUpdate(Integer value) {
        return new JAXBElement<Integer>(_Update_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of
     * {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "", name = "errmsg")
    public JAXBElement<String> createErrmsg(String value) {
        return new JAXBElement<String>(_Errmsg_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of
     * {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "", name = "terminalid")
    public JAXBElement<String> createTerminalid(String value) {
        return new JAXBElement<String>(_Terminalid_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of
     * {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "", name = "okmsg")
    public JAXBElement<String> createOkmsg(String value) {
        return new JAXBElement<String>(_Okmsg_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of
     * {@link JAXBElement }{@code <}{@link Long }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "", name = "counter")
    public JAXBElement<Long> createCounter(Long value) {
        return new JAXBElement<Long>(_Counter_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of
     * {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "", name = "version")
    public JAXBElement<String> createVersion(String value) {
        return new JAXBElement<String>(_Version_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of
     * {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "", name = "token")
    public JAXBElement<String> createToken(String value) {
        return new JAXBElement<String>(_Token_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of
     * {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "", name = "uid")
    public JAXBElement<String> createUid(String value) {
        return new JAXBElement<String>(_Uid_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of
     * {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "", name = "changekey")
    public JAXBElement<Integer> createChangekey(Integer value) {
        return new JAXBElement<Integer>(_Changekey_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of
     * {@link JAXBElement }{@code <}{@link Long }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "", name = "startid")
    public JAXBElement<Long> createStartid(Long value) {
        return new JAXBElement<Long>(_Startid_QNAME, Long.class, null, value);
    }

    /**
     * Create an instance of
     * {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "", name = "prtstate")
    public JAXBElement<Integer> createPrtstate(Integer value) {
        return new JAXBElement<Integer>(_Prtstate_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of
     * {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "", name = "prscode")
    public JAXBElement<String> createPrscode(String value) {
        return new JAXBElement<String>(_Prscode_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of
     * {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "", name = "cargo")
    public JAXBElement<String> createCargo(String value) {
        return new JAXBElement<String>(_Cargo_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of
     * {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "", name = "redirection")
    public JAXBElement<String> createRedirection(String value) {
        return new JAXBElement<String>(_Redirection_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of
     * {@link JAXBElement }{@code <}{@link String }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "", name = "branchno")
    public JAXBElement<String> createBranchno(String value) {
        return new JAXBElement<String>(_Branchno_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of
     * {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "", name = "retcode")
    public JAXBElement<Integer> createRetcode(Integer value) {
        return new JAXBElement<Integer>(_Retcode_QNAME, Integer.class, null, value);
    }

}
