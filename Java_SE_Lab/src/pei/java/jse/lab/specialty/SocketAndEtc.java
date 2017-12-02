package pei.java.jse.lab.specialty;

import java.io.IOException;
import java.net.UnknownHostException;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

import org.junit.Test;

/**
 * 
 * @author pei
 *
 */
public class SocketAndEtc {
	
	@Test
	public void handShake() throws UnknownHostException, IOException {

		SSLSocket remoteSocket = (SSLSocket) ((SSLSocketFactory) SSLSocketFactory.getDefault())
				.createSocket("www.google.com", 443);
		remoteSocket.startHandshake();
		remoteSocket.setSoTimeout(10*1000);// when stuck in read(),time out in 10s
		System.out.println(remoteSocket.getRemoteSocketAddress());
	}

}
