package es.udc.rs.app.jaxrs.dto;

import javax.ws.rs.core.Link;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name = "client")
@XmlType(name="clientType", propOrder = {"id", "name","dni"})
public class ClientDtoJaxb {
	@XmlElement(name = "client-id", required = true)
    private Long id;
    @XmlElement(required = true)
    private String name;
    @XmlElement(required = true)
    private String dni;

	public ClientDtoJaxb(){
    	
    }

	public ClientDtoJaxb(Long id, String name, String dni) {
		this.id = id;
		this.name = name;
		this.dni = dni;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDNI() {
		return dni;
	}

	public void setDNI(String dni) {
		this.dni = dni;
	}   
}
