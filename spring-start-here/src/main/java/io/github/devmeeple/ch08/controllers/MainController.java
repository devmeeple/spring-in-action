package io.github.devmeeple.ch08.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("mainControllerCh08")
public class MainController {

    @RequestMapping("/ch08/home/{color}")
    public String home(
            @PathVariable String color,
            Model page) {
        page.addAttribute("username", "Katy");
        page.addAttribute("color", color);
        return "home.html";
    }
}
