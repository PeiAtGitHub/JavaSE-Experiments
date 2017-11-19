package pei.java.jse.lab.io;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * 
 * @author pei
 *
 */
public class MyJavaLogger {
	
	// This is the java.util.logging.Logger
	public static Logger logger = Logger.getLogger("MyTestLogger"); 
	
	static {
		try {
			FileHandler fh = new FileHandler("logs/MyTestLogs.log", true);
			fh.setFormatter(new SimpleFormatter());
			logger.addHandler(fh);
//			fh.close();
		} catch (SecurityException | IOException e) {
			throw new RuntimeException(e);
		}
		logger.setUseParentHandlers(true);// the logs will be automatically output to console
	}
}
