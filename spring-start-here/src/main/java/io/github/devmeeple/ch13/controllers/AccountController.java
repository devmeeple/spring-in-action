package io.github.devmeeple.ch13.controllers;

import io.github.devmeeple.ch13.dto.TransferRequest;
import io.github.devmeeple.ch13.model.Account;
import io.github.devmeeple.ch13.services.TransferService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("accountControllerCh13")
public class AccountController {

    private final TransferService transferService;

    public AccountController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping("/ch13/transfer")
    public void transferMoney(@RequestBody TransferRequest request) {
        transferService.transferMoney(
                request.getSenderAccountId(),
                request.getReceiverAccountId(),
                request.getAmount()
        );
    }

    @GetMapping("/ch13/accounts")
    public List<Account> getAllAccounts() {
        return transferService.getAllAccounts();
    }
}
