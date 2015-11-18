package es.udc.rs.app.jaxrs.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import es.udc.rs.app.exceptions.RemoveClientException;
import es.udc.rs.app.jaxrs.dto.client.RemoveClientExceptionDtoJaxb;

public class RemoveClientExceptionMapper implements ExceptionMapper<RemoveClientException>{

	@Override
	public Response toResponse(RemoveClientException ex) {
		return Response.status(Response.Status.FORBIDDEN)
				.entity(new RemoveClientExceptionDtoJaxb(ex.getClientId()))
				.build();
	}

}
