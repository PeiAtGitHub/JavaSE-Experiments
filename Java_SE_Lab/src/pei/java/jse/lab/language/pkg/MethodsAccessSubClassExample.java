package pei.java.jse.lab.language.pkg;


import org.junit.jupiter.api.Test;

import pei.java.jse.lab.language.pkg.subPkg.MethodsAccessExample;

/**
 * @author pei
 */
public class MethodsAccessSubClassExample extends MethodsAccessExample {

    @Test
    public void testMethodAccessibilityInSub() throws Exception {
        
        MethodsAccessExample maExample = new MethodsAccessExample();
        
        maExample.aPublicMethod();
//      maExample.aProtectdMethod(); // NO ACCESS !
        
        aPublicMethod();
        aProtectdMethod();

    }
    

}
