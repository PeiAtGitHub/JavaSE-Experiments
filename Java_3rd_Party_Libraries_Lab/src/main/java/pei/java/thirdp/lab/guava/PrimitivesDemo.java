package pei.java.thirdp.lab.guava;


import static org.assertj.core.api.Assertions.*;
import static com.github.peiatgithub.java.utils.Constants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

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
		
		assertThat(Ints.concat(new int[] {1, 2}, new int[] {}, new int[] {3})).containsExactly(1, 2, 3);

		assertEquals(16, Ints.constrainToRange(10, 16, 20));
		assertEquals(6, Ints.constrainToRange(10, 3, 6));
		assertEquals(10, Ints.constrainToRange(10, 9, 11));
		
		assertTrue(Ints.contains(TEST_INT_ARRAY_123, 1));
		assertFalse(Ints.contains(TEST_INT_ARRAY_123, 0));
		
		assertEquals(0, Ints.indexOf(TEST_INT_ARRAY_123, 1));
		assertEquals(-1, Ints.indexOf(TEST_INT_ARRAY_123, 4));
		assertEquals(1, Ints.indexOf(TEST_INT_ARRAY_123, new int[] {2, 3}));
		assertEquals(-1, Ints.indexOf(TEST_INT_ARRAY_123, new int[] {1, 3}));
		assertEquals(2, Ints.lastIndexOf(TEST_INT_ARRAY_123, 3));
		
		assertThat(Ints.ensureCapacity(TEST_INT_ARRAY_123, 5, 1)).containsExactly(1, 2, 3, 0, 0, 0);
		assertThat(Ints.ensureCapacity(TEST_INT_ARRAY_123, 3, 1)).containsExactly(1, 2, 3);
		assertThat(Ints.ensureCapacity(TEST_INT_ARRAY_123, 2, 1)).containsExactly(1, 2, 3);
		
		assertEquals("1;2;3", Ints.join(SEMICOLON, TEST_INT_ARRAY_123));

		assertEquals(3, Ints.max(TEST_INT_ARRAY_123));
		assertEquals(1, Ints.min(TEST_INT_ARRAY_123));
		
		Ints.reverse(TEST_INT_ARRAY_123);
		assertThat(TEST_INT_ARRAY_123).containsExactly(3, 2, 1);
		Ints.reverse(TEST_INT_ARRAY_123);
		assertThat(TEST_INT_ARRAY_123).containsExactly(1, 2, 3);
		
		assertThat(Ints.toArray(TEST_LIST_123)).containsExactly(1, 2, 3);
		
		assertEquals(Integer.valueOf(1), Ints.tryParse("1"));
		assertEquals(Integer.valueOf(-1), Ints.tryParse("-1"));
		assertNull(Ints.tryParse(STR));

		assertEquals(100, Ints.checkedCast(100L));
		assertThatThrownBy(()->Ints.checkedCast(2147483648L)).isInstanceOf(IAE);
		
		assertEquals(100, Ints.saturatedCast(100L));
		assertEquals(2147483647, Ints.saturatedCast(2147483648L));
		assertEquals(Integer.MAX_VALUE, Ints.saturatedCast(2147483648L));
		
	}
	
	@Test
	public void doubles() throws Exception {
		
		assertEquals(Double.valueOf(3.1415926535), Doubles.tryParse("3.1415926535"));
		assertNull(Doubles.tryParse("3.14.15926535"));

	}
	
	@Test
	public void booleans() throws Exception {
		
		assertEquals(5, Booleans.countTrue(true, true, true, false, true, true));
		
	}
}
