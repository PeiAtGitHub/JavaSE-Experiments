package pei.java.design.pattern.lab.singleton;

import static org.junit.jupiter.api.Assertions.assertSame;

/**
 * eager initialization
 * 
 * @author pei
 *
 */
public class SingletonEager {

    private static SingletonEager instance = new SingletonEager();// no stackOverflowError problem.

    private SingletonEager() {
    }

    public static SingletonEager getInstance() {
        return instance;
    }
    
    public static void main(String[] args) {
    	assertSame(getInstance(), getInstance());
	}

}
