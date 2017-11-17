package pei.java.jse.lab.systemANDruntime;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Map.Entry;

import org.junit.Test;

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
			System.out.println(prop.toString());
		}
		
		//
	}

	@Test
	public void runtime() {
		Runtime rt = Runtime.getRuntime();
		// TODO
		
	}
	
	@Test
	public void networkHost() throws UnknownHostException {
		InetAddress localHost = InetAddress.getLocalHost();

	    System.out.println(Arrays.toString(localHost.getAddress()));
	    System.out.println(localHost.getHostAddress());
	    System.out.println(localHost.getHostName());
	}
}
