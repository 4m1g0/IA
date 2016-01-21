package es.udc.rs.app.constants;


public final class ModelConstants {
	
	public static enum enumType {LOCAL,NATIONAL,INTERNATIONAL};
	public static enum enumState {PENDING,BILLED,PAID};
	
	
	
	public static final enumState toEnumState(String state){
		if(state.equals("PENDING"))
			return enumState.PENDING;
		if(state.equals("BILLED"))
			return enumState.BILLED;
		if(state.equals("PAID"))
			return enumState.PAID;
		return null;
	}
	
	public static final enumType toEnumType(String type){
		if(type.equals("LOCAL"))
			return enumType.LOCAL;
		if(type.equals("NATIONAL"))
			return enumType.NATIONAL;
		if(type.equals("INTERNATIONAL"))
			return enumType.INTERNATIONAL;
		
		return null;
	}
	
	public static final String toStringState(enumState state){
		if(state.equals(enumState.PENDING))
			return "PENDING";
		if(state.equals(enumState.BILLED))
			return "BILLED";
		if(state.equals(enumState.PAID))
			return "PAID";
		return null;
	}
	
	public static final String toStringType(enumType type){
		if(type.equals(enumType.LOCAL))
			return "LOCAL";
		if(type.equals(enumType.NATIONAL))
			return "NATIONAL";
		if(type.equals(enumType.INTERNATIONAL))
			return "INTERNATIONAL";
		return null;
	}
	
	
	private ModelConstants() {
    }

}
