package com.payu.payu_sim.model;

public class Transaction {

    private String transactionId;
    private String cardNumber;
    private double amount;
    private String expiry;
    private String status;
    private long timestamp;


    public Transaction(
            String transactionId,
            String cardNumber,
            double amount,
            String expiry,
            String status,
            long timestamp) {

        this.transactionId = transactionId;
        this.cardNumber = cardNumber;
        this.amount = amount;
        this.expiry = expiry;
        this.status = status;
        this.timestamp = timestamp;
    }


    public String getTransactionId() {
        return transactionId;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public double getAmount() {
        return amount;
    }

    public String getExpiry() {
        return expiry;
    }

    public String getStatus() {
        return status;
    }

    public long getTimestamp() {
        return timestamp;
    }


    public void setStatus(String status) {
        this.status = status;
    }
}
