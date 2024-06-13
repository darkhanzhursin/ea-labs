package repository;

import accounts.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query(value = "SELECT * FROM address WHERE city = :city", nativeQuery = true)
    List<Address> findByCity(@Param("city") String city);

    @Query("select a.zip from Address a where a.city = :city")
    List<String> findZipcodesByCity(@Param("city") String city);
}
