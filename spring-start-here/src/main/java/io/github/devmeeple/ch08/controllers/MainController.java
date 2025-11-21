package io.github.devmeeple.ch08.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller("mainControllerCh08")
public class MainController {

    @RequestMapping("/ch08/home")
    public String home(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String color,
            Model page) {
        page.addAttribute("username", name);
        page.addAttribute("color", color);
        return "home.html";
    }
}
