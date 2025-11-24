package io.github.devmeeple.ch10.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.devmeeple.ch10.model.PaymentDetails;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class PaymentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testPayment() throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        PaymentDetails paymentDetails = new PaymentDetails();
        paymentDetails.setAmount(1000);

        String result = mapper.writeValueAsString(paymentDetails);

        mockMvc.perform(post("/ch10/payment")
                .content(result)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted())
                .andExpect(content().json(result));
    }
}
