package pei.java.thirdp.lab.utils;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.IntStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomUtils;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

import lombok.experimental.UtilityClass;

/**
 * 
 * @author pei
 *
 */
@UtilityClass
public class Utils {
    
    // Some commonly used strings
	public final static String STR = "STR";
	public final static String DEFAULT_STR = "DEFAULT_STR";
	public final static String HELLO_WORLD = "HELLO WORLD";
	
    public final static String FIRST_NAME = "FirstName";
    public final static String LAST_NAME = "LastName";
    
    public final static String SHOULDv_THROWN_EXCEPTION = "Should'v thrown exception.";
    
    public final static String S1 = "S1";
    public final static String S2 = "S2";
    public final static String S3 = "S3";
    
    public final static String COMMA = ",";
    public final static String SEMICOLON = ";";
    //
    public final static int[] TEST_INT_ARRAY_123 = new int[] {1, 2, 3};
    public final static List<Integer> TEST_LIST_123 = ImmutableList.of(1, 2, 3);
    public final static Set<Integer> TEST_SET_123 = ImmutableSet.of(1, 2, 3);
    public final static Map<String, Integer> TEST_MAP_123 = ImmutableMap.of(S1, 1, S2, 2, S3, 3);
    
    // URLs
    public static final String URL_APACHE_COMMONS_IO_MAIN = "https://commons.apache.org/proper/commons-io/index.html";
    public static final String URL_WIKIPEDIA_MAIN_PAGE= "https://en.wikipedia.org/wiki/Main_Page";
    public static final String URL_GOOGLE = "https://www.google.com";
    
    //
    public static Throwable catchThrowable(NonArgFunction function) {
        try {
            function.doSth();
            return null;
        } catch (Throwable t) {
            return t;
        }
    }

    /**
     * 
     * @return
     */
    public static long getRandom16DigitNumber() {
        return RandomUtils.nextLong(1_000_000_000_000_000L, 10_000_000_000_000_000L);
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
    
    public static void printlnWithThreadName(String message) {
        System.out.format("%s: %s%n", Thread.currentThread().getName(), message);
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

    /**
     * Return the human readable string which represents an array
     * @param arr
     */
    public static <T> String getReadableArrayString(T[] arr) {
    	return Arrays.asList(arr).toString();
    }
    
    public static void repeatRun(int times, NonArgFunction function) {
    	IntStream.range(0, times).forEach(i->function.doSth());
    }
    
    public static void repeatRun(int times, Consumer<Integer> c) {
    	IntStream.range(0, times).forEach(i->c.accept(i));
    }
    
    public static void ifNotNull(Object obj, NonArgFunction fnc) {
    	if(obj != null) {
    		fnc.doSth();	
    	}
    }
    
    public static int sum(int... numbers) {
        int sum = 0;
        for (int num : numbers) {
            sum = sum + num;
        }
        return sum;
    }
    
}

