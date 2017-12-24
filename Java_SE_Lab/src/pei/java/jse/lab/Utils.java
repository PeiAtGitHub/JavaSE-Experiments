package pei.java.jse.lab;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * The Utils for the lab here 
 * 
 * @author pei
 *
 */
public class Utils {
	
	// some resources
	public final static String wikipediaUrl = "https://www.wikipedia.org/";
	public final static String wikipediaDomain = "www.wikipedia.org";
	public final static String googleDomain = "www.google.com";
	public final static String anOnlineXmlFileUrl = "https://www.w3schools.com/xml/note.xml";
	
	// local files
	public static final String inputXmlFile = "Files/PlantsCatalog.xml";
	public static final String inputXsdFile = "Files/PlantsCatalog.xsd";
	
	/*
	 * 
	 */
	
    public static void printWithThreadName(String message) {
        System.out.format("%s: %s%n", Thread.currentThread().getName(), message);
    }


}
