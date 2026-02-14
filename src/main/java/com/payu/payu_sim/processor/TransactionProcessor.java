package com.payu.payu_sim.processor;

import org.springframework.stereotype.Component;

import com.payu.payu_sim.bank.BankSimulator;
import com.payu.payu_sim.cache.TransactionCache;
import com.payu.payu_sim.model.TransactionRequest;
import com.payu.payu_sim.model.Transaction;

@Component
public class TransactionProcessor {

    private final TransactionCache cache;
    private final BankSimulator bankSimulator;

    public TransactionProcessor(
            TransactionCache cache,
            BankSimulator bankSimulator) {

        this.cache = cache;
        this.bankSimulator = bankSimulator;
    }

    public String process(TransactionRequest request) {

        String txnId = "TXN" + System.currentTimeMillis();

        String status = bankSimulator.authorize(
                request.getCardNumber(),
                request.getAmount());

        Transaction txn = new Transaction(
                txnId,
                request.getCardNumber(),
                request.getAmount(),
                request.getExpiry(),
                status,
                System.currentTimeMillis());

        cache.put(txn);

        System.out.println("Processed transaction: " + txnId);

        return txnId;
    }

}
