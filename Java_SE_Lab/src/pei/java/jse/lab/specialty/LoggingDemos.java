package pei.java.jse.lab.specialty;

import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;

/**
 * @author pei
 */
@Slf4j
public class LoggingDemos {
    
    @Test
    public void loggerFormat() throws Exception {
        log.info("Hello, {} and {}.");
        log.info("Hello, {} and {}.", "Tom");
        log.info("Hello, {} and {}.", "Tom", "Jerry");
        log.info("Hello, {} and {}.", "Tom", "Jerry", "Some one");
        log.info("Hello, { } and {}.", "Tom", "Jerry", "Some one");
        log.info("Hello, \\{} and {}.", "Tom", "Jerry", "Some one");
    }

}
