package pei.java.thirdp.lab.apachecommons;

import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.junit.jupiter.api.Test;

import com.github.peiatgithub.java.utils.JavaRegexBuilder;
import com.google.common.base.Strings;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static com.github.peiatgithub.java.utils.Constants.*;

/**
 * @author pei
 */
public class Lang3Tests {

    @Test
    public void testRandomString() throws Exception {

        int n = 20;

        assertThat(RandomStringUtils.random(n)).hasSize(n).matches(getRegexBuilder().anyChar(n / 2, n).build());

        assertThat(RandomStringUtils.randomAlphanumeric(n)).hasSize(n)
                .matches(getRegexBuilder().lettersAndNumbers(n).build());

        assertThat(RandomStringUtils.randomAlphabetic(n)).hasSize(n)
                .matches(getRegexBuilder().lettersIgnoreCase(n).build());

        assertThat(RandomStringUtils.randomNumeric(n)).hasSize(n).matches(getRegexBuilder().digit(n).build());

    }

    @Test
    public void testValidate() {
        assertThatThrownBy(() -> Validate.exclusiveBetween(100, 200, new Random().nextInt(100))).isInstanceOf(IAE);
        assertThatThrownBy(() -> Validate.notBlank(Strings.repeat(SPACE, 3))).isInstanceOf(IAE);
        assertThatThrownBy(() -> Validate.validState(false)).isInstanceOf(ISE);
    }

