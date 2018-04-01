package pei.java.jse.lab.java7new;

import java.io.IOException;

/**
 * The try-with-resources Statement
 * 
 * "Any object that implements java.lang.AutoCloseable can be used as a resource."
 * 
 * java.io.Closeable is a subinterface of java.lang.AutoCloseable
 * 
 * @author pei
 *
 */
public class TryWithResourcesStatement {
	
	public static void main(String[] args) throws Exception {

		try(AnAutoCloseableResource aacr = new AnAutoCloseableResource()){};
		try(AnCloseableResource acr = new AnCloseableResource()){};
	
	}

}

class AnAutoCloseableResource implements java.lang.AutoCloseable{
	@Override
	public void close() throws Exception {
		System.out.println("AutoCloseable, being CLOSED.");
	}
	
}

class AnCloseableResource implements java.io.Closeable {
	@Override
	public void close() throws IOException {
		System.out.println("Closeable, being CLOSED.");
	}
}
