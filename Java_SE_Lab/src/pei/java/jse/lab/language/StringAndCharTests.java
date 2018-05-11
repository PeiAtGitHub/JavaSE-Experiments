package pei.java.jse.lab.language;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.*;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import static com.github.peiatgithub.java.utils.Utils.*;
import static com.github.peiatgithub.java.utils.Constants.*;

/**
 * 
 * @author pei
 *
 */
public class StringAndCharTests {

    private static final String REGEX_RELUCTANT = "a.+?c";
	private static final String REGEX_GREEDY = "a.+c";

	@Test
    public void testIndexing() {
        /*
         * The convention of indexing is: begin index is inclusive, end index is
         * exclusive, for short: [ )
         */
        String str = "1,1;2,2;3,3;";

        assertEquals(3, str.indexOf(SEMICOLON));
        assertEquals(3, str.indexOf(SEMICOLON, 3)); // begin index inclusive
        assertEquals(7, str.indexOf(SEMICOLON, 4));
        assertEquals(-1, str.indexOf("abc"));

        assertThat(str.substring(0, 2), is("1,"));
        assertThat(str.substring(0), is(str));
        assertThat(str.substring(str.length() - 1), is(SEMICOLON));
        assertTrue(str.substring(str.length()).isEmpty()); // this behavior, i think, makes no sense

        assertThat(catchThrowable(()->str.substring(str.length() + 1)), instanceOf(IndexOutOfBoundsException.class));
    }

    @Test
    public void testEquals() {
        String s1 = "abc";
        String s2 = "abc";

        assertEquals(s1,  s2);
        assertSame(s1, s2); // literal defined same-content strings are actually one object internally

        //
        s1 = new String("abc");
        s2 = new String("abc");

        assertEquals(s1, s2);
        assertNotSame(s1, s2);
    }

    @Test
    public void testNoSpliter() {
        String[] splitted = STR.split("$"); 
        assertEquals(1, splitted.length);
        assertThat(splitted[0], is(STR));
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
        String regex = new Random().nextBoolean() ? REGEX_GREEDY : REGEX_RELUCTANT;
        System.out.println("Regex: " + regex);

        Pattern regexPattern = Pattern.compile(regex);
        Matcher matcher = regexPattern.matcher("abc-xxxxx-abbbc-ac");// the string under search
        // 1st
        assertTrue(matcher.find());
        switch (regex) {
        case REGEX_GREEDY:
            assertTrue(matcher.group().equals("abc-xxxxx-abbbc-ac"));
            assertThat(matcher.start(), is(0));
            assertThat(matcher.end(), is(18));
            break;
        case REGEX_RELUCTANT:
            assertThat(matcher.group(), is("abc"));
            assertThat(matcher.start(), is(0));
            assertThat(matcher.end(), is(3));
            break;
        default:
            break;
        }
        // 2nd
        boolean found = matcher.find();
        switch (regex) {
        case REGEX_GREEDY:
            assertFalse(found);
            break;
        case REGEX_RELUCTANT:
            assertThat(matcher.group(), is("abbbc"));
            assertEquals(10, matcher.start());
            assertEquals(15, matcher.end());
            break;
        default:
            break;
        }
        // 3rd
        assertFalse(matcher.find());
    }

    @Test
    public void testMatches() {
        String regex = new Random().nextBoolean()? REGEX_GREEDY : REGEX_RELUCTANT; 
        System.out.println("Regex: " + regex);
        assertTrue("abc-xxxxx-abbbc-ac".matches(regex));
    }

    @Test
    public void testExtraction() { // search matches
        Matcher matcher = Pattern.compile("First Name:(.*?); Last Name:")
        		.matcher("First Name: Three; Last Name: Zhang");
        assertTrue(matcher.find());
        assertThat(matcher.group(1).trim(), is("Three"));
    }

}
