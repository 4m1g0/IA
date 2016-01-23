package es.udc.rs.app.jaxrs.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="callDetails")
@XmlType(name="callDetailsType", propOrder = {"id", "clientId", "dateCall", 
		"duration", "destPhone", "state", "type"})

public class CallDetailsDtoJaxb {
	
	@XmlElement(name = "call-id", required = true)
    private Long id;
    @XmlElement(required = true)
    private Long clientId;
    @XmlElement(required = true)
    private String dateCall;
    @XmlElement(required = true)
    private Integer duration;
    @XmlElement(required = true)
    private String destPhone;
    @XmlElement(required = true)
    private String state;
    @XmlElement(required = true)
    private String type;
    
    public CallDetailsDtoJaxb(){
    	
    }
	public CallDetailsDtoJaxb(Long id, Long clientId, String dateCall,
			Integer duration, String destPhone, String state, 
			String type) {
		this.id = id;
		this.clientId = clientId;
		this.dateCall = dateCall;
		this.duration = duration;
		this.destPhone = destPhone;
		this.state = state;
		this.type = type;
		
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

	public String getDateCall() {
		return dateCall;
	}

	public void setDateCall(String dateCall) {
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
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}


	
	
}
