package pei.java.jse.lab.language;

import static org.junit.Assert.*;

import org.junit.Test;

public class TheMainMethod {
	
	
	public static void main(String[] args) {
		assertNotNull(args); // by default, args is an empty array
		System.out.println(args.length);
	}
	
	@Test
	public void callMainMethodExplicitly() throws Exception {
		TheMainMethod.main(new String[] {"a1", "a2", "a3"});
	}

}
