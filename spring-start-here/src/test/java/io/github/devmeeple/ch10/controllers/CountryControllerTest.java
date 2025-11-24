package io.github.devmeeple.ch10.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.devmeeple.ch10.model.Country;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class CountryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetFrance() throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        Country country = new Country();
        country.setName("France");
        country.setPopulation(67);

        String result = mapper.writeValueAsString(country);

        mockMvc.perform(get("/ch10/france"))
                .andExpect(status().isOk())
                .andExpect(content().json(result));
    }

    @Test
    void testGetAllCountries() throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        Country country1 = new Country();
        country1.setName("France");
        country1.setPopulation(67);

        Country country2 = new Country();
        country2.setName("Spain");
        country2.setPopulation(47);

        String result = mapper.writeValueAsString(List.of(country1, country2));

        mockMvc.perform(get("/ch10/all"))
                .andExpect(status().isOk())
                .andExpect(content().json(result));
    }
}
