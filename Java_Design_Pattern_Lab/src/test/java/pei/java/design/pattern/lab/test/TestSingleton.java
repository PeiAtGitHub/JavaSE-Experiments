package pei.java.design.pattern.lab.test;

import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

import pei.java.design.pattern.lab.singleton.SingletonEager;
import pei.java.design.pattern.lab.singleton.SingletonLazy;

/**
 * @author pei
 */
public class TestSingleton {
    
    @Test
    public void testSingletonLazy() {
        assertSame(SingletonLazy.getInstance(), SingletonLazy.getInstance());
    }

    @Test
    public void testSingletonEager() {
        assertSame(SingletonEager.getInstance(), SingletonEager.getInstance());
    }
}
