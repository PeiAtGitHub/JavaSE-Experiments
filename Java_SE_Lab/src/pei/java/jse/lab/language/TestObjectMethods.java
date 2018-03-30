package pei.java.jse.lab.language;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;

import org.junit.Test;

/**
 * 
 * @author pei
 *
 */
public class TestObjectMethods {

    @Test
    public void cloneMethod(){
        // (new Nut()).clone(); // compile error, invisible method, Object.clone is "protected"
        Computer computer1 = new Computer();
        Computer computer2 = computer1.clone();
        
        assertNotSame(computer1, computer2);
        // the default clone() is a SHALLOW copy
        assertSame(computer1.cpu, computer2.cpu);
        assertSame(computer1.name, computer2.name);
        // primitives are independent
        computer1.memoryGB = 16;
        assertEquals(16, computer1.memoryGB);
        assertEquals(8, computer2.memoryGB);
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
 
