package pei.java.jse.lab.language;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Test;

/**
 * 
 * @author pei
 *
 */
public class StringTests {

	@Test(expected = IndexOutOfBoundsException.class)
	public void testIndexing() {
		/*
		 * The convention of indexing is: 
		 * begin index is inclusive, end index is exclusive, 
		 * for short: [ )
		 */
		String str = "1,1;2,2;3,3;";

		assertEquals(3, str.indexOf(";"));
		assertEquals(3, str.indexOf(";", 3)); // begin index inclusive
		assertEquals(7, str.indexOf(";", 4));
		assertEquals(-1, str.indexOf("abc"));

		assertTrue(str.substring(0, 2).equals("1,"));
		assertTrue(str.substring(0).equals(str));
		assertTrue(str.substring(str.length() - 1).equals(";"));
		assertTrue(str.substring(str.length()).isEmpty()); // this behavior, i think, makes no sense
		str.substring(str.length()+1); // throw exception
	}
	
	
	@Test
	public void testEquals() {
		String s1 = "abc";
		String s2 = "abc";
		
		assertTrue(s1.equals(s2));
		assertTrue(s1 == s2); // literal defined same-content strings are actually one object internally
		
		// but this is different:
		s1 = new String("abc");
		s2 = new String("abc");
		
		assertTrue(s1.equals(s2));
		assertTrue(s1 != s2); 		
	}
	
	@Test
	public void testSplit() {
		// when the string does not contain the splitter
		// the entire string itself is the only resulted element
		assertTrue(Arrays.toString("abc".split("$")).equals("[abc]"));
	}
}
