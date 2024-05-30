package customers.repository.product;

import customers.domain.Product;
import customers.integration.logging.Logger;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private Logger logger;

    public ProductRepositoryImpl(Logger logger) {
        this.logger = logger;
    }

    @Override
    public void save(Product product) {
        try {
            Thread.sleep(350);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\nProductRepository: saving product " + product.getName());
        logger.log("Product is saved in the DB: " + product.getName());
    }
}
