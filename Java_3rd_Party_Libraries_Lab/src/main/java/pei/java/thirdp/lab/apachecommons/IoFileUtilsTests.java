package pei.java.thirdp.lab.apachecommons;

import static com.github.peiatgithub.java.utils.Constants.*;

import java.io.File;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * 
 * @author pei
 *
 */
public class IoFileUtilsTests {

    public static final String URL_APACHE_COMMONS_IO_MAIN = "https://commons.apache.org/proper/commons-io/index.html";

    static File dir1 = new File("dir1");
    static File dir2 = new File("dir2");
    static File dir21 = new File("dir2/dir1");

    static File file11 = new File("dir1/file1.html");
    static File file12 = new File("dir1/file2.txt");
    static File fileWrite = new File("dir1/fw.txt");

    static File file21 = new File("dir2/file1.html");
    static File file22 = new File("dir2/file2.txt");

    static File file211 = new File("dir2/dir1/file1.html");
    static File file212 = new File("dir2/dir1/file2.txt");


    @Test
    public void testFileUtils() throws Exception {
        /*
         * constants
         */
        assertThat(FileUtils.ONE_KB).isEqualTo(1024);
        assertThat(FileUtils.ONE_MB).isEqualTo(1024 * 1024);
        assertThat(FileUtils.ONE_GB).isEqualTo(1024 * 1024 * 1024);

        /*
         * dir and file manipulation
         */
        FileUtils.deleteDirectory(dir1);
        assertThat(dir1.mkdir());
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
        assertThat(dir2.getPath(), is("dir2"));
        assertThat(dir2.exists()).isFalse();
        assertThat(file211.getPath(), is("dir2/dir1/file1.html"));
        assertThat(file211.exists()).isFalse();

        // write, read
        assertThat(FileUtils.readFileToString(file11, Charset.defaultCharset())).as("Commons IO main page has changed")
                .contains("Commons IO 2.6 is the latest version");
        // create a new file under dir1
        FileUtils.writeStringToFile(fileWrite, S1, Charset.defaultCharset(), true);
        FileUtils.writeStringToFile(fileWrite, S2, Charset.defaultCharset(), true);
        assertThat(FileUtils.readLines(fileWrite, Charset.defaultCharset())).containsExactly("S1S2");

        // clean, delete dir
        assertThat(dir1.exists());
        assertThat(dir1.list()).hasSize(4);
        FileUtils.cleanDirectory(dir1);
        assertThat(dir1.exists());
        assertThat(dir1.list()).hasSize(0);
        FileUtils.deleteDirectory(dir1);
        assertThat(dir1.exists()).isFalse();

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
