package pei.java.thirdp.lab.guava;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static pei.java.thirdp.lab.utils.Utils.*;

import org.junit.Test;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;

/**
 * 
 * @author pei
 *
 */
public class StringsDemo {

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
		
		Iterable<String> iterable = 
				Splitter.on(',').trimResults().omitEmptyStrings().split("foo,bar,,   qux");
		
		for(String s : iterable) {
			System.out.println(s);
		}
		
		System.out.println("============= Fixed length splitting:");
		
		for(String s : Splitter.fixedLength(3).split(HELLO_WORLD)) {
			System.out.println(s);
		}
		
		System.out.println(Splitter.fixedLength(3).splitToList(HELLO_WORLD));
		
		//MapSplitter
		assertThat(Splitter.fixedLength(3).withKeyValueSeparator(";").split("a;xb;yc;z").toString()
				, is("{a=x, b=y, c=z}"));
		
		assertThat(Splitter.on(',').withKeyValueSeparator(";").split("a;x,b;y,c;z").toString()
				, is("{a=x, b=y, c=z}"));
		
	}
	
}
