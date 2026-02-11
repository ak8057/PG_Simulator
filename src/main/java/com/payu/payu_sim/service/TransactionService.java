package com.payu.payu_sim.service;

import org.springframework.stereotype.Service;

import com.payu.payu_sim.processor.TransactionProcessor;

@Service
public class TransactionService {

    private final TransactionProcessor processor;

    public TransactionService(TransactionProcessor processor) {
        this.processor = processor;
    }

    public String processTransaction() {

        return processor.execute();
    }
}
