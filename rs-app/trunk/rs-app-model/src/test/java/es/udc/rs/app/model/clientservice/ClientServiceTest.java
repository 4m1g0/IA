package es.udc.rs.app.model.clientservice;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import es.udc.rs.app.model.client.Client;
import es.udc.rs.app.model.util.ModelConstants.enumType;
import es.udc.ws.util.exceptions.InputValidationException;
import es.udc.ws.util.exceptions.InstanceNotFoundException;

public class ClientServiceTest {
	
	
	private static ClientService clientService = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		

	}

	@Before
	public void setUp() throws Exception {
		
		Client client1 = new Client("Paco", "45777777C", "Calle pepito 22", 678956745);
		Client client2 = new Client("Pepe", "77775437A", "Calle pepito 23", 678922222);
		Client client3 = new Client("Ramon", "77273477R", "Calle pepito 25",678953453);
		
		try {
			clientService.addClient(client1);
			clientService.addClient(client2);
			clientService.addClient(client3);
			clientService.makeCall(client1.getClientId(), 234, enumType.LOCAL,65943902);
			clientService.makeCall(client1.getClientId(), 234, enumType.LOCAL,63443933);
		} catch (Exception e) {
			
		}	
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
	public Client testAddClient() {
		Client addedClient = null;
		try {
			addedClient = clientService.addClient(getValidClient());
		} catch (InputValidationException e) {
			throw new RuntimeException(e);
		}
		return addedClient;
	}
	
	

	@Test
	public void testRemoveClient() {
		try {
			clientService.removeClient(99999L);
		} catch (InstanceNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void testUpdateClient() throws InputValidationException,
	InstanceNotFoundException{
		Client client = getValidClient("Alberto");
		try {
			clientService.addClient(client);
			client.setDNI("87654321");
			client.setAddress("fffff");
			clientService.updateClient(client);
			Client result = clientService.findClient(client.getClientId());
			assertEquals(result, client);
		} finally{
			clientService.removeClient(client.getClientId());
			
		}
	}

	@Test
	public void testFindClientId() {
		ArrayList<Client> clients = new ArrayList<Client>();
	}

	@Test
	public void testFindClientDNI() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindClientsString() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindClientsStringInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testMakeCall() {
		fail("Not yet implemented");
	}

	@Test
	public void testChangeCallState() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindCallsLongCalendar() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindCallsLongCalendarCalendar() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindCallsLongCalendarCalendarEnumType() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindCallsLongCalendarCalendarIntInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindCallsLongCalendarCalendarEnumTypeIntInt() {
		fail("Not yet implemented");
	}

}
