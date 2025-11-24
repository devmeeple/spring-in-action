package io.github.devmeeple.ch10.controllers.advice;

import io.github.devmeeple.ch10.exceptions.NotEnoughMoneyException;
import io.github.devmeeple.ch10.model.ErrorDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(NotEnoughMoneyException.class)
    public ResponseEntity<ErrorDetails> exceptionNotEnoughMoneyHandler() {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setMessage("Not enough money to make the payment.");

        return ResponseEntity
                .badRequest()
                .body(errorDetails);
    }
}
