package com.example.android.financerpro.DataModels;


public class BalanceEntry {

    private String personName;
    private Double amountPaid;
    private Double balance;


    public BalanceEntry(String personName, Double amountPaid) {
        this.personName = personName;
        this.amountPaid = amountPaid;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public Double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(Double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
