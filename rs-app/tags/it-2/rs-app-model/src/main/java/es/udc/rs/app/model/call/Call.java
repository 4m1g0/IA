package es.udc.rs.app.model.call;

import java.util.Calendar;

import es.udc.rs.app.constants.ModelConstants.enumState;
import es.udc.rs.app.constants.ModelConstants.enumType;

public class Call {
	
	private Long callId;
	private Long clientId;
	private Calendar dateCall;
	private Integer duration;
	private String destPhone;
	private enumType type;
	private enumState state;
	
	public Call(Long clientId, Calendar dateCall, Integer duration, 
			enumType type, String destPhone) {
		this.clientId = clientId;
		this.dateCall = dateCall;
		this.duration = duration;
		this.type = type;
		this.destPhone = destPhone;
		this.state = enumState.PENDING;
		
	}

	public enumType getType() {
		return type;
	}

	public void setType(enumType type) {
		this.type = type;
	}

	public enumState getState() {
		return state;
	}

	public void setState(enumState state) {
		this.state = state;
	}

	public Long getCallId() {
		return callId;
	}

	public void setCallId(Long callId) {
		this.callId = callId;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}

	public Calendar getDateCall() {
		return dateCall;
	}

	public void setDateCall(Calendar dateCall) {
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
}
