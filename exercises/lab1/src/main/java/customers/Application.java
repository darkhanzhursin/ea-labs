package customers;

import customers.domain.Product;
import customers.service.customer.CustomerService;
import customers.service.product.ProductService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

	public static void main(String[] args) {

		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

		// CustomerService customerService = (CustomerServiceImpl) context.getBean(CustomerService.class);
		// CustomerService customerService =  context.getBean("customerServiceImpl", CustomerService.class);
		CustomerService customerService = context.getBean(CustomerService.class);
		ProductService productService = context.getBean(ProductService.class);

		customerService.addCustomer("Frank Brown", "fbrown@acme.com",
				"mainstreet 5", "Chicago", "60613");
		productService.addProduct("Smartphone", "IPhone 15 pro, 512 gb", 1000.0, "fbrown@acme.com");
	}
}

