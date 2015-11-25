package es.udc.rs.app.client.dto;

import java.net.URI;
import java.util.List;

public class ClientListIntervalDto {
	private List<ClientDto> clients;
	private URI nextIntervalUri;
	private URI previousIntervalUri;
	
	public ClientListIntervalDto(List<ClientDto> products, URI nextIntervalUri,
			URI previousIntervalUri) {
		super();
		this.clients = products;
		this.nextIntervalUri = nextIntervalUri;
		this.previousIntervalUri = previousIntervalUri;
	}
	
	@Override
	public String toString() {
		return "ClientListInterval [products=" + clients
				+ ", nextIntervalUri=" + nextIntervalUri
				+ ", previousIntervalUri=" + previousIntervalUri + "]";
	}

	public List<ClientDto> getProducts() {
		return clients;
	}
	public void setProducts(List<ClientDto> products) {
		this.clients = products;
	}
	public URI getNextIntervalUri() {
		return nextIntervalUri;
	}
	public void setNextIntervalUri(URI nextIntervalUri) {
		this.nextIntervalUri = nextIntervalUri;
	}
	public URI getPreviousIntervalUri() {
		return previousIntervalUri;
	}
	public void setPreviousIntervalUri(URI previousIntervalUri) {
		this.previousIntervalUri = previousIntervalUri;
	}
}
