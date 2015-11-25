package es.udc.rs.app.client.rest.util;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import es.udc.rs.app.client.dto.ClientDetailsDto;
import es.udc.rs.app.client.dto.ClientDto;
import es.udc.rs.app.client.service.rest.dto.ClientDetailsDtoJaxb;
import es.udc.rs.app.client.service.rest.dto.ClientDtoJaxb;
import es.udc.rs.app.client.service.rest.dto.ClientDtoJaxbList;
import es.udc.rs.app.client.service.rest.dto.JaxbLink;


public class ClientDtoToClientDtoJaxbConversor {

	public static ClientDto toClientDto(ClientDtoJaxb client){
		return new ClientDto(client.getClientId(), client.getName(),
				client.getDni(), LinkUtil.getLinkUri(client.getLink()));
	}
	
	public static List<ClientDto> toClientDtos(ClientDtoJaxbList clientListDto){
	
		List<ClientDtoJaxb> clientList = clientListDto.getClient();
		List<ClientDto> clientDtos = new ArrayList<>(clientList.size());
		for (int i = 0; i < clientList.size(); i++) {
			clientDtos.add(toClientDto(clientList.get(i)));
		}
		return clientDtos;
	}
	
	public static ClientDetailsDto toClientDetailsDto(ClientDetailsDtoJaxb client){
		List<JaxbLink> links = client.getLink();
		URI callUri = LinkUtil.getLinkUriFromList(links, "call");
		URI selfUri = LinkUtil.getLinkUriFromList(links, "self");
		return new ClientDetailsDto(client.getClientId(), client.getName(), client.getDNI(), 
				client.getAddress(), client.getPhone(), selfUri, callUri);
		
	}
}