    @Test
    public void testStringUtils() throws Exception {
        /*
         * Utils here are all NULL-SAFE
         */
        assertEquals(0, StringUtils.length(null));

        // constants
        assertEquals(-1, StringUtils.INDEX_NOT_FOUND);
        assertEquals(" ", StringUtils.SPACE);

        // empty, blank
        assertTrue(StringUtils.isEmpty(null));
        assertTrue(StringUtils.isEmpty(EMPTY));
        assertFalse(StringUtils.isEmpty("   "));

        assertTrue(StringUtils.isBlank(null));
        assertTrue(StringUtils.isBlank(EMPTY));
        assertTrue(StringUtils.isBlank("   "));

        assertEquals(DEFAULT_STR, StringUtils.defaultString(null, DEFAULT_STR));
        assertEquals(STR, StringUtils.defaultString(STR, DEFAULT_STR));

        assertEquals(DEFAULT_STR, StringUtils.defaultIfBlank(null, DEFAULT_STR));
        assertEquals(DEFAULT_STR, StringUtils.defaultIfBlank("       ", DEFAULT_STR));
        assertEquals(STR, StringUtils.defaultIfBlank(STR, DEFAULT_STR));
        assertEquals(DEFAULT_STR, StringUtils.defaultIfEmpty(null, DEFAULT_STR));
        assertEquals("       ", StringUtils.defaultIfEmpty("       ", DEFAULT_STR));
        assertEquals(STR, StringUtils.defaultIfEmpty(STR, DEFAULT_STR));

        // truncate, sub string, split
        assertNull(StringUtils.truncate(null, Math.abs(new Random().nextInt())));
        assertEquals("12345", StringUtils.truncate("123456789", 5));
        assertEquals("12345678", StringUtils.truncate("123456789", 8));
        assertEquals("123456789", StringUtils.truncate("123456789", 10));

        assertThatThrownBy(() -> StringUtils.truncate("123456789", -1)).isInstanceOf(IAE);

        assertEquals("ab", StringUtils.left("abcdef", 2));
        assertEquals("ef", StringUtils.right("abcdef", 2));
        assertEquals("de", StringUtils.mid("abcdef", 3, 2));
        assertEquals("ab", StringUtils.substringBefore("abcdef", "cd"));
        assertEquals("ef", StringUtils.substringAfter("abcdef", "cd"));
        assertEquals("abcdef", StringUtils.substringBetween(",abcdef,", COMMA));
        assertEquals("abcdef", StringUtils.substringBetween("...<tag>abcdef</tag>...", "<tag>", "</tag>"));

        assertThat(StringUtils.substringsBetween("[a][b][c]", "[", "]")).containsExactly("a", "b", "c");
        assertThat(StringUtils.split("abc  def")).containsExactly("abc", "def");
        assertThat(StringUtils.split("a..b.c", '.')).containsExactly("a", "b", "c");
        assertThat(StringUtils.splitByCharacterType("ab   de fg")).containsExactly("ab", "   ", "de", " ", "fg");
        assertThat(StringUtils.splitByCharacterType("foo200BarDrink")).containsExactly("foo", "200", "B", "ar", "D",
                "rink");
        assertThat(StringUtils.splitByCharacterTypeCamelCase("foo200BarDrink")).containsExactly("foo", "200", "Bar",
                "Drink");

        assertEquals("5417543010", StringUtils.getDigits("(541) 754-3010"));

        // trim, strip, remove
        assertNull(StringUtils.trim(null));
        assertEquals(EMPTY, StringUtils.trim(EMPTY));
        assertEquals(EMPTY, StringUtils.trim("   "));
        assertEquals("abc", StringUtils.trim("    abc    "));
        assertNull(StringUtils.strip(null, "abc"));
        assertEquals("abc", StringUtils.strip(" abc ", null));
        assertEquals("  abc", StringUtils.strip("  abcyx", "xyz"));
        assertEquals("abc  ", StringUtils.stripStart("y xabc  ", "xyz "));
        assertEquals("a-b-c  ", StringUtils.stripStart("$$$%%%---a-b-c  ", "xyz$%- "));
        assertEquals("abc", StringUtils.deleteWhitespace("   ab  c  "));
        assertEquals("domain.com", StringUtils.remove("www.domain.www.com", "www."));
        assertEquals("domain.com", StringUtils.removeIgnoreCase("www.domain.www.com", "www."));
        assertEquals("domain.www.com", StringUtils.removeStart("www.domain.www.com", "www."));
        assertEquals("a;b;c", StringUtils.removeEnd("a;b;c;", SEMICOLON));
        assertEquals("www.domain", StringUtils.removeEndIgnoreCase("www.domain.COM", ".com"));
        assertEquals("ABC123", StringUtils.removeAll("ABCabc123abc", "[a-z]"));
        assertEquals("ABC123", StringUtils.removePattern("ABCabc123", "[a-z]"));
        assertEquals("abc", StringUtils.chomp("abc\r"));
        assertEquals("abc", StringUtils.chomp("abc\n"));
        assertEquals("abc", StringUtils.chomp("abc\r\n"));
        assertEquals("abc\r\n", StringUtils.chomp("abc\r\n\r\n"));
        assertEquals("a;b;c", StringUtils.chop("a;b;c;"));
        assertEquals("Hello", StringUtils.chop("Hello,"));
        assertEquals("abc", StringUtils.chop("abc\r\n"));
        assertEquals("ab", StringUtils.unwrap("'ab'", "'"));

        // replace
        assertEquals("ABCABCABC", StringUtils.replaceIgnoreCase("FoOFoofoo", "foo", "ABC"));
        assertEquals("ABCFoofoo", StringUtils.replaceOnceIgnoreCase("FoOFoofoo", "foo", "ABC"));
        assertEquals("ABC___123", StringUtils.replacePattern("ABCabc123", "[a-z]", "_"));
        assertEquals("dcte", StringUtils.replaceEach("abcde", new String[] { "ab", "d" }, new String[] { "d", "t" }));
        assertEquals("abzzzzef", StringUtils.overlay("abcdef", "zzzz", 2, 4));
        assertEquals("ABC", StringUtils.upperCase("aBc"));
        assertEquals("Hello", StringUtils.capitalize("hello"));
        assertEquals("hello", StringUtils.uncapitalize("Hello"));
        assertEquals("hELLO", StringUtils.swapCase("Hello"));
        assertEquals("This is what they call normalize space",
                StringUtils.normalizeSpace("    This is     what they call normalize space   "));

        // add, join
        assertEquals("a", StringUtils.join(EMPTY, null, "a"));
        assertEquals("abc", StringUtils.join("a", "b", "c"));
        assertEquals("1;2;3", StringUtils.join(TEST_INT_ARRAY_123, ';'));
        assertEquals("1;", StringUtils.join(1, ';'));
        assertEquals("1;2;3", StringUtils.join(TEST_LIST_123.iterator(), ';'));
        assertEquals("a;b;c", StringUtils.joinWith(SEMICOLON, "a", "b", "c"));
        assertEquals("HelloHelloHello", StringUtils.repeat("Hello", 3));
        assertEquals("Hello,Hello,Hello", StringUtils.repeat("Hello", COMMA, 3));
        assertEquals("Hello", StringUtils.rightPad("Hello", 3));
        assertEquals("Hello     ", StringUtils.rightPad("Hello", 10));
        assertEquals("Hellozzzzz", StringUtils.rightPad("Hello", 10, 'z'));
        assertEquals("Helloxyzxy", StringUtils.rightPad("Hello", 10, "xyz"));
        assertEquals("xyzxyHello", StringUtils.leftPad("Hello", 10, "xyz"));
        assertEquals("$$$$$ME$$$$$", StringUtils.center("ME", 12, '$'));
        assertEquals("'ab'", StringUtils.wrap("ab", '\''));

        // equal, comparison
        assertTrue(StringUtils.equals(null, null));
        assertFalse(StringUtils.equals("abc", null));
        assertTrue(StringUtils.equalsIgnoreCase("abc", "Abc"));
        assertEquals("there", StringUtils.difference("hello world", "hello there"));
        assertEquals("ello there", StringUtils.difference("hallo world", "hello there"));
        assertEquals(1, StringUtils.indexOfDifference("hallo world", "hello there"));

        // indexing, containing
        assertEquals(10, StringUtils.ordinalIndexOf("abcdefgabcabc", "abc", 3));
        assertEquals(1, StringUtils.indexOfAny("abcdefgabcabc", 'b', 'f'));
        assertEquals(1, StringUtils.indexOfAny("abcdefgabcabc", "bf"));
        assertEquals(3, StringUtils.indexOfAnyBut("abcdefgabcabc", "abc"));
        assertFalse(StringUtils.contains(null, 'a'));
        assertTrue(StringUtils.containsIgnoreCase("abcdefg", "ABC"));
        assertTrue(StringUtils.containsWhitespace("ab cdefg"));// Whitespace is defined by Character.isWhitespace(char).
        assertTrue(StringUtils.containsAny("abcdefgabcabc", 'b', 'f'));
        assertTrue(StringUtils.containsAny("abcdefgabcabc", "bf"));
        assertFalse(StringUtils.containsAny("abcdefgabcabc", "bf", "ae"));
        assertTrue(StringUtils.containsAny("abcdefgabcabc", "bf", "ab"));
        assertTrue(StringUtils.containsOnly("abab", "abc"));
        assertTrue(StringUtils.containsOnly("abz", "abc"));
        assertTrue(StringUtils.containsNone("abab", "xyz"));
        assertTrue(StringUtils.containsOnly("abz", "xyz"));
        assertEquals(1, StringUtils.countMatches("abba", "ab"));
        assertTrue(StringUtils.isAlphanumeric(RandomStringUtils.randomAlphanumeric(10)));
        assertTrue(StringUtils.startsWithIgnoreCase("abcxyz", "ABc"));
        assertTrue(StringUtils.startsWithAny("abcxyz", new String[] { "abc", "xyz" }));

        // are the following features really useful...
        assertNull(StringUtils.stripAccents(null));
        assertEquals("eclair", StringUtils.stripAccents("éclair"));
        assertEquals("uber", StringUtils.stripAccents("über"));
        assertEquals("fgabcde", StringUtils.rotate("abcdefg", 2));
        assertEquals("gfedcba", StringUtils.reverse("abcdefg"));
        assertEquals("com.domain.www", StringUtils.reverseDelimited("www.domain.com", '.'));
        assertEquals("This is...", StringUtils.abbreviate("This is a weird feature.", 10));
        assertEquals("This is a...", StringUtils.abbreviate("This is a weird feature.", 12));
        assertEquals("...weird ...", StringUtils.abbreviate("This is a weird feature.", 10, 12));
    }

    /*
     * 
     */

    private JavaRegexBuilder getRegexBuilder() {
        return new JavaRegexBuilder();
    }

}
