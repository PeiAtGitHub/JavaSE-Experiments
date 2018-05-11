package pei.java.thirdp.lab.guava;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static com.github.peiatgithub.java.utils.Utils.*;
import static com.github.peiatgithub.java.utils.Constants.*;

import org.junit.Test;

import com.google.common.base.MoreObjects;
import com.google.common.base.Strings;

import pei.java.thirdp.lab.utils.Person;

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
        assertThat(MoreObjects.firstNonNull(null, DEFAULT_STR), is(DEFAULT_STR));
    }
    
    @Test
    public void testPreconditions() throws Exception {
        Person person = new Person(FIRST_NAME, LAST_NAME, 50, Person.Gender.MALE);
        
        System.out.println(checkNotNull(person).toString());
        assertThat(catchThrowable(()->checkNotNull(null).toString()), instanceOf(NullPointerException.class));
        
        assertThat(catchThrowable(()->checkState(person.getAge()>30)), nullValue());
        assertThat(catchThrowable(()->checkState(person.getAge()<30)), instanceOf(IllegalStateException.class));
    }
}
