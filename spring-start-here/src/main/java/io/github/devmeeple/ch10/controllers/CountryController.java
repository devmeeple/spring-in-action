package io.github.devmeeple.ch10.controllers;

import io.github.devmeeple.ch10.model.Country;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("countryControllerCh10")
public class CountryController {

    @GetMapping("/ch10/france")
    public Country france() {
        Country country = Country.of("France", 67);
        return country;
    }

    @GetMapping("/ch10/all")
    public List<Country> countries() {
        Country country1 = Country.of("France", 67);
        Country country2 = Country.of("Spain", 47);

        return List.of(country1, country2);
    }
}
