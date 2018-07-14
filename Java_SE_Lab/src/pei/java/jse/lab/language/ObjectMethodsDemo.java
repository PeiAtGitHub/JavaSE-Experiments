package pei.java.jse.lab.language;

import static com.github.peiatgithub.java.utils.Constants.*;

import java.util.Random;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
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

}
