package es.udc.rs.app.client.dto;

import java.util.Calendar;

public class Client {
	private Long clientId;
	private String name;
	private String DNI;
	private String address;
	private String phone;
	private Calendar creationDate;
	
	
	public Client(Long clientId, String name, String dNI, String address,
			String phone, Calendar creationDate) {
		super();
		this.clientId = clientId;
		this.name = name;
		DNI = dNI;
		this.address = address;
		this.phone = phone;
		this.creationDate = creationDate;
	}
	
	@Override
	public String toString() {
		return "Client [clientId=" + clientId + ", name=" + name + ", DNI="
				+ DNI + ", address=" + address + ", phone=" + phone
				+ ", creationDate=" + creationDate + "]";
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Calendar getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Calendar creationDate) {
		this.creationDate = creationDate;
	}
}
