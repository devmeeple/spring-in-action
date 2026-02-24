package io.github.devmeeple.bootcamp.product;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@ResponseBody
@Controller
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public void saveProduct(@RequestBody Product product) {
        productService.saveProduct(product);
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
    public String getProductById(@PathVariable int id) {
        System.out.println(id);
        return productService.getProductById(id);
    }
}
