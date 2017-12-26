package pei.java.jse.lab.io;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Properties;

import org.junit.Test;

import pei.java.jse.lab.utils.Utils;

/**
 * 
 * @author pei
 *
 */
public class TestFilesIO {
	
	@Test
	public void testPropertiesFile() throws IOException {
		Properties props = new Properties();
		// Properties has another constructor that takes a default Properties object
		// which has default properties
		// Demo for this feature is skipped here.
		
		FileInputStream in = new FileInputStream(Utils.testPropertiesFile);
		props.load(in);
		in.close();
		
		assertThat(props.getProperty("p1"), is("abcdefg"));
		assertThat(props.getProperty("p2"), is("1234567890"));
		assertTrue(props.getProperty("p3").isEmpty());
		assertNull(props.getProperty("NonExistentKey"));
	}
	
	@Test
	public void testFileReadLine() throws IOException {
		BufferedReader br = null; 
		String oneLine;
		try {
			br = new BufferedReader(new FileReader(Utils.fileToRead));
			while ((oneLine = br.readLine()) != null) {
				System.out.println(oneLine);
			}
		}catch (Exception e) {
			fail(e.getMessage());
		}finally{
			br.close();
		}
	}
	
	@Test
	public void testFileWrite() throws IOException{
		// if file does not exist, it will be created.
		// if path does not exist, exception thrown
		BufferedWriter bw = new BufferedWriter(new FileWriter(Utils.fileToWrite));
	    
		bw.write("first");
	    bw.newLine();
	    bw.newLine();
	    bw.write("middle");
	    bw.newLine();
	    bw.newLine();
	    bw.write("last");
	    
	    bw.flush();
	    bw.close();
	}
	
	@Test
	public void testRandomAccessFileLastLine() throws IOException{

		RandomAccessFile rf = new RandomAccessFile(Utils.fileToRead, "r");// r means read only
		long len = rf.length();//total length
		long start = rf.getFilePointer();//start position
		long nextend = start + len - 1; // end position
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
		rf.close();
		System.out.println(theLastLine);
	}
}
