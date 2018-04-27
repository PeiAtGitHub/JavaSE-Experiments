package pei.java.thirdp.lab.guava;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static pei.java.thirdp.lab.utils.Utils.*;

import java.util.Arrays;

import org.junit.Test;

import com.google.common.primitives.Ints;

/**
 * 
 * @author pei
 *
 */
public class PrimitivesDemo {

	
	@Test
	public void ints() throws Exception {
		
		assertThat(Ints.concat(new int[] {1, 2}, new int[] {}, new int[] {3}), is(TEST_INT_ARRAY_123));

		assertThat(Ints.constrainToRange(10, 16, 20), is(16));
		assertThat(Ints.constrainToRange(10, 3, 6), is(6));
		assertThat(Ints.constrainToRange(10, 9, 11), is(10));
		
		assertThat(Ints.contains(TEST_INT_ARRAY_123, 1), is(true));
		assertThat(Ints.contains(TEST_INT_ARRAY_123, 0), is(false));
		assertThat(Ints.indexOf(TEST_INT_ARRAY_123, 1), is(0));
		assertThat(Ints.indexOf(TEST_INT_ARRAY_123, 4), is(-1));
		assertThat(Ints.indexOf(TEST_INT_ARRAY_123, new int[] {2, 3}), is(1));
		assertThat(Ints.indexOf(TEST_INT_ARRAY_123, new int[] {1, 3}), is(-1));
		assertThat(Ints.lastIndexOf(TEST_INT_ARRAY_123, 3), is(2));
		
		assertThat(Ints.ensureCapacity(TEST_INT_ARRAY_123, 5, 1), is(new int[] {1, 2, 3, 0, 0, 0}));
		assertThat(Ints.ensureCapacity(TEST_INT_ARRAY_123, 3, 1), is(TEST_INT_ARRAY_123));
		assertThat(Ints.ensureCapacity(TEST_INT_ARRAY_123, 2, 1), is(TEST_INT_ARRAY_123));
		
		assertThat(Ints.join(";", TEST_INT_ARRAY_123), is("1;2;3"));

		assertThat(Ints.max(TEST_INT_ARRAY_123), is(3));
		assertThat(Ints.min(TEST_INT_ARRAY_123), is(1));
		
		Ints.reverse(TEST_INT_ARRAY_123);
		assertThat(TEST_INT_ARRAY_123, is(new int[] {3, 2, 1}));
		Ints.reverse(TEST_INT_ARRAY_123);
		assertThat(TEST_INT_ARRAY_123, is(new int[] {1, 2, 3}));
		
		assertThat(Ints.toArray(TEST_LIST_123), is(TEST_INT_ARRAY_123));
		
		assertThat(Ints.tryParse("1"), is(1));
		assertThat(Ints.tryParse("-1"), is(-1));
		assertThat(Ints.tryParse("abc"), nullValue());
		
	}
	
}
