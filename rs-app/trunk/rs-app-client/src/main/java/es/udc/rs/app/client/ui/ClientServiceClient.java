package es.udc.rs.app.client.ui;

import java.util.Calendar;
import java.util.List;

import es.udc.rs.app.client.dto.CallDetailsDto;
import es.udc.rs.app.client.dto.CallDto;
import es.udc.rs.app.client.dto.CallListIntervalDto;
import es.udc.rs.app.client.dto.ClientDetailsDto;
import es.udc.rs.app.client.dto.ClientDto;
import es.udc.rs.app.client.dto.ClientListIntervalDto;
import es.udc.rs.app.client.service.ClientService;
import es.udc.rs.app.client.service.ClientServiceFactory;
import es.udc.rs.app.constants.ModelConstants;
import es.udc.rs.app.constants.ModelConstants.enumState;
import es.udc.rs.app.constants.ModelConstants.enumType;
import es.udc.rs.app.exceptions.CallStateException;
import es.udc.rs.app.exceptions.MonthExpirationException;
import es.udc.rs.app.exceptions.RemoveClientException;
import es.udc.rs.app.jaxb.StringToDate;
import es.udc.ws.util.exceptions.InputValidationException;
import es.udc.ws.util.exceptions.InstanceNotFoundException;

public class ClientServiceClient {
	
