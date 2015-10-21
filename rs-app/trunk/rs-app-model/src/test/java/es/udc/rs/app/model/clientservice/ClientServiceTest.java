package es.udc.rs.app.model.clientservice;

import static org.junit.Assert.*;

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
		
		Client client1 = new Client("Paco", "45777777C", "Calle pepito 22", (short)678956745);
		Client client2 = new Client("Pepe", "77775437A", "Calle pepito 23", (short)678922222);
		Client client3 = new Client("Ramon", "77273477R", "Calle pepito 25", (short)678953453);
		try {
			clientService.addClient(client1);
			clientService.addClient(client2);
			clientService.addClient(client3);
			clientService.makeCall(client1.getClientId(), Calendar.getInstance(), (short)234, enumType.LOCAL,(short)65943902);
			clientService.makeCall(client1.getClientId(), Calendar.getInstance(), (short)234, enumType.LOCAL,(short)63443933);
		} catch (Exception e) {
			
		}	
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public Client testAddClient(Client client) {
		Client addedClient = null;
		try {
			addedClient = clientService.addClient(client);
		} catch (InputValidationException e) {
			throw new RuntimeException(e);
		}
		return addedClient;
	}

	@Test
	public void testRemoveClient(Long clientId) {
		try {
			clientService.removeClient(clientId);
		} catch (InstanceNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	@Test
	public void testUpdateClient(Client client) {
		try {
			clientService.updateClient(client);
		} catch (InputValidationException e) {
			// TODO: handle exception
		} catch (InstanceNotFoundException e){
			
		}
	}

	@Test
	public void testFindClientLong() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindClientString() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindClientsString() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindClientsStringIntInt() {
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
