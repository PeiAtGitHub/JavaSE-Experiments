package pei.java.design.pattern.lab.mediator;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import lombok.AllArgsConstructor;
import lombok.Setter;

/**
 * 
 * @author pei
 *
 */
public class MediatorDemo {

    public static void main(String[] args) {

        BtnActionListener bal = new BtnActionListener();
        
        BtnView btnView = new BtnView(bal);
        BtnBook btnBook = new BtnBook(bal);
        BtnSearch btnSearch = new BtnSearch(bal);
        TextDisplay textDisplay = new TextDisplay();
        
        Mediator med = new Mediator(textDisplay, btnView, btnSearch, btnBook);
        
        btnView.setMediator(med);
        btnSearch.setMediator(med);
        btnBook.setMediator(med);
        
        JPanel p = new JPanel();
		p.add(btnView);
		p.add(btnBook);
		p.add(btnSearch);
        //
        JFrame jfr = new JFrame();
		jfr.getContentPane().add(textDisplay, "North");
        jfr.getContentPane().add(p, "South");
        jfr.setBounds(500, 300, 300, 300);
        jfr.setVisible(true);
    }
}

 
@AllArgsConstructor
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
    
    public void execute();
}

/*
 * Colleagues impls
 */
class TextDisplay extends JLabel {

    TextDisplay() {
        super("Just start...");
        setFont(new Font("Arial", Font.BOLD, 26));
    }
    
}

class BtnView extends JButton implements Command {

	@Setter
    Mediator mediator;

    BtnView(ActionListener al) {
        super("View");
        addActionListener(al);
    }
 
    public void execute() {
        mediator.view();
    }
 
}
 
class BtnSearch extends JButton implements Command {
 
    @Setter
    Mediator mediator;

    BtnSearch(ActionListener al) {
        super("Search");
        addActionListener(al);
    }
 
    public void execute() {
        mediator.search();
    }
 
}
 
class BtnBook extends JButton implements Command {
 
    @Setter
    Mediator mediator;

    BtnBook(ActionListener al) {
        super("Book");
        addActionListener(al);
    }
 
    public void execute() {
        mediator.book();
    }
 
}


class BtnActionListener implements ActionListener{

    public void actionPerformed(ActionEvent ae) {
        ((Command) ae.getSource()).execute();
    }
 
}
 
