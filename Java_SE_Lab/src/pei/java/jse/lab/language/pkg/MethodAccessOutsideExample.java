package pei.java.jse.lab.language.pkg;

import org.junit.Test;

import pei.java.jse.lab.language.pkg.subPkg.MethodsAccessExample;


public class MethodAccessOutsideExample {

    @Test
    public void testMethodAccessibility() throws Exception {

        MethodsAccessExample maExample = new MethodsAccessExample();
        MethodsAccessSubClassExample maSubClassExample = new MethodsAccessSubClassExample();

        maExample.aPublicMethod();

        maSubClassExample.aPublicMethod();

    }

}
