package es.udc.rs.app.jaxrs.util;

import java.util.ArrayList;
import java.util.List;

import es.udc.rs.app.jaxrs.dto.client.ClientDtoJaxb;
import es.udc.rs.app.model.client.Client;

public class ClientToClientDtoJaxbConversor {

	public static List<ClientDtoJaxb> toClientDtoJaxb(List<Client> clients){
		List<ClientDtoJaxb> clientDtos = new ArrayList<>(clients.size());
		for (int i = 0; i < clients.size(); i++) {
            Client client = clients.get(i);
            ////////////////////////////7clientDtos.add(toClientDtoJaxb(client));
        }
        return clientDtos;
	}
	
	public static ClientDtoJaxb toMovieDtoJaxb(Client client) {
        return new ClientDtoJaxb(client.getClientId(), client.getName(),
        		client.getDNI(), client.getAddress(),client.getPhone());
    }

    public static Client toClient(ClientDtoJaxb client) {
        return new Client(client.getName(), client.getDNI(),
        		client.getAddress(), client.getPhone());
    }    	
}
