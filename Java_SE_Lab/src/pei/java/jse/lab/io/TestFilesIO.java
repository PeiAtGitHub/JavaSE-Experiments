package pei.java.jse.lab.io;

import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;

import org.junit.Test;

/**
 * 
 * @author pei
 *
 */
public class TestFilesIO {
	String fileToRead = "TestReadFile.txt";
	
	@Test
	public void testFileReadLine() throws IOException {
		BufferedReader br = null; 
		String oneLine;
		try {
			br = new BufferedReader(new FileReader(fileToRead));
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
		BufferedWriter bw = new BufferedWriter(new FileWriter("TestWriteFile.txt"));
	    
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

		RandomAccessFile rf = new RandomAccessFile(fileToRead, "r");// r means read only
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
				if (theLastLine != null) {
					counter++;
					if (counter == 1)
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
