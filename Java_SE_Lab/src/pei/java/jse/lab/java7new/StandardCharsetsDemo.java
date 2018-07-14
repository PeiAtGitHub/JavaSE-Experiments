package pei.java.jse.lab.java7new;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @author pei
 */
public class StandardCharsetsDemo {

    public static void main(String[] args) {

        // StandardCharsets is new in Java 7
        System.out.println(StandardCharsets.ISO_8859_1.name());
        System.out.println(StandardCharsets.US_ASCII.name());
        System.out.println(StandardCharsets.UTF_16.name());
        System.out.println(StandardCharsets.UTF_16BE.name());
        System.out.println(StandardCharsets.UTF_16LE.name());
        System.out.println(StandardCharsets.UTF_8.name());
        // Charset is available since 1.4
        System.out.println(Charset.isSupported(StandardCharsets.US_ASCII.name()));
        System.out.println(Charset.availableCharsets());
        System.out.println(Charset.defaultCharset());

    }
}
