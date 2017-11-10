package pei.java.jse.lab.utils;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

/**
 * 
 * @author pei
 *
 */
public class CollectionsUtils {
	
	@Test
	public void testSorting() {
		List<Integer> aList = Arrays.asList(5,2,1,4,3);

		assertTrue(Collections.max(aList) == 5);
		assertTrue(Collections.min(aList) == 1);
		
		assertTrue(aList.toString().equals("[5, 2, 1, 4, 3]"));
		Collections.reverse(aList);
		assertTrue(aList.toString().equals("[3, 4, 1, 2, 5]"));
		Collections.sort(aList);
		assertTrue(aList.toString().equals("[1, 2, 3, 4, 5]"));
	}

}
