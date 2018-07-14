package pei.java.jse.lab.java8new;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

/**
 * @author pei
 */
@Slf4j
public class DateAndTimeDemos {

    @Test
    public void testLocalDateTime() {
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

        // log.info(localNow.toEpochSecond(ZoneOffset.UTC.));
    }

    @Test
    public void testZonedDateTime() {
        log.info("ZDT shanghai: {}", ZonedDateTime.parse("2017-12-03T10:15:30+05:30[Asia/Shanghai]"));
        log.info("ZoneId: {}", ZoneId.of("Europe/Paris"));
        log.info("CurrentZone: {}", ZoneId.systemDefault());
    }

    @Test
    public void miscs() {
        LocalDate localDate = LocalDate.now();

        log.info("Local date, Now: {}", localDate);
        log.info("1 week later: {}", localDate.plus(1, ChronoUnit.WEEKS));
        log.info("1 year later: {}", localDate.plus(1, ChronoUnit.YEARS));
        log.info("1 decade later: {}", localDate.plus(1, ChronoUnit.DECADES));

        // period: date based amount of time
        Period period = Period.between(localDate, localDate.plus(1, ChronoUnit.MONTHS));
        log.info("Period: {}", period);

        // duration: time based amount of time
        // Duration duration = Duration.between(localDate,
        // localDate.plus(Duration.ofMinutes(3l)));
        // log.info("Duration: " + duration);

        // adjusters
        log.info("Next Monday : {}", localDate.with(TemporalAdjusters.next(DayOfWeek.MONDAY)));
        log.info("2nd Saturday of this month : {}",
                LocalDate.of(localDate.getYear(), localDate.getMonth(), 1)
                        .with(TemporalAdjusters.nextOrSame(DayOfWeek.SATURDAY))
                        .with(TemporalAdjusters.next(DayOfWeek.SATURDAY)));
    }

}
