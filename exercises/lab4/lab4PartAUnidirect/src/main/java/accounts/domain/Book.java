package domain;

import jakarta.persistence.*;

@Entity
public class Book {

    @Id
    @GeneratedValue
    private Integer id;

    private String isbn;
    private String title;
    private String author;

    @ManyToOne(optional = true)
    @JoinTable(name = "book_publisher")
    private Publisher publisher;

    public Book(String isbn, String title, String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
    }

    protected Book() {

    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }
}
