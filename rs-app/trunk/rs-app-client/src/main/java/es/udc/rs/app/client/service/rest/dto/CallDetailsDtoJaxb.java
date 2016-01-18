//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.7 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2016.01.18 a las 12:43:45 PM CET 
//


package es.udc.rs.app.client.service.rest.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para callDetailsType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="callDetailsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="call-id" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="clientId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="dateCall" type="{http://rs.udc.es/clients/xml}dateType"/>
 *         &lt;element name="duration" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="destPhone" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="state" type="{}enumState"/>
 *         &lt;element name="type" type="{}enumType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "callDetailsType", propOrder = {
    "callId",
    "clientId",
    "dateCall",
    "duration",
    "destPhone",
    "state",
    "type"
})
public class CallDetailsDtoJaxb {

    @XmlElement(name = "call-id")
    protected long callId;
    protected long clientId;
    @XmlElement(required = true)
    protected DateDtoJaxb dateCall;
    protected int duration;
    @XmlElement(required = true)
    protected String destPhone;
    @XmlElement(required = true)
    protected EnumState state;
    @XmlElement(required = true)
    protected EnumType type;

    /**
     * Obtiene el valor de la propiedad callId.
     * 
     */
    public long getCallId() {
        return callId;
    }

    /**
     * Define el valor de la propiedad callId.
     * 
     */
    public void setCallId(long value) {
        this.callId = value;
    }

    /**
     * Obtiene el valor de la propiedad clientId.
     * 
     */
    public long getClientId() {
        return clientId;
    }

    /**
     * Define el valor de la propiedad clientId.
     * 
     */
    public void setClientId(long value) {
        this.clientId = value;
    }

    /**
     * Obtiene el valor de la propiedad dateCall.
     * 
     * @return
     *     possible object is
     *     {@link DateDtoJaxb }
     *     
     */
    public DateDtoJaxb getDateCall() {
        return dateCall;
    }

    /**
     * Define el valor de la propiedad dateCall.
     * 
     * @param value
     *     allowed object is
     *     {@link DateDtoJaxb }
     *     
     */
    public void setDateCall(DateDtoJaxb value) {
        this.dateCall = value;
    }

    /**
     * Obtiene el valor de la propiedad duration.
     * 
     */
    public int getDuration() {
        return duration;
    }

    /**
     * Define el valor de la propiedad duration.
     * 
     */
    public void setDuration(int value) {
        this.duration = value;
    }

    /**
     * Obtiene el valor de la propiedad destPhone.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDestPhone() {
        return destPhone;
    }

    /**
     * Define el valor de la propiedad destPhone.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDestPhone(String value) {
        this.destPhone = value;
    }

    /**
     * Obtiene el valor de la propiedad state.
     * 
     * @return
     *     possible object is
     *     {@link EnumState }
     *     
     */
    public EnumState getState() {
        return state;
    }

    /**
     * Define el valor de la propiedad state.
     * 
     * @param value
     *     allowed object is
     *     {@link EnumState }
     *     
     */
    public void setState(EnumState value) {
        this.state = value;
    }

    /**
     * Obtiene el valor de la propiedad type.
     * 
     * @return
     *     possible object is
     *     {@link EnumType }
     *     
     */
    public EnumType getType() {
        return type;
    }

    /**
     * Define el valor de la propiedad type.
     * 
     * @param value
     *     allowed object is
     *     {@link EnumType }
     *     
     */
    public void setType(EnumType value) {
        this.type = value;
    }

}
