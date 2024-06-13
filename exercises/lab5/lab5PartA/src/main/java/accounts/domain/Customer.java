package domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
public class Customer {

    @Id
    @GeneratedValue
    private Integer id;

    private String firstName;
    private String lastName;

    @OneToOne(cascade = CascadeType.ALL)
    private Address address;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Order> orderList = new ArrayList<>();

    public Customer() {}
    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Collection<Order> getTheOrders() {
        return Collections.unmodifiableCollection(orderList);
    }

    public boolean addOrder(Order order) {
        boolean added = orderList.add(order);
        if (added) {
            order.setCustomer(this);
        }
        return added;
    }

    public boolean removeOrder(Order order) {
        boolean removed = orderList.remove(order);
        if (removed) {
            orderList.remove(order);
        }
        return removed;
    }
}
