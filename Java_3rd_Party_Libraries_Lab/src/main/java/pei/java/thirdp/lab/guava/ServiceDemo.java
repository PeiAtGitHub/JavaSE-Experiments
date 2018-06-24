package pei.java.thirdp.lab.guava;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static com.github.peiatgithub.java.utils.Constants.*;
import static com.github.peiatgithub.java.utils.Utils.printlnWithThreadName;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.assertj.core.api.Assertions.*;
import static com.github.peiatgithub.java.utils.Utils.*;

import org.junit.Test;

import com.google.common.collect.Sets;
import com.google.common.util.concurrent.AbstractExecutionThreadService;
import com.google.common.util.concurrent.AbstractScheduledService;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.Service;
import com.google.common.util.concurrent.Service.State;
import com.google.common.util.concurrent.ServiceManager;
import com.google.common.util.concurrent.ServiceManager.Listener;

/**
 *
 * Quote: "The Service interface is subtle and complicated. We do not recommend
 * implementing it directly. Instead please use one of the abstract base classes
 * in guava as the base for your implementation. Each base class supports a
 * specific threading model."
 * 
 * @author pei
 *
 */
public class ServiceDemo {

    private Random r = new Random();

    @Test
    public void testAbstractExeThreadService() throws Exception {

        MySingleThreadService s = new MySingleThreadService();
        assertThat(s.state(), is(State.NEW));

        s.addListener(new MyListener(), Executors.newSingleThreadExecutor());

        s.startAsync().awaitRunning();
        assertThat(s.isRunning());
        assertThat(s.state(), is(State.RUNNING));

        int ms = r.nextInt(10000);
        printlnWithThreadName("Service gonna run for " + TimeUnit.MILLISECONDS.toSeconds(ms) + "s.");
        Thread.sleep(ms);

        s.stopAsync().awaitTerminated();
        assertThat(s.state(), is(State.TERMINATED));

    }

    @Test
    public void testAbstractScheduledService() throws Exception {

        MyScheduledService s = new MyScheduledService();
        assertThat(s.state(), is(State.NEW));

        s.addListener(new MyListener(), Executors.newSingleThreadExecutor());

        s.startAsync().awaitRunning();
        assertThat(s.isRunning());
        assertThat(s.state(), is(State.RUNNING));

        int ms = r.nextInt(10000);
        printlnWithThreadName("Service gonna run for " + TimeUnit.MILLISECONDS.toSeconds(ms) + "s.");
        Thread.sleep(ms);

        s.stopAsync().awaitTerminated();
        assertThat(s.state(), is(State.TERMINATED));

    }

    @Test
    public void testServiceFailure() throws Exception {

        ADoomedService s = new ADoomedService();
        s.addListener(new MyListener(), Executors.newSingleThreadExecutor());
        s.startAsync().awaitRunning();

        Throwable e = catchThrowable(() -> s.awaitTerminated());
        assertThat(e).isInstanceOf(ISE);
        printlnWithThreadName(e.getMessage());

        assertThat(s.state(), is(State.FAILED));
        assertThat(s.failureCause().getMessage(), is("Service Failed"));

    }

    @Test
    public void testServiceManager() throws Exception {
        /*
         * Below simulates a server run
         */
        ServiceManager manager = new ServiceManager(
                Sets.newHashSet(new MySingleThreadService(), new MySingleThreadService()));

        manager.addListener(new Listener() {
            public void stopped() {
                printlnWithThreadName("ServiceManager.Listener: stopped.");
            }

            public void healthy() {
                printlnWithThreadName("ServiceManager.Listener: healthy.");
            }

            public void failure(Service service) {
                printlnWithThreadName("ServiceManager.Listener: failure.");
            }
        }, MoreExecutors.directExecutor());

        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                printlnWithThreadName("Running shutdown hook.");
                manager.stopAsync().awaitStopped();
            }
        });

        manager.startAsync();
    }

}

/*
 * 
 */
class MySingleThreadService extends AbstractExecutionThreadService {

    private int counter = 0;

    @Override
    protected void run() throws Exception {
        while (isRunning()) {
            Thread.sleep(1000);
            printlnWithThreadName(str("{} has been running for {}s.", this.getClass().getSimpleName(), ++counter));
        }
        printlnWithThreadName("Service " + this.getClass().getSimpleName() + " terminated.");
    }

}

class MyScheduledService extends AbstractScheduledService {

    private int counter = 0;

    @Override
    protected void runOneIteration() throws Exception {
        printlnWithThreadName("Scheduled service iteration: " + (++counter));
    }

    @Override
    protected Scheduler scheduler() {
        return AbstractScheduledService.Scheduler.newFixedDelaySchedule(1, 1, TimeUnit.SECONDS);
    }

}

class ADoomedService extends AbstractExecutionThreadService {
    @Override
    protected void run() throws Exception {
        for (int i = 3; i > 0; i--) {
            printlnWithThreadName("Service failure count down: " + i);
            Thread.sleep(1000);
        }
        throw new Exception("Service Failed");
    }
}

class MyListener extends com.google.common.util.concurrent.Service.Listener {

    @Override
    public void starting() {
        printlnWithThreadName("LISTENER: starting.");
    }

    @Override
    public void running() {
        printlnWithThreadName("LISTENER: running.");
    }

    @Override
    public void stopping(State from) {
        printlnWithThreadName("LISTENER: stopping from state " + from);
    }

    @Override
    public void failed(State from, Throwable failure) {
        printlnWithThreadName("LISTENER: failed from state " + from + ", because: " + failure.getMessage());
    }

    @Override
    public void terminated(State from) {
        printlnWithThreadName("LISTENER: terminated from state " + from);
    }

}
