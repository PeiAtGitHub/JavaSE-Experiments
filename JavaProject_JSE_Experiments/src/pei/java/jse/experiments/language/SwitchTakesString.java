package pei.java.jse.experiments.language;

/**
 * @author Pei
 */
public class SwitchTakesString {
	
	
	public static void main(String[] args) {
		/*
		 * Since Java 7 the good old 'switch' takes a string :)
		 */
		String name = "Pei";
		
		switch (name) {
		case "Pei":
			System.out.println("This is me.");
			break;
		default:
			System.out.println("This is another person.");
			break;
		}
	}
	
}
