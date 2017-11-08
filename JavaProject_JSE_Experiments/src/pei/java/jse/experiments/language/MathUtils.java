package pei.java.jse.experiments.language;

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
}
