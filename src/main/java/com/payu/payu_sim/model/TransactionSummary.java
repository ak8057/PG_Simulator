package com.payu.payu_sim.model;

public class TransactionSummary {

    private String transactionId;
    private String cardNumber;
    private double amount;
    private String status;
    private long timestamp;


    public TransactionSummary(
            String transactionId,
            String cardNumber,
            double amount,
            String status,
            long timestamp) {

        this.transactionId = transactionId;
        this.cardNumber = cardNumber;
        this.amount = amount;
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

    public String getStatus() {
        return status;
    }

    public long getTimestamp() {
        return timestamp;
    }
}
