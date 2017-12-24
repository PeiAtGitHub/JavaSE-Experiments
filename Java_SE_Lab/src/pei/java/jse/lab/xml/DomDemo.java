package pei.java.jse.lab.xml;

import java.io.File;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import lombok.extern.slf4j.Slf4j;
import pei.java.jse.lab.Utils;

/**
 * 
 * @author pei
 *
 */
@Slf4j
public class DomDemo {

	public static void main(String[] args) throws Exception {

		Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder()
				.parse(new File(Utils.inputXmlFile));
		Element root = document.getDocumentElement();
		root.normalize();
		Element the3rdPlant = (Element)root.getElementsByTagName("PLANT").item(2);
		log.info("The 3rd plant common name: {}", 
				the3rdPlant.getElementsByTagName("COMMON").item(0).getTextContent());
	}
	
}
