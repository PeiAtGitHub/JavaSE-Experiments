package pei.java.jse.lab.threading;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static pei.java.jse.lab.Utils.printWithThreadName;

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
		Thread thr = new Thread(new MessageLooper());
		thr.start();
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
		Thread thr = new Thread(()->{
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				printWithThreadName("Interrupted!");
			}
		});
		thr.start();
		
		if(thr.isAlive()) {
			thr.interrupt();
			assertTrue(thr.isInterrupted());
		}else {
			fail("The thread is not alive.");
		}
		thr.join();
	}

	/*
	 * 
	 */
	//
	class MessageLooper implements Runnable {
		public void run() {
			String message[] = { "M1", "M2", "M3", "M4", "M5", "M6" };
			try {
				for (String msg : message) {
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
		try {
			for (int i = 1; i <= 3; i++) {
				printWithThreadName(":)");
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
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
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			printWithThreadName("5. Gonna release lock of " + this);
		}
	}

	@Override
	public String toString() {
		return "WaitForNotify Object";
	}
}
