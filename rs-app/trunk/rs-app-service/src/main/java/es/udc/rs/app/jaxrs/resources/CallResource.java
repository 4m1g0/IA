package es.udc.rs.app.jaxrs.resources;

import java.net.URI;
import java.util.Calendar;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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

import es.udc.rs.app.constants.EnumState;
import es.udc.rs.app.constants.EnumType;
import es.udc.rs.app.exceptions.CallStateException;
import es.udc.rs.app.exceptions.MonthExpirationException;
import es.udc.rs.app.jaxrs.dto.CallDetailsDtoJaxb;
import es.udc.rs.app.jaxrs.dto.CallDetailsDtoJaxbList;
import es.udc.rs.app.jaxrs.dto.CallDtoJaxb;
import es.udc.rs.app.jaxrs.dto.CallDtoJaxbList;
import es.udc.rs.app.jaxrs.util.CallToCallDtoJaxbConversor;
import es.udc.rs.app.jaxrs.util.ServiceUtil;
import es.udc.rs.app.model.call.Call;
import es.udc.rs.app.model.clientservice.ClientServiceFactory;
import es.udc.ws.util.exceptions.InputValidationException;
import es.udc.ws.util.exceptions.InstanceNotFoundException;
import es.udc.rs.app.jaxb.*;

@Path("/calls")
public class CallResource {
	
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response makeCall(CallDetailsDtoJaxb callDto, @Context UriInfo ui, @Context HttpHeaders headers) throws InputValidationException, InstanceNotFoundException{
		Call call = ClientServiceFactory.getService().makeCall(callDto.getClientId(), callDto.getDateCall(), callDto.getDuration(), EnumType.toEnumType(callDto.getType()), callDto.getDestPhone());
		CallDtoJaxb resultCallDto = CallToCallDtoJaxbConversor.toCallDtoJaxb(call, ui.getBaseUri(), ServiceUtil.getTypeAsStringFromHeaders(headers));
		
		String newId = String.valueOf(call.getClientId());
		URI uri = ui.getAbsolutePathBuilder().path(newId).build();
		
		return Response.created(uri)
					   .entity(resultCallDto)
					   .build();
	}
	
	@POST
	@Path("/state")
	public Response changeState(@QueryParam("id") Long id, 
							@QueryParam("month") String month,
							@QueryParam("year") String year,
							@QueryParam("state") String state) 
			throws InputValidationException, CallStateException, InstanceNotFoundException, MonthExpirationException{
		
		Calendar callDate = StringToDate.getCalendar(year + "-" + month + "-01 00:00:00"); // throws input validation
		
		ClientServiceFactory.getService().changeCallState(id, callDate, EnumState.toEnumState(state));

		return Response.ok().build();
	}
	
	
	
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response findCalls(
			@QueryParam("id") Long id,
			@QueryParam("initDate") String initDate,
			@QueryParam("endDate") String endDate,
			@QueryParam("month") String month,
			@QueryParam("year") String year,
			@DefaultValue("-1") @QueryParam("index") int index, 
			@DefaultValue("-1") @QueryParam("numRows") int numRows,
			@QueryParam("type") String callType,
			@Context UriInfo uriInfo, @Context HttpHeaders headers) throws InputValidationException, NumberFormatException, CallStateException, InstanceNotFoundException{
		
		List<Call> calls;
		
		String type = ServiceUtil.getTypeAsStringFromHeaders(headers);
		
		if(month != null){
			calls = ClientServiceFactory.getService().findCalls(id, StringToDate.getCalendar(year + "-" + month + "-01 00:00:00"));
			List<CallDetailsDtoJaxb> callDetailsDtos = CallToCallDtoJaxbConversor.toCallDetailsDtoJaxb(calls, uriInfo.getBaseUri(), type);
			
			Link selfLink = Link.fromUri(uriInfo.getRequestUri()).build();
			
			ResponseBuilder response = Response.ok(new CallDetailsDtoJaxbList(callDetailsDtos)).links(selfLink);
			return response.build();
			
		}
		
			Calendar callInitDate = StringToDate.getCalendar(initDate); // throws input validation
			Calendar callEndDate = StringToDate.getCalendar(endDate); // throws input validation
			
			if (callType == null){
				calls = ClientServiceFactory.getService().findCalls(id, callInitDate, callEndDate, index, numRows);
			} else {

				calls = ClientServiceFactory.getService().findCalls(id, callInitDate, callEndDate, index, numRows, EnumType.toEnumType(callType));
		}
		
		

		List<CallDtoJaxb> callDtos = CallToCallDtoJaxbConversor.toCallDtoJaxb(calls, uriInfo.getBaseUri(), type);
		
		Link selfLink = Link.fromUri(uriInfo.getRequestUri()).build();
		
		Link nextLink = getNextLink(uriInfo, index, numRows, calls.size());
		Link previousLink = getPreviousLink(uriInfo, index, numRows, calls.size());
		
		ResponseBuilder response = Response.ok(new CallDtoJaxbList(callDtos)).links(selfLink);
		
		if (nextLink != null) {
			response.links(nextLink);
		}
		if (previousLink != null) {
			response.links(previousLink);
		}
		return response.build();
	}
	
	private static Link getNextLink(UriInfo self, int index, int numrows, int size) {
		if (index == -1 || numrows == -1)
			return null;
		
		if (numrows > size)
			return null;
		
		UriBuilder uriBuilder = self.getRequestUriBuilder()
				.replaceQueryParam("index", index+numrows)
				.replaceQueryParam("numRows", numrows);
		Link.Builder linkBuilder = Link.fromUriBuilder(uriBuilder)
				.rel("next")
				.title("Next call page");
		
		return linkBuilder.build();
	}

	private static Link getPreviousLink(UriInfo self, int index, int numrows, int size) {
		if (index == 0 || numrows == -1)
			return null;
		
		if (size < numrows)
			return null;
		
		UriBuilder uriBuilder = self.getRequestUriBuilder()
				.replaceQueryParam("index", index-numrows)
				.replaceQueryParam("numRows", numrows);
		Link.Builder linkBuilder = Link.fromUriBuilder(uriBuilder)
				.rel("previous")
				.title("previous call page");
		
		return linkBuilder.build();
	}

}
