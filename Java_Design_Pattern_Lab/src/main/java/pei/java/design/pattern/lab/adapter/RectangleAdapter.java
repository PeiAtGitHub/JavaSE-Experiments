package pei.java.design.pattern.lab.adapter;

/**
 * 
 * Adapter, is a wrapper
 * it translates one interface for a class into another interface that client needs.
 * The adapter translates calls to its interface into calls to the original interface of 
 * the wrapped(adapted) class, the adapter could also transform data into appropriate forms. 
 * 
 * @author pei
 *
 */
public class RectangleAdapter implements Shape{
	
	private Rectangle rectangle= new Rectangle();
	
	public void draw(int x1, int y1, int x2, int y2) {
		rectangle.draw(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x2 - x1),
				Math.abs(y2 - y1));
	}

}
