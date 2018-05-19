package pei.java.jse.lab.threading;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.assertj.core.api.Assertions.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import lombok.AllArgsConstructor;
import static com.github.peiatgithub.java.utils.Utils.*;

/**
 * 
 * @author pei
 *
 */
public class ExecutorAndThreadPooling {
    
    @Test
    public void basics() throws InterruptedException {
        ExecutorService  thrPoolExecutor = Executors.newFixedThreadPool(3);
        thrPoolExecutor.execute(new TheTask("AAA",5));
        thrPoolExecutor.execute(new TheTask("BBB",5));
        thrPoolExecutor.execute(new TheTask("CCC",5));
        thrPoolExecutor.execute(new TheTask("DDD",5));
        thrPoolExecutor.execute(new TheTask("EEE",5));
        thrPoolExecutor.shutdown();
        assertThat(thrPoolExecutor.isShutdown());
        assertThat(thrPoolExecutor.isTerminated()).isFalse();
        assertThat(thrPoolExecutor.awaitTermination(11, TimeUnit.SECONDS));
        assertThat(thrPoolExecutor.isTerminated());
        
    }
    
    @Test
    public void scheduled() throws InterruptedException {
        ScheduledExecutorService scheduledThrPool = Executors.newScheduledThreadPool(1);
        
        ScheduledFuture<?> beepingTask = scheduledThrPool
                .scheduleAtFixedRate(()->printlnWithThreadName("Beep"), 3, 3, TimeUnit.SECONDS);
                
        scheduledThrPool.schedule(()->{
            printlnWithThreadName("Gonna cancel beeping.");
            beepingTask.cancel(true);
        }, 16, TimeUnit.SECONDS);
        
        scheduledThrPool.awaitTermination(17, TimeUnit.SECONDS);
        scheduledThrPool.shutdown();
    }
}


/*
 * 
 */
@AllArgsConstructor
class TheTask implements Runnable {
    private String message;
    private int iterations;
    
    public void run() {
        for (int i=0; i < iterations; i++) {
            printlnWithThreadName(message);
            threadSleep(1000);
        }
    }
}