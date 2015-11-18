package es.udc.rs.app.jaxrs.dto.client;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="clients")
@XmlType(name="clientListType")
public class ClientDtoJaxbList {
	
	@XmlElement(name = "client")
	private List<ClientDtoJaxb> clients = null;

	public ClientDtoJaxbList(){
		this.clients = new ArrayList<ClientDtoJaxb>();
	}
	
	public ClientDtoJaxbList(List<ClientDtoJaxb> clients){
		this.clients = clients;
	}

	public List<ClientDtoJaxb> getClients() {
		return clients;
	}

	public void setClients(List<ClientDtoJaxb> clients) {
		this.clients = clients;
	}
	
}
