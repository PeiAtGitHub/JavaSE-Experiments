package pei.java.jse.lab.language;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

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
		assertTrue(i==1);

		String s = "abc";
		manipulateString(s);
		assertTrue(s.equals("abc"));
		
		ArrayList<String> list = new ArrayList<>();
		list.add("abc");
		manipulateList(list);
		assertTrue(list.toString().equals("[abc, def]"));
		
		Person p = new Person("AAA");
		manipulateObj(p);
		assertTrue(p.getName().equals("BBB"));
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
		p.setName("BBB");
	}
	


	@Test
	public void infiniteArgs() {
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

//
class Person{
	String name="";

	public Person(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
