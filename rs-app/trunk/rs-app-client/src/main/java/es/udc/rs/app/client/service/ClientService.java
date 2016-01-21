package es.udc.rs.app.client.service;

import java.util.List;

import es.udc.rs.app.client.dto.CallDetailsDto;
import es.udc.rs.app.client.dto.CallListIntervalDto;
import es.udc.rs.app.client.dto.ClientDetailsDto;
import es.udc.rs.app.client.dto.ClientListIntervalDto;
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

    public ClientDetailsDto findClient(Long clientId) throws InstanceNotFoundException;
    
    public ClientDetailsDto findClient(String DNI) throws InstanceNotFoundException;
    
    public ClientListIntervalDto findClients(String keywords, int index, int numRows);
    
    public Long makeCall(Long clientId, String date, Integer duration, String type, String destPhone) throws InstanceNotFoundException, InputValidationException;
    
    public void changeCallState(Long clientId, String month, String year , String state) throws CallStateException, InstanceNotFoundException, MonthExpirationException;
    
    public List<CallDetailsDto> findCallsToBill(Long clientId, String month, String year ,int index, int numRows ) throws CallStateException, InstanceNotFoundException;
    
    public CallListIntervalDto findCalls(Long clientId, String initDate, String endDate, int index, int numRows) throws  InstanceNotFoundException;

    public CallListIntervalDto findCalls(Long clientId, String initDate, String endDate, int index, int numRows, String type) throws CallStateException, InstanceNotFoundException;
}
