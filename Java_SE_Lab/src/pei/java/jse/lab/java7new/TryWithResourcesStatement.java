package pei.java.jse.lab.java7new;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.assertj.core.api.Assertions.*;

import com.github.peiatgithub.java.utils.RunFlag;
import java.io.IOException;

import org.junit.Test;

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
		assertThat(RunFlag.runTimes(), is(1));
		try(AnCloseableResource acr = new AnCloseableResource()){
		};
		assertThat(RunFlag.runTimes(), is(2));
		
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
