package pei.java.thirdp.lab.apachecommons;

import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.junit.Test;

import com.github.peiatgithub.java.utils.JavaRegexBuilder;
import com.google.common.base.Strings;

import static org.assertj.core.api.Assertions.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static com.github.peiatgithub.java.utils.Utils.*;
import static com.github.peiatgithub.java.utils.Constants.*;

/**
 * 
 * @author pei
 *
 */
public class Lang3Tests {

	@Test
	public void testRandomString() throws Exception {
		
		int n = 20;
		
		assertThat(RandomStringUtils.random(n)).hasSize(n).matches(getRegexBuilder().anyChar(n/2, n).build());

		assertThat(RandomStringUtils.randomAlphanumeric(n)).hasSize(n)
			.matches(getRegexBuilder().lettersAndNumbers(n).build());

		assertThat(RandomStringUtils.randomAlphabetic(n)).hasSize(n)
			.matches(getRegexBuilder().lettersIgnoreCase(n).build());

		assertThat(RandomStringUtils.randomNumeric(n)).hasSize(n).matches(getRegexBuilder().digit(n).build());

	}

	@Test
	public void testValidate() {
		assertThatThrownBy(() -> Validate.exclusiveBetween(100, 200, new Random().nextInt(100)))
				.isInstanceOf(IAE);
		assertThatThrownBy(() -> Validate.notBlank(Strings.repeat(SPACE, 3))).isInstanceOf(IAE);
		assertThatThrownBy(() -> Validate.validState(false)).isInstanceOf(ISE);
	}

