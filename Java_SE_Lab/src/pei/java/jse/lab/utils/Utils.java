package pei.java.jse.lab.utils;

import java.text.DecimalFormat;

import org.apache.commons.io.FileUtils;

/**
 * 
 * The Utils for the lab here 
 * 
 * @author pei
 *
 */
public class Utils {
    // Some commonly used strings
    public final static String FIRST_NAME = "FirstName";
    public final static String LAST_NAME = "LastName";
    
    public final static String ABC = "abc";
    public final static String DEF = "def";
    public final static String GHI = "ghi";
    public final static String TOM = "TOM";
    public final static String DORAEMON = "DORAEMON";
    public final static String CAT = "CAT";
    public final static String JERRY = "JERRY";
    public final static String MICKEY = "MICKEY";
    public final static String MOUSE = "MOUSE";

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
    
    /**
     * FileUtils.byteCountToDisplaySize() rounds down to integer numbers
     * 
     * This method keep 1 digit after decimal point
     * 
     * @param numOfBytes
     * @return
     */
    
    public static String bytesToReadable(double numOfBytes) {
        
        if (numOfBytes < 0) {
            throw new IllegalArgumentException("Number of bytes must bigger than or equal to 0.");
        }
        
        DecimalFormat df = new DecimalFormat("#.#");

        if (numOfBytes >= FileUtils.ONE_GB) {
            return df.format(numOfBytes / FileUtils.ONE_GB) + " GB";
        } else if (numOfBytes >= FileUtils.ONE_MB) {
            return df.format(numOfBytes / FileUtils.ONE_MB) + " MB";
        } else if (numOfBytes >= FileUtils.ONE_KB) {
            return df.format(numOfBytes / FileUtils.ONE_KB) + " KB";
        } else {
            return df.format(numOfBytes) + " Bytes";
        }
    }


}
