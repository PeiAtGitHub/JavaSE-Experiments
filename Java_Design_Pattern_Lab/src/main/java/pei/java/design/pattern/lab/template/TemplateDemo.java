package pei.java.design.pattern.lab.template;

/**
 * 
 * @author pei
 *
 */
public class TemplateDemo {

	public static void main(String[] args) {
		new AutomotiveRobot().go();
		new CookieRobot().go();
	}
}

abstract class RobotTemplate {
	// the template method
	public final void go() {
		start();
		//
		getParts();
		work();
		test();
		//
		stop();
	}

	public void start() {
		System.out.println("Robot Starting...");
	}

	public abstract void getParts();
	public abstract void work() ;
	public abstract void test(); 

	public void stop() {
		System.out.println("Robot Stopping...");
	}
}

class AutomotiveRobot extends RobotTemplate {
	
	public void getParts() {
		System.out.println("Getting automobile parts...");
	}
	public void work() {
		System.out.println("Puting all parts together...");
	}
	public void test() {
		System.out.println("Testing the car...");
	}
	
}

class CookieRobot extends RobotTemplate {
	
	public void getParts() {
		System.out.println("Getting flour and sugar...");
	}
	public void work() {
		System.out.println("Baking a cookie...");
	}
	public void test() {
		System.out.println("Crunching a cookie...");
	}
}

