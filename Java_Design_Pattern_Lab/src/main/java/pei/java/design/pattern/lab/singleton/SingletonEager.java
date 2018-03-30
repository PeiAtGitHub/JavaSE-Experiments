package pei.java.design.pattern.lab.singleton;

/**
 * eager initialization
 * 
 * @author pei
 *
 */
public class SingletonEager {

    private static SingletonEager instance = new SingletonEager();

    private SingletonEager() {// private constructor, for internal use only
    }

    public static SingletonEager getInstance() {
        return instance;
    }

}
