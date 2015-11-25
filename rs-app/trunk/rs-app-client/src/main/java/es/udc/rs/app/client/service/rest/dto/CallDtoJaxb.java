//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.7 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2015.11.25 a las 11:56:52 AM CET 
//


package es.udc.rs.app.client.service.rest.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para callType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="callType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="dateCall" type="{http://rs.udc.es/clients/xml}dateType"/>
 *         &lt;element name="duration" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="destPhone" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="link" type="{}jaxbLink" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "callType", propOrder = {
    "dateCall",
    "duration",
    "destPhone",
    "link"
})
public class CallDtoJaxb {

    @XmlElement(required = true)
    protected DateDtoJaxb dateCall;
    protected int duration;
    @XmlElement(required = true)
    protected String destPhone;
    protected JaxbLink link;

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
     * Obtiene el valor de la propiedad link.
     * 
     * @return
     *     possible object is
     *     {@link JaxbLink }
     *     
     */
    public JaxbLink getLink() {
        return link;
    }

    /**
     * Define el valor de la propiedad link.
     * 
     * @param value
     *     allowed object is
     *     {@link JaxbLink }
     *     
     */
    public void setLink(JaxbLink value) {
        this.link = value;
    }

}
