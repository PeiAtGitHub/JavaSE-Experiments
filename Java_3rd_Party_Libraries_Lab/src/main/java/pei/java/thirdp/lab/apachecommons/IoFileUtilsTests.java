package pei.java.thirdp.lab.apachecommons;

import static com.github.peiatgithub.java.utils.Constants.*;

import java.io.File;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author pei
 */
public class IoFileUtilsTests {

    public static final String URL_APACHE_COMMONS_IO_MAIN = "https://commons.apache.org/proper/commons-io/index.html";

    static File dir1 = Paths.get("dir1").toFile();
    static File dir2 = Paths.get("dir2").toFile();
    static File dir21 = Paths.get("dir2", "dir1").toFile();

    static File file11 = Paths.get("dir1", "file1.html").toFile();
    static File file12 = Paths.get("dir1", "file2.txt").toFile();
    static File fileWrite = Paths.get("dir1", "fw.txt").toFile();

    static File file21 = Paths.get("dir2", "file1.html").toFile();
    static File file22 = Paths.get("dir2", "file2.txt").toFile();

    static File file211 = Paths.get("dir2", "dir1", "file1.html").toFile();
    static File file212 = Paths.get("dir2", "dir1", "file2.txt").toFile();


    @Test
    public void testFileUtils() throws Exception {
        /*
         * constants
         */
        assertEquals(1024, FileUtils.ONE_KB);
        assertEquals(1024 * 1024, FileUtils.ONE_MB);
        assertEquals(1024 * 1024 * 1024, FileUtils.ONE_GB);

        /*
         * dir and file manipulation
         */
        FileUtils.deleteDirectory(dir1);
        assertTrue(dir1.mkdir());
        assertThat(dir1.listFiles()).hasSize(0);
        FileUtils.copyURLToFile(new URL(URL_APACHE_COMMONS_IO_MAIN), file11);
        Thread.sleep(1000);
        FileUtils.copyFile(file11, file12, false);
        assertThat(FileUtils.isFileNewer(file12, file11));
        // now: dir1/two identical files
        assertThat(dir1.listFiles()).hasSize(2);
        assertThat(FileUtils.contentEquals(file11, file12));

        FileUtils.copyDirectory(dir1, dir2);
        FileUtils.copyDirectoryToDirectory(dir1, dir2);
        // after copy: dir1, dir2/dir1, each dir contains the two files
        assertThat(FileUtils.directoryContains(dir2, file21));
        assertThat(dir2.list()).hasSize(3);
        // iterate dir
        Iterator<File> htmlFiles = FileUtils.iterateFiles(dir2, new String[] { "html" }, true);
        Collection<File> txtFiles = FileUtils.listFiles(dir2, new String[] { "txt" }, true);
        assertThat(htmlFiles).hasSize(2).allMatch(f->f.getName().endsWith(".html"));
        assertThat(txtFiles).hasSize(2).allMatch(f->f.getName().endsWith(".txt"));

        FileUtils.moveDirectoryToDirectory(dir2, dir1, false);
        // after move: dir1/dir2/dir1
        // after move: file obj won't change! its path won't change
        assertEquals("dir2", dir2.getPath());
        assertFalse(dir2.exists());
        assertEquals(Paths.get("dir2", "dir1", "file1.html").toString(), file211.getPath());
        assertFalse(file211.exists());

        // write, read
        assertThat(FileUtils.readFileToString(file11, Charset.defaultCharset())).as("Commons IO main page has changed")
                .contains("Commons IO 2.6 is the latest version");
        // create a new file under dir1
        FileUtils.writeStringToFile(fileWrite, S1, Charset.defaultCharset(), true);
        FileUtils.writeStringToFile(fileWrite, S2, Charset.defaultCharset(), true);
        assertThat(FileUtils.readLines(fileWrite, Charset.defaultCharset())).containsExactly("S1S2");

        // clean, delete dir
        assertTrue(dir1.exists());
        assertThat(dir1.list()).hasSize(4);
        FileUtils.cleanDirectory(dir1);
        assertTrue(dir1.exists());
        assertThat(dir1.list()).hasSize(0);
        FileUtils.deleteDirectory(dir1);
        assertFalse(dir1.exists());

        /*
         * miscs
         */
        // FileUtils.copyInputStreamToFile(f2);
        // FileUtils.getUserDirectory();
        // FileUtils.getFile(names);
        // Create a filter for ".txt" files
        // IOFileFilter txtSuffixFilter = FileFilterUtils.suffixFileFilter(".txt");
        // IOFileFilter txtFiles = FileFilterUtils.andFileFilter(FileFileFilter.FILE,
        // txtSuffixFilter);
        // Create a filter for either directories or ".txt" files
        // FileFilter filter =
        // FileFilterUtils.orFileFilter(DirectoryFileFilter.DIRECTORY, txtFiles);
        // Copy using the filter
        // FileUtils.copyDirectory(srcDir, destDir, filter);
    }

}
