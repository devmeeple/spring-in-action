package io.github.devmeeple.ch10.controllers;

import io.github.devmeeple.ch10.model.Country;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("countryControllerCh10")
public class CountryController {

    @GetMapping("/ch10/france")
    public ResponseEntity<Country> france() {
        Country country = Country.of("France", 67);

        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .header("continent", "Europe")
                .header("capital", "Paris")
                .header("favorite_food", "cheese and wine")
                .body(country);
    }
}
