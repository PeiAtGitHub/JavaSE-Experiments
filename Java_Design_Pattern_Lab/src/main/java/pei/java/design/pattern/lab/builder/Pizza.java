package pei.java.design.pattern.lab.builder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter @AllArgsConstructor @NoArgsConstructor
public class Pizza {
	String name;
	int size; // in cm
	private String dough = "";
	private String sauce = "";
	private String topping = "";
}
