package pei.java.jse.lab.threading;

import static com.github.peiatgithub.java.utils.Utils.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Random;

import org.junit.jupiter.api.Test;

import lombok.AllArgsConstructor;

/**
 * @author pei
 */
public class ThreadLocalDemo {
    
        public ThreadLocal<Integer> tlNumber = new ThreadLocal<Integer>();
        public Integer number = null;
        
        @Test
        public void testThreadLocal() throws InterruptedException {
            ThreadLocalDemo tlDemo = new ThreadLocalDemo();
            tlDemo.tlNumber.set(10);
            tlDemo.number = new Integer(10);
            
            assertEquals(10, tlDemo.tlNumber.get().intValue());
            assertEquals(10, tlDemo.number.intValue());

            ThrLocalSetter t1 = new ThrLocalSetter(50, tlDemo);
            ThrLocalSetter t2 = new ThrLocalSetter(100, tlDemo);
            Thread thr1 = createAndStartThread(t1);
            Thread thr2 = createAndStartThread(t2);
            thr1.join();
            thr2.join();
            
            assertEquals(10, tlDemo.tlNumber.get().intValue());
            assertEquals(10, t1.getTlNumber());
            assertEquals(10, t2.getTlNumber());

            assertThat(tlDemo.number.intValue()).isIn(50, 100);
            assertEquals(t1.getNumber(), tlDemo.number.intValue()); 
            assertEquals(t2.getNumber(), t1.getNumber()); 
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
        assertEquals(n, getTlNumber());
        this.tlt.number = n;
    }
    
    public int getTlNumber() {
        return this.tlt.tlNumber.get().intValue();
    }
    
    public int getNumber() {
        return this.tlt.number.intValue();
    }
    
}

