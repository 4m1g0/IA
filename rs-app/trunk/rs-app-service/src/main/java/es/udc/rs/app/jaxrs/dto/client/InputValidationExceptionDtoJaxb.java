package es.udc.rs.app.jaxrs.dto.client;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="InputValidationException")
@XmlType(name="inputValidationExceptionType")
public class InputValidationExceptionDtoJaxb {

    @XmlElement(required = true)
	private String message;
    
    public InputValidationExceptionDtoJaxb() {    	
    }

    public InputValidationExceptionDtoJaxb(String message) {
    	this.message = message;
    }

    public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
    
    
}