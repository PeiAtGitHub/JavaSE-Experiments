package pei.java.thirdp.lab.guava;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static pei.java.thirdp.lab.utils.Utils.*;

import org.junit.Test;

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
		
		assertThat(Strings.commonPrefix("Please eat this cake!", "Please take this cake!"), is("Please "));
		assertThat(Strings.commonSuffix("Please eat this cake!", "Please take this cake!"), is(" this cake!"));
		
		assertThat(Strings.emptyToNull(STR), is(STR));
		assertThat(Strings.emptyToNull(""), nullValue());
		
		assertThat(Strings.nullToEmpty(STR), is(STR));
		assertThat(Strings.nullToEmpty(null), is(""));

		assertThat(Strings.isNullOrEmpty(null), is(true));
		assertThat(Strings.isNullOrEmpty(""), is(true));
		assertThat(Strings.isNullOrEmpty(STR), is(false));
		assertThat(Strings.isNullOrEmpty(" "), is(false));
		
		assertThat(Strings.padEnd("Please eat this cake!", 5, '!'), is("Please eat this cake!"));
		assertThat(Strings.padEnd("Please eat this cake!", 30, '!'), is("Please eat this cake!!!!!!!!!!"));
		
		assertThat(Strings.repeat(STR, 3), is("STRSTRSTR"));
		
	}

	/*
	 * "joiner instances are always immutable. 
	 *  The joiner configuration methods will always return a new Joiner, 
	 *  which you must use to get the desired semantics. 
	 *  This makes any Joiner thread safe, and usable as a static final constant."
	 *  (This is different from the Builder pattern, with config method is effective on itself, and
	 *   always returns the same builder instance)
	 *  For Example:
	 *  Joiner j = Joiner.on(',');
     *  j.skipNulls(); // has NO EFFECT on j, but returns another Joiner instance
	 */
	@Test
	public void joinerDemo() throws Exception {
		
		Joiner joiner1 = Joiner.on("; ").skipNulls();
		Joiner joiner2 = Joiner.on("; ").useForNull("");
		
		assertThat(joiner1.join(S1, null, S2), is("S1; S2"));
		assertThat(joiner2.join(S1, null, S2), is("S1; ; S2"));

		assertThat(joiner1.appendTo(new StringBuilder(STR), TEST_LIST_123).toString(),
				is("STR1; 2; 3"));
		assertThat(joiner1.appendTo(new StringBuilder(STR), S1, S2).toString(),
				is("STRS1; S2"));
		
		//MapJoiner
		Joiner.MapJoiner mapJoiner = joiner2.withKeyValueSeparator("$");
		assertThat(mapJoiner.join(TEST_MAP_123).toString(),
				is("S1$1; S2$2; S3$3"));
		assertThat(mapJoiner.appendTo(new StringBuilder(STR), TEST_MAP_123).toString(),
				is("STRS1$1; S2$2; S3$3"));
		
	}
	
	
	/*
	 * "Splitter instances are immutable. 
	 *  Invoking a configuration method has no effect on the receiving instance; 
	 *  you must store and use the new splitter instance it returns instead."
	 *  
	 *  For Example: 
	 *  each config method in the following chain returns a distinct instance of Splitter
	 *  Splitter.on(',').trimResults().omitEmptyStrings().split("foo,bar,,   qux");
	 *  Namely: 
	 *  Splitter spl = Splitter.on(...);
 	 *  spl.trimResults(...); // has NO EFFECT on spl, but returns another Splitter instance 
	 *  
	 */
	@Test
	public void splitterDemo() throws Exception {
		
		Splitter.on(',').trimResults().omitEmptyStrings().split("foo,bar,,   qux").forEach(s->System.out.println(s));
		
		System.out.println("============= Fixed length splitting:");
		Splitter.fixedLength(3).split(HELLO_WORLD).forEach(s->System.out.println(s));
		
		System.out.println(Splitter.fixedLength(3).splitToList(HELLO_WORLD));
		
		//MapSplitter
		assertThat(Splitter.fixedLength(3).withKeyValueSeparator(";").split("a;xb;yc;z").toString()
				, is("{a=x, b=y, c=z}"));
		
		assertThat(Splitter.on(',').withKeyValueSeparator(";").split("a;x,b;y,c;z").toString()
				, is("{a=x, b=y, c=z}"));
		
	}
	
	
	@Test
	public void charMathcerDemo() throws Exception {
		
		assertThat(CharMatcher.anyOf("aec").countIn("Please eat this cake!"), is(8));
		assertThat(CharMatcher.anyOf("aec").indexIn("Please eat this cake!"), is(2));
		assertThat(CharMatcher.anyOf("aec").removeFrom("Please eat this cake!"), is("Pls t this k!"));
		
		assertThat(CharMatcher.inRange('a', 'f').removeFrom("Please eat this cake!"), is("Pls t this k!"));
		assertThat(CharMatcher.inRange('0', '9').removeFrom("Please123 eat0 this555 cake!123")
				, is("Please eat this cake!"));
		assertThat(CharMatcher.inRange('0', '9').retainFrom("Please123 eat0 this555 cake!123")
				, is("1230555123"));
		assertThat(CharMatcher.inRange('a', 'f').negate()
				.removeFrom("Please eat this cake!"), is("eaeeacae"));
		assertThat(CharMatcher.inRange('a', 'f').matches('f'), is(true));
		assertThat(CharMatcher.inRange('a', 'f').matches('F'), is(false));
		
		assertThat(CharMatcher.whitespace().trimFrom("			Please eat this cake!\n\r  ")
				, is("Please eat this cake!"));
		assertThat(CharMatcher.breakingWhitespace().collapseFrom("Please   eat			this \n cake !", '-')
				, is("Please-eat-this-cake-!"));
		
	} 
	
	@Test
	public void caseFormatDemo() throws Exception {
		
		assertThat(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_HYPHEN, "pleaseEatThisCake!")
				, is("please-eat-this-cake!")); 
		
		assertThat(CaseFormat.UPPER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, "PleaseEatThisCake!")
				, is("PLEASE_EAT_THIS_CAKE!"));

		// when origin format does not match, it cannot convert properly
		assertThat(CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_HYPHEN, "Please eat this cake!")
				, is("please eat this cake!"));
		assertThat(CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_HYPHEN, "Please eat this cake!")
				, is("please eat this cake!"));
		
	}
	
}
