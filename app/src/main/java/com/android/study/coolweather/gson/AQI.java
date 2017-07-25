package com.android.study.coolweather.gson;

/**
 * Created by runningwater on 2017/7/24.
 */

public class AQI {
    public AQICity city;

    public class AQICity {
        public String aqi;
        public String pm25;
        public String qlty;
    }
}
