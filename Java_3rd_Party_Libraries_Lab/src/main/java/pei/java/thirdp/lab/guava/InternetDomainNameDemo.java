package pei.java.thirdp.lab.guava;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.google.common.net.InternetDomainName;
import static com.github.peiatgithub.java.utils.Constants.*;


/**
 * @author pei
 */
public class InternetDomainNameDemo {

	@Test
	public void theStaticMethods() throws Exception {

	    assertTrue(InternetDomainName.isValid("www.google.com"));
	    assertTrue(InternetDomainName.isValid("www.google.co.uk"));
	    assertTrue(InternetDomainName.isValid("en.wikipedia.org"));
	    assertTrue(InternetDomainName.isValid("commons.apache.org"));
	    assertTrue(InternetDomainName.isValid("someone.wordpress.com"));
		
	    assertTrue(InternetDomainName.isValid("wikipedia.org"));
	    assertTrue(InternetDomainName.isValid("org"));
		
	    assertFalse(InternetDomainName.isValid("https://www.wikipedia.org"));
	    assertFalse(InternetDomainName.isValid("en.wikipedia.org/wiki/Main_Page"));
		
		assertThatThrownBy(()->InternetDomainName.from("https://www.wikipedia.org"))
		.isInstanceOf(IllegalArgumentException.class).hasMessageContaining("Not a valid domain name");

	}
	
	@Test
	public void miscs() throws Exception {
		
		final InternetDomainName d1 = InternetDomainName.from("www.google.com");
		
		assertThat(d1.hasPublicSuffix());
		assertEquals("com", d1.publicSuffix().toString());
		assertFalse(d1.isPublicSuffix());
		assertThat(d1.isUnderPublicSuffix());

		assertFalse(d1.isTopPrivateDomain());
		assertEquals("google.com", d1.topPrivateDomain().toString());
		
		assertThat(d1.hasParent());
		assertEquals("google.com", d1.parent().toString());

		assertEquals("str.www.google.com", d1.child(STR).toString());
		
		final InternetDomainName d2 = InternetDomainName.from("www.google.co.uk");
		
		assertThat(d2.hasPublicSuffix());
		assertEquals("co.uk", d2.publicSuffix().toString());
		assertFalse(d2.isPublicSuffix());
		assertThat(d2.isUnderPublicSuffix());

		assertFalse(d2.isTopPrivateDomain());
		assertEquals("google.co.uk", d2.topPrivateDomain().toString());
		
		assertThat(d2.hasParent());
		assertEquals("google.co.uk", d2.parent().toString());

		assertEquals("str.www.google.co.uk", d2.child(STR).toString());

		final InternetDomainName d3 = InternetDomainName.from("en.wikipedia.org");
		
		assertThat(d3.hasPublicSuffix());
		assertEquals("org", d3.publicSuffix().toString());
		assertFalse(d3.isPublicSuffix());
		assertThat(d3.isUnderPublicSuffix());
		
		assertFalse(d3.isTopPrivateDomain());
		assertEquals("wikipedia.org", d3.topPrivateDomain().toString());
		
		assertThat(d3.hasParent());
		assertEquals("wikipedia.org", d3.parent().toString());
		
		assertEquals("str.en.wikipedia.org", d3.child(STR).toString());

		final InternetDomainName d4 = InternetDomainName.from("wikipedia.org");
		
		assertThat(d4.hasPublicSuffix());
		assertEquals("org", d4.publicSuffix().toString());
		assertFalse(d4.isPublicSuffix());
		assertThat(d4.isUnderPublicSuffix());
		
		assertThat(d4.isTopPrivateDomain());
		assertEquals("wikipedia.org", d4.topPrivateDomain().toString());
		
		assertThat(d4.hasParent());
		assertEquals("org", d4.parent().toString());
		
		assertEquals("str.wikipedia.org", d4.child(STR).toString());

		final InternetDomainName d5 = InternetDomainName.from("org");
		
		assertThat(d5.hasPublicSuffix());
		assertEquals("org", d5.publicSuffix().toString());
		assertThat(d5.isPublicSuffix());
		assertFalse(d5.isUnderPublicSuffix());
		
		assertFalse(d5.isTopPrivateDomain());
		assertThatThrownBy(()->d5.topPrivateDomain()).isInstanceOf(ISE);
		
		assertFalse(d5.hasParent());
		assertThatThrownBy(()->d5.parent()).isInstanceOf(ISE);
		
		assertEquals("str.org", d5.child(STR).toString());

	}

}
