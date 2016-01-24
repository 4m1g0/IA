package es.udc.rs.app.client.rest.util;

import es.udc.rs.app.client.service.rest.dto.CallStateExceptionDtoJaxb;
import es.udc.rs.app.client.service.rest.dto.InputValidationExceptionDtoJaxb;
import es.udc.rs.app.client.service.rest.dto.InstanceNotFoundExceptionDtoJaxb;
import es.udc.rs.app.client.service.rest.dto.RemoveClientExceptionDtoJaxb;
import es.udc.rs.app.exceptions.CallStateException;
import es.udc.rs.app.exceptions.RemoveClientException;
import es.udc.ws.util.exceptions.InputValidationException;
import es.udc.ws.util.exceptions.InstanceNotFoundException;



public class JaxbExceptionConversor {

	
	//public final static String CONVERSION_PATTERN = "EEE, d MMM yyyy HH:mm:ss Z";

	public static InputValidationException toInputValidationException(
			InputValidationExceptionDtoJaxb exDto) {
		return new InputValidationException(exDto.getMessage());
	}

	public static InstanceNotFoundException toInstanceNotFoundException(
			InstanceNotFoundExceptionDtoJaxb exDto) {
		return new InstanceNotFoundException(exDto.getInstanceId(),
				exDto.getInstanceType());
	}

	public static RemoveClientException toRemoveClientException(
			RemoveClientExceptionDtoJaxb exDto) {

		return new RemoveClientException(Long.valueOf(exDto
				.getClientId()));
	}

	public static CallStateException toCallStatException(
			CallStateExceptionDtoJaxb exDto) {

		return new CallStateException(Long.valueOf(exDto
				.getClientId()), (exDto.getCallId()));
	}
}
