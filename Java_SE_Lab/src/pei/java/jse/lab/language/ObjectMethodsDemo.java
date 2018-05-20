package pei.java.jse.lab.language;

import static com.github.peiatgithub.java.utils.Constants.*;

import java.util.Random;

import static org.assertj.core.api.Assertions.*;

import org.junit.Assert;
import org.junit.Test;

/**
 * 
 * @author pei
 *
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
			assertThat(NULL_TEXT.equals(aNullableObj)).isFalse();
			assertThatThrownBy(()->aNullableObj.equals(NULL_TEXT)).isInstanceOf(NPE);
		}else {
			assertThat(NULL_TEXT.equals(aNullableObj));
			assertThat(aNullableObj.equals(NULL_TEXT));
		}
		
	}
	
	
	@Test
	public void hashCodeMethod() throws Exception {
		//TODO
		Assert.fail("This test has not been implemented.");
		
	}

}
