package es.udc.rs.app.model.clientservice;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import es.udc.rs.app.exceptions.CallStateException;
import es.udc.rs.app.model.call.Call;
import es.udc.rs.app.model.client.Client;
import es.udc.rs.app.model.util.ModelConstants.enumState;
import es.udc.rs.app.model.util.ModelConstants.enumType;
import es.udc.ws.util.exceptions.InputValidationException;
import es.udc.ws.util.exceptions.InstanceNotFoundException;

public class ClientServiceTest {
	
	
	private static ClientService clientService = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		clientService = ClientServiceFactory.getService();
		Client client1 = new Client("Paco", "45777777C", "Calle pepito 22", 678956745);
		Client client2 = new Client("Pepe", "77775437A", "Calle pepito 23", 678922222);
		Client client3 = new Client("Ramon", "77273477R", "Calle pepito 25",678953453);
		Client client4 = new Client("Tito", "66673477R", "Calle pepito 29",666953453);
		
		try {
			clientService.addClient(client1);
			clientService.addClient(client2);
			clientService.addClient(client3);
			clientService.addClient(client4);
			Calendar cal = Calendar.getInstance();
			Calendar cal2 = Calendar.getInstance();
			cal.set(Calendar.MONTH, 4);
			List<Call> callist = new ArrayList<Call>();
			callist.add(new Call(client1.getClientId(), cal, 234, enumType.LOCAL,65943902));
			callist.add(new Call(client1.getClientId(), cal, 244, enumType.LOCAL,65954912));
			callist.add(new Call(client1.getClientId(), cal, 244, enumType.LOCAL,65954912));
			cal2.set(Calendar.MONTH, 6);
			callist.add(new Call(client1.getClientId(), cal2, 244, enumType.LOCAL,65954912));
			client1.setCallList(callist);
		} catch (Exception e) {
			throw new InputValidationException("error");
			
		}

	}

	@Before
	public void setUp() throws Exception {
		
	
	}

	
	private Client getValidClient(String name)
	{
		return new Client(name, "45777777C", "Calle pepito 22", 678956745);
	}
	
	private Client getValidClient()
	{
		return getValidClient("Paco");
	}

	@Test
	public void testAddClient() {
		Client client = new Client("rosa", "72734577R", "Calle pepito 25",678953453);
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
		Client client = new Client("rosa", "", "Calle pepito 25",953453);
		clientService.addClient(client);
	}

	@Test
	public void testRemoveClient() throws InputValidationException, CallStateException{
		Client client = new Client("alberto", "12345678B", "asdfff", 654321233);
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
		
		Client client = new Client("alberto", "12345678W", "asdfff", 654321265);
		try {
			clientService.addClient(client);
			client.setDNI("87654321A");
			client.setAddress("fffff");
			clientService.updateClient(client);
			Client result = clientService.findClient(client.getClientId());
			assertEquals(result, client);
		} finally{
			//clientService.removeClient(client.getClientId());
			
		}
	}

	@Test
	public void testFindClientId() throws InputValidationException, InstanceNotFoundException, CallStateException{
		Client client = new Client("proba1", "67654312E", "calle 21", 654321234);
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
	public void testFindClientsString() throws InputValidationException, InstanceNotFoundException, CallStateException {
		Client c1 = new Client("carlos", "12121212E", "calle x", 565434567);
		Client c2 = new Client("carla", "12121212E", "calle x", 565434567);
		try {
			
			clientService.addClient(c1);
			clientService.addClient(c2);
			assertEquals(clientService.findClients("car").size(), 2);	
		} finally{
			clientService.removeClient(c1.getClientId());
			clientService.removeClient(c2.getClientId());
		}
		
	}

	@Test
	public void testFindClientsStringRange() throws InputValidationException, InstanceNotFoundException, CallStateException {
		Client c1 = new Client("carlos", "12121212E", "calle x", 565434567);
		Client c2 = new Client("carla", "12121212E", "calle x", 565434567);
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
	public void testMakeCall() throws InstanceNotFoundException, InputValidationException {
		
		try {
			Client c1 = clientService.findClient("77775437A");
			clientService.makeCall(c1.getClientId(), 234, enumType.LOCAL,65943902);
			assertEquals(c1.getCallList().get(0).getDestPhone(), (Integer)65943902);
			
		} finally {
			
		}
		
	}

	@Test
	public void testChangeCallState() throws InstanceNotFoundException, CallStateException {
		Client c = clientService.findClient("66673477R");
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, 7);
		List<Call> callist = new ArrayList<Call>();
		callist.add(new Call(c.getClientId(), cal, 234, enumType.LOCAL,65943902));
		callist.add(new Call(c.getClientId(), cal, 244, enumType.LOCAL,65954912));
		c.setCallList(callist);
		clientService.changeCallState(c.getClientId(), cal, enumState.BILLED);
		List<Call> calls = clientService.findCalls(c.getClientId(), cal);
		assertEquals(enumState.BILLED, calls.get(0).getState());
	}
	
	@Test(expected = CallStateException.class)
	public void testNotChangeState() throws CallStateException, InstanceNotFoundException {
		Client c = clientService.findClient("66673477R");
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, 7);
		clientService.changeCallState(c.getClientId(), cal, enumState.PENDING);
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
		List<Call> calls = clientService.findCalls(c1.getClientId(), cal1, cal2);
		assertEquals(4, calls.size());
		
		
	}

	@Test
	public void testFindCallsRangeTimeType() throws InstanceNotFoundException, CallStateException{
		Client c1 = clientService.findClient("45777777C");
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.set(Calendar.MONTH, 3);
		cal2.set(Calendar.MONTH, 11);
		List<Call> calls = clientService.findCalls(c1.getClientId(), cal1, cal2, enumType.LOCAL);
		assertEquals(4, calls.size());
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
		List<Call> calls = clientService.findCalls(c1.getClientId(), cal1, cal2, enumType.LOCAL, 1, 2);
		assertEquals(2, calls.size());
	}
	
	@Test(expected = CallStateException.class)
	public void testRemoveClientWithCalls() throws InstanceNotFoundException, CallStateException{
		Client c1 = clientService.findClient("45777777C");
		clientService.removeClient(c1.getClientId());
	}
}
