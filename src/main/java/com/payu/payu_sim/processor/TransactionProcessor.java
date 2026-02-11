package com.payu.payu_sim.processor;

import org.springframework.stereotype.Component;

import com.payu.payu_sim.cache.TransactionCache;

@Component
public class TransactionProcessor {

    private final TransactionCache cache;

    public TransactionProcessor(TransactionCache cache) {

        this.cache = cache;
    }


    public String execute() {

        String txnId = "TXN" + System.currentTimeMillis();

        // Store in cache
        cache.put(txnId, "SUCCESS");

        cache.printAll();

        return "Transaction processed with ID: " + txnId;
    }
}
