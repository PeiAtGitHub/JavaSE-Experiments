package pei.java.jse.lab.io;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.junit.Test;

/**
 * 
 * @author pei
 *
 */
public class PropertiesFile {
	
	@Test
	public void testPropertiesFile() throws IOException {
		Properties props = new Properties();
		// Properties has another constructor that takes a default Properties object
		// which has default properties
		// Demo for this feature is skipped here.
		
		FileInputStream in = new FileInputStream("TestProperties.properties");
		props.load(in);
		in.close();
		
		assertTrue(props.getProperty("p1").equals("abcdefg"));
		assertTrue(props.getProperty("p2").equals("1234567890"));
		assertTrue(props.getProperty("p3").isEmpty());
		assertNull(props.getProperty("NonExistentKey"));
	}

}
