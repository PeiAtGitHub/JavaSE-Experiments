package pei.java.jse.lab.language;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.CoreMatchers.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.junit.Test;

/**
 * 
 * Somebody told me reflection is not encouraged to use nowadays.
 * 
 * @author pei
 *
 */
public class ReflectionTests {
	
	public int testField = 1;
	
	
	@Test
	public void intanceofclass() {
		/*
		 * LinkedHashMap is a sub class of HashMap
		 */
		HashMap<?, ?> aHashMap = new HashMap<>();
		LinkedHashMap<?, ?> aLinkedHashMap = new LinkedHashMap<>();
		//
		assertThat(aLinkedHashMap, instanceOf(HashMap.class));
		assertThat(aLinkedHashMap, instanceOf(LinkedHashMap.class));
		assertThat(aLinkedHashMap.getClass().getName(), is("java.util.LinkedHashMap"));
		assertThat(aHashMap.getClass().getName(), is("java.util.HashMap"));

		System.out.println("intanceofclass() Finished.");
	}
	
	@Test
	public void  testReflections()throws Exception {
		
		Class<?> classObj = Class.forName("pei.java.jse.lab.language.ReflectionTests", 
					false, Thread.currentThread().getContextClassLoader());

		System.out.println("class name: " + classObj.getName());
		System.out.println("Is interface: " + classObj.isInterface());
		System.out.println("Is primitive type: " + classObj.isPrimitive());
		System.out.println("Is array: " + classObj.isArray());
		System.out.println("Super class :" + classObj.getSuperclass().getName());
		System.out.println("Fields: " + Arrays.toString(classObj.getFields()));
		System.out.println("Methods: " + Arrays.toString(classObj.getMethods()));
		System.out.println("Constructors:  " + Arrays.toString(classObj.getConstructors()));
		System.out.println("Class loader: " + classObj.getClassLoader());
		System.out.println("Class loader: " + classObj.getClassLoader().getParent());

		((ReflectionTests) classObj.newInstance()).intanceofclass();
	}
}
