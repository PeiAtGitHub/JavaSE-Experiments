package pei.java.design.pattern.lab.adapter;

/**
 * 
 * @author pei
 *
 */
public class Line {

	public void draw(int x1, int y1, int x2, int y2) {
		System.out.format("line from (%s, %s) to (%s, %s).%n", x1, y1, x2, y2);
	}

}
