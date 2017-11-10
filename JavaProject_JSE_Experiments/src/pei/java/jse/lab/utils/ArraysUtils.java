package pei.java.jse.lab.utils;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/**
 * 
 * @author pei
 *
 */
public class ArraysUtils {

	@Test
	public void testAsList() {
		List<Integer> aList = Arrays.asList(1,2,3,4,5);
		assertTrue(aList.toString().equals("[1, 2, 3, 4, 5]"));
	}
	
	@Test
	public void testToString() {
		int[] arr = {1, 2, 6};
		System.out.println("An array's toString() produces like this: "+ arr.toString());
		assertTrue(Arrays.toString(arr).equals("[1, 2, 6]"));
	}
}
