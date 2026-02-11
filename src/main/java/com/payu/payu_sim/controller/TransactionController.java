package com.payu.payu_sim.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payu.payu_sim.service.TransactionService;

@RestController
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/process")
    public String process() {

        return transactionService.processTransaction();
    }
    
    @GetMapping("/check")
    public String check() {

    return "Cache system working!";
    }

}
