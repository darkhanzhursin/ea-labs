package app;

import domain.Address;
import domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import repository.CustomerRepository;

import java.util.List;

@SpringBootApplication
@EnableJpaRepositories("repository")
@EntityScan("domain")
public class Application implements CommandLineRunner {

    @Autowired
    CustomerRepository customerRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Address address1 = new Address("mainstreet 1", "Chicago", "58902");
        Customer customer1 = new Customer("Frank", "Brown");
        customer1.setAddress(address1);
        customerRepository.save(customer1);

        Address address2 = new Address("mainstreet 4", "New York", "21345");
        Customer customer2 = new Customer("Frank", "Johnson");
        customer2.setAddress(address2);
        customerRepository.save(customer2);

        List<Customer> customerList = customerRepository.findByFirstName("Frank");
        customerList.forEach(System.out::println);

        List<Customer> customerList2 = customerRepository.findByFirstNameLazy("Frank");
        customerList2.forEach(System.out::println);

        List<Customer> customerList3 = customerRepository.findByFirstNameEager("Frank");
        customerList3.forEach(System.out::println);

    }
}
