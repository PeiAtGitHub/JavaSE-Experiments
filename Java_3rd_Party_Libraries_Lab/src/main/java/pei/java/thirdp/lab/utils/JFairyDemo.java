package pei.java.thirdp.lab.utils;

import static com.devskiller.jfairy.producer.person.PersonProperties.male;
import static com.devskiller.jfairy.producer.person.PersonProperties.minAge;
import static com.devskiller.jfairy.producer.person.PersonProperties.withCompany;

import com.devskiller.jfairy.Fairy;
import com.devskiller.jfairy.producer.DateProducer;
import com.devskiller.jfairy.producer.company.Company;
import com.devskiller.jfairy.producer.net.NetworkProducer;
import com.devskiller.jfairy.producer.payment.CreditCard;
import com.devskiller.jfairy.producer.text.TextProducer;

/**
 * "Java fake data generator"
 * 
 * @author pei
 *
 */
public class JFairyDemo {
	
	public static void main(String[] args) {
		
		Fairy fairy = Fairy.create();
		System.out.println("********************");
		com.devskiller.jfairy.producer.person.Person person = fairy.person();
		System.out.println(person.getFullName());            
		System.out.println(person.getEmail());               
		System.out.println(person.getTelephoneNumber());     
		//
		System.out.println("********************");
		person = fairy.person(male(), minAge(21));
		System.out.println(person.isMale());           
		System.out.println(person.getDateOfBirth());
		//
		Company company = fairy.company();
		System.out.println("********************");
		System.out.println(company.getName());          
		System.out.println(company.getUrl());           

		System.out.println("********************");
		person = fairy.person(withCompany(company));
		System.out.println(person.getFullName());     
		System.out.println(person.getCompanyEmail());
		//
		System.out.println("********************");
		CreditCard cc = fairy.creditCard();
		System.out.println(cc.getVendor());
		System.out.println(cc.getExpiryDate());
		//
		System.out.println("********************");
		DateProducer dp = fairy.dateProducer();
		System.out.println(dp.randomDateBetweenYears(1900,2000));
		//
		System.out.println("********************");
		NetworkProducer np = fairy.networkProducer();
		System.out.println(np.ipAddress());
		System.out.println(np.url(true));
		System.out.println(np.url(false));
		
		System.out.println("********************");
		TextProducer tp = fairy.textProducer();
		System.out.println(tp.latinSentence(10));
		System.out.println(tp.loremIpsum());
		System.out.println(tp.paragraph());
		System.out.println(tp.sentence());
		System.out.println(tp.randomString(10));
		System.out.println(tp.word());
		System.out.println(tp.text());
		
	}
}
