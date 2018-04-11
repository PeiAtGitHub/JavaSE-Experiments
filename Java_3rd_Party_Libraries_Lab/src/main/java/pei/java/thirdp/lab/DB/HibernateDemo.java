package pei.java.thirdp.lab.DB;

import java.util.List;
import java.util.Random;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import static pei.java.thirdp.lab.DB.DbUtils.*;

/**
 * 
 * @author pei
 *
 */
public class HibernateDemo {

    public static void main(String[] args) {
        
    	try(SessionFactory factory = getSessionFactory(HIBERNATE_CONFIG_FILE)){

    		Integer customerID = null;
    		String customerName = "Alfreds Futterkiste";
    		Customer theCustomer = Customer.builder().customerName(customerName).contactName("Maria Anders")
    		    .address("Obere Str. 57").city("Berlin").postalCode("12209").country("Germany").build();

    		// list customers
    		List<Customer> customers = listCustomers(factory);
    		boolean hasTheCustomer = false;
    		System.out.println("Printing customers in DB:");
    		for (Customer customer : customers) {
    			System.out.println(customer.toString());
    			if (customer.equals(theCustomer)) {
    				hasTheCustomer = true;
    				customerID = customer.getId();
    			}
    		}
    		// add or update customer
    		if (hasTheCustomer) {
    			System.out.format("Customer %s Exists.%n", customerName);
    			System.out.println("Gonna update postal code to 12345.");
    			updateCustomerPostalCode(customerID, factory, "12345");
    		} else {
    			System.out.format("Customer %s NOT exists.%n", customerName);
    			customerID = addCustomer(factory, theCustomer);
    			System.out.format("Customer %s Inserted. ID is %d.%n", customerName, customerID);
    		}

    		// delete customer
    		if (new Random().nextBoolean()) {
    			System.out.format("Gonna delete Customer %s from DB.%n", customerID);
    			deleteCustomer(customerID, factory);
    		}else {
    			System.out.format("NOT gonna delete Customer %s from DB.%n", customerID);
    		}
    		
    		// list customer 
    		customers = listCustomers(factory);
    		System.out.println("Printing customers in DB again:");
    		for (Customer customer : customers) {
    			System.out.println(customer.toString());
    		}
    	}

    }
    
    public static SessionFactory getSessionFactory(String res) {
    	SessionFactory factory;
    	try {
    		factory = new Configuration().configure(res).buildSessionFactory();
    	} catch (Throwable e) {
    		System.err.println("Failed to create sessionFactory object." + e);
    		throw new ExceptionInInitializerError(e);
    	}
    	return factory;
    }


    public static Integer addCustomer(SessionFactory factory, Customer customer) {
        Transaction tx = null;
        Integer id = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            id = (Integer) session.save(customer);
            tx.commit();
        } catch (HibernateException e) {
            rollBackTx(tx);
            e.printStackTrace();
        }
        return id;
    }

    public static List<Customer> listCustomers(SessionFactory factory) {
        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            List<Customer> customers = session.createQuery("FROM Customer").list();
            tx.commit();
            return customers;
        } catch (HibernateException e) {
            rollBackTx(tx);
            e.printStackTrace();
            return null;
        }
    }

    public static void updateCustomerPostalCode(Integer id, SessionFactory factory, String newPostalCode) {
        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            Customer customer = (Customer) session.get(Customer.class, id);
            customer.setPostalCode(newPostalCode);
            session.update(customer);
            tx.commit();
        } catch (HibernateException e) {
            rollBackTx(tx);
            e.printStackTrace();
        }

    }

    public static void deleteCustomer(Integer id, SessionFactory factory) {
        Transaction tx = null;
        try (Session session = factory.openSession()) {
            tx = session.beginTransaction();
            Customer customer = (Customer) session.get(Customer.class, id);
            session.delete(customer);
            tx.commit();
        } catch (HibernateException e) {
            rollBackTx(tx);
            e.printStackTrace();
        }
    }

    private static void rollBackTx(Transaction tx) {
        if (tx != null) {
            tx.rollback();
        }
    }
    
}
