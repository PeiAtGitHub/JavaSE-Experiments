package pei.java.thirdp.lab.apachecommons;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.io.File;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

/**
 * 
 * @author pei
 *
 */
public class IoFileUtilsTests {

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

	static final String ACIO_MAIN_PAGE = "https://commons.apache.org/proper/commons-io/index.html";

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
		assertTrue(dir1.listFiles().length == 0);
		FileUtils.copyURLToFile(new URL(ACIO_MAIN_PAGE), file11);
		Thread.sleep(1000);
		FileUtils.copyFile(file11, file12, false);
		assertTrue(FileUtils.isFileNewer(file12, file11));
		// now: dir1/two identical files
		assertTrue(dir1.listFiles().length == 2);
		assertTrue(FileUtils.contentEquals(file11, file12));

		FileUtils.copyDirectory(dir1, dir2);
		FileUtils.copyDirectoryToDirectory(dir1, dir2);
		// after copy: dir1, dir2/dir1, each dir contains the two files
		assertTrue(FileUtils.directoryContains(dir2, file21));
		assertTrue(dir2.list().length == 3);
		// iterate dir
		Iterator<File> htmlFiles = FileUtils.iterateFiles(dir2, new String[] { "html" }, true);
		Collection<File> txtFiles = FileUtils.listFiles(dir2, new String[] { "txt" }, true);
		int count = 0;
		while (htmlFiles.hasNext()) {
			assertThat(((File) htmlFiles.next()).getName(), endsWith(".html"));
			count++;
		}
		assertThat(count, is(2));
		assertThat(txtFiles.size(), is(2));
		for (File file : txtFiles) {
			assertThat(file.getName(), endsWith(".txt"));
		}

		FileUtils.moveDirectoryToDirectory(dir2, dir1, false);
		// after move: dir1/dir2/dir1
		// after move: file obj won't change! its path won't change
		assertThat(dir2.getPath(), is("dir2"));
		assertFalse(dir2.exists());
		assertThat(file211.getPath(), is("dir2/dir1/file1.html"));
		assertFalse(file211.exists());

		// write, read
		assertTrue("Commons IO main page has changed", FileUtils.readFileToString(file11, Charset.defaultCharset())
				.contains("Commons IO 2.6 is the latest version"));
		// create a new file under dir1
		FileUtils.writeStringToFile(fileWrite, "HELLO", Charset.defaultCharset(), true);
		FileUtils.writeStringToFile(fileWrite, "WORLD", Charset.defaultCharset(), true);
		assertThat(FileUtils.readLines(fileWrite, Charset.defaultCharset()).toString(), is("[HELLOWORLD]"));

		// clean, delete dir
		assertTrue(dir1.exists());
		assertTrue(dir1.list().length == 4);
		FileUtils.cleanDirectory(dir1);
		assertTrue(dir1.exists());
		assertTrue(dir1.list().length == 0);
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
