package com.example.travel4.retrofit;

import com.google.gson.internal.LinkedTreeMap;

public class Weather {

    private LinkedTreeMap main;

    private LinkedTreeMap wind;

    private LinkedTreeMap clouds;

    public int getCurrentTemperature() {
        return (int) Math.ceil((double) main.get("temp"));
    }

    public int getMinTemperature() {
        return (int) Math.ceil((double) main.get("temp_min"));
    }

    public int getMaxTemperature() {
        return (int) Math.ceil((double) main.get("temp_max"));
    }

    public int getHumidity() {
        return (int) Math.ceil((double) main.get("humidity"));
    }

    public int getWind() {
        return (int) Math.ceil((double) wind.get("speed"));
    }

    public int getClouds() {
        return (int) Math.ceil((double) clouds.get("all"));
    }


    @Override
    public String toString() {
        return "Weather{" +
                "main=" + main +
                ", wind=" + wind +
                ", clouds=" + clouds +
                '}';
    }
}
