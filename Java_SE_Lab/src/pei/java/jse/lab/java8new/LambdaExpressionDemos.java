package pei.java.jse.lab.java8new;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * 
 * @author pei
 *
 */
public class LambdaExpressionDemos {

	public static void main(String[] args) {
		
		/*
		 *  Replace Runnable impl
		 */
		new Thread(() -> System.out.println("Current thread name: " + Thread.currentThread().getName())).start();

		/*
		 *  Replace Comparator impl
		 */
		List<Person> persons = Arrays.asList(new Person("San", "Zhang", 30), new Person("Si", "Li", 28),
				new Person("Wu", "Wang", 80));
		// 
		System.out.println("Sort by Last Name:");
		Collections.sort(persons, (p1, p2)->p1.getLn().compareTo(p2.getLn()));
		System.out.println(persons);
		// 
		System.out.println("Sort by Age:");
		Collections.sort(persons, (p1, p2)-> {
			if (p1.getAge() > p2.getAge()) {
				return 1;
			}else if (p1.getAge() < p2.getAge()) {
				return -1;
			}else {
				return 0;
			}
		});
		System.out.println(persons);

		/*
		 * Use functional interfaces 
		 */
		System.out.println(calculate(6, 6, (x, y) -> x+y));
		System.out.println(calculate(6, 6, (x, y) -> x*y));
		System.out.println(calculate(6, 6, (x, y) -> x/y));
		System.out.println("*************");
		//
		int[] someNums = {1,2,3,4,5};
		int key =10;
		process(someNums, key, (v, k)-> System.out.println(v*k));
		//
		System.out.println("Print all: ");
		performConditionally(persons, p->System.out.println(p), p->true); // print all
		System.out.println("Print younger than 30");
		performConditionally(persons, p->System.out.println(p.getAge()), p->p.getAge()<30); // print age of the person younger than 30 
	}
	
	static private void performConditionally(List<Person> persons, Consumer<Person> consumer, Predicate<Person> predicate ){
		for (Person p: persons) {
			if(predicate.test(p)) {
				consumer.accept(p);
			}
		}
		
	}
	
	static private long calculate(long x, long y, BiFunction<Long, Long, Long> biFunc) {
		return biFunc.apply(x, y);
	}
	
	
	static private void process(int[] nums, int key, BiConsumer<Integer, Integer> biConsumer) {
		for (int i : nums) {
			biConsumer.accept(i,  key);
		}
	}

}


/*
 * 
 */

class Person {
	String fn = "";
	String ln = "";
	int age = 0;

	public Person(String fn, String ln, int age) {
		super();
		this.fn = fn;
		this.ln = ln;
		this.age = age;
	}

	public String getFn() {
		return fn;
	}

	public void setFn(String fn) {
		this.fn = fn;
	}

	public String getLn() {
		return ln;
	}

	public void setLn(String ln) {
		this.ln = ln;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return String.format("[%s, %s, %d]", fn, ln, age);

	}


}
