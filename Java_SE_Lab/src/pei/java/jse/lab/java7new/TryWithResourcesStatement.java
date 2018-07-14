package pei.java.jse.lab.java7new;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.github.peiatgithub.java.utils.RunFlag;
import java.io.IOException;

import org.junit.jupiter.api.Test;


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
	
	@Test
	public void tryWithResourcesDemo() throws Exception {
		
		RunFlag.reset();
		
		try(AnAutoCloseableResource aacr = new AnAutoCloseableResource()){
		};
		
		assertEquals(1, RunFlag.runTimes());
		
		try(AnCloseableResource acr = new AnCloseableResource()){
		};
		
		assertEquals(2, RunFlag.runTimes());
		
		RunFlag.reset();
	}

}

class AnAutoCloseableResource implements java.lang.AutoCloseable{
	@Override
	public void close() throws Exception {
		RunFlag.run();
	}
	
}

class AnCloseableResource implements java.io.Closeable {
	@Override
	public void close() throws IOException {
		RunFlag.run();
	}
}
