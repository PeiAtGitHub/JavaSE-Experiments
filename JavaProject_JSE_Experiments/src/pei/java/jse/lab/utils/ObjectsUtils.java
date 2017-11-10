package pei.java.jse.lab.utils;

import java.util.Objects;

import org.junit.Test;

/**
 * 
 * @author Pei
 *
 */
public class ObjectsUtils {


	@Test(expected = NullPointerException.class)
	public void testNullnessChecking(){
		Objects.requireNonNull(null, "Sth is null!"); // Null checking made one liner.
	}

}
