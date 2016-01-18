package es.udc.rs.app.jaxrs.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import es.udc.rs.app.exceptions.MonthExpirationException;
import es.udc.rs.app.jaxrs.dto.MonthExpirationExceptionDtoJaxb;

public class MonthExpirationExceptionMaper implements ExceptionMapper<MonthExpirationException> {

	@Override
	public Response toResponse(MonthExpirationException ex) {
		return Response
				.status(Response.Status.CONFLICT)
				.entity(new MonthExpirationExceptionDtoJaxb(ex.getDate()))
				.build();
	}

}
