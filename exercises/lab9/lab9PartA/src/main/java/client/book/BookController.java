package client.book;

import client.CustomErrorType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/books")
public class BookController {

    private Map<String, Book> books = new HashMap<>();

    public BookController() {
        books.put("136", new Book("136", "The Odyssey", "Homer", 10.99));
        books.put("601", new Book("601", "Harry Potter", "J.K. Rowling", 12.99));
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<?> getBook(@PathVariable String isbn) {
        Book book = books.get(isbn);
        if (book == null) {
            return new ResponseEntity<CustomErrorType>(new CustomErrorType("Book with isbn=" + isbn + " is not available"),
                    HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addBook(@RequestBody Book book) {
        books.put(book.getIsbn(), book);
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    @DeleteMapping("/{isbn}")
    public ResponseEntity<?> deleteBook(@PathVariable String isbn) {
        Book book = books.get(isbn);
        if (book == null) {
            return new ResponseEntity<>(new CustomErrorType("Book with isbn=" + isbn + " is not available"),
                    HttpStatus.NOT_FOUND);
        }
        books.remove(isbn);
        return new ResponseEntity<>(book, HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{isbn}")
    public ResponseEntity<?> updateBook(@PathVariable String isbn, @RequestBody Book book) {
        books.put(isbn, book);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getAllBooks() {
        Books allBooks = new Books(books.values());
        return new ResponseEntity<>(allBooks, HttpStatus.OK);
    }
}
