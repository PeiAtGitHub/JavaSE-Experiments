package pei.java.thirdp.lab.utils;

import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author pei
 *
 */
@Slf4j
public class NanoStopWatch {
    
    private static boolean running = false;
    
    private static long begin;
    
    public static void begin() {
        begin = System.nanoTime();
        running = true;
    }
        
    public static long stopAndGetElapsedMillis() {
    	return stopAndGetElapsed(TimeUnit.MILLISECONDS);
    }
    
    public static long stopAndGetElapsedNanos() {
    	return stopAndGetElapsed(TimeUnit.NANOSECONDS);
    }
    
    
    private static long stopAndGetElapsed(TimeUnit tu) {
        
        long elapsed = System.nanoTime() - begin;
        
        if (running) {
            running = false;
            log.info("Elapsed nanos since last begin is {}.", elapsed);
            switch (tu) {
			case MILLISECONDS:
				return TimeUnit.NANOSECONDS.toMillis(elapsed);
			case NANOSECONDS:
				return elapsed;
			default:
				throw new RuntimeException("Unsupported TimeUnit: " + tu.name());
			}
        }else{
            log.info("NanoStopWatch was NOT running! Returning 0!");
            return 0;
        }
    
    }
    
}
