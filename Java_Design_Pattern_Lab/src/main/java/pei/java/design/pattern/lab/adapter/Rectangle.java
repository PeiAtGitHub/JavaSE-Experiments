package pei.java.design.pattern.lab.adapter;

/**
 * 
 * @author pei
 *
 */
public class Rectangle {

    public void draw(int x, int y, int w, int h) {
        System.out.format("Rectangle at point (%s, %s) with width %s and height %s.%n", x, y, w, h);
    }
}
