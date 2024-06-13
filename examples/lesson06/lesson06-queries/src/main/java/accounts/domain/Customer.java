package accounts.domain;

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

    @OneToMany(cascade={CascadeType.PERSIST})
    private Collection<CreditCard> creditCards =new ArrayList<>();

    @ManyToOne(cascade={CascadeType.PERSIST})
    private Address address;

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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstname='" + firstName + '\'' +
                ", lastname='" + lastName + '\'' +
                //		", creditcard=" + creditcards +
                //		", address=" + address +
                '}';
    }
}
