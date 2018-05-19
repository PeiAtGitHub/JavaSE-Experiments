package pei.java.jse.lab.language;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.assertj.core.api.Assertions.*;
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
    public void systemTimeMillis() {
        
        assertThat(String.valueOf(System.currentTimeMillis()))
        .as("Expected to fail if you run this test earlier than 2001.Sep.09 or later than 2286.Nov.20, ")
        .hasSize(13);

    }

    @Test
    public void calendarAndDate() {
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(THE_MAX_13_DIGIT_NUMBER ); 
        Date date = calendar.getTime();
        
        assertThat(date.toString(), is("Sat Nov 20 18:46:39 CET 2286"));
        
        assertThat(calendar.getTimeInMillis(), is(THE_MAX_13_DIGIT_NUMBER));
        assertThat(calendar.get(Calendar.YEAR), is(2286));
        assertThat(calendar.get(Calendar.MONTH), is(Calendar.NOVEMBER));
        assertThat(calendar.get(Calendar.DATE), is(20));
        assertThat(calendar.get(Calendar.DAY_OF_WEEK), is(Calendar.SATURDAY));
        assertThat(calendar.get(Calendar.DAY_OF_YEAR), is(324));
        assertThat(calendar.get(Calendar.HOUR_OF_DAY), is(18));
        assertThat(calendar.get(Calendar.HOUR), is(6));
    
        // Representation
        assertThat(calendar.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.US), is("Nov"));
        
        // Manipulation
        calendar.add(Calendar.DATE, 100); // after 100 days 
        assertThat(calendar.get(Calendar.YEAR), is(2287));
        assertThat(calendar.get(Calendar.MONTH), is(Calendar.FEBRUARY));
        assertThat(calendar.get(Calendar.DATE), is(28));
        
        // Output formatting
        // Format method has features particularly for calendar 
        System.out.format("%1$tY-%1$tm-%1$td%n", calendar);// 2287-02-28
        System.out.format("%tD%n", calendar); // 02/28/87

    }

}
