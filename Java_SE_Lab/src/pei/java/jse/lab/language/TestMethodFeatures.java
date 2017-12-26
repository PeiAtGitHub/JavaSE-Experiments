package pei.java.jse.lab.language;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;

import org.junit.Test;

import pei.java.jse.lab.utils.Person;

/**
 * 
 * @author pei
 *
 */
public class TestMethodFeatures {
	
	@Test
	public void testPassingArgs() throws Exception {
		int i = 1;
		manipulatePrimitive(i);
		assertThat(i, is(1));

		String s = "abc";
		manipulateString(s);
		assertThat(s, is("abc"));
		
		ArrayList<String> list = new ArrayList<>();
		list.add("abc");
		manipulateList(list);
		assertThat(list.toString(), is("[abc, def]"));
		
		Person p = new Person("AAA", "AAA", 0);
		manipulateObj(p);
		assertThat(p.getFirstName(), is("BBB"));
	}
	
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
	public void testVarArgs() {
		assertEquals(0, sum());
		assertEquals(1, sum(1));
		assertEquals(3, sum(1,2));
		assertEquals(6, sum(1,2,3));
	}

	
	private static int sum(int... numbers) {
		int sum = 0;
		for (int num : numbers)
			sum = sum + num;
		return sum;
	}
}

