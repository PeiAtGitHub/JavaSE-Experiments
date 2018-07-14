package pei.java.jse.lab.language;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import com.google.common.collect.Lists;

import pei.java.jse.lab.utils.Person;

/**
 * @author pei
 */
public class MethodFeatures {
	
	static private void manipulatePrimitive(int i) {
		i = 2;
	}
	static private void manipulateString(String s) {
		s = "def";
	}
	static private void manipulateList(ArrayList<String> list) {
		list.add("def");
	}
	static private void manipulateObj(Person p) {
		p.setFirstName("BBB");
	}
    
    @Test
    public void testPassingArgs() throws Exception {
        int i = 1;
        manipulatePrimitive(i);
        assertEquals(1,  i);

        String s = "abc";
        manipulateString(s);
        assertEquals("abc", s);
        
        ArrayList<String> list = Lists.newArrayList("abc");
        manipulateList(list);
        assertThat(list).containsExactly("abc", "def");
        
        Person p = new Person("AAA", "AAA", 0);
        manipulateObj(p);
        assertEquals("BBB", p.getFirstName());
    }
    

	@Test
	public void assignmentAndPassInArgAtSameTime() throws Exception {

		boolean pass1 = false;
		int pass2;

		assertTrue(testPassInBl(pass1 = true));
		assertFalse(testPassInBl(pass1 = false));

		assertEquals(1, testPassInNum(pass2 = 1));
		assertEquals(100, testPassInNum(pass2 = 100));
		
	}


	private boolean testPassInBl(boolean a) {
		return a;
	}

	private int testPassInNum(int a) {
		return a;
	}

}

