package pei.java.thirdp.lab.guava;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.Test;

import com.google.common.net.InternetDomainName;
import static com.github.peiatgithub.java.utils.Constants.*;


/**
 * 
 * @author pei
 *
 */
public class InternetDomainNameDemo {

	@Test
	public void theStaticMethods() throws Exception {

		assertThat(InternetDomainName.isValid("www.google.com"));
		assertThat(InternetDomainName.isValid("www.google.co.uk"));
		assertThat(InternetDomainName.isValid("en.wikipedia.org"));
		assertThat(InternetDomainName.isValid("commons.apache.org"));
		assertThat(InternetDomainName.isValid("someone.wordpress.com"));
		
		assertThat(InternetDomainName.isValid("wikipedia.org"));
		assertThat(InternetDomainName.isValid("org"));
		
		assertThat(InternetDomainName.isValid("https://www.wikipedia.org")).isFalse();
		assertThat(InternetDomainName.isValid("en.wikipedia.org/wiki/Main_Page")).isFalse();
		
		assertThatThrownBy(()->InternetDomainName.from("https://www.wikipedia.org"))
		.isInstanceOf(IllegalArgumentException.class).hasMessageContaining("Not a valid domain name");

	}
	
	@Test
	public void miscs() throws Exception {
		
		final InternetDomainName d1 = InternetDomainName.from("www.google.com");
		
		assertThat(d1.hasPublicSuffix());
		assertThat(d1.publicSuffix().toString(), is("com"));
		assertThat(d1.isPublicSuffix()).isFalse();
		assertThat(d1.isUnderPublicSuffix());

		assertThat(d1.isTopPrivateDomain()).isFalse();
		assertThat(d1.topPrivateDomain().toString(), is("google.com"));
		
		assertThat(d1.hasParent());
		assertThat(d1.parent().toString(), is("google.com"));

		assertThat(d1.child(STR).toString(), is("str.www.google.com"));
		
		final InternetDomainName d2 = InternetDomainName.from("www.google.co.uk");
		
		assertThat(d2.hasPublicSuffix());
		assertThat(d2.publicSuffix().toString(), is("co.uk"));
		assertThat(d2.isPublicSuffix()).isFalse();
		assertThat(d2.isUnderPublicSuffix());

		assertThat(d2.isTopPrivateDomain()).isFalse();
		assertThat(d2.topPrivateDomain().toString(), is("google.co.uk"));
		
		assertThat(d2.hasParent());
		assertThat(d2.parent().toString(), is("google.co.uk"));

		assertThat(d2.child(STR).toString(), is("str.www.google.co.uk"));

		final InternetDomainName d3 = InternetDomainName.from("en.wikipedia.org");
		
		assertThat(d3.hasPublicSuffix());
		assertThat(d3.publicSuffix().toString(), is("org"));
		assertThat(d3.isPublicSuffix()).isFalse();
		assertThat(d3.isUnderPublicSuffix());
		
		assertThat(d3.isTopPrivateDomain()).isFalse();
		assertThat(d3.topPrivateDomain().toString(), is("wikipedia.org"));
		
		assertThat(d3.hasParent());
		assertThat(d3.parent().toString(), is("wikipedia.org"));
		
		assertThat(d3.child(STR).toString(), is("str.en.wikipedia.org"));

		final InternetDomainName d4 = InternetDomainName.from("wikipedia.org");
		
		assertThat(d4.hasPublicSuffix());
		assertThat(d4.publicSuffix().toString(), is("org"));
		assertThat(d4.isPublicSuffix()).isFalse();
		assertThat(d4.isUnderPublicSuffix());
		
		assertThat(d4.isTopPrivateDomain());
		assertThat(d4.topPrivateDomain().toString(), is("wikipedia.org"));
		
		assertThat(d4.hasParent());
		assertThat(d4.parent().toString(), is("org"));
		
		assertThat(d4.child(STR).toString(), is("str.wikipedia.org"));

		final InternetDomainName d5 = InternetDomainName.from("org");
		
		assertThat(d5.hasPublicSuffix());
		assertThat(d5.publicSuffix().toString(), is("org"));
		assertThat(d5.isPublicSuffix());
		assertThat(d5.isUnderPublicSuffix()).isFalse();
		
		assertThat(d5.isTopPrivateDomain()).isFalse();
		assertThatThrownBy(()->d5.topPrivateDomain()).isInstanceOf(ISE);
		
		assertThat(d5.hasParent()).isFalse();
		assertThatThrownBy(()->d5.parent()).isInstanceOf(ISE);
		
		assertThat(d5.child(STR).toString(), is("str.org"));

	}

}
