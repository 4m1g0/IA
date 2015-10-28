package es.udc.rs.app.exceptions;

@SuppressWarnings("serial")
public class CallStateException extends Exception{
	
	private Long clientId;
	private Long callId;
	
	
	public CallStateException(Long clientId, Long callId) {
		super();
		this.clientId = clientId;
		this.callId = callId;
	}


	public Long getClientId() {
		return clientId;
	}


	public Long getCallId() {
		return callId;
	}


	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}


	public void setCallId(Long callId) {
		this.callId = callId;
	}
	
	
	
	
	

}
