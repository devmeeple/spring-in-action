package io.github.devmeeple.ch10.service;

import io.github.devmeeple.ch10.exceptions.NotEnoughMoneyException;
import io.github.devmeeple.ch10.model.PaymentDetails;
import org.springframework.stereotype.Service;

@Service("paymentServiceCh10")
public class PaymentService {

    public PaymentDetails processPayment() {
        throw new NotEnoughMoneyException();
    }
}
