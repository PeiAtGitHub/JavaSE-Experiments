package pei.java.jse.lab.language;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Objects;

import org.junit.Test;

/**
 * 
 * @author Pei
 *
 */
public class ObjectsUtils {


	@Test
	public void testNullnessChecking(){
		try {
			// Null checking made one liner.
			Objects.requireNonNull(null, "Sth is null!"); 
			fail("Should'v thrown an exception.");
		}catch (Exception e) {
			assertTrue(e instanceof NullPointerException);
		}
		
	}

}
