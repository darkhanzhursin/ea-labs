package customers;

import customers.service.customer.CustomerService;
import customers.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	CustomerService customerService;

	@Autowired
	ProductService productService;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		customerService.addCustomer("Frank Brown", "fbrown@acme.com",
				"mainstreet 5", "Chicago", "60613");
		productService.addProduct("Smartphone", "IPhone 15 pro, 512 gb", 1000.0, "fbrown@acme.com");
	}
}

