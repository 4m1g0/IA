package es.udc.rs.app.exceptions;

@SuppressWarnings("serial")
public class RemoveClientException extends Exception{
	
	private Long clientId;
	
	public RemoveClientException(Long clientId) {
		super();
		this.clientId = clientId;
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}
}
