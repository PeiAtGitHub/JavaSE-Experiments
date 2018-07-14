package pei.java.jse.lab.language;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static com.github.peiatgithub.java.utils.Utils.*;
import java.util.Random;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

/**
 * @author pei
 */
@Slf4j
public class Numbers {
	
	 @Test
		public void division() throws Exception {
	    	assertEquals(0, 3/5);
	    	assertEquals(0.6, 3.0/5);

	    	assertEquals(1, 5/3);
	    	assertEquals(1.6666666666666667d, 5/3.0);
		}
	    
	    @Test
	    public void modOperator(){
	        assertEquals(0, 0 % 255);
	        assertEquals(180, 180 % 255);
	        assertEquals(0, 255 % 255);
	        assertEquals(5, 155555 % 255);
	    }


    @Test
    public void someMathUtils() {
        assertEquals(0, Math.round(0.4));
        assertEquals(1, Math.round(0.6));
        
        assertThat(Math.random()).isGreaterThanOrEqualTo(0d).isLessThan(1d);
        
        assertEquals(2, Math.max(1, 2));
        assertEquals(1, Math.min(1, 2));
        
        assertEquals(256, Math.pow(2, 8));
        assertEquals(6, Math.sqrt(36));
        assertEquals(180, Math.toDegrees(Math.PI));
    }
    
    @Test
	public void someNumberUtils() throws Exception {
        assertEquals(0, Integer.compare(1, 1));
        assertEquals(2, Integer.max(1, 2));
        assertEquals(1, Integer.parseInt("1"));
        assertEquals(new Integer(1), Integer.valueOf("1"));

        assertEquals(0, Double.compare(1, 1));
        assertEquals(22d/7d, Double.max(22d/7d, Math.PI));
	}
    
    @Test
    public void numberWrappers() {
        assertEquals("3", new Integer(3).toString());
        assertEquals("3.3", new Double(3.3).toString());
    }


    @Test
    public void numberOverFlow() {
        log.info("Integer.MAX_VALUE is {}, appx {}", Integer.MAX_VALUE, numberToReadable(Integer.MAX_VALUE));
        log.info("Long.MAX_VALUE is {}, appx {}", Long.MAX_VALUE, numberToReadable(Long.MAX_VALUE));
        log.info("Float.MAX_VALUE is {}, {} times bigger than Long.MAX_VALUE.", Float.MAX_VALUE
        		, (Float.MAX_VALUE/Long.MAX_VALUE));
        log.info("Double.MAX_VALUE is {}.", Double.MAX_VALUE);

        log.info("Demo of number overflow:");
        log.info("Integer.MAX_VALUE + 123 is: {}", (Integer.MAX_VALUE + 123));
        log.info("Integer.MAX_VALUE x 123 is: {}", (Integer.MAX_VALUE * 123));
        assertThat((Integer.MAX_VALUE + 123)).isLessThan(Integer.MAX_VALUE);
        assertThat((Integer.MAX_VALUE * 123)).isLessThan(Integer.MAX_VALUE);
        
        log.info("Interesting to Know, Integer.MIN_VALUE == -Integer.MAX_VALUE-1");
        assertEquals(-Integer.MAX_VALUE-1, Integer.MIN_VALUE);
        assertThat((Integer.MIN_VALUE - 1)).isGreaterThan(Integer.MIN_VALUE);// overflow
    }
    
    @Test
    public void randoms() {

    	Random r = new Random();
        repeatRun(5, ()->System.out.println(r.nextInt(100))); // random integer [0,100)
        repeatRun(5, ()->System.out.println(r.nextGaussian()));
        repeatRun(5, ()->System.out.println(r.nextBoolean())); 
        
    }
    
    @Test
    public void numberPreciseDemos() {
        System.out.println("1 + 0.000000000000001 is: "+(1 + 0.000000000000001));// 1.000000000000001
        System.out.println("1 + 0.0000000000000001 is: "+(1 + 0.0000000000000001));// 1.0
        System.out.println("0.00000025 * 0.00000025 is: "+(0.00000025 * 0.00000025)); // 6.25E-14
        System.out.println("0.000000025 * 0.000000025 is: "+(0.000000025 * 0.000000025)); // 6.249999999999999E-16
        System.out.println("100.00000025 * 100.00000025 is: "+(100.00000025 * 100.00000025)); // 10000.00005
        System.out.println("100.000000025 * 100.000000025 is: "+(100.000000025 * 100.000000025)); // 10000.000005000002
    }

}
