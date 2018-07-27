package pei.java.jse.lab.io;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Properties;

import org.junit.jupiter.api.Test;

import static com.github.peiatgithub.java.utils.Constants.*;


/**
 * @author pei
 */
public class TestFilesIO {

	public static final String testPropertiesFile = "Files/TestProperties.properties";
	public static final String fileToRead = "Files/TestReadFile.txt";
	public static final String fileToWrite= "Files/TestWriteFile.txt";

	@Test
	public void testPropertiesFile() throws IOException {
		Properties props = new Properties();
		
		// Properties has another constructor that takes a default Properties object
		// which has default properties
		// Demo for this feature is skipped here.
        
        try(FileInputStream in = new FileInputStream(testPropertiesFile)){
        	props.load(in);
        };
        
        assertEquals("abcdefg", props.getProperty("p1"));
        assertEquals("1234567890", props.getProperty("p2"));
        assertEquals("", props.getProperty("p3"));
        assertEquals(DEFAULT_STR, props.getProperty("NonExistentKey", DEFAULT_STR));
        assertNull(props.getProperty("NonExistentKey"));
    }
    
    @Test
    public void testFileReadLine() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(fileToRead))){
        	br.lines().forEach(oneLine -> System.out.println(oneLine));
        }
    }
    
    @Test
    public void testFileWrite() throws IOException{
        // if file does not exist, it will be created.
        // if path does not exist, exception thrown
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(fileToWrite))){
        	bw.write(S1);
        	bw.newLine();
        	bw.newLine();
        	bw.write(S2);
        	bw.newLine();
        	bw.newLine();
        	bw.write(S3);
        	
        	bw.flush();
        }
    }
    
    @Test
    public void testRandomAccessFileLastLine() throws IOException{

        try(RandomAccessFile rf = new RandomAccessFile(fileToRead, "r")){// r means read only
        	//total length
        	long start = rf.getFilePointer();//start position
        	long nextend = start + rf.length() - 1; // end position
        	rf.seek(nextend);// pointer to the end
        	
        	int c;//char
        	int counter = 0;
        	String theLastLine = null;
        	
        	while (nextend > start) {
        		c = rf.read();
        		if (c == '\n' || c == '\r') {
        			theLastLine = rf.readLine();
        			if (theLastLine != null && ++counter == 1) {
        				break;
        			}
        			nextend--;
        		}
        		nextend--;
        		rf.seek(nextend);
        		if (nextend == 0) {// pointer goes back to the beginning of file
        			theLastLine = rf.readLine();
        		}
        	}
        	System.out.println(theLastLine);
        }
        
    }
}
