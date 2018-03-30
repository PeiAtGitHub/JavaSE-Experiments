package pei.java.jse.lab.java7new;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.Random;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author pei
 *
 */
@Slf4j
public class MultiCatch {
    
    @Test
    public void testMultiCatch(){
        
        Random r = new Random();
        
        for (int i = 0; i < 5; i++) {
            try {
                if(r.nextBoolean()) {
                    log.info("About to throw Exception A");
                    throw new ExceptionA();
                }else {
                    log.info("About to throw Exception B");
                    throw new ExceptionB();
                }
            } catch (ExceptionA | ExceptionB e) { 
                assertThat(e, anyOf(instanceOf(ExceptionA.class), instanceOf(ExceptionB.class)));
            }
        }
    }

}

class ExceptionA extends Exception{}
class ExceptionB extends Exception{}
