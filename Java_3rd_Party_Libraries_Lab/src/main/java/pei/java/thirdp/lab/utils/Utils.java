package pei.java.thirdp.lab.utils;

/**
 * 
 * @author pei
 *
 */
public class Utils {
	
	public final static String SHOULD_THROW_EXCEPTION = "Should'v thrown exception.";

	//
	public static Exception catchException(NonArgFunction function) {
		try {
			function.doSth();
			return null;
		} catch (Exception e) {
			return e;
		}
	}

}
