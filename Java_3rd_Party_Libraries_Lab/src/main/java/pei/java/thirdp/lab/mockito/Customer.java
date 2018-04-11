package pei.java.thirdp.lab.mockito;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pei.java.thirdp.lab.utils.Person;

@Setter @Getter @ToString 
public class Customer extends Person {
    
    long id;
    LocalDate registrationDate;
    
    public Customer(String firstName, String lastName) {
        super.setFirstName(firstName);
        super.setLastName(lastName);
    }

}
