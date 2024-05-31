package customers.dao;

import customers.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductRepository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public void clearDB() {
        Map<String, Object> namedParameters = new HashMap<>();
        jdbcTemplate.update("DELETE from product", namedParameters);
    }

    public void save(Product product) {
        Map<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("productnumber", product.getProductNumber());
        namedParameters.put("name", product.getName());
        namedParameters.put("price", product.getPrice());
        jdbcTemplate.update("INSERT INTO product VALUES ( :productnumber, :name, :price)", namedParameters);

        Map<String, Object> namedParameterss = new HashMap<>();
        namedParameterss.put("phone", product.getSupplier().getPhone());
        namedParameterss.put("name", product.getSupplier().getName());
        namedParameterss.put("productNumber", product.getProductNumber());
        jdbcTemplate.update("INSERT INTO supplier VALUES ( :phone, :name, :productNumber)", namedParameterss);
    }

    public List<Product> getAllProducts() {
        List<Product> products = jdbcTemplate.query("SELECT * FROM product",
                new HashMap<String, Product>(),
                (rs, rowNum) -> new Product(
                        rs.getInt("productNumber"),
                        rs.getString("name"),
                        rs.getBigDecimal("price")
                        ));
        return products;
    }

    public Product findByProductName(String name) {
        Map<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("productName", name);
        Product product = jdbcTemplate.queryForObject("SELECT * FROM product WHERE name=:productName",
                namedParameters,
                (rs, rowNum) -> new Product(
                        rs.getInt("productNumber"),
                        rs.getString("name"),
                        rs.getBigDecimal("price")
                ));
        return product;
    }

    public Product findByProductNumber(int productNumber) {
        Map<String, Object> namedParameters = new HashMap<>();
        namedParameters.put("productNumber", productNumber);
        Product product = jdbcTemplate.queryForObject("SELECT * FROM product WHERE productNumber=:productNumber",
                namedParameters,
                (rs, rowNum) -> new Product(
                        rs.getInt("productNumber"),
                        rs.getString("name"),
                        rs.getBigDecimal("price")
                ));
        return product;
    }

    public void removeProduct(long number) {
        String SQL = "DELETE FROM product where productNumber = :productNumber";
        jdbcTemplate.update(SQL, new MapSqlParameterSource("productNumber", number));
    }
}
