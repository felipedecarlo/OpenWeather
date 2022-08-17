package com.example.openweather.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitConfig {

    private final Retrofit retrofit;

    public RetrofitConfig() {
        this.retrofit = new Retrofit.Builder()
//                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .baseUrl("https://api.openweathermap.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public DataService getDataService() {
        return this.retrofit.create(DataService.class);
    }
}
