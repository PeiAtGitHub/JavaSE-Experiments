package pei.java.thirdp.lab.apachecommons;

import java.io.File;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.comparator.NameFileComparator;
import org.apache.commons.io.comparator.SizeFileComparator;
import org.junit.jupiter.api.Test;

import lombok.extern.slf4j.Slf4j;
import static com.github.peiatgithub.java.utils.Utils.*;

/**
 * @author pei
 */
@Slf4j
public class IoMiscs {

    @Test
    public void testComparators() throws Exception {

        Collection<File> files = FileUtils.listFiles(new File("downloads"), null, true);

        Collections.sort((List<File>) files, new NameFileComparator());
        log.info("---Downloaded files after sorting by NAME:---");
        files.forEach(f -> log.info(f.getName()));

        Collections.sort((List<File>) files, new SizeFileComparator());
        log.info("---Downloaded files after sorting by SIZE:---");
        files.forEach(f -> log.info("{} or {} ({}):{}", FileUtils.byteCountToDisplaySize(FileUtils.sizeOf(f)),
                bytesToReadable(FileUtils.sizeOf(f)), FileUtils.sizeOf(f), f.getName()));
    }
}
