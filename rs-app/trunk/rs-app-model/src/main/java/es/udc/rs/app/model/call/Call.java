package es.udc.rs.app.model.call;

import java.util.Calendar;

import es.udc.rs.app.model.util.ModelConstants;

public class Call {
	
	private Long callId;
	private Long clientId;
	private Calendar dateCall;
	private short duration;
	private short destPhone;
	private ModelConstants.enumType type;
	private ModelConstants.enumState state;
	private static long countCall = 0;
	
	public Call(Long clientId, Calendar dateCall, short duration, 
			ModelConstants.enumType type, short destPhone) {
		this.clientId = clientId;
		this.dateCall = dateCall;
		this.duration = duration;
		this.type = type;
		this.destPhone = destPhone;
		this.state = ModelConstants.enumState.PENDING;
		this.callId = countCall;
		
	}
	
	public synchronized void increment(){
		countCall++;	
	}


	public ModelConstants.enumType getType() {
		return type;
	}


	public void setType(ModelConstants.enumType type) {
		this.type = type;
	}


	public ModelConstants.enumState getState() {
		return state;
	}


	public void setState(ModelConstants.enumState state) {
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


	public short getDuration() {
		return duration;
	}


	public void setDuration(short duration) {
		this.duration = duration;
	}


	public short getDestPhone() {
		return destPhone;
	}


	public void setDestPhone(short destPhone) {
		this.destPhone = destPhone;
	}
	
	
	

}
