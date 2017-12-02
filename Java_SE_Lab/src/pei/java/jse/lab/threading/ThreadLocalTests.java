package pei.java.jse.lab.threading;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Random;

import org.junit.Test;

/**
 * 
 * @author pei
 *
 */
public class ThreadLocalTests {
	
		public ThreadLocal<Integer> tlNumber = new ThreadLocal<Integer>();
		public Integer number = null;
		
		
		@Test
		public void testThreadLocal() throws InterruptedException {
			ThreadLocalTests tlt = new ThreadLocalTests();
			tlt.tlNumber.set(10);
			tlt.number = new Integer(10);
			
			assertEquals(10, tlt.tlNumber.get().intValue());
			assertEquals(10, tlt.number.intValue());

			ThrLocalSetter t1 = new ThrLocalSetter(50, tlt);
			ThrLocalSetter t2 = new ThrLocalSetter(100, tlt);
			Thread thr1 = new Thread(t1);
			Thread thr2 = new Thread(t2);
			thr1.start();
			thr2.start();
			thr1.join();
			thr2.join();
			
			assertEquals(10, tlt.tlNumber.get().intValue());
			assertEquals(10, t1.getTlNumber());
			assertEquals(10, t2.getTlNumber());

			assertTrue((tlt.number.intValue() == 50) 
					|| (tlt.number.intValue() == 100));
			assertTrue((tlt.number.intValue() == t1.getNumber()) 
					&& (t1.getNumber() == t2.getNumber()));
		}

}

/*
 * 
 */
class ThrLocalSetter implements Runnable {
	ThreadLocalTests tlt;
	int n;
	//
	ThrLocalSetter(int n, ThreadLocalTests tlt){
		this.n = n;
		this.tlt = tlt;
	}
	//
	public void run() {
		this.tlt.tlNumber.set(n);
		try {
			Thread.sleep(new Random().nextInt(3000)); // max 3s
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		assertEquals(n, getTlNumber());
		this.tlt.number = n;
	}
	
	//
	public int getTlNumber() {
		return this.tlt.tlNumber.get().intValue();
	}
	
	public int getNumber() {
		return this.tlt.number.intValue();
	}
	
}

