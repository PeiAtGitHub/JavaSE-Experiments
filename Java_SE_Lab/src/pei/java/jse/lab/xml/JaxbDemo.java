package pei.java.jse.lab.xml;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import lombok.extern.slf4j.Slf4j;
import pei.java.jse.lab.Utils;
import pei.java.jse.lab.xml.CATALOG.PLANT;

/**
 * 
 * @author pei
 *
 */
@Slf4j
public class JaxbDemo {
	
	public static void main(String[] args) throws JAXBException, IOException {
		objToXml();
		xmlToObj();
	}
	
	
	public static void objToXml() throws IOException, JAXBException {
		Animal animal = new Animal();
		animal.setName("Tiger");
		animal.setId(1);
		animal.setAge(10);
		//
		Marshaller jaxbMarshaller = JAXBContext.newInstance(Animal.class).createMarshaller();
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);//formatted
		jaxbMarshaller.marshal(animal, new File("Files/createdXMLfromObject.xml"));
		StringWriter strWriter = new StringWriter();
		jaxbMarshaller.marshal(animal, strWriter);
		log.info("Creating XML file with JAXB, content:\n{}", strWriter.toString()); 
	}
	
	public static void xmlToObj() throws JAXBException {
			Unmarshaller jaxbUnmarshaller = JAXBContext.newInstance(CATALOG.class).createUnmarshaller();
			CATALOG catalog = (CATALOG) jaxbUnmarshaller.unmarshal(new File(Utils.inputXmlFile));
			PLANT the3rdPlant = catalog.getPLANT().get(2);
			log.info("Data about the 3rd plant:");
			log.info(String.valueOf(the3rdPlant.getId()));
			log.info(the3rdPlant.getCOMMON());
			log.info(the3rdPlant.getBOTANICAL());
			log.info(the3rdPlant.getLIGHT());
			log.info(the3rdPlant.getZONE());
			log.info("{} {}",String.valueOf(the3rdPlant.getPRICE().getValue())
					, the3rdPlant.getPRICE().getCurrency());
			log.info(String.valueOf(the3rdPlant.getAVAILABILITY()));
	}
}
