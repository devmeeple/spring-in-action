package io.github.devmeeple.ch11.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import io.github.devmeeple.ch11.model.Payment;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.springframework.http.HttpStatus.OK;

@AutoConfigureMockMvc
@SpringBootTest
class PaymentsControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    private static WireMockServer wireMockServer;

    @BeforeAll
    static void init() {
        wireMockServer = new WireMockServer(new WireMockConfiguration().port(8080));
        wireMockServer.start();
        configureFor("localhost", 8080);
    }

    @Test
    void testPaymentEndpoint() throws Exception {
        Payment requestBody = new Payment();
        requestBody.setAmount(1000);

        ObjectMapper mapper = new ObjectMapper();

        stubFor(post(urlMatching("/ch11/payment"))
                .willReturn(aResponse()
                        .withBody(mapper.writeValueAsString(requestBody))
                        .withHeader("content-type", MediaType.APPLICATION_JSON_VALUE)
                        .withStatus(OK.value())));

        webTestClient.post()
                .uri("/ch11/payment")
                .bodyValue(requestBody)
                .exchange()
                .expectStatus().isOk();
    }
}
