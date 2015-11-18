package es.udc.rs.app.client.dto;

import java.net.URI;
import java.util.List;

public class ClientListInterval {
	private List<Client> clients;
	private URI nextIntervalUri;
	private URI previousIntervalUri;
	
	public ClientListInterval(List<Client> products, URI nextIntervalUri,
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

	public List<Client> getProducts() {
		return clients;
	}
	public void setProducts(List<Client> products) {
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
