package pei.java.jse.lab.systemAndRuntime;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.nio.file.Paths;
import java.util.Arrays;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author pei
 *
 */
@Slf4j
public class FileSystemTests {
	
	@Test
	public void fs() {
		File[] roots = File.listRoots();
		assertTrue(roots.length > 0);
		log.info("FS roots: {}", Arrays.toString(roots));
		log.info("Abs Path: {}", roots[0].getAbsolutePath());
		log.info("Total Space: {}", FileUtils.byteCountToDisplaySize(roots[0].getTotalSpace()));
		log.info("Free Space: {}", FileUtils.byteCountToDisplaySize(roots[0].getFreeSpace()));
		log.info("Usable Space: {}", FileUtils.byteCountToDisplaySize(roots[0].getUsableSpace()));
		
		log.info("File.pathSeparator: {}", File.pathSeparator);
		log.info("File.separator: {}", File.separator);
		
	}
	
	
	@Test
	public void thePaths() {
		// the output depends on the OS u run this
		// TODO: i'v never verified this test on windows OS
		if(System.getProperties().getProperty("os.name").matches(".*Linux.*")) {
			assertTrue(Paths.get("/what/ever/path/aFile.txt").getFileName().toString()
					.equals("aFile.txt"));
		}else if(System.getProperties().getProperty("os.name").matches(".*Windows.*")){
			assertTrue(Paths.get("C:\\what\\ever\\path\\aFile.txt").getFileName().toString()
					.equals("aFile.txt"));
		}else {
			fail("The test currently does not support OS besides Linux and Windows.");
		}
	}

}
