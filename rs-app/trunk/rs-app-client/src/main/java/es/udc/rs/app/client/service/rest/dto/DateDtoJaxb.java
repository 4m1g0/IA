//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.2.7 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2015.11.25 a las 01:34:43 PM CET 
//


package es.udc.rs.app.client.service.rest.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dateType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="dateType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *       &lt;/sequence>
 *       &lt;attribute name="day" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="month" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="year" use="required" type="{http://www.w3.org/2001/XMLSchema}int" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dateType")
public class DateDtoJaxb {

    @XmlAttribute(name = "day", required = true)
    protected int day;
    @XmlAttribute(name = "month", required = true)
    protected int month;
    @XmlAttribute(name = "year", required = true)
    protected int year;

    /**
     * Obtiene el valor de la propiedad day.
     * 
     */
    public int getDay() {
        return day;
    }

    /**
     * Define el valor de la propiedad day.
     * 
     */
    public void setDay(int value) {
        this.day = value;
    }

    /**
     * Obtiene el valor de la propiedad month.
     * 
     */
    public int getMonth() {
        return month;
    }

    /**
     * Define el valor de la propiedad month.
     * 
     */
    public void setMonth(int value) {
        this.month = value;
    }

    /**
     * Obtiene el valor de la propiedad year.
     * 
     */
    public int getYear() {
        return year;
    }

    /**
     * Define el valor de la propiedad year.
     * 
     */
    public void setYear(int value) {
        this.year = value;
    }

}
