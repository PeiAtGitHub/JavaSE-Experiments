package pei.java.jse.lab.specialty;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Arrays;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import static com.github.peiatgithub.java.utils.Utils.*;
import static com.github.peiatgithub.java.utils.Constants.*;

/**
 * 
 * @author pei
 *
 */
public class Networking {
	
    public final static String DOMAIN_WIKIPEDIA = "www.wikipedia.org";
    public final static String URL_ONLINE_XML = "https://www.w3schools.com/xml/note.xml";

    
    @Test
    public void hostAndPing() throws IOException {
        
        InetAddress localHost = InetAddress.getLocalHost();
        System.out.println(Arrays.toString(localHost.getAddress()));
        System.out.println(localHost.getHostAddress());
        System.out.println(localHost.getHostName());
        assertTrue(localHost.isReachable(1000));
        
        InetAddress wikipediaAddress = InetAddress.getByName(DOMAIN_WIKIPEDIA);
        System.out.println(Arrays.toString(wikipediaAddress.getAddress()));
        System.out.println(wikipediaAddress.getHostAddress());
        System.out.println(wikipediaAddress.getHostName());
        assertTrue("Failed to ping wikipedia within 3s", wikipediaAddress.isReachable(3000));
    }
    
    @Test
    public void testUrlConnection() throws Exception {

        HttpURLConnection connection = (HttpURLConnection) new URL(URL_ONLINE_XML).openConnection(); 
        connection.connect();
        System.out.println(IOUtils.toString(connection.getInputStream(), Charset.defaultCharset()));
        connection.disconnect();
        
        // an simpler way
        System.out.println(IOUtils.toString(new URL(URL_ONLINE_XML), Charset.defaultCharset()));
    }
    
}

