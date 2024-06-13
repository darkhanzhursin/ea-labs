package app;

import accounts.domain.Address;
import accounts.domain.CreditCard;
import accounts.domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import repository.AddressRepository;
import repository.CreditCardRepository;
import repository.CustomerRepository;

import java.util.Date;

@SpringBootApplication
@EntityScan("domain")
@EnableJpaRepositories("repository")
public class Application implements CommandLineRunner {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CreditCardRepository creditCardRepository;

    @Autowired
    private AddressRepository addressRepository;

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
        Address address1 = new Address("mainstreet 1", "Chicago", "58902");
        customer1.setAddress(address1);
        customerRepository.save(customer1);

        CreditCard creditCard11 = new CreditCard("356", "Frank Johnson", new Date());
        CreditCard creditCard22 = new CreditCard("666", "Frank Johnson", new Date());
        Customer customer2 = new Customer("Frank", "Johnson");
        customer2.getCreditcard().add(creditCard11);
        customer2.getCreditcard().add(creditCard22);
        Address address2 = new Address("mainstreet 4", "New York", "21345");
        customer2.setAddress(address2);
        customerRepository.save(customer2);

        System.out.println("Get creditcard by number 356");
        System.out.println(creditCardRepository.findByNumber("356"));
        System.out.println("------------------------");

        System.out.println("Get creditcard by number 356 and name Frank Johnson");
        System.out.println(creditCardRepository.findByNumberAndName("356", "Frank Johnson"));
        System.out.println("------------------------");

        System.out.println("Get creditcards from Frank Johnson");
        creditCardRepository.findByName("Frank Johnson").forEach(System.out::println);
        System.out.println("------------------------");

        System.out.println("Get customers from Chicago");
        customerRepository.findCustomerByCity("Chicago").forEach(System.out::println);
        System.out.println("------------------------");

        System.out.println("Get customers with creditcard number 356");
        customerRepository.findCustomerByCreditCard("356").forEach(System.out::println);
        System.out.println("------------------------");

        System.out.println("Get addresses from New York");
        addressRepository.findByCity("New York").forEach(System.out::println);
        System.out.println("------------------------");

        System.out.println("Get all zip codes from New York");
        addressRepository.findZipcodesByCity("New York").forEach(System.out::println);
        System.out.println("------------------------");
    }
}
