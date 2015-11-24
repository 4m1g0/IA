package es.udc.rs.app.jaxrs.resources;

import java.util.Calendar;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import es.udc.rs.app.constants.ModelConstants.enumState;
import es.udc.rs.app.exceptions.CallStateException;
import es.udc.rs.app.exceptions.MonthExpirationException;
import es.udc.rs.app.jaxrs.dto.call.CallDetailsDtoJaxb;
import es.udc.rs.app.jaxrs.dto.call.CallDtoJaxb;
import es.udc.rs.app.jaxrs.dto.call.DateDtoJaxb;
import es.udc.rs.app.jaxrs.util.CallToCallDtoJaxbConversor;
import es.udc.rs.app.model.call.Call;
import es.udc.rs.app.model.clientservice.ClientServiceFactory;
import es.udc.ws.util.exceptions.InputValidationException;
import es.udc.ws.util.exceptions.InstanceNotFoundException;

@Path("/calls")
public class CallResource {
	
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public void addPhoneCall(CallDtoJaxb callDto) throws InputValidationException, InstanceNotFoundException{
		Call call = CallToCallDtoJaxbConversor.toCall(callDto);
		ClientServiceFactory.getService().makeCall(call.getClientId(), call.getDateCall(), 
				call.getDuration(), call.getType(), call.getDestPhone());
		
	}
	
	@PUT
	@Path("/{id}")
	public void changeState(@PathParam("id") String id, DateDtoJaxb date, enumState state) 
			throws InputValidationException, CallStateException, InstanceNotFoundException, 
			MonthExpirationException{
		
		Long clientId;
		try {
			clientId = Long.valueOf(id);
		} catch (Exception e) {
			throw new InputValidationException("Invalid Request: "
					+ "unable to parse client id '" + id + "'");
		}
		Calendar cal = Calendar.getInstance();
		cal.set(date.getYear(), date.getMonth(), date.getDay());
		ClientServiceFactory.getService().changeCallState(clientId, cal, state);
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response findCallsToBill(
			@DefaultValue("") @QueryParam("id") String id,
			@DefaultValue("0") @QueryParam("index") int index, 
			@DefaultValue("2") @QueryParam("numRows") int numRows,
			@Context UriInfo uriInfo, @Context HttpHeaders headers){
				return null;
		
		
	}

}
