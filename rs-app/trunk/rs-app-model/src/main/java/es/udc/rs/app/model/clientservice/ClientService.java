package es.udc.rs.app.model.clientservice;


import java.util.Calendar;
import java.util.List;

import es.udc.rs.app.exceptions.CallStateException;
import es.udc.rs.app.exceptions.MonthExpirationException;
import es.udc.rs.app.exceptions.RemoveClientException;
import es.udc.rs.app.model.call.Call;
import es.udc.rs.app.model.client.Client;
import es.udc.rs.app.model.util.ModelConstants;
import es.udc.rs.app.model.util.ModelConstants.enumType;
import es.udc.ws.util.exceptions.InputValidationException;
import es.udc.ws.util.exceptions.InstanceNotFoundException;

public interface ClientService {

    public Client addClient(Client client) throws InputValidationException;

    public void removeClient(Long clientId) throws InstanceNotFoundException, RemoveClientException;

    public void updateClient(Client client) throws InputValidationException, InstanceNotFoundException;

    public Client findClient(Long clientId) throws InstanceNotFoundException;
    
    public Client findClient(String DNI) throws InstanceNotFoundException;
    
    public List<Client> findClients(String keywords, int index, int numRows);
    
    public void makeCall(Long clientId, Calendar date, Integer duration, ModelConstants.enumType type, Integer destPhone) throws InstanceNotFoundException, InputValidationException;
    
    public void changeCallState(Long clientId, Calendar date ,ModelConstants.enumState state) throws CallStateException, InstanceNotFoundException, MonthExpirationException;
    
    public List<Call> findCalls(Long clientId, Calendar month, int index, int numRows) throws CallStateException, InstanceNotFoundException;
    
    public List<Call> findCalls(Long clientId, Calendar initDate, Calendar endDate, int index, int numRows) throws  InstanceNotFoundException;

    public List<Call> findCalls(Long clientId, Calendar initDate, Calendar endDate, int index, int numRows, ModelConstants.enumType type) throws CallStateException, InstanceNotFoundException;

    
}
