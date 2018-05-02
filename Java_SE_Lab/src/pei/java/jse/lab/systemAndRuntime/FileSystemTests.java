package pei.java.jse.lab.systemAndRuntime;

import static org.junit.Assert.*;
import static pei.java.jse.lab.utils.Utils.*;
import static org.hamcrest.CoreMatchers.*;

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
        log.info("Total Space: {} or {}"
                , FileUtils.byteCountToDisplaySize(roots[0].getTotalSpace())
                , bytesToReadable(roots[0].getTotalSpace()));
        log.info("Free Space: {} or {}"
                , FileUtils.byteCountToDisplaySize(roots[0].getFreeSpace())
                , bytesToReadable(roots[0].getFreeSpace()));
        log.info("Usable Space: {} or {}"
                , FileUtils.byteCountToDisplaySize(roots[0].getUsableSpace())
                , bytesToReadable(roots[0].getUsableSpace()));
        
        log.info("File.pathSeparator: {}", File.pathSeparator);
        log.info("File.separator: {}", File.separator);
        
    }
    
    
    @Test
    public void thePaths() {
        // the output depends on the OS u run this
        // TODO: i'v never verified this test on windows OS
        String sysPropertyOsName = System.getProperties().getProperty("os.name");
        
		if(sysPropertyOsName.matches(".*Linux.*")) {
            assertThat(Paths.get("/what/ever/path/aFile.txt").getFileName().toString(), is("aFile.txt"));
        }else if(sysPropertyOsName.matches(".*Windows.*")){
            assertThat(Paths.get("C:\\what\\ever\\path\\aFile.txt").getFileName().toString(), is("aFile.txt"));
        }else {
            fail("The test currently does not support OS besides Linux and Windows.");
        }
		
    }

}
