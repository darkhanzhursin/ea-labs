package client;

import client.book.Book;
import client.book.Books;
import client.contact.Contact;
import client.contact.Contacts;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class Application implements CommandLineRunner {

	private RestTemplate restTemplate = new RestTemplate();

	private String serverUrl = "http://localhost:8080/";
	private final String CONTACTS_URL = serverUrl + "contacts";
	private final String BOOKS_URL = serverUrl + "books";

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		// get frank
		Contact contact= restTemplate.getForObject(CONTACTS_URL+"/{firstName}", Contact.class, "Frank");
		System.out.println(contact);

		// add John
		restTemplate.postForLocation(CONTACTS_URL, new Contact("John","Doe", "jdoe@acme.com", "6739127563"));

		// get john
		contact= restTemplate.getForObject(CONTACTS_URL+"/{firstName}", Contact.class, "John");
		System.out.println(contact);

		// delete mary
		restTemplate.delete(CONTACTS_URL+"/{firstName}", "Mary");

		// update John
		contact.setEmail("johndoe@acme.com");
		restTemplate.put(CONTACTS_URL+"/{firstName}" , contact, "John");

		// get john
		contact= restTemplate.getForObject(CONTACTS_URL+"/{firstName}", Contact.class, "John");
		System.out.println(contact);

        // get all contacts
		Contacts contacts = restTemplate.getForObject(CONTACTS_URL, Contacts.class);
		System.out.println(contacts);

		// get book
		Book book = restTemplate.getForObject(BOOKS_URL + "/{isbn}", Book.class, "136");
		System.out.println(book);

		// post
		restTemplate.postForLocation(BOOKS_URL, new Book("084", "To Kill a Mockingbird", "Unknown", 7.19));
		book = restTemplate.getForObject(BOOKS_URL + "/{isbn}", Book.class, "084");
		System.out.println(book);

		// delete
		restTemplate.delete(BOOKS_URL+"/{isbn}", "136");

		// update
		book.setAuthor("Darkhan");
		restTemplate.put(BOOKS_URL+"/{isbn}" , book, "084");

		book = restTemplate.getForObject(BOOKS_URL + "/{isbn}", Book.class, "084");
		System.out.println(book);

		Books books = restTemplate.getForObject(BOOKS_URL, Books.class);
		System.out.println(books);
	}

}
