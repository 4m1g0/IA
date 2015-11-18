package es.udc.rs.app.jaxrs.resources;

import java.net.URI;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import es.udc.rs.app.jaxrs.dto.client.ClientDtoJaxb;
import es.udc.rs.app.jaxrs.dto.client.ClientDtoJaxbList;
import es.udc.rs.app.jaxrs.util.ClientToClientDtoJaxbConversor;
import es.udc.rs.app.model.client.Client;
import es.udc.rs.app.model.clientservice.ClientServiceFactory;
import es.udc.ws.util.exceptions.InputValidationException;

@Path("/clients")
public class ClientResource {
	
	@GET
	@Produces({ MediaType.APPLICATION_XML, 
		MediaType.APPLICATION_JSON })
	public ClientDtoJaxbList findClients(@QueryParam("keywords")
	String keywords, @QueryParam("index")
	String index, @QueryParam("numRows")
	String numRows){
		List<Client> clients = ClientServiceFactory.getService().findClients(
				keywords, Integer.parseInt(index), Integer.parseInt(numRows));
		List<ClientDtoJaxb> clientDtos = ClientToClientDtoJaxbConversor.toClientDtoJaxb(clients);
		return new ClientDtoJaxbList(clientDtos);	
	}
	
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response addClient(ClientDtoJaxb clientDto, @Context UriInfo ui) throws InputValidationException{
		Client client = ClientToClientDtoJaxbConversor.toClient(clientDto);
		client = ClientServiceFactory.getService().addClient(client);
		ClientDtoJaxb resultClientDto = ClientToClientDtoJaxbConversor.toClientDtoJaxb(client);
		String requestUri = ui.getRequestUri().toString();
		return Response.created(URI.create(requestUri + (requestUri.endsWith("/") ? "" : "/") 
				+ client.getClientId())).entity(resultClientDto).build();
	}
}
