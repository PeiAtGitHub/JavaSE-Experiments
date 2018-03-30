package pei.java.jse.lab.xml;

import java.io.File;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import lombok.extern.slf4j.Slf4j;
import pei.java.jse.lab.utils.Utils;

/**
 * 
 * @author pei
 *
 */
@Slf4j
public class XpathDemo {

    public static void main(String[] args) throws Exception {

        findPlantWhichNeedsSunLight();
        
    }

    private static void findPlantWhichNeedsSunLight() throws Exception {
        String xpathExpr = "//PLANT/LIGHT[contains(., 'Sun')]";
        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder()
                .parse(new File(Utils.inputXmlFile));
        NodeList lightNodes = (NodeList) XPathFactory.newInstance().newXPath()
                .compile(xpathExpr).evaluate(document, XPathConstants.NODESET);

        log.info("Plant which needs sun light:");
        for (int i = 0; i < lightNodes.getLength(); i++) {
            String commonName = ((Element) lightNodes.item(i).getParentNode())
                    .getElementsByTagName("COMMON").item(0).getTextContent();
            log.info("Plant {} need: {}.", commonName, lightNodes.item(i).getTextContent());
        }

    }

}
