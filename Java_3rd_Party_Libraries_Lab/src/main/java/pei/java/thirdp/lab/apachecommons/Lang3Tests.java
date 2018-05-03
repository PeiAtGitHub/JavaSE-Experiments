package pei.java.thirdp.lab.apachecommons;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.junit.Test;

import static pei.java.thirdp.lab.utils.Utils.*;

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

        assertThat(s1.length(), is(20));
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
        assertThat(catchThrowable(()->Validate.exclusiveBetween(100, 200, new Random().nextInt(100)))
                ,instanceOf(IllegalArgumentException.class));
        
        assertThat(catchThrowable(()->Validate.notBlank("   "))
                ,instanceOf(IllegalArgumentException.class));

        assertThat(catchThrowable(()->Validate.validState(false))
                ,instanceOf(IllegalStateException.class));
    }

    @Test
    public void testStringUtils() throws Exception {
        /*
         * Utils here are all NULL-SAFE
         */
        // just null-safe
        assertEquals(0, StringUtils.length(null));
        
        // constants
        assertThat(StringUtils.INDEX_NOT_FOUND, is(-1));
        assertThat(StringUtils.SPACE, is(" "));

        // empty, blank
        assertTrue(StringUtils.isEmpty(null));
        assertTrue(StringUtils.isEmpty(""));
        assertFalse(StringUtils.isEmpty("   "));

        assertTrue(StringUtils.isBlank(null));
        assertTrue(StringUtils.isBlank(""));
        assertTrue(StringUtils.isBlank("   "));
        
        assertThat(StringUtils.defaultString(null, DEFAULT_STR), is(DEFAULT_STR));
        assertThat(StringUtils.defaultString(STR, DEFAULT_STR), is(STR));

        assertThat(StringUtils.defaultIfBlank(null, DEFAULT_STR), is(DEFAULT_STR));
        assertThat(StringUtils.defaultIfBlank("       ", DEFAULT_STR), is(DEFAULT_STR));
        assertThat(StringUtils.defaultIfBlank(STR, DEFAULT_STR), is(STR));
        assertThat(StringUtils.defaultIfEmpty(null, DEFAULT_STR), is(DEFAULT_STR));
        assertThat(StringUtils.defaultIfEmpty("       ", DEFAULT_STR), is("       "));
        assertThat(StringUtils.defaultIfEmpty(STR, DEFAULT_STR), is(STR));

        // truncate, sub string, split
        assertNull(StringUtils.truncate(null, Math.abs(new Random().nextInt())));
        assertThat(StringUtils.truncate("123456789", 5), is("12345"));
        assertThat(StringUtils.truncate("123456789", 8), is("12345678"));
        assertThat(StringUtils.truncate("123456789", 10), is("123456789"));

        assertThat(catchThrowable(()->StringUtils.truncate("123456789", -1)),
                        instanceOf(IllegalArgumentException.class));
        
        assertThat(StringUtils.left("abcdef", 2), is("ab"));
        assertThat(StringUtils.right("abcdef", 2), is("ef"));
        assertThat(StringUtils.mid("abcdef", 3, 2), is("de"));
        assertThat(StringUtils.substringBefore("abcdef", "cd"), is("ab"));
        assertThat(StringUtils.substringAfter("abcdef", "cd"), is("ef"));
        assertThat(StringUtils.substringBetween(",abcdef,", ","), is("abcdef"));
        assertThat(StringUtils.substringBetween("...<tag>abcdef</tag>...", "<tag>", "</tag>"), is("abcdef"));

        assertThat(getReadableArrayString(StringUtils.substringsBetween("[a][b][c]", "[", "]"))
        		, is("[a, b, c]"));
        
        assertThat(getReadableArrayString(StringUtils.split("abc  def"))
        		, is("[abc, def]"));
        assertThat(getReadableArrayString(StringUtils.split("a..b.c", '.'))
        		, is("[a, b, c]"));
        assertThat(getReadableArrayString(StringUtils.splitByCharacterType("ab   de fg"))
        		, is("[ab,    , de,  , fg]"));
        assertThat(getReadableArrayString(StringUtils.splitByCharacterType("foo200BarDrink"))
        		, is("[foo, 200, B, ar, D, rink]"));
        assertThat(getReadableArrayString(StringUtils.splitByCharacterTypeCamelCase("foo200BarDrink"))
        		, is("[foo, 200, Bar, Drink]"));
        
        assertThat(StringUtils.getDigits("(541) 754-3010"), is("5417543010"));

        // trim, strip, remove
        assertNull(StringUtils.trim(null));
        assertThat(StringUtils.trim(""), is(""));
        assertThat(StringUtils.trim("   "), is(""));
        assertThat(StringUtils.trim("    abc    "), is("abc"));
        assertNull(StringUtils.strip(null, "abc"));
        assertThat(StringUtils.strip(" abc ", null), is("abc"));
        assertThat(StringUtils.strip("  abcyx", "xyz"), is("  abc"));
        assertThat(StringUtils.stripStart("y xabc  ", "xyz "), is("abc  "));
        assertThat(StringUtils.stripStart("$$$%%%---a-b-c  ", "xyz$%- "), is("a-b-c  "));
        assertThat(StringUtils.deleteWhitespace("   ab  c  "), is("abc"));
        assertThat(StringUtils.remove("www.domain.www.com", "www."), is("domain.com"));
        assertThat(StringUtils.removeIgnoreCase("www.domain.www.com", "www."), is("domain.com"));
        assertThat(StringUtils.removeStart("www.domain.www.com", "www."), is("domain.www.com"));
        assertThat(StringUtils.removeEnd("a;b;c;", ";"), is("a;b;c"));
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
        assertThat(
                StringUtils.replaceEach("abcde", new String[] { "ab", "d" }, new String[] { "d", "t" }), is("dcte"));
        assertThat(StringUtils.overlay("abcdef", "zzzz", 2, 4), is("abzzzzef"));
        assertThat(StringUtils.upperCase("aBc"), is("ABC"));
        assertThat(StringUtils.capitalize("hello"), is("Hello"));
        assertThat(StringUtils.uncapitalize("Hello"), is("hello"));
        assertThat(StringUtils.swapCase("Hello"), is("hELLO"));
        assertThat(StringUtils.normalizeSpace("    This is     what they call normalize space   ")
                , is("This is what they call normalize space"));

        // add, join
        assertThat(StringUtils.join("", null, "a"), is("a"));
        assertThat(StringUtils.join("a", "b", "c"), is("abc"));
        String[] strs2 = { "a", "b", "c" };
        assertThat(StringUtils.join(strs2, ';'), is("a;b;c"));
        assertThat(StringUtils.join(Arrays.asList(strs2).iterator(), ';'), is("a;b;c"));
        assertThat(StringUtils.joinWith(";", "a", "b", "c"), is("a;b;c"));
        assertThat(StringUtils.repeat("Hello", 3), is("HelloHelloHello"));
        assertThat(StringUtils.repeat("Hello", ",", 3), is("Hello,Hello,Hello"));
        assertThat(StringUtils.rightPad("Hello", 3), is("Hello"));
        assertThat(StringUtils.rightPad("Hello", 10), is("Hello     "));
        assertThat(StringUtils.rightPad("Hello", 10, 'z'), is("Hellozzzzz"));
        assertThat(StringUtils.rightPad("Hello", 10, "xyz"), is("Helloxyzxy"));
        assertThat(StringUtils.leftPad("Hello", 10, "xyz"), is("xyzxyHello"));
        assertThat(StringUtils.center("ME", 12, '$'), is("$$$$$ME$$$$$"));
        assertThat(StringUtils.wrap("ab", '\''), is("'ab'"));

        // equal, comparison
        assertTrue(StringUtils.equals(null, null));
        assertFalse(StringUtils.equals("abc", null));
        assertTrue(StringUtils.equalsIgnoreCase("abc", "Abc"));
        assertThat(StringUtils.difference("hello world", "hello there"), is("there"));
        assertThat(StringUtils.difference("hallo world", "hello there"), is("ello there"));
        assertTrue(StringUtils.indexOfDifference("hallo world", "hello there") == 1);

        // indexing, containing
        assertThat(StringUtils.ordinalIndexOf("abcdefgabcabc", "abc", 3), is(10));
        assertThat(StringUtils.indexOfAny("abcdefgabcabc", 'b', 'f'), is(1));
        assertThat(StringUtils.indexOfAny("abcdefgabcabc", "bf"), is(1));
        assertThat(StringUtils.indexOfAnyBut("abcdefgabcabc", "abc"), is(3));
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
        assertThat(StringUtils.stripAccents("éclair"), is("eclair"));
        assertThat(StringUtils.stripAccents("über"), is("uber"));
        assertThat(StringUtils.rotate("abcdefg", 2), is("fgabcde"));
        assertThat(StringUtils.reverse("abcdefg"), is("gfedcba"));
        assertThat(StringUtils.reverseDelimited("www.domain.com", '.'), is("com.domain.www"));
        assertThat(StringUtils.abbreviate("This is a weird feature.", 10), is("This is..."));
        assertThat(StringUtils.abbreviate("This is a weird feature.", 12), is("This is a..."));
        assertThat(StringUtils.abbreviate("This is a weird feature.", 10, 12), is("...weird ..."));
    }
}
