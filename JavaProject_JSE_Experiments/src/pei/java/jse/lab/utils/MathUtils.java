package pei.java.jse.lab.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * 
 * @author pei
 *
 */
public class MathUtils {

	@Test
	public void testRound() {
		assertEquals(0, Math.round(0.4));
		assertEquals(1, Math.round(0.6));
	}

	@Test
	public void testRandom() {
		double r = Math.random();
		assertTrue(r >= 0.0 && r < 1.0);
	}
	
	@Test
	public void testComparisons() {
		assertTrue(Math.max(1, 2) == 2);
		assertTrue(Math.min(1, 2) == 1);
	}

	@Test
	public void testCalculations() {
		assertTrue(Math.pow(2, 8) == 256);
		assertTrue(Math.sqrt(36) == 6);
		assertTrue(Math.toDegrees(Math.PI) == 180);
	}

}
