package pei.java.jse.lab.language;

import static com.github.peiatgithub.java.utils.Constants.*;

import java.util.Random;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * @author pei
 */
public class ObjectMethodsDemo {
	
	@Test
	public void equalsOpndOrder() throws Exception {
		
		/*
		 * 
		 * It is more readable to write: 
		 *   if (theUnderCheck.equals(theExpected)){...
		 * than:
		 *   if (theExpected.equals(theUnderCheck)){...
		 * 
		 * but there are benefit using the less readable one when 'theUnderCheck' is nullable
		 *
		 * when none is nullable, order does not matter, should use the more readable one.
		 * when one of them is nullable. put the nullable one as the arg of equals() to secure no Exception
		 * 
		 */
		boolean isNull = new Random().nextBoolean();
		final Object aNullableObj = isNull ? null : NULL_TEXT; 
		
		if(isNull) {
			assertFalse(NULL_TEXT.equals(aNullableObj));
			assertThrows(NPE, ()->aNullableObj.equals(NULL_TEXT));
		}else {
			assertTrue(NULL_TEXT.equals(aNullableObj));
			assertTrue(aNullableObj.equals(NULL_TEXT));
		}
	}
	
	
	@Test
	@Disabled
	public void hashCodeMethod() throws Exception {
		//TODO
	}
	
	@Test
    public void cloneMethod() {

        // (new Nut()).clone(); // compile error, invisible method, Object.clone() is
        // "protected"

        Computer computer1 = new Computer();
        Computer computer2 = computer1.clone();

        assertNotSame(computer1, computer2);
        // the default clone() is a SHALLOW copy
        assertSame(computer1.cpu, computer2.cpu);
        assertSame(computer1.name, computer2.name);
        // primitives are independent
        computer1.memoryGB = 16;
        assertEquals(16, computer1.memoryGB);
        assertEquals(8, computer2.memoryGB);
    }

}


class Nut {
}

class Computer implements Cloneable {
    public String name = new String("localhost");
    public int memoryGB = 8;
    public CPU cpu = new CPU();

    public Computer clone() {
        try {
            return (Computer) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }
}

class CPU implements Cloneable {
    public CPU clone() {
        try {
            return (CPU) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }
}

