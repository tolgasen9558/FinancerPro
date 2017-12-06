package com.example.android.financerpro;

import android.os.Bundle;

import com.example.android.financerpro.BaseActivities.BaseDrawerActivity;

/**
 * Created by Drystan on 12/6/17.
 */

public class CurrencyConverterActivity extends BaseDrawerActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContent(R.layout.activity_currency_convert);
    }

}
