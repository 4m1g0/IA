package es.udc.rs.app.jaxrs.util;

import java.net.URI;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.ws.rs.core.Link;

import es.udc.rs.app.jaxrs.dto.CallDetailsDtoJaxb;
import es.udc.rs.app.jaxrs.dto.CallDtoJaxb;
import es.udc.rs.app.jaxrs.resources.CallResource;
import es.udc.rs.app.jaxrs.resources.ClientResource;
import es.udc.rs.app.model.call.Call;

public class CallToCallDtoJaxbConversor {
	
	public static CallDtoJaxb toCallDtoJaxb(Call call, URI  baseUri,
			String type){
		return new CallDtoJaxb(call.getDateCall(),
				call.getDuration(), call.getType());
	}
	
	public static List<CallDtoJaxb> toCallDtoJaxb(List<Call> calls, URI baseUri,
			String type){
		
		List<CallDtoJaxb> callsDtos = new ArrayList<>(calls.size());
		for (int i = 0; i < calls.size(); i++) {
			Call call = calls.get(i);
			callsDtos.add(toCallDtoJaxb(call, baseUri, type));
		}
		return callsDtos;
	}
	
	public static Call toCall(CallDetailsDtoJaxb call){
		Calendar callDate = Calendar.getInstance();
		callDate.set(call.getDateCall().getYear(), call.getDateCall().getMonth(), 
				call.getDateCall().getDay());
		return new Call(call.getClientId(), callDate, call.getDuration(),
				call.getType(), call.getDestPhone());
	}
	
	public static CallDetailsDtoJaxb toCallDetailsDtoJaxb(Call call, URI baseUri,
			String type){
		return new CallDetailsDtoJaxb(call.getCallId(), call.getClientId(), call.getDateCall(), 
				call.getDuration(), call.getDestPhone(), call.getState(), call.getType());
	}

}
