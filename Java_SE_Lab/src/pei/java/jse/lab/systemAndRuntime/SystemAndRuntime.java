package pei.java.jse.lab.systemAndRuntime;

import java.util.Map.Entry;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author pei
 *
 */
@Slf4j
public class SystemAndRuntime {
	
	@Test
	public void system() {
		// demo of system properties
		for (Entry<Object, Object> prop : System.getProperties().entrySet()){
			log.info(prop.toString());
		}
	}

	@Test
	public void runtime() {
		Runtime rt = Runtime.getRuntime();
		log.info("Max Memory: {}", FileUtils.byteCountToDisplaySize(rt.maxMemory()));
		log.info("Total Memory: {}", FileUtils.byteCountToDisplaySize(rt.totalMemory()));
		log.info("Free Memory: {}", FileUtils.byteCountToDisplaySize(rt.freeMemory()));
		log.info("Available Processors: {}", rt.availableProcessors());
		rt.gc();
		log.info("Free Memory AFTER called gc(): {}", FileUtils.byteCountToDisplaySize(rt.freeMemory()));		
	}
}
