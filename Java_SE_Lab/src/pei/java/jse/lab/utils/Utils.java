package pei.java.jse.lab.utils;


/**
 * 
 * The Utils for the lab here 
 * 
 * @author pei
 *
 */
public class Utils {
	//
	public final static String SHOULD_THROW_EXCEPTION = "Should'v thrown exception.";
	
	// some resources
	public final static String wikipediaUrl = "https://www.wikipedia.org/";
	public final static String wikipediaDomain = "www.wikipedia.org";
	public final static String googleDomain = "www.google.com";
	public final static String anOnlineXmlFileUrl = "https://www.w3schools.com/xml/note.xml";
	
	// local files
	public static final String inputXmlFile = "Files/PlantsCatalog.xml";
	public static final String inputXsdFile = "Files/PlantsCatalog.xsd";
	public static final String testPropertiesFile = "Files/TestProperties.properties";
	public static final String fileToRead = "Files/TestReadFile.txt";
	public static final String fileToWrite= "Files/TestWriteFile.txt";

	/*
	 * 
	 */
	
    public static void printWithThreadName(String message) {
        System.out.format("%s: %s%n", Thread.currentThread().getName(), message);
    }
    
    public static Exception catchException(NonArgFunction function) {
		try {
			function.doSth();
			return null;
		} catch (Exception e) {
			return e;
		}
	}


}
