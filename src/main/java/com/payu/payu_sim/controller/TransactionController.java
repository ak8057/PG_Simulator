package com.payu.payu_sim.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.payu.payu_sim.model.TransactionRequest;
import com.payu.payu_sim.model.TransactionResponse;
import com.payu.payu_sim.service.TransactionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import com.payu.payu_sim.model.TransactionSummary;

@RestController
public class TransactionController {

    private final TransactionService service;

    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @GetMapping("/transaction/{txnId}")
    public TransactionResponse getTransactionStatus(
            @PathVariable String txnId) {

        return service.getStatus(txnId);
    }


    @PostMapping("/transaction")
    public TransactionResponse createTransaction(
            @RequestBody TransactionRequest request) {

        return service.process(request);
    }

    //for saving all transactions in memory and retrieving them with hashed card number
    @GetMapping("/transactions")
    public List<TransactionSummary> getAllTransactions() {

        return service.getAllTransactions();
    }

}