	@Test
	public void testStringUtils() throws Exception {
		/*
		 * Utils here are all NULL-SAFE
		 */
		assertThat(StringUtils.length(null), is(0));

		// constants
		assertThat(StringUtils.INDEX_NOT_FOUND, is(-1));
		assertThat(StringUtils.SPACE, is(" "));

		// empty, blank
		assertThat(StringUtils.isEmpty(null));
		assertThat(StringUtils.isEmpty(EMPTY));
		assertThat(StringUtils.isEmpty("   ")).isFalse();

		assertThat(StringUtils.isBlank(null));
		assertThat(StringUtils.isBlank(EMPTY));
		assertThat(StringUtils.isBlank("   "));

		assertThat(StringUtils.defaultString(null, DEFAULT_STR), is(DEFAULT_STR));
		assertThat(StringUtils.defaultString(STR, DEFAULT_STR), is(STR));

		assertThat(StringUtils.defaultIfBlank(null, DEFAULT_STR), is(DEFAULT_STR));
		assertThat(StringUtils.defaultIfBlank("       ", DEFAULT_STR), is(DEFAULT_STR));
		assertThat(StringUtils.defaultIfBlank(STR, DEFAULT_STR), is(STR));
		assertThat(StringUtils.defaultIfEmpty(null, DEFAULT_STR), is(DEFAULT_STR));
		assertThat(StringUtils.defaultIfEmpty("       ", DEFAULT_STR), is("       "));
		assertThat(StringUtils.defaultIfEmpty(STR, DEFAULT_STR), is(STR));

		// truncate, sub string, split
		assertThat(StringUtils.truncate(null, Math.abs(new Random().nextInt()))).isNull();
		assertThat(StringUtils.truncate("123456789", 5), is("12345"));
		assertThat(StringUtils.truncate("123456789", 8), is("12345678"));
		assertThat(StringUtils.truncate("123456789", 10), is("123456789"));

		assertThatThrownBy(() -> StringUtils.truncate("123456789", -1)).isInstanceOf(IAE);

		assertThat(StringUtils.left("abcdef", 2), is("ab"));
		assertThat(StringUtils.right("abcdef", 2), is("ef"));
		assertThat(StringUtils.mid("abcdef", 3, 2), is("de"));
		assertThat(StringUtils.substringBefore("abcdef", "cd"), is("ab"));
		assertThat(StringUtils.substringAfter("abcdef", "cd"), is("ef"));
		assertThat(StringUtils.substringBetween(",abcdef,", COMMA), is("abcdef"));
		assertThat(StringUtils.substringBetween("...<tag>abcdef</tag>...", "<tag>", "</tag>"), is("abcdef"));

		assertThat(StringUtils.substringsBetween("[a][b][c]", "[", "]")).containsExactly("a", "b", "c");
		assertThat(StringUtils.split("abc  def")).containsExactly("abc", "def");
		assertThat(StringUtils.split("a..b.c", '.')).containsExactly("a", "b", "c");
		assertThat(StringUtils.splitByCharacterType("ab   de fg")).containsExactly("ab", "   ", "de", " ", "fg");
		assertThat(StringUtils.splitByCharacterType("foo200BarDrink")).containsExactly("foo", "200", "B", "ar", "D",
				"rink");
		assertThat(StringUtils.splitByCharacterTypeCamelCase("foo200BarDrink")).containsExactly("foo", "200", "Bar",
				"Drink");

		assertThat(StringUtils.getDigits("(541) 754-3010"), is("5417543010"));

		// trim, strip, remove
		assertThat(StringUtils.trim(null)).isNull();
		assertThat(StringUtils.trim(EMPTY), is(EMPTY));
		assertThat(StringUtils.trim("   "), is(EMPTY));
		assertThat(StringUtils.trim("    abc    "), is("abc"));
		assertThat(StringUtils.strip(null, "abc")).isNull();
		assertThat(StringUtils.strip(" abc ", null), is("abc"));
		assertThat(StringUtils.strip("  abcyx", "xyz"), is("  abc"));
		assertThat(StringUtils.stripStart("y xabc  ", "xyz "), is("abc  "));
		assertThat(StringUtils.stripStart("$$$%%%---a-b-c  ", "xyz$%- "), is("a-b-c  "));
		assertThat(StringUtils.deleteWhitespace("   ab  c  "), is("abc"));
		assertThat(StringUtils.remove("www.domain.www.com", "www."), is("domain.com"));
		assertThat(StringUtils.removeIgnoreCase("www.domain.www.com", "www."), is("domain.com"));
		assertThat(StringUtils.removeStart("www.domain.www.com", "www."), is("domain.www.com"));
		assertThat(StringUtils.removeEnd("a;b;c;", SEMICOLON), is("a;b;c"));
		assertThat(StringUtils.removeEndIgnoreCase("www.domain.COM", ".com"), is("www.domain"));
		assertThat(StringUtils.removeAll("ABCabc123abc", "[a-z]"), is("ABC123"));
		assertThat(StringUtils.removePattern("ABCabc123", "[a-z]"), is("ABC123"));
		assertThat(StringUtils.chomp("abc\r"), is("abc"));
		assertThat(StringUtils.chomp("abc\n"), is("abc"));
		assertThat(StringUtils.chomp("abc\r\n"), is("abc"));
		assertThat(StringUtils.chomp("abc\r\n\r\n"), is("abc\r\n"));
		assertThat(StringUtils.chop("a;b;c;"), is("a;b;c"));
		assertThat(StringUtils.chop("Hello,"), is("Hello"));
		assertThat(StringUtils.chop("abc\r\n"), is("abc"));
		assertThat(StringUtils.unwrap("'ab'", "'"), is("ab"));

		// replace
		assertThat(StringUtils.replaceIgnoreCase("FoOFoofoo", "foo", "ABC"), is("ABCABCABC"));
		assertThat(StringUtils.replaceOnceIgnoreCase("FoOFoofoo", "foo", "ABC"), is("ABCFoofoo"));
		assertThat(StringUtils.replacePattern("ABCabc123", "[a-z]", "_"), is("ABC___123"));
		assertThat(StringUtils.replaceEach("abcde", new String[] { "ab", "d" }, new String[] { "d", "t" }), is("dcte"));
		assertThat(StringUtils.overlay("abcdef", "zzzz", 2, 4), is("abzzzzef"));
		assertThat(StringUtils.upperCase("aBc"), is("ABC"));
		assertThat(StringUtils.capitalize("hello"), is("Hello"));
		assertThat(StringUtils.uncapitalize("Hello"), is("hello"));
		assertThat(StringUtils.swapCase("Hello"), is("hELLO"));
		assertThat(StringUtils.normalizeSpace("    This is     what they call normalize space   ")
				, is("This is what they call normalize space"));

		// add, join
		assertThat(StringUtils.join(EMPTY, null, "a"), is("a"));
		assertThat(StringUtils.join("a", "b", "c"), is("abc"));
		assertThat(StringUtils.join(TEST_INT_ARRAY_123, ';'), is("1;2;3"));
		assertThat(StringUtils.join(1, ';'), is("1;"));
		assertThat(StringUtils.join(TEST_LIST_123.iterator(), ';'), is("1;2;3"));
		assertThat(StringUtils.joinWith(SEMICOLON, "a", "b", "c"), is("a;b;c"));
		assertThat(StringUtils.repeat("Hello", 3), is("HelloHelloHello"));
		assertThat(StringUtils.repeat("Hello", COMMA, 3), is("Hello,Hello,Hello"));
		assertThat(StringUtils.rightPad("Hello", 3), is("Hello"));
		assertThat(StringUtils.rightPad("Hello", 10), is("Hello     "));
		assertThat(StringUtils.rightPad("Hello", 10, 'z'), is("Hellozzzzz"));
		assertThat(StringUtils.rightPad("Hello", 10, "xyz"), is("Helloxyzxy"));
		assertThat(StringUtils.leftPad("Hello", 10, "xyz"), is("xyzxyHello"));
		assertThat(StringUtils.center("ME", 12, '$'), is("$$$$$ME$$$$$"));
		assertThat(StringUtils.wrap("ab", '\''), is("'ab'"));

		// equal, comparison
		assertThat(StringUtils.equals(null, null));
		assertThat(StringUtils.equals("abc", null)).isFalse();
		assertThat(StringUtils.equalsIgnoreCase("abc", "Abc"));
		assertThat(StringUtils.difference("hello world", "hello there"), is("there"));
		assertThat(StringUtils.difference("hallo world", "hello there"), is("ello there"));
		assertThat(StringUtils.indexOfDifference("hallo world", "hello there") == 1);

		// indexing, containing
		assertThat(StringUtils.ordinalIndexOf("abcdefgabcabc", "abc", 3), is(10));
		assertThat(StringUtils.indexOfAny("abcdefgabcabc", 'b', 'f'), is(1));
		assertThat(StringUtils.indexOfAny("abcdefgabcabc", "bf"), is(1));
		assertThat(StringUtils.indexOfAnyBut("abcdefgabcabc", "abc"), is(3));
		assertThat(StringUtils.contains(null, 'a')).isFalse();
		assertThat(StringUtils.containsIgnoreCase("abcdefg", "ABC"));
		assertThat(StringUtils.containsWhitespace("ab cdefg"));// Whitespace is defined by Character.isWhitespace(char).
		assertThat(StringUtils.containsAny("abcdefgabcabc", 'b', 'f'));
		assertThat(StringUtils.containsAny("abcdefgabcabc", "bf"));
		assertThat(StringUtils.containsAny("abcdefgabcabc", "bf", "ae")).isFalse();
		assertThat(StringUtils.containsAny("abcdefgabcabc", "bf", "ab"));
		assertThat(StringUtils.containsOnly("abab", "abc"));
		assertThat(StringUtils.containsOnly("abz", "abc")).isFalse();
		assertThat(StringUtils.containsNone("abab", "xyz"));
		assertThat(StringUtils.containsOnly("abz", "xyz")).isFalse();
		assertThat(StringUtils.countMatches("abba", "ab") == 1);
		assertThat(StringUtils.isAlphanumeric(RandomStringUtils.randomAlphanumeric(10)));
		assertThat(StringUtils.startsWithIgnoreCase("abcxyz", "ABc"));
		assertThat(StringUtils.startsWithAny("abcxyz", new String[] { "abc", "xyz" }));

		// are the following features really useful...
		assertThat(StringUtils.stripAccents(null)).isNull();
		assertThat(StringUtils.stripAccents("éclair"), is("eclair"));
		assertThat(StringUtils.stripAccents("über"), is("uber"));
		assertThat(StringUtils.rotate("abcdefg", 2), is("fgabcde"));
		assertThat(StringUtils.reverse("abcdefg"), is("gfedcba"));
		assertThat(StringUtils.reverseDelimited("www.domain.com", '.'), is("com.domain.www"));
		assertThat(StringUtils.abbreviate("This is a weird feature.", 10), is("This is..."));
		assertThat(StringUtils.abbreviate("This is a weird feature.", 12), is("This is a..."));
		assertThat(StringUtils.abbreviate("This is a weird feature.", 10, 12), is("...weird ..."));
	}
	
	/*
	 * 
	 */
	
	private JavaRegexBuilder getRegexBuilder() {
		return new JavaRegexBuilder();
	}
		
}
