package pei.java.jse.lab.threading;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.assertj.core.api.Assertions.*;

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

        future = Executors.newCachedThreadPool().submit(theTask);
        
        final Future<String> tmpFuture = future; // this is only to make the below lamda work.
        assertThatThrownBy(()->tmpFuture.get(1, TimeUnit.SECONDS)).isInstanceOf(TimeoutException.class);
        
        future.cancel(true);
        assertThat(future.isCancelled());
        assertThat(future.isDone());
    }
}

//
class CallableTask implements Callable<String> {
    public String call() throws Exception {
        Thread.sleep(3000);
        return CallableAndFuture.TASK_RETURNED_TEXT;
    }
}
