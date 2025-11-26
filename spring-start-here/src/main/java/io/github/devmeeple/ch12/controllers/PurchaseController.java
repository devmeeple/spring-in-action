package io.github.devmeeple.ch12.controllers;

import io.github.devmeeple.ch12.model.Purchase;
import io.github.devmeeple.ch12.repositories.PurchaseRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/ch12/purchase")
@RestController("purchaseControllerCh12")
public class PurchaseController {

    private final PurchaseRepository purchaseRepository;

    public PurchaseController(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    @PostMapping
    public void storePurchase(@RequestBody Purchase purchase) {
        purchaseRepository.storePurchase(purchase);
    }

    @GetMapping
    public List<Purchase> findPurchases() {
        return purchaseRepository.findAllPurchases();
    }
}
