package es.udc.rs.app.client.service;

import java.util.Calendar;
import java.util.List;

import es.udc.rs.app.client.dto.CallDto;
import es.udc.rs.app.client.dto.ClientDetailsDto;
import es.udc.rs.app.client.dto.ClientDto;
import es.udc.rs.app.constants.ModelConstants.enumState;
import es.udc.rs.app.constants.ModelConstants.enumType;
import es.udc.rs.app.exceptions.CallStateException;
import es.udc.rs.app.exceptions.MonthExpirationException;
import es.udc.rs.app.exceptions.RemoveClientException;
import es.udc.ws.util.exceptions.InputValidationException;
import es.udc.ws.util.exceptions.InstanceNotFoundException;

public interface ClientService {
	public ClientDetailsDto addClient(ClientDetailsDto client) throws InputValidationException;

    public void removeClient(Long clientId) throws InstanceNotFoundException, RemoveClientException;

    public void updateClient(ClientDetailsDto client) throws InputValidationException, InstanceNotFoundException;

    public ClientDto findClient(Long clientId) throws InstanceNotFoundException;
    
    public ClientDto findClient(String DNI) throws InstanceNotFoundException;
    
    public List<ClientDto> findClients(String keywords, int index, int numRows);
    
    public Long makeCall(Long clientId, Calendar date, Integer duration, enumType type, String destPhone) throws InstanceNotFoundException, InputValidationException;
    
    public void changeCallState(Long clientId, Calendar date , enumState state) throws CallStateException, InstanceNotFoundException, MonthExpirationException;
    
    public List<CallDto> findCalls(Long clientId, Calendar month, int index, int numRows) throws CallStateException, InstanceNotFoundException;
    
    public List<CallDto> findCalls(Long clientId, Calendar initDate, Calendar endDate, int index, int numRows) throws  InstanceNotFoundException;

    public List<CallDto> findCalls(Long clientId, Calendar initDate, Calendar endDate, int index, int numRows, enumType type) throws CallStateException, InstanceNotFoundException;

    public String getClientUrl(Long saleId) throws InstanceNotFoundException;
}
