package pei.java.thirdp.lab.guava;

import static com.github.peiatgithub.java.utils.Constants.*;

import java.util.Set;



import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;

import com.google.common.collect.BoundType;
import com.google.common.collect.ContiguousSet;
import com.google.common.collect.DiscreteDomain;
import com.google.common.collect.Range;


/**
 * @author pei
 */
public class RangeDemo {
	
	@Test
	public void miscRangeDemos() throws Exception {
		/*
		 * ...Jerry.........Tom...............
		 * ...........Stan..........Wendy.....
		 */
		Range<String> rangeJT = Range.closedOpen("Jerry", "Tom");
		
		assertFalse(rangeJT.contains("Emily"));
		assertTrue(rangeJT.contains("Jerry"));
		assertTrue(rangeJT.contains("Mike"));
		assertFalse(rangeJT.contains("mike"));
		assertFalse(rangeJT.contains("Tom"));
		assertThat(rangeJT.containsAll(TEST_MAP_123.keySet()));
		assertEquals(Range.range("Jerry", BoundType.CLOSED, "Tom", BoundType.OPEN), rangeJT);

		Range<String> rangeST = rangeJT.intersection(Range.closed("Stan", "Wendy"));
		
		assertEquals("Stan", rangeST.lowerEndpoint());
		assertEquals("Tom", rangeST.upperEndpoint());
		
		assertEquals(BoundType.CLOSED, rangeST.lowerBoundType());
		assertEquals(BoundType.OPEN, rangeST.upperBoundType());
		
		assertThat(rangeST.isConnected(rangeJT));
		assertEquals(rangeJT, rangeST.span(rangeJT));

		assertThat(rangeST.encloses(Range.singleton("Str")));
		
		Range<Integer> range1To100 = Range.closed(1, 100); // [1, 100]
		
		assertThat(range1To100.containsAll(TEST_LIST_123));
		assertThat(range1To100.contains(RandomUtils.nextInt(1, 101)));
		
		assertThat(Range.openClosed(1, 1).isEmpty());
		assertThat(Range.closedOpen(1, 1).isEmpty());
		
		assertThatThrownBy(()->Range.open(1, 1)).isInstanceOf(IAE);
		assertThatThrownBy(()->Range.open(2, 1)).isInstanceOf(IAE);
		
	}
	
	@Test
	public void discreteDomainDemo() throws Exception {
		
		DiscreteDomain<Integer> integers = DiscreteDomain.integers();
		
		assertEquals(9, integers.distance(1, 10));
		assertEquals(Integer.valueOf(10), integers.next(9));
		assertEquals(Integer.valueOf(8), integers.previous(9));
		
		/*
		 * "ContiguousSet.create does not actually construct the entire range, 
		 *  but instead returns a view of the range as a set."
		 */
		Set<Integer> intSet = ContiguousSet.create(Range.closed(1, 100), integers);
		assertThat(intSet).hasSize(100).isEqualTo(ContiguousSet.closed(1, 100));
		
		MyStringsDiscreteDomain1 msdd1 = new MyStringsDiscreteDomain1();
		assertEquals(99, msdd1.distance("S1", "S100"));
		assertThat(ContiguousSet.create(Range.closedOpen("S1", "S100"), msdd1)).hasSize(99);
		
		assertThatThrownBy(()->msdd1.distance("Jerry", "Tom")).isInstanceOf(NumberFormatException.class);
		assertThatThrownBy(()->ContiguousSet.create(Range.closedOpen("Jerry", "Tom"), msdd1))
		.isInstanceOf(NumberFormatException.class);
		
		MyStringsDiscreteDomain2 msdd2 = new MyStringsDiscreteDomain2();
		assertEquals(MyStringsDiscreteDomain2.FIXED_DISTANCE, msdd2.distance("Jerry", "Tom"));
		
		Set<String> strSet = ContiguousSet.create(Range.closedOpen("Jerry", "Tom"), msdd2);
		assertThat(strSet.size()).isEqualTo(MyStringsDiscreteDomain2.FIXED_DISTANCE + 1); 
		/*
		 *  this Set actually contains only two strings: "Jerry" and "STR"
		 *  this is a demo of improper impl of DiscreteDomain
		 */
		assertEquals("[Jerry..STR]", strSet.toString());
		strSet.forEach(s->System.out.println(s));
		
	}

}

class MyStringsDiscreteDomain1 extends DiscreteDomain<String> {

	public static final String STR_PREFIX = "S";

	@Override
	public String next(String value) {
		return STR_PREFIX + (Integer.valueOf(StringUtils.removeStartIgnoreCase(value, STR_PREFIX)) + 1);
	}

	@Override
	public String previous(String value) {
		return STR_PREFIX + (Integer.valueOf(StringUtils.removeStartIgnoreCase(value, STR_PREFIX)) - 1);
	}

	@Override
	public long distance(String start, String end) {
		return Integer.valueOf(StringUtils.removeStartIgnoreCase(end, STR_PREFIX))
				-Integer.valueOf(StringUtils.removeStartIgnoreCase(start, STR_PREFIX));
	}

}

class MyStringsDiscreteDomain2 extends DiscreteDomain<String> {
	
	public static final String FIXED_STR = STR;
	public static final long FIXED_DISTANCE = 88L;
	
	@Override
	public String next(String value) {
		return FIXED_STR;
	}
	
	@Override
	public String previous(String value) {
		return FIXED_STR;
	}
	
	@Override
	public long distance(String start, String end) {
		return FIXED_DISTANCE;
	}
	
}