package pei.java.jse.lab.threading;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.concurrent.*;

import org.junit.jupiter.api.Test;


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
        assertEquals(TASK_RETURNED_TEXT, future.get());

        FutureTask<String> fTask = new FutureTask<String>(theTask);
        threadPool.submit(fTask);
        assertEquals(TASK_RETURNED_TEXT, fTask.get());

        threadPool.shutdown();

        future = Executors.newCachedThreadPool().submit(theTask);
        
        final Future<String> tmpFuture = future; // this is only to make the below lamda work.
        assertThrows(TimeoutException.class, ()->tmpFuture.get(1, TimeUnit.SECONDS));
        
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
