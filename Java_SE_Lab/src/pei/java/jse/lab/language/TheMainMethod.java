package pei.java.jse.lab.language;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.Test;

import com.github.peiatgithub.java.utils.RunFlag;

/**
 * 
 * @author pei
 *
 */
public class TheMainMethod {
    
    public static void main(String[] args) {
    	
        if(RunFlag.hasRun()) {
        	assertThat(args).isNotNull(); 
        	assertThat(args).hasSize(3);
        }else {
        	assertThat(args).isNotNull(); 
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
