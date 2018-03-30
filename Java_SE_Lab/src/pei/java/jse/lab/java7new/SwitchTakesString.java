package pei.java.jse.lab.java7new;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static pei.java.jse.lab.utils.Utils.*;

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
        String name = TOM;
        String msg = MOUSE;
        
        switch (name) {
        case TOM:
            msg = CAT;
            break;
        default:
            break;
        }

        assertThat(msg, is(CAT));
    }
    
}
