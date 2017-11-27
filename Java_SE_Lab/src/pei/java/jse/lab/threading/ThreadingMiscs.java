package pei.java.jse.lab.threading;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

/**
 * 
 * @author pei
 *
 */
public class ThreadingMiscs {
	
	@Test
	public void threadStackTraces() {
		Set<Map.Entry<Thread, StackTraceElement[]>> entrySet =  Thread.getAllStackTraces().entrySet();

		for(Map.Entry<Thread, StackTraceElement[]> entry : entrySet){
			System.out.format("Thread: %s; StackTraces: %s%n", entry.getKey(), Arrays.toString(entry.getValue()));
		}
	}
  


}
