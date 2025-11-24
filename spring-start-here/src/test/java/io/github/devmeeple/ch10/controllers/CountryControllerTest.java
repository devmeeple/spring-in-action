package io.github.devmeeple.ch10.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.devmeeple.ch10.model.Country;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
                .andExpect(status().isAccepted())
                .andExpect(content().json(result))
                .andExpect(header().string("continent", "Europe"))
                .andExpect(header().string("capital", "Paris"))
                .andExpect(header().string("favorite_food", "cheese and wine"));
    }
}
