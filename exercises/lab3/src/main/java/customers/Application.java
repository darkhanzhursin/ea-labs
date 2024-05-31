package customers;

import customers.dao.CustomerRepository;
import customers.dao.ProductRepository;
import customers.domain.CreditCard;
import customers.domain.Customer;
import customers.domain.Product;
import customers.domain.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;

@SpringBootApplication
public class Application implements CommandLineRunner {

	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private ProductRepository productRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		customerRepository.clearDB();
		productRepository.clearDB();
		Customer customer = new Customer(101,"John doe", "johnd@acme.com", "0622341678");
		CreditCard creditCard = new CreditCard("12324564321", "Visa", "11/23");
		customer.setCreditCard(creditCard);
		customerRepository.save(customer);
		customer = new Customer(66,"James Johnson", "jj123@acme.com", "068633452");
		creditCard = new CreditCard("99876549876", "MasterCard", "01/24");
		customer.setCreditCard(creditCard);
		customerRepository.save(customer);
		System.out.println(customerRepository.getCustomer(101));
		System.out.println(customerRepository.getCustomer(66));
		System.out.println("-----------All customers ----------------");
		System.out.println(customerRepository.getAllCustomers());

		Supplier supplier = new Supplier("Supplier1", "319-533-12-12");
		Product product1 = new Product(12, "Product1", new BigDecimal(100.00));
		product1.setSupplier(supplier);
		Product product2 = new Product(14, "Product2", new BigDecimal(101.00));
		supplier = new Supplier("Supplier2", "123-231-33-22");
		product2.setSupplier(supplier);
		productRepository.save(product1);
		productRepository.save(product2);
		System.out.println(productRepository.getAllProducts());
		System.out.println("Find by name: " + productRepository.findByProductName("Product1"));
		System.out.println("Find by number: " + productRepository.findByProductNumber(14));
		productRepository.removeProduct(14);
		System.out.println(productRepository.getAllProducts());
	}

}
