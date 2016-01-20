package es.udc.rs.app.jaxrs.dto;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import es.udc.rs.app.jaxrs.util.ServiceUtil;

@XmlRootElement(name="call")
@XmlType(name="callType", propOrder = {"dateCall", 
		"duration", "destPhone"})

public class CallDtoJaxb {

    @XmlElement(required = true)
    private String dateCall;
    @XmlElement(required = true)
    private Integer duration;
    @XmlElement(required = true)
    private String destPhone;
    
    public CallDtoJaxb(){
    	
    }
    
	public CallDtoJaxb(Calendar dateCall, Integer duration, String destPhone) {
		SimpleDateFormat format = new SimpleDateFormat(ServiceUtil.DATE_FORMAT_MIN);
		String date = format.format(dateCall.getTime());
		this.dateCall = date;
		this.duration = duration;
		this.destPhone = destPhone;
		
	}

	public String getDateCall() {
		return dateCall;
	}

	public void setDateCall(String dateCall) {
		this.dateCall = dateCall;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public String getDestPhone() {
		return destPhone;
	}

	public void setDestPhone(String destPhone) {
		this.destPhone = destPhone;
	}
	
}
