package pei.java.jse.lab.java8new;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.assertj.core.api.Assertions.*;

import java.util.Optional;
import java.util.function.Function;

import org.junit.Test;

import static com.github.peiatgithub.java.utils.Constants.*;


/**
 * 
 * @author pei
 *
 */
public class OptionalDemo {
    
    final static String str1 = "abcdefg";
    final static String str2 = "Good day!";
    
    @Test
    public void basics() {
        
        Optional<String> strOpt1 = Optional.of(str2);
        assertThatThrownBy(()->Optional.of(null)).isInstanceOf(NPE);
        
        Optional<Object> nullOpt = Optional.ofNullable(null);
        Optional<Object> emptyOpt = Optional.empty();
        Optional<String> strOpt2 = strOpt1.filter(s->s.contains("morning")); // empty 
        
        assertThat(strOpt1.isPresent());
        assertThat(strOpt2.isPresent()).isFalse();
        assertThat(emptyOpt.isPresent()).isFalse();
        assertThat(nullOpt.isPresent()).isFalse();
        
        strOpt1.ifPresent(str -> System.out.println("Optional 1 present: " + str));
        
        assertThat(strOpt1.orElse(str1), is(str2));
        assertThat(strOpt2.orElse(str2), is(str2));
        assertThat(strOpt2.orElseGet(()->str1), is(str1));
        
        Function<String, String> f = ((Function<String, String>) s->s.concat(" San")).andThen(s->s.concat(" Zhang"));
        
        Optional<String> strOpt1Mapped = strOpt1.map(f);
        assertThat(strOpt1Mapped.get(), is(str2.concat( " San").concat(" Zhang")));

        Optional<String> strOpt2Mapped = strOpt2.map(f);
        assertThat(strOpt2Mapped.isPresent()).isFalse();
    }

}
