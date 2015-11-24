package es.udc.rs.app.jaxrs.dto;

import java.util.Calendar;

import javax.ws.rs.core.Link;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import es.udc.rs.app.constants.ModelConstants.enumState;
import es.udc.rs.app.constants.ModelConstants.enumType;

@XmlRootElement(name="call")
@XmlType(name="callType", propOrder = {"dateCall", 
		"duration", "destPhone","self"})

public class CallDtoJaxb {

    @XmlElement(required = true)
    private DateDtoJaxb dateCall;
    @XmlElement(required = true)
    private Integer duration;
    @XmlElement(required = true)
    private String destPhone;
    @XmlElement(name = "link")
    @XmlJavaTypeAdapter(Link.JaxbAdapter.class)
    private Link self;
    
    public CallDtoJaxb(){
    	
    }
    
	public CallDtoJaxb(Calendar dateCall,
			Integer duration, enumType type, Link self) {
		this.dateCall = new DateDtoJaxb(dateCall);
		this.duration = duration;
		this.destPhone = destPhone;
		this.self = self;
		
	}

	public DateDtoJaxb getDateCall() {
		return dateCall;
	}

	public void setDateCall(DateDtoJaxb dateCall) {
		this.dateCall = dateCall;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public String getDestPhone() {
		return destPhone;
	}

	public void setDestPhone(String destPhone) {
		this.destPhone = destPhone;
	}

	public Link getSelf() {
		return self;
	}

	public void setSelf(Link self) {
		this.self = self;
	}
	
}
