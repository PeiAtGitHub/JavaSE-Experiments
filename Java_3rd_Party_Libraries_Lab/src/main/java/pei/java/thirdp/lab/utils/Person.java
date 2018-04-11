package pei.java.thirdp.lab.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author pei
 *
 */

@Setter @Getter @AllArgsConstructor @NoArgsConstructor 
@Builder
@ToString
public class Person {
    
    public enum Gender {MALE, FEMALE;}
    
    String firstName;
    String lastName;
    int age;
    Gender gender;
    
    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }
    
}
