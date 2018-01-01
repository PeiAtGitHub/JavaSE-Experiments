package pei.java.thirdp.lab.mockito;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import pei.java.thirdp.lab.mockito.Event.Type;

/**
 * 
 * @author pei
 *
 */
@AllArgsConstructor
public class ReminderSender {

    private final InvoiceStorage invoiceStorage;
    private final EmailSender emailSender;
    private final EventRecorder eventRecorder;
	
	/**
	 * outstanding invoice: 
	 *   an invoice that has not yet been paid
	 * @param customer
	 */
	public void sendEmailIfHasOutstandingInvoice(Customer customer) {
		 if(invoiceStorage.hasOutstandingInvoice(customer)){
             emailSender.sendEmail(customer);
             eventRecorder.recordEvent(new Event(Type.REMINDER_SENT, 
            		 customer.getFullName(), customer.getId(), LocalDate.now()));
         }
	}
}
