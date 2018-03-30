package pei.java.thirdp.lab.mockito;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author pei
 *
 */
@Slf4j @AllArgsConstructor
public class CustomerDao {

    EntityManager entityManager;

    
    /**
     * 
     * @param customerID
     * @return
     */
    public Customer findCustomer(Long customerID) {
        return entityManager.find(Customer.class, customerID);
    }

    /**
     * 
     * @param customer
     * @return
     */
    public Customer saveCustomer(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException();
        }
        entityManager.persist(customer); // implicitly assign id to customer
        entityManager.flush();
        
        log.info("Saved customer {} with id {}", customer.getFullName(), customer.getId());
        
        return customer; 
    }

}
