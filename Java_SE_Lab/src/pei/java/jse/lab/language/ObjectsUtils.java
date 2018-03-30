package pei.java.jse.lab.language;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

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
        assertTrue(e instanceof NullPointerException);
        assertTrue(e.getMessage().equals("Sth is null!"));
                        
    }

}
