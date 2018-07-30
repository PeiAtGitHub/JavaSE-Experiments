package pei.java.jse.lab.language;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

import static com.github.peiatgithub.java.utils.Constants.*;

/**
 * @author pei
 */
public class StringAndCharTests {

    private static final String STRING_UNDER_SEARCH = "abc-xxxxx-abbbc-ac";
    private static final String REGEX_RELUCTANT = "a.+?c";
    private static final String REGEX_GREEDY = "a.+c";
    private static final String ABC = "abc";

    @Test
    public void testStringBuilder() throws Exception {

        assertEquals(0, EMPTY.length());
        
        StringBuilder sb = new StringBuilder();
        assertEquals(0, sb.length());
        assertEquals(EMPTY, sb.toString());

        sb = new StringBuilder(EMPTY);
        assertEquals(0, sb.length());
        assertEquals(EMPTY, sb.toString());

    }

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
        assertEquals(-1, str.indexOf(ABC));

        assertEquals("1,", str.substring(0, 2));
        assertEquals(str, str.substring(0));
        assertEquals(SEMICOLON, str.substring(str.length() - 1));
        assertThat(str.substring(str.length())).isEmpty(); // this behavior, i think, makes no sense

        assertThatThrownBy(() -> str.substring(str.length() + 1)).isInstanceOf(IOBE);
    }

    @Test
    public void testEquals() {
        String s1 = "abc";
        String s2 = "abc";
        assertEquals(s1, s2);
        assertSame(s1, s2); // literal defined same-content strings are actually one object internally

        s1 = new String("abc");
        s2 = new String("abc");
        assertEquals(s1, s2);
        assertNotSame(s1, s2);
    }

    @Test
    public void testFormat() throws Exception {
        assertEquals(ABC, String.format(ABC, "def"));
        
        assertEquals("null", String.format("%s", null));
    }

    @Test
    public void testNoSpliter() {
        assertThat(STR.split("$")).hasSize(1).contains(STR, atIndex(0));
    }

    @Test
    public void numberToChar() {
        assertEquals('0', (char) (48));
        assertEquals('1', (char) (49));
        assertEquals('H', (char) (72));
        assertEquals('I', (char) (73));
    }

    @Test
    public void demoOfSpecialChars() {
        
        assertEquals("|", String.format("\u007C"));
        assertEquals("\\u007C", String.format("\\u007C"));

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
     * Regex tests. Regex behavior from language to language may vary, e.g. Java and
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
        Matcher matcher = Pattern.compile(regex).matcher(STRING_UNDER_SEARCH);

        // 1st
        assertTrue(matcher.find());
        switch (regex) {
        case REGEX_GREEDY:
            assertEquals(STRING_UNDER_SEARCH, matcher.group());
            assertEquals(0, matcher.start());
            assertEquals(18, matcher.end());
            break;
        case REGEX_RELUCTANT:
            assertEquals(ABC, matcher.group());
            assertEquals(0, matcher.start());
            assertEquals(3, matcher.end());
            break;
        default:
            throw new RuntimeException(UNSUPPORTED_CASE);
        }
        // 2nd
        boolean found = matcher.find();
        switch (regex) {
        case REGEX_GREEDY:
            assertFalse(found);
            break;
        case REGEX_RELUCTANT:
            assertTrue(found);
            assertEquals("abbbc", matcher.group());
            assertEquals(10, matcher.start());
            assertEquals(15, matcher.end());
            break;
        default:
            throw new RuntimeException(UNSUPPORTED_CASE);
        }
        // 3rd
        assertFalse(matcher.find());
    }

    @Test
    public void testMatches() {
        String regex = new Random().nextBoolean() ? REGEX_GREEDY : REGEX_RELUCTANT;
        System.out.println("Regex: " + regex);
        assertTrue(STRING_UNDER_SEARCH.matches(regex));
    }

    @Test
    public void testExtraction() { // search matches
        Matcher matcher = Pattern.compile("First Name:(.*?); Last Name:")
                .matcher("First Name: Three; Last Name: Zhang");
        assertTrue(matcher.find());
        assertEquals("Three", matcher.group(1).trim());
    }

}
