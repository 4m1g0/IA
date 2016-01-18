package es.udc.rs.app.jaxrs.util;

import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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

	public static Call toCall(CallDetailsDtoJaxb call) throws InputValidationException {
		Calendar callDate = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		try {
			callDate.setTime(sdf.parse(call.getDateCall()));
		} catch (ParseException e) {
			throw new InputValidationException("Formato de fecha incorrecto");
		}
		return new Call(call.getClientId(), callDate, call.getDuration(), call.getType(), call.getDestPhone());
	}

	public static CallDetailsDtoJaxb toCallDetailsDtoJaxb(Call call, URI baseUri, String type) {
		return new CallDetailsDtoJaxb(call.getCallId(), call.getClientId(), call.getDateCall(), call.getDuration(),
				call.getDestPhone(), call.getState(), call.getType());
	}

}
