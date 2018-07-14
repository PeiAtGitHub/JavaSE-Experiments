package pei.java.jse.lab.language;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.junit.jupiter.api.Test;

/**
 * @author pei
 */
public class DateAndTime {

    public static final long THE_MAX_13_DIGIT_NUMBER = 9999999999999L;

    @Test
    public void systemTimeMillis() {

        assertThat(String.valueOf(System.currentTimeMillis()))
                .as("Expected to fail if you run this test earlier than 2001.Sep.09 or later than 2286.Nov.20, ")
                .hasSize(13);

    }

    @Test
    public void calendarAndDate() {

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(THE_MAX_13_DIGIT_NUMBER);
        Date date = calendar.getTime();

        assertEquals("Sat Nov 20 18:46:39 CET 2286", date.toString());

        assertEquals(THE_MAX_13_DIGIT_NUMBER, calendar.getTimeInMillis());
        assertEquals(2286, calendar.get(Calendar.YEAR));
        assertEquals(Calendar.NOVEMBER, calendar.get(Calendar.MONTH));
        assertEquals(20, calendar.get(Calendar.DATE));
        assertEquals(Calendar.SATURDAY, calendar.get(Calendar.DAY_OF_WEEK));
        assertEquals(324, calendar.get(Calendar.DAY_OF_YEAR));
        assertEquals(18, calendar.get(Calendar.HOUR_OF_DAY));
        assertEquals(6, calendar.get(Calendar.HOUR));

        // Representation
        assertEquals("Nov", calendar.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.US));

        // add 100 days
        calendar.add(Calendar.DATE, 100);
        assertEquals(2287, calendar.get(Calendar.YEAR));
        assertEquals(Calendar.FEBRUARY, calendar.get(Calendar.MONTH));
        assertEquals(28, calendar.get(Calendar.DATE));

        // Format method has features particularly for calendar
        System.out.format("%1$tY-%1$tm-%1$td%n", calendar);// 2287-02-28
        System.out.format("%tD%n", calendar); // 02/28/87

    }

}
