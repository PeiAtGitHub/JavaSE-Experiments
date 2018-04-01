package pei.java.thirdp.lab.DB;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter 
@NoArgsConstructor @AllArgsConstructor 
@ToString 
@EqualsAndHashCode(exclude={"id", "contactName", "city", "postalCode", "country"})
public class Customer implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	int id;
    String customerName;
    String contactName;
    String address;
    String city;
    String postalCode;
    String country;
    
    
    public Customer(String customerName, String contactName, String address, String city, String postalCode,
            String country) {
        super();
        this.customerName = customerName;
        this.contactName = contactName;
        this.address = address;
        this.city = city;
        this.postalCode = postalCode;
        this.country = country;
    }
    
    
    
}
