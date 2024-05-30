package customers.repository.customer;

import customers.domain.Customer;
import customers.integration.logging.Logger;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Repository
@Profile("prod")
public class CustomerRepositoryImpl implements CustomerRepository{

	private Logger logger;

	public CustomerRepositoryImpl(Logger logger) {
		this.logger = logger;
	}

	public void save(Customer customer) {
		System.out.println("Prod");
		// simple sleep
		try {
			Thread.sleep(350);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("CustomerRepository: saving customer "+customer.getName());
		logger.log("Customer is saved in the DB: "+ customer.getName() );
	}

}
