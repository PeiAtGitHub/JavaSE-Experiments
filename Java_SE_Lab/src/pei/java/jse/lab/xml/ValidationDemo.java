package pei.java.jse.lab.xml;

import java.io.File;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.validation.SchemaFactory;

import org.w3c.dom.Document;

import lombok.extern.slf4j.Slf4j;
import static pei.java.jse.lab.xml.XmlUtils.*;

/**
 * 
 * @author pei
 *
 */
@Slf4j
public class ValidationDemo {

    public static void main(String[] args) throws Exception {
        
        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder()
                .parse(new File(INPUT_XML));

        log.info("Validating {} against {}", INPUT_XML, INPUT_XSD);
        SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI).newSchema(new File(INPUT_XSD))
                .newValidator().validate(new DOMSource(document));
        log.info("Validate {} successfully against {}.", INPUT_XML, INPUT_XSD);

    }

}
