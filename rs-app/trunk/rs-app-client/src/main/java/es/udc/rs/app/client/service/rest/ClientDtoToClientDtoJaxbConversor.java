package es.udc.rs.app.client.service.rest;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBElement;

import org.eclipse.persistence.jaxb.xmlmodel.ObjectFactory;

import es.udc.rs.app.client.dto.ClientDto;

public class ClientDtoToClientDtoJaxbConversor {
	public static JAXBElement<ClientDtoJaxb> toJaxbMovie(ClientDto clientDto) {
		ClientDtoJaxb client = new ClientDtoJaxb();
		client.setCId(clientDto.getMovieId() != null ? movieDto.getMovieId() : -1);
		client.setTitle(clientDto.getTitle());
		client.setRuntime(clientDto.getRuntime());
		client.setDescription(clientDto.getDescription());
		client.setPrice(clientDto.getPrice());
		JAXBElement<ClientDtoJaxb> jaxbElement= new ObjectFactory().createMovie(client);
		return jaxbElement;
	}

	public static ClientDto toMovieDto(ClientDtoJaxb movie) {
		return new ClientDto(movie.getMovieId(), movie.getTitle(),
				movie.getRuntime(), movie.getDescription(), movie.getPrice());
	}

	public static List<ClientDto> toMovieDtos(ClientDtoJaxbList ClientListDto) {
		List<ClientDtoJaxb> clientList = ClientListDto.getClients();
		List<ClientDto> clientDtos = new ArrayList<>(clientList.size());
		for (int i = 0; i < clientList.size(); i++) {
			clientDtos.add(toMovieDto(clientList.get(i)));
		}
		return clientDtos;
	}
}
