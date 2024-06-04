package domain;

import jakarta.persistence.*;

@Entity
public class Customer {

    @Id
    @GeneratedValue
    private long id;

    private String firstName;

    private String lastName;

    @ManyToOne(cascade={CascadeType.PERSIST}, fetch=FetchType.LAZY)
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
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                //		", address=" + address +
                '}';
    }
}
