package es.udc.rs.app.model.clientservice;

import java.util.ArrayList;

// TODO: excepciones permanentes: inputvalidation y error de cliente ya tiene llamadas al borrarlo!
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import es.udc.rs.app.exceptions.CallStateException;
import es.udc.rs.app.exceptions.RemoveClientException;
import es.udc.rs.app.model.call.Call;
import es.udc.rs.app.model.client.Client;
import es.udc.rs.app.model.util.ModelConstants.enumState;
import es.udc.rs.app.model.util.ModelConstants.enumType;
import es.udc.ws.util.exceptions.InputValidationException;
import es.udc.ws.util.exceptions.InstanceNotFoundException;

public class ClientServiceImpl implements ClientService {
	
	private long countClient = 0;
	private long countCall = 0;
	
	private LinkedHashMap<Long, Client> clients = new LinkedHashMap<Long, Client>();
	private LinkedHashMap<Long, Call> calls = new LinkedHashMap<Long, Call>();
	
	
	public ClientServiceImpl() {
		
	}
	
	public synchronized long incrementClient(){
		return countClient++;	
	}
	
	public synchronized long incrementCall(){
		return countCall++;	
	}
	
	private Client validateClient(Client client) throws InputValidationException {
		if(client.getDNI().length() != 9 && (client.getPhone().toString().length() != 9)){
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
	public List<Client> findClients(String keywords) {
		List<Client> findClients = new ArrayList<Client>();
		for (Client client : clients.values()) {
			if(client.getName().contains(keywords)){
				findClients.add(client);
			}
		}
		return findClients;
	}
	
	@Override
	public List<Client> findClients(String keywords, int index, int numRows) {
		List<Client> retorno = findClients(keywords);
		if(index+numRows > retorno.size()){
			return retorno.subList(index, retorno.size());
		}
		return retorno.subList(index, index + numRows);
	}

	@Override
	public void makeCall(Long clientId, Calendar date, Integer duration, enumType type, Integer destPhone) 
			throws InstanceNotFoundException, InputValidationException { // FIXME: esto nunca lanza inputvalidation y deberia¿?!!
		Client c = clients.get(clientId);
		
		if(c == null){
			throw new InstanceNotFoundException(clientId, clientId.toString());
		}
		
		Call call = new Call(clientId, date, duration, type, destPhone);
		call.setCallId(incrementCall());
		calls.put(call.getCallId(), call);
	}

	@Override
	public void changeCallState(Long clientId, Calendar date, enumState state) throws CallStateException, InstanceNotFoundException {
		boolean found = false;
		for (Call call : calls.values()) {
			if (!call.getClientId().equals(clientId) || call.getDateCall().YEAR != date.YEAR || call.getDateCall().MONTH != date.MONTH) {
				continue;
			}
			
			found = true;
			if(call.getState().ordinal() == state.ordinal() -1) {
				call.setState(state);
			}
			else throw new CallStateException(clientId, call.getCallId());
		}
		
		if (!found) // TODO: o deberia lanzarlo solo si no existe el cliente??
			throw new InstanceNotFoundException(clientId, clientId.toString());
	}

	@Override
	public List<Call> findCalls(Long clientId, Calendar month) throws InstanceNotFoundException { // TODO: añadir año también y comprobar que estan en estado pendiente, si no llamar excepcion
		List<Call> findCalls = new ArrayList<Call>();
		for (Call call : calls.values()) {
			if (!call.getClientId().equals(clientId)) {
				continue;
			}
			
			if(call.getDateCall().get(Calendar.MONTH) == month.get(Calendar.MONTH)){
				findCalls.add(call);
			}
		}
		
		return findCalls;
	}

	@Override
	public List<Call> findCalls(Long clientId, Calendar initDate,
			Calendar endDate) throws InstanceNotFoundException {
		List<Call> findCalls = new ArrayList<Call>();
		for (Call call : calls.values()) {
			if (!call.getClientId().equals(clientId)) {
				continue;
			}
			
			if(call.getDateCall().get(Calendar.MONTH) > initDate.get(Calendar.MONTH)){
				if(call.getDateCall().get(Calendar.MONTH) < endDate.get(Calendar.MONTH)){
					findCalls.add(call);	
				}
			}
		}
		return findCalls;
	}

	@Override
	public List<Call> findCalls(Long clientId, Calendar initDate,
			Calendar endDate, enumType type) throws CallStateException,
			InstanceNotFoundException {
		List<Call> findCalls = new ArrayList<Call>();
		for (Call call : calls.values()) {
			if (!call.getClientId().equals(clientId)) {
				continue;
			}
			
			if(call.getDateCall().get(Calendar.MONTH) > initDate.get(Calendar.MONTH)){
				if(call.getDateCall().get(Calendar.MONTH) < endDate.get(Calendar.MONTH)){
					if(type == call.getType())
						findCalls.add(call);	
				}
			}
		}
		return findCalls;
	}

	@Override
	public List<Call> findCalls(Long clientId, Calendar initDate,
			Calendar endDate, int index, int numRows)
			throws InstanceNotFoundException {
		List<Call> retorno = findCalls(clientId, initDate, endDate);
		if(index+numRows > retorno.size()){
			return retorno.subList(index, retorno.size());
		}
		return retorno.subList(index, index + numRows);
	}

	@Override
	public List<Call> findCalls(Long clientId, Calendar initDate, Calendar endDate, enumType type, 
			int index, int numRows) throws CallStateException, InstanceNotFoundException { //TODO: no hace falta callStateexception
		List<Call> retorno = findCalls(clientId, initDate, endDate, type);
		if(index+numRows > retorno.size()){
			return retorno.subList(index, retorno.size());
		}
		return retorno.subList(index, index + numRows);
	}
}
