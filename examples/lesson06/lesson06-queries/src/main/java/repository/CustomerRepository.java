package repository;

import domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findByFirstName(String name);

    @Query("select c from Customer c where c.address.city= :city")
    List<Customer> findCustomerByCity(@Param("city") String city);

    @Query("select c from Customer c JOIN c.creditCards cr where cr.number= :ccnumber ")
    List<Customer> findCustomerByCreditCard(@Param("ccnumber") String ccnumber);
}
