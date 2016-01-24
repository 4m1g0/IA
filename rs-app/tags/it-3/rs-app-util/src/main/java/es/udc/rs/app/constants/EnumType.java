package es.udc.rs.app.constants;

public enum EnumType {
	
	LOCAL(0),NATIONAL(1),INTERNATIONAL(2);
	
	private Integer value;

	public Integer getValue() {
		return value;
	}

	private EnumType(Integer value) {
		this.value = value;
	}

	public static final EnumType toEnumType(String type){
		if(type.equals("LOCAL"))
			return EnumType.LOCAL;
		if(type.equals("NATIONAL"))
			return EnumType.NATIONAL;
		if(type.equals("INTERNATIONAL"))
			return EnumType.INTERNATIONAL;
		
		return null;
	}
	
}
