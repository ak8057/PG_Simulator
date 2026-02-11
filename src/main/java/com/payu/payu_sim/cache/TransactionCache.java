package com.payu.payu_sim.cache;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

@Component
public class TransactionCache {

    // Thread-safe cache
    private ConcurrentHashMap<String, String> cache = new ConcurrentHashMap<>();


    // Store transaction
    public void put(String transactionId, String status) {

        cache.put(transactionId, status);

        System.out.println("Cached: " + transactionId + " → " + status);
    }


    // Get transaction status
    public String get(String transactionId) {

        return cache.get(transactionId);
    }


    // Check if exists
    public boolean exists(String transactionId) {

        return cache.containsKey(transactionId);
    }


    // Print all cache
    public void printAll() {

        System.out.println("Current Cache: " + cache.toString());
    }

}
