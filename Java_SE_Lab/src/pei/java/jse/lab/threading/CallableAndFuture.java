package pei.java.jse.lab.threading;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static pei.java.jse.lab.utils.Utils.*;

import java.util.concurrent.*;

import org.junit.Test;


/**
 * A Future represents the result of an asynchronous computation.
 * 
 * @author pei
 *
 */

public class CallableAndFuture {

    final static String TASK_RETURNED_TEXT = "Callable Task Finished";

    @Test
    public void basics() throws ExecutionException, InterruptedException {
        ExecutorService threadPool = Executors.newFixedThreadPool(1);

        Callable<String> theTask = new CallableTask();
        Future<String> future = threadPool.submit(theTask);
        assertThat(future.get(), is(TASK_RETURNED_TEXT));

        FutureTask<String> fTask = new FutureTask<String>(theTask);
        threadPool.submit(fTask);
        assertThat(fTask.get(), is(TASK_RETURNED_TEXT));

        threadPool.shutdown();

        //
        future = Executors.newCachedThreadPool().submit(theTask);
        try {
            future.get(1, TimeUnit.SECONDS);
            fail(SHOULDv_THROWN_EXCEPTION);
        } catch (Exception e) {
            assertThat(e, instanceOf(TimeoutException.class));
        }
        future.cancel(true);
        assertTrue(future.isCancelled());
        assertTrue(future.isDone());
    }
}

//
class CallableTask implements Callable<String> {
    public String call() throws Exception {
        Thread.sleep(3000);
        return CallableAndFuture.TASK_RETURNED_TEXT;
    }
}
