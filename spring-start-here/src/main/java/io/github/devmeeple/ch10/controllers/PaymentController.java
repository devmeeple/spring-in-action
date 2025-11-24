package io.github.devmeeple.ch10.controllers;

import io.github.devmeeple.ch10.model.PaymentDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController("paymentControllerCh10")
public class PaymentController {

    private static Logger logger = Logger.getLogger(PaymentController.class.getName());

    @PostMapping("/ch10/payment")
    public ResponseEntity<PaymentDetails> makePayment(@RequestBody PaymentDetails paymentDetails) {
        logger.info("Received payment " + paymentDetails.getAmount());

        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(paymentDetails);
    }
}
