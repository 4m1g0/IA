package es.udc.rs.app.jaxrs.dto;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "client-details")
@XmlType(name="clientDetailsType", propOrder = {"id", "name", "DNI", 
		"address", "phone"})
public class ClientDetailsDtoJaxb {
	@XmlElement(name = "client-id", required = true)
    private Long id;
    @XmlElement(required = true)
    private String name;
    @XmlElement(required = true)
    private String DNI;
    @XmlElement(required = true)
    private String address;
    @XmlElement(required = true)
    private String phone;

	public ClientDetailsDtoJaxb(){
    	
    }

	public ClientDetailsDtoJaxb(Long id, String name, String DNI, String address,
			String phone) {
		this.id = id;
		this.name = name;
		this.DNI = DNI;
		this.address = address;
		this.phone = phone;
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

}
