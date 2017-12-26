package pei.java.jse.lab.xml;

import java.io.File;

import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import lombok.extern.slf4j.Slf4j;
import pei.java.jse.lab.utils.Utils;

/**
 * 
 * @author pei
 *
 */
public class SaxDemo {

	public static void main(String[] args) throws Exception {
		// log the common name of the 3rd plant
		SAXParserFactory.newInstance().newSAXParser()
		.parse(new File(Utils.inputXmlFile), new MySaxHandler());
	}

}

//
@Slf4j
class MySaxHandler extends DefaultHandler {

	static final String PLANT_ELEMENT = "PLANT";
	static final String COMMON_ELEMENT = "COMMON";

	boolean the3rdPlantFlag = false;
	boolean commonFlag = false;
	int counter = 0;

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		switch (qName) {
		case PLANT_ELEMENT:
			if (++counter == 3) {
				the3rdPlantFlag = true;
			}
			break;
		case COMMON_ELEMENT:
			commonFlag = true;
		default:
			break;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		switch (qName) {
		case PLANT_ELEMENT:
			if (the3rdPlantFlag) {
				the3rdPlantFlag = false;
			}
			break;
		case COMMON_ELEMENT:
			commonFlag = false;
		default:
			break;
		}
	}

	@Override
	public void characters(char ch[], int start, int length) throws SAXException {
		if (the3rdPlantFlag && commonFlag) {
			log.info("The 3rd plant common name: {}", new String(ch, start, length));
		}
	}
}