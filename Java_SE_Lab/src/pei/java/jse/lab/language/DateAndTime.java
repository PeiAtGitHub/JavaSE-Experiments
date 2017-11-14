package pei.java.jse.lab.language;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * 
 * @author pei
 *
 */
public class DateAndTime {

	@Test
	public void systemTime() {
		long nanoTime1 = System.nanoTime();// only for measuring elapsed time 
		
		assertEquals(13, String.valueOf(System.currentTimeMillis()).length());

		assertTrue((System.nanoTime() - nanoTime1) > 0);
	}

}
