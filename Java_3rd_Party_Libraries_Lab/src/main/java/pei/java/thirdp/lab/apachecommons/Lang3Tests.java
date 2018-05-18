package pei.java.thirdp.lab.apachecommons;

import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

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
		String s1 = RandomStringUtils.random(20);
		String s2 = RandomStringUtils.randomAlphanumeric(20);
		String s3 = RandomStringUtils.randomAlphabetic(20);
		String s4 = RandomStringUtils.randomNumeric(20);

		assertThat(s1).hasSize(20);
		ifThen(s1.matches("\\w{20}"), () -> System.out.println("When can this happen???!!!"));

		assertThat(s2.matches("\\w{20}"));
		ifThen(s2.matches("[a-zA-Z]{20}"), () -> System.out.println("Not a single digit in the string!!!"));

		assertThat(s3.matches("[a-zA-Z]{20}"));
		assertThat(s3.matches("\\w{20}"));

		assertThat(s4.matches("\\d{20}"));

		System.out.println(s1);
		System.out.println(s2);
		System.out.println(s3);
		System.out.println(s4);
	}

	@Test
	public void testValidate() {
		assertThatThrownBy(() -> Validate.exclusiveBetween(100, 200, new Random().nextInt(100)))
				.isInstanceOf(IllegalArgumentException.class);
		assertThatThrownBy(() -> Validate.notBlank("   ")).isInstanceOf(IllegalArgumentException.class);
		assertThatThrownBy(() -> Validate.validState(false)).isInstanceOf(IllegalStateException.class);
	}

	@Test
	public void testStringUtils() throws Exception {
		/*
		 * Utils here are all NULL-SAFE
		 */
		assertThat(StringUtils.length(null)).isEqualTo(0);

		// constants
		assertThat(StringUtils.INDEX_NOT_FOUND).isEqualTo(-1);
		assertThat(StringUtils.SPACE).isEqualTo(" ");

		// empty, blank
		assertThat(StringUtils.isEmpty(null));
		assertThat(StringUtils.isEmpty(EMPTY));
		assertThat(StringUtils.isEmpty("   ")).isFalse();

		assertThat(StringUtils.isBlank(null));
		assertThat(StringUtils.isBlank(EMPTY));
		assertThat(StringUtils.isBlank("   "));

		assertThat(StringUtils.defaultString(null, DEFAULT_STR)).isEqualTo(DEFAULT_STR);
		assertThat(StringUtils.defaultString(STR, DEFAULT_STR)).isEqualTo(STR);

		assertThat(StringUtils.defaultIfBlank(null, DEFAULT_STR)).isEqualTo(DEFAULT_STR);
		assertThat(StringUtils.defaultIfBlank("       ", DEFAULT_STR)).isEqualTo(DEFAULT_STR);
		assertThat(StringUtils.defaultIfBlank(STR, DEFAULT_STR)).isEqualTo(STR);
		assertThat(StringUtils.defaultIfEmpty(null, DEFAULT_STR)).isEqualTo(DEFAULT_STR);
		assertThat(StringUtils.defaultIfEmpty("       ", DEFAULT_STR)).isEqualTo("       ");
		assertThat(StringUtils.defaultIfEmpty(STR, DEFAULT_STR)).isEqualTo(STR);

		// truncate, sub string, split
		assertThat(StringUtils.truncate(null, Math.abs(new Random().nextInt()))).isNull();
		assertThat(StringUtils.truncate("123456789", 5)).isEqualTo("12345");
		assertThat(StringUtils.truncate("123456789", 8)).isEqualTo("12345678");
		assertThat(StringUtils.truncate("123456789", 10)).isEqualTo("123456789");

		assertThatThrownBy(() -> StringUtils.truncate("123456789", -1)).isInstanceOf(IllegalArgumentException.class);

		assertThat(StringUtils.left("abcdef", 2)).isEqualTo("ab");
		assertThat(StringUtils.right("abcdef", 2)).isEqualTo("ef");
		assertThat(StringUtils.mid("abcdef", 3, 2)).isEqualTo("de");
		assertThat(StringUtils.substringBefore("abcdef", "cd")).isEqualTo("ab");
		assertThat(StringUtils.substringAfter("abcdef", "cd")).isEqualTo("ef");
		assertThat(StringUtils.substringBetween(",abcdef,", COMMA)).isEqualTo("abcdef");
		assertThat(StringUtils.substringBetween("...<tag>abcdef</tag>...", "<tag>", "</tag>")).isEqualTo("abcdef");

		assertThat(StringUtils.substringsBetween("[a][b][c]", "[", "]")).containsExactly("a", "b", "c");
		assertThat(StringUtils.split("abc  def")).containsExactly("abc", "def");
		assertThat(StringUtils.split("a..b.c", '.')).containsExactly("a", "b", "c");
		assertThat(StringUtils.splitByCharacterType("ab   de fg")).containsExactly("ab", "   ", "de", " ", "fg");
		assertThat(StringUtils.splitByCharacterType("foo200BarDrink")).containsExactly("foo", "200", "B", "ar", "D",
				"rink");
		assertThat(StringUtils.splitByCharacterTypeCamelCase("foo200BarDrink")).containsExactly("foo", "200", "Bar",
				"Drink");

		assertThat(StringUtils.getDigits("(541) 754-3010")).isEqualTo("5417543010");

		// trim, strip, remove
		assertThat(StringUtils.trim(null)).isNull();
		assertThat(StringUtils.trim(EMPTY)).isEqualTo(EMPTY);
		assertThat(StringUtils.trim("   ")).isEqualTo(EMPTY);
		assertThat(StringUtils.trim("    abc    ")).isEqualTo("abc");
		assertThat(StringUtils.strip(null, "abc")).isNull();
		assertThat(StringUtils.strip(" abc ", null)).isEqualTo("abc");
		assertThat(StringUtils.strip("  abcyx", "xyz")).isEqualTo("  abc");
		assertThat(StringUtils.stripStart("y xabc  ", "xyz ")).isEqualTo("abc  ");
		assertThat(StringUtils.stripStart("$$$%%%---a-b-c  ", "xyz$%- ")).isEqualTo("a-b-c  ");
		assertThat(StringUtils.deleteWhitespace("   ab  c  ")).isEqualTo("abc");
		assertThat(StringUtils.remove("www.domain.www.com", "www.")).isEqualTo("domain.com");
		assertThat(StringUtils.removeIgnoreCase("www.domain.www.com", "www.")).isEqualTo("domain.com");
		assertThat(StringUtils.removeStart("www.domain.www.com", "www.")).isEqualTo("domain.www.com");
		assertThat(StringUtils.removeEnd("a;b;c;", SEMICOLON)).isEqualTo("a;b;c");
		assertThat(StringUtils.removeEndIgnoreCase("www.domain.COM", ".com")).isEqualTo("www.domain");
		assertThat(StringUtils.removeAll("ABCabc123abc", "[a-z]")).isEqualTo("ABC123");
		assertThat(StringUtils.removePattern("ABCabc123", "[a-z]")).isEqualTo("ABC123");
		assertThat(StringUtils.chomp("abc\r")).isEqualTo("abc");
		assertThat(StringUtils.chomp("abc\n")).isEqualTo("abc");
		assertThat(StringUtils.chomp("abc\r\n")).isEqualTo("abc");
		assertThat(StringUtils.chomp("abc\r\n\r\n")).isEqualTo("abc\r\n");
		assertThat(StringUtils.chop("a;b;c;")).isEqualTo("a;b;c");
		assertThat(StringUtils.chop("Hello,")).isEqualTo("Hello");
		assertThat(StringUtils.chop("abc\r\n")).isEqualTo("abc");
		assertThat(StringUtils.unwrap("'ab'", "'")).isEqualTo("ab");

		// replace
		assertThat(StringUtils.replaceIgnoreCase("FoOFoofoo", "foo", "ABC")).isEqualTo("ABCABCABC");
		assertThat(StringUtils.replaceOnceIgnoreCase("FoOFoofoo", "foo", "ABC")).isEqualTo("ABCFoofoo");
		assertThat(StringUtils.replacePattern("ABCabc123", "[a-z]", "_")).isEqualTo("ABC___123");
		assertThat(StringUtils.replaceEach("abcde", new String[] { "ab", "d" }, new String[] { "d", "t" }))
				.isEqualTo("dcte");
		assertThat(StringUtils.overlay("abcdef", "zzzz", 2, 4)).isEqualTo("abzzzzef");
		assertThat(StringUtils.upperCase("aBc")).isEqualTo("ABC");
		assertThat(StringUtils.capitalize("hello")).isEqualTo("Hello");
		assertThat(StringUtils.uncapitalize("Hello")).isEqualTo("hello");
		assertThat(StringUtils.swapCase("Hello")).isEqualTo("hELLO");
		assertThat(StringUtils.normalizeSpace("    This is     what they call normalize space   "))
				.isEqualTo("This is what they call normalize space");

		// add, join
		assertThat(StringUtils.join(EMPTY, null, "a")).isEqualTo("a");
		assertThat(StringUtils.join("a", "b", "c")).isEqualTo("abc");
		assertThat(StringUtils.join(TEST_INT_ARRAY_123, ';')).isEqualTo("1;2;3");
		assertThat(StringUtils.join(TEST_LIST_123.iterator(), ';')).isEqualTo("1;2;3");
		assertThat(StringUtils.joinWith(SEMICOLON, "a", "b", "c")).isEqualTo("a;b;c");
		assertThat(StringUtils.repeat("Hello", 3)).isEqualTo("HelloHelloHello");
		assertThat(StringUtils.repeat("Hello", COMMA, 3)).isEqualTo("Hello,Hello,Hello");
		assertThat(StringUtils.rightPad("Hello", 3)).isEqualTo("Hello");
		assertThat(StringUtils.rightPad("Hello", 10)).isEqualTo("Hello     ");
		assertThat(StringUtils.rightPad("Hello", 10, 'z')).isEqualTo("Hellozzzzz");
		assertThat(StringUtils.rightPad("Hello", 10, "xyz")).isEqualTo("Helloxyzxy");
		assertThat(StringUtils.leftPad("Hello", 10, "xyz")).isEqualTo("xyzxyHello");
		assertThat(StringUtils.center("ME", 12, '$')).isEqualTo("$$$$$ME$$$$$");
		assertThat(StringUtils.wrap("ab", '\'')).isEqualTo("'ab'");

		// equal, comparison
		assertThat(StringUtils.equals(null, null));
		assertThat(StringUtils.equals("abc", null)).isFalse();
		assertThat(StringUtils.equalsIgnoreCase("abc", "Abc"));
		assertThat(StringUtils.difference("hello world", "hello there")).isEqualTo("there");
		assertThat(StringUtils.difference("hallo world", "hello there")).isEqualTo("ello there");
		assertThat(StringUtils.indexOfDifference("hallo world", "hello there") == 1);

		// indexing, containing
		assertThat(StringUtils.ordinalIndexOf("abcdefgabcabc", "abc", 3)).isEqualTo(10);
		assertThat(StringUtils.indexOfAny("abcdefgabcabc", 'b', 'f')).isEqualTo(1);
		assertThat(StringUtils.indexOfAny("abcdefgabcabc", "bf")).isEqualTo(1);
		assertThat(StringUtils.indexOfAnyBut("abcdefgabcabc", "abc")).isEqualTo(3);
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
		assertThat(StringUtils.stripAccents("éclair")).isEqualTo("eclair");
		assertThat(StringUtils.stripAccents("über")).isEqualTo("uber");
		assertThat(StringUtils.rotate("abcdefg", 2)).isEqualTo("fgabcde");
		assertThat(StringUtils.reverse("abcdefg")).isEqualTo("gfedcba");
		assertThat(StringUtils.reverseDelimited("www.domain.com", '.')).isEqualTo("com.domain.www");
		assertThat(StringUtils.abbreviate("This is a weird feature.", 10)).isEqualTo("This is...");
		assertThat(StringUtils.abbreviate("This is a weird feature.", 12)).isEqualTo("This is a...");
		assertThat(StringUtils.abbreviate("This is a weird feature.", 10, 12)).isEqualTo("...weird ...");
	}
}
