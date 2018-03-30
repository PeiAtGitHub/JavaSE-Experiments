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
    
    //
    
    public static void begin() {
        begin = System.nanoTime();
        running = true;
    }
        
    public static long stopAndGetElapsedMillis() {

        long elapsed = System.nanoTime() - begin;
        
        if (running) {
            running = false;
            log.info("Elapsed nanos since last begin is {}.", elapsed);
            return TimeUnit.NANOSECONDS.toMillis(elapsed);
        }else{
            log.info("NanoStopWatch was NOT running! Returning ZERO!");
            return 0;
        }
        
    }
    
    public static long stopAndGetElapsedNanos() {
        
        long elapsed = System.nanoTime() - begin;
        
        if (running) {
            running = false;
            log.info("Elapsed nanos since last begin is {}.", elapsed);
            return elapsed;
        }else{
            log.info("NanoStopWatch was NOT running! Returning ZERO!");
            return 0;
        }
    }
    
}
