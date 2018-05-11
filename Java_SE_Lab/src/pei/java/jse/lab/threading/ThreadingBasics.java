package pei.java.jse.lab.threading;

import static org.junit.Assert.assertTrue;
import static com.github.peiatgithub.java.utils.Utils.*;
import com.github.peiatgithub.java.utils.NanoStopWatch;

import org.junit.Test;


/**
 * 
 * @author pei
 *
 */
public class ThreadingBasics {

    @Test
    public void directExtending() {
        final ASimpleThread aSimpleThr = new ASimpleThread();
        // u can call run() directly but it is NOT in a separate thread
        aSimpleThr.run();
        // calling start() runs in a separate thread.
        aSimpleThr.start();

        printlnWithThreadName("Main gonna wait.");
        threadJoin(aSimpleThr);
        printlnWithThreadName("Main gonna end.");
    }

    @Test
    public void implRunnable() throws InterruptedException {

    	printlnWithThreadName("Starting a separate thread");
        
    	NanoStopWatch.begin();
        Thread thr = createAndStartThread(new MessageLooper());
        printlnWithThreadName("Waiting for the separate thread to finish");

        while (thr.isAlive()) {
        	printlnWithThreadName("Waiting...");
            thr.join(2000); // join(): current thread wait for thr to complete
            if (((NanoStopWatch.getMillis()) > 28000L) && thr.isAlive()) {// wait at most 28s
            	printlnWithThreadName("Tired of waiting!");
                thr.interrupt();
                thr.join();
            }
        }
        printlnWithThreadName("Finished!");
    }

    @Test
    public void waitAndNotify() throws InterruptedException {
        WaitForNotify wfn = new WaitForNotify();
        Thread thr = new Thread(wfn);
        synchronized (wfn) {
            printlnWithThreadName("1.Got lock of " + wfn);
            thr.start();
            Thread.sleep(1000);// sleep() Does not release lock
            printlnWithThreadName("3.Gonna release lock of " + wfn);
            wfn.wait(); // release wfn lock and wait to be notified
            printlnWithThreadName("6.Got lock of " + wfn);
        }
        thr.join();
    }
    
    @Test
    public void interruption() throws InterruptedException {
        Thread thr = createAndStartThread(()->threadSleep(10000));
        assertTrue(thr.isAlive());
        thr.interrupt();
        assertTrue(thr.isInterrupted());
        thr.join();
    }

    //
    class MessageLooper implements Runnable {
        public void run() {
            try {
                for (String msg : new String[] { "M1", "M2", "M3", "M4", "M5", "M6" }) {
                    Thread.sleep(5000); // 5s
                    printlnWithThreadName(msg);
                }
            } catch (InterruptedException e) {
                printlnWithThreadName("I wasn't done! But Interrupted!");
            }
        }
    }
}

//
class ASimpleThread extends Thread {
	public void run() {
		printlnWithThreadName("ASimpleThread.run() starts!");
		repeatRun(3, ()-> {
			printlnWithThreadName(":)");
			threadSleep(1000);
		});
		printlnWithThreadName("ASimpleThread.run() ends!");
	}
}

//
class WaitForNotify implements Runnable {
    public void run() {
        printlnWithThreadName("2. Try to get lock of " + this);
        synchronized (this) {
            printlnWithThreadName("4. Got lock of " + this);
            notify(); // notified but not releasing lock so the waiting thread cannot resume
            threadSleep(1000);
            printlnWithThreadName("5. Gonna release lock of " + this);
        }
    }

    @Override
    public String toString() {
        return "WaitForNotify Object";
    }
}
