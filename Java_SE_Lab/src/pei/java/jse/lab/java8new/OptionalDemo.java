package pei.java.jse.lab.java8new;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;
import java.util.function.Function;

import org.junit.jupiter.api.Test;

import static com.github.peiatgithub.java.utils.Constants.*;

/**
 * @author pei
 */
public class OptionalDemo {

    @Test
    public void basics() {

        Optional<String> strOpt1 = Optional.of(S2);
        assertThrows(NPE, () -> Optional.of(null));

        Optional<Object> nullOpt = Optional.ofNullable(null);
        Optional<Object> emptyOpt = Optional.empty();
        Optional<String> strOpt2 = strOpt1.filter(s -> s.contains("3")); // empty

        assertTrue(strOpt1.isPresent());
        strOpt1.ifPresent(theValue -> assertEquals(S2, theValue));

        assertFalse(strOpt2.isPresent());
        assertFalse(emptyOpt.isPresent());
        assertFalse(nullOpt.isPresent());

        assertEquals(S2, strOpt1.orElse(S1));
        assertEquals(S2, strOpt2.orElse(S2));
        assertEquals(S1, strOpt2.orElseGet(() -> S1));

        Function<String, String> f = ((Function<String, String>) s -> s.concat(" San"))
                .andThen(s -> s.concat(" Zhang"));

        Optional<String> strOpt1Mapped = strOpt1.map(f);
        assertEquals(S2.concat(" San").concat(" Zhang"), strOpt1Mapped.get());

        Optional<String> strOpt2Mapped = strOpt2.map(f);
        assertFalse(strOpt2Mapped.isPresent());
    }

}
