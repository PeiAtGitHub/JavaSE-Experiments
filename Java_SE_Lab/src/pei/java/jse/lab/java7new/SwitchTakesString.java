package pei.java.jse.lab.java7new;


import static com.github.peiatgithub.java.utils.Constants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;


/**
 * @author Pei
 */
public class SwitchTakesString {

    @Test
    public void switchTakesString() {
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

        assertEquals(FIRST_NAME, msg);
    }
    
}
