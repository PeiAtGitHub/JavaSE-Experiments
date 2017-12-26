package pei.java.thirdp.lab.guava;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

import pei.java.thirdp.lab.utils.Person;
import pei.java.thirdp.lab.utils.Utils;

/**
 * 
 * @author pei
 *
 */
public class BaseDemos {

	
	@Test
	public void testStrings() throws Exception {
		assertTrue(Strings.isNullOrEmpty(null));
		assertTrue(Strings.isNullOrEmpty(""));
		assertFalse(Strings.isNullOrEmpty(" "));
		
		assertThat(Strings.nullToEmpty(null), is(""));
		
		assertThat(Strings.padEnd("HELLO", 8, '$'), is("HELLO$$$"));
		assertThat(Strings.repeat("HELLO", 3), is("HELLOHELLOHELLO"));
		
		
	}
	
	@Test
	public void testMiscs() throws Exception{
		assertThat(MoreObjects.firstNonNull(null, "default_value"), is("default_value"));
	}
	
	@Test
	public void testPreconditions() throws Exception {
		Person zhang = new Person("Zhang", "Male", 50);
		
		System.out.println(checkNotNull(zhang).toString());
		
		checkState(zhang.getAge()>30);
		assertThat(Utils.catchException(()->Preconditions.checkState(((Person) zhang).getAge()<30)),
				instanceOf(IllegalStateException.class));
	}
}
