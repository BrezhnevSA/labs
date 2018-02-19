
package soaps;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the soaps package. 
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

    private final static QName _SearchPersons_QNAME = new QName("http://soapS/", "searchPersons");
    private final static QName _SearchPersonsResponse_QNAME = new QName("http://soapS/", "searchPersonsResponse");
    private final static QName _GetPersons_QNAME = new QName("http://soapS/", "getPersons");
    private final static QName _GetPersonsResponse_QNAME = new QName("http://soapS/", "getPersonsResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: soaps
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SearchPersonsResponse }
     * 
     */
    public SearchPersonsResponse createSearchPersonsResponse() {
        return new SearchPersonsResponse();
    }

    /**
     * Create an instance of {@link SearchPersons }
     * 
     */
    public SearchPersons createSearchPersons() {
        return new SearchPersons();
    }

    /**
     * Create an instance of {@link GetPersons }
     * 
     */
    public GetPersons createGetPersons() {
        return new GetPersons();
    }

    /**
     * Create an instance of {@link GetPersonsResponse }
     * 
     */
    public GetPersonsResponse createGetPersonsResponse() {
        return new GetPersonsResponse();
    }

    /**
     * Create an instance of {@link Person }
     * 
     */
    public Person createPerson() {
        return new Person();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchPersons }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soapS/", name = "searchPersons")
    public JAXBElement<SearchPersons> createSearchPersons(SearchPersons value) {
        return new JAXBElement<SearchPersons>(_SearchPersons_QNAME, SearchPersons.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchPersonsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soapS/", name = "searchPersonsResponse")
    public JAXBElement<SearchPersonsResponse> createSearchPersonsResponse(SearchPersonsResponse value) {
        return new JAXBElement<SearchPersonsResponse>(_SearchPersonsResponse_QNAME, SearchPersonsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPersons }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soapS/", name = "getPersons")
    public JAXBElement<GetPersons> createGetPersons(GetPersons value) {
        return new JAXBElement<GetPersons>(_GetPersons_QNAME, GetPersons.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPersonsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soapS/", name = "getPersonsResponse")
    public JAXBElement<GetPersonsResponse> createGetPersonsResponse(GetPersonsResponse value) {
        return new JAXBElement<GetPersonsResponse>(_GetPersonsResponse_QNAME, GetPersonsResponse.class, null, value);
    }

}
