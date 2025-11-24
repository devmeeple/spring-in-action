package io.github.devmeeple.ch10.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/ch10/hello")
    public String hello() {
        return "Hello!";
    }

    @GetMapping("/ch10/ciao")
    public String ciao() {
        return "Ciao!";
    }
}
