package pei.java.thirdp.lab.emails;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Session;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.junit.Test;

import com.dumbster.smtp.SimpleSmtpServer;
import com.dumbster.smtp.SmtpMessage;


/**
 * Dumbster: "a simple fake SMTP server for unit testing"
 * 
 * @author pei
 *
 */
public class JavaMailAndDumbster {
	
	private static final String SUBJECT = "java mail test subject";
	private static final String BODY = "java mail test body";
	private static final String FROM  = "sender@here.com";
	private static final String TO = "receiver@there.com";
	private static final String CC = "ccReceiver@there.com";
	
	@Test
	public void testJavaMailAndDumbster() throws Exception {
		
		try (SimpleSmtpServer server = SimpleSmtpServer.start(SimpleSmtpServer.AUTO_SMTP_PORT)) {
			
			sendEmail("smtp", "localhost", server.getPort());
			
			List<SmtpMessage> emails = server.getReceivedEmails();
			assertThat(emails.size(), is(1));

			SmtpMessage email = emails.get(0);
			System.out.format("A demo of all email headers: %n%s", email.getHeaderNames());

			assertThat(email.getBody(), is(BODY));
			assertThat(email.getHeaderValue("Subject"), is(SUBJECT));
			assertThat(email.getHeaderValue("To"), is(TO));
			assertThat(email.getHeaderValue("From"), is(FROM));
			assertThat(email.getHeaderValue("Cc"), is(CC));
		}

	}


	private void sendEmail(String protocol, String host, int port) throws AddressException, MessagingException {

		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);
		props.put("mail.transport.protocol", protocol);

		//
		Message msg = new MimeMessage(Session.getInstance(props, null));
		msg.setFrom(new InternetAddress(FROM));
		msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(TO, false));
		msg.setRecipients(Message.RecipientType.CC, InternetAddress.parse(CC, false));
		msg.setSubject(SUBJECT);
		msg.setText(BODY);
		msg.setSentDate(new Date());
		//
		Transport.send(msg);
		System.out.format("Message sent to host %s, port %s, protocol %s%n"
				,host, port, protocol);


	}

}