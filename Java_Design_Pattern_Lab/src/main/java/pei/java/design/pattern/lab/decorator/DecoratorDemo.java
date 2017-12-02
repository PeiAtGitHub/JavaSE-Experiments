package pei.java.design.pattern.lab.decorator;


/**
 * 
 * @author pei
 * 
 */


public class DecoratorDemo {
	
	public static void main(String[] args) {
		(new HorizontalScrollBarDecorator (new VerticalScrollBarDecorator(new SimpleWindow())))
		.draw();
		//
		(new VerticalScrollBarDecorator(new HorizontalScrollBarDecorator(new SimpleWindow())))
		.draw();
		//
		(new VerticalScrollBarDecorator(new VerticalScrollBarDecorator(new SimpleWindow())))
		.draw();
		
	}
	
	
}

/*
 * 
 */
interface Window {
	public void draw(); 
}

class SimpleWindow implements Window {
	public void draw() {
		System.out.println("A simple window to be decorated.");
	}
}

/*
 * Below two decorators could extract an abstract class which
 * implements the interface and be inherited by the below two decorators.
 * But to make things simpler, I don't introduce one more layer of complexity 
 * in this demo.
 */
class VerticalScrollBarDecorator implements Window{
	
	protected Window window; // the Window to be decorated

	public VerticalScrollBarDecorator(Window window) {
		this.window = window;
	}

	public void draw() {
		this.window.draw();
		System.out.println("AND here's a Vertical Scroll Bar.");
	}
}

class HorizontalScrollBarDecorator implements Window {
	
	protected Window window; // the Window to be decorated

	public HorizontalScrollBarDecorator(Window window) {
		this.window = window;
	}

	public void draw() {
		this.window.draw();
		System.out.println("AND here's a Horizontal Scroll Bar.");
	}
}

