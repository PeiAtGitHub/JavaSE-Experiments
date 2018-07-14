package pei.java.thirdp.lab.guava;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author pei
 *
 */
@Slf4j
public class ConcurrencyDemo {


    @Test
    public void testListenableFuture() throws Exception {
        
        ListeningExecutorService exeService = MoreExecutors.listeningDecorator(Executors.newFixedThreadPool(2));

        Futures.addCallback((ListenableFuture<String>) exeService
        		.submit(new CallableImpl()), new FutureCallbackImpl<String>(), exeService);
        
        exeService.awaitTermination(2, TimeUnit.SECONDS);
    }
    
}

@Slf4j
class FutureCallbackImpl<String> implements FutureCallback<String> {
    
    public void onSuccess(String result) {
        log.info("Got Result: {}", result);
    }

    public void onFailure(Throwable thrown) {
        log.info("Got Failure throwable: ", thrown);
    }

}

class CallableImpl<String> implements Callable<String> {
    @Override
    public String call() throws Exception {
        Thread.sleep(1000);
        if(new Random().nextBoolean()) {
            return (String) "Callable Task Complete!";
        }else {
            throw new Exception("Callable Task Fail!");
        }
    }

}

