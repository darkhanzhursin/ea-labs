package repository;

import domain.Address;
import domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    List<Customer> findAllCustomers();

    List<Customer> findByCountry(@Param("country") String country);

    @Query("select c from Customer c where c.firstName=:firstName and c.lastName=:lastName and c.address.city=:city")
    List<Customer> findByFirstNameAndLastNameAndCity(@Param("firstName") String firstName,
                                                     @Param("lastName") String lastName,
                                                     @Param("city") String city);

    @Query(value = "SELECT * FROM address WHERE city = :city", nativeQuery = true)
    List<Address> findByCity(@Param("city") String city);
}
