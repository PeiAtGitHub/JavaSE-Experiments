package pei.java.jse.lab.systemANDruntime;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Map.Entry;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
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
				FileUtils.byteCountToDisplaySize(rt.maxMemory()));
		System.out.println("Total Memory: " +
				FileUtils.byteCountToDisplaySize(rt.totalMemory()));
		System.out.println("Free Memory: " +
				FileUtils.byteCountToDisplaySize(rt.freeMemory()));
		System.out.println("Available Processors: " + rt.availableProcessors());
		rt.gc();
		System.out.println("Free Memory AFTER called gc(): " +
				FileUtils.byteCountToDisplaySize(rt.freeMemory()));		
	}
}
