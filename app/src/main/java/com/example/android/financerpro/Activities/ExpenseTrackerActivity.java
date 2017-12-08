package com.example.android.financerpro.Activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.android.financerpro.Adapters.ExpenseListAdapter;
import com.example.android.financerpro.BaseActivities.BaseDrawerActivity;
import com.example.android.financerpro.ExpenseEntry;
import com.example.android.financerpro.Fragments.ExpenseDialogFragment;
import com.example.android.financerpro.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ExpenseTrackerActivity extends BaseDrawerActivity {

    private List<ExpenseEntry> expensesList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ExpenseListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContent(R.layout.activity_expense_tracker);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recyclerview_expenses);

        mAdapter = new ExpenseListAdapter(expensesList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        prepareData();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ExpenseDialogFragment().show(getFragmentManager(), "TAG");
            }
        });
    }


    private void prepareData(){
        expensesList.add(new ExpenseEntry("Expense 1", new Date(), "Category 1", 15.5));
        expensesList.add(new ExpenseEntry("Expense 1", new Date(), "Category 1", 15.5));
        expensesList.add(new ExpenseEntry("Expense 1", new Date(), "Category 1", 15.5));
        expensesList.add(new ExpenseEntry("Expense 1", new Date(), "Category 1", 15.5));
        expensesList.add(new ExpenseEntry("Expense 1", new Date(), "Category 1", 15.5));

        mAdapter.notifyDataSetChanged();
    }
}
