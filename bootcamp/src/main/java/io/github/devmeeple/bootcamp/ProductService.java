package io.github.devmeeple.bootcamp;

import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public String getProduct() {
        return productRepository.getProduct();
    }
}
