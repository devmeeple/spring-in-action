package io.github.devmeeple.ch12.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.devmeeple.ch12.model.Purchase;
import io.github.devmeeple.ch12.repositories.PurchaseRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class PurchaseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PurchaseRepository purchaseRepository;

    @Test
    void storePurchase() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Purchase purchase = new Purchase();

        purchase.setProduct("Spring Quickly");
        purchase.setPrice(BigDecimal.TEN);

        mockMvc.perform(post("/ch12/purchase")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(purchase))
        ).andExpect(status().isOk());

        verify(purchaseRepository).storePurchase(purchase);
    }

    @Test
    void findPurchases() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        Purchase purchase = new Purchase();

        purchase.setProduct("Spring Quickly");
        purchase.setPrice(BigDecimal.TEN);

        List<Purchase> results = List.of(purchase);

        when(purchaseRepository.findAllPurchases()).thenReturn(results);

        mockMvc.perform(get("/ch12/purchase"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(results)));
    }
}
