package es.udc.rs.app.model.clientservice;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Calendar;

import org.junit.BeforeClass;
import org.junit.Test;

import es.udc.rs.app.constants.EnumState;
import es.udc.rs.app.constants.EnumType;
import es.udc.rs.app.exceptions.CallStateException;
import es.udc.rs.app.exceptions.MonthExpirationException;
import es.udc.rs.app.exceptions.RemoveClientException;
import es.udc.rs.app.model.call.Call;
import es.udc.rs.app.model.client.Client;
import es.udc.ws.util.exceptions.InputValidationException;
import es.udc.ws.util.exceptions.InstanceNotFoundException;

public class ClientServiceTest {
	
	
	private static ClientService clientService = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		clientService = ClientServiceFactory.getService();
		Client client1 = new Client("Paco", "45777777C", "Calle pepito 22", "678956745");
		Client client2 = new Client("Pepe", "77775437A", "Calle pepito 23", "678922222");
		Client client3 = new Client("Ramon", "77273477R", "Calle pepito 25","678953453");
		Client client4 = new Client("Tito", "66673477R", "Calle pepito 29", "666953453");
		
		try {
			clientService.addClient(client1);
			clientService.addClient(client2);
			clientService.addClient(client3);
			clientService.addClient(client4);
			Calendar cal = Calendar.getInstance();
			Calendar cal2 = Calendar.getInstance();
			cal.set(Calendar.MONTH, 4);

			clientService.makeCall(client1.getClientId(), cal, 234, EnumType.LOCAL,"65943902");
			clientService.makeCall(client1.getClientId(), cal, 244, EnumType.LOCAL,"65954912");
			clientService.makeCall(client1.getClientId(), cal, 244, EnumType.LOCAL,"65954912");
			cal2.set(Calendar.MONTH, 6);
			clientService.makeCall(client1.getClientId(), cal2, 244, EnumType.LOCAL,"65954912");
		} catch (Exception e) {
			throw new InputValidationException("error");
			
		}

	}

	@Test
	public void testAddClient() {
		Client client = new Client("rosa", "72734577R", "Calle pepito 25","678953453");
		Client addedClient;
		try {
			addedClient = clientService.addClient(client);
			assertEquals(addedClient, client);
		} catch (InputValidationException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Test(expected = InputValidationException.class)
	public void testAddClientException() throws InputValidationException {
		Client client = new Client("rosa", "", "Calle pepito 25","953453");
		clientService.addClient(client);
	}

	@Test
	public void testRemoveClient() throws InputValidationException, RemoveClientException{
		Client client = new Client("alberto", "12345678B", "asdfff", "654321233");
		try {
			clientService.addClient(client);
			clientService.removeClient(client.getClientId());
		} catch (InstanceNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void testUpdateClient() throws InputValidationException,
	InstanceNotFoundException{
		
		Client client = new Client("alberto", "12345678W", "asdfff", "654321265");

		clientService.addClient(client);
		client.setDNI("87654321A");
		client.setAddress("fffff");
		clientService.updateClient(client);
		Client result = clientService.findClient(client.getClientId());
		assertEquals(result, client);
	}

	@Test
	public void testFindClientId() throws InputValidationException, InstanceNotFoundException, RemoveClientException{
		Client client = new Client("proba1", "67654312E", "calle 21", "654321234");
		try{
			clientService.addClient(client);
			Client c = clientService.findClient(client.getClientId());
			assertEquals(c, client);
		} finally{
			clientService.removeClient(client.getClientId());
		}
	}



	@Test
	public void testFindClientDNI() throws InstanceNotFoundException {
		Client client = clientService.findClient("45777777C");
		assertEquals(client.getDNI(), "45777777C");
	}

	@Test
	public void testFindClientsString() throws InputValidationException, InstanceNotFoundException, RemoveClientException {
		Client c1 = new Client("carlos", "12121212E", "calle x", "565434567");
		Client c2 = new Client("carla", "12121212E", "calle x", "565434567");
		try {
			
			clientService.addClient(c1);
			clientService.addClient(c2);
			assertEquals(clientService.findClients("car", 0, 99999).size(), 2);	
		} finally{
			clientService.removeClient(c1.getClientId());
			clientService.removeClient(c2.getClientId());
		}
		
	}

	@Test
	public void testFindClientsStringRange() throws InputValidationException, InstanceNotFoundException, RemoveClientException {
		Client c1 = new Client("carlos", "12121212E", "calle x", "565434567");
		Client c2 = new Client("carla", "12121212E", "calle x", "565434567");
		try {
			
			clientService.addClient(c1);
			clientService.addClient(c2);
			assertEquals(clientService.findClients("car", 1, 4).size(), 1);
			assertEquals(clientService.findClients("car", 0, 1).size(), 1);	
			assertEquals(clientService.findClients("car", 0, 3).size(), 2);	
		} finally{
			clientService.removeClient(c1.getClientId());
			clientService.removeClient(c2.getClientId());
		}
	}

	@Test
	public void testMakeCall() throws InstanceNotFoundException, InputValidationException, CallStateException {
		
		try {
			Client c1 = clientService.findClient("77775437A");
			Calendar date = Calendar.getInstance();
			clientService.makeCall(c1.getClientId(), date, 234, EnumType.LOCAL,"65943902");
			
			assertEquals(clientService.findCalls(c1.getClientId(), date).get(0).getDestPhone(), "65943902");
			
		} finally {
			
		}
		
	}

	@Test(expected = MonthExpirationException.class)
	public void testChangeCallState() throws InstanceNotFoundException, CallStateException, InputValidationException, MonthExpirationException {
		Client c = clientService.findClient("66673477R");
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, 7);

		clientService.makeCall(c.getClientId(), cal, 234, EnumType.LOCAL,"65943902");
		clientService.makeCall(c.getClientId(), cal, 244, EnumType.LOCAL,"65954912");
		clientService.changeCallState(c.getClientId(), cal, EnumState.BILLED);
		// la busqueda debe lanzar excepcion si el estado se ha cambiado correctamente
		clientService.findCalls(c.getClientId(), cal);
	}
	
	@Test(expected = MonthExpirationException.class)
	public void testNotChangeState() throws CallStateException, InstanceNotFoundException, MonthExpirationException {
		Client c = clientService.findClient("66673477R");
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, 7);
		clientService.changeCallState(c.getClientId(), cal, EnumState.PENDING);
	}

	@Test
	public void testFindCalls() throws InstanceNotFoundException, CallStateException {
		Client c1 = clientService.findClient("45777777C");
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, 4);
		List<Call> calls = clientService.findCalls(c1.getClientId(),cal);
		assertEquals(3, calls.size());
		cal.set(Calendar.MONTH, 6);
		calls = clientService.findCalls(c1.getClientId(),cal);
		assertEquals(1, calls.size());
		
	}

	@Test
	public void testFindCallsRangeTime() throws InstanceNotFoundException{
		Client c1 = clientService.findClient("45777777C");
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.set(Calendar.MONTH, 3);
		cal2.set(Calendar.MONTH, 11);
		List<Call> calls = clientService.findCalls(c1.getClientId(), cal1, cal2, 0, 99999);
		assertEquals(4, calls.size());
		
		
	}

	@Test
	public void testFindCallsRangeTimeType() throws InstanceNotFoundException, CallStateException{
		Client c1 = clientService.findClient("45777777C");
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.set(Calendar.MONTH, 3);
		cal2.set(Calendar.MONTH, 11);
		List<Call> calls = clientService.findCalls(c1.getClientId(), cal1, cal2, 0, 9999, EnumType.LOCAL);
		assertEquals(6, calls.size());
	}

	@Test
	public void testFindCallsIndex() throws InstanceNotFoundException{
		Client c1 = clientService.findClient("45777777C");
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.set(Calendar.MONTH, 3);
		cal2.set(Calendar.MONTH, 11);
		List<Call> calls = clientService.findCalls(c1.getClientId(), cal1, cal2, 1, 2);
		assertEquals(2, calls.size());
	}

	@Test
	public void testFindCallsIndexType() throws InstanceNotFoundException, CallStateException{
		Client c1 = clientService.findClient("45777777C");
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.set(Calendar.MONTH, 3);
		cal2.set(Calendar.MONTH, 11);
		List<Call> calls = clientService.findCalls(c1.getClientId(), cal1, cal2, 1, 2, EnumType.LOCAL);
		assertEquals(2, calls.size());
	}
	
	@Test(expected = RemoveClientException.class)
	public void testRemoveClientWithCalls() throws InstanceNotFoundException, RemoveClientException{
		Client c1 = clientService.findClient("45777777C");
		clientService.removeClient(c1.getClientId());
	}
}
