package pei.java.design.pattern.lab.proxy;

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
class RealImage implements Image {
    private String filename;
 
    public RealImage(String filename) { 
        this.filename = filename;
        System.out.println("Loading   " + this.filename);
    }
 
    public void displayImage() { 
        System.out.println("Displaying " + this.filename); 
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
