package pei.java.jse.lab.threading;

import static org.junit.Assert.assertTrue;
import static pei.java.jse.lab.utils.Utils.*;

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

        printWithThreadName("Main gonna wait.");
        threadJoin(aSimpleThr);
        printWithThreadName("Main gonna end.");
    }

    @Test
    public void implRunnable() throws InterruptedException {

        printWithThreadName("Starting a separate thread");
        long startTime = System.currentTimeMillis();
        Thread thr = createAndStartThread(new MessageLooper());
        printWithThreadName("Waiting for the separate thread to finish");

        while (thr.isAlive()) {
            printWithThreadName("Waiting...");
            thr.join(2000); // join(): current thread wait for thr to complete
            if (((System.currentTimeMillis() - startTime) > (long) (28000)) // wait at most 28s
                    && thr.isAlive()) {
                printWithThreadName("Tired of waiting!");
                thr.interrupt();
                thr.join();
            }
        }
        printWithThreadName("Finished!");
    }

    @Test
    public void waitAndNotify() throws InterruptedException {
        WaitForNotify wfn = new WaitForNotify();
        Thread thr = new Thread(wfn);
        synchronized (wfn) {
            printWithThreadName("1.Got lock of " + wfn);
            thr.start();
            Thread.sleep(1000);// sleep() Does not release lock
            printWithThreadName("3.Gonna release lock of " + wfn);
            wfn.wait(); // release wfn lock and wait to be notified
            printWithThreadName("6.Got lock of " + wfn);
        }
        thr.join();
    }
    
    @Test
    public void interruption() throws InterruptedException {
        Thread thr = createAndStartThread(()->{
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                printWithThreadName("Interrupted!");
            }
        });
        
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
                    printWithThreadName(msg);
                }
            } catch (InterruptedException e) {
                printWithThreadName("I wasn't done! But Interrupted!");
            }
        }
    }
}

//
class ASimpleThread extends Thread {
	public void run() {
		printWithThreadName("ASimpleThread.run() starts!");
		repeatRun(3, ()-> {
			printWithThreadName(":)");
			threadSleep(1000);
		});
		printWithThreadName("ASimpleThread.run() ends!");
	}
}

//
class WaitForNotify implements Runnable {
    public void run() {
        printWithThreadName("2. Try to get lock of " + this);
        synchronized (this) {
            printWithThreadName("4. Got lock of " + this);
            notify(); // notified but not releasing lock so the waiting thread cannot resume
            threadSleep(1000);
            printWithThreadName("5. Gonna release lock of " + this);
        }
    }

    @Override
    public String toString() {
        return "WaitForNotify Object";
    }
}
