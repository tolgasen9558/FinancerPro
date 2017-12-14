package com.example.android.financerpro.DataModels;

import android.graphics.Bitmap;

import java.util.Date;


public class ExpenseEntry2 {

    private String name;
    private Date date;
    private String category;
    private Bitmap imageBitmap;
    private Double amount;

    public ExpenseEntry2(String name, Date date, String category, Double amount, Bitmap image) {
        this.name = name;
        this.date = date;
        this.category = category;
        this.amount = amount;
        this.imageBitmap = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Bitmap getImageBitmap() {
        return imageBitmap;
    }

    public void setImageBitmap(Bitmap imageBitmap) {
        this.imageBitmap = imageBitmap;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}