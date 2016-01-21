package es.udc.rs.app.client.rest.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.xml.bind.JAXBElement;

import es.udc.rs.app.client.dto.CallDetailsDto;
import es.udc.rs.app.client.dto.CallDto;
import es.udc.rs.app.client.dto.ClientDto;
import es.udc.rs.app.client.service.rest.dto.CallDetailsDtoJaxb;
import es.udc.rs.app.client.service.rest.dto.CallDtoJaxb;
import es.udc.rs.app.client.service.rest.dto.CallDtoJaxbList;
import es.udc.rs.app.client.service.rest.dto.ClientDtoJaxb;
import es.udc.rs.app.client.service.rest.dto.ClientDtoJaxbList;
import es.udc.rs.app.client.service.rest.dto.EnumState;
import es.udc.rs.app.client.service.rest.dto.EnumType;
import es.udc.rs.app.client.service.rest.dto.ObjectFactory;
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
	
	public static CallDetailsDto toCallDetailsDto(CallDetailsDtoJaxb call){
		Calendar cal = null;
		try {
			cal = StringToDate.getCalendar(call.getDateCall());
		} catch (InputValidationException e) {
			e.printStackTrace();
		}
		return new CallDetailsDto(call.getCallId(), call.getClientId(), 
				cal, call.getDuration(), call.getDestPhone(), 
				callEnumToCallEnumDtoJaxbConversor.toEnumType(call.getType()), 
				callEnumToCallEnumDtoJaxbConversor.toEnumState(call.getState()));
	}
	
	public static JAXBElement<CallDetailsDtoJaxb> toJaxbCallDetails(CallDetailsDto callDetailsDto){
		CallDetailsDtoJaxb callDetails = new CallDetailsDtoJaxb();
		callDetails.setCallId(callDetailsDto.getCallId()!= null ? callDetailsDto.getCallId() : -1);
		callDetails.setClientId(callDetailsDto.getClientId());
		callDetails.setDuration(callDetailsDto.getDuration());
		callDetails.setType(EnumType.valueOf(callDetailsDto.getType().toString()));
		callDetails.setState(EnumState.valueOf(callDetailsDto.getState().toString()));
		callDetails.setDestPhone(callDetailsDto.getDestPhone());
		callDetails.setDateCall(StringToDate.getDateString(callDetailsDto.getDateCall()));
		JAXBElement<CallDetailsDtoJaxb> jaxbElement = new ObjectFactory().createCallDetails(callDetails);
		return jaxbElement;
	}

}
