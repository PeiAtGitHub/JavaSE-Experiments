package pei.java.jse.lab.java7new;

import static org.junit.Assert.assertTrue;

import java.util.Random;
import org.junit.Test;

/**
 * 
 * @author pei
 *
 */
public class MultiCatch {
	
	@Test
	public void testMultiCatch(){
		Random r = new Random();
		for (int i = 0; i < 5; i++) {
			try {
				if(r.nextBoolean()) {
					System.out.println("About to throw Exception A");
					throw new ExceptionA();
				}else {
					System.out.println("About to throw Exception B");
					throw new ExceptionB();
				}
			} catch (ExceptionA | ExceptionB e) { 
				assertTrue(e instanceof ExceptionA 
						|| e instanceof ExceptionB);
			}
		}
	}

}

class ExceptionA extends Exception{}
class ExceptionB extends Exception{}
