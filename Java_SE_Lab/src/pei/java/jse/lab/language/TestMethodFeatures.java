package pei.java.jse.lab.language;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * 
 * @author pei
 *
 */
public class TestMethodFeatures {
	
	@Test
	public void infiniteArgs() {
		assertEquals(0, sum());
		assertEquals(1, sum(1));
		assertEquals(3, sum(1,2));
		assertEquals(6, sum(1,2,3));
	}

	
	private static int sum(int... numbers) {
		int sum = 0;
		for (int num : numbers)
			sum = sum + num;
		return sum;
	}
}
