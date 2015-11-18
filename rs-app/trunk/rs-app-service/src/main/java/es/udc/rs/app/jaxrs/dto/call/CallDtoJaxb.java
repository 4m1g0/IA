package es.udc.rs.app.jaxrs.dto.call;

import java.util.Calendar;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import es.udc.rs.app.model.util.ModelConstants;

@XmlRootElement(name="call")
@XmlType(name="callType", propOrder = {"id", "clientId", "dateCall", 
		"duration", "destPhone", "type", "clientUrl"})

public class CallDtoJaxb {
	
	@XmlElement(name = "callId", required = true)
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
    private ModelConstants.enumState state;
    @XmlElement(required = true)
    private ModelConstants.enumType type;
    
    public CallDtoJaxb(){
    	
    }
    
	public CallDtoJaxb(Long id, Long clientId, Calendar dateCall,
			Integer duration, String destPhone, ModelConstants.enumState state, ModelConstants.enumType type) {
		this.id = id;
		this.clientId = clientId;
		this.dateCall = new DateDtoJaxb(dateCall);
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
	
	public ModelConstants.enumState getState() {
		return state;
	}

	public void setState(ModelConstants.enumState state) {
		this.state = state;
	}

	public ModelConstants.enumType getType() {
		return type;
	}

	public void setType(ModelConstants.enumType type) {
		this.type = type;
	}

	
	
	
    
    
	
}
