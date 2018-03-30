package pei.java.jse.lab.systemAndRuntime;

import static pei.java.jse.lab.utils.Utils.bytesToReadable;

import java.util.Map.Entry;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author pei
 *
 */
@Slf4j
public class SystemAndRuntime {
    
    @Test
    public void system() {
        // demo of system properties
        for (Entry<Object, Object> prop : System.getProperties().entrySet()){
            log.info(prop.toString());
        }
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
        rt.gc();
        log.info("Free Memory AFTER called gc(): {} or {}"
                , FileUtils.byteCountToDisplaySize(rt.freeMemory())
                , bytesToReadable(rt.freeMemory()));        
    }
}
