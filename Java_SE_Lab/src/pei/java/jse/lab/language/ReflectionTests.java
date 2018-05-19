package pei.java.jse.lab.language;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;
import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.junit.Test;

/**
 * 
 * Somebody said reflection is not encouraged to use nowadays.
 * 
 * @author pei
 *
 */
public class ReflectionTests {
    
    @Test
    public void intanceOfClass() {
    	
        LinkedHashMap<?, ?> aLinkedHashMap = new LinkedHashMap<>();
        assertThat(aLinkedHashMap).isInstanceOf(HashMap.class);
        assertThat(aLinkedHashMap).isInstanceOf(LinkedHashMap.class);
        assertThat(aLinkedHashMap.getClass().getName(), is("java.util.LinkedHashMap"));

        assertThat(new HashMap<>().getClass().getName(), is("java.util.HashMap"));

    }
    
    @Test
    public void  testReflections()throws Exception {
        
        Class<?> classObj = Class.forName("pei.java.jse.lab.language.ReflectionTests", 
                    false, Thread.currentThread().getContextClassLoader());
        
        assertThat(classObj.isInterface()).isFalse();
        assertThat(classObj.isPrimitive()).isFalse();
        assertThat(classObj.isArray()).isFalse();
        
        assertThat(classObj.getName(), is("pei.java.jse.lab.language.ReflectionTests"));
        assertThat(classObj.getSuperclass().getName(), is("java.lang.Object"));
        assertThat(classObj.getFields()).hasSize(0);
        assertThat(classObj.getMethods()).hasSize(11);
        assertThat(Arrays.toString(classObj.getConstructors())
        		, is("[public pei.java.jse.lab.language.ReflectionTests()]"));
        
        assertThat(classObj.getClassLoader().toString()).startsWith("sun.misc.Launcher$AppClassLoader@");
        assertThat(classObj.getClassLoader().getParent().toString()).startsWith("sun.misc.Launcher$ExtClassLoader@");

        ((ReflectionTests) classObj.newInstance()).intanceOfClass();
        
    }
}
