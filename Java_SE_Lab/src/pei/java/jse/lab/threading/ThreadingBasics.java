package pei.java.jse.lab.threading;

import static pei.java.jse.lab.Utils.prefixThreadName;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

/**
 * 
 * @author pei
 *
 */
public class ThreadingBasics {

	@Test
	public void basics() throws InterruptedException {

        prefixThreadName("Starting MessageLooper thread");
        long startTime = System.currentTimeMillis();
        Thread thr = new Thread(new MessageLooper());
        thr.start();
        prefixThreadName("Waiting for MessageLooper thread to finish");
        
        while (thr.isAlive()) {
            prefixThreadName("Waiting...");
            thr.join(2000); // join(): current thread wait for thr to complete
            if (((System.currentTimeMillis() - startTime) > (long) (28000))  // wait at most 28s
            		&& thr.isAlive()) {
                prefixThreadName("Tired of waiting!");
                thr.interrupt();
                thr.join();
            }
        }
        prefixThreadName("Finished!");
	}
	
	/*
 * 
 */
	
class MessageLooper implements Runnable {
        public void run() {
            String message[] = {"M1","M2","M3","M4","M5","M6"};
            try {
            	for (String msg : message) {
            		Thread.sleep(5000); //5s
            		prefixThreadName(msg);
            	}
            } catch (InterruptedException e) {
                prefixThreadName("I wasn't done! But Interrupted!");
            }
        }
    }

}
