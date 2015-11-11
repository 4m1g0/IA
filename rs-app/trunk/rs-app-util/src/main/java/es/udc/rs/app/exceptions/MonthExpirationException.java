package es.udc.rs.app.exceptions;

import java.util.Calendar;

public class MonthExpirationException extends Exception {
	private Calendar date;
	
	public MonthExpirationException(Calendar date){
		this.setDate(date);
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}
}
