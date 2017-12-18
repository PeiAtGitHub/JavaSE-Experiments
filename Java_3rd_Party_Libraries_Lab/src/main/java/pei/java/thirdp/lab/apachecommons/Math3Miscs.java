package pei.java.thirdp.lab.apachecommons;

import static org.junit.Assert.*;

import java.util.Random;

import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.primes.Primes;
import org.apache.commons.math3.util.ArithmeticUtils;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author pei
 *
 */
@Slf4j
public class Math3Miscs {

	
	@Test
	public void primesTest() throws Exception {
		assertTrue(Primes.isPrime(7));
		assertFalse(Primes.isPrime(8));
		log.info("The prime factors of 8 is: {}", Primes.primeFactors(8).toString());

		int n = new Random().nextInt(10000);// random number below 10K
		int np = Primes.nextPrime(n);
		log.info("The first Prime number bigger or equal to {} is {}.",
				n, np);
		assertTrue(Primes.isPrime(np));
	}
	
	@Test
	public void arithmeticTest() throws Exception {
		try {
			ArithmeticUtils.addAndCheck(Integer.MAX_VALUE, 1);
			fail("Add should'v caused overflow.");
		} catch (Exception e) {
			assertTrue(e instanceof MathArithmeticException);
		}
		
		try {
			ArithmeticUtils.subAndCheck(Integer.MIN_VALUE, 1);
			fail("Sub should'v caused overflow.");
		} catch (Exception e) {
			assertTrue(e instanceof MathArithmeticException);
		}
		
	}
}
