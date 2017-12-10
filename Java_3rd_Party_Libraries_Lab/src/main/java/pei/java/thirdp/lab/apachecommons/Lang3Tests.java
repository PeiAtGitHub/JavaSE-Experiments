package pei.java.thirdp.lab.apachecommons;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.junit.Test;

/**
 * 
 * @author pei
 *
 */
public class Lang3Tests {

	@Test
	public void testRandomString() throws Exception {
		String s1 = RandomStringUtils.random(20);
		String s2 = RandomStringUtils.randomAlphanumeric(20);
		String s3 = RandomStringUtils.randomAlphabetic(20);
		String s4 = RandomStringUtils.randomNumeric(20);

		assertTrue(s1.length() == 20);
		if (s1.matches("\\w{20}")) {
			System.out.println("When can this happen???!!!");
		}

		assertTrue(s2.matches("\\w{20}"));
		if (s2.matches("[a-zA-Z]{20}")) {
			System.out.println("Not a single digit in the string!!!");
		}

		assertTrue(s3.matches("[a-zA-Z]{20}"));
		assertTrue(s3.matches("\\w{20}"));

		assertTrue(s4.matches("\\d{20}"));

		//
		System.out.println(s1);
		System.out.println(s2);
		System.out.println(s3);
		System.out.println(s4);
	}
	
	@Test
	public void testValidate() {
		String msg = "Value should be in (100, 200)";
		try {
			Validate.exclusiveBetween(100, 200, new Random().nextInt(100), msg);	
			fail();
		} catch (Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
			assertTrue(e.getMessage().equals(msg));
		}
		msg = "The string is empty.";
		try {
			Validate.notBlank("   ", msg);	
			fail();
		} catch (Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
			assertTrue(e.getMessage().equals(msg));
		}
		msg = "The state is false.";
		try {
			Validate.validState(false, msg);
			fail();
		} catch (Exception e) {
			assertTrue(e instanceof IllegalStateException);
			assertTrue(e.getMessage().equals(msg));
		}
	}

