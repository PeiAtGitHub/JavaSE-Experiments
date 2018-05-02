package pei.java.jse.lab.language;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * 
 * @author pei
 *
 */
public class InitializerBlocksDemo {
	
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
	
	static {
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