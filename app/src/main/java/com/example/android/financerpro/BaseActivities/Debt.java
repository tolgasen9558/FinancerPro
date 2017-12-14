package com.example.android.financerpro.BaseActivities;

/**
 * Created by shellytaylor on 12/12/17.
 */


public class Debt {
    private double value;
    private String name, contact;

    public Debt() {
    }

    public Debt(String name, double value, String contact) {
        this.name = name;
        this.value = value;
        this.contact = contact;
    }

    public Debt(String name, double value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) { this.value = value; }
}