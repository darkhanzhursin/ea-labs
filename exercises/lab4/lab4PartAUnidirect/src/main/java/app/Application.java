package app;

import domain.Book;
import domain.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import repository.BookRepository;
import repository.PublisherRepository;

@SpringBootApplication
@EnableJpaRepositories("repository")
@EntityScan("domain")
public class Application implements CommandLineRunner {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PublisherRepository publisherRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Publisher publisher = new Publisher("publisher1");
        publisherRepository.save(publisher);
        Book book1 = new Book("123", "title1", "author1");
        book1.setPublisher(publisher);
        Book book2 = new Book("112", "title2", "author2");
        bookRepository.save(book1);
        bookRepository.save(book2);
    }
}
