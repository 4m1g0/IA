package es.udc.rs.app.jaxrs.util;

import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import es.udc.rs.app.constants.ModelConstants;
import es.udc.rs.app.jaxb.StringToDate;
import es.udc.rs.app.jaxrs.dto.CallDetailsDtoJaxb;
import es.udc.rs.app.jaxrs.dto.CallDtoJaxb;
import es.udc.rs.app.model.call.Call;
import es.udc.ws.util.exceptions.InputValidationException;

public class CallToCallDtoJaxbConversor {

	public static CallDtoJaxb toCallDtoJaxb(Call call, URI baseUri, String type) {
		return new CallDtoJaxb(call.getDateCall(), call.getDuration(), call.getDestPhone());
	}

	public static List<CallDtoJaxb> toCallDtoJaxb(List<Call> calls, URI baseUri, String type) {

		List<CallDtoJaxb> callsDtos = new ArrayList<>(calls.size());
		for (int i = 0; i < calls.size(); i++) {
			Call call = calls.get(i);
			callsDtos.add(toCallDtoJaxb(call, baseUri, type));
		}
		return callsDtos;
	}
	
	public static List<CallDetailsDtoJaxb> toCallDetailsDtoJaxb(List<Call> calls, URI baseUri, String type) {

		List<CallDetailsDtoJaxb> callsDtos = new ArrayList<>(calls.size());
		for (int i = 0; i < calls.size(); i++) {
			Call call = calls.get(i);
			callsDtos.add(toCallDetailsDtoJaxb(call, baseUri, type));
		}
		return callsDtos;
	}

	public static Call toCall(CallDetailsDtoJaxb call) throws InputValidationException {
		
		
		
		return new Call(call.getClientId(), StringToDate.getCalendar(call.getDateCall()), call.getDuration(), 
				ModelConstants.toEnumType(call.getType()), call.getDestPhone());
	}

	public static CallDetailsDtoJaxb toCallDetailsDtoJaxb(Call call, URI baseUri, String type) {
		return new CallDetailsDtoJaxb(call.getCallId(), call.getClientId(), StringToDate.getDateString(call.getDateCall()), call.getDuration(),
				call.getDestPhone(), ModelConstants.toStringState(call.getState()), ModelConstants.toStringType(call.getType()));
	}

}
