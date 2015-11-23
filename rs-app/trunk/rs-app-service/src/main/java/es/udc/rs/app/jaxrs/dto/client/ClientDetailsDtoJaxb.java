package es.udc.rs.app.jaxrs.dto.client;

import java.util.List;

import javax.ws.rs.core.Link;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlRootElement(name = "client")
@XmlType(name="clientType", propOrder = {"id", "name", "DNI", 
		"address", "phone", "links"})
public class ClientDetailsDtoJaxb {
	@XmlElement(name = "clientId", required = true)
    private Long id;
    @XmlElement(required = true)
    private String name;
    @XmlElement(required = true)
    private String DNI;
    @XmlElement(required = true)
    private String address;
    @XmlElement(required = true)
    private String phone;
    @XmlElement(name = "link")
    @XmlJavaTypeAdapter(Link.JaxbAdapter.class)
	private List<Link> links;

	public ClientDetailsDtoJaxb(){
    	
    }

	public ClientDetailsDtoJaxb(Long id, String name, String DNI, String address,
			String phone, List<Link> links) {
		this.id = id;
		this.name = name;
		this.DNI = DNI;
		this.address = address;
		this.phone = phone;
		this.links = links;
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
		return DNI;
	}

	public void setDNI(String DNI) {
		this.DNI = DNI;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}

	
	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}

	
	@Override
	public String toString() {
		return "ClientDtoJaxb [clientId=" + id + ", name=" + name
                + ", DNI=" + DNI
                + ", address=" + address + ", phone=" + phone + "]";
	}
	
	
    
    

}
