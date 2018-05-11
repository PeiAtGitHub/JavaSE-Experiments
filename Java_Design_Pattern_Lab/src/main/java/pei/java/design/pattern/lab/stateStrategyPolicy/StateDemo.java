package pei.java.design.pattern.lab.stateStrategyPolicy;

import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import static org.apache.commons.lang3.StringUtils.*;
import static com.github.peiatgithub.java.utils.Utils.*;


/**
 * 
 * @author pei
 *
 */
public class StateDemo {

    public static void main(String[] args) {
    	
        TextWriter wrt = new TextWriter();
        repeatRun(12, ()->wrt.write("Today is my good day."));
        
    }

}

/**
 * the Stateful object, the context object
 * @author pei
 *
 */
@Setter
class TextWriter {
    // both context object and states themselves can change the state
    private TextingState txtState = new lowerCaseState(); // initial state
    
    public void write(String txt) {
        txtState.write(this, txt);
    }
}
/**
 * the State interface
 */
interface TextingState {
    void write(TextWriter stateCtxt, String txt);
}

class lowerCaseState implements TextingState {
    public void write(TextWriter stateCtxt, String txt) {
        System.out.println(txt.toLowerCase());
        stateCtxt.setTxtState(new upperCaseState());
    }
}

class upperCaseState implements TextingState {
    public void write(TextWriter stateCtxt, String txt) {
        System.out.println(txt.toUpperCase());
        stateCtxt.setTxtState(new camelCaseState());
    }
}

class camelCaseState implements TextingState {
    public void write(TextWriter stateCtxt, String txt) {
        StringBuilder result = new StringBuilder();
        for(String s : txt.split(SPACE)) {
            result.append(StringUtils.capitalize(s));
        }
        System.out.println(result.toString());
        stateCtxt.setTxtState(new removeSpaceState());
    }
}

class removeSpaceState implements TextingState {
    public void write(TextWriter stateCtxt, String txt) {
        System.out.println(txt.replaceAll(SPACE, EMPTY));
        stateCtxt.setTxtState(new dashJoinedState());
    }
}

class dashJoinedState implements TextingState {
    public void write(TextWriter stateCtxt, String txt) {
        System.out.println(txt.replaceAll(SPACE, "-"));
        stateCtxt.setTxtState(new underScoreJoinedState());
    }
}

class underScoreJoinedState implements TextingState {
    public void write(TextWriter stateCtxt, String txt) {
        System.out.println(txt.replaceAll(SPACE, "_"));
        stateCtxt.setTxtState(new lowerCaseState());
    }
}
