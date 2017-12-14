package com.example.android.financerpro;

import android.annotation.SuppressLint;
import android.content.Context;
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

    @SuppressLint("StaticFieldLeak")
    private static FinancerAppData instance = null;

    private List<CheckEntry> checkEntries = new ArrayList<>();
    private HashMap<String, Double> moneySpent = new HashMap<>();
    private List<ExpenseEntry> expensesList = new ArrayList<>();

    private PeopleListAdapter peopleListAdapter;
    private BillsListAdapter billsListAdapter;
    private ExpenseListAdapter expenseListAdapter;

    private Context context;

    protected FinancerAppData() {
        // Exists only to defeat instantiation.
    }
    public static FinancerAppData getInstance() {
        if(instance == null) {
            instance = new FinancerAppData();
        }
        return instance;
    }

    public void initalise(Context context) {
        this.context = context;
//        loadData();
    }

    //API FUNCTIONS
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

    public void deleteBill(int position) {
        CheckEntry entry = checkEntries.get(position);
        String name = entry.getPersonPaid();
        if (moneySpent.containsKey(name)) {
            moneySpent.put(name, moneySpent.get(name) - entry.getAmount());
            if (moneySpent.get(name) <= 0) {
                moneySpent.remove(name);
            }
        }
        checkEntries.remove(position);
        updateData();
    }

    public void deleteExpense(int position) {
        expensesList.remove(position);
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

//    private void saveData(){
//        SharedPreferences sharedPref = context.getSharedPreferences(
//                PREFERENCE_FILE_KEY, Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPref.edit();
//        for (CheckEntry entry : checkEntries) {
//            editor.putString(CHECK_PREFIX + entry.getPersonPaid(), entry.getPersonPaid());
//        }
//        editor.apply();
//
//    }
//
//    private void loadData(){
//
//    }



    //Getters and Setters
    public void setPeopleListAdapter(PeopleListAdapter peopleListAdapter) {
        this.peopleListAdapter = peopleListAdapter;
    }

    public void setBillsListAdapter(BillsListAdapter billsListAdapter) {
        this.billsListAdapter = billsListAdapter;
    }

    public List<ExpenseEntry> getExpensesList() {
        return expensesList;
    }

    public void setExpenseListAdapter(ExpenseListAdapter expenseListAdapter) {
        this.expenseListAdapter = expenseListAdapter;
    }

    public PeopleListAdapter getPeopleListAdapter() {
        return peopleListAdapter;
    }

    public BillsListAdapter getBillsListAdapter() {
        return billsListAdapter;
    }

    public ExpenseListAdapter getExpenseListAdapter() {
        return expenseListAdapter;
    }
}
