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
import es.udc.rs.app.jaxrs.dto.client.ClientDetailsDtoJaxb;
import es.udc.rs.app.jaxrs.dto.client.ClientDtoJaxb;
import es.udc.rs.app.jaxrs.dto.client.ClientDtoJaxbList;
import es.udc.rs.app.jaxrs.util.ClientToClientDtoJaxbConversor;
import es.udc.rs.app.jaxrs.util.ServiceUtil;
import es.udc.rs.app.model.client.Client;
import es.udc.rs.app.model.clientservice.ClientService;
import es.udc.rs.app.model.clientservice.ClientServiceFactory;
import es.udc.rs.app.model.clientservice.ClientServiceImpl;
import es.udc.ws.util.exceptions.InputValidationException;
import es.udc.ws.util.exceptions.InstanceNotFoundException;

@Path("/clients")
public class ClientResource {
	
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response findClients(
	@DefaultValue("") @QueryParam("keywords") String keywords, 
	@DefaultValue("0") @QueryParam("index") int index, 
	@DefaultValue("2") @QueryParam("numRows") int numRows,
	@Context UriInfo uriInfo, @Context HttpHeaders headers){
		
		List<Client> clients = ClientServiceFactory.getService().findClients(
				keywords, index, numRows);
		
		String type = ServiceUtil.getTypeAsStringFromHeaders(headers);

		List<ClientDtoJaxb> clientDtos = ClientToClientDtoJaxbConversor
				.toClientDtoJaxb(clients, uriInfo.getBaseUri(), type);
		Link selfLink = getSelfLink(uriInfo, keywords, index, numRows, type);
		Link nextLink = getNextLink(uriInfo, keywords, index, numRows, clients.size(), type);
		Link previousLink = getPreviousLink(uriInfo, keywords, index,
				numRows, type);
		ResponseBuilder response = Response.ok(
				new ClientDtoJaxbList(clientDtos)).links(selfLink);
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
				+ count, count, "next", "Next interval of products", type);
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
				startIndex, count, "previous", "Previous interval of products",
				type);
	}
	
	private Link getSelfLink(UriInfo uriInfo, String keyword, int startIndex,
			int count, String type) {
		return ServiceUtil
				.getProductsIntervalLink(uriInfo, keyword, startIndex, count,
						"self", "Current interval of products", type);
	}

	@GET
	@Path("/{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public ClientDetailsDtoJaxb findById(@PathParam("id") long id,
			@Context UriInfo uriInfo, @Context HttpHeaders headers)
			throws InstanceNotFoundException {
		return ClientToClientDtoJaxbConversor.toClientDetailsDtoJaxb(ClientServiceFactory
				.getService().findClient(id), uriInfo.getBaseUri(), 
				ServiceUtil.getTypeAsStringFromHeaders(headers));

	}
	@GET
	@Path("/{Dni}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public ClientDetailsDtoJaxb findByDni(@PathParam("Dni") String Dni,
			@Context UriInfo uriInfo, @Context HttpHeaders headers)
			throws InstanceNotFoundException {
		return ClientToClientDtoJaxbConversor.toClientDetailsDtoJaxb(ClientServiceFactory
				.getService().findClient(Dni), uriInfo.getBaseUri(), 
				ServiceUtil.getTypeAsStringFromHeaders(headers));

	}
	
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response addClient(ClientDtoJaxb clientDto, @Context UriInfo ui,
			@Context HttpHeaders headers)
			throws InputValidationException{
		Client client = ClientToClientDtoJaxbConversor.toClient(clientDto);
		client = ClientServiceFactory.getService().addClient(client);
		ClientDtoJaxb resultClientDto = ClientToClientDtoJaxbConversor
				.toClientDtoJaxb(client, ui.getBaseUri(), 
						ServiceUtil.getTypeAsStringFromHeaders(headers));
		String requestUri = ui.getRequestUri().toString();
		return Response.created(URI.create(requestUri +
				(requestUri.endsWith("/") ? "" : "") +
				client.getClientId())).entity(resultClientDto).build();
		
	}
	
	@PUT
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Path("/{id}")
	public void updateclient(ClientDtoJaxb clientDto,@PathParam("id") String id)
			throws InputValidationException, InstanceNotFoundException {
		
		Long clientId;
		try {
			clientId = Long.valueOf(id);
		} catch (Exception e) {
			throw new InputValidationException("Invalid Request: "
					+ "unable to parse client id '" + id + "'");
		}
		
		Client client = ClientToClientDtoJaxbConversor.toClient(clientDto);
		client.setClientId(clientId);
		ClientServiceFactory.getService().updateClient(client);
	}
	
	@DELETE
	@Path("/{id}")
	public void deleteClient(@PathParam("id") String id)
			throws InputValidationException, InstanceNotFoundException, RemoveClientException{
		
		Long clientId;
		try {
			clientId = Long.valueOf(id);
		} catch (Exception e) {
			throw new InputValidationException("Invalid Request: "
					+ "unable to parse client id '" + id + "'");
		}
		ClientServiceFactory.getService().removeClient(clientId);
	}
	
	
}
