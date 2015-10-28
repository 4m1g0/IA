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
	
	private static Map<Long, Client> clients = new HashMap<Long, Client>();
	
	
	public ClientServiceImpl() {
		
	}
	private void validateClient(Client client) throws InputValidationException {
		if(client.getDNI().length() != 9 && (client.getPhone().toString().length() != 9)){
			throw new InputValidationException(client.getDNI());
		}

		
	}

	@Override
	public Client addClient(Client client) throws InputValidationException {
		validateClient(client);
		if(this.clients.put(client.getClientId(), client) == null)
			return client;
		return null;
	}

	@Override
	public void removeClient(Long clientId) throws InstanceNotFoundException, CallStateException {
		if (clients.get(clientId).getCallList().isEmpty())
			clients.remove(clientId);
		else
			throw new CallStateException(clientId, clients.get(clientId).getCallList().get(0).getCallId());
	}

	@Override
	public void updateClient(Client client) throws InputValidationException, InstanceNotFoundException{
		if (clients.replace(client.getClientId(), client) == null){
			throw new InstanceNotFoundException(client, client.toString());
		}
	}

	@Override
	public Client findClient(Long clientId) throws InstanceNotFoundException {
		return clients.get(clientId);
	}

	@Override
	public Client findClient(String DNI) throws InstanceNotFoundException {
		for (Entry<Long, Client> client : clients.entrySet()) {
			if(client.getValue().getDNI().contentEquals(DNI))
				return client.getValue();
		}
		throw new InstanceNotFoundException(null,DNI);
	}


	@Override
	public List<Client> findClients(String keywords) {
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
		List<Client> retorno = findClients(keywords);
		if(index+numRows > retorno.size()){
			return retorno.subList(index, retorno.size());
		}
		return retorno.subList(index, index + numRows);
	}

	@Override
	public void makeCall(Long clientId, Integer duration, enumType type, Integer destPhone) 
			throws InstanceNotFoundException, InputValidationException {
		Client c = clients.get(clientId);
		if(c == null){
			throw new InstanceNotFoundException(clientId, clientId.toString());
		}
		c.addCall(new Call(clientId, Calendar.getInstance(), duration, type, destPhone));
		
	}

	@Override
	public void changeCallState(Long clientId, Calendar month, enumState state) throws CallStateException {
		for (Call call : clients.get(clientId).getCallList()) {
			if(call.getState().ordinal() == state.ordinal() -1){
				call.setState(state);
			}
			else throw new CallStateException(clientId, call.getCallId());
		}
	}

	@Override
	public List<Call> findCalls(Long clientId, Calendar month) throws InstanceNotFoundException {
		List<Call> findCalls = new ArrayList<Call>();
		for (Call call : clients.get(clientId).getCallList()) {
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
		for (Call call : clients.get(clientId).getCallList()) {
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
		for (Call call : clients.get(clientId).getCallList()) {
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
			int index, int numRows) throws CallStateException, InstanceNotFoundException {
		List<Call> retorno = findCalls(clientId, initDate, endDate, type);
		if(index+numRows > retorno.size()){
			return retorno.subList(index, retorno.size());
		}
		return retorno.subList(index, index + numRows);
	}



}
