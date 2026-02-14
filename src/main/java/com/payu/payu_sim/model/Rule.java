package com.payu.payu_sim.model;

public class Rule {

    private String cardPrefix;
    private Double amountGreaterThan;
    private String response;


    public String getCardPrefix() {
        return cardPrefix;
    }

    public void setCardPrefix(String cardPrefix) {
        this.cardPrefix = cardPrefix;
    }


    public Double getAmountGreaterThan() {
        return amountGreaterThan;
    }

    public void setAmountGreaterThan(Double amountGreaterThan) {
        this.amountGreaterThan = amountGreaterThan;
    }


    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
