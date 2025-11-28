package io.github.devmeeple.ch14.controllers;

import io.github.devmeeple.ch14.dto.TransferRequest;
import io.github.devmeeple.ch14.model.Account;
import io.github.devmeeple.ch14.services.TransferService;
import org.springframework.web.bind.annotation.*;

@RestController("accountControllerCh14")
public class AccountController {

    private final TransferService transferService;

    public AccountController(TransferService transferService) {
        this.transferService = transferService;
    }

    @PostMapping("/ch14/transfer")
    public void transferMoney(@RequestBody TransferRequest request) {
        transferService.transferMoney(
                request.getSenderAccountId(),
                request.getReceiverAccountId(),
                request.getAmount()
        );
    }

    @GetMapping("/ch14/accounts")
    public Iterable<Account> getAllAccounts(@RequestParam(required = false) String name) {
        if (name == null) {
            return transferService.getAllAccounts();
        } else {
            return transferService.findAccountsByName(name);
        }
    }
}
