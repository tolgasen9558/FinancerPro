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
import com.example.android.financerpro.FinancerAppData;
import com.example.android.financerpro.Fragments.ExpenseDialogFragment;
import com.example.android.financerpro.R;

public class ExpenseTrackerActivity extends BaseDrawerActivity {

    private RecyclerView recyclerView;
    private ExpenseListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContent(R.layout.activity_expense_tracker);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.recyclerview_expenses);

        mAdapter = new ExpenseListAdapter();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        FinancerAppData.getInstance().setExpenseListAdapter(mAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ExpenseDialogFragment().show(getSupportFragmentManager(), "TAG");
            }
        });
    }
}
