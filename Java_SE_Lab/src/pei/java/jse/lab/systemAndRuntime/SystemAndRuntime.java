package pei.java.jse.lab.systemAndRuntime;

import static com.github.peiatgithub.java.utils.Constants.UNSUPPORTED_CASE;
import static com.github.peiatgithub.java.utils.Utils.*;

import java.io.File;
import java.nio.file.Paths;
import java.util.Arrays;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.assertj.core.api.Assertions.*;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.SystemUtils;
import org.junit.Assert;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author pei
 *
 */
@Slf4j
public class SystemAndRuntime {
	
    private static final String FILE_NAME = "aFile.txt";
    
    @Test
    public void systemPropsDemo() {
        System.getProperties().entrySet().forEach(prop -> log.info(prop.toString()));
    }

    @Test
    public void runtime() {
        Runtime rt = Runtime.getRuntime();
        log.info("Max Memory: {} or {}" 
                , FileUtils.byteCountToDisplaySize(rt.maxMemory())
                , bytesToReadable(rt.maxMemory()));
        log.info("Total Memory: {} or {}"
                , FileUtils.byteCountToDisplaySize(rt.totalMemory())
                , bytesToReadable(rt.totalMemory()));
        log.info("Free Memory: {} or {}"
                , FileUtils.byteCountToDisplaySize(rt.freeMemory())
                , bytesToReadable(rt.freeMemory()));
        log.info("Available Processors: {}", rt.availableProcessors());
        //
        rt.gc();
        log.info("Free Memory AFTER called gc(): {} or {}"
                , FileUtils.byteCountToDisplaySize(rt.freeMemory())
                , bytesToReadable(rt.freeMemory()));        
    }
    

	@Test
    public void fileSystem() {
        File[] roots = File.listRoots();
        assertThat(roots.length > 0);
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
    	
		if(SystemUtils.IS_OS_LINUX) {
            assertThat(Paths.get("/what/ever/path/" + FILE_NAME).getFileName().toString(), is(FILE_NAME));
        }else if(SystemUtils.IS_OS_WINDOWS){
            assertThat(Paths.get("C:\\what\\ever\\path\\" + FILE_NAME).getFileName().toString(), is(FILE_NAME));
        }else {
        	Assert.fail(UNSUPPORTED_CASE);
        }
		
    }

}
