package es.udc.rs.app.jaxrs.util;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.xml.datatype.DatatypeConfigurationException;

import es.udc.rs.app.constants.EnumType;
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
		
		
		
		return new Call(call.getClientId(), call.getDateCall(), call.getDuration(), 
				EnumType.toEnumType(call.getType()), call.getDestPhone());
	}

	public static CallDetailsDtoJaxb toCallDetailsDtoJaxb(Call call, URI baseUri, String type) {

		try {
			return new CallDetailsDtoJaxb(call.getCallId(), call.getClientId(),call.getDateCall(), call.getDuration(),
						call.getDestPhone(), call.getState().name(), call.getType().name());
		} catch (DatatypeConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

}
