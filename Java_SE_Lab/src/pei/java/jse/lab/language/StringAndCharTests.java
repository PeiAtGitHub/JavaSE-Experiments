package pei.java.jse.lab.language;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.hamcrest.CoreMatchers.*;

import java.io.NotActiveException;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import pei.java.jse.lab.utils.Utils;

/**
 * 
 * @author pei
 *
 */
public class StringAndCharTests {

	@Test
	public void testIndexing() {
		/*
		 * The convention of indexing is: begin index is inclusive, end index is
		 * exclusive, for short: [ )
		 */
		String str = "1,1;2,2;3,3;";

		assertEquals(3, str.indexOf(";"));
		assertEquals(3, str.indexOf(";", 3)); // begin index inclusive
		assertEquals(7, str.indexOf(";", 4));
		assertEquals(-1, str.indexOf("abc"));

		assertThat(str.substring(0, 2), is("1,"));
		assertThat(str.substring(0), is(str));
		assertThat(str.substring(str.length() - 1), is(";"));
		assertTrue(str.substring(str.length()).isEmpty()); // this behavior, i think, makes no sense

		assertThat(Utils.catchException(()->str.substring(str.length() + 1)),
				instanceOf(IndexOutOfBoundsException.class));
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
		assertThat(splitted[0], is(str));
	}

	@Test
	public void numberToChar() {
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

	@Test
	public void demoOfSpecialChars() {
		assertThat(String.format("\u007C"), is("|"));
		assertThat(String.format("\u00F1"), is("ñ"));
		assertThat(String.format("\\u007C"), is("\\u007C"));

		String first = "AAA\r\nBBB\r\nCCC\r\n";
		String second = "AAA\nBBB\nCCC\n";
		String third = "AAA\rBBB\rCCC\r";
		assertEquals(15, first.length());
		assertEquals(12, second.length());
		assertEquals(12, third.length());
		// below demo uses print() instead of println();
		System.out.print(first);
		System.out.print(second);
		System.out.print(third);
	}

	/*
	 * Regex tests.
	 * Regex behavior from language to language may vary, e.g. Java and
	 * Python
	 * 
	 * "^"(begin) and "$"(end) are necessary for some languages in some cases, while
	 * unnecessary for other languages in the same case. (Demos below are not to
	 * show this trick...)
	 * 
	 * Pattern's compile() is said to consume a lot computing resource, recommended
	 * to reuse the object.
	 */
	@Test
	public void searchMatches() {
		final String greedy = "a.+c";
		final String reluctant = "a.+?c";
		String regex;
		if (new Random().nextBoolean()) {
			regex = greedy;
		} else {
			regex = reluctant;
		}
		System.out.println("Regex: " + regex);

		Pattern regexPattern = Pattern.compile(regex);
		Matcher matcher = regexPattern.matcher("abc-xxxxx-abbbc-ac");// the string under search
		boolean found;
		// 1st
		found = matcher.find();
		assertTrue(found);
		switch (regex) {
		case greedy:
			assertTrue(matcher.group().equals("abc-xxxxx-abbbc-ac"));
			assertThat(matcher.start(), is(0));
			assertThat(matcher.end(), is(18));
			break;
		case reluctant:
			assertThat(matcher.group(), is("abc"));
			assertThat(matcher.start(), is(0));
			assertThat(matcher.end(), is(3));
			break;
		default:
			break;
		}
		// 2nd
		found = matcher.find();
		switch (regex) {
		case greedy:
			assertFalse(found);
			break;
		case reluctant:
			assertThat(matcher.group(), is("abbbc"));
			assertEquals(10, matcher.start());
			assertEquals(15, matcher.end());
			break;
		default:
			break;
		}
		// 3rd
		found = matcher.find();
		assertFalse(found);
	}

	@Test
	public void testMatches() {
		String regex;
		if (new Random().nextBoolean()) {
			regex = "a.+c"; // greedy
		} else {
			regex = "a.+?c"; // reluctant
		}
		System.out.println("Regex: " + regex);
		assertTrue("abc-xxxxx-abbbc-ac".matches(regex));
	}

	@Test
	public void testExtraction() { // search matches
		Matcher matcher = Pattern.compile("First Name:(.*?); Last Name:")
				.matcher("First Name: Three; Last Name: Zhang");
		if (matcher.find()) {
			assertThat(matcher.group(1).trim(), is("Three"));
		} else {
			fail("Should have a match...");
		}
	}

	@Test
	public void testTheSelfMadeUtil() {
		final String theStr = "111,aaa,bbb,,ccc";
		String delimiter = ",";
		
		assertThat(getSubString(theStr, delimiter, 2, 3), is("bbb"));
		assertThat(getSubString(theStr, delimiter, 2, 4), is("bbb,"));
		assertThat(getSubString(theStr, delimiter, 3, 4), is(""));
		assertThat(getSubString(theStr, delimiter, 0, 2), is("111,aaa"));
		assertThat(getSubString(theStr, delimiter, 0, 100), is(""));
		// unusual edge cases
		assertThat(getSubString(theStr, delimiter, 1, 1), is(""));
		assertThat(getSubString(theStr, delimiter, 100, 100), is(""));
		assertThat(getSubString(theStr, delimiter, 2, 1), is(""));
		assertThat(getSubString(theStr, delimiter, 2, -1), is(""));
		assertThat(getSubString(theStr, delimiter, -2, -1), is(""));
		assertThat(getSubString(theStr, delimiter, -2, -2), is(""));
		assertThat(getSubString(theStr, delimiter, -1, -2), is(""));
		assertThat(getSubString(theStr, delimiter, -1, 2), is("111,aaa"));
		assertThat(getSubString(theStr, delimiter, -2, 2), is("111,aaa"));
		assertThat(getSubString(theStr, delimiter, -2, 100), is(""));
	}

	/*
	 * a self-made Util
	 */

	/**
	 * String.split() consumption on memory and cpu is high, and inconvenient in
	 * some cases.
	 *
	 * This method is simpler for getting the sub string between the n1th and the
	 * n2th delimiter 
	 * E.g. 
	 * getSubString("111,aaa,bbb,,ccc", ",", 2, 3) returns "bbb"
	 * getSubString("111,aaa,bbb,,ccc", ",", 0, 2) returns "111,aaa"
	 * getSubString("111,aaa,bbb,,ccc", ",", 3, 4) returns ""
	 * 
	 */
	private static String getSubString(String str, String delimiter, int n1, int n2) {
		if (n2 <= n1) {
			return "";
		}
		try {
			int idx1 = -1;
			int pointer = -1;
			for (int i = 1; i <= n2; i++) {
				pointer = str.indexOf(delimiter, pointer + 1);
				if (i == n1) {
					idx1 = pointer;
				}
			}
			return str.substring(idx1 + 1, pointer);
		} catch (IndexOutOfBoundsException e) {
			return "";
		}
	}

}
