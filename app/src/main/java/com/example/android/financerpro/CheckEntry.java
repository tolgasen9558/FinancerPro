package com.example.android.financerpro;


public class CheckEntry {

    private String personPaid;
    private String info;
    private Double amount;

    public CheckEntry(String personPaid, String info, Double amount) {
        this.personPaid = personPaid;
        this.info = info;
        this.amount = amount;
    }

    public String getPersonPaid() {
        return personPaid;
    }

    public void setPersonPaid(String personPaid) {
        this.personPaid = personPaid;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