	public static void main(String[] args){
		
		if (args.length == 0){
			System.out.println(args[0]);
			printUsageAndExit();
		}
		
		ClientService clientService = 
				ClientServiceFactory.getService();
		
		if("-a".equalsIgnoreCase(args[0])){
			validateArgs(args, 5, new int[] {});
			String name = args[1];
			String dni = args[2];
			String address = args[3];
			String phone = args[4];
			try {
				Long clientId = clientService.addClient(
						new ClientDetailsDto(null,name, dni, address, phone)).getClientId();
				
				System.out.println("Client " + clientId + " "
						+ "created successfully");
				
			} catch (NumberFormatException | InputValidationException e) {
				e.printStackTrace(System.err);
			}
			
			
		}
		if("-r".equalsIgnoreCase(args[0])){
			validateArgs(args, 2, new int[] { 1 });
			
			long clientId = Long.valueOf(args[1]);
			try {
				clientService.removeClient(clientId);
				
				System.out.println("Client " + clientId + " "
						+ "removed successfully");
			} catch (InstanceNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NumberFormatException |RemoveClientException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		if("-u".equalsIgnoreCase(args[0])){
			validateArgs(args, 6, new int[] { 1 });
			
			long clientId = Long.valueOf(args[1]);
			String name;
			if(args[2].equals(""))
				name = null;
			else
				name = args[2];
			String dni = args[3];
			String address = args[4];
			String phone = args[5];
			
			try {
				ClientDetailsDto clientDto = new ClientDetailsDto(clientId, name, dni, address, phone);
				clientService.updateClient(clientDto);
				System.out.println("Client " + clientId + " "
						+ "updated successfully");
			} catch (NumberFormatException | InstanceNotFoundException | InputValidationException e) {
				e.printStackTrace();			}
			
		}
		if("-fid".equalsIgnoreCase(args[0])){
			validateArgs(args, 2, new int[] { 1 });
			
			try {
				ClientDetailsDto clientDto = clientService.findClient(Long.parseLong(args[1]));
				System.out.println(clientDto.toString());
			} catch (NumberFormatException  | InstanceNotFoundException e) {
				e.printStackTrace(System.err);
			}
			
		}
		if("-fdni".equalsIgnoreCase(args[0])){
			validateArgs(args, 2, new int[] { 1 });
			try {
				ClientDetailsDto clientDto = clientService.findClient(args[1]);
				System.out.println(clientDto.toString());
			} catch (NumberFormatException  | InstanceNotFoundException e) {
				e.printStackTrace(System.err);
			}
			
		}
		if("-fcs".equalsIgnoreCase(args[0])){
			validateArgs(args, 4, new int[] { 2, 3 });
			
			String keywords = args[1];
			Integer index = Integer.valueOf(args[2]);
			Integer numRows = Integer.valueOf(args[3]);
			
			try {
				ClientListIntervalDto clientDtos = clientService.findClients(keywords, index, numRows);
				System.out.println(clientDtos.toString());
			} catch (NumberFormatException e) {
				e.printStackTrace(System.err);
			}
		}
		if("-mc".equalsIgnoreCase(args[0])){
			validateArgs(args, 6, new int[] { 1, 3 });
			
			try {
				Long clientId = Long.valueOf(args[1]);
				String date = args[2];
				int duration = Integer.parseInt(args[3]);
				String phone = args[4];
				enumType type = enumType.valueOf(args[5]);
				clientService.makeCall(clientId, date, duration, type, phone);
				System.out.println("call made successfully");
			} catch (NumberFormatException | InstanceNotFoundException | InputValidationException e) {
				e.printStackTrace(System.err);
			} 
			
			
		}
		if("-cs".equalsIgnoreCase(args[0])){
			validateArgs(args, 5, new int[] { 1 });
			Long clientId = Long.parseLong(args[1]);
			Calendar date = Calendar.getInstance();
			date.set(Integer.parseInt(args[2]), Integer.parseInt(args[1]), 1);
			try {
				clientService.changeCallState(clientId, StringToDate.getDateString(date), args[4]);
				System.out.println("State calls from " + clientId
						+ "on month" + args[2] + " has been updated successfully.");
			} catch (NumberFormatException e) {
				// TODO: handle exception
				e.printStackTrace();
			} catch (CallStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstanceNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (MonthExpirationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		if("-fcll".equalsIgnoreCase(args[0])){
			Long clientId = Long.parseLong(args[1]);
			String initDate = null, endDate = null;
			CallListIntervalDto listCall = null;
			int index, numRows;
			
			try {
				initDate = args[2];
				if(args.length > 5){
					endDate = args[3];
					index = Integer.parseInt(args[4]);
					numRows = Integer.parseInt(args[5]);
					if(args.length > 6){
						listCall = clientService.findCalls(clientId, initDate, endDate, index, numRows, args[6]);
					}
					else{
						listCall = clientService.findCalls(clientId, initDate, endDate, index, numRows);
					}
				}else{
					index = Integer.parseInt(args[3]);
					numRows = Integer.parseInt(args[4]);
					listCall = clientService.findCalls(clientId, initDate,index, numRows);
				}
				System.out.println(listCall.toString());
				
			} catch (CallStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstanceNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		
		if("-fcb".equalsIgnoreCase(args[0])){
			validateArgs(args, 6, new int[] { 1 });
			
			Long clientId = Long.parseLong(args[1]);
			Calendar date = Calendar.getInstance();
			date.set(Integer.parseInt(args[3]), Integer.parseInt(args[2]), 10);
			int index = Integer.parseInt(args[4]);
			int numRows = Integer.parseInt(args[5]);
			try {
				CallListIntervalDto listCall = clientService.findCalls(clientId, StringToDate.getDateString(date), index, numRows);
				System.out.println(listCall.toString());
			} catch (NumberFormatException e) {
				// TODO: handle exception
			} catch (CallStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstanceNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		
	}
	
	public static void validateArgs(String[] args, int expectedArgs,
			int[] numericArguments) {
		if (expectedArgs != args.length) {
			printUsageAndExit();
		}
		for (int i = 0; i < numericArguments.length; i++) {
			int position = numericArguments[i];
			try {
				Double.parseDouble(args[position]);
			} catch (NumberFormatException n) {
				printUsageAndExit();
			}
		}
	}

	public static void printUsageAndExit() {
		printUsage();
		System.exit(-1);
	}

	public static void printUsage() {
		System.err
				.println("Usage:\n"
						+ "    [addClient]       ServiceClient -a <name> <DNI> <address> <phone>\n"
						+ "    [removeClient]    ServiceClient -r <clientId>\n"
						+ "    [updateClient]    ServiceClient -u <clientId> <name>  <DNI> <address> <phone>\n"
						+ "    [findClientById]  ServiceClient -fid <clientId>\n"
						+ "    [findClientByDNI] ServiceClient -fdni <DNI>\n"
						+ "    [findClients]     ServiceClient -fcs <keywords> <index> <numRows>\n"
						+ "    [makeCall]        ServiceClient -mc <clientId> <date> <duration> <phoneDest> <type>\n"
						+ "    [changeState]     ServiceClient -cs <clientId> <month>  <year> <state>\n"
						+ "    [findCalls]       ServiceClient -fcll <clientId> <dateInit>  <dateEnd>(OP) <index> <numRows> <type>(OP)\n"
						+ "    [findCallsBill]   ServiceClient -fcb <clientId> <month> <year> <index> <numRows>\n");
	}

}
