package repository;

import accounts.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findByFirstName(String name);

    @Query("select c from Customer c")
    List<Customer> findByFirstNameLazy(String name);

    @Query("select distinct c from Customer c join fetch c.creditCards")
    List<Customer> findByFirstNameEager(String name);
}
