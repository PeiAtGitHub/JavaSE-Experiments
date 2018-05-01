package pei.java.jse.lab.utils;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

import lombok.experimental.UtilityClass;

/**
 * 
 * The Utils for the lab here 
 * 
 * @author pei
 *
 */
@UtilityClass
public class Utils {
    
    // Some commonly used strings
	public final static String STR = "STR";
	public final static String DEFAULT_STR = "DEFAULT_STR";
	
    public final static String FIRST_NAME = "FirstName";
    public final static String LAST_NAME = "LastName";
    
    public final static String SHOULDv_THROWN_EXCEPTION = "Should'v thrown exception.";
    
    public final static String S1 = "S1";
    public final static String S2 = "S2";
    public final static String S3 = "S3";
    
    //
    public final static List<Integer> TEST_LIST_123 = ImmutableList.of(1, 2, 3);
    public final static Set<Integer> TEST_SET_123 = ImmutableSet.of(1, 2, 3);
    public final static Map<String, Integer> TEST_MAP_123 = ImmutableMap.of(S1, 1, S2, 2, S3, 3);
    
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

    /**
     * Thread.sleep(millis), 
     * Swallow the Exception (just e.printStackTrace())
     * 
     * @param millis
     */
    public static void threadSleep(long millis) {
    	
    	try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	
    }

}
