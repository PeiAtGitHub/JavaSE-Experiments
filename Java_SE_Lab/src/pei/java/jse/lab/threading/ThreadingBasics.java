package pei.java.jse.lab.threading;

import static pei.java.jse.lab.Utils.printWithThreadName;

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
	public void directExtending() {
		ASimpleThread aSimpleThr = new ASimpleThread();
		// u can call run() directly but it is NOT in a separate thread
		aSimpleThr.run();
		// calling start() runs in a separate thread.
		aSimpleThr.start();

		printWithThreadName("Main gonna wait.");
		try {
			aSimpleThr.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		printWithThreadName("Main gonna end.");
	}
	

	@Test
	public void implRunnable() throws InterruptedException {

        printWithThreadName("Starting a separate thread");
        long startTime = System.currentTimeMillis();
        Thread thr = new Thread(new RunnalbeImpl());
        thr.start();
        printWithThreadName("Waiting for the separate thread to finish");
        
        while (thr.isAlive()) {
            printWithThreadName("Waiting...");
            thr.join(2000); // join(): current thread wait for thr to complete
            if (((System.currentTimeMillis() - startTime) > (long) (28000))  // wait at most 28s
            		&& thr.isAlive()) {
                printWithThreadName("Tired of waiting!");
                thr.interrupt();
                thr.join();
            }
        }
        printWithThreadName("Finished!");
	}
	
/*
 * 
 */
	
class RunnalbeImpl implements Runnable {
        public void run() {
            String message[] = {"M1","M2","M3","M4","M5","M6"};
            try {
            	for (String msg : message) {
            		Thread.sleep(5000); //5s
            		printWithThreadName(msg);
            	}
            } catch (InterruptedException e) {
                printWithThreadName("I wasn't done! But Interrupted!");
            }
        }
    }
}


class ASimpleThread extends Thread {
	public void run() {
		printWithThreadName("ASimpleThread.run() starts!");
		try {
			for(int i = 1; i<=3; i++) {
				printWithThreadName(":)");
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		printWithThreadName("ASimpleThread.run() ends!");
	}
}
