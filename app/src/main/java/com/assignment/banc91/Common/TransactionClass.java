package com.assignment.banc91.Common;

public class TransactionClass {



    private String transactionId;
    private boolean isCredited;
    private String transactionDate;
    private String amount;


    public TransactionClass (String transactionId,String transactionDate,String amount,boolean isCredited){
        this.amount=amount;
        this.isCredited=isCredited;
        this.transactionDate=transactionDate;
        this.transactionId=transactionId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public boolean isCredited() {
        return isCredited;
    }

    public void setCredited(boolean credited) {
        isCredited = credited;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
