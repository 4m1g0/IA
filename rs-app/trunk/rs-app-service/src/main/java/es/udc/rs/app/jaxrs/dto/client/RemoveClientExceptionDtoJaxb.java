package es.udc.rs.app.jaxrs.dto.client;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="RemoveClientException")
@XmlType(name="removeClientExceptionType")
public class RemoveClientExceptionDtoJaxb {

	@XmlElement(required = true)
	private Long clientId;
	
	public RemoveClientExceptionDtoJaxb(Long clientId) {
		this.clientId = clientId;
	}
	
	public RemoveClientExceptionDtoJaxb() {
	}

	public Long getClientId() {
		return clientId;
	}

	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}
}
