package com.example.android.financerpro.DataModels;


public class DebtEntry {

    String personPays;
    String personReceives;
    Double amount;

    public DebtEntry(String personPays, String personReceives, Double amount) {
        this.personPays = personPays;
        this.personReceives = personReceives;
        this.amount = amount;
    }

    public String getPersonPays() {
        return personPays;
    }

    public void setPersonPays(String personPays) {
        this.personPays = personPays;
    }

    public String getPersonReceives() {
        return personReceives;
    }

    public void setPersonReceives(String personReceives) {
        this.personReceives = personReceives;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
