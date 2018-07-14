package pei.java.jse.lab.language.pkg.subPkg;

import org.junit.jupiter.api.Test;

import pei.java.jse.lab.language.pkg.MethodsAccessSubClassExample;

/**
 * @author pei
 */
public class MethodAccessSamePackageExample {
    
    @Test
    public void testMethodAccessibility() throws Exception {
        
        MethodsAccessExample maExample = new MethodsAccessExample();
        MethodsAccessSubClassExample maSubClassExample = new MethodsAccessSubClassExample();
        
        maExample.aPublicMethod();
        maExample.aProtectdMethod();
        maExample.aPackageAccessMethod();
        
        maSubClassExample.aPublicMethod();
        maSubClassExample.aProtectdMethod();        
    }
    

}
