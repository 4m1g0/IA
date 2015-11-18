package es.udc.rs.app.jaxrs.dto.client;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "CallStateException")
@XmlType(name="callStateExceptionType", propOrder = {"clientId", "callId"})
public class CallStateExceptionDtoJaxb {
		@XmlElement(required = true)
		private Long clientId;
		@XmlElement(required = true)
		private Long callId;

		public CallStateExceptionDtoJaxb() {
		}
		
		public CallStateExceptionDtoJaxb(Long clientId, Long callId) {
			this.clientId = clientId;
			this.callId = callId;
		}

		public Long getClientId() {
			return clientId;
		}

		public void setClientId(Long clientId) {
			this.clientId = clientId;
		}

		public Long getCallId() {
			return callId;
		}

		public void setCallId(Long callId) {
			this.callId = callId;
		}
}
