package com.example.openweather.api;

import com.example.openweather.model.Forecast;
import com.example.openweather.model.ForecastMain;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DataService {

    @GET("/data/2.5/weather")
    Call<Forecast> getForecast(@Query("appid") String appId,
                               @Query("units") String units,
                               @Query("q") String location);
//?appid=6ce076990a18909a88f380f89a02aef4&units=metric&q=Curitiba,br"
}
