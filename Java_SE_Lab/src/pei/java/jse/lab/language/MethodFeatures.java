package pei.java.jse.lab.language;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;

import org.junit.Test;

import com.google.common.collect.Lists;

import pei.java.jse.lab.utils.Person;

/**
 * 
 * @author pei
 *
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
        assertThat(i, is(1));

        String s = "abc";
        manipulateString(s);
        assertThat(s, is("abc"));
        
        ArrayList<String> list = Lists.newArrayList("abc");
        manipulateList(list);
        assertThat(list).containsExactly("abc", "def");
        
        Person p = new Person("AAA", "AAA", 0);
        manipulateObj(p);
        assertThat(p.getFirstName(), is("BBB"));
    }
    

}

