package es.udc.rs.app.jaxrs.resources;

import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.sql.rowset.spi.TransactionalWriter;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.ResponseBuilder;

import es.udc.rs.app.constants.ModelConstants;
import es.udc.rs.app.constants.ModelConstants.enumState;
import es.udc.rs.app.constants.ModelConstants.enumType;
import es.udc.rs.app.exceptions.CallStateException;
import es.udc.rs.app.exceptions.MonthExpirationException;
import es.udc.rs.app.jaxrs.dto.CallDetailsDtoJaxb;
import es.udc.rs.app.jaxrs.dto.CallDtoJaxb;
import es.udc.rs.app.jaxrs.dto.ClientDtoJaxb;
import es.udc.rs.app.jaxrs.dto.ClientDtoJaxbList;
import es.udc.rs.app.jaxrs.util.CallToCallDtoJaxbConversor;
import es.udc.rs.app.jaxrs.util.ClientToClientDtoJaxbConversor;
import es.udc.rs.app.jaxrs.util.ServiceUtil;
import es.udc.rs.app.model.call.Call;
import es.udc.rs.app.model.clientservice.ClientServiceFactory;
import es.udc.ws.util.exceptions.InputValidationException;
import es.udc.ws.util.exceptions.InstanceNotFoundException;
import es.udc.rs.app.jaxb.StringToDate;

@Path("/calls")
public class CallResource {
	
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response makeCall(CallDetailsDtoJaxb callDto, @Context UriInfo ui, @Context HttpHeaders headers) throws InputValidationException, InstanceNotFoundException{
		Calendar callDate = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
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
	@Path("/{id}/{year}/{month}/{state}")
	public void changeState(@PathParam("id") String id, @PathParam("month") String monthDate,
			@PathParam("year") String yearDate, @PathParam("state") String state) 
			throws InputValidationException, CallStateException, InstanceNotFoundException, 
			MonthExpirationException{
		
		Long clientId;
		enumState st = ModelConstants.enumState.valueOf(state);
		int month;
		int year;
		try {
			clientId = Long.valueOf(id);
			month = Integer.parseInt(monthDate)- 1;
			year = Integer.parseInt(yearDate);
		} catch (Exception e) {
			throw new InputValidationException("Invalid Request: "
					+ "unable to parse client id '" + id + "'");
		}
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, month);
		cal.set(Calendar.YEAR, year);
		ClientServiceFactory.getService().changeCallState(clientId, cal, st);
	}
	
	/*@GET
	@Path("/{id}")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response findCalls(
			@PathParam("id") String id,
	@DefaultValue("") @QueryParam("month") String month,
	@DefaultValue("") @QueryParam("year") String year, 
	@DefaultValue("0") @QueryParam("index") String index, 
	@DefaultValue("2") @QueryParam("numRows") String numRows,
	@Context UriInfo uriInfo, @Context HttpHeaders headers) throws InputValidationException, NumberFormatException, CallStateException, InstanceNotFoundException{
		
		Long clientId = null;
		Calendar cal = Calendar.getInstance();
		int startIndex;
		int size;
		try {
			startIndex = Integer.valueOf(index);
			size = Integer.valueOf(numRows);
			clientId = Long.parseLong(id);
			cal.set(Integer.parseInt(year), Integer.parseInt(month), 10);
		} catch (Exception e) {
			throw new InputValidationException("Invalid Request: "
					+ "unable to parse Frames '" + index + "'" + "'" + numRows + "'");
		}
		
		List<Call> calls =ClientServiceFactory.getService().findCalls(clientId, cal, Integer.parseInt(index), 
				Integer.parseInt(numRows));
		
		String type = ServiceUtil.getTypeAsStringFromHeaders(headers);

		List<CallDtoJaxb> callDtos = CallToCallDtoJaxbConversor.toCallDtoJaxb(
				calls, uriInfo.getBaseUri(), type);
		Link selfLink = getSelfLink(uriInfo, id, startIndex, size, type);
		Link nextLink = getNextLink(uriInfo, id, startIndex, size, calls.size(), type);
		Link previousLink = getPreviousLink(uriInfo, id, startIndex,
				size, type);
		ResponseBuilder response = Response.ok(
				new CallDtoJaxbList(callDtos)).links(selfLink);
		if (nextLink != null) {
			response.links(nextLink);
		}
		if (previousLink != null) {
			response.links(previousLink);
		}
		return response.build();
	}
	
	private static Link getNextLink(UriInfo uriInfo, String keyword,
			int startIndex, int count, int numberOfProducts, String type) {
		if (numberOfProducts < count) {
			return null;
		}
		return ServiceUtil.getProductsIntervalLink(uriInfo, keyword, startIndex
				+ count, count, "next", "Next interval of clients", type);
	}

	private Link getPreviousLink(UriInfo uriInfo, String keyword,
			int startIndex, int count, String type) {
		if (startIndex <= 0) {
			return null;
		}
		startIndex = startIndex - count;
		if (startIndex < 0) {
			startIndex = 0;
		}
		return ServiceUtil.getProductsIntervalLink(uriInfo, keyword,
				startIndex, count, "previous", "Previous interval of clients",
				type);
	}
	
	private Link getSelfLink(UriInfo uriInfo, String keyword, int startIndex,
			int count, String type) {
		return ServiceUtil
				.getProductsIntervalLink(uriInfo, keyword, startIndex, count,
						"self", "Current interval of clients", type);
	}
	*/

}
