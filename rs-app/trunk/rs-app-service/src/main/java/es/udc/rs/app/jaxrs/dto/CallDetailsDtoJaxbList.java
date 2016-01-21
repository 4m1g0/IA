package es.udc.rs.app.jaxrs.dto;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="calls")
@XmlType(name="callDetailsListType")
public class CallDetailsDtoJaxbList {
	
	@XmlElement(name = "call")
	private List<CallDetailsDtoJaxb> calls = null;

	public CallDetailsDtoJaxbList(){
		this.calls = new ArrayList<CallDetailsDtoJaxb>();
	}
	
	public CallDetailsDtoJaxbList(List<CallDetailsDtoJaxb> calls){
		this.calls = calls;
	}

	public List<CallDetailsDtoJaxb> getCalls() {
		return calls;
	}

	public void setCalls(List<CallDetailsDtoJaxb> calls) {
		this.calls = calls;
	}
	
}