package io.github.devmeeple.bootcamp.ch06;

import org.springframework.stereotype.Component;

@Component
public class ProductController {

    public ProductController() {
        System.out.println("ProductController Bean 생성 - Spring IoC 컨테이너 동작 확인");
    }
}
