package io.github.devmeeple.ch10.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.devmeeple.ch10.exceptions.NotEnoughMoneyException;
import io.github.devmeeple.ch10.model.ErrorDetails;
import io.github.devmeeple.ch10.model.PaymentDetails;
import io.github.devmeeple.ch10.service.PaymentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
class PaymentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PaymentService paymentService;

    @DisplayName("결제 요청이 성공하면 정상 응답 요청을 반환한다.")
    @Test
    void testMakePaymentSuccessful() throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        PaymentDetails paymentDetails = new PaymentDetails();
        paymentDetails.setAmount(1000);

        when(paymentService.processPayment())
                .thenReturn(paymentDetails);

        String result = mapper.writeValueAsString(paymentDetails);

        mockMvc.perform(post("/ch10/payment"))
                .andExpect(status().isAccepted())
                .andExpect(content().json(result));
    }

    @DisplayName("잔액이 부족하면 예외가 발생한다.")
    @Test
    void testMakePaymentNotEnoughMoney() throws Exception {
        ObjectMapper mapper = new ObjectMapper();

        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setMessage("Not enough money to make the payment.");

        when(paymentService.processPayment())
                .thenThrow(new NotEnoughMoneyException());

        String result = mapper.writeValueAsString(errorDetails);

        mockMvc.perform(post("/ch10/payment"))
                .andExpect(status().isBadRequest())
                .andExpect(content().json(result));
    }
}
