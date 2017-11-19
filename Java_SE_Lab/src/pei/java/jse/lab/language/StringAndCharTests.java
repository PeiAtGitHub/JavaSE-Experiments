package pei.java.jse.lab.language;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * 
 * @author pei
 *
 */
public class StringAndCharTests {

	@Test
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
		
		try {
			str.substring(str.length()+1); 
			fail("Should'v thrown IndexOutOfBoundsException.");
		} catch (Exception e) {
			assertTrue(e instanceof IndexOutOfBoundsException);
		}
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
		String str = "abcde";
		String[] splitted = str.split("$"); // when the string does not contain the splitter
		// the entire string itself is the only resulted element
		assertEquals(1, splitted.length);
		assertTrue(splitted[0].equals(str));
	}
	
	@Test 
	public void numberToChar(){
		assertEquals('0', (char) (48));
		assertEquals('1', (char) (49));
		assertEquals('H', (char) (72));
		assertEquals('I', (char) (73));
		// each char above takes only one byte (0~127)
		// each char below takes more than one byte
		assertEquals('Ä', (char) (196));
		assertEquals('Ā', (char) (256));
		assertEquals('횇', (char) (1234567));
	}
}
