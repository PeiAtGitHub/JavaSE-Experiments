package pei.java.jse.lab.systemANDruntime;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileStore;
import java.nio.file.FileSystem;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.nio.file.WatchService;
import java.nio.file.attribute.UserPrincipalLookupService;
import java.nio.file.spi.FileSystemProvider;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Set;

import org.junit.Test;

/**
 * 
 * @author pei
 *
 */
public class FileSystemTests {
	
	@Test
	public void fs() {
		File[] roots = File.listRoots();
		assertTrue(roots.length > 0);
		System.out.println("FS roots: "+ Arrays.toString(roots));
		System.out.println("Abs Path: " + roots[0].getAbsolutePath());
		System.out.println("Total Space: " 
				+ convertBytesNumberToReadableString(roots[0].getTotalSpace()));
		System.out.println("Free Space: "
				+ convertBytesNumberToReadableString(roots[0].getFreeSpace()));
		System.out.println("Usable Space: "
				+ convertBytesNumberToReadableString(roots[0].getUsableSpace()));
		
		System.out.println("File.pathSeparator: "+ File.pathSeparator);
		System.out.println("File.separator: " + File.separator);
		
	}
	
	@Test
	public void testBytesConverter() {
		try {
			convertBytesNumberToReadableString(0.5);
			fail("Should'v thrown an exception before this line.");
		} catch (IllegalArgumentException ex) {
			System.out.println("Expected exception: " + ex.getMessage());
		}
		assertTrue(convertBytesNumberToReadableString(1).equals("1 B"));
		assertTrue(convertBytesNumberToReadableString(11).equals("11 B"));
		assertTrue(convertBytesNumberToReadableString(1024).equals("1 KB"));
		assertTrue(convertBytesNumberToReadableString(1888).equals("1.8 KB"));
		assertTrue(convertBytesNumberToReadableString(1900).equals("1.9 KB"));
		assertTrue(convertBytesNumberToReadableString(1024 * 1024).equals("1 MB"));
		assertTrue(convertBytesNumberToReadableString((1024 * 1024) + (1024 * 100)).equals("1.1 MB"));
		assertTrue(convertBytesNumberToReadableString(1024 * 1024 * 1000).equals("1000 MB"));
		assertTrue(convertBytesNumberToReadableString(1024 * 1024 * 1024).equals("1 GB"));
		assertTrue(convertBytesNumberToReadableString(1024d * 1024d * 1024d * 1000d).equals("1000 GB"));
		assertTrue(convertBytesNumberToReadableString(1024d * 1024d * 1024d * 9999d).equals("9999 GB"));
		assertTrue(convertBytesNumberToReadableString(1024d * 1024d * 1024d * 10000d).equals("10000 GB"));
	}
	
	
	@Test
	public void thePaths() {
		String onlyFileName = "aFile.txt";
		String fullWinFilePath = "C:\\what\\ever\\path\\" + onlyFileName;
		String fullLnxFilePath = "/what/ever/path/" + onlyFileName;
		
		// the output depends on the OS u run this
		System.out.println(Paths.get(fullWinFilePath).getFileName());
		System.out.println(Paths.get(fullLnxFilePath).getFileName());
	}

	
	
	/*
	 * Utils
	 */
	public static String convertBytesNumberToReadableString(double numOfBytes) {
		if (numOfBytes < 1) {
			throw new IllegalArgumentException("Number of bytes must bigger or equal then 1.");
		}
		long KB = 1024;
		long MB = 1024 * 1024;
		long GB = 1024 * 1024 * 1024;
		DecimalFormat df = new DecimalFormat("#.#");

		if (numOfBytes >= GB) {
			return df.format(numOfBytes / GB) + " GB";
		} else if (numOfBytes >= MB) {
			return df.format(numOfBytes / MB) + " MB";
		} else if (numOfBytes >= KB) {
			return df.format(numOfBytes / KB) + " KB";
		} else {
			return df.format(numOfBytes) + " B";
		}
	}

}
