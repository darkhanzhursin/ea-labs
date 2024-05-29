package contacts;

import contacts.domain.Contact;
import contacts.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private ContactService contactService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        contactService.add(new Contact("Frank","Brown","fbrown@gmail.com","4723459800"));
        System.out.println(contactService.findByFirstName("Frank"));
    }
}
