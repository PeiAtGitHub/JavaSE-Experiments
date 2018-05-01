package pei.java.thirdp.lab.guava;

import static pei.java.thirdp.lab.utils.Utils.*;

import java.util.Set;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import com.google.common.collect.BoundType;
import com.google.common.collect.ContiguousSet;
import com.google.common.collect.DiscreteDomain;
import com.google.common.collect.Range;


/**
 * 
 * @author pei
 *
 */
public class RangeDemo {
	
	@Test
	public void miscRangeDemos() throws Exception {
		/*
		 * ...Jerry.........Tom...............
		 * ...........Stan..........Wendy.....
		 */
		Range<String> rangeJT = Range.closedOpen("Jerry", "Tom");
		
		assertThat(rangeJT.contains("Emily"), is(false));
		assertThat(rangeJT.contains("Jerry"), is(true));
		assertThat(rangeJT.contains("Mike"), is(true));
		assertThat(rangeJT.contains("mike"), is(false));
		assertThat(rangeJT.contains("Tom"), is(false));
		assertThat(rangeJT.containsAll(TEST_MAP_123.keySet()), is(true));
		assertThat(rangeJT, is(Range.range("Jerry", BoundType.CLOSED, "Tom", BoundType.OPEN)));

		Range<String> rangeST = rangeJT.intersection(Range.closed("Stan", "Wendy"));
		
		assertThat(rangeST.lowerEndpoint(), is("Stan"));
		assertThat(rangeST.upperEndpoint(), is("Tom"));
		
		assertThat(rangeST.lowerBoundType(), is(BoundType.CLOSED));
		assertThat(rangeST.upperBoundType(), is(BoundType.OPEN));
		
		assertThat(rangeST.isConnected(rangeJT), is(true));
		assertThat(rangeST.span(rangeJT), is(rangeJT));

		assertThat(rangeST.encloses(Range.singleton("Str")), is(true));
		
		//
		Range<Integer> range1To100 = Range.closed(1, 100); // [1, 100]
		
		assertThat(range1To100.containsAll(TEST_LIST_123), is(true));
		assertThat(range1To100.contains(RandomUtils.nextInt(1, 101)), is(true));
		
		//
		assertThat(Range.openClosed(1, 1).isEmpty(), is(true));
		assertThat(Range.closedOpen(1, 1).isEmpty(), is(true));
		
		assertThat(catchException(()->Range.open(1, 1)), instanceOf(IllegalArgumentException.class));
		assertThat(catchException(()->Range.open(2, 1)), instanceOf(IllegalArgumentException.class));
		
	}
	
	@Test
	public void discreteDomainDemo() throws Exception {
		
		DiscreteDomain<Integer> integers = DiscreteDomain.integers();
		
		assertThat(integers.distance(1, 10), is(9L));
		assertThat(integers.next(9), is(10));
		assertThat(integers.previous(9), is(8));
		
		/*
		 * "ContiguousSet.create does not actually construct the entire range, 
		 *  but instead returns a view of the range as a set."
		 */
		Set<Integer> intSet = ContiguousSet.create(Range.closed(1, 100), integers);
		assertThat(intSet, is(ContiguousSet.closed(1, 100)));
		assertThat(intSet.size(), is(100));
		
		//
		MyStringsDiscreteDomain1 msdd1 = new MyStringsDiscreteDomain1();
		assertThat(msdd1.distance("S1", "S100"), is(99L));
		assertThat(ContiguousSet.create(Range.closedOpen("S1", "S100"), msdd1).size(), is(99));
		
		assertThat(catchException(()->msdd1.distance("Jerry", "Tom")), instanceOf(NumberFormatException.class));
		assertThat(catchException(()->ContiguousSet.create(Range.closedOpen("Jerry", "Tom"), msdd1))
				, instanceOf(NumberFormatException.class));
		
		MyStringsDiscreteDomain2 msdd2 = new MyStringsDiscreteDomain2();
		assertThat(msdd2.distance("Jerry", "Tom"), is(MyStringsDiscreteDomain2.FIXED_DISTANCE));
		
		Set<String> strSet = ContiguousSet.create(Range.closedOpen("Jerry", "Tom"), msdd2);
		assertEquals(strSet.size(), MyStringsDiscreteDomain2.FIXED_DISTANCE + 1); 
		/*
		 *  this Set actually contains only two strings: "Jerry" and "STR"
		 *  this is a demo of improper impl of DiscreteDomain
		 */
		assertThat(strSet.toString(), is("[Jerry..STR]"));
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