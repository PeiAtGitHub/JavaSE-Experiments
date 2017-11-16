package pei.java.jse.lab.language;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.junit.Test;

/**
 * 
 * @author pei
 *
 */
public class DateAndTime {

	public static final long THE_MAX_13_DIGIT_NUMBER = 9999999999999l;
	
	@Test
	public void systemTime() {
		long nanoTime1 = System.nanoTime();// only for measuring elapsed time 
		
		assertEquals(13, String.valueOf(System.currentTimeMillis()).length());

		assertTrue((System.nanoTime() - nanoTime1) > 0);
	}

	@Test
	public void calendarAndDate() {
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(THE_MAX_13_DIGIT_NUMBER ); 
		Date date = calendar.getTime();
		
		// get a human readable text
		assertTrue(date.toString().equals("Sat Nov 20 18:46:39 CET 2286"));
		
		assertEquals(THE_MAX_13_DIGIT_NUMBER , calendar.getTimeInMillis());
		assertEquals(2286, calendar.get(Calendar.YEAR));
		assertEquals(Calendar.NOVEMBER, calendar.get(Calendar.MONTH));
		assertEquals(20, calendar.get(Calendar.DATE));
		assertEquals(Calendar.SATURDAY, calendar.get(Calendar.DAY_OF_WEEK));
		assertEquals(324, calendar.get(Calendar.DAY_OF_YEAR));
		assertEquals(18, calendar.get(Calendar.HOUR_OF_DAY));
		assertEquals(6, calendar.get(Calendar.HOUR));
	
		// Representation
		assertTrue(calendar.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.US).equals("Nov"));
		
		// Manipulation
		calendar.add(Calendar.DATE, 100); // after 100 days 
		assertEquals(2287, calendar.get(Calendar.YEAR));
		assertEquals(Calendar.FEBRUARY, calendar.get(Calendar.MONTH));
		assertEquals(28, calendar.get(Calendar.DATE));
		
		// Output formatting
		// Format method has features particularly for calendar 
		System.out.format("%tY-%tm-%td%n", calendar, calendar, calendar);// 2287-02-28
		System.out.format("%tD%n", calendar); // 02/28/87

		
	}

}
