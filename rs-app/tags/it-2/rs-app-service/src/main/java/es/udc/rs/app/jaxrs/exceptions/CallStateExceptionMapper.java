package es.udc.rs.app.jaxrs.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import es.udc.rs.app.exceptions.CallStateException;
import es.udc.rs.app.jaxrs.dto.CallStateExceptionDtoJaxb;
import es.udc.rs.app.jaxrs.dto.InstanceNotFoundExceptionDtoJaxb;
import es.udc.rs.app.jaxrs.dto.RemoveClientExceptionDtoJaxb;

public class CallStateExceptionMapper implements ExceptionMapper<CallStateException> {

	@Override
	public Response toResponse(CallStateException ex) {
		return Response.status(Response.Status.CONFLICT)
				.entity(new CallStateExceptionDtoJaxb(ex.getClientId(), ex.getCallId()))
				.build();
	}


}
