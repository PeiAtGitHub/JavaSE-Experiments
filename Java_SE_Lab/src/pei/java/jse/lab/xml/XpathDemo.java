package pei.java.jse.lab.xml;

import java.io.File;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import lombok.extern.slf4j.Slf4j;
import pei.java.jse.lab.utils.Utils;
import static pei.java.jse.lab.xml.XmlUtils.*;

/**
 * 
 * @author pei
 *
 */
@Slf4j
public class XpathDemo {

    @Test
	public void findPlantWhichNeedsSunLight() throws Exception {
    	
        String xpath = "//PLANT/LIGHT[contains(., 'Sun')]";
        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder()
        		.parse(new File(Utils.inputXmlFile));
        NodeList lightNodes = (NodeList) XPathFactory.newInstance().newXPath()
                .compile(xpath).evaluate(document, XPathConstants.NODESET);

        log.info("Plant which needs sun light:");
        for (int i = 0; i < lightNodes.getLength(); i++) {
            String commonName = ((Element) lightNodes.item(i).getParentNode())
                    .getElementsByTagName(COMMON_TAG_NAME).item(0).getTextContent();
            log.info("Plant {} need: {}.", commonName, lightNodes.item(i).getTextContent());
        }

    }

}
