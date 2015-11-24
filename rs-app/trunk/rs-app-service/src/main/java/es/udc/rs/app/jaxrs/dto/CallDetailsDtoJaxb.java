package es.udc.rs.app.jaxrs.dto;

import java.util.Calendar;
import java.util.List;

import javax.ws.rs.core.Link;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import es.udc.rs.app.constants.ModelConstants.enumState;
import es.udc.rs.app.constants.ModelConstants.enumType;

@XmlRootElement(name="call-details")
@XmlType(name="callDetailsType", propOrder = {"id", "clientId", "dateCall", 
		"duration", "destPhone", "state", "type", "links"})

public class CallDetailsDtoJaxb {
	
	@XmlElement(name = "call-id", required = true)
    private Long id;
    @XmlElement(required = true)
    private Long clientId;
    @XmlElement(required = true)
    private DateDtoJaxb dateCall;
    @XmlElement(required = true)
    private Integer duration;
    @XmlElement(required = true)
    private String destPhone;
    @XmlElement(required = true)
    private enumState state;
    @XmlElement(required = true)
    private enumType type;
    @XmlElement(name = "link")
    @XmlJavaTypeAdapter(Link.JaxbAdapter.class)
    private List<Link> links;
    
    public CallDetailsDtoJaxb(){
    	
    }
    
	public CallDetailsDtoJaxb(Long id, Long clientId, Calendar dateCall,
			Integer duration, String destPhone, enumState state, 
			enumType type, List<Link> links) {
		this.id = id;
		this.clientId = clientId;
		this.dateCall = new DateDtoJaxb(dateCall);
		this.duration = duration;
		this.destPhone = destPhone;
		this.state = state;
		this.type = type;
		this.links = links;
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
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
	
	public enumState getState() {
		return state;
	}

	public void setState(enumState state) {
		this.state = state;
	}

	public enumType getType() {
		return type;
	}

	public void setType(enumType type) {
		this.type = type;
	}

	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}

	
	
}
