package es.udc.rs.app.jaxrs.dto;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="calls")
@XmlType(name="callListType")
public class CallDtoJaxbList {
	
	@XmlElement(name = "call")
	private List<CallDtoJaxb> calls = null;

	public CallDtoJaxbList(){
		this.calls = new ArrayList<CallDtoJaxb>();
	}
	
	public CallDtoJaxbList(List<CallDtoJaxb> calls){
		this.calls = calls;
	}

	public List<CallDtoJaxb> getClients() {
		return calls;
	}

	public void setClients(List<CallDtoJaxb> calls) {
		this.calls = calls;
	}
	
}