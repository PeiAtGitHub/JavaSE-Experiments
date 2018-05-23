package pei.java.thirdp.lab.mockito;

import static com.github.peiatgithub.java.utils.Constants.*;
import static com.github.peiatgithub.java.utils.Utils.*;

import java.util.List;

import static org.mockito.Mockito.*;
import org.mockito.ArgumentMatchers;
import org.apache.commons.lang3.RandomUtils;
import org.junit.*;
import org.mockito.ArgumentCaptor;

import com.google.common.collect.Lists;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import static org.assertj.core.api.Assertions.*;


/**
 * 
 * @author pei
 *
 */
public class MockitoDemos {

	// Classes to be tested
	CustomerDao customerDao;
	ReminderSender reminderSender;
	RegistrationService registrationService;

	// dependent infrastructures (will be mocked)
	EntityManager entityManager;
	EmailSender emailSender;
	InvoiceStorage invoiceStorage;
	EventRecorder eventRecorder;

	//
	long id;
	Customer customer;

	@Before
	public void setup() {
		entityManager = mock(EntityManager.class);
		invoiceStorage = mock(InvoiceStorage.class);
		emailSender = mock(EmailSender.class);
		eventRecorder = mock(EventRecorder.class);

		customerDao = new CustomerDao(entityManager);
		reminderSender = new ReminderSender(invoiceStorage, emailSender, eventRecorder);
		registrationService = new RegistrationService(customerDao, eventRecorder);

		id = randomNumberOfDigit(16);
		customer = new Customer(FIRST_NAME, LAST_NAME);

	}

	@Test
	public void findCustomerHappyPath() {
		when(entityManager.find(Customer.class, id)).thenReturn(customer);
		assertThat(customerDao.findCustomer(id).getFullName(), is(FIRST_NAME + " " + LAST_NAME));
	}

	@Test
	public void notFoundCustomer() {
		when(entityManager.find(Customer.class, id)).thenReturn(null);// non-existent customer id
		assertNull(customerDao.findCustomer(id));
	}

	@Test
	public void sendReminder() {
		when(invoiceStorage.hasOutstandingInvoice(customer)).thenReturn(true);
		reminderSender.sendEmailIfHasOutstandingInvoice(customer);
		verify(emailSender).sendEmail(customer);
		
		ArgumentCaptor<Event> myCaptor = ArgumentCaptor.forClass(Event.class);
		verify(eventRecorder).recordEvent(myCaptor.capture());

		Event sentEvent = myCaptor.getValue();
		assertNotNull(sentEvent.getEventTimestamp());
		assertThat(sentEvent.getType(), is(Event.Type.REMINDER_SENT));
		assertThat(sentEvent.getCustomerFullName(), is(customer.getFullName()));
	}

	@Test
	public void notSendReminder() {
		when(invoiceStorage.hasOutstandingInvoice(customer)).thenReturn(false);
		reminderSender.sendEmailIfHasOutstandingInvoice(customer);
		verify(emailSender, times(0)).sendEmail(customer);
		ArgumentCaptor<Event> myCaptor = ArgumentCaptor.forClass(Event.class);
		verify(eventRecorder, times(0)).recordEvent(myCaptor.capture());
	}

	@Test
	public void saveCustomerHappyPath() {
		doAnswer(invocation -> {
			Customer customer = invocation.getArgument(0);
			customer.setId(id);
			return null;
		}).when(entityManager).persist(ArgumentMatchers.any(Customer.class));

		assertThat(customerDao.saveCustomer(customer).getId(), is(id));
	}

	@Test
	public void saveCustomerNull() {
		assertThatThrownBy(()->customerDao.saveCustomer(null)).isInstanceOf(IAE);
	}

	@Test
	public void regiesterCustomers() {
		List<Customer> customers = Lists.newArrayList(new Customer(FIRST_NAME, LAST_NAME), new Customer(S1, S2),
				new Customer(S1, S2));

		doAnswer(invocation -> {
			Customer customer = invocation.getArgument(0);
			customer.setId(randomNumberOfDigit(16));
			return null;
		}).when(entityManager).persist(ArgumentMatchers.any(Customer.class));

		registrationService.register(customers);

		ArgumentCaptor<Event> myCaptor = ArgumentCaptor.forClass(Event.class);
		verify(eventRecorder, times(customers.size())).recordEvent(myCaptor.capture());

		List<Event> sentEvents = myCaptor.getAllValues();
		assertThat(customers).hasSameSizeAs(sentEvents);
		for (int i = 0; i < sentEvents.size(); i++) {
			Event event = sentEvents.get(i);
			assertNotNull(customers.get(i).getRegistrationDate());
			assertThat(String.valueOf(event.getCustomerId())).hasSize(16);
			assertNotNull(event.getEventTimestamp());
			assertThat(event.getType(), is(Event.Type.REGISTRATION));
			assertThat(event.getCustomerFullName(), is(customers.get(i).getFullName()));
		}
	}

}
