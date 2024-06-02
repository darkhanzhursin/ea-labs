package app;

import domain.CreditCard;
import domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import repository.CustomerRepository;

import java.util.Optional;

@SpringBootApplication
@EnableJpaRepositories("repository")
@EntityScan("domain")
public class Application implements CommandLineRunner {

    @Autowired
    private CustomerRepository customerRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Customer customer = new Customer("Jack", "Bauer", "jack@acme.com");
        CreditCard c1 = new CreditCard("123","Visa", "08/07/24");
        CreditCard c2 = new CreditCard("345","MasterCard", "05/09/24");
        customer.addCreditCard(c1);
        customer.addCreditCard(c2);
        customerRepository.save(customer);

        // fetch an individual customer by ID
        System.out.println("Fetch Customer  with findById(1L):");
        Optional<Customer> custOpt = customerRepository.findById(1L);
        Customer customer2 = custOpt.get();
        System.out.println("Customer found with findById(1L):");
        System.out.println("--------------------------------");
        System.out.println(customer2);
        System.out.println();
    }
}
