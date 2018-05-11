package pei.java.design.pattern.lab.chainOfResponsibility;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import static com.github.peiatgithub.java.utils.Utils.*;

/**
 * the request ENDs where is handled
 * 
 * @author pei
 *
 */
public class ChainOfResponsibilityDemo1 {
    
    public static void main(String[] args) {
        FrontEnd frontEnd = new FrontEnd(new IntermediateLayer(new EndOfChain()));
        frontEnd.getHelp(1);
        frontEnd.getHelp(2);
        frontEnd.getHelp(3);
    }
    
}


interface HelpInterface {
    public void getHelp(int num);
}

/*
 *  below are 3 chainable classes
 */
@AllArgsConstructor
class FrontEnd implements HelpInterface {

    HelpInterface successor;

    public void getHelp(int num) {
    	ifElse(num == 1, ()->System.out.println("This is the front end."), ()->successor.getHelp(num));
    }
}
@AllArgsConstructor
class IntermediateLayer implements HelpInterface {
    
    HelpInterface successor;

    public void getHelp(int num) {
    	ifElse(num == 2, ()->System.out.println("This is the intermediate layer."), ()->successor.getHelp(num));
    }
}

@NoArgsConstructor
class EndOfChain implements HelpInterface {
    public void getHelp(int num) {
        System.out.println("This is the end of the chain, no successor.");
    }
}

