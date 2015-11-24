package es.udc.rs.app.jaxrs.dto;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "InstanceNotFoundException")
@XmlType(name="instanceNotFoundExceptionType", propOrder = {"instanceId", "instanceType"})
public class InstanceNotFoundExceptionDtoJaxb {

	@XmlElement(required = true)
	private String instanceId;
	@XmlElement(required = true)
	private String instanceType;

	public InstanceNotFoundExceptionDtoJaxb() {
	}

	public InstanceNotFoundExceptionDtoJaxb(Object instanceId, String instanceType) {
		this.instanceId = instanceId.toString();
		this.instanceType = instanceType;
	}

	public String getInstanceId() {
		return instanceId;
	}

	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}

	public String getInstanceType() {
		return instanceType;
	}

	public void setInstanceType(String instanceType) {
		this.instanceType = instanceType;
	}


}