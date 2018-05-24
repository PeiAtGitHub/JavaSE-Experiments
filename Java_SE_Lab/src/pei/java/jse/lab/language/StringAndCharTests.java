package pei.java.jse.lab.language;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.assertj.core.api.Assertions.*;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import static com.github.peiatgithub.java.utils.Constants.*;

/**
 * 
 * @author pei
 *
 */
public class StringAndCharTests {

    private static final String STRING_UNDER_SEARCH = "abc-xxxxx-abbbc-ac";
	private static final String REGEX_RELUCTANT = "a.+?c";
	private static final String REGEX_GREEDY = "a.+c";
	private static final String ABC = "abc";

	@Test
	public void testLen() {
		
		assertThat(EMPTY.length()).isEqualTo(0);
		assertThat((new StringBuilder(EMPTY)).length()).isEqualTo(0);
		
	}
	
	@Test
    public void testIndexing() {
        /*
         * The convention of indexing is: begin index is inclusive, end index is
         * exclusive, for short: [ )
         */
        String str = "1,1;2,2;3,3;";

        assertThat(str.indexOf(SEMICOLON), is(3));
        assertThat(str.indexOf(SEMICOLON, 3), is(3)); // begin index inclusive
        assertThat(str.indexOf(SEMICOLON, 4), is(7));
        assertThat(str.indexOf(ABC), is(-1));

        assertThat(str.substring(0, 2), is("1,"));
        assertThat(str.substring(0), is(str));
        assertThat(str.substring(str.length() - 1), is(SEMICOLON));
        assertThat(str.substring(str.length())).isEmpty(); // this behavior, i think, makes no sense

        assertThatThrownBy(()->str.substring(str.length() + 1)).isInstanceOf(IOBE);
    }

    @Test
    public void testEquals() {
        String s1 = "abc";
        String s2 = "abc";
        assertEquals(s1,  s2);
        assertSame(s1, s2); // literal defined same-content strings are actually one object internally

        s1 = new String("abc");
        s2 = new String("abc");
        assertEquals(s1, s2);
        assertNotSame(s1, s2);
    }
    
    @Test
	public void testFormat() throws Exception {
    	assertThat(String.format(ABC, "def"), is(ABC));
	}

    @Test
    public void testNoSpliter() {
        assertThat(STR.split("$")).hasSize(1).contains(STR, atIndex(0));
    }

    @Test
    public void numberToChar() {
    	assertThat((char) (48), is('0'));
    	assertThat((char) (49), is('1'));
    	assertThat((char) (72), is('H'));
    	assertThat((char) (73), is('I'));
        // each char above takes only one byte (0~127)
        // each char below takes more than one byte
    	assertThat((char) (196), is('Ä'));
    	assertThat((char) (256), is('Ā'));
    	assertThat((char) (1234567), is('횇'));
    }

    @Test
    public void demoOfSpecialChars() {
        assertThat(String.format("\u007C"), is("|"));
        assertThat(String.format("\u00F1"), is("ñ"));
        assertThat(String.format("\\u007C"), is("\\u007C"));

        String first = "AAA\r\nBBB\r\nCCC\r\n";
        String second = "AAA\nBBB\nCCC\n";
        String third = "AAA\rBBB\rCCC\r";
        assertThat(first.length(), is(15));
        assertThat(second.length(), is(12));
        assertThat(third.length(), is(12));
         
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
        Matcher matcher = Pattern.compile(regex).matcher(STRING_UNDER_SEARCH);

        // 1st
        assertTrue(matcher.find());
        switch (regex) {
        case REGEX_GREEDY:
        	assertThat(matcher.group(), is(STRING_UNDER_SEARCH));
            assertThat(matcher.start(), is(0));
            assertThat(matcher.end(), is(18));
            break;
        case REGEX_RELUCTANT:
            assertThat(matcher.group(), is(ABC));
            assertThat(matcher.start(), is(0));
            assertThat(matcher.end(), is(3));
            break;
        default:
        	throw new RuntimeException(UNSUPPORTED_CASE);
        }
        // 2nd
        boolean found = matcher.find();
        switch (regex) {
        case REGEX_GREEDY:
        	assertThat(found).isFalse();
            break;
        case REGEX_RELUCTANT:
        	assertThat(found);
            assertThat(matcher.group(), is("abbbc"));
            assertThat(matcher.start(), is(10));
            assertThat(matcher.end(), is(15));
            break;
        default:
        	throw new RuntimeException(UNSUPPORTED_CASE);
        }
        // 3rd
        assertThat(matcher.find()).isFalse();
    }

    @Test
    public void testMatches() {
        String regex = new Random().nextBoolean()? REGEX_GREEDY : REGEX_RELUCTANT; 
        System.out.println("Regex: " + regex);
        assertThat(STRING_UNDER_SEARCH.matches(regex));
    }

    @Test
    public void testExtraction() { // search matches
        Matcher matcher = Pattern.compile("First Name:(.*?); Last Name:")
        		.matcher("First Name: Three; Last Name: Zhang");
        assertThat(matcher.find());
        assertThat(matcher.group(1).trim(), is("Three"));
    }

}
