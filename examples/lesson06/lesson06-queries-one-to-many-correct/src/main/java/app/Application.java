package app;

import accounts.domain.CreditCard;
import accounts.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import repository.CustomerRepository;

import java.util.Date;
import java.util.List;

@SpringBootApplication
@EnableJpaRepositories("repository")
@EntityScan("domain")
public class Application implements CommandLineRunner{

    @Autowired
    CustomerRepository customerRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        CreditCard creditCard1 = new CreditCard("123", "Frank Brown", new Date());
        CreditCard creditCard2 = new CreditCard("345", "Frank Brown", new Date());
        Customer customer1 = new Customer("Frank", "Brown");
        customer1.getCreditcard().add(creditCard1);
        customer1.getCreditcard().add(creditCard2);
        customerRepository.save(customer1);

        CreditCard creditCard11 = new CreditCard("123", "Frank Johnson", new Date());
        CreditCard creditCard22 = new CreditCard("345", "Frank Johnson", new Date());
        Customer customer2 = new Customer("Frank", "Johnson");
        customer2.getCreditcard().add(creditCard11);
        customer2.getCreditcard().add(creditCard22);
        customerRepository.save(customer2);

        List<Customer> customerList = customerRepository.findByFirstName("Frank");
        customerList.forEach(System.out::println);

        List<Customer> customerList2 = customerRepository.findByFirstNameLazy("Frank");
        customerList2.forEach(System.out::println);

        List<Customer> customerList3 = customerRepository.findByFirstNameEager("Frank");
        customerList3.forEach(System.out::println);

    }
}
