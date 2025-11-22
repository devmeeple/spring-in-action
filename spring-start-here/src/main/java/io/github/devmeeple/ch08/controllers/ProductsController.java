package io.github.devmeeple.ch08.controllers;

import io.github.devmeeple.ch08.model.Product;
import io.github.devmeeple.ch08.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller("productsControllerCh08")
public class ProductsController {

    private final ProductService productService;

    public ProductsController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/ch08/products")
    public String viewProducts(Model model) {
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);

        return "/ch08/products.html";
    }

    @PostMapping("/ch08/products")
    public String addProduct(
            Product p,
            Model model
    ) {
        productService.addProduct(p);

        List<Product> products = productService.findAll();
        model.addAttribute("products", products);

        return "/ch08/products.html";
    }
}
