package com.example.openweather.model;

public class Forecast {

    String base;
    ForecastMain main;

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public ForecastMain getMain() {
        return main;
    }

    public void setMain(ForecastMain main) {
        this.main = main;
    }
}
