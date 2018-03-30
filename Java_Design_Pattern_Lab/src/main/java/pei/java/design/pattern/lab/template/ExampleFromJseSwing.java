package pei.java.design.pattern.lab.template;


import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

/**
 * 
 * @author pei
 *
 */
public class ExampleFromJseSwing  extends JFrame {

    private static final long serialVersionUID = 1L;

    public static void main(String[] args) {
        final JFrame jf = new ExampleFromJseSwing();
        
        jf.setTitle("Hello Application");
        jf.setBounds(100, 100, 300, 300);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        jf.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    /*
     * paint() is one of the "steps" methods in template method update() of JFrame.
     * Here overrides this step method to make it display customized stuff. 
     */
    public void paint(Graphics g) {
        super.paint(g);
        g.drawString("TAKE IT EASY!", 100, 60);
    }
}