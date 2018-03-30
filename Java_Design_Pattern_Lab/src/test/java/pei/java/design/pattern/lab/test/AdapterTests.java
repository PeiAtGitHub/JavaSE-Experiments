package pei.java.design.pattern.lab.test;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import pei.java.design.pattern.lab.adapter.LineAdapter;
import pei.java.design.pattern.lab.adapter.RectangleAdapter;
import pei.java.design.pattern.lab.adapter.Shape;

/**
 * 
 * @author pei
 *
 */
public class AdapterTests {

    @Test
    public void testAdapters() {

        List<Shape> shapes = Arrays.asList(new LineAdapter(), new RectangleAdapter());

        int x1 = 10, y1 = 20;
        int x2 = 30, y2 = 60;

        shapes.forEach((shape)-> shape.draw(x1, y1, x2, y2));

    }
}
