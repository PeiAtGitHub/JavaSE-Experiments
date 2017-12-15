package pei.java.jse.lab;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * The Utils for the lab here 
 * 
 * @author pei
 *
 */
public class Utils {
	
	public final static Logger gLogger = LoggerFactory.getLogger("Global_Logger");
	
	
    public static void printWithThreadName(String message) {
        System.out.format("%s: %s%n", Thread.currentThread().getName(), message);
    }


}
