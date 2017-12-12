package pei.java.jse.lab.specialty;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.UUID;
import org.junit.Test;

/**
 * 
 * @author pei
 *
 */
public class UUIDUtils {
	
	@Test
	public void testUUID() {
		UUID uuid4 = UUID.randomUUID();
		assertEquals(4, uuid4.version());
		assertTrue(uuid4.toString()
				.matches("\\w{8}-\\w{4}-\\w{4}-\\w{4}-\\w{12}")); 
	}

}
