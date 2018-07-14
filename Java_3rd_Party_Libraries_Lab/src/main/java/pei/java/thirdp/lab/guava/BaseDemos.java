package pei.java.thirdp.lab.guava;

import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;
import static org.assertj.core.api.Assertions.*;
import static com.github.peiatgithub.java.utils.Constants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.google.common.base.MoreObjects;
import com.google.common.base.Strings;

import pei.java.thirdp.lab.utils.Person;

/**
 * @author pei
 */
public class BaseDemos {

    @Test
    public void testStrings() throws Exception {
    	assertThat(Strings.isNullOrEmpty(null));
    	assertThat(Strings.isNullOrEmpty(EMPTY));
    	assertThat(Strings.isNullOrEmpty(SPACE)).isFalse();
        
        assertEquals(EMPTY, Strings.nullToEmpty(null));
        
        assertEquals("HELLO$$$", Strings.padEnd("HELLO", 8, '$'));
        assertEquals("HELLOHELLOHELLO", Strings.repeat("HELLO", 3));
    }
    
    @Test
    public void testMiscs() throws Exception{
        assertEquals(DEFAULT_STR, MoreObjects.firstNonNull(null, DEFAULT_STR));
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
