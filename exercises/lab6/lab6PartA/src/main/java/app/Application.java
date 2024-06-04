package app;

import domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import repository.CustomerRepository;

import java.time.LocalDate;

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
        Customer customer = new Customer("Eddy", "John");
        Order order1 = new Order(LocalDate.now(), "started");
        Order order2 = new Order(LocalDate.of(2024, 5, 1), "finished");
        Product book = new Book("book1", "desc", 20.0, "isbn123");
        Product cd = new CD("cd1", "cd desc", 10.0, "Author1");
        Product dvd = new DVD("dvd1", "dvd desc", 15.0, "thriller");

        OrderLine orderLine1 = new OrderLine(1, book);
        OrderLine cdOrder = new OrderLine(2, cd);
        OrderLine dvdOrder = new OrderLine(3, dvd);
        order1.addOrderLine(orderLine1);
        order1.addOrderLine(cdOrder);
        order2.addOrderLine(orderLine1);
        order2.addOrderLine(dvdOrder);

        customer.addOrder(order1);
        customer.addOrder(order2);
        Address address = new Address("4th st.", "Fairfield", "55667", "USA");
        customer.setAddress(address);

        customerRepository.save(customer);
    }
}
