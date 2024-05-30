package customers.repository.customer;

import customers.domain.Customer;
import customers.integration.logging.Logger;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
@Profile("test")
public class CustomerDAOMock implements CustomerRepository {

    private Logger logger;
    private Map<String, Customer> customerMap;

    public CustomerDAOMock(Logger logger) {
        this.logger = logger;
        this.customerMap = new HashMap<>();
    }

    @Override
    public void save(Customer customer) {
        System.out.println("Test");
        try {
            Thread.sleep(100); // reduced sleep time for testing purposes
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        customerMap.put(customer.getName(), customer);
        System.out.println("CustomerDAOMock: saving customer " + customer.getName());
        logger.log("Customer is saved in the mock DB: " + customer.getName());
    }

    public Customer findByName(String name) {
        return customerMap.get(name);
    }

    public void clear() {
        customerMap.clear();
    }
}
