package pei.java.jse.lab.xml;

import static pei.java.jse.lab.xml.XmlUtils.*;

import java.io.File;

import org.junit.Test;

import static org.assertj.core.api.Assertions.*;
/**
 * 
 * @author pei
 *
 */
public class XmlComparison {

	@Test
	public void effectivelySame() throws Exception {
		assertThat(contentOf(new File(INPUT_XML))).isXmlEqualToContentOf(new File(INPUT_XML2));
	}
	
}
