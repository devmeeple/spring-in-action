package io.github.devmeeple.bootcamp;

import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {

    public String getProduct() {
        return "NoteBook!!";
    }
}
