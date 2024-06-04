package domain;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
public class Customer {

    @Id
    @GeneratedValue
    private long id;

    private String firstName;

    private String lastName;

    @OneToMany (cascade={CascadeType.PERSIST}, fetch=FetchType.EAGER)
    private Collection<CreditCard> creditCards =new ArrayList<>();


    public Customer(){
    }

    public Customer(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstname) {
        this.firstName = firstname;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastname) {
        this.lastName = lastname;
    }

    public Collection<CreditCard> getCreditcard() {
        return creditCards;
    }

    public void setCreditcard(Collection<CreditCard> creditcard) {
        this.creditCards = creditcard;
    }


    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstname='" + firstName + '\'' +
                ", lastname='" + lastName + '\'' +
                ", creditcards=" + creditCards +
                '}';
    }
}
