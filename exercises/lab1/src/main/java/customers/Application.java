package customers;

import customers.service.CustomerService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

	public static void main(String[] args) {

		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

		// CustomerService customerService = (CustomerServiceImpl) context.getBean(CustomerService.class);
		// CustomerService customerService =  context.getBean("customerServiceImpl", CustomerService.class);
		CustomerService customerService = context.getBean(CustomerService.class);

		customerService.addCustomer("Frank Brown", "fbrown@acme.com",
				"mainstreet 5", "Chicago", "60613");
	}
}

