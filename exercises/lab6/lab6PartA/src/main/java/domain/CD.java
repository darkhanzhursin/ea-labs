package domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.NamedQuery;

@Entity
@DiscriminatorValue("cd")
@NamedQuery(name = "Product.findByAuthor",
        query = "select c from CD c where c.author = :author")
public class CD extends Product{
    private String author;

    public CD(String name, String description, double price, String author) {
        super(name, description, price);
        this.author = author;
    }

    public CD() {
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
