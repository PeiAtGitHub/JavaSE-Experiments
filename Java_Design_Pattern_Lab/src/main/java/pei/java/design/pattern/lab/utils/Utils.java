package pei.java.design.pattern.lab.utils;

import java.util.function.Consumer;
import java.util.stream.IntStream;


/**
 * 
 * @author pei
 *
 */
public class Utils {
	
	   public static void repeatRun(int times, NonArgFunction function) {
	    	IntStream.range(0, times).forEach(i->function.doSth());
	    }
	    
	    public static void repeatRun(int times, Consumer<Integer> c) {
	    	IntStream.range(0, times).forEach(i->c.accept(i));
	    }

}
