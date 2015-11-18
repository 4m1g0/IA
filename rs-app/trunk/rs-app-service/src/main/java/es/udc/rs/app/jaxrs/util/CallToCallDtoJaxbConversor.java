package es.udc.rs.app.jaxrs.util;

import es.udc.rs.app.jaxrs.dto.call.CallDtoJaxb;
import es.udc.rs.app.model.call.Call;

public class CallToCallDtoJaxbConversor {
	
	public static CallDtoJaxb toCallDtoJaxb(Call call){
		return new CallDtoJaxb(call.getCallId(), call.getClientId(), call.getDateCall(),
				call.getDuration(), call.getDestPhone(), call.getState(), call.getType());
	}

}
