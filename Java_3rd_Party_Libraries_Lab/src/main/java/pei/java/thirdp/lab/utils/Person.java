package pei.java.thirdp.lab.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author pei
 *
 */

@Setter @Getter @AllArgsConstructor @NoArgsConstructor 
@ToString
public class Person {
	
	public enum Gender {MALE, FEMALE;}
	
	String firstName;
	String lastName;
	int age;
	Gender gender;
	
}
