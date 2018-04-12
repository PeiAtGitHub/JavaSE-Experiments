package pei.java.design.pattern.lab.proxy;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author pei
 *
 */
public class ProxyDemo {
    
    public static void main(String[] args) {
        RealImage.builder().filename("Photo1").build().displayImage();
        ImageProxy.builder().filename("Photo2").build().displayImage();
    }
}

//
interface Image {
    void displayImage();
}
//
@Slf4j @AllArgsConstructor @Builder
class RealImage implements Image {
    private String filename;
 
    public void displayImage() { 
        log.info("Displaying {}", this.filename); 
    }
}
//
/*
 * The proxy does not load the image until it needs to be displayed.
 */
@AllArgsConstructor @Builder
class ImageProxy implements Image {
    private String filename;
    private RealImage image;
 
    public void displayImage() {
        if(image == null) {
            image = new RealImage(this.filename);
        }
        image.displayImage();
    }
}
