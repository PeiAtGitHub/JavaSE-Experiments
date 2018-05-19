package pei.java.jse.lab.java7new;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import static com.github.peiatgithub.java.utils.Constants.*;


import org.junit.Test;

/**
 * @author Pei
 */
public class SwitchTakesString {


    @Test
    public void testSwitchTakesString() {
        /*
         * Since Java 7 the good old 'switch' takes a string
         */
        String name = FIRST_NAME;
        String msg = EMPTY;
        
        switch (name) {
        case FIRST_NAME:
            msg = FIRST_NAME;
            break;
        default:
            break;
        }

        assertThat(msg, is(FIRST_NAME));
    }
    
}
