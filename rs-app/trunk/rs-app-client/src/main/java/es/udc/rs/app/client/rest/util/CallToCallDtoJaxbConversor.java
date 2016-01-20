package es.udc.rs.app.client.rest.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.xml.bind.JAXBElement;

import es.udc.rs.app.client.dto.CallDetailsDto;
import es.udc.rs.app.client.dto.CallDto;
import es.udc.rs.app.client.service.rest.dto.CallDetailsDtoJaxb;
import es.udc.rs.app.client.service.rest.dto.CallDtoJaxb;
import es.udc.rs.app.client.service.rest.dto.DateDtoJaxb;
import es.udc.rs.app.client.service.rest.dto.EnumState;
import es.udc.rs.app.client.service.rest.dto.EnumType;
import es.udc.rs.app.client.service.rest.dto.ObjectFactory;
import es.udc.rs.app.jaxrs.util.ServiceUtil;
import es.udc.ws.util.exceptions.InputValidationException;

public class CallToCallDtoJaxbConversor {
	
	public static CallDto toCallDto(CallDtoJaxb call){
		Calendar cal = Calendar.getInstance();
		cal.set(call.getDateCall().getYear(), call.getDateCall().getMonth(), call.getDateCall().getDay());
		return new CallDto(cal, call.getDuration(), call.getDestPhone());
	}
	
	public static CallDetailsDto toCallDetailsDto(CallDetailsDtoJaxb call){
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(ServiceUtil.DATE_FORMAT_DAY);
		try {
			callInitDate.setTime(sdf.parse(initDate));
		} catch (Exception e) {
			throw new InputValidationException("Formato de fecha incorrecto");
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
		DateDtoJaxb date = null;
		date.setYear(callDetailsDto.getDateCall().get(Calendar.YEAR));
		date.setMonth(callDetailsDto.getDateCall().get(Calendar.MONTH));
		date.setDay(callDetailsDto.getDateCall().get(Calendar.DATE));
		callDetails.setDuration(callDetailsDto.getDuration());
		callDetails.setType(EnumType.valueOf(callDetailsDto.getType().toString()));
		callDetails.setState(EnumState.valueOf(callDetailsDto.getState().toString()));
		callDetails.setDestPhone(callDetailsDto.getDestPhone());
		JAXBElement<CallDetailsDtoJaxb> jaxbElement = new ObjectFactory().createCallDetails(callDetails);
		return jaxbElement;
	}

}
