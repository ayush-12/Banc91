package com.assignment.banc91.Common;

import java.util.List;
import java.util.Map;

public class CardDetails {

    private String cardNumber;
    private String cvv;
    private String expiry;
    private boolean isActive;
    private List<Map<String,Object>> transactions;


    public CardDetails(){}
    public CardDetails(String cardNumber,String cvv,String expiry,
                       boolean isActive, List<Map<String,Object>> transactions){

        this.cardNumber=cardNumber;
        this.cvv=cvv;
        this.expiry=expiry;
        this.isActive=isActive;
        this.transactions =transactions;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getExpiry() {
        return expiry;
    }

    public void setExpiry(String expiry) {
        this.expiry = expiry;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public List<Map<String, Object>> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Map<String, Object>> transactions) {
        this.transactions = transactions;
    }
}
