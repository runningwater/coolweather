package com.android.study.coolweather.gson.weather;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by runningwater on 2017/7/24.
 */

public class Weather {
    public String status;
    public Basic basic;
    public AQI aqi;
    public Now now;
    public Suggestion suggestion;
    @SerializedName("daily_forecast")
    public List<Forcast> forcastList;
}
