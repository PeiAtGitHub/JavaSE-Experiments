package pei.java.design.pattern.lab.proxy;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author pei
 *
 */
public class ProxyDemo {
	
	public static void main(String[] args) {
		Image img1 = new RealImage("Photo1"); 
		Image img2 = new ImageProxy("Photo2");
		// so far image2 is not loaded
		img1.displayImage();
		img2.displayImage();
    }
}

//
interface Image {
    void displayImage();
}
//
@Slf4j
class RealImage implements Image {
    private String filename;
 
    public RealImage(String filename) { 
        this.filename = filename;
        log.info("Loading {}", this.filename);
    }
 
    public void displayImage() { 
    	log.info("Displaying {}", this.filename); 
    }
}
//
/*
 * The proxy does not load the image until it needs to be displayed.
 */
class ImageProxy implements Image {
    private String filename;
    private RealImage image;
 
    public ImageProxy(String filename) { 
        this.filename = filename; 
    }
 
    public void displayImage() {
    	if(image == null) {
        	image = new RealImage(this.filename);
    	}
        image.displayImage();
    }
}
