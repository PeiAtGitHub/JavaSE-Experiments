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

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.FileFilterUtils;
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
				+ FileUtils.byteCountToDisplaySize(roots[0].getTotalSpace()));
		System.out.println("Free Space: "
				+ FileUtils.byteCountToDisplaySize(roots[0].getFreeSpace()));
		System.out.println("Usable Space: "
				+ FileUtils.byteCountToDisplaySize(roots[0].getUsableSpace()));
		
		System.out.println("File.pathSeparator: "+ File.pathSeparator);
		System.out.println("File.separator: " + File.separator);
		
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

}