	@Test
	public void testStringUtils() throws Exception {
		/*
		 * Utils here are all NULL-SAFE
		 */
		// just null-safe
		assertEquals(0, StringUtils.length(null));
		
		// constants
		assertTrue(StringUtils.INDEX_NOT_FOUND == -1);
		assertTrue(StringUtils.SPACE.equals(" "));

		// empty, blank
		assertTrue(StringUtils.isEmpty(null));
		assertTrue(StringUtils.isEmpty(""));
		assertFalse(StringUtils.isEmpty("   "));

		assertTrue(StringUtils.isBlank(null));
		assertTrue(StringUtils.isBlank(""));
		assertTrue(StringUtils.isBlank("   "));
		
		assertTrue(StringUtils.defaultString(null, "").isEmpty());
		assertTrue(StringUtils.defaultString("ABC", "").equals("ABC"));

		assertTrue(StringUtils.defaultIfBlank(null, "").isEmpty());
		assertTrue(StringUtils.defaultIfBlank("       ", "").isEmpty());
		assertTrue(StringUtils.defaultIfBlank("ABC", "").equals("ABC"));
		assertTrue(StringUtils.defaultIfEmpty(null, "").isEmpty());
		assertTrue(StringUtils.defaultIfEmpty("       ", "").equals("       "));
		assertTrue(StringUtils.defaultIfEmpty("ABC", "").equals("ABC"));

		// truncate, sub string, split
		assertNull(StringUtils.truncate(null, Math.abs(new Random().nextInt())));
		assertTrue(StringUtils.truncate("123456789", 5).equals("12345"));
		assertTrue(StringUtils.truncate("123456789", 8).equals("12345678"));
		assertTrue(StringUtils.truncate("123456789", 10).equals("123456789"));
		try {
			StringUtils.truncate("123456789", -1);
			fail();
		} catch (Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
		}
		assertTrue(StringUtils.left("abcdef", 2).equals("ab"));
		assertTrue(StringUtils.right("abcdef", 2).equals("ef"));
		assertTrue(StringUtils.mid("abcdef", 3, 2).equals("de"));
		assertTrue(StringUtils.substringBefore("abcdef", "cd").equals("ab"));
		assertTrue(StringUtils.substringAfter("abcdef", "cd").equals("ef"));
		assertTrue(StringUtils.substringBetween(",abcdef,", ",").equals("abcdef"));
		assertTrue(StringUtils.substringBetween("...<tag>abcdef</tag>...", "<tag>", "</tag>").equals("abcdef"));

		String[] strs = StringUtils.substringsBetween("[a][b][c]", "[", "]");
		assertTrue(Arrays.asList(strs).toString().equals("[a, b, c]"));

		strs = StringUtils.split("abc  def");
		assertTrue(Arrays.asList(strs).toString().equals("[abc, def]"));

		strs = StringUtils.split("a..b.c", '.');
		assertTrue(Arrays.asList(strs).toString().equals("[a, b, c]"));

		strs = StringUtils.splitByCharacterType("ab   de fg");
		assertTrue(Arrays.asList(strs).toString().equals("[ab,    , de,  , fg]"));
		strs = StringUtils.splitByCharacterType("foo200BarDrink");
		assertTrue(Arrays.asList(strs).toString().equals("[foo, 200, B, ar, D, rink]"));
		strs = StringUtils.splitByCharacterTypeCamelCase("foo200BarDrink");
		assertTrue(Arrays.asList(strs).toString().equals("[foo, 200, Bar, Drink]"));
		assertTrue( StringUtils.getDigits("(541) 754-3010").equals("5417543010"));

		// trim, strip, remove
		assertNull(StringUtils.trim(null));
		assertTrue(StringUtils.trim("").equals(""));
		assertTrue(StringUtils.trim("   ").equals(""));
		assertTrue(StringUtils.trim("    abc    ").equals("abc"));
		assertNull(StringUtils.strip(null, "abc"));
		assertTrue(StringUtils.strip(" abc ", null).equals("abc"));
		assertTrue(StringUtils.strip("  abcyx", "xyz").equals("  abc"));
		assertTrue(StringUtils.stripStart("y xabc  ", "xyz ").equals("abc  "));
		assertTrue(StringUtils.stripStart("$$$%%%---a-b-c  ", "xyz$%- ").equals("a-b-c  "));
		assertTrue(StringUtils.deleteWhitespace("   ab  c  ").equals("abc"));
		assertTrue(StringUtils.remove("www.domain.www.com", "www.").equals("domain.com"));
		assertTrue(StringUtils.removeIgnoreCase("www.domain.www.com", "www.").equals("domain.com"));
		assertTrue(StringUtils.removeStart("www.domain.www.com", "www.").equals("domain.www.com"));
		assertTrue(StringUtils.removeEnd("a;b;c;", ";").equals("a;b;c"));
		assertTrue(StringUtils.removeEndIgnoreCase("www.domain.COM", ".com").equals("www.domain"));
		assertTrue(StringUtils.removeAll("ABCabc123abc", "[a-z]").equals("ABC123"));
		assertTrue(StringUtils.removePattern("ABCabc123", "[a-z]").equals("ABC123"));
		assertTrue(StringUtils.chomp("abc\r").equals("abc"));
		assertTrue(StringUtils.chomp("abc\n").equals("abc"));
		assertTrue(StringUtils.chomp("abc\r\n").equals("abc"));
		assertTrue(StringUtils.chomp("abc\r\n\r\n").equals("abc\r\n"));
		assertTrue(StringUtils.chop("a;b;c;").equals("a;b;c"));
		assertTrue(StringUtils.chop("Hello,").equals("Hello"));
		assertTrue(StringUtils.chop("abc\r\n").equals("abc"));
		assertTrue(StringUtils.unwrap("'ab'", "'").equals("ab"));

		// replace
		assertTrue(StringUtils.replaceIgnoreCase("FoOFoofoo", "foo", "ABC").equals("ABCABCABC"));
		assertTrue(StringUtils.replaceOnceIgnoreCase("FoOFoofoo", "foo", "ABC").equals("ABCFoofoo"));
		assertTrue(StringUtils.replacePattern("ABCabc123", "[a-z]", "_").equals("ABC___123"));
		assertTrue(
				StringUtils.replaceEach("abcde", new String[] { "ab", "d" }, new String[] { "d", "t" }).equals("dcte"));
		assertTrue(StringUtils.overlay("abcdef", "zzzz", 2, 4).equals("abzzzzef"));
		assertTrue(StringUtils.upperCase("aBc").equals("ABC"));
		assertTrue(StringUtils.capitalize("hello").equals("Hello"));
		assertTrue(StringUtils.uncapitalize("Hello").equals("hello"));
		assertTrue(StringUtils.swapCase("Hello").equals("hELLO"));
		assertTrue(StringUtils.normalizeSpace("    This is     what they call normalize space   ")
				.equals("This is what they call normalize space"));

		// add, join
		assertTrue(StringUtils.join("", null, "a").equals("a"));
		assertTrue(StringUtils.join("a", "b", "c").equals("abc"));
		String[] strs2 = { "a", "b", "c" };
		assertTrue(StringUtils.join(strs2, ';').equals("a;b;c"));
		assertTrue(StringUtils.join(Arrays.asList(strs2).iterator(), ';').equals("a;b;c"));
		assertTrue(StringUtils.joinWith(";", "a", "b", "c").equals("a;b;c"));
		assertTrue(StringUtils.repeat("Hello", 3).equals("HelloHelloHello"));
		assertTrue(StringUtils.repeat("Hello", ",", 3).equals("Hello,Hello,Hello"));
		assertTrue(StringUtils.rightPad("Hello", 3).equals("Hello"));
		assertTrue(StringUtils.rightPad("Hello", 10).equals("Hello     "));
		assertTrue(StringUtils.rightPad("Hello", 10, 'z').equals("Hellozzzzz"));
		assertTrue(StringUtils.rightPad("Hello", 10, "xyz").equals("Helloxyzxy"));
		assertTrue(StringUtils.leftPad("Hello", 10, "xyz").equals("xyzxyHello"));
		assertTrue(StringUtils.center("ME", 12, '$').equals("$$$$$ME$$$$$"));
		assertTrue(StringUtils.wrap("ab", '\'').equals("'ab'"));

		// equal, comparison
		assertTrue(StringUtils.equals(null, null));
		assertFalse(StringUtils.equals("abc", null));
		assertTrue(StringUtils.equalsIgnoreCase("abc", "Abc"));
		assertTrue(StringUtils.difference("hello world", "hello there").equals("there"));
		assertTrue(StringUtils.difference("hallo world", "hello there").equals("ello there"));
		assertTrue(StringUtils.indexOfDifference("hallo world", "hello there") == 1);

		// indexing, containing
		assertTrue(StringUtils.ordinalIndexOf("abcdefgabcabc", "abc", 3) == 10);
		assertTrue(StringUtils.indexOfAny("abcdefgabcabc", 'b', 'f') == 1);
		assertTrue(StringUtils.indexOfAny("abcdefgabcabc", "bf") == 1);
		assertTrue(StringUtils.indexOfAnyBut("abcdefgabcabc", "abc") == 3);
		assertFalse(StringUtils.contains(null, 'a'));
		assertTrue(StringUtils.containsIgnoreCase("abcdefg", "ABC"));
		assertTrue(StringUtils.containsWhitespace("ab cdefg"));// Whitespace is defined by Character.isWhitespace(char).
		assertTrue(StringUtils.containsAny("abcdefgabcabc", 'b', 'f'));
		assertTrue(StringUtils.containsAny("abcdefgabcabc", "bf"));
		assertFalse(StringUtils.containsAny("abcdefgabcabc", "bf", "ae"));
		assertTrue(StringUtils.containsAny("abcdefgabcabc", "bf", "ab"));
		assertTrue(StringUtils.containsOnly("abab", "abc"));
		assertFalse(StringUtils.containsOnly("abz", "abc"));
		assertTrue(StringUtils.containsNone("abab", "xyz"));
		assertFalse(StringUtils.containsOnly("abz", "xyz"));
		assertTrue(StringUtils.countMatches("abba", "ab") == 1);
		assertTrue(StringUtils.isAlphanumeric(RandomStringUtils.randomAlphanumeric(10)));
		assertTrue(StringUtils.startsWithIgnoreCase("abcxyz", "ABc"));
		assertTrue(StringUtils.startsWithAny("abcxyz", new String[] {"abc", "xyz"}));

		// are the following features really useful...
		assertNull(StringUtils.stripAccents(null));
		assertTrue(StringUtils.stripAccents("éclair").equals("eclair"));
		assertTrue(StringUtils.stripAccents("über").equals("uber"));
		assertTrue(StringUtils.rotate("abcdefg", 2).equals("fgabcde"));
		assertTrue(StringUtils.reverse("abcdefg").equals("gfedcba"));
		assertTrue(StringUtils.reverseDelimited("www.domain.com", '.').equals("com.domain.www"));
		assertTrue(StringUtils.abbreviate("This is a weird feature.", 10).equals("This is..."));
		assertTrue(StringUtils.abbreviate("This is a weird feature.", 12).equals("This is a..."));
		assertTrue(StringUtils.abbreviate("This is a weird feature.", 10, 12).equals("...weird ..."));
	}
}
