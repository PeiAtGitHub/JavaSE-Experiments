package pei.java.jse.lab.io;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

	public static final String TEST_PROPS_FILE = "Files/TestProperties.properties";
	public static final String FILE_TO_READ = "Files/TestReadFile.txt";
	public static final String FILE_TO_WRITE= "Files/TestWriteFile.txt";

	@Test
	public void propertiesFile() throws IOException {
		Properties props = new Properties();
		// Properties has another constructor that takes a default Properties object.
        
        try(FileInputStream in = new FileInputStream(TEST_PROPS_FILE)){
        	props.load(in);
        };
        
        assertEquals("abcdefg", props.getProperty("p1"));
        assertEquals("1234567890", props.getProperty("p2"));
        assertEquals("", props.getProperty("p3"));
        assertEquals(DEFAULT_STR, props.getProperty("NonExistentKey", DEFAULT_STR));
        assertNull(props.getProperty("NonExistentKey"));
    }
    
    @Test
    public void fileReadLine() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_TO_READ))){
        	br.lines().forEach(oneLine -> System.out.println(oneLine));
        }
    }
    
    @Test
    public void fileWrite() throws IOException{
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_TO_WRITE))){
            /*
             *  if file does not exist, it will be created.
             *  if path does not exist, exception thrown
             */
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
    public void randomAccessFile() throws Exception{
        try(RandomAccessFile rf = new RandomAccessFile(FILE_TO_READ, "r")){// "r": read only
            /*
             * <pre>
               File content has 3 lines, each line has 26 chars(EXCL. the 2 chars "\r\n"), as follows:
               The 1st line of this file.
               The 2nd line of this file.
               The 3rd line of this file.
               Total number of chars of the file is 28+28+26=82
             * </pre>
             */
            assertEquals(82, rf.length());
            assertEquals(0, rf.getFilePointer());
            
            // pointer MOVES FORWARD after read byte or read line.
            assertEquals('T', (char)rf.read());
            assertEquals(1, rf.getFilePointer()); 
            
            assertEquals("he 1st line of this file.", rf.readLine()); // read the line FROM the pointer.
            assertEquals(28, rf.getFilePointer()); 
            assertEquals("The 2nd line of this file.", rf.readLine());
            assertEquals(56, rf.getFilePointer()); 

            //
            rf.seek(26);
            assertEquals('\r', (char)rf.read());
            assertEquals('\n', (char)rf.read());
            assertEquals('T', (char)rf.read());
            assertEquals('h', (char)rf.read());
            assertEquals('e', (char)rf.read());

            rf.seek(26);
            assertEquals("", rf.readLine()); // when pointer on \r
            rf.seek(27);
            assertEquals("", rf.readLine()); // when pointer on \n
            assertEquals("The 2nd line of this file.", rf.readLine()); 
            
            // file end
            rf.seek(rf.length() - 1);
            assertEquals('.', (char)rf.read());
            assertEquals(-1, rf.read());
            
            rf.seek(rf.length() - 1);
            assertEquals(".", rf.readLine());
            assertNull(rf.readLine());
        }
    }
    
}
