package pei.java.thirdp.lab.apachecommons;

import java.util.Random;

import org.apache.commons.math3.exception.MathArithmeticException;
import org.apache.commons.math3.primes.Primes;
import org.apache.commons.math3.util.ArithmeticUtils;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

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
		assertThat(Primes.isPrime(7));
		assertThat(Primes.isPrime(8)).isFalse();
		log.info("The prime factors of 8 is: {}", Primes.primeFactors(8).toString());

		int n = new Random().nextInt(10000);// random number below 10K
		int np = Primes.nextPrime(n);
		log.info("The first Prime number bigger or equal to {} is {}.", n, np);
		assertThat(Primes.isPrime(np));
	}

	@Test
	public void arithmeticTest() throws Exception {
		// overflows
		assertThatThrownBy(() -> ArithmeticUtils.addAndCheck(Integer.MAX_VALUE, 1))
				.isInstanceOf(MathArithmeticException.class);
		assertThatThrownBy(() -> ArithmeticUtils.subAndCheck(Integer.MIN_VALUE, 1))
				.isInstanceOf(MathArithmeticException.class);
	}
}
