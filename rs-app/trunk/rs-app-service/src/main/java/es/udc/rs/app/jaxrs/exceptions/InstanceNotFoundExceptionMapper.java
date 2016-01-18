package es.udc.rs.app.jaxrs.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import es.udc.rs.app.jaxrs.dto.InstanceNotFoundExceptionDtoJaxb;
import es.udc.ws.util.exceptions.InstanceNotFoundException;

@Provider
public class InstanceNotFoundExceptionMapper implements
		ExceptionMapper<InstanceNotFoundException> {

	@Override
	public Response toResponse(InstanceNotFoundException ex) {
		return Response
				.status(Response.Status.NOT_FOUND)
				.entity(new InstanceNotFoundExceptionDtoJaxb(ex.getInstanceId(), ex.getInstanceType()))
				.build();

	}

}