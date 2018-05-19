package pei.java.jse.lab.threading;

import static com.github.peiatgithub.java.utils.Utils.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.assertj.core.api.Assertions.*;

import java.util.Random;

import org.junit.Test;

import lombok.AllArgsConstructor;

/**
 * 
 * @author pei
 *
 */
public class ThreadLocalDemo {
    
        public ThreadLocal<Integer> tlNumber = new ThreadLocal<Integer>();
        public Integer number = null;
        
        @Test
        public void testThreadLocal() throws InterruptedException {
            ThreadLocalDemo tlDemo = new ThreadLocalDemo();
            tlDemo.tlNumber.set(10);
            tlDemo.number = new Integer(10);
            
            assertThat(tlDemo.tlNumber.get().intValue(), is(10));
            assertThat(tlDemo.number.intValue(), is(10));

            ThrLocalSetter t1 = new ThrLocalSetter(50, tlDemo);
            ThrLocalSetter t2 = new ThrLocalSetter(100, tlDemo);
            Thread thr1 = createAndStartThread(t1);
            Thread thr2 = createAndStartThread(t2);
            thr1.join();
            thr2.join();
            
            assertThat(tlDemo.tlNumber.get().intValue(), is(10));
            assertThat(t1.getTlNumber(), is(10));
            assertThat(t2.getTlNumber(), is(10));

            assertThat(tlDemo.number.intValue(), anyOf(is(50), is(100)));
            assertThat(tlDemo.number.intValue(), is(t1.getNumber())); 
            assertThat(t1.getNumber(), is(t2.getNumber())); 
        }

}

/*
 * 
 */
@AllArgsConstructor
class ThrLocalSetter implements Runnable {
    
	int n;
	ThreadLocalDemo tlt;

    public void run() {
        this.tlt.tlNumber.set(n);
        threadSleep(new Random().nextInt(3000)); // max 3s
        assertThat(getTlNumber(), is(n));
        this.tlt.number = n;
    }
    
    public int getTlNumber() {
        return this.tlt.tlNumber.get().intValue();
    }
    
    public int getNumber() {
        return this.tlt.number.intValue();
    }
    
}

