package es.udc.rs.app.model.clientservice;

import java.util.ArrayList;

import java.util.Calendar;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import es.udc.rs.app.constants.ModelConstants.enumState;
import es.udc.rs.app.constants.ModelConstants.enumType;
import es.udc.rs.app.exceptions.CallStateException;
import es.udc.rs.app.exceptions.MonthExpirationException;
import es.udc.rs.app.exceptions.RemoveClientException;
import es.udc.rs.app.model.call.Call;
import es.udc.rs.app.model.client.Client;
import es.udc.ws.util.exceptions.InputValidationException;
import es.udc.ws.util.exceptions.InstanceNotFoundException;

public class ClientServiceImpl implements ClientService {
	
	private long countClient = 0;
	private long countCall = 0;
	
	private LinkedHashMap<Long, Client> clients = new LinkedHashMap<Long, Client>();
	private LinkedHashMap<Long, Call> calls = new LinkedHashMap<Long, Call>();
	
	
	public ClientServiceImpl() {
		try{
			addClient(new Client("pepe", "234345546", "caslle del pozo", "234345546"));
			makeCall(0L, Calendar.getInstance(), 50, enumType.LOCAL, "696969699");
			
		}catch (Exception e){
			
		}
	}
	
	public synchronized long incrementClient(){
		return countClient++;	
	}
	
	public synchronized long incrementCall(){
		return countCall++;	
	}
	
	private Client validateClient(Client client) throws InputValidationException {
		if(client.getDNI().length() != 9 || (client.getPhone().length() != 9)){
			throw new InputValidationException(client.getDNI());
		}
		
		return client;
	}

	@Override
	public Client addClient(Client client) throws InputValidationException {
		validateClient(client);
		client.setClientId(incrementClient());
		
		if(clients.put(client.getClientId(), client) == null)
			return client;
		
		return null;
	}
	
	// only for tests
	public void removeCallsFromClient(Long clientId) {
		Iterator<Map.Entry<Long, Call>> it = calls.entrySet().iterator();
		while (it.hasNext()){
			Map.Entry<Long, Call> entry = it.next();
			if (entry.getKey().equals(clientId)){
				// Use iterator to remove instead of map, in order not to alter the loop sequence
				it.remove();
			}
		}
	}
	
	// only for tests
	public void removeCall(Long callId){
		calls.remove(callId);
	}

	@Override
	public void removeClient(Long clientId) throws InstanceNotFoundException, RemoveClientException {
		for (Call call : calls.values()){
			if (call.getClientId().equals(clientId)){
				throw new RemoveClientException(clientId);
			}
		}
		
		clients.remove(clientId);
	}

	@Override
	public void updateClient(Client client) throws InputValidationException, InstanceNotFoundException{
		if (clients.replace(client.getClientId(), validateClient(client)) == null){
			throw new InstanceNotFoundException(client, client.toString());
		}
	}

	@Override
	public Client findClient(Long clientId) throws InstanceNotFoundException {
		if(clients.get(clientId) == null){
			throw new InstanceNotFoundException(clientId, clientId.toString());
		}
		return clients.get(clientId);
	}

	@Override
	public Client findClient(String DNI) throws InstanceNotFoundException {
		for (Client client : clients.values()) {
			if(client.getDNI().contentEquals(DNI))
				return client;
		}
		
		throw new InstanceNotFoundException(null,DNI);
	}
	
	@Override
	public List<Client> findClients(String keywords, int index, int numRows) {
		List<Client> findClients = new ArrayList<Client>();
		int i = 0;
		for (Client client : clients.values()) {
			if(client.getName().contains(keywords) && i++ >= index){
				findClients.add(client);
				if (findClients.size() >= numRows)
					break;
			}
		}
		return findClients;
	}

	@Override
	public Call makeCall(Long clientId, Calendar date, Integer duration, enumType type, String destPhone) 
			throws InstanceNotFoundException, InputValidationException { 
		Client c = clients.get(clientId);
		
		if(c == null){
			throw new InstanceNotFoundException(clientId, clientId.toString());
		}
		
		Call call = new Call(clientId, date, duration, type, destPhone);
		call.setCallId(incrementCall());
		calls.put(call.getCallId(), call);
		
		return call;
	}

	@Override
	public void changeCallState(Long clientId, Calendar date, enumState state) throws CallStateException, InstanceNotFoundException, MonthExpirationException {
		findClient(clientId); // throws exception if client doesn't exist
		Calendar today = Calendar.getInstance();
		today.set(Calendar.DAY_OF_MONTH, 0);
		if (date.after(today)) {
			throw new MonthExpirationException(date);
		}
		for (Call call : calls.values()) {
			if (!call.getClientId().equals(clientId) || call.getDateCall().get(Calendar.YEAR) != date.get(Calendar.YEAR) 
					|| call.getDateCall().get(Calendar.MONTH) != date.get(Calendar.MONTH)) {
				continue;
			}
			
			if(call.getState().ordinal() == state.ordinal() -1) {
				call.setState(state);
			}
			else 
				throw new CallStateException(clientId, call.getCallId());
		}
	}

	@Override
	public List<Call> findCalls(Long clientId, Calendar date, int index, int numRows) throws InstanceNotFoundException, CallStateException {
		findClient(clientId); // throws exception if client doesn't exist
		List<Call> findCalls = new ArrayList<Call>();
		
		int i = 0;
		for (Call call : calls.values()) {
			if (!call.getClientId().equals(clientId)) {
				continue;
			}
			
			if(call.getDateCall().get(Calendar.MONTH) == date.get(Calendar.MONTH) && call.getDateCall().get(Calendar.YEAR) == date.get(Calendar.YEAR) && i++ >= index){
				if (call.getState() != enumState.PENDING)
					throw new CallStateException(clientId, call.getCallId());
				findCalls.add(call);
				if (findCalls.size() >= numRows)
					break;
			}
		}
		
		return findCalls;
	}

	@Override
	public List<Call> findCalls(Long clientId, Calendar initDate, Calendar endDate, int index, int numRows) throws InstanceNotFoundException {
		return findCalls(clientId, initDate, endDate, index, numRows, null);
	}

	@Override
	public List<Call> findCalls(Long clientId, Calendar initDate, Calendar endDate, int index, int numRows, enumType type) throws InstanceNotFoundException { 
		List<Call> findCalls = new ArrayList<Call>();
		
		int i = 0;
		for (Call call : calls.values()) {
			if (call.getDateCall().after(initDate) && call.getDateCall().before(endDate) && i++ >= index && (type == null || call.getType() == type)) {
				findCalls.add(call);
				if (findCalls.size() >= numRows)
					break;
			}
		}
		
		return findCalls;
	}
}
