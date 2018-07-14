package pei.java.jse.lab.language;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static com.github.peiatgithub.java.utils.Constants.*;
import java.util.Objects;

import org.junit.jupiter.api.Test;

/**
 * @author Pei
 */
public class ObjectsUtils {

    @Test
    public void testNullnessChecking() {
        // Null checking made one liner.
        final String STH_IS_NULL = "Sth is null";
        assertThatThrownBy(() -> Objects.requireNonNull(null, STH_IS_NULL)).isInstanceOf(NPE).hasMessage(STH_IS_NULL);
    }

    @Test
    public void toStringDemo() {
        assertEquals(STR, Objects.toString(STR));
        assertEquals(NULL_TEXT, Objects.toString(null));
    }

}
