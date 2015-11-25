package es.udc.rs.app.client.dto;

import java.net.URI;
import java.util.Calendar;

import javax.ws.rs.core.Link;

public class ClientDto {
	private Long clientId;
	private String name;
	private String DNI;
	
	
	
	public ClientDto(Long clientId, String name, String dNI) {
		super();
		this.clientId = clientId;
		this.name = name;
		this.DNI = dNI;
	}
	
	public ClientDto(String name, String dNI) {
		this.name = name;
		DNI = dNI;
	}
	
	public Long getClientId() {
		return clientId;
	}
	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDNI() {
		return DNI;
	}
	public void setDNI(String dNI) {
		DNI = dNI;
	}

	@Override
	public String toString() {
		return "ClientDto [clientId=" + clientId + ", name=" + name + ", DNI="
				+ DNI + "]";
	}
	

}
