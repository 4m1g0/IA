package es.udc.rs.app.client.dto;

import java.net.URI;
import java.util.Calendar;

import es.udc.rs.app.constants.ModelConstants.enumState;
import es.udc.rs.app.constants.ModelConstants.enumType;

public class CallDetailsDto {
	private Long callId;
	private Long clientId;
	private Calendar dateCall;
	private int duration;
	private String destPhone;
	private enumType type;
	private enumState state;
	
	public CallDetailsDto() {
		super();
	}

	public CallDetailsDto(Long callId, Long clientId, Calendar dateCall,
			int duration, String destPhone, enumType type, enumState state) {
		super();
		this.callId = callId;
		this.clientId = clientId;
		this.dateCall = dateCall;
		this.duration = duration;
		this.destPhone = destPhone;
		this.type = type;
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

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getDestPhone() {
		return destPhone;
	}

	public void setDestPhone(String destPhone) {
		this.destPhone = destPhone;
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

	@Override
	public String toString() {
		return "CallDetailsDto [callId=" + callId + ", clientId=" + clientId
				+ ", dateCall=" + dateCall + ", duration=" + duration
				+ ", destPhone=" + destPhone + ", type=" + type + ", state="
				+ state + "]";
	}
	
	

}
