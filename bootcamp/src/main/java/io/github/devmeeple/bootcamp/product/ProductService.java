package io.github.devmeeple.bootcamp.product;

import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public void saveProduct() {
        productRepository.saveProduct();
    }

    public String getProduct() {
        return productRepository.getProduct();
    }
}
