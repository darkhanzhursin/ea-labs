package repository;

import domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByAuthorAndPriceLessThan(String author, double price);

    List<Product> findByAuthor(@Param("author") String author);

    @Query("select cd from CD cd where cd.author=:author and cd.price > :price")
    List<Product> findByAuthorAndPriceGreaterThan(@Param("author") String author, @Param("price") double price);
}
