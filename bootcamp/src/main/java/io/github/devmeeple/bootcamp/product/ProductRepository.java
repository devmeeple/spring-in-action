package io.github.devmeeple.bootcamp.product;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class ProductRepository {

    private final Map<Integer, String> database = new HashMap<>();

    public void saveProduct() {
        database.put(1, "NoteBook");
    }

    public String getProduct() {
        return database.get(1);
    }
}
