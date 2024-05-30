package customers.service.product;

import customers.domain.Product;
import customers.integration.ems.EmailSender;
import customers.repository.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    ProductRepository productRepository;

    EmailSender emailSender;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Autowired
    public void setEmailSender(EmailSender emailSender) {
        this.emailSender = emailSender;
    }

    @Override
    public void addProduct(String name, String desc, double price, String email) {
        Product product = new Product(name, desc, price);
        productRepository.save(product);
        emailSender.sendEmail(email, "Product: " + name + "was added");
    }
}
