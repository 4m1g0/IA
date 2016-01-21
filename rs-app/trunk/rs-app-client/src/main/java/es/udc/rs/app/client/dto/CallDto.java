package es.udc.rs.app.client.dto;

import java.util.Calendar;

import es.udc.rs.app.jaxb.StringToDate;

public class CallDto {

	private Calendar dateCall;
	private int duration;
	private String destPhone;
	
	public CallDto() {
		super();
	}

	public CallDto(Calendar dateCall, int duration, String destPhone) {
		super();
		this.dateCall = dateCall;
		this.duration = duration;
		this.destPhone = destPhone;
	}

	public Calendar getDateCall() {
		return dateCall;
	}

	public void setDateCall(Calendar dateCall) {
		this.dateCall = dateCall;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getDestPhone() {
		return destPhone;
	}

	public void setDestPhone(String destPhone) {
		this.destPhone = destPhone;
	}

	@Override
	public String toString() {
		return "CallDto [dateCall=" + StringToDate.getDateString(dateCall) + ", duration=" + duration
				+ ", destPhone=" + destPhone + "]";
	}
	
	
	
}
