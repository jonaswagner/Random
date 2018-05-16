//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2018.05.03 um 08:38:24 AM CEST 
//


package contractfirstgenerated;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the contractfirstgenerated package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ResultTO_QNAME = new QName("mein.ns", "resultTO");
    private final static QName _InputTO_QNAME = new QName("mein.ns", "inputTO");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: contractfirstgenerated
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link InputTO }
     * 
     */
    public InputTO createInputTO() {
        return new InputTO();
    }

    /**
     * Create an instance of {@link ResultTO }
     * 
     */
    public ResultTO createResultTO() {
        return new ResultTO();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResultTO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "mein.ns", name = "resultTO")
    public JAXBElement<ResultTO> createResultTO(ResultTO value) {
        return new JAXBElement<ResultTO>(_ResultTO_QNAME, ResultTO.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InputTO }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "mein.ns", name = "inputTO")
    public JAXBElement<InputTO> createInputTO(InputTO value) {
        return new JAXBElement<InputTO>(_InputTO_QNAME, InputTO.class, null, value);
    }

}
