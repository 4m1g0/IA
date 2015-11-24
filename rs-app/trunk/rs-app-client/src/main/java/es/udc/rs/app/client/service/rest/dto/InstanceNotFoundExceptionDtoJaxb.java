//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.7 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2015.11.24 a las 11:47:19 PM CET 
//


package es.udc.rs.app.client.service.rest.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para instanceNotFoundExceptionType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="instanceNotFoundExceptionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="instanceId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="instanceType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "instanceNotFoundExceptionType", propOrder = {
    "instanceId",
    "instanceType"
})
public class InstanceNotFoundExceptionDtoJaxb {

    @XmlElement(required = true)
    protected String instanceId;
    @XmlElement(required = true)
    protected String instanceType;

    /**
     * Obtiene el valor de la propiedad instanceId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInstanceId() {
        return instanceId;
    }

    /**
     * Define el valor de la propiedad instanceId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInstanceId(String value) {
        this.instanceId = value;
    }

    /**
     * Obtiene el valor de la propiedad instanceType.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInstanceType() {
        return instanceType;
    }

    /**
     * Define el valor de la propiedad instanceType.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInstanceType(String value) {
        this.instanceType = value;
    }

}
