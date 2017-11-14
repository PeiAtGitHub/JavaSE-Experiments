package pei.java.jse.lab.specialty;

import static org.junit.Assert.assertEquals;
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
		assertEquals(36, uuid4.toString().length()); // including 4 hyphens
	}

}
