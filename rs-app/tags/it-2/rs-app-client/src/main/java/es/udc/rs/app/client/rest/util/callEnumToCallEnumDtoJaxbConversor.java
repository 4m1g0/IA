package es.udc.rs.app.client.rest.util;

import es.udc.rs.app.client.service.rest.dto.EnumState;
import es.udc.rs.app.client.service.rest.dto.EnumType;
import es.udc.rs.app.constants.ModelConstants.enumState;
import es.udc.rs.app.constants.ModelConstants.enumType;

public final class callEnumToCallEnumDtoJaxbConversor {
	
	
	public static enumState toEnumState(EnumState state){
		return enumState.valueOf(state.value());
		
	}
	
	public static enumType toEnumType(EnumType type){
		return enumType.valueOf(type.value());
	}

}
