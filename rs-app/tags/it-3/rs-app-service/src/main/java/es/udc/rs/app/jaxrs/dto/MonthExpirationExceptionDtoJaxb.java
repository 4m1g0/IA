package es.udc.rs.app.jaxrs.dto;

import java.util.Calendar;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "MonthExpirationException")
@XmlType(name="monthExpirationExceptionType")
public class MonthExpirationExceptionDtoJaxb {
	@XmlElement(required = true)
	private Calendar date;
	
	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public MonthExpirationExceptionDtoJaxb() {
		
	}

	public MonthExpirationExceptionDtoJaxb(Calendar date) {
		super();
		this.date = date;
	}

}
