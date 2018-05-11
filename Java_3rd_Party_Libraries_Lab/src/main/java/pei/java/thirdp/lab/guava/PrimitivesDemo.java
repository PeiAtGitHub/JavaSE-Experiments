package pei.java.thirdp.lab.guava;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static com.github.peiatgithub.java.utils.Utils.*;
import static com.github.peiatgithub.java.utils.Constants.*;

import org.junit.Test;

import com.google.common.primitives.Booleans;
import com.google.common.primitives.Doubles;
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
		
		assertThat(Ints.join(SEMICOLON, TEST_INT_ARRAY_123), is("1;2;3"));

		assertThat(Ints.max(TEST_INT_ARRAY_123), is(3));
		assertThat(Ints.min(TEST_INT_ARRAY_123), is(1));
		
		Ints.reverse(TEST_INT_ARRAY_123);
		assertThat(TEST_INT_ARRAY_123, is(new int[] {3, 2, 1}));
		Ints.reverse(TEST_INT_ARRAY_123);
		assertThat(TEST_INT_ARRAY_123, is(new int[] {1, 2, 3}));
		
		assertThat(Ints.toArray(TEST_LIST_123), is(TEST_INT_ARRAY_123));
		
		assertThat(Ints.tryParse("1"), is(1));
		assertThat(Ints.tryParse("-1"), is(-1));
		assertThat(Ints.tryParse(STR), nullValue());

		assertThat(Ints.checkedCast(100L), is(100));
		assertThat(catchThrowable(()->Ints.checkedCast(2147483648L)), instanceOf(IllegalArgumentException.class));
		
		assertThat(Ints.saturatedCast(100L), is(100));
		assertThat(Ints.saturatedCast(2147483648L), is(2147483647));
		assertThat(Ints.saturatedCast(2147483648L), is(Integer.MAX_VALUE));
		
	}
	
	@Test
	public void doubles() throws Exception {
		
		assertThat(Doubles.tryParse("3.1415926535"), is(3.1415926535));
		assertThat(Doubles.tryParse("3.14.15926535"), nullValue());

	}
	
	@Test
	public void booleans() throws Exception {
		
		assertThat(Booleans.countTrue(true, true, true, false, true, true), is(5));
		
	}
}
