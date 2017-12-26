package pei.java.jse.lab.xml;

import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.validation.SchemaFactory;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import lombok.extern.slf4j.Slf4j;
import pei.java.jse.lab.utils.Utils;

/**
 * 
 * @author pei
 *
 */
@Slf4j
public class ValidationDemo {

	public static void main(String[] args) throws Exception {
		
		Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder()
				.parse(new File(Utils.inputXmlFile));

		log.info("Validating {} against {}", Utils.inputXmlFile, Utils.inputXsdFile);
		SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI).newSchema(new File(Utils.inputXsdFile))
				.newValidator().validate(new DOMSource(document));
		log.info("Validate {} successfully against {}.", Utils.inputXmlFile, Utils.inputXsdFile);

	}

}
