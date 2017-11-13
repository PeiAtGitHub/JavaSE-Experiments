package pei.java.jse.lab.language;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.DecimalFormat;
import java.util.Random;

import org.junit.Test;

/**
 * 
 * @author pei
 *
 */
public class Numbers {

	@Test
	public void someMathUtils() {
		//
		assertEquals(0, Math.round(0.4));
		assertEquals(1, Math.round(0.6));
		//
		double r = Math.random();
		assertTrue(r >= 0.0 && r < 1.0);
		//
		assertTrue(Math.max(1, 2) == 2);
		assertTrue(Math.min(1, 2) == 1);
		//
		assertTrue(Math.pow(2, 8) == 256);
		assertTrue(Math.sqrt(36) == 6);
		assertTrue(Math.toDegrees(Math.PI) == 180);

	}

	@Test
	public void numberOverFlow() {
		System.out.println("Integer.MAX_VALUE is " + Integer.MAX_VALUE + ", which is appx "
				+ convertNumberToReadableString(Integer.MAX_VALUE));

		System.out.println("Long.MAX_VALUE is " + Long.MAX_VALUE + ", which is appx "
				+ convertNumberToReadableString(Long.MAX_VALUE));

		System.out.println("Float.MAX_VALUE is " + Float.MAX_VALUE + ", which is appx "
				+ convertNumberToReadableString(Float.MAX_VALUE));

		System.out.println("Double.MAX_VALUE is " + Double.MAX_VALUE + ", which has no better representation.");

		System.out.println("Demo of number overflow:");
		System.out.println("Integer.MAX_VALUE + 123 is: " + (Integer.MAX_VALUE + 123));
		System.out.println("Integer.MAX_VALUE x 123 is: " + (Integer.MAX_VALUE * 123));

		assertTrue((Integer.MAX_VALUE + 123) < Integer.MAX_VALUE);
		assertTrue((Integer.MAX_VALUE * 123) < Integer.MAX_VALUE);

	}
	
	@Test
	public void randoms() {
		Random r = new Random();
		for (int i = 0; i < 5; i++) {
			// random integer [0,100), same convention: ending value is exclusive
			System.out.println(r.nextInt(100)); 
		}
		for (int i = 0; i < 5; i++) {
			System.out.println(r.nextBoolean());// convenient for the 2-options-case 
		}
		for (int i = 0; i < 5; i++) {
			System.out.println(r.nextGaussian()); 
		}
	}
	

	@Test
	public void numberPrecise() {
		// Some Demos
		System.out.println("1 + 0.000000000000001 is: "+(1 + 0.000000000000001));// 1.000000000000001
		System.out.println("1 + 0.0000000000000001 is: "+(1 + 0.0000000000000001));// 1.0
	
		System.out.println("100000000002.0002 + 100000000009.0009 is: "
				+ (100000000002.0002d + 100000000009.0009d));   // 2.000000000110011E11
		System.out.println("111111111111.1111 + 222222222222.2222 is: "
				+ (111111111111.1111d + 222222222222.2222d));   // 3.333333333333333E11
		
		System.out.println("0.00000025 * 0.00000025 is: "+(0.00000025 * 0.00000025)); // 6.25E-14
		System.out.println("0.000000025 * 0.000000025 is: "+(0.000000025 * 0.000000025)); // 6.249999999999999E-16
		
		System.out.println("100.00000025 * 100.00000025 is: "+(100.00000025 * 100.00000025)); // 10000.00005
		System.out.println("100.000000025 * 100.000000025 is: "+(100.000000025 * 100.000000025)); // 10000.000005000002
		System.out.println("2000000000 * 100.123456789 is: "+(2000000000 * 100.123456789)); // 2.00246913578E11
	}

	/*
	 * Utils
	 */
	private static String convertNumberToReadableString(double num) {
		String result = "";
		double numAbs = Math.abs(num);
		long K = 1000;
		long MN = 1000000;
		long BN = 1000000000;

		DecimalFormat df = new DecimalFormat("#.#");

		if (numAbs >= BN) {
			result = df.format(numAbs / BN).toString() + " Bn";
		} else if (numAbs >= MN) {
			result = df.format(numAbs / MN).toString() + " Mn";
		} else if (numAbs >= K) {
			result = df.format(numAbs / K).toString() + " K";
		} else {
			result = String.valueOf(numAbs);
		}
		if (num < 0) {
			result = "-" + result;
		}
		return result;
	}


}
