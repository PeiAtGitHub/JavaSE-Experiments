package pei.java.thirdp.lab.mockito;

public interface Infrastructures {}


/*
 * Simulated infrastructures:
 */

interface EntityManager{

	Customer find(Class<Customer> class1, Long customerID);

	void persist(Customer customer);

	void flush();
	
}

interface InvoiceStorage{

	boolean hasOutstandingInvoice(Customer customer);
	
	
}

interface EmailSender{

	void sendEmail(Customer customer);
	
}

interface EventRecorder{

	void recordEvent(Event capture);
	
}

