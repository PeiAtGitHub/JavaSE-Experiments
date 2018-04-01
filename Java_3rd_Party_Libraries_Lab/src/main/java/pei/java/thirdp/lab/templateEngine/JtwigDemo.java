package pei.java.thirdp.lab.templateEngine;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;
import org.jtwig.environment.DefaultEnvironmentConfiguration;
import org.jtwig.environment.Environment;
import org.jtwig.environment.EnvironmentFactory;
import org.jtwig.resource.reference.ResourceReference;
import org.junit.Test;

/**
 * 
 * @author pei
 *
 */
public class JtwigDemo {
	
	public static final String JTWIG_FILE = "JtwigExample.twig"; 
	// the jtwig file content is same as the string of JTWIG_TEXT 
	public static final String JTWIG_TEXT = "{{var1}}!{% if (more) %} and {{var2}}!{% endif %}";
	
	@Test
	public void testFile() throws Exception {
		verifyTemplate(JtwigTemplate.classpathTemplate(JTWIG_FILE));
	}
	
	@Test
	public void testString() throws Exception {
		Environment environment = new EnvironmentFactory().create(new DefaultEnvironmentConfiguration());
		ResourceReference resource = new ResourceReference(ResourceReference.STRING, JTWIG_TEXT);
		verifyTemplate(new JtwigTemplate(environment, resource));
	}

	private void verifyTemplate(JtwigTemplate template) {
		JtwigModel model = JtwigModel.newModel();
		model.with("var1", "Hello World");
		model.with("var2", "Hello World Again");
		//
		model.with("more", false);
		assertThat(template.render(model), is("Hello World!"));
		model.with("more", true);
		assertThat(template.render(model), is("Hello World! and Hello World Again!"));
	}

}