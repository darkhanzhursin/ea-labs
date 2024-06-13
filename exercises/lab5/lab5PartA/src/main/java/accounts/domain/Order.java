package domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

@Entity
@Table(name = "myOrder")
public class Order {

    @Id
    @GeneratedValue
    private Integer orderNumber;

    private LocalDate orderDate;
    private String status;

    @ManyToOne
    private Customer customer;

    @OneToMany
    @JoinColumn(name = "order_number")
    private Collection<OrderLine> orderLines = new ArrayList<>();

    public Order(){}

    public Order(LocalDate orderDate, String status) {
        this.orderDate = orderDate;
        this.status = status;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Collection<OrderLine> getOrderLines() {
        return Collections.unmodifiableCollection(orderLines);
    }

    public boolean addOrderLine(OrderLine ol) {
        return orderLines.add(ol);
    }
}
