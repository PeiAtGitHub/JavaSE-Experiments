package pei.java.design.pattern.lab.singleton;

import static org.junit.Assert.assertSame;

/**
 * lazy instantiation
 * @author pei
 *
 */
public class SingletonLazy {
    
    private static SingletonLazy instance = null;
    
    private static Object syncObj = new Object();

    private SingletonLazy() {
    }

    public static SingletonLazy getInstance() {
        synchronized (syncObj) {// thread-safe, otherwise more than 1 instance could be created.
            if (instance == null) {
                instance = new SingletonLazy();
            }    
            return instance;
        }
    }
    
    public static void main(String[] args) {
    	assertSame(getInstance(), getInstance());
	}


}
