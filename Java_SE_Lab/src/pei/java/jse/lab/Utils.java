package pei.java.jse.lab;

/**
 * 
 * The Utils for the lab here 
 * 
 * @author pei
 *
 */
public class Utils {
	
	
	
    public static void prefixThreadName(String message) {
        System.out.format("%s: %s%n", Thread.currentThread().getName(), message);
    }


}
