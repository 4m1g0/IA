package es.udc.rs.app.client.rest.util;

import java.net.URI;
import java.util.Calendar;
import java.util.List;

import es.udc.rs.app.client.dto.CallDetailsDto;
import es.udc.rs.app.client.dto.CallDto;
import es.udc.rs.app.client.service.rest.dto.CallDetailsDtoJaxb;
import es.udc.rs.app.client.service.rest.dto.CallDtoJaxb;
import es.udc.rs.app.client.service.rest.dto.JaxbLink;

public class CallToCallDtoJaxbConversor {
	
	public static CallDto toCallDto(CallDtoJaxb call){
		Calendar cal = Calendar.getInstance();
		cal.set(call.getDateCall().getYear(), call.getDateCall().getMonth(), call.getDateCall().getDay());
		return new CallDto(cal, call.getDuration(), call.getDestPhone(), 
				LinkUtil.getLinkUri(call.getLink()));
	}
	
	public static CallDetailsDto toCallDetailsDto(CallDetailsDtoJaxb call){
		List<JaxbLink> links = call.getLink();
		URI clientUri = LinkUtil.getLinkUriFromList(links, "client");
		URI selfUri = LinkUtil.getLinkUriFromList(links, "self");
		Calendar cal = Calendar.getInstance();
		cal.set(call.getDateCall().getYear(), call.getDateCall().getMonth(), call.getDateCall().getDay());
		return new CallDetailsDto(call.getCallId(), call.getClientId(), 
				cal, call.getDuration(), call.getDestPhone(), 
				callEnumToCallEnumDtoJaxbConversor.toEnumType(call.getType()), 
				callEnumToCallEnumDtoJaxbConversor.toEnumState(call.getState()), 
				selfUri, clientUri);
	}

}
