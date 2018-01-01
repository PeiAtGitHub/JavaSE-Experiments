package pei.java.thirdp.lab.mockito;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author pei
 *
 */
@Setter @Getter @AllArgsConstructor
public class Event {

	public enum Type {REMINDER_SENT, REGISTRATION, INVOICE_ISSUED, PAYMENT, SETTLEMENT};
	
	Type type;
	String customerFullName;
	long customerId;
	LocalDate eventTimestamp;
}
