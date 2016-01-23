package es.udc.rs.app.constants;

public enum EnumState {
	
	PENDING(0),
	BILLED(1),
	PAID(2);
	
	private Integer value;

	public Integer getValue() {
		return value;
	}

	private EnumState(Integer value) {
		this.value = value;
	}

	public static final EnumState toEnumState(String state){
		if(state.equals("PENDING"))
			return EnumState.PENDING;
		if(state.equals("BILLED"))
			return EnumState.BILLED;
		if(state.equals("PAID"))
			return EnumState.PAID;
		return null;
	}
}
