package es.udc.rs.app.client.dto;

import java.net.URI;
import java.util.Calendar;

import es.udc.rs.app.constants.ModelConstants.enumState;
import es.udc.rs.app.constants.ModelConstants.enumType;

public class CallDto {

	private Calendar dateCall;
	private int duration;
	private String destPhone;
	private URI self;
	
	public CallDto() {
		super();
	}

	public CallDto(Calendar dateCall, int duration, String destPhone,
			URI self) {
		super();
		this.dateCall = dateCall;
		this.duration = duration;
		this.destPhone = destPhone;
		this.self = self;
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

	public URI getSelf() {
		return self;
	}

	public void setSelf(URI self) {
		this.self = self;
	}

	

	
}
