package pei.java.jse.lab.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * 
 * @author pei
 *
 */
public class SystemUtils {

	@Test
	public void testTime() {
		long nanoTime1 = System.nanoTime();
		String currentTimeMilli = String.valueOf(System.currentTimeMillis());

		assertEquals(13, currentTimeMilli.length());
		assertTrue(currentTimeMilli.startsWith("1"));

		long nanoTime2 = System.nanoTime();
		assertTrue((nanoTime2 - nanoTime1) > 0);

	}


}
