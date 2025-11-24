package io.github.devmeeple.ch10.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @ResponseBody
    @GetMapping("/ch10/hello")
    public String hello() {
        return "Hello!";
    }

    @ResponseBody
    @GetMapping("/ch10/ciao")
    public String ciao() {
        return "Ciao!";
    }
}
