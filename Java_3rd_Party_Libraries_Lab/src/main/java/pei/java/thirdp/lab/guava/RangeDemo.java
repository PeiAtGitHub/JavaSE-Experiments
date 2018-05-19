package pei.java.thirdp.lab.guava;

import static com.github.peiatgithub.java.utils.Constants.*;

import java.util.Set;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.assertj.core.api.Assertions.*;

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
		
		assertThat(rangeJT.contains("Emily")).isFalse();
		assertThat(rangeJT.contains("Jerry"));
		assertThat(rangeJT.contains("Mike"));
		assertThat(rangeJT.contains("mike")).isFalse();
		assertThat(rangeJT.contains("Tom")).isFalse();
		assertThat(rangeJT.containsAll(TEST_MAP_123.keySet()));
		assertThat(rangeJT, is(Range.range("Jerry", BoundType.CLOSED, "Tom", BoundType.OPEN)));

		Range<String> rangeST = rangeJT.intersection(Range.closed("Stan", "Wendy"));
		
		assertThat(rangeST.lowerEndpoint(), is("Stan"));
		assertThat(rangeST.upperEndpoint(), is("Tom"));
		
		assertThat(rangeST.lowerBoundType(), is(BoundType.CLOSED));
		assertThat(rangeST.upperBoundType(), is(BoundType.OPEN));
		
		assertThat(rangeST.isConnected(rangeJT));
		assertThat(rangeST.span(rangeJT), is(rangeJT));

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
		
		assertThat(integers.distance(1, 10), is(9L));
		assertThat(integers.next(9), is(10));
		assertThat(integers.previous(9), is(8));
		
		/*
		 * "ContiguousSet.create does not actually construct the entire range, 
		 *  but instead returns a view of the range as a set."
		 */
		Set<Integer> intSet = ContiguousSet.create(Range.closed(1, 100), integers);
		assertThat(intSet).hasSize(100).isEqualTo(ContiguousSet.closed(1, 100));
		
		MyStringsDiscreteDomain1 msdd1 = new MyStringsDiscreteDomain1();
		assertThat(msdd1.distance("S1", "S100"), is(99L));
		assertThat(ContiguousSet.create(Range.closedOpen("S1", "S100"), msdd1)).hasSize(99);
		
		assertThatThrownBy(()->msdd1.distance("Jerry", "Tom")).isInstanceOf(NumberFormatException.class);
		assertThatThrownBy(()->ContiguousSet.create(Range.closedOpen("Jerry", "Tom"), msdd1))
		.isInstanceOf(NumberFormatException.class);
		
		MyStringsDiscreteDomain2 msdd2 = new MyStringsDiscreteDomain2();
		assertThat(msdd2.distance("Jerry", "Tom"), is(MyStringsDiscreteDomain2.FIXED_DISTANCE));
		
		Set<String> strSet = ContiguousSet.create(Range.closedOpen("Jerry", "Tom"), msdd2);
		assertThat(strSet.size()).isEqualTo(MyStringsDiscreteDomain2.FIXED_DISTANCE + 1); 
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