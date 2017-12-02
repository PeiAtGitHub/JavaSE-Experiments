package pei.java.jse.lab.threading;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Ignore;
import org.junit.Test;

import static pei.java.jse.lab.Utils.*;

/**
 * 
 * @author pei
 *
 */
public class ExplicitSync {

	@Test
	public void testLock() throws InterruptedException {
		Counter counter = new Counter(0);
		Thread t1 = new Thread(new Incrementor(counter));
		Thread t2 = new Thread(new Decrementor(counter));
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		// without the sync mechanism, the result should be a random number
		assertEquals(0, counter.getCount());
	}

	@Test
	public void tesSemaphore() throws InterruptedException {
		int numberOfThreads = 100;
		CountDownLatch latch = new CountDownLatch(numberOfThreads);
		int numberOfPermits = 30;
		Semaphore semp = new Semaphore(numberOfPermits);
		Random r = new Random();
		for (int i = 1; i <= numberOfThreads; i++) {
			new Thread(()-> {
				printWithThreadName("Started.");
				try {
					semp.acquire();// blocked until got a permit
					printWithThreadName("Got a permit.");
					Thread.sleep(r.nextInt(3000));
					semp.release();
					printWithThreadName("Released a permit. Available permit: " + semp.availablePermits());
					latch.countDown();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}).start();
		}
		latch.await();
		printWithThreadName("All threads finished. Available permits: " + semp.availablePermits());
		assertEquals(numberOfPermits, semp.availablePermits());
	}
	
	@Test @Ignore("This method got stuck.")
	public void deadLockDemo() throws InterruptedException {

		final Object resource1 = new Object();
		final Object resource2 = new Object();

		Thread t1 = new Thread(()->{
				synchronized (resource1) {
					printWithThreadName("Locked resource1");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						//
					}
					printWithThreadName("Blocked till get resource2 lock.");
					synchronized (resource2) {
						printWithThreadName("Should never get resource2 lock.");
					}
				}
				printWithThreadName("Should never reach here.");
			});
		
		Thread t2 = new Thread(()->{
			synchronized (resource2) {
				printWithThreadName("Locked resource2");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					//
				}
				printWithThreadName("Blocked till get resource1 lock.");
				synchronized (resource1) {
					printWithThreadName("Should never get resource1 lock.");
				}
			}
			printWithThreadName("Should never reach here.");
		});
		
		t1.start();
		t2.start();

		t1.join();
		t2.join();
		
		fail("Should never reach here.");
		
		/*
		 * Sync cannot be interrupted.
		 * ReentrantLock.lockInterruptibly() can achieve an 
		 * interruptible waiting for a lock.
		 */
	}
}

/*
 * 
 */

class Incrementor implements Runnable {
	private Counter counter;
	
	public Incrementor(Counter counter) {
		this.counter = counter;
	}
	
	public void run() {
		for (int i = 0; i < 100; i++) {
			counter.increment();
		}
	}
}

class Decrementor implements Runnable {
	private Counter counter;
	
	public Decrementor(Counter counter) {
		this.counter = counter;
	}

	public void run() {
		for (int i = 0; i < 100; i++) {
			counter.decrement();
		}
	}
}

//
class Counter {
	private int count;
	private Lock lock = new ReentrantLock();
	Random r = new Random();

	public Counter(int count) {
		super();
		this.count = count;
	}

	/*
	 * Actually, the access conflicts can be solved simply by sync the methods.
	 * However, we use Lock here for demo
	 */
	public void increment() {
		try {
			lock.lock();// thread acquires the lock
			try {
				Thread.sleep(r.nextInt(500));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			count++;
			printWithThreadName(String.valueOf(count));
		} finally {
			lock.unlock();// thread release the lock
		}
	}

	public void decrement() {
		try {
			lock.lock();
			try {
				Thread.sleep(r.nextInt(500));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			count--;
			printWithThreadName(String.valueOf(count));
		} finally {
			lock.unlock();
		}
	}

	public int getCount() {
		return count;
	}
}