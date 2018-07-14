package pei.java.jse.lab.java7new;

import static org.assertj.core.api.Assertions.*;
import java.util.Random;
import org.junit.jupiter.api.Test;

/**
 * @author pei
 */
public class MultiCatch {

    @Test
    public void testMultiCatch() {

        Random r = new Random();

        for (int i = 0; i < 5; i++) {
            try {
                if (r.nextBoolean()) {
                    throw new ExceptionA();
                } else {
                    throw new ExceptionB();
                }
            } catch (ExceptionA | ExceptionB e) {
                assertThat(e).isInstanceOfAny(ExceptionA.class, ExceptionB.class);
            }
        }
    }

}

class ExceptionA extends Exception {
}

class ExceptionB extends Exception {
}
