package es.udc.rs.app.model.call;

import java.util.Calendar;

import es.udc.rs.app.constants.EnumState;
import es.udc.rs.app.constants.EnumType;

public class Call {
	
	private Long callId;
	private Long clientId;
	private Calendar dateCall;
	private Integer duration;
	private String destPhone;
	private EnumType type;
	private EnumState state;
	
	public Call(Long clientId, Calendar dateCall, Integer duration, 
			EnumType type, String destPhone) {
		this.clientId = clientId;
		this.dateCall = dateCall;
		this.duration = duration;
		this.type = type;
		this.destPhone = destPhone;
		this.state = EnumState.PENDING;
		
	}

	public EnumType getType() {
		return type;
	}

	public void setType(EnumType type) {
		this.type = type;
	}

	public EnumState getState() {
		return state;
	}

	public void setState(EnumState state) {
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
