package com.example.android.financerpro;

import android.graphics.Bitmap;

import com.example.android.financerpro.Adapters.BillsListAdapter;
import com.example.android.financerpro.Adapters.ExpenseListAdapter;
import com.example.android.financerpro.Adapters.PeopleListAdapter;
import com.example.android.financerpro.DataModels.CheckEntry;
import com.example.android.financerpro.DataModels.ExpenseEntry;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class FinancerAppData {

    private static FinancerAppData instance = null;

    private List<CheckEntry> checkEntries = new ArrayList<>();
    private HashMap<String, Double> moneySpent = new HashMap<>();
    private List<ExpenseEntry> expensesList = new ArrayList<>();

    private PeopleListAdapter peopleListAdapter;
    private BillsListAdapter billsListAdapter;
    private ExpenseListAdapter expenseListAdapter;


    protected FinancerAppData() {
        // Exists only to defeat instantiation.
    }
    public static FinancerAppData getInstance() {
        if(instance == null) {
            instance = new FinancerAppData();
        }
        return instance;
    }

    public List<CheckEntry> getCheckEntries() {
        return checkEntries;
    }

    public void addNewCheck(CheckEntry check){
        if(check.getAmount() != 0){
            checkEntries.add(check);
        }
        String name = check.getPersonPaid();
        Double amount = check.getAmount();
        if (!moneySpent.containsKey(name)) {
            moneySpent.put(name, amount);
        }
        else {
            moneySpent.put(name, moneySpent.get(name) + amount);
        }

        updateData();
    }

    public void addNewExpense(String description, Date date, String category
            , Double amount, Bitmap image) {
        expensesList.add(new ExpenseEntry(description, date, category, amount, image));
        expenseListAdapter.notifyDataSetChanged();
    }

    public List<String> getPeopleNames() {
        return new ArrayList<>(moneySpent.keySet());
    }

    public Double getMoneySpent(String personName) {
        return moneySpent.get(personName);
    }

    private void updateData(){
        peopleListAdapter.notifyDataSetChanged();
        billsListAdapter.notifyDataSetChanged();
    }

    public void setPeopleListAdapter(PeopleListAdapter peopleListAdapter) {
        this.peopleListAdapter = peopleListAdapter;
    }

    public void setBillsListAdapter(BillsListAdapter billsListAdapter) {
        this.billsListAdapter = billsListAdapter;
    }

    public ExpenseListAdapter getExpenseListAdapter() {
        return expenseListAdapter;
    }

    public List<ExpenseEntry> getExpensesList() {
        return expensesList;
    }

    public void setExpenseListAdapter(ExpenseListAdapter expenseListAdapter) {
        this.expenseListAdapter = expenseListAdapter;
    }
}
