package io.github.devmeeple.ch07.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("mainControllerCh07")
public class MainController {

    @RequestMapping("/ch07/home")
    public String home() {
        return "home.html";
    }
}
