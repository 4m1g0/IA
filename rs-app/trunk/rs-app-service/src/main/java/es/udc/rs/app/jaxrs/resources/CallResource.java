package es.udc.rs.app.jaxrs.resources;

import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.UriInfo;
import es.udc.rs.app.constants.ModelConstants.enumState;
import es.udc.rs.app.constants.ModelConstants.enumType;
import es.udc.rs.app.exceptions.CallStateException;
import es.udc.rs.app.exceptions.MonthExpirationException;
import es.udc.rs.app.jaxrs.dto.CallDetailsDtoJaxb;
import es.udc.rs.app.jaxrs.dto.CallDtoJaxb;
import es.udc.rs.app.jaxrs.dto.CallDtoJaxbList;
import es.udc.rs.app.jaxrs.util.CallToCallDtoJaxbConversor;
import es.udc.rs.app.jaxrs.util.ServiceUtil;
import es.udc.rs.app.model.call.Call;
import es.udc.rs.app.model.clientservice.ClientServiceFactory;
import es.udc.ws.util.exceptions.InputValidationException;
import es.udc.ws.util.exceptions.InstanceNotFoundException;

@Path("/calls")
public class CallResource {
	
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response makeCall(CallDetailsDtoJaxb callDto, @Context UriInfo ui, @Context HttpHeaders headers) throws InputValidationException, InstanceNotFoundException{
		Calendar callDate = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(ServiceUtil.DATE_FORMAT_DAY);
		try {
			callDate.setTime(sdf.parse(callDto.getDateCall()));
		} catch (ParseException e) {
			throw new InputValidationException("Formato de fecha incorrecto");
		}
		Call call = ClientServiceFactory.getService().makeCall(callDto.getClientId(), callDate, callDto.getDuration(), callDto.getType(), callDto.getDestPhone());
		CallDtoJaxb resultCallDto = CallToCallDtoJaxbConversor.toCallDtoJaxb(call, ui.getBaseUri(), ServiceUtil.getTypeAsStringFromHeaders(headers));
		
		String newId = String.valueOf(call.getClientId());
		URI uri = ui.getAbsolutePathBuilder().path(newId).build();
		
		return Response.created(uri)
					   .entity(resultCallDto)
					   .build();
	}
	
	@PUT
	public void changeState(@QueryParam("id") Long id, 
							@QueryParam("date") String date,
							@QueryParam("state") String state) 
			throws InputValidationException, CallStateException, InstanceNotFoundException, MonthExpirationException{
		
		enumState st;
		Calendar callDate = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(ServiceUtil.DATE_FORMAT_DAY);
		try {
			callDate.setTime(sdf.parse(date));
			st = enumState.valueOf(state);
		} catch (Exception e) {
			throw new InputValidationException("Formato de fecha incorrecto");
		}
		

		ClientServiceFactory.getService().changeCallState(id, callDate, st);
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response findCalls(
			@QueryParam("id") Long id,
			@QueryParam("initdate") String initDate,
			@QueryParam("enddate") String endDate,
			@DefaultValue("-1") @QueryParam("index") int index, 
			@DefaultValue("-1") @QueryParam("numRows") int numRows,
			@QueryParam("type") String callType,
			@Context UriInfo uriInfo, @Context HttpHeaders headers) throws InputValidationException, NumberFormatException, CallStateException, InstanceNotFoundException{
		
		Calendar callInitDate = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(ServiceUtil.DATE_FORMAT_DAY);
		try {
			callInitDate.setTime(sdf.parse(initDate));
		} catch (Exception e) {
			throw new InputValidationException("Formato de fecha incorrecto");
		}
		List<Call> calls;
		
		if (endDate == null){
			calls = ClientServiceFactory.getService().findCalls(id, callInitDate, index, numRows);
		} else{
			Calendar callEndDate = Calendar.getInstance();
			try {
				callEndDate.setTime(sdf.parse(endDate));
			} catch (Exception e) {
				throw new InputValidationException("Formato de fecha incorrecto");
			}
			
			if (callType == null){
				calls = ClientServiceFactory.getService().findCalls(id, callInitDate, callEndDate, index, numRows);
			} else {
				enumType et;
				try {
					et = enumType.valueOf(callType);
				}catch (Exception e) {
					throw new InputValidationException("Tipo incorrecto");
				}
				calls = ClientServiceFactory.getService().findCalls(id, callInitDate, callEndDate, index, numRows, et);
			}
		}
		
		String type = ServiceUtil.getTypeAsStringFromHeaders(headers);

		List<CallDtoJaxb> callDtos = CallToCallDtoJaxbConversor.toCallDtoJaxb(calls, uriInfo.getBaseUri(), type);
		
		Link selfLink = Link.fromUri(uriInfo.getRequestUri()).build();
		Link nextLink = getNextLink(uriInfo, index, numRows);
		Link previousLink = getPreviousLink(uriInfo, index, numRows);
		
		ResponseBuilder response = Response.ok(new CallDtoJaxbList(callDtos)).links(selfLink);
		
		if (nextLink != null) {
			response.links(nextLink);
		}
		if (previousLink != null) {
			response.links(previousLink);
		}
		return response.build();
	}
	
	private static Link getNextLink(UriInfo self, int index, int numrows) {
		if (index == -1 || numrows == -1)
			return null;
		
		UriBuilder uriBuilder = self.getRequestUriBuilder()
				.queryParam("inde", index+numrows)
				.queryParam("numrows", numrows);
		Link.Builder linkBuilder = Link.fromUriBuilder(uriBuilder)
				.rel("next")
				.title("Next call page");
		
		return linkBuilder.build();
	}

	private static Link getPreviousLink(UriInfo self, int index, int numrows) {
		if (index == -1 || numrows == -1)
			return null;
		
		UriBuilder uriBuilder = self.getRequestUriBuilder()
				.queryParam("inde", index-numrows)
				.queryParam("numrows", numrows);
		Link.Builder linkBuilder = Link.fromUriBuilder(uriBuilder)
				.rel("previous")
				.title("previous call page");
		
		return linkBuilder.build();
	}

}
