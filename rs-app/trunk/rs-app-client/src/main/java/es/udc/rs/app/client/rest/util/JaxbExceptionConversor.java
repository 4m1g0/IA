package es.udc.rs.app.client.rest.util;

import es.udc.rs.app.client.service.rest.dto.InstanceNotFoundExceptionDtoJaxb;
import es.udc.ws.util.exceptions.InstanceNotFoundException;



public class JaxbExceptionConversor {

	public static InstanceNotFoundException toInstanceNotFoundException(
			InstanceNotFoundExceptionDtoJaxb exDto) {
		return new InstanceNotFoundException(exDto.getInstanceId(),
				exDto.getInstanceType());
	}
}
