package pei.java.jse.lab.language;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

/**
 * 
 * @author pei
 *
 */
public class CloneDemo {

    @Test
    public void cloneMethod(){
    	
        // (new Nut()).clone(); // compile error, invisible method, Object.clone() is "protected"
    	
        Computer computer1 = new Computer();
        Computer computer2 = computer1.clone();
        
        assertNotSame(computer1, computer2);
        // the default clone() is a SHALLOW copy
        assertSame(computer1.cpu, computer2.cpu);
        assertSame(computer1.name, computer2.name);
        // primitives are independent
        computer1.memoryGB = 16;
        assertThat(computer1.memoryGB, is(16));
        assertThat(computer2.memoryGB, is(8));
    }
}

class Nut {}

class Computer implements Cloneable{
    public String name = new String("localhost");
    public int memoryGB = 8;
    public CPU cpu = new CPU();
    
    public Computer clone(){
        try {
            return (Computer)super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }
}

class CPU implements Cloneable{
    public CPU clone(){
        try {
            return (CPU)super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
 
