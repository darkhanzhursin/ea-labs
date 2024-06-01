package app;

import domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import repository.AddressRepository;
import repository.CustomerRepository;
import repository.OrderRepository;

import java.time.LocalDate;

@SpringBootApplication
@EnableJpaRepositories("repository")
@EntityScan("domain")
public class Application implements CommandLineRunner {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private OrderRepository orderRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Customer customer = new Customer("Eddy", "John");
        Order order1 = new Order(LocalDate.now(), "started");
        Order order2 = new Order(LocalDate.of(2024, 5, 1), "finished");

        OrderLine orderLine1 = new OrderLine(2, new Product("chicken", "indian", 20.0));
        order1.addOrderLine(orderLine1);
        order2.addOrderLine(orderLine1);
        orderRepository.save(order1);
        orderRepository.save(order2);

        customer.addOrder(order1);
        customer.addOrder(order2);
        Address address = new Address("4th st.", "Fairfield", "55667", "USA");
        addressRepository.save(address);
        customer.setAddress(address);

        customerRepository.save(customer);
    }
}
