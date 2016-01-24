package es.udc.rs.app.jaxb;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import es.udc.ws.util.exceptions.InputValidationException;

public final class StringToDate {
	public static final String DATE_FORMAT_MIN = "yyyy-MM-dd HH:mm:ss";
	public static final String DATE_FORMAT_DAY = "yyyy-MM-dd HH:mm:ss";
	
	public static Calendar getCalendar(String date) throws InputValidationException{
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_MIN);
		try {
			cal.setTime(sdf.parse(date));
		} catch (Exception e) {
			throw new InputValidationException("Formato de fecha incorrecto");
		}
		
		return cal;
	}
	
	public static String getDateString(Calendar cal) {
		SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_MIN);
		return format.format(cal.getTime());
	}

}
