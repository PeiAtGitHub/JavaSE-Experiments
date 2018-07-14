package pei.java.jse.lab.language.pkg.subPkg;

import org.junit.jupiter.api.Test;

/**
 * @author pei
 */
public class MethodsAccessExample {

    /*
     * Below methods are listed in the order of high to low accessibility
     */
    
    /**
     * 1. Accessible from anywhwere.
     */
    public void aPublicMethod() {
    }
    
    
    /**
     * 2. Accessible from same package and sub classes
     */
    protected void aProtectdMethod() {
    }
    
    /**
     * 3. Accessible only from same package. 
     *   (A method without modifier (the default case) has package-access)
     */
    void aPackageAccessMethod() {
    }

    
    /**
     * 4. Accessible only from class
     */
    private void aPrivateMethod() {
    }
    

    @Test
    public void methodAccessibility() {
        aPublicMethod();
        aProtectdMethod();
        aPackageAccessMethod();
        aPrivateMethod();
    }
}
