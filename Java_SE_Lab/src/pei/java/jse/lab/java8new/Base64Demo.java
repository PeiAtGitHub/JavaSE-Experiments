package pei.java.jse.lab.java8new;

import java.util.Base64;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author pei
 *
 */
@Slf4j
public class Base64Demo {
	
		public static final String CHAR_SET = "utf-8";
		public static final String ORIGINAL_TEXT = "OriginalText";

		@Test
		public void b64() throws Exception {

			String b64encoded = Base64.getEncoder().encodeToString(ORIGINAL_TEXT.getBytes(CHAR_SET));
			log.info("Base64 Encoded String (Basic) :{}", b64encoded);
			log.info("Decoded: {}", new String(Base64.getDecoder().decode(b64encoded), CHAR_SET));
			log.info("Base64 Encoded String (URL) :{}", 
					Base64.getUrlEncoder().encodeToString(ORIGINAL_TEXT.getBytes(CHAR_SET)));
			log.info("Base64 Encoded String (MIME) :{}",
					Base64.getMimeEncoder().encodeToString(ORIGINAL_TEXT.getBytes(CHAR_SET)));
		}


}
