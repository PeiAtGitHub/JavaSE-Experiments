package pei.java.jse.lab.language;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		
		// unmodifiable view
		List<Integer> unmodifiableList = Collections.unmodifiableList(aList);
		try {
			unmodifiableList.set(0, 1);
			fail();
		} catch (Exception e) {
			assertTrue(e instanceof UnsupportedOperationException);
		}
		
		/*
		 *  empty list, always the same immutable & singleton instance.
		 *  faster and safer than "new" an new list object.
		 *  a better return value than returning null
		 */
		List<String> emptyList = Collections.<String> emptyList();
		List<String> emptyListAgain = Collections.<String> emptyList();
		assertSame(emptyList, emptyListAgain); // same reference
		try {
			emptyList.add("abc");
			fail();
		} catch (Exception e) {
			assertTrue(e instanceof UnsupportedOperationException);
		}

	}
	
	
	@Test
	public void someArraysUtils() {
		// Arrays.asList
		List<Integer> list = Arrays.asList(6,2,1); // convenient!
		assertTrue(list.toString().equals("[6, 2, 1]")); // List.toString() is human readable
		
		// convert list to array
		Integer[] arr = list.toArray(new Integer[list.size()]);
		
		// Arrays.toString
		System.out.println(arr.toString()); // An array's toString() is unreadable for human
		assertTrue(Arrays.toString(arr).equals("[6, 2, 1]")); // converted to human readable string
		
		// 
		Arrays.sort(arr);
		assertTrue(Arrays.toString(arr).equals("[1, 2, 6]")); 
	}
	
	@Test
	public void constructCollection() {
		List<String> originalList = Arrays.asList("aaa", "bbb", "ccc");
		// make a new list copying from an existing list
		ArrayList<String> newList = new ArrayList<>(originalList);
		
		assertNotSame(originalList, newList); // not same object reference
		assertTrue(originalList.equals(newList)); // object.equals() is overridden for List
		assertEquals(originalList, newList); // this method makes use of object.equals()
		
		// they don't share the common base
		newList.remove(2);
		assertNotEquals(originalList, newList);
	}

	@Test
	public void listFeatures() {
		ArrayList<String> emptyList = new ArrayList<String>(8); // with initial capacity
		try {
			emptyList.get(0); // there is no default initial value as an array
			fail();
		} catch (Exception e) {
			assertTrue(e instanceof IndexOutOfBoundsException);
		}
	}
	
	@Test
	public void mapFeatures() {
		Map<String, String> theMap = new HashMap<>();
		theMap.put("A", "X");
		theMap.put("B", "Y");
		theMap.put("C", "Z");
		
		// toString is human readable
		assertTrue((theMap.toString().equals("{A=X, B=Y, C=Z}")));
		
		// get non-existing key returns null
		assertNull(theMap.get("F"));
		// remove non-existing key returns null
		assertNull(theMap.remove("F"));
		// remove() returns the Value of Key
		assertTrue(theMap.remove("A").equals("X"));
		// Set.remove() returns boolean
		assertFalse(theMap.keySet().remove("A")); 
		assertTrue(theMap.keySet().remove("B")); 
		assertFalse(theMap.keySet().remove("F"));
	}

	@Test
	public void arrayFeatures() {
		int n = 3;
		
		long[] longArr = new long[n];
		boolean[] booleanArr = new boolean[n];
		String[] strArr = new String[n];
		
		// default initial values
		for (int i = 0; i < n; i++) {
			assertEquals(0, longArr[i]);
			assertNull(strArr[i]);
			assertFalse(booleanArr[i]);
		}
		
				
	}
	
}
