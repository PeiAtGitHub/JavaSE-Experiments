package pei.java.thirdp.lab.junit;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * @author pei
 */
public class JUnitDemo {
    
    
    @Test
    void aboutAssertEquals() throws Exception {
        assertEquals(1, 1L);
        assertEquals(1L, 1.0D);
        assertEquals(Integer.valueOf(1), new Integer(1)); 
//        assertEquals(1, new Integer(1)); // compile error
    }

}
