package pei.java.design.pattern.lab.decorator;

import lombok.extern.slf4j.Slf4j;

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

//
interface Window {
	public void draw(); 
}

@Slf4j
class SimpleWindow implements Window {
	
	public void draw() {
		log.info("A simple window to be decorated.");
	}
}

abstract class WindowDecorator implements Window {

	protected Window window; // the Window to be decorated

	public WindowDecorator(Window window) {
		this.window = window;
	}
	
	public abstract void draw();
}

@Slf4j
class VerticalScrollBarDecorator extends WindowDecorator{

	public VerticalScrollBarDecorator(Window window) {
		super(window);
	}

	public void draw() {
		this.window.draw();
		log.info("AND here's a Vertical Scroll Bar.");
	}
}

@Slf4j
class HorizontalScrollBarDecorator extends WindowDecorator{
	
	public HorizontalScrollBarDecorator(Window window) {
		super(window);
	}

	public void draw() {
		this.window.draw();
		log.info("AND here's a Horizontal Scroll Bar.");
	}
}

