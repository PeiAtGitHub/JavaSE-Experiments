package pei.java.jse.lab.specialty;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.assertj.core.api.Assertions.*;

import java.util.UUID;

import org.junit.Test;

/**
 * 
 * @author pei
 *
 */
public class UUIDUtils {
    
    @Test
    public void testUUID() {
        UUID uuid4 = UUID.randomUUID();
        assertThat(uuid4.version(), is(4));
        assertThat(uuid4.toString().matches("\\w{8}-\\w{4}-\\w{4}-\\w{4}-\\w{12}")); 
    }

}
