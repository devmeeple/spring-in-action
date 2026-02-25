package io.github.devmeeple.bootcamp.product;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProductRepository {

    private final Map<Integer, Product> database = new HashMap<>();
    private int id = 1;

    public void saveProduct(Product product) {
        database.put(id++, product);
    }

    public List<Product> getProduct() {
        return new ArrayList<>(database.values());
    }

    public Product getProductById(int id) {
        return database.get(id);
    }
}
