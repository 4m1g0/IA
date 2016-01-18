package es.udc.rs.app.model.clientservice;


import java.util.Calendar;
import java.util.List;

import es.udc.rs.app.constants.ModelConstants.enumState;
import es.udc.rs.app.constants.ModelConstants.enumType;
import es.udc.rs.app.exceptions.CallStateException;
import es.udc.rs.app.exceptions.MonthExpirationException;
import es.udc.rs.app.exceptions.RemoveClientException;
import es.udc.rs.app.model.call.Call;
import es.udc.rs.app.model.client.Client;
import es.udc.ws.util.exceptions.InputValidationException;
import es.udc.ws.util.exceptions.InstanceNotFoundException;

public interface ClientService {

    public Client addClient(Client client) throws InputValidationException;

    public void removeClient(Long clientId) throws InstanceNotFoundException, RemoveClientException;

    public void updateClient(Client client) throws InputValidationException, InstanceNotFoundException;

    public Client findClient(Long clientId) throws InstanceNotFoundException;
    
    public Client findClient(String DNI) throws InstanceNotFoundException;
    
    public List<Client> findClients(String keywords, int index, int numRows);
    
    public Call makeCall(Long clientId, Calendar date, Integer duration, enumType type, String destPhone) throws InstanceNotFoundException, InputValidationException;
    
    public void changeCallState(Long clientId, Calendar date , enumState state) throws CallStateException, InstanceNotFoundException, MonthExpirationException;
    
    public List<Call> findCalls(Long clientId, Calendar month, int index, int numRows) throws CallStateException, InstanceNotFoundException;
    
    public List<Call> findCalls(Long clientId, Calendar initDate, Calendar endDate, int index, int numRows) throws  InstanceNotFoundException;

    public List<Call> findCalls(Long clientId, Calendar initDate, Calendar endDate, int index, int numRows, enumType type) throws CallStateException, InstanceNotFoundException;

    
}
