package pei.java.jse.lab.language;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.github.peiatgithub.java.utils.RunFlag;

/**
 * @author pei
 */
public class TheMainMethod {
    
    public static void main(String[] args) {
    	
        if(RunFlag.hasRun()) {
        	assertNotNull(args); 
        	assertThat(args).hasSize(3);
        }else {
            assertNotNull(args); 
        	assertThat(args).hasSize(0);
        }
        RunFlag.reset();
        
    }
    
    @Test
    public void callMainMethodExplicitly() throws Exception {
    	RunFlag.reset();
    	RunFlag.run();
        TheMainMethod.main(new String[] {"a1", "a2", "a3"});
    }

}
