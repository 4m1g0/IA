//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.7 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2015.11.25 a las 04:42:46 PM CET 
//


package es.udc.rs.app.client.service.rest.dto;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para enumType.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <p>
 * <pre>
 * &lt;simpleType name="enumType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="LOCAL"/>
 *     &lt;enumeration value="NATIONAL"/>
 *     &lt;enumeration value="INTERNATIONAL"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "enumType", namespace = "")
@XmlEnum
public enum EnumType {

    LOCAL,
    NATIONAL,
    INTERNATIONAL;

    public String value() {
        return name();
    }

    public static EnumType fromValue(String v) {
        return valueOf(v);
    }

}
