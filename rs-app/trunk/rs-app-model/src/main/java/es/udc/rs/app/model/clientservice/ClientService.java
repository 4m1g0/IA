package es.udc.rs.app.model.clientservice;


import java.util.Calendar;
import java.util.List;

import es.udc.rs.app.exceptions.CallStateException;
import es.udc.rs.app.model.call.Call;
import es.udc.rs.app.model.client.Client;
import es.udc.rs.app.model.util.ModelConstants;
import es.udc.ws.util.exceptions.InputValidationException;
import es.udc.ws.util.exceptions.InstanceNotFoundException;

public interface ClientService {

    public Client addClient(Client client) throws InputValidationException;

    public void removeClient(Long clientId) throws InstanceNotFoundException;

    public void updateClient(Client client) throws InputValidationException, InstanceNotFoundException;

    public Client findClient(Long clientId) throws InstanceNotFoundException;
    
    public Client findClient(String DNI) throws InstanceNotFoundException;
    
    public List<Client> findClients(String keywords);
    
    public List<Client> findClients(String keywords, int index, int numRows);
    
    public void makeCall(Long clientId, Integer duration, ModelConstants.enumType type, Integer destPhone) 
    		throws InstanceNotFoundException, InputValidationException;
    
    public void changeCallState(Long clientId, Calendar month ,ModelConstants.enumState state) throws CallStateException;
    
    public List<Call> findCalls(Long clientId, Calendar month) 
    		throws CallStateException, InstanceNotFoundException;

    public List<Call> findCalls(Long clientId, Calendar initDate, Calendar endDate) throws InstanceNotFoundException;

    public List<Call> findCalls(Long clientId, Calendar initDate, Calendar endDate, ModelConstants.enumType type)
    		throws CallStateException, InstanceNotFoundException;
    
    public List<Call> findCalls(Long clientId, Calendar initDate, Calendar endDate, int index, int numRows) 
    		throws  InstanceNotFoundException;

    public List<Call> findCalls(Long clientId, Calendar initDate, Calendar endDate, ModelConstants.enumType type,
    		int index, int numRows) throws CallStateException, InstanceNotFoundException;

    
}
