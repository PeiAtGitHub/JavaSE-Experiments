package pei.java.design.pattern.lab.singleton;

/**
 * lazy instantiation
 * @author pei
 *
 */
public class SingletonLazy {
	
	private static SingletonLazy instance = null;
	
	private static Object syncObj = new Object();

	// prevents external instantiation
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

}
