package pei.java.jse.lab.language;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.junit.jupiter.api.Test;

/**
 * Somebody said reflection is not encouraged to use nowadays.
 * 
 * @author pei
 */
public class ReflectionTests {
    
    @Test
    public void intanceOfClass() {
    	
        LinkedHashMap<?, ?> aLinkedHashMap = new LinkedHashMap<>();
        assertThat(aLinkedHashMap).isInstanceOf(HashMap.class);
        assertThat(aLinkedHashMap).isInstanceOf(LinkedHashMap.class);
        assertEquals("java.util.LinkedHashMap", aLinkedHashMap.getClass().getName());
        assertEquals("java.util.HashMap", new HashMap<>().getClass().getName());

    }
    
    @Test
    public void  testReflections()throws Exception {
        
        Class<?> classObj = Class.forName("pei.java.jse.lab.language.ReflectionTests", 
                    false, Thread.currentThread().getContextClassLoader());
        
        assertFalse(classObj.isInterface());
        assertFalse(classObj.isPrimitive());
        assertFalse(classObj.isArray());
        
        assertEquals("pei.java.jse.lab.language.ReflectionTests", classObj.getName());
        assertEquals("java.lang.Object", classObj.getSuperclass().getName());
        assertThat(classObj.getFields()).hasSize(0);
        assertThat(classObj.getMethods()).hasSize(11);
        assertEquals("[public pei.java.jse.lab.language.ReflectionTests()]", Arrays.toString(classObj.getConstructors()));
        
        assertThat(classObj.getClassLoader().toString()).startsWith("sun.misc.Launcher$AppClassLoader@");
        assertThat(classObj.getClassLoader().getParent().toString()).startsWith("sun.misc.Launcher$ExtClassLoader@");

        ((ReflectionTests) classObj.newInstance()).intanceOfClass();
        
    }
}
