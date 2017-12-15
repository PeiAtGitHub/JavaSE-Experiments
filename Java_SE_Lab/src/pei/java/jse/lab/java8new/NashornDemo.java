package pei.java.jse.lab.java8new;

import static org.junit.Assert.assertEquals;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.junit.Test;

/**
 * 
 * @author pei
 *
 */
public class NashornDemo {

	final static String jsEngineName = "nashorn";

	@Test
	public void basics() throws ScriptException {
		ScriptEngine nashorn = new ScriptEngineManager().getEngineByName(jsEngineName);
		nashorn.eval("print('Hello Nashorn!')");
		nashorn.eval("print(new Date())");
		nashorn.eval("print(Math.PI)");
		assertEquals(9, ((Integer) nashorn.eval("3*3")).intValue());
	}

}
