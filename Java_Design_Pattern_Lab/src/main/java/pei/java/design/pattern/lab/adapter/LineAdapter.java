package pei.java.design.pattern.lab.adapter;

/**
 * 
 * Adapter of the Line class, they happen to behave exactly same
 * @author pei
 *
 */
public class LineAdapter implements Shape{

    private Line line = new Line();

    public void draw(int x1, int y1, int x2, int y2) {
        line.draw(x1, y1, x2, y2);
    }

}
