package pei.java.jse.lab.java8new;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.OffsetDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

/**
 * @author pei
 */
@Slf4j
public class DateAndTimeDemos {

    
    static final long ONE_HOUR_IN_SECS = 3600L;

    /**
     * <pre>
     * Notes for Understanding Java 8's Date and Time:
     * 1. You must understand: There is only one UNIQUE time on Earth. 
     *    DateAndTime and ZoneOffset Combined is the representation of this UNIQUE time.
     * 2. UTC(Universal Time Coordinated) is roughly interchangeable with the less scientific GMT(Greenwich Mean Time). 
     * 
     * Some abbreviations: 
     *                     TZ: Time Zone; 
     *                     STZ: System Time Zone; 
     *                     DST: Daylight Saving Time
     * </pre>
     */
    @Test
    public void understandTheJavaDateTime() throws Exception {
        
        /*
         *  Note: Some people may have language and locale settings different from where they really are,
         *        but people would localise their computer clock & TZ(or automatically synced from network clock)
         *        E.g. 
         *        a guy from USA is staying in China, his computer language and locale are still English and en_US, 
         *        ("user.language" is "en"; "user.country" is "US, "locale" is "en_US")
         *        but most likely he will change the computer's clock and TZ to China's clock and TZ.
         *        (or maybe automatically changed by network clock)
         * 
         *  JVM got all these properties from the hosting OS.
         */
        log.info("***** Check out your settings in following statements:");
        
        log.info(System.getProperty("user.language"));
        log.info(System.getProperty("user.country"));
        
        log.info(Locale.getDefault().toString());
        
        log.info(TimeZone.getDefault().getDisplayName());
        log.info(ZoneId.systemDefault().getId());
        
        /*
         * Local Date and Time is just a Representation, Regardless of Location
         */
        log.info("***** Local DateAndTime:");
        LocalDateTime localDt201801 = LocalDateTime.of(2018, 1, 1, 0, 0, 0);
        assertEquals("2018-01-01T00:00", localDt201801.toString());
        
        LocalDateTime localDt201807 = LocalDateTime.of(2018, 7, 1, 0, 0, 0);;
        assertEquals("2018-07-01T00:00", localDt201807.toString());
        
        log.info("This LocalDateTime is a representation of the STZ's current date and time: {}" , LocalDateTime.now());
        
        /*
         * Time Zones, Offsets, Rules, DST, The Universal Time, and etc.
         */
        log.info("This is the STZ's ZoneId: {}", ZoneOffset.systemDefault());
        
        ZoneId zoneIdCet = ZoneId.of("CET");
        // TZ offset depends on a LocalDateTime because there might be DST.
        ZoneOffset zoneOffset = zoneIdCet.getRules().getOffset(localDt201801);
        assertEquals("+01:00", zoneOffset.toString(), "TZ CET has 1 hour offset from UTC at 20180101000000");
        assertEquals(ONE_HOUR_IN_SECS, zoneOffset.get(ChronoField.OFFSET_SECONDS));
        
        zoneOffset = zoneIdCet.getRules().getOffset(localDt201807);
        assertEquals("+02:00", zoneOffset.toString(), "TZ CET has 2 hours offset from UTC at 20180701000000");
        assertEquals(ONE_HOUR_IN_SECS * 2, zoneOffset.get(ChronoField.OFFSET_SECONDS));
        
        ZoneId zoneIdPrc = ZoneId.of("PRC");
        // this TZ has NO DST
        zoneOffset = zoneIdPrc.getRules().getOffset(localDt201801);
        assertEquals("+08:00", zoneOffset.toString(), "TZ PRC has 8 hours offset from UTC at 20180101000000");
        assertEquals(ONE_HOUR_IN_SECS * 8, zoneOffset.get(ChronoField.OFFSET_SECONDS));
        
        zoneOffset = zoneIdPrc.getRules().getOffset(localDt201807);
        assertEquals("+08:00", zoneOffset.toString(), "TZ PRC has 8 hours offset from UTC at 20180701000000");
        assertEquals(ONE_HOUR_IN_SECS * 8, zoneOffset.get(ChronoField.OFFSET_SECONDS));
    
        ZoneOffset zoneOffset8 = ZoneOffset.ofHours(8);
        assertEquals(zoneOffset, zoneOffset8);
        ZoneOffset zoneOffsetNegative8 = ZoneOffset.ofHours(-8);

        // 1970.1.1 00:00 UTC(ZoneOffSet 0) is computer's Epoch time
        LocalDateTime epochLDT= LocalDateTime.of(1970, 1, 1, 0, 0, 0);

        // DateAndTime Combined with ZoneOffSet: OffsetDateTime
        OffsetDateTime offsetDt201801= localDt201801.atOffset(zoneOffset8);
        assertEquals("2018-01-01T00:00+08:00", offsetDt201801.toString());
        assertEquals(0, offsetDt201801.getHour(), "The hour is STILL 0!");
        assertEquals(zoneOffset8, offsetDt201801.getOffset());
        
        assertEquals(-1, epochLDT.atOffset(zoneOffset8).toInstant().compareTo(Instant.EPOCH));// Earlier
        assertEquals(1, epochLDT.atOffset(zoneOffsetNegative8).toInstant().compareTo(Instant.EPOCH));// Later

        // DateAndTime Combined with Zone: ZonedDateTime 
        ZonedDateTime zonedDt201801 = ZonedDateTime.of(localDt201801, zoneIdPrc);
        assertEquals(0, zonedDt201801.getHour(), "The hour is STILL 0");
        assertEquals("2018-01-01T00:00+08:00[PRC]", zonedDt201801.toString());
        assertEquals(zoneOffset8, zonedDt201801.getOffset());
        
        assertEquals(-1, ZonedDateTime.of(epochLDT, zoneIdPrc).toInstant().compareTo(Instant.EPOCH));// Earlier
        
        // Instant: the universal time
        assertEquals(Instant.now().getEpochSecond(), System.currentTimeMillis()/1000L
                , "This assertion has very low possibility to fail...");

        assertEquals("1970-01-01T00:00:00Z", Instant.EPOCH.toString(), "instant.toString() shows the UTC time");
        assertEquals("1970-01-01T08:00+08:00", Instant.EPOCH.atOffset(zoneOffset8).toString());
        assertEquals("1970-01-01T08:00+08:00[PRC]", Instant.EPOCH.atZone(zoneIdPrc).toString());

        assertEquals(1, Instant.now().compareTo(Instant.EPOCH)); // later

        // DateTime --> Universal time
        assertEquals("1970-01-01T00:00:00Z", epochLDT.toInstant(ZoneOffset.UTC).toString());
        assertEquals("1969-12-31T16:00:00Z", epochLDT.toInstant(zoneOffset8).toString()); 
        assertEquals("1970-01-01T08:00:00Z", epochLDT.toInstant(zoneOffsetNegative8).toString()); 

        // Universal time --> DateTime
        assertEquals("1970-01-01T01:00", LocalDateTime.ofInstant(Instant.EPOCH, zoneIdCet).toString());
        assertEquals("1970-01-01T08:00", LocalDateTime.ofInstant(Instant.EPOCH, zoneIdPrc).toString());

        // Universal time --> Java's old Date
        assertEquals("Thu Jan 01 01:00:00 CET 1970", Date.from(Instant.EPOCH).toString()
                , "Date implicitly uses STZ");
    }
    

