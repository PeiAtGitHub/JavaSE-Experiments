package pei.java.thirdp.lab.guava;

import static com.github.peiatgithub.java.utils.Constants.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.google.common.base.CaseFormat;
import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;

/**
 * 
 * @author pei
 *
 */
public class StringsDemo {

    @Test
    public void stringsDemo() throws Exception {

        assertEquals("Please ", Strings.commonPrefix("Please eat this cake!", "Please take this cake!"));
        assertEquals(" this cake!", Strings.commonSuffix("Please eat this cake!", "Please take this cake!"));

        assertEquals(STR, Strings.emptyToNull(STR));
        assertNull(Strings.emptyToNull(EMPTY));

        assertEquals(STR, Strings.nullToEmpty(STR));
        assertEquals(EMPTY, Strings.nullToEmpty(null));

        assertTrue(Strings.isNullOrEmpty(null));
        assertTrue(Strings.isNullOrEmpty(EMPTY));
        assertFalse(Strings.isNullOrEmpty(STR));
        assertFalse(Strings.isNullOrEmpty(SPACE));

        assertEquals("Please eat this cake!", Strings.padEnd("Please eat this cake!", 5, '!'));
        assertEquals("Please eat this cake!!!!!!!!!!", Strings.padEnd("Please eat this cake!", 30, '!'));

        assertEquals("STRSTRSTR", Strings.repeat(STR, 3));

    }

    /*
     * "joiner instances are always immutable. The joiner configuration methods will
     * always return a new Joiner, which you must use to get the desired semantics.
     * This makes any Joiner thread safe, and usable as a static final constant."
     * (This is different from the Builder pattern, with config method is effective
     * on itself, and always returns the same builder instance) For Example: Joiner
     * j = Joiner.on(','); j.skipNulls(); // has NO EFFECT on j, but returns another
     * Joiner instance
     */
    @Test
    public void joinerDemo() throws Exception {

        Joiner joiner1 = Joiner.on("; ").skipNulls();
        Joiner joiner2 = Joiner.on("; ").useForNull(EMPTY);

        assertEquals("S1; S2", joiner1.join(S1, null, S2));
        assertEquals("S1; ; S2", joiner2.join(S1, null, S2));

        assertEquals("STR1; 2; 3", joiner1.appendTo(new StringBuilder(STR), TEST_LIST_123).toString());
        assertEquals("STRS1; S2", joiner1.appendTo(new StringBuilder(STR), S1, S2).toString());

        // MapJoiner
        Joiner.MapJoiner mapJoiner = joiner2.withKeyValueSeparator("$");
        assertThat(mapJoiner.join(TEST_MAP_123)).contains("S1$1", "S2$2", "S3$3");
        assertThat(mapJoiner.appendTo(new StringBuilder(STR), TEST_MAP_123).toString()).contains("STR", "S1$1", "S2$2",
                "S3$3");

    }

    /*
     * "Splitter instances are immutable. Invoking a configuration method has no
     * effect on the receiving instance; you must store and use the new splitter
     * instance it returns instead."
     * 
     * For Example: each config method in the following chain returns a distinct
     * instance of Splitter
     * Splitter.on(',').trimResults().omitEmptyStrings().split("foo,bar,,   qux");
     * Namely: Splitter spl = Splitter.on(...); spl.trimResults(...); // has NO
     * EFFECT on spl, but returns another Splitter instance
     * 
     */
    @Test
    public void splitterDemo() throws Exception {

        Splitter.on(',').trimResults().omitEmptyStrings().split("foo,bar,,   qux").forEach(s -> System.out.println(s));

        System.out.println("============= Fixed length splitting:");
        Splitter.fixedLength(3).split(HELLO_WORLD).forEach(s -> System.out.println(s));

        System.out.println(Splitter.fixedLength(3).splitToList(HELLO_WORLD));

        // MapSplitter
        assertEquals("{a=x, b=y, c=z}",
                Splitter.fixedLength(3).withKeyValueSeparator(SEMICOLON).split("a;xb;yc;z").toString());

        assertEquals("{a=x, b=y, c=z}",
                Splitter.on(',').withKeyValueSeparator(SEMICOLON).split("a;x,b;y,c;z").toString());

    }

    @Test
    public void charMathcerDemo() throws Exception {

        assertEquals(8, CharMatcher.anyOf("aec").countIn("Please eat this cake!"));
        assertEquals(2, CharMatcher.anyOf("aec").indexIn("Please eat this cake!"));
        assertEquals("Pls t this k!", CharMatcher.anyOf("aec").removeFrom("Please eat this cake!"));

        assertEquals("Pls t this k!", CharMatcher.inRange('a', 'f').removeFrom("Please eat this cake!"));
        assertEquals("Please eat this cake!",
                CharMatcher.inRange('0', '9').removeFrom("Please123 eat0 this555 cake!123"));
        assertEquals("1230555123", CharMatcher.inRange('0', '9').retainFrom("Please123 eat0 this555 cake!123"));
        assertEquals("eaeeacae", CharMatcher.inRange('a', 'f').negate().removeFrom("Please eat this cake!"));
        assertThat(CharMatcher.inRange('a', 'f').matches('f'));
        assertThat(CharMatcher.inRange('a', 'f').matches('F')).isFalse();

        assertEquals("Please eat this cake!",
                CharMatcher.whitespace().trimFrom("			Please eat this cake!\n\r  "));
        assertEquals("Please-eat-this-cake-!",
                CharMatcher.breakingWhitespace().collapseFrom("Please   eat			this \n cake !", '-'));

    }

    @Test
    public void caseFormatDemo() throws Exception {

        assertEquals("please-eat-this-cake!", CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_HYPHEN, "pleaseEatThisCake!"));

        assertEquals("PLEASE_EAT_THIS_CAKE!",
                CaseFormat.UPPER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, "PleaseEatThisCake!"));

        // when origin format does not match, it cannot convert properly
        assertEquals("please eat this cake!",
                CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_HYPHEN, "Please eat this cake!"));
        assertEquals("please eat this cake!",
                CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_HYPHEN, "Please eat this cake!"));

    }

}
