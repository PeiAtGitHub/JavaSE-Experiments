package pei.java.jse.lab.systemANDruntime;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Map.Entry;
import java.util.logging.Logger;

import org.junit.Test;

import pei.java.jse.lab.io.MyJavaLogger;

/**
 * 
 * @author pei
 *
 */
public class SystemAndRuntime {
	
	@Test
	public void system() {
		// demo of system properties
		for (Entry<Object, Object> prop : System.getProperties().entrySet()){
			MyJavaLogger.logger.info(prop.toString());
		}
	}

	@Test
	public void runtime() {
		Runtime rt = Runtime.getRuntime();
		System.out.println("Max Memory: " +
				FileSystem.convertBytesNumberToReadableString(rt.maxMemory()));
		System.out.println("Total Memory: " +
				FileSystem.convertBytesNumberToReadableString(rt.totalMemory()));
		System.out.println("Free Memory: " +
				FileSystem.convertBytesNumberToReadableString(rt.freeMemory()));
		System.out.println("Available Processors: " + rt.availableProcessors());
		rt.gc();
		System.out.println("Free Memory AFTER called gc(): " +
				FileSystem.convertBytesNumberToReadableString(rt.freeMemory()));		
	}
}
