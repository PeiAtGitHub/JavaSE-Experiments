package pei.java.jse.lab.language;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

/**
 * 
 * @author pei
 *
 */
public class CollectionTests {
	

	@Test
	public void someCollectionsUtils() {
		List<Integer> aList = Arrays.asList(5,2,1,4,3);

		assertTrue(Collections.max(aList) == 5);
		assertTrue(Collections.min(aList) == 1);
		
		assertTrue(aList.toString().equals("[5, 2, 1, 4, 3]"));
		Collections.reverse(aList);
		assertTrue(aList.toString().equals("[3, 4, 1, 2, 5]"));
		Collections.sort(aList);
		assertTrue(aList.toString().equals("[1, 2, 3, 4, 5]"));
	}
	
	
	@Test
	public void someArraysUtils() {
		// Arrays.asList
		List<Integer> aList = Arrays.asList(1,2,3,4,5); // convenient!
		assertTrue(aList.toString().equals("[1, 2, 3, 4, 5]")); // List.toString() is human readable

		// Arrays.toString
		int[] arr = {1, 2, 6};
		System.out.println(arr.toString()); // An array's toString() is unreadable for human
		assertTrue(Arrays.toString(arr).equals("[1, 2, 6]")); // converted to human readable string 
	}
	
}
