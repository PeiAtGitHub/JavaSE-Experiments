package pei.java.design.pattern.lab.adapter;

/**
 *
 * @author pei
 *
 */
public class RectangleAdapter implements Shape{
    
    private Rectangle rectangle= new Rectangle();
    
    public void draw(int x1, int y1, int x2, int y2) {
        rectangle.draw(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x2 - x1), Math.abs(y2 - y1));
    }

}
