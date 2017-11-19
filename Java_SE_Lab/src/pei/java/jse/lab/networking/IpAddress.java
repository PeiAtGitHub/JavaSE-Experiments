package pei.java.jse.lab.networking;

import static org.junit.Assert.fail;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

import org.junit.Test;

/**
 * 
 * @author pei
 *
 */
public class IpAddress {

	@Test
	public void networkHost() throws UnknownHostException {
		InetAddress localHost = InetAddress.getLocalHost();

	    System.out.println(Arrays.toString(localHost.getAddress()));
	    System.out.println(localHost.getHostAddress());
	    System.out.println(localHost.getHostName());
	}
	
	@Test
	public void ping() throws IOException {
		String hostName = "www.google.com";
		InetAddress address = InetAddress.getByName(hostName);
		System.out.println(address.getHostAddress());
		for(int i = 1; i<=5; i ++){
			System.out.format("Ping x%s%n", i);
			if(address.isReachable(3000)) {
				System.out.println("Reached!");
				return;
			}
		}
		fail(String.format("Fail to ping %s", hostName));
	}
	
	

}
