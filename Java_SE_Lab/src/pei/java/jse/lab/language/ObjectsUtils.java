package pei.java.jse.lab.language;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static pei.java.jse.lab.utils.Utils.*;
import java.util.Objects;

import org.junit.Test;

import pei.java.jse.lab.utils.Utils;

/**
 * 
 * @author Pei
 *
 */
public class ObjectsUtils {

    @Test
    public void testNullnessChecking(){
        // Null checking made one liner.
        Exception e = Utils.catchException(()->Objects.requireNonNull(null, "Sth is null!"));
        assertThat(e, instanceOf(NullPointerException.class));
        assertThat(e.getMessage(), is("Sth is null!"));
    }

    @Test
    public void toStringDemo(){
    	assertThat(Objects.toString(STR), is(STR));
    	assertThat(Objects.toString(null), is("null"));
    }

}
