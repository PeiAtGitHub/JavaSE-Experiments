package pei.java.jse.lab.language;

import static org.hamcrest.Matchers.*;
import static pei.java.jse.lab.utils.Utils.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Random;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author pei
 *
 */
@Slf4j
public class Numbers {

    @Test
    public void someMathUtils() {
        
    	assertThat(Math.round(0.4), is(0l));
    	assertThat(Math.round(0.6), is(1l));
        
        assertThat(Math.random(), allOf(greaterThanOrEqualTo(0d), lessThan(1d)));
        
        assertThat(Math.max(1, 2), is(2));
        assertThat(Math.min(1, 2), is(1));
        
        assertThat(Math.pow(2, 8), is(256d));
        assertThat(Math.sqrt(36), is(6d));
        assertThat(Math.toDegrees(Math.PI), is(180d));

    }
    
    @Test
	public void someNumberUtils() throws Exception {
		
    	assertThat(Integer.compare(1, 1), is(0));
    	assertThat(Integer.max(1, 2), is(2));
    	assertThat(Integer.parseInt("1"), is(1));
    	assertThat(Integer.valueOf("1"), is(1));

    	assertThat(Double.compare(1, 1), is(0));
    	assertThat(Double.max(22d/7d, Math.PI), is(22d/7d));
    	
	}

    @Test
    public void numberOverFlow() {
        log.info("Integer.MAX_VALUE is {}, appx {}", Integer.MAX_VALUE, numberToReadableString(Integer.MAX_VALUE));
        log.info("Long.MAX_VALUE is {}, appx {}", Long.MAX_VALUE, numberToReadableString(Long.MAX_VALUE));
        log.info("Float.MAX_VALUE is {}, appx {}", Float.MAX_VALUE, numberToReadableString(Float.MAX_VALUE));
        log.info("Double.MAX_VALUE is {}, which has no better representation.", Double.MAX_VALUE);

        log.info("Demo of number overflow:");
        log.info("Integer.MAX_VALUE + 123 is: {}", (Integer.MAX_VALUE + 123));
        log.info("Integer.MAX_VALUE x 123 is: {}", (Integer.MAX_VALUE * 123));
        assertThat((Integer.MAX_VALUE + 123), lessThan(Integer.MAX_VALUE));
        assertThat((Integer.MAX_VALUE * 123), lessThan(Integer.MAX_VALUE));
        
        log.info("Interesting to Know, Integer.MIN_VALUE == -Integer.MAX_VALUE-1");
        assertThat(Integer.MIN_VALUE, is(-Integer.MAX_VALUE-1));
        assertThat((Integer.MIN_VALUE - 1), greaterThan(Integer.MIN_VALUE));// overflow
    }
    
    @Test
    public void randoms() {

    	Random r = new Random();
        repeatRun(5, ()->System.out.println(r.nextInt(100))); // random integer [0,100)
        repeatRun(5, ()->System.out.println(r.nextGaussian()));
        repeatRun(5, ()->System.out.println(r.nextBoolean())); 
        
    }
    
    @Test
    public void modOperator(){
        assertEquals(0, 0 % 255);
        assertEquals(180, 180 % 255);
        assertEquals(0, 255 % 255);
        assertEquals(5, 155555 % 255);
    }
    

    @Test
    public void numberPrecise() {
        // Some Demos
        System.out.println("1 + 0.000000000000001 is: "+(1 + 0.000000000000001));// 1.000000000000001
        System.out.println("1 + 0.0000000000000001 is: "+(1 + 0.0000000000000001));// 1.0
        System.out.println("0.00000025 * 0.00000025 is: "+(0.00000025 * 0.00000025)); // 6.25E-14
        System.out.println("0.000000025 * 0.000000025 is: "+(0.000000025 * 0.000000025)); // 6.249999999999999E-16
        System.out.println("100.00000025 * 100.00000025 is: "+(100.00000025 * 100.00000025)); // 10000.00005
        System.out.println("100.000000025 * 100.000000025 is: "+(100.000000025 * 100.000000025)); // 10000.000005000002
    }

}
