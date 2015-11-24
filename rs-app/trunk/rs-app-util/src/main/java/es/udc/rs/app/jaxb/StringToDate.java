package es.udc.rs.app.jaxb;

import java.util.Calendar;

public final class StringToDate {
	
	
	public static Calendar parseStringToDate(String date){
		
		String [] elem = date.split("[- :]");
		Calendar cal = Calendar.getInstance();
		cal.set(Integer.parseInt(elem[0]), Integer.parseInt(elem[1]), 
				Integer.parseInt(elem[2]), Integer.parseInt(elem[3]), Integer.parseInt(elem[4]),
				Integer.parseInt(elem[5]));
		return cal;
	}

}
