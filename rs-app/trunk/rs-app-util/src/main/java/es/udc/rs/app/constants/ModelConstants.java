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
	private ModelConstants() {
    }

}
