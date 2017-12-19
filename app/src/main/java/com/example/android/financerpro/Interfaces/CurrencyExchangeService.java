package com.example.android.financerpro.Interfaces;


import com.example.android.financerpro.DataModels.CurrencyExchange;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Drystan on 12/14/17.
 */

public interface CurrencyExchangeService  {
    @GET("/latest?base=USD")
    Call<CurrencyExchange> loadCurrencyExchange();
}
