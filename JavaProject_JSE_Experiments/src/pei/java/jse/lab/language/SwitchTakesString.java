package pei.java.jse.lab.language;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * @author Pei
 */
public class SwitchTakesString {


	@Test
	public void testSwitchTakesString() {
		/*
		 * Since Java 7 the good old 'switch' takes a string
		 */
		String name = "Pei";
		String thisIsMe = "This is me.";
		String msg = null;
		
		switch (name) {
		case "Pei":
			msg = thisIsMe;
			break;
		default:
			break;
		}

		assertTrue(msg.equals(thisIsMe));
	}
	
}
