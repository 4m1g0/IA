package es.udc.rs.app.jaxrs.resources;

import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
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
import javax.ws.rs.core.Link;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.ResponseBuilder;

import es.udc.rs.app.exceptions.RemoveClientException;
import es.udc.rs.app.jaxrs.dto.ClientDetailsDtoJaxb;
import es.udc.rs.app.jaxrs.dto.ClientDtoJaxb;
import es.udc.rs.app.jaxrs.dto.ClientDtoJaxbList;
import es.udc.rs.app.jaxrs.util.ClientToClientDtoJaxbConversor;
import es.udc.rs.app.jaxrs.util.ServiceUtil;
import es.udc.rs.app.model.client.Client;
import es.udc.rs.app.model.clientservice.ClientServiceFactory;
import es.udc.ws.util.exceptions.InputValidationException;
import es.udc.ws.util.exceptions.InstanceNotFoundException;

@Path("/clients")
public class ClientResource {
	
	@GET
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response findClients(
		@DefaultValue("") @QueryParam("keywords") String keywords, 
		@DefaultValue("0") @QueryParam("index") String index, 
		@DefaultValue("2") @QueryParam("numRows") String numRows,
		@DefaultValue("") @QueryParam("dni") String dni,
		@Context UriInfo uriInfo, @Context HttpHeaders headers) throws InputValidationException, InstanceNotFoundException{
		
		if (!dni.equals("")){
			ClientDetailsDtoJaxb clientDto =  ClientToClientDtoJaxbConversor.toClientDetailsDtoJaxb(ClientServiceFactory.getService().findClient(dni), uriInfo.getBaseUri(), 
					ServiceUtil.getTypeAsStringFromHeaders(headers));
			
			String newId = String.valueOf(clientDto.getId());
			URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
			
			return Response.ok(clientDto)
					.link(uri, "self")
					.build();
		}
		
		int startIndex;
		int size;
		try {
			startIndex = Integer.valueOf(index);
			size = Integer.valueOf(numRows);
		} catch (Exception e) {
			throw new InputValidationException("Invalid Request: unable to parse Frames '" + index + "'" + "'" + numRows + "'");
		}
		
		List<Client> clients = ClientServiceFactory.getService().findClients(keywords, startIndex, size);
		
		String type = ServiceUtil.getTypeAsStringFromHeaders(headers);

		List<ClientDtoJaxb> clientDtos = ClientToClientDtoJaxbConversor.toClientDtoJaxb(clients, uriInfo.getBaseUri(), type);
		
		Link selfLink = getSelfLink(uriInfo, keywords, startIndex, size, type);
		Link nextLink = getNextLink(uriInfo, keywords, startIndex, size, clients.size(), type);
		Link previousLink = getPreviousLink(uriInfo, keywords, startIndex, size, type);
		
		ResponseBuilder response = Response.ok(new ClientDtoJaxbList(clientDtos)).links(selfLink);
		if (nextLink != null) {
			response.links(nextLink);
		}
		if (previousLink != null) {
			response.links(previousLink);
		}
		return response.build();
	}
	
	private static Link getNextLink(UriInfo uriInfo, String keyword,
			int startIndex, int numRows, int count, String type) {
		if (numRows >= count) {
			return null;
		}
		return ServiceUtil.getIntervalLink(uriInfo, keyword, startIndex
				+ numRows, numRows, "next", "Next interval of clients", type);
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
		return ServiceUtil.getIntervalLink(uriInfo, keyword,
				startIndex, count, "previous", "Previous interval of clients",
				type);
	}
	
	private Link getSelfLink(UriInfo uriInfo, String keyword, int startIndex, int count, String type) {
		return ServiceUtil.getIntervalLink(uriInfo, keyword, startIndex, count, "self", "Current interval of clients", type);
	}

	@GET
	@Path("/{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public ClientDetailsDtoJaxb findById(@PathParam("id") Long id, @Context UriInfo uriInfo, @Context HttpHeaders headers)
			throws InstanceNotFoundException, InputValidationException {
		
		return ClientToClientDtoJaxbConversor.toClientDetailsDtoJaxb(ClientServiceFactory.getService().findClient(id), uriInfo.getBaseUri(), 
				ServiceUtil.getTypeAsStringFromHeaders(headers));

	}
	
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response addClient(ClientDetailsDtoJaxb clientDto, @Context UriInfo ui, @Context HttpHeaders headers)
			throws InputValidationException{
		
		Client client = ClientToClientDtoJaxbConversor.toClient(clientDto);
		client = ClientServiceFactory.getService().addClient(client);
		
		ClientDetailsDtoJaxb resultClientDto = ClientToClientDtoJaxbConversor.toClientDetailsDtoJaxb(client, ui.getBaseUri(), ServiceUtil.getTypeAsStringFromHeaders(headers));
		
		String newId = String.valueOf(client.getClientId());
		URI uri = ui.getAbsolutePathBuilder().path(newId).build();
		
		return Response.created(uri).entity(resultClientDto).build();
		
	}
	
	@PUT
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Path("/{id}")
	public void updateClient(ClientDetailsDtoJaxb clientDto,@PathParam("id") Long id)
			throws InputValidationException, InstanceNotFoundException {
		
		Client client = ClientToClientDtoJaxbConversor.toClient(clientDto);
		client.setClientId(id);
		ClientServiceFactory.getService().updateClient(client);
	}
	
	@DELETE
	@Path("/{id}")
	public void deleteClient(@PathParam("id") Long id)
			throws InputValidationException, InstanceNotFoundException, RemoveClientException{
		
		ClientServiceFactory.getService().removeClient(id);
	}
	
	
}
