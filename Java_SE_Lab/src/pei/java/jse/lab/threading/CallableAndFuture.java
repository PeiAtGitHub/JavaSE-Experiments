package pei.java.jse.lab.threading;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.junit.Test;

import pei.java.jse.lab.utils.Utils;

/**
 * A Future represents the result of an asynchronous computation.
 * 
 * @author pei
 *
 */

public class CallableAndFuture {

	final static String RETURNED_TEXT = "Callable Task Finished";

	@Test
	public void basics() throws ExecutionException, InterruptedException {
		ExecutorService threadPool = Executors.newFixedThreadPool(1);

		Callable<String> theTask = new CallableTask();
		Future<String> future = threadPool.submit(theTask);
		assertTrue(future.get().equals(RETURNED_TEXT));// wait until task finished.

		// Callable task can be reused
		// FutureTask implements the Future interface.
		FutureTask<String> fTask = new FutureTask<String>(theTask);
		threadPool.submit(fTask);
		assertFalse(fTask.isDone());
		assertTrue(fTask.get().equals(RETURNED_TEXT));

		threadPool.shutdown();

		//
		future = Executors.newCachedThreadPool().submit(theTask);
		try {
			future.get(3, TimeUnit.SECONDS);
			fail(Utils.SHOULD_THROW_EXCEPTION);
		} catch (Exception e) {
			assertTrue(e instanceof TimeoutException);
		}
		future.cancel(true);
		assertTrue(future.isCancelled());
		assertTrue(future.isDone());
	}
}

//
class CallableTask implements Callable<String> {
	public String call() throws Exception {
		Thread.sleep(5000);// 5s
		return CallableAndFuture.RETURNED_TEXT;
	}
}
