package app;

import domain.Book;
import domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import repository.BookRepository;
import repository.CustomerRepository;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
@EnableJpaRepositories("repository")
@EntityScan("domain")
public class Application implements CommandLineRunner {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    BookRepository bookRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        customerRepository.save(new Customer("Jack", "Bauer", "jack@acme.com"));
        customerRepository.save(new Customer("Chloe", "O'Brian", "chloe@acme.com"));
        customerRepository.save(new Customer("Kim", "Bauer", "kim@acme.com"));
        customerRepository.save(new Customer("David", "Palmer", "dpalmer@gmail.com"));
        customerRepository.save(new Customer("Michelle", "Dessler", "mich@hotmail.com"));

        // fetch all customers
        System.out.println("Customers found with findAll():");
        System.out.println("-------------------------------");
        for (Customer customer : customerRepository.findAll()) {
            System.out.println(customer);
        }
        System.out.println();

        // fetch an individual customer by ID
        Optional<Customer> custOpt = customerRepository.findById(1L);
        Customer customer = custOpt.get();
        System.out.println("Customer found with findOne(1L):");
        System.out.println("--------------------------------");
        System.out.println(customer);
        System.out.println();

        System.out.println("-------------Books:----------------------");
        Book book1 = new Book("Effective Java", "978-0134685991", "Joshua Bloch", 45.99);
        Book book2 = new Book("Clean Code", "978-0132350884", "Robert C. Martin", 37.95);
        Book book3 = new Book("Design Patterns", "978-0201633610", "Erich Gamma, Richard Helm, Ralph Johnson, John Vlissides", 54.95);
        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);
        List<Book> books = bookRepository.findAll();
        books.forEach(System.out::println);

        Book updatedBook = bookRepository.findById(1L).orElseThrow(() -> new RuntimeException("Book not found"));
        updatedBook.setTitle("Super Effective Java");
        bookRepository.save(updatedBook);
        bookRepository.deleteById(3L);
        System.out.println();
        System.out.println("------------Updated---------------");
        bookRepository.findAll().forEach(System.out::println);
    }
}
