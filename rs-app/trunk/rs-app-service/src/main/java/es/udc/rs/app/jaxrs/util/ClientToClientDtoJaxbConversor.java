package es.udc.rs.app.jaxrs.util;

import java.net.URI;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.ws.rs.core.Link;

import es.udc.rs.app.jaxrs.dto.ClientDetailsDtoJaxb;
import es.udc.rs.app.jaxrs.dto.ClientDtoJaxb;
import es.udc.rs.app.jaxrs.resources.ClientResource;
import es.udc.rs.app.model.client.Client;

public class ClientToClientDtoJaxbConversor {

	public static List<ClientDtoJaxb> toClientDtoJaxb(List<Client> clients,
			URI baseUri, String type) {
		List<ClientDtoJaxb> clientDtos = new ArrayList<>(clients.size());
		for (int i = 0; i < clients.size(); i++) {
			Client client = clients.get(i);
			clientDtos.add(toClientDtoJaxb(client, baseUri, type));
		}
		return clientDtos;
	}
	
	public static ClientDtoJaxb toClientDtoJaxb(Client client, URI baseUri,
			String type) {
		Link selfLink = ServiceUtil.getLinkFromUri(baseUri, ClientResource.class,
				client.getClientId(), "self", "Self link", type);
		return new ClientDtoJaxb(client.getClientId(), client.getName(),
				client.getDNI(), client.getAddress(), client.getPhone(), selfLink);
	}

	public static ClientDetailsDtoJaxb toClientDetailsDtoJaxb(
			Client client, URI baseUri, String type) {
		Link nextLink = ServiceUtil.getLinkFromUri(baseUri,
				ClientResource.class, client.getClientId(), "client",
				"next", type);
		Link selfLink = ServiceUtil.getLinkFromUri(baseUri, ClientResource.class,
				client.getClientId(), "self", "Self link", type);
		List<Link> links = new ArrayList<Link>();
		links.add(nextLink);
		links.add(selfLink);
		return new ClientDetailsDtoJaxb(client.getClientId(), client.getName(),
				client.getDNI(), client.getAddress(), client.getPhone(), links);
	}  
	
	public static Client toClient(ClientDtoJaxb client) {
        return new Client(client.getName(), client.getDNI(), client.getAddress(), 
        		client.getPhone());
    }   
}
