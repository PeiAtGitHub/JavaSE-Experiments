package pei.java.jse.lab.language;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import static pei.java.jse.lab.utils.Utils.*;

/**
 * 
 * @author pei
 *
 */
public class InitializationDemo {
    
    @Test
	public void stackOverflow() {
    	assertThat(catchThrowable(()->new InfiniteInitRecurseDemo()), instanceOf(StackOverflowError.class));
	}
    
	@Test
	public void initBlocksDemo() throws Exception {

		System.out.println("==================");
		new IBDemo();

		System.out.println("==================");
		new IBDemo(1);

		System.out.println("==================");
		new IBDemo(1, 1);

	}
	
}

class IBDemo {
	
	static 
	{
		System.out.println("Static Initializer Block, runs only once for class loading.");
	}
	
	{
		System.out.println("Instance Initializer Block, runs once for each instance initialization.");
	}
	
	/*
	 * constructors
	 */
	public IBDemo() {
		System.out.println("0-Args Constructor");
	}
	public IBDemo(int i) {
		System.out.println("1-Args Constructor");
	}
	public IBDemo(int i, int j) {
		System.out.println("2-Args Constructor");
	}
	
}

class InfiniteInitRecurseDemo {

	private InfiniteInitRecurseDemo iird = new InfiniteInitRecurseDemo(); 
	
	public InfiniteInitRecurseDemo() {
		
		throw new RuntimeException("Never reach here."
				+ "the member initialization 'recurses too deeply' which causes java.lang.StackOverflowError");
		
	}
	
}

