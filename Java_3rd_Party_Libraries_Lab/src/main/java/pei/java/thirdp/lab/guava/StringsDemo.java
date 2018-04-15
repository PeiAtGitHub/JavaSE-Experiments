package pei.java.thirdp.lab.guava;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static pei.java.thirdp.lab.utils.Utils.S1;
import static pei.java.thirdp.lab.utils.Utils.S2;
import static pei.java.thirdp.lab.utils.Utils.STR;
import static pei.java.thirdp.lab.utils.Utils.TEST_LIST_123;
import static pei.java.thirdp.lab.utils.Utils.TEST_MAP_123;

import org.junit.Test;

import com.google.common.base.Joiner;

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
	
	

}
