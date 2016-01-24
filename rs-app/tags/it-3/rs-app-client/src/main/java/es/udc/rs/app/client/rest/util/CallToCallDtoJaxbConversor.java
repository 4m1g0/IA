package es.udc.rs.app.client.rest.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.bind.JAXBElement;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import es.udc.rs.app.client.dto.CallDetailsDto;
import es.udc.rs.app.client.dto.CallDto;
import es.udc.rs.app.client.service.rest.dto.CallDetailsDtoJaxb;
import es.udc.rs.app.client.service.rest.dto.CallDetailsDtoJaxbList;
import es.udc.rs.app.client.service.rest.dto.CallDtoJaxb;
import es.udc.rs.app.client.service.rest.dto.CallDtoJaxbList;
import es.udc.rs.app.client.service.rest.dto.ObjectFactory;
import es.udc.rs.app.constants.EnumState;
import es.udc.rs.app.constants.EnumType;
import es.udc.rs.app.jaxb.StringToDate;
import es.udc.ws.util.exceptions.InputValidationException;

public class CallToCallDtoJaxbConversor {
	
	public static CallDto toCallDto(CallDtoJaxb call){
		Calendar cal = null;
		try {
			cal = StringToDate.getCalendar(call.getDateCall());
		} catch (InputValidationException e) {
			e.printStackTrace();
		}
		
		return new CallDto(cal, call.getDuration(), call.getDestPhone());
	}
	
	public static List<CallDto> toCallDtos(CallDtoJaxbList callListDto){
		
		List<CallDtoJaxb> callList = callListDto.getCall();
		List<CallDto> callDtos = new ArrayList<>(callList.size());
		for (int i = 0; i < callList.size(); i++) {
			callDtos.add(toCallDto(callList.get(i)));
		}
		return callDtos;
	}
	
public static List<CallDetailsDto> toCallDetailsDtos(CallDetailsDtoJaxbList callDetailsListDto){
		
		List<CallDetailsDtoJaxb> callList = callDetailsListDto.getCall();
		List<CallDetailsDto> callDetailsDtos = new ArrayList<>(callList.size());
		for (int i = 0; i < callList.size(); i++) {
			callDetailsDtos.add(toCallDetailsDto(callList.get(i)));
		}
		return callDetailsDtos;
	}
	
	public static CallDetailsDto toCallDetailsDto(CallDetailsDtoJaxb call){
		return new CallDetailsDto(call.getCallId(), call.getClientId(), 
				call.getDateCall().toGregorianCalendar(), call.getDuration(), call.getDestPhone(), 
				EnumType.toEnumType(call.getType()),EnumState.toEnumState(call.getState()));
	}
	
	public static JAXBElement<CallDetailsDtoJaxb> toJaxbCallDetails(CallDetailsDto callDetailsDto){
		CallDetailsDtoJaxb callDetails = new CallDetailsDtoJaxb();
		callDetails.setCallId(callDetailsDto.getCallId()!= null ? callDetailsDto.getCallId() : -1);
		callDetails.setClientId(callDetailsDto.getClientId());
		callDetails.setDuration(callDetailsDto.getDuration());
		callDetails.setType(callDetailsDto.getType().name());
		callDetails.setState(null);
		callDetails.setDestPhone(callDetailsDto.getDestPhone());
		
		try {
			GregorianCalendar greg = new GregorianCalendar();
			greg.setTime(callDetailsDto.getDateCall().getTime());
			callDetails.setDateCall( DatatypeFactory.newInstance().newXMLGregorianCalendar(greg));
		} catch (DatatypeConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JAXBElement<CallDetailsDtoJaxb> jaxbElement = new ObjectFactory().createCallDetails(callDetails);
		return jaxbElement;
	}

}
