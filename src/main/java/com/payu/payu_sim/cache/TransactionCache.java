package com.payu.payu_sim.cache;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.payu.payu_sim.model.Transaction;

@Component
public class TransactionCache {

    private ConcurrentHashMap<String, Transaction> cache =
            new ConcurrentHashMap<>();


    public void put(Transaction txn) {

        cache.put(txn.getTransactionId(), txn);

        System.out.println("Stored transaction: " +
                txn.getTransactionId());
    }


    public Transaction get(String txnId) {

        return cache.get(txnId);
    }


    public boolean exists(String txnId) {

        return cache.containsKey(txnId);
    }


    public ConcurrentHashMap<String, Transaction> getAll() {

        return cache;
    }
}
