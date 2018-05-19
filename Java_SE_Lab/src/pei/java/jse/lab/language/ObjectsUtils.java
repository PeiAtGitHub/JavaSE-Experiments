package pei.java.jse.lab.language;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.assertj.core.api.Assertions.*;

import static com.github.peiatgithub.java.utils.Constants.*;
import java.util.Objects;

import org.junit.Test;

/**
 * 
 * @author Pei
 *
 */
public class ObjectsUtils {

    @Test
    public void testNullnessChecking(){
        // Null checking made one liner.
        final String STH_IS_NULL = "Sth is null";
		assertThatThrownBy(()->Objects.requireNonNull(null, STH_IS_NULL)).isInstanceOf(NPE).hasMessage(STH_IS_NULL);
    }

    @Test
    public void toStringDemo(){
    	assertThat(Objects.toString(STR), is(STR));
    	assertThat(Objects.toString(null), is(NULL_TEXT));
    }

}
