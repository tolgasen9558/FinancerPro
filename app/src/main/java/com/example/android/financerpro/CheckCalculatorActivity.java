package com.example.android.financerpro;

import android.os.Bundle;

import com.example.android.financerpro.BaseActivities.BaseDrawerActivity;

public class CheckCalculatorActivity extends BaseDrawerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContent(R.layout.activity_check_calculator);
    }
}
