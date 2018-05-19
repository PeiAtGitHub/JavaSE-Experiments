package pei.java.jse.lab.threading;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.assertj.core.api.Assertions.*;
import static com.github.peiatgithub.java.utils.Utils.*;
import static com.github.peiatgithub.java.utils.Constants.*;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 
 * @author pei
 *
 */
public class ExplicitSync {

    @Test
    public void testLock() throws InterruptedException {
        Counter counter = new Counter(0);
        Thread t1 = createAndStartThread(new Incrementor(counter));
        Thread t2 = createAndStartThread(new Decrementor(counter));
        t1.join();
        t2.join();
        // without the sync mechanism, the result should be a random number
        assertThat(counter.getCount(), is(0));
    }

    @Test
    public void tesSemaphore() throws InterruptedException {
        int numOfThreads = 100;
        CountDownLatch latch = new CountDownLatch(numOfThreads);
        int numOfPermits = 30;
        Semaphore semp = new Semaphore(numOfPermits);
        Random r = new Random();
        for (int i = 1; i <= numOfThreads; i++) {
            new Thread(()-> {
                printlnWithThreadName("Started.");
                try {
                    semp.acquire();// blocked until got a permit
                    printlnWithThreadName("Got a permit.");
                    Thread.sleep(r.nextInt(3000));
                    semp.release();
                    printlnWithThreadName("Released a permit. Available permit: " + semp.availablePermits());
                    latch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        latch.await();
        printlnWithThreadName("All threads finished. Available permits: " + semp.availablePermits());
        assertThat(semp.availablePermits(), is(numOfPermits));
    }
    
    @Test @Ignore("This method got stuck.")
    public void deadLockDemo() throws InterruptedException {

        final Object resource1 = new Object();
        final Object resource2 = new Object();

        Thread t1 = createAndStartThread(()->{
                synchronized (resource1) {
                    printlnWithThreadName("Locked resource1");
                    threadSleep(1000);
                    printlnWithThreadName("Blocked till get resource2 lock.");
                    synchronized (resource2) {
                        printlnWithThreadName("Should never get resource2 lock.");
                    }
                }
                printlnWithThreadName("Should never reach here.");
            });
        
        Thread t2 = createAndStartThread(()->{
            synchronized (resource2) {
                printlnWithThreadName("Locked resource2");
                threadSleep(1000);
                printlnWithThreadName("Blocked till get resource1 lock.");
                synchronized (resource1) {
                    printlnWithThreadName("Should never get resource1 lock.");
                }
            }
            printlnWithThreadName("Should never reach here.");
        });
        
        t1.join();
        t2.join();
        
        Assert.fail(CODE_SHOULD_NOT_REACH_HERE);
        
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
@AllArgsConstructor
class Incrementor implements Runnable {
    private Counter counter;
    
    public void run() {
        repeatRun(100, ()->counter.increment());
    }
}

@AllArgsConstructor
class Decrementor implements Runnable {
    private Counter counter;
    
    public void run() {
    	repeatRun(100, ()->counter.decrement());
    }
}

//
class Counter {
	@Getter
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
            
            threadSleep(r.nextInt(500));

            printlnWithThreadName(String.valueOf(++count));
        } finally {
            lock.unlock();// thread release the lock
        }
    }

    public void decrement() {
        try {
            lock.lock();
            threadSleep(r.nextInt(500));
            printlnWithThreadName(String.valueOf(--count));
        } finally {
            lock.unlock();
        }
    }

}