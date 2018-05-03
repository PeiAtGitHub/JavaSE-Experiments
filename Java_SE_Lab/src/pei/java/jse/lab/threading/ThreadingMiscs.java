package pei.java.jse.lab.threading;

import java.util.Arrays;

import org.junit.Test;

/**
 * 
 * @author pei
 *
 */
public class ThreadingMiscs {
    
    @Test
    public void threadStackTraces() {
        
        Thread.getAllStackTraces().entrySet()
        	.forEach(entry->System.out.format("Thread: %s; StackTraces: %s%n"
        			, entry.getKey(), Arrays.toString(entry.getValue())));

    }

}
