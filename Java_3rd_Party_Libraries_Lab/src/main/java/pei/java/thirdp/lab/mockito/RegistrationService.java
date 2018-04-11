package pei.java.thirdp.lab.mockito;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pei.java.thirdp.lab.mockito.Event.Type;

/**
 * 
 * @author pei
 *
 */
@Slf4j @AllArgsConstructor
public class RegistrationService {

    CustomerDao customerDao;
    EventRecorder eventRecorder;

    /**
     * 
     * @param customers
     */
    public void register(List<Customer> customers) {
        for (Customer customer : customers) {
            customer.setRegistrationDate(LocalDate.now());
            customerDao.saveCustomer(customer);
            eventRecorder.recordEvent(
                    new Event(Type.REGISTRATION, customer.getFullName()
                            , customer.getId(), LocalDate.now()));
            log.info("Registered customer {} with ID {} at {}",
                    customer.getFullName(), customer.getId(), 
                    customer.getRegistrationDate().toString());
        }
        
    }
}
