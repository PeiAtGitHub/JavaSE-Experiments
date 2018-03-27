package pei.java.design.pattern.lab.mediator;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import lombok.Setter;

/**
 * 
 * @author pei
 *
 */
public class MediatorDemo {

	public static void main(String[] args) {

		JPanel p = new JPanel();
    	Mediator med = new Mediator();
    	BtnActionListener bal = new BtnActionListener();
    	p.add(new BtnView(bal, med));
    	p.add(new BtnBook(bal, med));
    	p.add(new BtnSearch(bal, med));
    	//
    	JFrame jfr = new JFrame();
    	jfr.getContentPane().add(new TextDisplay(med), "North");
    	jfr.getContentPane().add(p, "South");
    	jfr.setBounds(500, 300, 300, 300);
    	jfr.setVisible(true);
    }
}

 
@Setter
class Mediator {
 
	TextDisplay textDisplay;

	BtnView btnView;
    BtnSearch btnSearch;
    BtnBook btnBook;
 
    public void book() {
        btnBook.setEnabled(false);
        btnView.setEnabled(true);
        btnSearch.setEnabled(true);
        textDisplay.setText("booking...");
    }
 
    public void view() {
        btnView.setEnabled(false);
        btnSearch.setEnabled(true);
        btnBook.setEnabled(true);
        textDisplay.setText("viewing...");
    }
 
    public void search() {
        btnSearch.setEnabled(false);
        btnView.setEnabled(true);
        btnBook.setEnabled(true);
        textDisplay.setText("searching...");
    }
}
 
/*
 * Colleague interface
 */
interface Command {
    void execute();
}

/*
 * Colleague impl
 */
class TextDisplay extends JLabel {
	
	Mediator med;
	
	TextDisplay(Mediator m) {
		super("Just start...");
		med = m;
		med.setTextDisplay(this);
		setFont(new Font("Arial", Font.BOLD, 26));
	}
	
}

class BtnView extends JButton implements Command {
 
    Mediator med;
 
    BtnView(ActionListener al, Mediator m) {
        super("View");
        addActionListener(al);
        med = m;
        med.setBtnView(this);
    }
 
    public void execute() {
        med.view();
    }
 
}
 
class BtnSearch extends JButton implements Command {
 
    Mediator med;
 
    BtnSearch(ActionListener al, Mediator m) {
        super("Search");
        addActionListener(al);
        med = m;
        med.setBtnSearch(this);
    }
 
    public void execute() {
        med.search();
    }
 
}
 
class BtnBook extends JButton implements Command {
 
    Mediator med;
 
    BtnBook(ActionListener al, Mediator m) {
        super("Book");
        addActionListener(al);
        med = m;
        med.setBtnBook(this);
    }
 
    public void execute() {
        med.book();
    }
 
}


class BtnActionListener implements ActionListener{

    public void actionPerformed(ActionEvent ae) {
        ((Command) ae.getSource()).execute();
    }
 
}
 
