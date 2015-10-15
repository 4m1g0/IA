package es.udc.rs.app.model.clientservice;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import es.udc.rs.app.exceptions.CallStateException;
import es.udc.rs.app.model.call.Call;
import es.udc.rs.app.model.client.Client;
import es.udc.rs.app.model.util.ModelConstants.enumState;
import es.udc.rs.app.model.util.ModelConstants.enumType;
import es.udc.ws.util.exceptions.InputValidationException;
import es.udc.ws.util.exceptions.InstanceNotFoundException;

public class ClientServiceImpl implements ClientService {
	
	Map<Long, Client> clients = new TreeMap<Long, Client>();
	
	
	public ClientServiceImpl() {
		
		
		
	}

	@Override
	public Client addClient(Client client) throws InputValidationException {
		// TODO Auto-generated method stub
		this.clients.put(client.getClientId(), client);
		return client;
	}

	@Override
	public void removeClient(Long clientId) throws InstanceNotFoundException {
		// TODO Auto-generated method stub
		clients.remove(clientId);
		
	}

	@Override
	public void updateClient(Client client) throws InputValidationException {
		// TODO Auto-generated method stub
		clients.replace(client.getClientId(), client);
		
	}

	@Override
	public Client findClient(Long clientId) throws InstanceNotFoundException {
		// TODO Auto-generated method stub
		return clients.get(clientId);
	}

	@Override
	public Client findClient(short DNI) throws InstanceNotFoundException {
		// TODO Auto-generated method stub
		for (Entry<Long, Client> client : clients.entrySet()) {
			if(client.getValue().getDNI() == DNI)
				return client.getValue();
		}
		return null;
	}


	@Override
	public List<Client> findClients(String keywords) {
		// TODO Auto-generated method stub
		List<Client> findClients = new ArrayList<Client>();
		for (Entry<Long, Client> client : clients.entrySet()) {
			if(client.getValue().getName().contains(keywords)){
				findClients.add(client.getValue());
			}
			
		}
		return findClients;
	}
	
	@Override
	public List<Client> findClients(String keywords, int index, int numRows) {
		// TODO Auto-generated method stub
		return findClients(keywords).subList(index, numRows);
	}

	@Override
	public void makeCall(Long clientId, Calendar dateCall, short duration,
			enumType type, short destPhone) throws InstanceNotFoundException,
			InputValidationException {
		// TODO Auto-generated method stub
		clients.get(clientId).addCall(new Call(clientId, dateCall, duration, type, destPhone));
		
	}

	@Override
	public void changeCallState(Long clientId, Calendar month, enumState state)
			throws CallStateException {
		// TODO Auto-generated method stub
		
		for (Call call : clients.get(clientId).getCallList()) {
			if(call.getState().ordinal() == state.ordinal() -1){
				call.setState(state);
			}
			else throw new CallStateException(clientId, call.getCallId());
		}
		
		
	}

	@Override
	public List<Call> findCalls(Long clientId, Calendar month)
			throws CallStateException, InstanceNotFoundException {
		// TODO Auto-generated method stub
		List<Call> findCalls = new ArrayList<Call>();
		for (Call call : clients.get(clientId).getCallList()) {
			if(call.getDateCall().MONTH == month.MONTH && call.getDateCall().MONTH < Calendar.MONTH){
				findCalls.add(call);
			}
		}
		
		return findCalls;
	}

	@Override
	public List<Call> findCalls(Long clientId, Calendar initDate,
			Calendar endDate) throws CallStateException,
			InstanceNotFoundException {
		// TODO Auto-generated method stub
		List<Call> findCalls = new ArrayList<Call>();
		for (Call call : clients.get(clientId).getCallList()) {
			if(call.getDateCall().compareTo(initDate) >= 0 && call.getDateCall().compareTo(initDate) <= 0){
				findCalls.add(call);
			}
		}
		return findCalls;
	}

	@Override
	public List<Call> findCalls(Long clientId, Calendar initDate,
			Calendar endDate, enumType type) throws CallStateException,
			InstanceNotFoundException {
		// TODO Auto-generated method stub
		List<Call> findCalls = new ArrayList<Call>();
		for (Call call : clients.get(clientId).getCallList()) {
			if(call.getDateCall().compareTo(initDate) >= 0 && call.getDateCall().compareTo(initDate) <= 0
					&& call.getType().compareTo(type) == 0){
				findCalls.add(call);
			}
		}
		return findCalls;
	}

	@Override
	public List<Call> findCalls(Long clientId, Calendar initDate,
			Calendar endDate, int index, int numRows)
			throws CallStateException, InstanceNotFoundException {
		// TODO Auto-generated method stub
		return findCalls(clientId, initDate, endDate).subList(index, numRows);
	}

	@Override
	public List<Call> findCalls(Long clientId, Calendar initDate,
			Calendar endDate, enumType type, int index, int numRows)
			throws CallStateException, InstanceNotFoundException {
		// TODO Auto-generated method stub
		return findCalls(clientId, initDate, endDate, type).subList(index, numRows);
	}



}
