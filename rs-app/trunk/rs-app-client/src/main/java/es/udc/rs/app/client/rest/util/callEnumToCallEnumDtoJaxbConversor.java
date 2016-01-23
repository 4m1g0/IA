package es.udc.rs.app.client.rest.util;

import es.udc.rs.app.client.service.rest.dto.EnumState;
import es.udc.rs.app.client.service.rest.dto.EnumType;

public final class callEnumToCallEnumDtoJaxbConversor {
	
	
	public static EnumState toEnumState(EnumState state){
		return EnumState.valueOf(state.value());
		
	}
	
	public static EnumType toEnumType(EnumType type){
		return EnumType.valueOf(type.value());
	}

}
