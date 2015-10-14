package es.udc.rs.app.model.client;

import java.util.ArrayList;
import java.util.Calendar;

import es.udc.rs.app.model.call.Call;

public class Client {

	private long clientId;
	private String name;
	private short DNI;
	private String address;
	private short phone;
	private Calendar creationDate;
	private ArrayList<Call> callList;
	private static long countClient = 0;

	public Client(String name, short DNI, String address, short phone) {
		this.name = name;
		this.DNI = DNI;
		this.address = address;
		this.phone = phone;
		this.creationDate.set(Calendar.MILLISECOND, 0);
		this.clientId = countClient;
		this.callList = new ArrayList<Call>();
		countClient++;
	}

	public ArrayList<Call> getCallList() {
		return callList;
	}

	public void setCallList(ArrayList<Call> callList) {
		this.callList = callList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public short getDNI() {
		return DNI;
	}

	public void setDNI(short dNI) {
		DNI = dNI;
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
		result = prime * result + DNI;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result
				+ ((callList == null) ? 0 : callList.hashCode());
		result = prime * result + (int) (clientId ^ (clientId >>> 32));
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
		if (DNI != other.DNI)
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
		if (clientId != other.clientId)
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
