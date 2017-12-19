package com.example.android.financerpro.Activities;

import android.os.Bundle;

import com.example.android.financerpro.BaseActivities.BaseDrawerActivity;
import com.example.android.financerpro.FinancerAppData;
import com.example.android.financerpro.R;

public class MainActivity extends BaseDrawerActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContent(R.layout.activity_mainn);

        FinancerAppData.getInstance().initalise(this);
    }
}
