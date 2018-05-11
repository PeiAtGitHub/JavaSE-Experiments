package pei.java.thirdp.lab.guava;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.Test;

import com.google.common.net.InternetDomainName;
import static com.github.peiatgithub.java.utils.Utils.*;
import static com.github.peiatgithub.java.utils.Constants.*;


/**
 * 
 * @author pei
 *
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
		
		Throwable e = catchThrowable(()->InternetDomainName.from("https://www.wikipedia.org"));
		assertThat(e, instanceOf(IllegalArgumentException.class));
		assertThat(e.getMessage(), containsString("Not a valid domain name"));

	}
	
	@Test
	public void miscs() throws Exception {
		
		final InternetDomainName d1 = InternetDomainName.from("www.google.com");
		
		assertThat(d1.hasPublicSuffix(), is(true));
		assertThat(d1.publicSuffix().toString(), is("com"));
		assertThat(d1.isPublicSuffix(), is(false));
		assertThat(d1.isUnderPublicSuffix(), is(true));

		assertThat(d1.isTopPrivateDomain(), is(false));
		assertThat(d1.topPrivateDomain().toString(), is("google.com"));
		
		assertThat(d1.hasParent(), is(true));
		assertThat(d1.parent().toString(), is("google.com"));

		assertThat(d1.child(STR).toString(), is("str.www.google.com"));
		
		final InternetDomainName d2 = InternetDomainName.from("www.google.co.uk");
		
		assertThat(d2.hasPublicSuffix(), is(true));
		assertThat(d2.publicSuffix().toString(), is("co.uk"));
		assertThat(d2.isPublicSuffix(), is(false));
		assertThat(d2.isUnderPublicSuffix(), is(true));

		assertThat(d2.isTopPrivateDomain(), is(false));
		assertThat(d2.topPrivateDomain().toString(), is("google.co.uk"));
		
		assertThat(d2.hasParent(), is(true));
		assertThat(d2.parent().toString(), is("google.co.uk"));

		assertThat(d2.child(STR).toString(), is("str.www.google.co.uk"));

		final InternetDomainName d3 = InternetDomainName.from("en.wikipedia.org");
		
		assertThat(d3.hasPublicSuffix(), is(true));
		assertThat(d3.publicSuffix().toString(), is("org"));
		assertThat(d3.isPublicSuffix(), is(false));
		assertThat(d3.isUnderPublicSuffix(), is(true));
		
		assertThat(d3.isTopPrivateDomain(), is(false));
		assertThat(d3.topPrivateDomain().toString(), is("wikipedia.org"));
		
		assertThat(d3.hasParent(), is(true));
		assertThat(d3.parent().toString(), is("wikipedia.org"));
		
		assertThat(d3.child(STR).toString(), is("str.en.wikipedia.org"));

		final InternetDomainName d4 = InternetDomainName.from("wikipedia.org");
		
		assertThat(d4.hasPublicSuffix(), is(true));
		assertThat(d4.publicSuffix().toString(), is("org"));
		assertThat(d4.isPublicSuffix(), is(false));
		assertThat(d4.isUnderPublicSuffix(), is(true));
		
		assertThat(d4.isTopPrivateDomain(), is(true));
		assertThat(d4.topPrivateDomain().toString(), is("wikipedia.org"));
		
		assertThat(d4.hasParent(), is(true));
		assertThat(d4.parent().toString(), is("org"));
		
		assertThat(d4.child(STR).toString(), is("str.wikipedia.org"));

		final InternetDomainName d5 = InternetDomainName.from("org");
		
		assertThat(d5.hasPublicSuffix(), is(true));
		assertThat(d5.publicSuffix().toString(), is("org"));
		assertThat(d5.isPublicSuffix(), is(true));
		assertThat(d5.isUnderPublicSuffix(), is(false));
		
		assertThat(d5.isTopPrivateDomain(), is(false));
		assertThat(catchThrowable(()->d5.topPrivateDomain()), instanceOf(IllegalStateException.class));
		
		assertThat(d5.hasParent(), is(false));
		assertThat(catchThrowable(()->d5.parent()), instanceOf(IllegalStateException.class));
		
		assertThat(d5.child(STR).toString(), is("str.org"));

	}

}
