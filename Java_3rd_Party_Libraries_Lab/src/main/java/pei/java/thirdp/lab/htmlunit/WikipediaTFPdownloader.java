package pei.java.thirdp.lab.htmlunit;

import static org.junit.Assert.*;
import static pei.java.thirdp.lab.utils.Utils.*;

import java.io.File;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.UnexpectedPage;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

/**
 * 
 * A demo of using HtmlUnit to download wikipedia's Today's Featured Picture
 * 
 * @author pei
 *
 */
public class WikipediaTFPdownloader {

    final static String tfpDownloadDir = "downloads" + File.separator + "TFP";

    /**
     * I'v tried several ways. Opening the image's URL in a page and then download
     * is the only way I found so far.
     */
    @Test
    public void testDownLoadTfp() throws Exception {
        
        try(WebClient webClient = new WebClient();) {
            HtmlPage mainPage = webClient.getPage(URL_WIKIPEDIA_MAIN_PAGE);

            // click the image element to open image details page
            HtmlElement tfpElement = mainPage.getHtmlElementById("mp-tfp").getElementsByTagName("img").get(0);
            HtmlPage imageDetailsPage = (HtmlPage) tfpElement.click();

            // the link "Original file"
            HtmlAnchor link = imageDetailsPage.<HtmlAnchor>getFirstByXPath("//a[contains(., 'Original file')]");
            // open the image in a page.
            URL imgUrl = ((UnexpectedPage) link.click()).getUrl();
            //
            String fileName = imgUrl.getPath().substring(imgUrl.getPath().lastIndexOf('/') + 1);
            File file = new File(tfpDownloadDir + File.separator + fileName);
            file.delete();
            assertFalse(file.exists());
            FileUtils.copyURLToFile(imgUrl, file);
            assertTrue(file.exists());
        } 
        
    }
}
