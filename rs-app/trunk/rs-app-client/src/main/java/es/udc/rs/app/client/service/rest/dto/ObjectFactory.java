//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.7 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2016.01.18 a las 12:43:45 PM CET 
//


package es.udc.rs.app.client.service.rest.dto;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the es.udc.rs.app.client.service.rest.dto package. 
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

    private final static QName _RemoveClientException_QNAME = new QName("http://rs.udc.es/clients/xml", "RemoveClientException");
    private final static QName _InputValidationException_QNAME = new QName("http://rs.udc.es/clients/xml", "InputValidationException");
    private final static QName _Client_QNAME = new QName("http://rs.udc.es/clients/xml", "client");
    private final static QName _InstanceNotFoundException_QNAME = new QName("http://rs.udc.es/clients/xml", "InstanceNotFoundException");
    private final static QName _CallStateException_QNAME = new QName("http://rs.udc.es/clients/xml", "CallStateException");
    private final static QName _Calls_QNAME = new QName("http://rs.udc.es/clients/xml", "calls");
    private final static QName _Clients_QNAME = new QName("http://rs.udc.es/clients/xml", "clients");
    private final static QName _Call_QNAME = new QName("http://rs.udc.es/clients/xml", "call");
    private final static QName _CallDetails_QNAME = new QName("http://rs.udc.es/clients/xml", "call-details");
    private final static QName _ClientDetails_QNAME = new QName("http://rs.udc.es/clients/xml", "client-details");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: es.udc.rs.app.client.service.rest.dto
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CallDtoJaxb }
     * 
     */
    public CallDtoJaxb createCallDtoJaxb() {
        return new CallDtoJaxb();
    }

    /**
     * Create an instance of {@link CallDetailsDtoJaxb }
     * 
     */
    public CallDetailsDtoJaxb createCallDetailsDtoJaxb() {
        return new CallDetailsDtoJaxb();
    }

    /**
     * Create an instance of {@link ClientDetailsDtoJaxb }
     * 
     */
    public ClientDetailsDtoJaxb createClientDetailsDtoJaxb() {
        return new ClientDetailsDtoJaxb();
    }

    /**
     * Create an instance of {@link ClientDtoJaxbList }
     * 
     */
    public ClientDtoJaxbList createClientDtoJaxbList() {
        return new ClientDtoJaxbList();
    }

    /**
     * Create an instance of {@link CallStateExceptionDtoJaxb }
     * 
     */
    public CallStateExceptionDtoJaxb createCallStateExceptionDtoJaxb() {
        return new CallStateExceptionDtoJaxb();
    }

    /**
     * Create an instance of {@link CallListType }
     * 
     */
    public CallListType createCallListType() {
        return new CallListType();
    }

    /**
     * Create an instance of {@link InstanceNotFoundExceptionDtoJaxb }
     * 
     */
    public InstanceNotFoundExceptionDtoJaxb createInstanceNotFoundExceptionDtoJaxb() {
        return new InstanceNotFoundExceptionDtoJaxb();
    }

    /**
     * Create an instance of {@link ClientDtoJaxb }
     * 
     */
    public ClientDtoJaxb createClientDtoJaxb() {
        return new ClientDtoJaxb();
    }

    /**
     * Create an instance of {@link InputValidationExceptionDtoJaxb }
     * 
     */
    public InputValidationExceptionDtoJaxb createInputValidationExceptionDtoJaxb() {
        return new InputValidationExceptionDtoJaxb();
    }

    /**
     * Create an instance of {@link RemoveClientExceptionDtoJaxb }
     * 
     */
    public RemoveClientExceptionDtoJaxb createRemoveClientExceptionDtoJaxb() {
        return new RemoveClientExceptionDtoJaxb();
    }

    /**
     * Create an instance of {@link DateDtoJaxb }
     * 
     */
    public DateDtoJaxb createDateDtoJaxb() {
        return new DateDtoJaxb();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RemoveClientExceptionDtoJaxb }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rs.udc.es/clients/xml", name = "RemoveClientException")
    public JAXBElement<RemoveClientExceptionDtoJaxb> createRemoveClientException(RemoveClientExceptionDtoJaxb value) {
        return new JAXBElement<RemoveClientExceptionDtoJaxb>(_RemoveClientException_QNAME, RemoveClientExceptionDtoJaxb.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InputValidationExceptionDtoJaxb }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rs.udc.es/clients/xml", name = "InputValidationException")
    public JAXBElement<InputValidationExceptionDtoJaxb> createInputValidationException(InputValidationExceptionDtoJaxb value) {
        return new JAXBElement<InputValidationExceptionDtoJaxb>(_InputValidationException_QNAME, InputValidationExceptionDtoJaxb.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ClientDtoJaxb }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rs.udc.es/clients/xml", name = "client")
    public JAXBElement<ClientDtoJaxb> createClient(ClientDtoJaxb value) {
        return new JAXBElement<ClientDtoJaxb>(_Client_QNAME, ClientDtoJaxb.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InstanceNotFoundExceptionDtoJaxb }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rs.udc.es/clients/xml", name = "InstanceNotFoundException")
    public JAXBElement<InstanceNotFoundExceptionDtoJaxb> createInstanceNotFoundException(InstanceNotFoundExceptionDtoJaxb value) {
        return new JAXBElement<InstanceNotFoundExceptionDtoJaxb>(_InstanceNotFoundException_QNAME, InstanceNotFoundExceptionDtoJaxb.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CallStateExceptionDtoJaxb }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rs.udc.es/clients/xml", name = "CallStateException")
    public JAXBElement<CallStateExceptionDtoJaxb> createCallStateException(CallStateExceptionDtoJaxb value) {
        return new JAXBElement<CallStateExceptionDtoJaxb>(_CallStateException_QNAME, CallStateExceptionDtoJaxb.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CallListType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rs.udc.es/clients/xml", name = "calls")
    public JAXBElement<CallListType> createCalls(CallListType value) {
        return new JAXBElement<CallListType>(_Calls_QNAME, CallListType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ClientDtoJaxbList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rs.udc.es/clients/xml", name = "clients")
    public JAXBElement<ClientDtoJaxbList> createClients(ClientDtoJaxbList value) {
        return new JAXBElement<ClientDtoJaxbList>(_Clients_QNAME, ClientDtoJaxbList.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CallDtoJaxb }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rs.udc.es/clients/xml", name = "call")
    public JAXBElement<CallDtoJaxb> createCall(CallDtoJaxb value) {
        return new JAXBElement<CallDtoJaxb>(_Call_QNAME, CallDtoJaxb.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CallDetailsDtoJaxb }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rs.udc.es/clients/xml", name = "call-details")
    public JAXBElement<CallDetailsDtoJaxb> createCallDetails(CallDetailsDtoJaxb value) {
        return new JAXBElement<CallDetailsDtoJaxb>(_CallDetails_QNAME, CallDetailsDtoJaxb.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ClientDetailsDtoJaxb }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://rs.udc.es/clients/xml", name = "client-details")
    public JAXBElement<ClientDetailsDtoJaxb> createClientDetails(ClientDetailsDtoJaxb value) {
        return new JAXBElement<ClientDetailsDtoJaxb>(_ClientDetails_QNAME, ClientDetailsDtoJaxb.class, null, value);
    }

}
