//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.7 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2015.11.24 a las 11:47:19 PM CET 
//


package es.udc.rs.app.client.service.rest.dto;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para enumState.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <p>
 * <pre>
 * &lt;simpleType name="enumState">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="BILLED"/>
 *     &lt;enumeration value="PENDING"/>
 *     &lt;enumeration value="PAID"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "enumState", namespace = "")
@XmlEnum
public enum EnumState {

    BILLED,
    PENDING,
    PAID;

    public String value() {
        return name();
    }

    public static EnumState fromValue(String v) {
        return valueOf(v);
    }

}
