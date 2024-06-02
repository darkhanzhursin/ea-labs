package domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Customer {

    @Id
    @GeneratedValue()
    private long id;

    private String firstName;

    private String lastName;

    private String email;

    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "Cust_id")
    private List<CreditCard> creditcards = new ArrayList<CreditCard>();

    protected Customer() {
    }

    public Customer(String firstName, String lastName, String email) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public void addCreditCard(CreditCard creditCard){
        creditcards.add(creditCard);
    }

    public List<CreditCard> getCreditcards() {
        return creditcards;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", creditcards=" + creditcards +
                '}';
    }
}
