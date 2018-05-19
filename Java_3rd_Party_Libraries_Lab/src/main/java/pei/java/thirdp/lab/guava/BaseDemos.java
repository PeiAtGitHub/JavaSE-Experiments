package pei.java.thirdp.lab.guava;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.assertj.core.api.Assertions.*;

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
    	assertThat(Strings.isNullOrEmpty(null));
    	assertThat(Strings.isNullOrEmpty(EMPTY));
    	assertThat(Strings.isNullOrEmpty(SPACE)).isFalse();
        
        assertThat(Strings.nullToEmpty(null)).isEmpty();
        
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
        assertThatThrownBy(()->checkNotNull(null).toString()).isInstanceOf(NPE);
        checkState(person.getAge()>30);
        assertThatThrownBy(()->checkState(person.getAge()<30)).isInstanceOf(ISE);
    }
}
