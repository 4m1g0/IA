package es.udc.rs.app.model.client;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import es.udc.rs.app.model.call.Call;

public class Client {

	private Long clientId;
	private String name;
	private String DNI;
	private String address;
	private short phone;
	private Calendar creationDate;
	private List<Call> callList;
	private static long countClient = 0;

	public Client(String name, String DNI, String address, short phone) {
		this.name = name;
		this.DNI = DNI;
		this.address = address;
		this.phone = phone;
		this.creationDate.set(Calendar.MILLISECOND, 0);
		this.clientId = countClient;
		this.callList = new ArrayList<Call>();
		this.increment();
	}
	
	public synchronized void increment(){
		countClient++;	
	}

	public List<Call> getCallList() {
		return callList;
	}

	public void setCallList(ArrayList<Call> callList) {
		this.callList = callList;
	}
	
	public void addCall(Call call){
		this.callList.add(call);
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

	public void setDNI(String DNI) {
		DNI = DNI;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Calendar getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Calendar creationDate) {
		this.creationDate = creationDate;
		if (creationDate != null) {
			this.creationDate.set(Calendar.MILLISECOND, 0);
		}
	}

	public long getClientId() {
		return clientId;
	}

	public short getPhone() {
		return phone;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((DNI == null) ? 0 : DNI.hashCode());
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result
				+ ((callList == null) ? 0 : callList.hashCode());
		result = prime * result
				+ ((clientId == null) ? 0 : clientId.hashCode());
		result = prime * result
				+ ((creationDate == null) ? 0 : creationDate.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + phone;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		if (DNI == null) {
			if (other.DNI != null)
				return false;
		} else if (!DNI.equals(other.DNI))
			return false;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (callList == null) {
			if (other.callList != null)
				return false;
		} else if (!callList.equals(other.callList))
			return false;
		if (clientId == null) {
			if (other.clientId != null)
				return false;
		} else if (!clientId.equals(other.clientId))
			return false;
		if (creationDate == null) {
			if (other.creationDate != null)
				return false;
		} else if (!creationDate.equals(other.creationDate))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (phone != other.phone)
			return false;
		return true;
	}

}