    @Test
    public void demoLocalDateTime() {
        LocalDateTime localDateTime = LocalDateTime.now();

        log.info("Local DateTime, Now: {}", localDateTime);
        log.info("Month: {}, Day: {}, Weekday: {}, Seconds: {}\n", localDateTime.getMonthValue(),
                localDateTime.getDayOfMonth(), localDateTime.getDayOfWeek(), localDateTime.getSecond());

        log.info("1: {}", localDateTime.toLocalDate());
        log.info("2: {}", localDateTime.withYear(2010).withDayOfMonth(10));
        log.info("3: {}", LocalDate.of(2018, Month.DECEMBER, 16));
        log.info("4: {}", LocalTime.of(23, 59));
        log.info("5: {}", LocalTime.parse("23:59:59"));

        assertTrue(localDateTime.isAfter(LocalDateTime.MIN));
        assertTrue(localDateTime.isBefore(LocalDateTime.MAX));

        log.info("Month 100 days later: {}", localDateTime.plusDays(100).getMonthValue());
        log.info("Years to the Java supported MAX date: {}", localDateTime.until(LocalDateTime.MAX, ChronoUnit.YEARS));

    }

    @Test
    public void demoZonedDateTime() {
        log.info("ZDT shanghai: {}", ZonedDateTime.parse("2017-12-03T10:15:30+05:30[Asia/Shanghai]"));
        log.info("ZoneId: {}", ZoneId.of("Europe/Paris"));
        log.info("CurrentZone: {}", ZoneId.systemDefault());
    }

    @Test
    public void miscs() {
        
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();

        log.info("Local date, Now: {}", localDate);
        log.info("Local time, Now: {}", localTime);
        log.info("1 week later: {}", localDate.plus(1, ChronoUnit.WEEKS));
        log.info("1 year later: {}", localDate.plus(1, ChronoUnit.YEARS));
        log.info("1 decade later: {}", localDate.plus(1, ChronoUnit.DECADES));

        // Period: date based amount of time
        Period period = Period.between(localDate, localDate.plus(1, ChronoUnit.MONTHS));
        log.info("Period: {}", period);

        // Duration: time based amount of time
        Duration duration = Duration.between(localTime, localTime.plus(Duration.ofMinutes(6L)));
        log.info("Duration: " + duration);

        // Adjusters
        log.info("Next Monday : {}", localDate.with(TemporalAdjusters.next(DayOfWeek.MONDAY)));
        log.info("2nd Saturday of this month : {}",
                LocalDate.of(localDate.getYear(), localDate.getMonth(), 1)
                        .with(TemporalAdjusters.nextOrSame(DayOfWeek.SATURDAY))
                        .with(TemporalAdjusters.next(DayOfWeek.SATURDAY)));
    }

    
}
