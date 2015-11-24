package es.udc.rs.app.client.service.rest;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBElement;

import org.eclipse.persistence.jaxb.xmlmodel.ObjectFactory;

import es.udc.rs.app.client.dto.ClientDto;

public class ClientDtoToClientDtoJaxbConversor {
	public static JAXBElement<ClientDtoJaxb> toJaxbClient(ClientDto clientDto) {
		ClientDtoJaxb client = new ClientDtoJaxb();
		client.setCId(clientDto.getClientId() != null ? clientDto.getClientId() : -1);
		client.setTitle(clientDto.getTitle());
		client.setRuntime(clientDto.getRuntime());
		client.setDescription(clientDto.getDescription());
		client.setPrice(clientDto.getPrice());
		JAXBElement<ClientDtoJaxb> jaxbElement= new ObjectFactory().createClient(client);
		return jaxbElement;
	}

	public static ClientDto toClientDto(ClientDtoJaxb client) {
		return new ClientDto(client.getClientId(), client.getTitle(),
				client.getRuntime(), client.getDescription(), client.getPrice());
	}

	public static List<ClientDto> toClientDtos(ClientDtoJaxbList ClientListDto) {
		List<ClientDtoJaxb> clientList = ClientListDto.getClients();
		List<ClientDto> clientDtos = new ArrayList<>(clientList.size());
		for (int i = 0; i < clientList.size(); i++) {
			clientDtos.add(toclientDto(clientList.get(i)));
		}
		return clientDtos;
	}
}
